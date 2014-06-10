package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class Calculation extends AIMElement
{
	private static final String[] ALL_ELEMENT_NAMES = { CALCULATION_RESULT_COLLECTION };
	private static final int CALCULATION_RESULT_COLLECTION_INDEX = 0;
	
	private static final String[] ALL_ATTRIBUTE_NAMES = { AIMAttribute.DESCRIPTION, AIMAttribute.TYPE };
	private static final int DESCRIPTION_INDEX = 0, TYPE_INDEX = 1;

	public int getNumberOfCalculationResultCollections()
	{
		return getAllAIMElements().get(CALCULATION_RESULT_COLLECTION_INDEX).size();
	}
	
	public CalculationResultCollection getCalculationResultCollection(int i)
	{
		return (CalculationResultCollection)getAllAIMElements().get(CALCULATION_RESULT_COLLECTION_INDEX).get(i);
	}
	
	public String getDescription()
	{
		return getAllAIMAttributes().get(DESCRIPTION_INDEX).getValue();
	}
	
	public String getType()
	{
		return getAllAIMAttributes().get(TYPE_INDEX).getValue();
	}
	
	public String getName()
	{
		return AIMElement.CALCULATION;
	}
	
	public String[] getAllAIMElementNames()
	{
		return ALL_ELEMENT_NAMES;
	}

	@Override
	public String[] getAllAIMAttributeNames()
	{
		return ALL_ATTRIBUTE_NAMES;
	}
}
