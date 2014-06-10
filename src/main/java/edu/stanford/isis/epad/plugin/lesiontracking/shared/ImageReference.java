package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class ImageReference extends AIMElement
{
    private static final String[] ALL_ELEMENT_NAMES = { IMAGE_STUDY, STUDY };
    private static final int IMAGE_STUDY_INDEX = 0,
                             STUDY_INDEX 	   = 1;

    public int getNumberOfImageStudies()
    {
        return getAllAIMElements().get(IMAGE_STUDY_INDEX).size();
    }

    public ImageStudy getImageStudy(int i)
    {
        return (ImageStudy)getAllAIMElements().get(IMAGE_STUDY_INDEX).get(i);
    }

    public int getNumberOfStudies()
    {
        return getAllAIMElements().get(STUDY_INDEX).size();
    }

    public Study getStudy(int i)
    {
        return (Study)getAllAIMElements().get(STUDY_INDEX).get(i);
    }

    public String getName()
    {
        return AIMElement.IMAGE_REFERENCE;
    }

    public String[] getAllAIMElementNames()
    {
        return ALL_ELEMENT_NAMES;
    }

    public String[] getAllAIMAttributeNames()
    {
        return new String[] {};
    }
}
