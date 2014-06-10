package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class SpatialCoordinateCollection extends AIMElement
{
	private static final String[] ALL_ELEMENT_NAMES = { SPATIAL_COORDINATE };
	private static final int SPATIAL_COORDINATE_INDEX = 0;

	public int getNumberOfSpatialCoordinates()
	{
		return getAllAIMElements().get(SPATIAL_COORDINATE_INDEX).size();
	}
	
	public SpatialCoordinate getSpatialCoordinate(int i)
	{
		return (SpatialCoordinate)getAllAIMElements().get(SPATIAL_COORDINATE_INDEX).get(i);
	}
	
	public String getName()
	{
		return AIMElement.SPATIAL_COORDINATE_COLLECTION;
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