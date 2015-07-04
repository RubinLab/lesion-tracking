package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class ImagingObservationCharacteristic extends AIMElement
{
	private static final String[] ALL_ELEMENT_NAMES = { TYPE_CODE };
    private static final int TYPE_CODE_INDEX = 0;
	
	private static final String[] ALL_ATTRIBUTE_NAMES = { AIMAttribute.CODE_MEANING };
	private static final int CODE_MEANING_INDEX = 0;

	public String getCodeMeaning()
	{
		return getAllAIMAttributes().get(CODE_MEANING_INDEX).getValue();
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
		return AIMElement.IMAGING_OBSERVATION_CHARACTERISTIC;
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
