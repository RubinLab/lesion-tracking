package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class ImageReferenceEntityCollection extends AIMElement
{
	private static final String[] ALL_ELEMENT_NAMES = { IMAGE_REFERENCE_ENTITY };
	private static final int IMAGE_REFERENCE_ENTITY_INDEX = 0;

	public int getNumberOfImageReferenceEntities()
	{
		return getAllAIMElements().get(IMAGE_REFERENCE_ENTITY_INDEX).size();
	}
	
	public ImageReferenceEntity getImageReferenceEntity(int i)
	{
		return (ImageReferenceEntity)getAllAIMElements().get(IMAGE_REFERENCE_ENTITY_INDEX).get(i);
	}

	public String getName()
	{
		return AIMElement.IMAGE_REFERENCE_ENTITY_COLLECTION;
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
