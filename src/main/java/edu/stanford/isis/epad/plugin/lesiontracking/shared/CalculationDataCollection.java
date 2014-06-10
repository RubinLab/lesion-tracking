package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class CalculationDataCollection extends AIMElement
{
	private static final String[] ALL_ELEMENT_NAMES = { CALCULATION_DATA };
	private static final int CALCULATION_DATA_INDEX = 0;

	public int getNumberOfCalculationDatas()
	{
		return getAllAIMElements().get(CALCULATION_DATA_INDEX).size();
	}
	
	public CalculationData getCalculationData(int i)
	{
		return (CalculationData)getAllAIMElements().get(CALCULATION_DATA_INDEX).get(i);
	}
	
	public String getName()
	{
		return AIMElement.CALCULATION_DATA_COLLECTION;
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
