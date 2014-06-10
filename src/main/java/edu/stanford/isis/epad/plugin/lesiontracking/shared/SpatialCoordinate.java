package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class SpatialCoordinate extends AIMElement
{
	private static final String[] ALL_ELEMENT_NAMES = {};
	private static final String[] ALL_ATTRIBUTE_NAMES = { AIMAttribute.X, AIMAttribute.Y, AIMAttribute.COORDINATE_INDEX };
	private static final int X_INDEX = 0,
						     Y_INDEX = 1,
						     COORDINATE_INDEX_INDEX = 2;

	public String getX()
	{
		return getAllAIMAttributes().get(X_INDEX).getValue();
	}

	public String getY()
	{
		return getAllAIMAttributes().get(Y_INDEX).getValue();
	}
	
	public String getCoordinateIndex()
	{
		return getAllAIMAttributes().get(COORDINATE_INDEX_INDEX).getValue();
	}

	public String getName()
	{
		return AIMElement.SPATIAL_COORDINATE;
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
