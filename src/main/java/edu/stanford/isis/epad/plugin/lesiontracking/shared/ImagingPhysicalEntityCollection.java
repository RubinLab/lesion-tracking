package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class ImagingPhysicalEntityCollection extends AIMElement
{
	private static final String[] ALL_ELEMENT_NAMES = { IMAGING_PHYSICAL_ENTITY };
	private static final int IMAGING_PHYSICAL_ENTITY_INDEX = 0;

	public int getNumberOfImagingPhysicalEntities()
	{
		return getAllAIMElements().get(IMAGING_PHYSICAL_ENTITY_INDEX).size();
	}
	
	public ImagingPhysicalEntity getImagingPhysicalEntity(int i)
	{
		return (ImagingPhysicalEntity)getAllAIMElements().get(IMAGING_PHYSICAL_ENTITY_INDEX).get(i);
	}

	public String getName()
	{
		return AIMElement.IMAGING_PHYSICAL_ENTITY_COLLECTION;
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
