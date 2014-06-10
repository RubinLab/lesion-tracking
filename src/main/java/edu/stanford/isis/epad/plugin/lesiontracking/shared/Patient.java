package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class Patient extends AIMElement
{
	private static final String[] ALL_ELEMENT_NAMES = { PATIENT };
	private static final int PATIENT_INDEX = 0;
	
	private static final String[] ALL_ATTRIBUTE_NAMES = { AIMAttribute.NAME };
	private static final int NAME_INDEX = 0;

	public int getNumberOfPatients()
	{
		return getAllAIMElements().get(PATIENT_INDEX).size();
	}
	
	public Patient getPatient(int i)
	{
		return (Patient)getAllAIMElements().get(PATIENT_INDEX).get(i);
	}
	
	public String getNameAttribute()
	{
		return getAllAIMAttributes().get(NAME_INDEX).getValue();
	}
	
	public String getName()
	{
		return AIMElement.PATIENT;
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
