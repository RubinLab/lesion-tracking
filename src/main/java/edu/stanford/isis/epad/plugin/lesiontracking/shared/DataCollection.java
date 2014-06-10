package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class DataCollection extends AIMElement
{
	private static final String[] ALL_ELEMENT_NAMES = { DATA };
	private static final int DATA_INDEX = 0;

	public int getNumberOfDatas()
	{
		return getAllAIMElements().get(DATA_INDEX).size();
	}
	
	public Data getData(int i)
	{
		return (Data)getAllAIMElements().get(DATA_INDEX).get(i);
	}
	
	public String getName()
	{
		return AIMElement.DATA_COLLECTION;
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
