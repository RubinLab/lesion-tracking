package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class DateTime extends AIMElement
{
	private static final String[] ALL_ELEMENT_NAMES = {};
	private static final String[] ALL_ATTRIBUTE_NAMES = { AIMAttribute.VALUE };
	private static final int VALUE_INDEX = 0;

	public String getValue()
	{
		return getAllAIMAttributes().get(VALUE_INDEX).getValue();
	}

	public String getName()
	{
		return AIMElement.DATE_TIME;
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
