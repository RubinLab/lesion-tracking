package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class GeometricShapeCollection extends AIMElement
{
	private static final String[] ALL_ELEMENT_NAMES = { GEOMETRIC_SHAPE };
	private static final int GEOMETRIC_SHAPE_INDEX = 0;

	public int getNumberOfGeometricShapes()
	{
		return getAllAIMElements().get(GEOMETRIC_SHAPE_INDEX).size();
	}
	
	public GeometricShape getGeometricShape(int i)
	{
		return (GeometricShape)getAllAIMElements().get(GEOMETRIC_SHAPE_INDEX).get(i);
	}
	
	public String getName()
	{
		return AIMElement.GEOMETRIC_SHAPE_COLLECTION;
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
