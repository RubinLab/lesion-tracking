package edu.stanford.isis.epad.plugin.lesiontracking.server;

//import java.text.DecimalFormat;

import java.text.DecimalFormat;

import edu.stanford.isis.epad.plugin.lesiontracking.client.recist.ImageAnnotationUtility;
import edu.stanford.isis.epad.plugin.lesiontracking.client.recist.UnitConversion;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.Calculation;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationCollection;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationData;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationDataCollection;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationResult;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationResultCollection;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.Data;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.DataCollection;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation;

public class TumorAnalysisCalculator
{
    private static final String COMPLETE_RESPONSE   = "CR",
                                PARTIAL_RESPONSE    = "PR",
                                STABLE_DISEASE	    = "SD",
                                PROGRESSIVE_DISEASE = "PD";

    private String baselineUnitOfMeasure = "", metric = "";

    private String[][] metricValues;

    private String[] studyDates;

    private String[] responseRates,
                     responseRatesSinceBaseline,
                     responseRatesSinceNadir,
                     responseCategories;

    private String[] metricSums;

    private double[] metricSumsDouble;

    public TumorAnalysisCalculator(ImageAnnotation[][] imageAnnotations, String metric)
    {
    	System.out.println("GOT: " + imageAnnotations.length  + " annotations.");
        this.metric = metric;

        // These will hold the metric values for each lesion.
        metricValues = new String[imageAnnotations.length][];

        for(int i = 0; i < imageAnnotations.length; i++)
            metricValues[i] = new String[imageAnnotations[i].length];

        // These will hold the sum of the metric values
        // for each study date.
        metricSums = new String[imageAnnotations.length];
        metricSumsDouble = new double[imageAnnotations.length];

        // Get the study dates.
        studyDates = new String[imageAnnotations.length];
        for(int i = 0; i < studyDates.length; i++)
            studyDates[i] = ImageAnnotationUtility.getStudyDate(imageAnnotations[i][0].getImageReferenceCollection(0));

        System.out.println("Request metric: " + metric);
        
        // Get the units of the first calculation result in the baseline study.
        baselineUnitOfMeasure = getStudyUnits(imageAnnotations[0][0], metric);

        // Simple hack to convert metric units to millimeters (if they are in centimeters).
        if(baselineUnitOfMeasure.equalsIgnoreCase("cm"))
            baselineUnitOfMeasure = "mm";

//       NumberFormat df = NumberFormat.getDecimalFormat();
        DecimalFormat df = new DecimalFormat();

        // Calculate the metric sum for each study date
        for( int i = 0; i < imageAnnotations.length; i++ )
            for( int j = 0; j < imageAnnotations[i].length; j++ )
                for( int k = 0; k < imageAnnotations[i][j].getNumberOfCalculationCollections(); k++)
                    {
                        CalculationCollection calculationCollection = imageAnnotations[i][j].getCalculationCollection(k);

                        // This function call adds all values within ONE calculationCollection (and therefore one lesion)
                        // that match the given metric. Usually there is only one such value per lesion.
                        float metricValue = sumLesionMetricValues(calculationCollection, baselineUnitOfMeasure, metric);
                        metricValues[i][j] = df.format(metricValue);
                        metricSumsDouble[i] += metricValue;
                    }

        for(int i = 0; i < metricSumsDouble.length; i++)
            metricSums[i] = df.format(metricSumsDouble[i]);

        // Calculate the smallest metric sum recorded since the treatment started
        // for each follow-up study.
        double[] minMetricSum = new double[imageAnnotations.length];
        for( int i = 0; i < minMetricSum.length; i++ )
            minMetricSum[i] = min(metricSumsDouble, i);

        // Calculate the response rates and response categories
        // for every study after the baseline.
        // This assumes that the first ImageAnnotation is the baseline.
        responseRates = new String[imageAnnotations.length];
        setResponseRatesSinceBaseline(new String[imageAnnotations.length]);
        setResponseRatesSinceNadir(new String[imageAnnotations.length]);
        responseCategories = new String[imageAnnotations.length];

        // The response rate is only defined for follow-up studies.
        responseRates[0] = "N/A";
        getResponseRatesSinceBaseline()[0] = "N/A";
        getResponseRatesSinceNadir()[0] = "N/A";

        for(int i = 1; i < imageAnnotations.length; i++)
        {
            double changeInMetricSinceBaseline 		 = metricSumsDouble[i] - metricSumsDouble[0];
            double changeInMetricSumSinceMinMetricSum = metricSumsDouble[i] - minMetricSum[i];

            getResponseRatesSinceBaseline()[i] = df.format(100 * changeInMetricSinceBaseline / metricSumsDouble[0]);
            getResponseRatesSinceNadir()[i] = df.format(100 * changeInMetricSumSinceMinMetricSum / minMetricSum[i]);

            if(changeInMetricSinceBaseline < 0 && metricSumsDouble[0] != 0)
            {
                double responseRate = 100 * changeInMetricSinceBaseline / metricSumsDouble[0];
                responseRates[i] = df.format(responseRate);
                responseCategories[i] = getResponseCategory(responseRate);
            }

            if(changeInMetricSumSinceMinMetricSum >= 0 && metricSumsDouble[i] != 0)
            {
                double responseRate = 100 * changeInMetricSumSinceMinMetricSum / minMetricSum[i];
                responseRates[i] = df.format(responseRate);
                responseCategories[i] = getResponseCategory(responseRate);
            }
        }
    }

    private float sumLesionMetricValues(CalculationCollection calculationCollection, String targetUnits, String metric)
    {
        float sum = 0;
        for( int i = 0; i < calculationCollection.getNumberOfCalculations(); i++ )
        {
            Calculation calculation = calculationCollection.getCalculation(i);

            /** Only include calculations that match the metric tag. **/

            if(metric.equalsIgnoreCase(calculation.getDescription()) || metric.equalsIgnoreCase(calculation.getType()))
            {
                for( int j = 0; j < calculation.getNumberOfCalculationResultCollections(); j++ )
                {
                    CalculationResultCollection calculationResultCollection = calculation.getCalculationResultCollection(j);
                    for( int k = 0; k < calculationResultCollection.getNumberOfCalculationResults(); k++ )
                    {
                        CalculationResult calculationResult = calculationResultCollection.getCalculationResult(k);
                        UnitConversion unitConversion = new UnitConversion(calculationResult.getUnitOfMeasure(), targetUnits);

                        /**
                         * This is a backwards compatibility check. If we can't find any CalculationDataCollection
                         * objects, we will look for the older type "DataCollection"
                         */
                        if(calculationResult.getNumberOfCalculationDataCollections() > 0)
                        {
                            for( int l = 0; l < calculationResult.getNumberOfCalculationDataCollections(); l++ )
                            {
                                CalculationDataCollection calculationDataCollection = calculationResult.getCalculationDataCollection(l);
                                for( int m = 0; m < calculationDataCollection.getNumberOfCalculationDatas(); m++ )
                                {
                                    CalculationData calculationData = calculationDataCollection.getCalculationData(m);
                                    sum += unitConversion.convertToTargetUnit(Float.parseFloat(calculationData.getValue()));
                                }
                            }
                        }
                        else
                        {
                            for( int l = 0; l < calculationResult.getNumberOfDataCollections(); l++ )
                            {
                                DataCollection dataCollection = calculationResult.getDataCollection(l);
                                for( int m = 0; m < dataCollection.getNumberOfDatas(); m++ )
                                {
                                    Data data = dataCollection.getData(m);
                                    sum += unitConversion.convertToTargetUnit(Float.parseFloat(data.getValue()));
                                }
                            }
                        }
                    }
                }
            }
        }
        return sum;
    }

    private String getResponseCategory(double responseRate)
    {
        // Disappearance of target lesions.
        if(responseRate == -100)
            return COMPLETE_RESPONSE;

        // Reduction in sum of the longest diameter of target
        // lesions since baseline.
        if(responseRate <= -30)
                return PARTIAL_RESPONSE;

        // Increase in the sum of the longest diameter of target
        // lesions since the smallest recorded sum.
        if(responseRate >= 20)
            return PROGRESSIVE_DISEASE;

        // Neither category fits, return stable disease.
        return STABLE_DISEASE;
    }

    private double min(double[] f, int endOffset)
    {
        double min = Double.MAX_VALUE;
        for( int i = 0; i < endOffset; i++ )
        {
            if( f[i] < min )
                min = f[i];
        }
        return min;
    }

    private String getStudyUnits(ImageAnnotation imageAnnotation, String metric)
    {
        for(int j = 0; j < imageAnnotation.getNumberOfCalculationCollections(); j++)
        {
            CalculationCollection calculationCollection = imageAnnotation.getCalculationCollection(j);
            for(int k = 0; k < calculationCollection.getNumberOfCalculations(); k++)
            {
                Calculation calculation = calculationCollection.getCalculation(k);

                /** We want the units of this calculation type. **/
                if(metric.equalsIgnoreCase(calculation.getDescription()) || metric.equalsIgnoreCase(calculation.getType()))
                {
                    for(int l = 0; l < calculation.getNumberOfCalculationResultCollections(); l++)
                    {
                        CalculationResultCollection calculationResultCollection = calculation.getCalculationResultCollection(l);

                        for( int m = 0; m < calculationResultCollection.getNumberOfCalculationResults(); )
                            return calculationResultCollection.getCalculationResult(m).getUnitOfMeasure();
                    }
                }
            }
        }

        return "";
    }

    public void setBaselineUnitOfMeasure(String baselineUnitOfMeasure)
    {
        this.baselineUnitOfMeasure = baselineUnitOfMeasure;
    }

    public String getBaselineUnitOfMeasure()
    {
        return baselineUnitOfMeasure;
    }

    public void setMetric(String metric)
    {
        this.metric = metric;
    }

    public String getMetric()
    {
        return metric;
    }

    public void setMetricValues(String[][] metricValues)
    {
        this.metricValues = metricValues;
    }

    public String[][] getMetricValues()
    {
        return metricValues;
    }

    public void setResponseRates(String[] responseRates)
    {
        this.responseRates = responseRates;
    }

    public String[] getResponseRates()
    {
        return responseRates;
    }

    public void setStudyDates(String[] studyDates)
    {
        this.studyDates = studyDates;
    }

    public String[] getStudyDates()
    {
        return studyDates;
    }

    public void setMetricSums(double[] metricSumsDouble)
    {
        this.metricSumsDouble = metricSumsDouble;
    }

    public String[] getMetricSums()
    {
        return metricSums;
    }

    public double[] getMetricSumsDouble()
    {
        return metricSumsDouble;
    }

    public void setResponseCategories(String[] responseCategories)
    {
        this.responseCategories = responseCategories;
    }

    public String[] getResponseCategories()
    {
        return responseCategories;
    }

    public void setResponseRatesSinceBaseline(
            String[] responseRatesSinceBaseline)
    {
        this.responseRatesSinceBaseline = responseRatesSinceBaseline;
    }

    public String[] getResponseRatesSinceBaseline()
    {
        return responseRatesSinceBaseline;
    }

    public void setResponseRatesSinceNadir(String[] responseRatesSinceNadir)
    {
        this.responseRatesSinceNadir = responseRatesSinceNadir;
    }

    public String[] getResponseRatesSinceNadir()
    {
        return responseRatesSinceNadir;
    }
}
