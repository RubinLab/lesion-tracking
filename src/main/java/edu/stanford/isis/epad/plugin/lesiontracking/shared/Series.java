package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class Series extends AIMElement
{
	private static final String[] ALL_ELEMENT_NAMES = { SERIES, IMAGE_COLLECTION };
	private static final int SERIES_INDEX = 0, IMAGE_COLLECTION_INDEX = 1;
	
	private static final String[] ALL_ATTRIBUTE_NAMES = { AIMAttribute.INSTANCE_UID };
	private static final int INSTANCE_UID_INDEX = 0;
	
	public String getName()
	{
		return AIMElement.SERIES;
	}
	
	public int getNumberOfSeries()
	{
		return getAllAIMElements().get(SERIES_INDEX).size();
	}
	
	public Series getSeries(int i)
	{
		return (Series)getAllAIMElements().get(SERIES_INDEX).get(i);
	}
	
	public int getNumberOfImageCollections()
	{
		return getAllAIMElements().get(IMAGE_COLLECTION_INDEX).size();
	}
	
    public ImageCollection getImageCollection(int i)
    {
        return (ImageCollection)getAllAIMElements().get(IMAGE_COLLECTION_INDEX).get(i);
    }
    
	public String getInstanceUID()
	{
		return getAllAIMAttributes().get(INSTANCE_UID_INDEX).getValue();
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
