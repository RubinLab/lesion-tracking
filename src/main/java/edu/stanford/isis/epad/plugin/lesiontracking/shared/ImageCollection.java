package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class ImageCollection extends AIMElement
{
	private static final String[] ALL_ELEMENT_NAMES = { IMAGE };
	private static final int IMAGE_INDEX = 0;

	public int getNumberOfImages()
	{
		return getAllAIMElements().get(IMAGE_INDEX).size();
	}
	
	public Image getImage(int i)
	{
		return (Image)getAllAIMElements().get(IMAGE_INDEX).get(i);
	}

	public String getName()
	{
		return AIMElement.IMAGE_COLLECTION;
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
