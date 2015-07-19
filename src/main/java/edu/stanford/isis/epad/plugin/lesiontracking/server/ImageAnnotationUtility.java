package edu.stanford.isis.epad.plugin.lesiontracking.server;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageReference;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageReferenceCollection;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageReferenceEntity;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageReferenceEntityCollection;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageSeries;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageStudy;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.Patient;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.Person;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.Series;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.StartDate;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.Study;

public class ImageAnnotationUtility
{
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    
	public static Date getStudyDate(ImageReferenceEntityCollection imageReferenceEntityCollection) throws ParseException
	{
		for(int i = 0; i < imageReferenceEntityCollection.getNumberOfImageReferenceEntities(); i++)
        {
            ImageReferenceEntity imageReferenceEntity = imageReferenceEntityCollection.getImageReferenceEntity(i);

            /**
             * This is backwards compatibility code. If ImageStudy doesn't exist,
             * we will look for the old Study format.
             */
            if(imageReferenceEntity.getNumberOfImageStudies() > 0)
            {
                for(int j = 0; j < imageReferenceEntity.getNumberOfImageStudies(); j++)
                {
                    ImageStudy imageStudy = imageReferenceEntity.getImageStudy(j);                    
                    
                    // TODO: Find out why there are nested ImageStudies.
                    for(int k = 0; k < imageStudy.getNumberOfStartDates();)
                    {
                        StartDate startDate = imageStudy.getStartDate(0);

                        /**
                         * There are currently two date/time formats:
                         *
                         * 1. 2004-12-02T15:14:21
                         * 2. 20041202
                         */

                        // This is for the first type.
                        String studyDateAndTime = startDate.getValue();
                        int timeStart = studyDateAndTime.indexOf('T');
                        // Try to get just the date (without the time)
                        if(timeStart > -1)
                            return dateFormat.parse(studyDateAndTime.substring(0, timeStart));


                        // This is for the second type.
                        if(studyDateAndTime.length() == 8)
                            return dateFormat.parse(studyDateAndTime.substring(0, 4) + "-" + studyDateAndTime.substring(4, 6) + "-" + studyDateAndTime.substring(6, 8));

                        return dateFormat.parse(studyDateAndTime);
                    }
                    
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
                            return dateFormat.parse(studyDateAndTime.substring(0, timeStart));


                        // This is for the second type.
                        if(studyDateAndTime.length() == 8)
                            return dateFormat.parse(studyDateAndTime.substring(0, 4) + "-" + studyDateAndTime.substring(4, 6) + "-" + studyDateAndTime.substring(6, 8));

                        return dateFormat.parse(studyDateAndTime);
                    }
                }
            }
            else
            {

            	//System.out.println("ImageReferenceEntity does NOT have at least one imageStudy.");
                for(int j = 0; j < imageReferenceEntity.getNumberOfStudies(); j++)
                {
                    Study study = imageReferenceEntity.getStudy(j);

                    // TODO: Find out why there are nested studies.
                    for(int k = 0; k < study.getNumberOfStudies();)
                    {
                        Study study2 = study.getStudy(k);
                        String studyDateAndTime = study2.getStudyDate();
                        int timeStart = studyDateAndTime.indexOf('T');

                        // Try to get just the date (without the time)
                        if(timeStart > -1)
                            return dateFormat.parse(studyDateAndTime.substring(0, timeStart));

                        return dateFormat.parse(studyDateAndTime);
                    }
                }
            }
        }
        
        //System.out.println("Could not find study date in ImageReferenceEntityCollection.");
        return null;
	}
	
    public static Date getStudyDate(ImageReferenceCollection imageReferenceCollection) throws ParseException
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
                            return dateFormat.parse(studyDateAndTime.substring(0, timeStart));


                        // This is for the second type.
                        if(studyDateAndTime.length() == 8)
                            return dateFormat.parse(studyDateAndTime.substring(0, 4) + "-" + studyDateAndTime.substring(4, 6) + "-" + studyDateAndTime.substring(6, 8));

                        return dateFormat.parse(studyDateAndTime);
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
                            return dateFormat.parse(studyDateAndTime.substring(0, timeStart));

                        return dateFormat.parse(studyDateAndTime);
                    }
                }
            }
        }
        
        //System.out.println("Could not find study date in ImageReferenceCollection.");
        return null;
    }

    public static String getStudyUID(ImageReferenceCollection imageReferenceCollection)
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
                        return imageStudy2.getInstanceUID();
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
                        return study2.getInstanceUID();
                    }
                }
            }
        }

        return null;
    }

    public static String getSeriesUID(ImageReferenceCollection imageReferenceCollection)
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

                        if(imageStudy2.getNumberOfImageSeries() > 0)
                        {
                            for(int l = 0; l < imageStudy2.getNumberOfImageSeries(); l++)
                            {
                                ImageSeries imageSeries = imageStudy2.getImageSeries(l);

                                // TODO: Find out why there are nested ImageSeries.
                                if(imageSeries.getNumberOfImageSeries() > 0)
                                    return imageSeries.getImageSeries(0).getInstanceUID();
                            }
                        }
                    }
                }
            }
            else
            {
                for(int j = 0; j < imageReference.getNumberOfStudies(); j++)
                {
                    Study study = imageReference.getStudy(j);

                    // TODO: Find out why there are nested studies.
                    if(study.getNumberOfStudies() > 0)
                    {
                        Study study2 = study.getStudy(0);

                        if(study2.getNumberOfSeries() > 0)
                        {
                            for(int l = 0; l < study2.getNumberOfSeries(); l++)
                            {
                                Series series = study2.getSeries(l);

                                // TODO: Find out why there are nested series.
                                if(series.getNumberOfSeries() > 0)
                                    return series.getSeries(0).getInstanceUID();
                            }
                        }
                        return study2.getInstanceUID();
                    }
                }
            }
        }

        return null;
    }

    public static String getPatientName(ImageAnnotation imageAnnotation)
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
}
