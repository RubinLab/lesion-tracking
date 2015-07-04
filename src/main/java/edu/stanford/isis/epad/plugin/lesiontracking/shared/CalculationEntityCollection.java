package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class CalculationEntityCollection extends AIMElement
{
	private static final String[] ALL_ELEMENT_NAMES = { CALCULATION_ENTITY };
	private static final int CALCULATION_ENTITY_INDEX = 0;

	public int getNumberOfCalculationEntities()
	{
		return getAllAIMElements().get(CALCULATION_ENTITY_INDEX).size();
	}
	
	public CalculationEntity getCalculationEntity(int i)
	{
		return (CalculationEntity)getAllAIMElements().get(CALCULATION_ENTITY_INDEX).get(i);
	}
	
	public String getName()
	{
		return AIMElement.CALCULATION_ENTITY_COLLECTION;
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