package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class TypeCode extends AIMElement
{
	private static final String[] ALL_ELEMENT_NAMES = {};
	
	private static final String[] ALL_ATTRIBUTE_NAMES = { AIMAttribute.CODE_SYSTEM };
	private static final int CODE_SYSTEM_INDEX = 0;

	public String getCodeSystem()
	{
		return getAllAIMAttributes().get(CODE_SYSTEM_INDEX).getValue();
	}

	public String getName()
	{
		return AIMElement.TYPE_CODE;
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
