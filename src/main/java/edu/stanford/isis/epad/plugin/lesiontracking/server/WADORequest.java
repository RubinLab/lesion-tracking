package edu.stanford.isis.epad.plugin.lesiontracking.server;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import edu.stanford.isis.epad.plugin.lesiontracking.shared.Image;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageReference;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageReferenceCollection;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageSeries;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageStudy;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.Series;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.Study;

public class WADORequest
{
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

        System.out.println(wadoURL);
        return wadoURL;
    }

    /** Simple function that gets an image from a given URL. **/
    private static BufferedImage getImage(String url)
    {
        try{ return ImageIO.read(new URL(url)); }
        catch(MalformedURLException malformedURLException){ return null; }
        catch(IOException ioException){ return null; }
    }

    public static BufferedImage getImage(ImageAnnotation ia)
    {
        return getImage(constructWADOURL(ia));
    }

    public static void main(String[] args)
    {
/*
        String studyUID  = "1.2.752.24.7.19011385.453825",
               seriesUID = "1.2.840.113704.1.111.424.1207241028.11",
               objectUID = "1.2.840.113704.1.111.3820.1207241493.1631";

        String wadoURL  = createWADOURL(studyUID, seriesUID, objectUID);
*/

        String wadoURL = constructWADOURL(AIMFileReader.parseAIMFile(new File("./war/aim_files/Case7/L01_303857131.163162.xml")));

        WADORequest.getImage(wadoURL);
    }
}
