package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class ImagingObservationCharacteristic extends AIMElement
{
	private static final String[] ALL_ELEMENT_NAMES = {};
	
	private static final String[] ALL_ATTRIBUTE_NAMES = { AIMAttribute.CODE_MEANING };
	private static final int CODE_MEANING_INDEX = 0;

	public String getCodeMeaning()
	{
		return getAllAIMAttributes().get(CODE_MEANING_INDEX).getValue();
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
