package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class ImagingObservationEntity extends AIMElement
{
	private static final String[] ALL_ELEMENT_NAMES = { IMAGING_OBSERVATION_CHARACTERISTIC_COLLECTION };
	private static final int IMAGING_OBSERVATION_CHARACTERISTIC_COLLECTION_INDEX = 0;

	public int getNumberOfImagingObservationCharacteristicCollections()
	{
		return getAllAIMElements().get(IMAGING_OBSERVATION_CHARACTERISTIC_COLLECTION_INDEX).size();
	}
	
	public ImagingObservationCharacteristicCollection getImagingObservationCharacteristicCollection(int i)
	{
		return (ImagingObservationCharacteristicCollection)getAllAIMElements().get(IMAGING_OBSERVATION_CHARACTERISTIC_COLLECTION_INDEX).get(i);
	}
	
	public String getName()
	{
		return AIMElement.IMAGING_OBSERVATION_ENTITY;
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