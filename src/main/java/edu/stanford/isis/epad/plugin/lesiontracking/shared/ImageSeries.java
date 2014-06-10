package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class ImageSeries extends AIMElement
{
	private static final String[] ALL_ELEMENT_NAMES = { IMAGE_SERIES, IMAGE_COLLECTION };
	private static final int IMAGE_SERIES_INDEX = 0,
	                         IMAGE_COLLECTION_INDEX = 1;
	
	private static final String[] ALL_ATTRIBUTE_NAMES = { AIMAttribute.INSTANCE_UID };
	private static final int INSTANCE_UID_INDEX = 0;

	public int getNumberOfImageSeries()
	{
		return getAllAIMElements().get(IMAGE_SERIES_INDEX).size();
	}
	
	public ImageSeries getImageSeries(int i)
	{
		return (ImageSeries)getAllAIMElements().get(IMAGE_SERIES_INDEX).get(i);
	}

	public int getNumberOfImageCollections()
	{
		return getAllAIMElements().get(IMAGE_COLLECTION_INDEX).size();
	}
	
    public ImageCollection getImageCollection(int i)
    {
        return (ImageCollection)getAllAIMElements().get(IMAGE_COLLECTION_INDEX).get(i);
    }

    public int getNumberOfSeries()
    {
        return getAllAIMElements().get(IMAGE_SERIES_INDEX).size();
    }
	
	public String getInstanceUID()
	{
	    return getAllAIMAttributes().get(INSTANCE_UID_INDEX).getValue();
	}
	
	public String getName()
	{
		return AIMElement.IMAGE_SERIES;
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
