package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class ImagingPhysicalEntity extends AIMElement
{
    private static final String[] ALL_ELEMENT_NAMES = { TYPE_CODE };
    private static final int TYPE_CODE_INDEX = 0;

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
