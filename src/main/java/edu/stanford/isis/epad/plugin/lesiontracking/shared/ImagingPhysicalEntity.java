package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class ImagingPhysicalEntity extends AIMElement
{
    private static final String[] ALL_ELEMENT_NAMES = { LABEL, TYPE_CODE };
    private static final int LABEL_INDEX = 0, TYPE_CODE_INDEX = 1;

    public int getNumberOfLabels()
    {
        return getAllAIMElements().get(LABEL_INDEX).size();
    }

    public Label getLabel(int i)
    {
        return (Label)getAllAIMElements().get(LABEL_INDEX).get(i);
    }
    
    public int getNumberOfTypeCodes()
    {
        return getAllAIMElements().get(TYPE_CODE_INDEX).size();
    }

    public TypeCode getTypeCode(int i)
    {
        return (TypeCode)getAllAIMElements().get(TYPE_CODE_INDEX).get(i);
    }

    public String getName()
    {
        return AIMElement.IMAGING_PHYSICAL_ENTITY;
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
