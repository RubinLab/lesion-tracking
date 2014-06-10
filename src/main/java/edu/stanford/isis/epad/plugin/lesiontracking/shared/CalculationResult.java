package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class CalculationResult extends AIMElement
{
	private static final String[] ALL_ELEMENT_NAMES = { DATA_COLLECTION, CALCULATION_DATA_COLLECTION };
	private static final int DATA_COLLECTION_INDEX = 0,
							 CALCULATION_DATA_COLLECTION_INDEX = 1;
	
	private static final String[] ALL_ATTRIBUTE_NAMES = { AIMAttribute.UNIT_OF_MEASURE };
	private static final int UNIT_OF_MEASURE_INDEX = 0;

	public int getNumberOfDataCollections()
	{
		return getAllAIMElements().get(DATA_COLLECTION_INDEX).size();
	}
	
	public DataCollection getDataCollection(int i)
	{
		return (DataCollection)getAllAIMElements().get(DATA_COLLECTION_INDEX).get(i);
	}
	
	public int getNumberOfCalculationDataCollections()
	{
		return getAllAIMElements().get(CALCULATION_DATA_COLLECTION_INDEX).size();
	}
	
	public CalculationDataCollection getCalculationDataCollection(int i)
	{
		return (CalculationDataCollection)getAllAIMElements().get(CALCULATION_DATA_COLLECTION_INDEX).get(i);
	}

	public String getUnitOfMeasure()
	{
		return getAllAIMAttributes().get(UNIT_OF_MEASURE_INDEX).getValue();
	}

	public String getName()
	{
		return AIMElement.CALCULATION_RESULT;
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