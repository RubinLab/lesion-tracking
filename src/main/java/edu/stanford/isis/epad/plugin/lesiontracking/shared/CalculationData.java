package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class CalculationData extends AIMElement
{
	private static final String[] ALL_ELEMENT_NAMES = { VALUE };
	private static final int ELEMENT_VALUE_INDEX = 0;
	
	private static final String[] ALL_ATTRIBUTE_NAMES = { AIMAttribute.VALUE };
	private static final int VALUE_INDEX = 0;

	public int getNumberOfValues()
	{
		return getAllAIMElements().get(ELEMENT_VALUE_INDEX).size();
	}
	
	public Value getValue(int i)
	{
		return (Value)getAllAIMElements().get(ELEMENT_VALUE_INDEX).get(i);
	}
	
	public String getValue()
	{
		return getAllAIMAttributes().get(VALUE_INDEX).getValue();
	}

	public String getName()
	{
		return AIMElement.CALCULATION_DATA;
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
