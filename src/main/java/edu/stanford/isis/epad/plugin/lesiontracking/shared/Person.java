package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class Person extends AIMElement
{
	private static final String[] ALL_ELEMENT_NAMES = { PERSON };
	private static final int PERSON_INDEX = 0;
	
	private static final String[] ALL_ATTRIBUTE_NAMES = { AIMAttribute.NAME };
	private static final int NAME_INDEX = 0;

	public int getNumberOfPersons()
	{
		return getAllAIMElements().get(PERSON_INDEX).size();
	}
	
	public Person getPerson(int i)
	{
		return (Person)getAllAIMElements().get(PERSON_INDEX).get(i);
	}
	
	public String getNameAttribute()
	{
		return getAllAIMAttributes().get(NAME_INDEX).getValue();
	}
	
	public String getName()
	{
		return AIMElement.PERSON;
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
