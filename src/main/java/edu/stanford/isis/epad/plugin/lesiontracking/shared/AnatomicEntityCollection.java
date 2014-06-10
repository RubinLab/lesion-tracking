package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class AnatomicEntityCollection extends AIMElement
{
	private static final String[] ALL_ELEMENT_NAMES = { ANATOMIC_ENTITY };
	private static final int ANATOMIC_ENTITY_INDEX = 0;

	public int getNumberOfAnatomicEntities()
	{
		return getAllAIMElements().get(ANATOMIC_ENTITY_INDEX).size();
	}
	
	public AnatomicEntity getAnatomicEntity(int i)
	{
		return (AnatomicEntity)getAllAIMElements().get(ANATOMIC_ENTITY_INDEX).get(i);
	}
	
	public String getName()
	{
		return AIMElement.ANATOMIC_ENTITY_COLLECTION;
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