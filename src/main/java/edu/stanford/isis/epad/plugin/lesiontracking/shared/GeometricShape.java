package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class GeometricShape extends AIMElement
{
	private static final String[] ALL_ELEMENT_NAMES = { SPATIAL_COORDINATE_COLLECTION };
	private static final int SPATIAL_COORDINATE_COLLECTION_INDEX = 0;

	public int getNumberOfSpatialCoordinateCollections()
	{
		return getAllAIMElements().get(SPATIAL_COORDINATE_COLLECTION_INDEX).size();
	}
	
	public SpatialCoordinateCollection getSpatialCoordinateCollection(int i)
	{
		return (SpatialCoordinateCollection)getAllAIMElements().get(SPATIAL_COORDINATE_COLLECTION_INDEX).get(i);
	}

	public String getName()
	{
		return AIMElement.GEOMETRIC_SHAPE;
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
