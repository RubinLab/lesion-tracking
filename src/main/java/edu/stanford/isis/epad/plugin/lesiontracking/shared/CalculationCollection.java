package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class CalculationCollection extends AIMElement
{
	private static final String[] ALL_ELEMENT_NAMES = { CALCULATION };
	private static final int CALCULATION_INDEX = 0;

	public int getNumberOfCalculations()
	{
		return getAllAIMElements().get(CALCULATION_INDEX).size();
	}
	
	public Calculation getCalculation(int i)
	{
		return (Calculation)getAllAIMElements().get(CALCULATION_INDEX).get(i);
	}
	
	public String getName()
	{
		return AIMElement.CALCULATION_COLLECTION;
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