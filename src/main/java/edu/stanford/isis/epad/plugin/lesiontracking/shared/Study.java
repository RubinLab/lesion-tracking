package edu.stanford.isis.epad.plugin.lesiontracking.shared;

public class Study extends AIMElement
{
	private static final String[] ALL_ELEMENT_NAMES = { STUDY, SERIES };
	private static final int STUDY_INDEX = 0,
							 SERIES_INDEX = 1;
	
	private static final String[] ALL_ATTRIBUTE_NAMES = { AIMAttribute.STUDY_DATE, AIMAttribute.INSTANCE_UID };
	private static final int STUDY_DATE_INDEX = 0, INSTANCE_UID_INDEX = 1;

	public int getNumberOfStudies()
	{
		return getAllAIMElements().get(STUDY_INDEX).size();
	}
	
	public Study getStudy(int i)
	{
		return (Study)getAllAIMElements().get(STUDY_INDEX).get(i);
	}

	public int getNumberOfSeries()
	{
		return getAllAIMElements().get(SERIES_INDEX).size();
	}
	
	public Series getSeries(int i)
	{
		return (Series)getAllAIMElements().get(SERIES_INDEX).get(i);
	}
	
	public String getStudyDate()
	{
		return getAllAIMAttributes().get(STUDY_DATE_INDEX).getValue();
	}
	
	public String getName()
	{
		return AIMElement.STUDY;
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
