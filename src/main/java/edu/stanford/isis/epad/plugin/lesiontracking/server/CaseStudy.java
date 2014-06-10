package edu.stanford.isis.epad.plugin.lesiontracking.server;

import java.io.Serializable;

public class CaseStudy implements Serializable
{
    private static final long serialVersionUID = -8283721806122100172L;
    
    private String patientName;
	private String[] aimFilenames;
	private String[] availableMetrics;
	
	public CaseStudy()
	{
		
	}
	
	public CaseStudy(String patientName, String[] aimFilenames, String[] availableMetrics)
	{
		this.setPatientName(patientName);
		this.setAimFilenames(aimFilenames);
		this.setAvailableMetrics(availableMetrics);
	}

	public void setPatientName(String patientName)
	{
		this.patientName = patientName;
	}

	public String getPatientName()
	{
		return patientName;
	}

	public void setAimFilenames(String[] aimFilenames)
	{
		this.aimFilenames = aimFilenames;
	}

	public String[] getAimFilenames()
	{
		return aimFilenames;
	}

	public void setAvailableMetrics(String[] availableMetrics)
	{
		this.availableMetrics = availableMetrics;
	}

	public String[] getAvailableMetrics()
	{
		return availableMetrics;
	}
}
