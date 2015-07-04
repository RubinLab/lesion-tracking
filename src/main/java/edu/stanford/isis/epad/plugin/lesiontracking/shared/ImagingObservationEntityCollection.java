package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class ImagingObservationEntityCollection extends AIMElement
{
	private static final String[] ALL_ELEMENT_NAMES = { IMAGING_OBSERVATION_ENTITY };
	private static final int IMAGING_OBSERVATION_ENTITY_INDEX = 0;

	public int getNumberOfImagingObservationEntities()
	{
		return getAllAIMElements().get(IMAGING_OBSERVATION_ENTITY_INDEX).size();
	}
	
	public ImagingObservationEntity getImagingObservationEntity(int i)
	{
		return (ImagingObservationEntity)getAllAIMElements().get(IMAGING_OBSERVATION_ENTITY_INDEX).get(i);
	}

	public String getName()
	{
		return AIMElement.IMAGING_OBSERVATION_ENTITY_COLLECTION;
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
