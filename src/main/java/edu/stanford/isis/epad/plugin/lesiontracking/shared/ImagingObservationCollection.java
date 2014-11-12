package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class ImagingObservationCollection extends AIMElement
{
	private static final String[] ALL_ELEMENT_NAMES = { IMAGING_OBSERVATION };
	private static final int IMAGING_OBSERVATION_INDEX = 0;

	public int getNumberOfImagingObservations()
	{
		return getAllAIMElements().get(IMAGING_OBSERVATION_INDEX).size();
	}
	
	public ImagingObservation getImagingObservation(int i)
	{
		return (ImagingObservation)getAllAIMElements().get(IMAGING_OBSERVATION_INDEX).get(i);
	}

	public String getName()
	{
		return AIMElement.IMAGING_OBSERVATION_COLLECTION;
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
