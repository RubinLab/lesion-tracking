package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class CalculationResultCollection extends AIMElement
{
	private static final String[] ALL_ELEMENT_NAMES = { CALCULATION_RESULT };
	private static final int CALCULATION_RESULT_INDEX = 0;

	public int getNumberOfCalculationResults()
	{
		return getAllAIMElements().get(CALCULATION_RESULT_INDEX).size();
	}
	
	public CalculationResult getCalculationResult(int i)
	{
		return (CalculationResult)getAllAIMElements().get(CALCULATION_RESULT_INDEX).get(i);
	}
	
	public String getName()
	{
		return AIMElement.CALCULATION_RESULT_COLLECTION;
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