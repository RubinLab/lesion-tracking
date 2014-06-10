package edu.stanford.isis.epad.plugin.lesiontracking.client.recist;

import java.io.Serializable;

public class CalculationResult implements Serializable
{
    private static final long serialVersionUID = 4749957791110986406L;

    private int numberOfTimePointsInStudy;
    private Lesion[] lesions;
    private String[][] metricSums;
    private String[] metricUnits;
    private String[][] studyDates;
    private String[][] responseRates,
                       responseRatesSinceBaseline,
                       responseRatesSinceNadir,
                       responseCategories;
    private String[] metrics, imageURLs;
    private String analysisImageURL;

    public CalculationResult()
    {
    }

    public void setImageURLs(String[] imageURLs)
    {
        this.imageURLs = imageURLs;
    }

    public String[] getImageURLs()
    {
        return imageURLs;
    }

    public void setLesions(Lesion[] lesions)
    {
        this.lesions = lesions;
    }

    public Lesion[] getLesions()
    {
        return lesions;
    }

    public void setResponseRates(String[][] responseRates)
    {
        this.responseRates = responseRates;
    }

    public String[][] getResponseRates()
    {
        return responseRates;
    }

    public void setNumberOfTimePointsInStudy(int numberOfTimePointsInStudy)
    {
        this.numberOfTimePointsInStudy = numberOfTimePointsInStudy;
    }

    public int getNumberOfTimePointsInStudy()
    {
        return numberOfTimePointsInStudy;
    }

    public void setMetrics(String[] metrics)
    {
        this.metrics = metrics;
    }

    public String[] getMetrics()
    {
        return metrics;
    }

    public void setAnalysisImageURL(String analysisImageURL)
    {
        this.analysisImageURL = analysisImageURL;
    }

    public String getAnalysisImageURL()
    {
        return analysisImageURL;
    }

    public void setResponseCategories(String[][] responseCategories)
    {
        this.responseCategories = responseCategories;
    }

    public String[][] getResponseCategories()
    {
        return responseCategories;
    }

    public void setMetricSums(String[][] metricSums)
    {
        this.metricSums = metricSums;
    }

    public String[][] getMetricSums()
    {
        return metricSums;
    }

    public void setMetricUnits(String[] metricUnits)
    {
        this.metricUnits = metricUnits;
    }

    public String[] getMetricUnits()
    {
        return metricUnits;
    }

    public void setResponseRatesSinceBaseline(String[][] responseRatesSinceBaseline)
    {
        this.responseRatesSinceBaseline = responseRatesSinceBaseline;
    }

    public String[][] getResponseRatesSinceBaseline()
    {
        return responseRatesSinceBaseline;
    }

    public void setResponseRatesSinceNadir(String[][] responseRatesSinceNadir)
    {
        this.responseRatesSinceNadir = responseRatesSinceNadir;
    }

    public String[][] getResponseRatesSinceNadir()
    {
        return responseRatesSinceNadir;
    }

    public String[][] getStudyDates()
    {
        return studyDates;
    }

    public void setStudyDates(String[][] studyDates)
    {
        this.studyDates = studyDates;
    }
}
