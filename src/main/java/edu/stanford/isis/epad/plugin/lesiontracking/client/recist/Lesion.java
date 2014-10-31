package edu.stanford.isis.epad.plugin.lesiontracking.client.recist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Lesion implements Serializable
{
    private static final long serialVersionUID = 7080097289394328463L;

    private ArrayList<String> metrics = new ArrayList<String>();
    private String lesionID, location;
    private HashMap<String, ArrayList<String>> temporalMeasurements = new HashMap<String, ArrayList<String>>();
    private ArrayList<String> wadoURLs = new ArrayList<String>();

    public Lesion()
    {
    }

    public ArrayList<String> getMetrics()
    {
        return metrics;
    }

    public void addTemporalMeasurementForMetric(String metric, String temporalMeasurement)
    {
        if(metric == null || temporalMeasurement == null) return;

        if(!getTemporalMeasurements().containsKey(metric))
        {
            metrics.add(metric);
            getTemporalMeasurements().put(metric, new ArrayList<String>());
        }

        getTemporalMeasurements().get(metric).add(temporalMeasurement);
    }

    public void addWADOURL(String wadoURL)
    {
        wadoURLs.add(wadoURL);
    }

    public int getNumberOfWADOURLs()
    {
        return wadoURLs.size();
    }

    public String getWADOURL(int i)
    {
        return wadoURLs.get(i);
    }

    public int getNumberOfTemporalMeasurements()
    {
        int max = 0;
        for(List<String> metricValues : getTemporalMeasurements().values())
            if(metricValues.size() > max)
                max = metricValues.size();

        return max;
    }

    public ArrayList<String> getMeasurementsForMetric(String metric)
    {
        return getTemporalMeasurements().get(metric);
    }

    public void setLesionID(String lesionID)
    {
        this.lesionID = lesionID;
    }

    public String getLesionID()
    {
        return lesionID;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getLocation()
    {
        return location;
    }

    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer( "Lesion ID: " + lesionID + "    Lesion Location: " + location );
        for(int i = 0; i < getTemporalMeasurements().size(); i++)
            sb.append("    T" + i + ": " + getTemporalMeasurements().get(i));// + "    WADO URL: " + wadoURLs.get(i));
        return sb.toString();
    }

	public HashMap<String, ArrayList<String>> getTemporalMeasurements() {
		return temporalMeasurements;
	}

	public void setTemporalMeasurements(HashMap<String, ArrayList<String>> temporalMeasurements) {
		this.temporalMeasurements = temporalMeasurements;
	}
}
