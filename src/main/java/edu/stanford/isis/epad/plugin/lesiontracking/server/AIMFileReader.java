package edu.stanford.isis.epad.plugin.lesiontracking.server;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMAttribute;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMElement;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageReference;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageReferenceCollection;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageStudy;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.Patient;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.Person;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.Study;

public class AIMFileReader
{
    public static String getStudyDate(ImageReferenceCollection imageReferenceCollection)
    {
        for(int i = 0; i < imageReferenceCollection.getNumberOfImageReferences(); i++)
        {
            ImageReference imageReference = imageReferenceCollection.getImageReference(i);

            /**
             * This is backwards compatibility code. If ImageStudy doesn't exist,
             * we will look for the old Study format.
             */
            if(imageReference.getNumberOfImageStudies() > 0)
            {
                for(int j = 0; j < imageReference.getNumberOfImageStudies(); j++)
                {
                    ImageStudy imageStudy = imageReference.getImageStudy(j);

                    // TODO: Find out why there are nested ImageStudies.
                    for(int k = 0; k < imageStudy.getNumberOfImageStudies();)
                    {
                        ImageStudy imageStudy2 = imageStudy.getImageStudy(k);

                        /**
                         * There are currently two date/time formats:
                         *
                         * 1. 2004-12-02T15:14:21
                         * 2. 20041202
                         */

                        // This is for the first type.
                        String studyDateAndTime = imageStudy2.getStartDate();
                        int timeStart = studyDateAndTime.indexOf('T');
                        // Try to get just the date (without the time)
                        if(timeStart > -1)
                            return studyDateAndTime.substring(0, timeStart);


                        // This is for the second type.
                        if(studyDateAndTime.length() == 8)
                            return studyDateAndTime.substring(0, 4) + "-" + studyDateAndTime.substring(4, 6) + "-" + studyDateAndTime.substring(6, 8);

                        return studyDateAndTime;
                    }
                }
            }
            else
            {
                for(int j = 0; j < imageReference.getNumberOfStudies(); j++)
                {
                    Study study = imageReference.getStudy(j);

                    // TODO: Find out why there are nested studies.
                    for(int k = 0; k < study.getNumberOfStudies();)
                    {
                        Study study2 = study.getStudy(k);
                        String studyDateAndTime = study2.getStudyDate();
                        int timeStart = studyDateAndTime.indexOf('T');

                        // Try to get just the date (without the time)
                        if(timeStart > -1)
                            return studyDateAndTime.substring(0, timeStart);

                        return studyDateAndTime;
                    }
                }
            }
        }

        return null;
    }

    public String getPatientName(ImageAnnotation imageAnnotation)
    {
        /** This is a backwards compatibility check. If we cannot find any "Person"
         *  objects, we will use the older object "Patient".
         */
        if(imageAnnotation.getNumberOfPersons() > 0)
        {
            for(int i = 0; i < imageAnnotation.getNumberOfPersons(); i++)
            {
                Person person = imageAnnotation.getPerson(i);

                for( int j = 0; j < person.getNumberOfPersons(); )
                {
                    // TODO: Find out why we need a sub person.
                    Person person2 = person.getPerson(j);

                    return person2.getNameAttribute();
                }
            }
        }
        else
        {
            for(int i = 0; i < imageAnnotation.getNumberOfPatients(); i++)
            {
                Patient patient = imageAnnotation.getPatient(i);

                for( int j = 0; j < patient.getNumberOfPatients();)
                {
                    // TODO: Find out why we need a sub patient.
                    Patient patient2 = patient.getPatient(j);

                    return patient2.getNameAttribute();
                }
            }
        }

        return "";
    }

    private static void recursivelyParse(Node node, AIMElement aimElement)
    {
        if(node.getNodeType() != Node.ELEMENT_NODE) return;

        Element element = (Element)node;
        NamedNodeMap attributesList = element.getAttributes();
        
        //System.out.println("Getting AIM attribtues for aimElement "  + aimElement.getName());

        String[] allAIMAttributeNames = aimElement.getAllAIMAttributeNames();
        for(int i = 0; i < attributesList.getLength(); i++)
        {
            Node attributeNode = attributesList.item(i);
            if(attributeNode.getNodeType() != Node.ATTRIBUTE_NODE) continue;

            Attr attribute = (Attr)attributeNode;
            for( int j = 0; j < allAIMAttributeNames.length; j++ )
            {
                String attributeName = attribute.getName();
                if(!attributeName.equalsIgnoreCase(allAIMAttributeNames[j])) continue;

                String attributeValue = attribute.getValue();
                aimElement.addAIMAttribute(new AIMAttribute(attributeName,attributeValue));
            }
        }

        NodeList allChildNodes = element.getChildNodes();
        String[] allAIMElementNames = aimElement.getAllAIMElementNames();

        for( int i = 0; i < allChildNodes.getLength(); i++ )
        {
            Node childNode = allChildNodes.item(i);

            if(childNode.getNodeType() != Node.ELEMENT_NODE) continue;

            Element childElement = (Element)childNode;
            String childElementName = childElement.getNodeName();
            
            boolean foundElementName = false;
            for( int j = 0; j < allAIMElementNames.length; j++ )
            {
                String childAIMElementName = allAIMElementNames[j];
                if(!childElementName.equalsIgnoreCase(childAIMElementName))continue;
                
                foundElementName = true;
                AIMElement childAIMElement = AIMElement.getAIMElementOfType(childAIMElementName);
                recursivelyParse(childElement, childAIMElement);
                aimElement.addAIMElement(childAIMElement);
            }
            
            if(!foundElementName)
            {
            	//System.out.println("Could not find element: " + childElementName + " from parent element: " + element.getNodeName());
            }
        }
    }

    public static ImageAnnotation parseAIMString(String aimData)
    {
        ImageAnnotation imageAnnotation = new ImageAnnotation();

        try
        {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new ByteArrayInputStream(aimData.getBytes()));
            doc.getDocumentElement().normalize();
            recursivelyParse(doc.getDocumentElement(), imageAnnotation);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return imageAnnotation;
    }
    
    public static ImageAnnotation parseAIMFile(File file)
    {
        try
        {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            
            return parseImageAnnotationFromNode(doc.getDocumentElement(), file.getName());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public static ImageAnnotation parseImageAnnotationFromNode(Node node, String imageAnnotationName)
    {
    	ImageAnnotation imageAnnotation = new ImageAnnotation(imageAnnotationName);
        recursivelyParse(node, imageAnnotation);
    	return imageAnnotation;
    }

    public static ImageAnnotation[][] loadAndSortAIMFilesByStudyDate(List<String> imageAnnotationFiles)
    {
        ArrayList<ArrayList<ImageAnnotation>> imageAnnotationsAsAL = new ArrayList<ArrayList<ImageAnnotation>>();
        HashMap<String, Integer> lesionToIndexMap = new HashMap<String, Integer>();
        HashMap<Integer, String> indexToLesionMap = new HashMap<Integer, String>();

        // Split the AIM files according to their study dates.
        int numberOfLesions = 0;
        for(int i = 0; i < imageAnnotationFiles.size(); i++)
        {
           // ImageAnnotation imageAnnotation = parseAIMFile(new File(EPadFilesImpl.SERVLET_PATH + "/" + imageAnnotationNames[i]));
            ImageAnnotation imageAnnotation = parseAIMFile(new File(imageAnnotationFiles.get(i)));
            String studyDate = getStudyDate(imageAnnotation.getImageReferenceCollection(0));

            if(!lesionToIndexMap.containsKey(studyDate))
            {
                imageAnnotationsAsAL.add(new ArrayList<ImageAnnotation>());
                lesionToIndexMap.put(studyDate, numberOfLesions);
                indexToLesionMap.put(numberOfLesions, studyDate);
                numberOfLesions++;
            }

            imageAnnotationsAsAL.get(lesionToIndexMap.get(studyDate)).add(imageAnnotation);
        }

        // Sort the imageAnnotationByLesion array by study date.
        PriorityQueue<Calendar> priorityQueue = new PriorityQueue<Calendar>();
        Set<String> allStudyDates = lesionToIndexMap.keySet();
        Iterator<String> allStudyDatesIterator = allStudyDates.iterator();
        while(allStudyDatesIterator.hasNext())
        {
            // Convert String date to an actual Calendar object.
            Calendar calendar = Calendar.getInstance();
            String currentDate = allStudyDatesIterator.next().trim();
            String[] yymmdd = currentDate.split("-");
            calendar.set(Integer.parseInt(yymmdd[0]), Integer.parseInt(yymmdd[1])-1, Integer.parseInt(yymmdd[2]));
            priorityQueue.add(calendar);
        }

        int newIndex = 0;
        while(!priorityQueue.isEmpty())
        {
            Calendar calendar = priorityQueue.remove();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String calendarAsString = simpleDateFormat.format(calendar.getTime());
            int oldIndex = lesionToIndexMap.get(calendarAsString);
            if(newIndex != oldIndex)
            {
                ArrayList<ImageAnnotation> swapOut = imageAnnotationsAsAL.get(newIndex);
                imageAnnotationsAsAL.set(newIndex, imageAnnotationsAsAL.get(oldIndex));
                imageAnnotationsAsAL.set(oldIndex, swapOut);
                lesionToIndexMap.put(calendarAsString, newIndex);

                String oldLesionName = indexToLesionMap.get(newIndex);
                lesionToIndexMap.put(oldLesionName, oldIndex);
                indexToLesionMap.put(newIndex, calendarAsString);
                indexToLesionMap.put(oldIndex, oldLesionName);
            }
            newIndex++;
        }

        ImageAnnotation[][] imageAnnotationByLesion = new ImageAnnotation[imageAnnotationsAsAL.size()][];
        for(int i = 0; i < imageAnnotationByLesion.length; i++)
        {
            ImageAnnotation[] currentImageAnnotationArray = new ImageAnnotation[imageAnnotationsAsAL.get(i).size()];
            for(int j = 0; j < currentImageAnnotationArray.length; j++)
                currentImageAnnotationArray[j] = imageAnnotationsAsAL.get(i).get(j);
            imageAnnotationByLesion[i] = currentImageAnnotationArray;

        }

        return imageAnnotationByLesion;
    }

    public static void main(String[] args)
    {
        /*
        String[] aimFilenames =

               {"src/main/webapp/aim_files/20101120_Definiens/T0_out0.xml",
                "src/main/webapp/aim_files/20101120_Definiens/T0_out1.xml",
                "src/main/webapp/aim_files/20101120_Definiens/T0_out2.xml",
                "src/main/webapp/aim_files/20101120_Definiens/T1_out0.xml",
                "src/main/webapp/aim_files/20101120_Definiens/T1_out1.xml",
                "src/main/webapp/aim_files/20101120_Definiens/T1_out2.xml",
                "src/main/webapp/aim_files/20101120_Definiens/T2_out0.xml",
                "src/main/webapp/aim_files/20101120_Definiens/T2_out1.xml",
                "src/main/webapp/aim_files/20101120_Definiens/T2_out2.xml"};

        int studyDateIndex = 0;
        ImageAnnotation[][] imageAnnotationsByStudyDate = loadAndSortAIMFilesByStudyDate(aimFilenames);
        for(ImageAnnotation[] imageAnnotationsOnStudyDate : imageAnnotationsByStudyDate)
        {
            System.out.println("Study date index [" + studyDateIndex + "].");
            studyDateIndex++;

            for(ImageAnnotation imageAnnotation : imageAnnotationsOnStudyDate)
            {
                String studyDate = imageAnnotation.getImageReferenceCollection(0).getImageReference(0).getImageStudy(0).getImageStudy(0).getStartDate();
                System.out.println(studyDate);
            }

            System.out.println();
        }

        CalculationResult cr = RECISTCalculator.calculateRECIST(imageAnnotationsByStudyDate, new String[] {"Diameter", "Circumference", "Cross-section area", "Mean density"});
        for(int i = 0; i < cr.getResponseRates().length; i++)
        {
            System.out.println();
            for(int j = 0; j < cr.getResponseRates()[i].length; j++)
            {
                System.out.println(cr.getResponseRates()[i][j]);
            }
        }

        LesionTrackingServiceImpl ltsi = new LesionTrackingServiceImpl();
        ltsi.downloadAsImage(cr);*/

        //SeriesGraph sg = new SeriesGraph(new TumorAnalysisCalculator[] { tac } );
        //sg.drawSeriesGraphToFile("war/image.jpg");
    }
}