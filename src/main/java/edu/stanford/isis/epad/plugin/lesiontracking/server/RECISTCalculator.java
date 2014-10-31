package edu.stanford.isis.epad.plugin.lesiontracking.server;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.stanford.isis.epad.plugin.lesiontracking.client.recist.ImageAnnotationUtility;
import edu.stanford.isis.epad.plugin.lesiontracking.server.TumorAnalysisCalculator.CalculationResult;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.Image;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageReference;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageReferenceCollection;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageSeries;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageStudy;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.Series;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.Study;

public class RECISTCalculator {

	static String server;
	
	public static Map<Date, List<ImageAnnotation>> loadAndSortAIMFilesByStudyDate(List<ImageAnnotation> imageAnnotations)
	{
		Map<Date, List<ImageAnnotation>> imageAnnotationsByStudyDate = new HashMap<Date, List<ImageAnnotation>>();

		for (ImageAnnotation imageAnnotation : imageAnnotations)
		{
			Date studyDate;
			try
			{
				studyDate = ImageAnnotationUtility.getStudyDate(imageAnnotation.getImageReferenceCollection(0));
				
			}
			catch(ParseException parseException)
			{
				studyDate = new Date(0l);
			}
			
			if(!imageAnnotationsByStudyDate.containsKey(studyDate))
				imageAnnotationsByStudyDate.put(studyDate, new ArrayList<ImageAnnotation>());

			imageAnnotationsByStudyDate.get(studyDate).add(imageAnnotation);
		}

		return imageAnnotationsByStudyDate;
	}

	public static String constructWADOURL(ImageAnnotation ia) {
		String wadoURL = "";

		if (ia.getNumberOfImageReferenceCollections() > 0) {
			ImageReferenceCollection irc = ia.getImageReferenceCollection(0);

			if (irc.getNumberOfImageReferences() > 0) {
				ImageReference ir = irc.getImageReference(0);

				if (ir.getNumberOfImageStudies() > 0) {
					ImageStudy imageStudy = ir.getImageStudy(0)
							.getImageStudy(0);
					ImageSeries imageSeries = imageStudy.getImageSeries(0)
							.getImageSeries(0);
					Image image = imageSeries.getImageCollection(0).getImage(0);

					String studyUID = imageStudy.getInstanceUID();
					String seriesUID = imageSeries.getInstanceUID();
					String objectUID = image.getSOPInstanceUID();

					wadoURL = createWADOURL(studyUID, seriesUID, objectUID);
				} else if (ir.getNumberOfStudies() > 0) // /< This is backwards
														// compatibility code.
				{
					Study study = ir.getStudy(0).getStudy(0);
					Series series = study.getSeries(0).getSeries(0);
					Image image = series.getImageCollection(0).getImage(0);

					String studyUID = study.getInstanceUID();
					String seriesUID = series.getInstanceUID();
					String objectUID = image.getSOPInstanceUID();

					wadoURL = createWADOURL(studyUID, seriesUID, objectUID);
				}
			}
		}

		return wadoURL;
	}

	public static String createWADOURL(String studyUID, String seriesUID,
			String objectUID) {
		String URL = server + "/epad/wado/?requestType=WADO" + "&studyUID="
				+ studyUID + "&seriesUID=" + seriesUID + "&objectUID="
				+ objectUID;
		// String URL =
		// "http://rufus.stanford.edu:8090/wado?requestType=WADO" +
		// "&studyUID=" + studyUID + "&seriesUID=" + seriesUID + "&objectUID=" +
		// objectUID;

		return URL;
	}
}
