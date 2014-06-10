package edu.stanford.isis.epad.plugin.lesiontracking.client.recist;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.CurrencyData;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.i18n.client.constants.NumberConstants;

import edu.stanford.isis.epad.plugin.lesiontracking.shared.AnatomicEntity;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.AnatomicEntityCollection;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.DateTimeFormat;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.Image;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageReference;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageReferenceCollection;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageSeries;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageStudy;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.Series;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.SharedNumberFormat;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.Study;

public class RECISTCalculator
{
    @SuppressWarnings("deprecation")
    public static ImageAnnotation[][] loadAndSortAIMFilesByStudyDate(List<ImageAnnotation> imageAnnotations)
    {
        ArrayList<ArrayList<ImageAnnotation>> imageAnnotationsAsAL = new ArrayList<ArrayList<ImageAnnotation>>();
        HashMap<String, Integer> lesionToIndexMap = new HashMap<String, Integer>();
        HashMap<Integer, String> indexToLesionMap = new HashMap<Integer, String>();

        // Split the AIM files according to their study dates.
        int numberOfLesions = 0;
        for(ImageAnnotation imageAnnotation : imageAnnotations)
        {
            String studyDate = ImageAnnotationUtility.getStudyDate(imageAnnotation.getImageReferenceCollection(0));

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
        PriorityQueue<Date> priorityQueue = new PriorityQueue<Date>();
        Set<String> allStudyDates = lesionToIndexMap.keySet();
        Iterator<String> allStudyDatesIterator = allStudyDates.iterator();
        while(allStudyDatesIterator.hasNext())
        {
            String currentDate = allStudyDatesIterator.next().trim();
            String[] yymmdd = currentDate.split("-");
            Date date = new Date();
            date.setYear(Integer.parseInt(yymmdd[0]) - 1900);
            date.setMonth(Integer.parseInt(yymmdd[1])-1);
            date.setDate(Integer.parseInt(yymmdd[2]));
            priorityQueue.add(date);
        }

        int newIndex = 0;
        while(!priorityQueue.isEmpty())
        {
            Date date = priorityQueue.remove();
            DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("yyyy-MM-dd");
            String calendarAsString = dateTimeFormat.format(date);
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

    public static CalculationResult calculateRECIST(ImageAnnotation[][] imageAnnotationsByStudyDate, String[] metrics, SharedNumberFormat df)
    {
        // Perform a calculation for each metric.
        TumorAnalysisCalculator[] tumorAnalysisCalculators = new TumorAnalysisCalculator[metrics.length];
        for(int i = 0; i < metrics.length; i++)
            tumorAnalysisCalculators[i] = new TumorAnalysisCalculator(imageAnnotationsByStudyDate, metrics[i], df);

        // Create an array of lesion references. Each one holds a lesion
        // and its information that changes over time.
        Lesion[] lesions = new Lesion[imageAnnotationsByStudyDate[0].length];
        for(int i = 0; i < lesions.length; i++)
        {
            ImageAnnotation ia = imageAnnotationsByStudyDate[0][i];
            String loc = "N/A";
            if(ia.getNumberOfAnatomicEntityCollections() > 0)
            {
                AnatomicEntityCollection aec = ia.getAnatomicEntityCollection(0);
                if(aec.getNumberOfAnatomicEntities() > 0)
                {
                    AnatomicEntity ae = aec.getAnatomicEntity(0);
                    loc = ae.getCodeMeaning();
                }
            }

            lesions[i] = new Lesion();
            lesions[i].setLesionID(Integer.toString(i));
            lesions[i].setLocation(loc);

            // For each study date, copy the lesion's information.
            for(int j = 0; j < imageAnnotationsByStudyDate.length; j++)
            {
            	if( i >= imageAnnotationsByStudyDate[j].length )
            		continue;
            	
                ia = imageAnnotationsByStudyDate[j][i];
                lesions[i].addWADOURL(constructWADOURL(ia));

                for(int k = 0; k < tumorAnalysisCalculators.length; k++)
                {
                    String metric = tumorAnalysisCalculators[k].getMetric();
                    String[][] metricValues =  tumorAnalysisCalculators[k].getMetricValues();

                    if( i < metricValues[j].length )
                    {
                        lesions[i].addTemporalMeasurementForMetric(metric, metricValues[j][i]);

                        /*
                        // Overlay the ROI on the image.
                        BufferedImage   bi  = WADORequest.getImage(ia);
                        ROI             roi = ROI.extractROI(ia);

                        if(ia != null && bi != null && roi != null)
                        {
                            File file = createRandomImageFile(servletPath);
                            ROIDraw.drawImageToFile(bi, roi, file);
                            lesions[i].addWADOURL(ROI_IMAGES_RELATIVE + file.getName());
                        }*/
                    }
                }
            }
        }

        /*
        SeriesGraph seriesGraph = new SeriesGraph(tumorAnalysisCalculators);

        File file = createRandomImageFile(servletPath);

        seriesGraph.drawSeriesGraphToFile(servletPath + "/" + ANALYSIS_IMAGES_RELATIVE + file.getName());
*/
        CalculationResult calculationResult = new CalculationResult();

//        calculationResult.setAnalysisImageURL(ANALYSIS_IMAGES_RELATIVE + file.getName());

        String[][] metricSums = new String[tumorAnalysisCalculators.length][];
        String[] metricUnits = new String[tumorAnalysisCalculators.length];
        String[][] responseRates = new String[tumorAnalysisCalculators.length][];
        String[][] responseRatesSinceBaseline = new String[tumorAnalysisCalculators.length][];
        String[][] responseRatesSinceNadir = new String[tumorAnalysisCalculators.length][];
        String[][] responseCategories = new String[tumorAnalysisCalculators.length][];
        String[][] studyDates = new String[tumorAnalysisCalculators.length][];

        for(int i = 0; i < tumorAnalysisCalculators.length; i++)
        {
            metricSums[i] = tumorAnalysisCalculators[i].getMetricSums();
            metricUnits[i] = tumorAnalysisCalculators[i].getBaselineUnitOfMeasure();
            responseRates[i] = tumorAnalysisCalculators[i].getResponseRates();
            responseRatesSinceBaseline[i] = tumorAnalysisCalculators[i].getResponseRatesSinceBaseline();
            responseRatesSinceNadir[i] = tumorAnalysisCalculators[i].getResponseRatesSinceNadir();
            responseCategories[i] = tumorAnalysisCalculators[i].getResponseCategories();
            studyDates[i] = tumorAnalysisCalculators[i].getStudyDates();
        }

        calculationResult.setNumberOfTimePointsInStudy(imageAnnotationsByStudyDate.length);
        calculationResult.setMetricSums(metricSums);
        calculationResult.setMetricUnits(metricUnits);
        calculationResult.setResponseRates(responseRates);
        calculationResult.setResponseRatesSinceBaseline(responseRatesSinceBaseline);
        calculationResult.setResponseRatesSinceNadir(responseRatesSinceNadir);
        calculationResult.setResponseCategories(responseCategories);
        calculationResult.setMetrics(metrics);
        calculationResult.setLesions(lesions);
        calculationResult.setStudyDates(studyDates);



        return calculationResult;
    }

    public static String constructWADOURL(ImageAnnotation ia)
    {
        String wadoURL = "";

        if(ia.getNumberOfImageReferenceCollections() > 0)
        {
            ImageReferenceCollection irc = ia.getImageReferenceCollection(0);

            if(irc.getNumberOfImageReferences() > 0)
            {
                ImageReference ir = irc.getImageReference(0);

                if(ir.getNumberOfImageStudies() > 0)
                {
                    ImageStudy  imageStudy  = ir.getImageStudy(0).getImageStudy(0);
                    ImageSeries imageSeries = imageStudy.getImageSeries(0).getImageSeries(0);
                    Image 	    image 	    = imageSeries.getImageCollection(0).getImage(0);

                    String studyUID  = imageStudy.getInstanceUID();
                    String seriesUID = imageSeries.getInstanceUID();
                    String objectUID = image.getSOPInstanceUID();

                    wadoURL = createWADOURL(studyUID, seriesUID, objectUID);
                }
                else
                    if(ir.getNumberOfStudies() > 0) ///< This is backwards compatibility code.
                    {
                        Study  study  = ir.getStudy(0).getStudy(0);
                        Series series = study.getSeries(0).getSeries(0);
                        Image  image  = series.getImageCollection(0).getImage(0);

                        String studyUID  = study.getInstanceUID();
                        String seriesUID = series.getInstanceUID();
                        String objectUID = image.getSOPInstanceUID();

                        wadoURL = createWADOURL(studyUID, seriesUID, objectUID);
                    }
            }
        }

        return wadoURL;
    }

    public static String createWADOURL(String studyUID, String seriesUID, String objectUID)
    {
    	String URL = 
    			"http://epad-dev2.stanford.edu:8080/epad/wado/?requestType=WADO" +
    					"&studyUID=" + studyUID + "&seriesUID=" + seriesUID + "&objectUID=" + objectUID;
//        String URL =
//            "http://rufus.stanford.edu:8090/wado?requestType=WADO" +
//                    "&studyUID=" + studyUID + "&seriesUID=" + seriesUID + "&objectUID=" + objectUID;

        return URL;
    }
}
