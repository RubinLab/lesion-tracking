package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class ImageStudy extends AIMElement
{
    private static final String[] ALL_ELEMENT_NAMES = { IMAGE_STUDY, IMAGE_SERIES, SERIES, START_DATE };
    private static final int IMAGE_STUDY_INDEX = 0,
                             IMAGE_SERIES_INDEX = 1,
                             SERIES_INDEX = 2,
                             START_DATE_ELEMENT_INDEX = 3;

    private static final String[] ALL_ATTRIBUTE_NAMES = { AIMAttribute.START_DATE, AIMAttribute.INSTANCE_UID };
    private static final int START_DATE_INDEX   = 0,
                             INSTANCE_UID_INDEX = 1;

    public int getNumberOfImageStudies()
    {
        return getAllAIMElements().get(IMAGE_STUDY_INDEX).size();
    }

    public ImageStudy getImageStudy(int i)
    {
        return (ImageStudy)getAllAIMElements().get(IMAGE_STUDY_INDEX).get(i);
    }

    public int getNumberOfImageSeries()
    {
        return getAllAIMElements().get(IMAGE_SERIES_INDEX).size();
    }

    public ImageSeries getImageSeries(int i)
    {
        return (ImageSeries)getAllAIMElements().get(IMAGE_SERIES_INDEX).get(i);
    }
    
    public int getNumberOfStartDates()
    {
        return getAllAIMElements().get(START_DATE_ELEMENT_INDEX).size();
    }

    public StartDate getStartDate(int i)
    {
        return (StartDate)getAllAIMElements().get(START_DATE_ELEMENT_INDEX).get(i);
    }

    public int getNumberOfSeries()
    {
        return getAllAIMElements().get(SERIES_INDEX).size();
    }

    public Series getSeries(int i)
    {
        return (Series)getAllAIMElements().get(SERIES_INDEX).get(i);
    }

    public String getStartDate()
    {
        return getAllAIMAttributes().get(START_DATE_INDEX).getValue();
    }

    public String getInstanceUID()
    {
        return getAllAIMAttributes().get(INSTANCE_UID_INDEX).getValue();
    }

    public String getName()
    {
        return AIMElement.IMAGE_STUDY;
    }

    public String[] getAllAIMElementNames()
    {
        return ALL_ELEMENT_NAMES;
    }

    public String[] getAllAIMAttributeNames()
    {
        return ALL_ATTRIBUTE_NAMES;
    }
}
