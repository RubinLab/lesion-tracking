package edu.stanford.isis.epad.plugin.lesiontracking.server;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import edu.stanford.isis.epad.plugin.lesiontracking.client.recist.ImageAnnotationUtility;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation;

public class EPadFilesImpl
{
    private static final String AIM_DIRECTORY = "aim_files";

    private String servletPath;
    private List<String> imageAnnotationFileNames;
    private ImageAnnotation[][] imageAnnotationsByStudyDate;

    public EPadFilesImpl(String servletPath)
    {
        this.servletPath = servletPath;
    }

    /*
    public ImageAnnotation[][] getImageAnnotationsByStudyDate()
    {
        if(imageAnnotationsByStudyDate == null)
            loadImageAnnoationsByStudyDate();

        return imageAnnotationsByStudyDate;
    }
	*/
    
    private void loadImageAnnotations(String path)
    {
        File root = new File(path);
        File[] list = root.listFiles();

        for(File f : list)
            if(f.isDirectory())
                loadImageAnnotations(f.getAbsolutePath());
            else
            {
                String fileName = f.getName();
                if(fileName != null && fileName.length() > 3)
                {
                    String extension = fileName.substring(fileName.length() - 3, fileName.length());
                    if("xml".equalsIgnoreCase(extension) && !imageAnnotationFileNames.contains(fileName))
                        imageAnnotationFileNames.add(f.getAbsolutePath());
                }
            }
    }

    /*
    private void loadImageAnnoationsByStudyDate()
    {
        imageAnnotationFileNames = new ArrayList<String>();
        loadImageAnnotations(servletPath + "/" + AIM_DIRECTORY);

        imageAnnotationsByStudyDate = AIMFileReader.loadAndSortAIMFilesByStudyDate(imageAnnotationFileNames);
    }
    */

    /*
    // TODO: This needs to be in the EPadFiles spec.
    public List<String> getPatientNames()
    {
        loadImageAnnoationsByStudyDate();

        List<String> patientNames = new ArrayList<String>();

        for(ImageAnnotation[] imageAnnotations : imageAnnotationsByStudyDate)
            for(ImageAnnotation imageAnnotation : imageAnnotations)
            {
                String patientName = ImageAnnotationUtility.getPatientName(imageAnnotation);

                if(!patientNames.contains(patientName))
                    patientNames.add(patientName);
            }

        return patientNames;
    }
    */
}
