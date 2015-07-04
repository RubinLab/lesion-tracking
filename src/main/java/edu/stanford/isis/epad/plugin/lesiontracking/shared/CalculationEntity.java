package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class CalculationEntity extends AIMElement
{
	private static final String[] ALL_ELEMENT_NAMES = { DESCRIPTION, CALCULATION_RESULT_COLLECTION };
	private static final int DESCRIPTION_INDEX = 0,
							 CALCULATION_RESULT_COLLECTION_INDEX = 1;

	public int getNumberOfCalculationResultCollections()
	{
		return getAllAIMElements().get(CALCULATION_RESULT_COLLECTION_INDEX).size();
	}
	
	public CalculationResultCollection getCalculationResultCollection(int i)
	{
		return (CalculationResultCollection)getAllAIMElements().get(CALCULATION_RESULT_COLLECTION_INDEX).get(i);
	}
	
	public int getNumberOfDescriptions()
	{
		return getAllAIMElements().get(DESCRIPTION_INDEX).size();
	}
	
	public Description getDescription(int i)
	{
		return (Description)getAllAIMElements().get(DESCRIPTION_INDEX).get(i);
	}
	
	public String getName()
	{
		return AIMElement.CALCULATION_ENTITY;
	}
	
	public String[] getAllAIMElementNames()
	{
		return ALL_ELEMENT_NAMES;
	}

	@Override
	public String[] getAllAIMAttributeNames()
	{
		return new String[] {};
	}
}
