package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class Image extends AIMElement
{
	private static final String[] ALL_ATTRIBUTE_NAMES = { AIMAttribute.SOP_INSTANCE_UID };
	private static final int SOP_INSTANCE_UID_INDEX = 0;
	
	public String getSOPInstanceUID()
	{
	    return getAllAIMAttributes().get(SOP_INSTANCE_UID_INDEX).getValue();
	}
	
	public String getName()
	{
		return AIMElement.IMAGE;
	}
	
	public String[] getAllAIMElementNames()
	{
		return new String[] {};
	}

	public String[] getAllAIMAttributeNames()
	{
		return ALL_ATTRIBUTE_NAMES;
	}
}
