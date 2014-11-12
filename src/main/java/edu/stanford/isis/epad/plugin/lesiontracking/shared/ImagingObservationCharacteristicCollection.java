package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class ImagingObservationCharacteristicCollection extends AIMElement
{
	private static final String[] ALL_ELEMENT_NAMES = { IMAGING_OBSERVATION_CHARACTERISTIC };
	private static final int IMAGING_OBSERVATION_CHARACTERISTIC_INDEX = 0;

	public int getNumberOfImagingObservationCharacteristics()
	{
		return getAllAIMElements().get(IMAGING_OBSERVATION_CHARACTERISTIC_INDEX).size();
	}
	
	public ImagingObservationCharacteristic getImagingObservationCharacteristic(int i)
	{
		return (ImagingObservationCharacteristic)getAllAIMElements().get(IMAGING_OBSERVATION_CHARACTERISTIC_INDEX).get(i);
	}

	public String getName()
	{
		return AIMElement.IMAGING_OBSERVATION_CHARACTERISTIC_COLLECTION;
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
