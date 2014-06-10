package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class ImageReferenceCollection extends AIMElement
{
	private static final String[] ALL_ELEMENT_NAMES = { IMAGE_REFERENCE };
	private static final int IMAGE_REFERENCE_INDEX = 0;

	public int getNumberOfImageReferences()
	{
		return getAllAIMElements().get(IMAGE_REFERENCE_INDEX).size();
	}
	
	public ImageReference getImageReference(int i)
	{
		return (ImageReference)getAllAIMElements().get(IMAGE_REFERENCE_INDEX).get(i);
	}

	public String getName()
	{
		return AIMElement.IMAGE_REFERENCE_COLLECTION;
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
