package edu.stanford.isis.epad.plugin.lesiontracking.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jfree.util.Log;

import com.google.gwt.core.shared.GWT;

import edu.stanford.hakan.aim4api.compability.aimv3.Calculation;
import edu.stanford.hakan.aim4api.compability.aimv3.CalculationCollection;
import edu.stanford.hakan.aim4api.compability.aimv3.CalculationData;
import edu.stanford.hakan.aim4api.compability.aimv3.CalculationDataCollection;
import edu.stanford.hakan.aim4api.compability.aimv3.CalculationResult;
import edu.stanford.hakan.aim4api.compability.aimv3.CalculationResultCollection;
import edu.stanford.hakan.aim4api.compability.aimv3.ImageAnnotation;
import edu.stanford.hakan.aim4api.project.epad.Aim;



public class TumorAnalysisCalculator
{
    private Map<Date, List<ImageAnnotation>> imageAnnotationsByStudyDate;
    static final TempLogger logger = TempLogger.getInstance();

    

    
    public class CalculationResultL
    {
        
    	private static final String COMPLETE_RESPONSE   = "CR",
                PARTIAL_RESPONSE    = "PR",
                STABLE_DISEASE	    = "SD",
                PROGRESSIVE_DISEASE = "PD";
        private Map<Date, Map<String, ImageAnnotation>> imageAnnotationsByNameAndStudyDate;
        private Map<ImageAnnotation, Float> metricValuesByImageAnnotation = new HashMap<ImageAnnotation, Float>();
        private Map<ImageAnnotation, String> anatomicEntitiesByImageAnnotation = new HashMap<ImageAnnotation, String>();
        private List<String> sortedImageAnnotationNames = new ArrayList<String>();
        private Map<String, String> anatomicEntityNamesByImageAnnotationName = new HashMap<String, String>();
        private List<Date> sortedStudyDates = new ArrayList<Date>();
        private Map<Date, Float> metricSumsByStudyDate = new HashMap<Date, Float>(),
        						 responseRatesByStudyDate = new HashMap<Date, Float>();
    	private String metric,
    				   unitOfMeasure;
    	
    	
    	
    	public CalculationResultL(String metric, String unitOfMeasure)
    	{
    		this.setMetric(metric);
    		this.setUnitOfMeasure(unitOfMeasure);
    		
    	}
    	
        public String getResponseCategory(double responseRate)
        {
            // Disappearance of target lesions.
            if(responseRate == -1) return COMPLETE_RESPONSE;

            // Reduction in sum of the longest diameter of target
            // lesions since baseline.
            if(responseRate <= -0.30) return PARTIAL_RESPONSE;

            // Increase in the sum of the longest diameter of target
            // lesions since the smallest recorded sum.
            if(responseRate >= 0.20) return PROGRESSIVE_DISEASE;

            // Neither category fits, return stable disease.
            return STABLE_DISEASE;
        }

		public String getMetric()
		{
			return metric;
		}

		public void setMetric(String metric)
		{
			this.metric = metric;
		}

		public String getUnitOfMeasure()
		{
			return unitOfMeasure;
		}

		public void setUnitOfMeasure(String unitOfMeasure)
		{
			this.unitOfMeasure = unitOfMeasure;
		}

		public List<Date> getSortedStudyDates() {
			return sortedStudyDates;
		}

		public void setSortedStudyDates(List<Date> sortedStudyDates) {
			this.sortedStudyDates = sortedStudyDates;
		}

		@Override
		public String toString()
		{
			StringBuffer sb = new StringBuffer();
			sb.append("Metric: " + metric + "\n");
			sb.append("Units:  " + unitOfMeasure + "\n\n");
			
			// Study dates across the top, with left most column for lesions.
			sb.append(fillToNLength(15, ""));
			for(Date studyDate : getSortedStudyDates())
				sb.append(fillToNLength(35, studyDate.toString()));
			
			sb.append("\n");
			
	        for(String imageAnnotationName : sortedImageAnnotationNames)
	        {
	        	String anatomicEntityCodeMeaning = "";
	        	if(getAnatomicEntityNamesByImageAnnotationName().containsKey(imageAnnotationName))
	        		anatomicEntityCodeMeaning += getAnatomicEntityNamesByImageAnnotationName().get(imageAnnotationName);
				sb.append(fillToNLength(15, imageAnnotationName + " " + anatomicEntityCodeMeaning));
				
				for(Date studyDate : sortedStudyDates)
				{
					Map<String, ImageAnnotation> imageAnnotationsByName = getImageAnnotationsByNameAndStudyDate().get(studyDate);
					if(imageAnnotationsByName.containsKey(imageAnnotationName))
					{
						ImageAnnotation imageAnnotation = imageAnnotationsByName.get(imageAnnotationName);
						Float metricValue = metricValuesByImageAnnotation.get(imageAnnotation);
						if(metricValue != null)
							sb.append(fillToNLength(35, metricValue.toString()));
						else
							sb.append(fillToNLength(35, "null"));
					}
					else
					{
						sb.append(fillToNLength(35, ""));
					}
				}
				
				sb.append("\n");
	        }
	        
			sb.append(fillToNLength(15, ""));
			for(Date studyDate : sortedStudyDates)
			{
				Float metricSum = metricSumsByStudyDate.get(studyDate);
				sb.append(fillToNLength(35, metricSum.toString()));
			}
			sb.append("\n");
			return sb.toString();
		}
		
		private String fillToNLength(int n, String string)
		{
			for(int i = string.length(); i < n; i++)
				string += " ";
			return string;
		}

		public List<String> getSortedImageAnnotationNames() {
			return sortedImageAnnotationNames;
		}

		public void setSortedImageAnnotationNames(List<String> sortedImageAnnotationNames) {
			this.sortedImageAnnotationNames = sortedImageAnnotationNames;
		}

		public Map<ImageAnnotation, Float> getMetricValuesByImageAnnotation() {
			return metricValuesByImageAnnotation;
		}

		public void setMetricValuesByImageAnnotation(
				Map<ImageAnnotation, Float> metricValuesByImageAnnotation) {
			this.metricValuesByImageAnnotation = metricValuesByImageAnnotation;
		}

		public Map<ImageAnnotation, String> getAnatomicEntitiesByImageAnnotation() {
			return anatomicEntitiesByImageAnnotation;
		}

		public void setAnatomicEntitiesByImageAnnotation(
				Map<ImageAnnotation, String> anatomicEntitiesByImageAnnotation) {
			this.anatomicEntitiesByImageAnnotation = anatomicEntitiesByImageAnnotation;
		}

		public Map<Date, Float> getMetricSumsByStudyDate() {
			return metricSumsByStudyDate;
		}

		public void setMetricSumsByStudyDate(Map<Date, Float> metricSumsByStudyDate) {
			this.metricSumsByStudyDate = metricSumsByStudyDate;
		}

		public Map<Date, Map<String, ImageAnnotation>> getImageAnnotationsByNameAndStudyDate() {
			return imageAnnotationsByNameAndStudyDate;
		}

		public void setImageAnnotationsByNameAndStudyDate(
				Map<Date, Map<String, ImageAnnotation>> imageAnnotationsByNameAndStudyDate) {
			this.imageAnnotationsByNameAndStudyDate = imageAnnotationsByNameAndStudyDate;
		}

		public Map<String, String> getAnatomicEntityNamesByImageAnnotationName() {
			return anatomicEntityNamesByImageAnnotationName;
		}

		public void setAnatomicEntityNamesByImageAnnotationName(
				Map<String, String> anatomicEntityNamesByImageAnnotationName) {
			this.anatomicEntityNamesByImageAnnotationName = anatomicEntityNamesByImageAnnotationName;
		}

		public Map<Date, Float> getResponseRatesByStudyDate() {
			return responseRatesByStudyDate;
		}

		public void setResponseRatesByStudyDate(
				Map<Date, Float> responseRatesByStudyDate) {
			this.responseRatesByStudyDate = responseRatesByStudyDate;
		}
    }
    
    public TumorAnalysisCalculator(Map<Date, List<ImageAnnotation>> imageAnnotationsByStudyDate)
    {
        this.imageAnnotationsByStudyDate = imageAnnotationsByStudyDate;
    }
    
    public CalculationResultL calculateRECIST(String metric, String unitOfMeasure)
    {
    	logger.info("CALCULATE RECIST..");
    	CalculationResultL calculationResult = new CalculationResultL(metric, unitOfMeasure);
    	
        Map<ImageAnnotation, Float> metricValuesByImageAnnotation = calculationResult.getMetricValuesByImageAnnotation();
        Map<ImageAnnotation, String> anatomicEntitiesByImageAnnotation = calculationResult.getAnatomicEntitiesByImageAnnotation(); 
        Map<String, String> anatomicEntityNamesByImageAnnotationName = calculationResult.getAnatomicEntityNamesByImageAnnotationName();
        Map<Date, Float> metricSumsByStudyDate = calculationResult.getMetricSumsByStudyDate(),
        			     responseRatesByStudyDate = calculationResult.getResponseRatesByStudyDate();
    	final Set<String> imageAnnotationNames = new HashSet<String>(); 
        
    	logger.info("number of date keyset " + imageAnnotationsByStudyDate.size());
        // Calculate the metric sum for each study date
        for(Date studyDate : imageAnnotationsByStudyDate.keySet())
        {
        	logger.info("study date :" + studyDate);
        	metricSumsByStudyDate.put(studyDate, 0.0f);
        	logger.info("length of study's array " + imageAnnotationsByStudyDate.get(studyDate).size());
        	
        	for(ImageAnnotation imageAnnotation : imageAnnotationsByStudyDate.get(studyDate))
        	{
        		imageAnnotationNames.add(imageAnnotation.getName());
        		logger.info("Image annotation :" + imageAnnotation.getName());
        		
        		logger.info("Number of Image annotation calculation :" +imageAnnotation.getCalculationCollection().getCalculationList().size());
        		
				if(imageAnnotation.getCalculationCollection().getCalculationList().size() > 0)
				{
					CalculationCollection calculationCollection = imageAnnotation.getCalculationCollection();
					logger.info("calculation collection : " + calculationCollection.getCalculationList().get(0).getAlgorithmName());
                    Float metricValue = sumLesionMetricValues(calculationCollection, unitOfMeasure, metric);
                    metricValuesByImageAnnotation.put(imageAnnotation, metricValue);
                    metricSumsByStudyDate.put(studyDate, metricSumsByStudyDate.get(studyDate) + metricValue);
					
				}
        		
                
                if(imageAnnotation.getAnatomicEntityCollection().getAnatomicEntityList().size() > 0)
                {
                	String anatomicEntityCodeMeaning = imageAnnotation.getAnatomicEntityCollection().getAnatomicEntityList().get(0).getAllowedTerm().getCodeMeaning();
                	
                	anatomicEntitiesByImageAnnotation.put(imageAnnotation, anatomicEntityCodeMeaning);
                	String imageAnnotationNameAttribute = imageAnnotation.getName();
                	logger.info("anatomic "+anatomicEntityCodeMeaning);
                	if(!anatomicEntityNamesByImageAnnotationName.containsKey(imageAnnotationNameAttribute))
                		anatomicEntityNamesByImageAnnotationName.put(imageAnnotationNameAttribute, anatomicEntityCodeMeaning);
                }
                
//            	for(int k = 0; k < imageAnnotation.; k++)
//            	{
//                	ImagingPhysicalEntityCollection imagingPhysicalEntityCollection = imageAnnotation.getImagingPhysicalEntityCollection(k);
//                	
//                	for(int l = 0; l < imagingPhysicalEntityCollection.getNumberOfImagingPhysicalEntities(); l++)
//                	{
//                		ImagingPhysicalEntity imagingPhysicalEntity = imagingPhysicalEntityCollection.getImagingPhysicalEntity(l);
//                		
//                		if(imagingPhysicalEntity.getNumberOfLabels() > 0)
//                		{
//                			Label label = imagingPhysicalEntity.getLabel(0);
//                			
//                			if("Location".equalsIgnoreCase(label.getValue()))
//                			{
//                				if(imagingPhysicalEntity.getNumberOfTypeCodes() > 0)
//                				{
//                					TypeCode typeCode = imagingPhysicalEntity.getTypeCode(0);
//                                	
//                                	String anatomicEntityCodeSystem = typeCode.getCodeSystem();
//                                	//System.out.println("ANATOMIC ENTITY: " + anatomicEntityCodeSystem);
//                                	
//                                	anatomicEntitiesByImageAnnotation.put(imageAnnotation, anatomicEntityCodeSystem);
//                                	String imageAnnotationNameAttribute = imageAnnotation.getNameAttribute();
//                                	if(!anatomicEntityNamesByImageAnnotationName.containsKey(imageAnnotationNameAttribute))
//                                		anatomicEntityNamesByImageAnnotationName.put(imageAnnotationNameAttribute, anatomicEntityCodeSystem);
//                				}
//                			}
//                		}
//                	}
//            	}
                	
        	}
        }

        // Calculate the smallest metric sum recorded since the treatment started for each follow-up study.
        Map<Date, Float> minMetricSumsByStudyDate = new HashMap<Date, Float>();
        calculationResult.setSortedStudyDates(asSortedList(imageAnnotationsByStudyDate.keySet()));
        List<Date> sortedStudyDates = calculationResult.getSortedStudyDates();
        for(int i = 0; i < sortedStudyDates.size(); i++)
        {
        	Date studyDate = sortedStudyDates.get(i);
        	
        	float minMetric = Float.MAX_VALUE;
        	for(int j = 0; j <= i; j++)
        	{
        		Date previousStudyDate = sortedStudyDates.get(j);
        		float metricSum = metricSumsByStudyDate.get(previousStudyDate);
        		
        		if(metricSum < minMetric)
        			minMetric = metricSum;
        	}
        	
        	minMetricSumsByStudyDate.put(studyDate, minMetric);
        }

    	
    	// Get a set of the baseline lesion names.
    	Date baselineStudyDate = sortedStudyDates.get(0);
    	
        for(int i = 1; i < sortedStudyDates.size(); i++)
        {
        	Date studyDate = sortedStudyDates.get(i);
        	
        	// Verify that all lesions present in the baseline are present at this study date.
        	boolean missingOne = false;
        	Set<String> currentStudyDateImageAnnotationNames = new HashSet<String>();
        	for(ImageAnnotation imageAnnotation : imageAnnotationsByStudyDate.get(studyDate))
        		currentStudyDateImageAnnotationNames.add(imageAnnotation.getName());
        	for(ImageAnnotation imageAnnotation : imageAnnotationsByStudyDate.get(baselineStudyDate))
        		if(!currentStudyDateImageAnnotationNames.contains(imageAnnotation.getName()))
        		{
        			missingOne = true;
        			break;
        		}
        	
        	if(missingOne)
        		continue;
        	
        	float metricSum = metricSumsByStudyDate.get(studyDate);
        	float baselineSum = metricSumsByStudyDate.get(baselineStudyDate);
        	float minMetricSum = minMetricSumsByStudyDate.get(studyDate);
        	
        	float changeInMetricSinceBaseline = metricSum - baselineSum;
            float changeInMetricSumSinceMinMetricSum = metricSum - minMetricSum;

            
            Date lastStudyDate = sortedStudyDates.get(i-1);
            float lastStudyDateMetricSum = metricSumsByStudyDate.get(lastStudyDate);

            float responseRate = 0.0f;
            if(lastStudyDateMetricSum > 0)
            	responseRate = 100 * (metricSum - baselineSum) / baselineSum;
            /*
            if(changeInMetricSinceBaseline < 0 && baselineSum > 0)
            	responseRate = changeInMetricSinceBaseline / baselineSum;

            if(changeInMetricSumSinceMinMetricSum >= 0 && minMetricSum >  0)
                responseRate = changeInMetricSumSinceMinMetricSum / minMetricSum;
            */
            
            //System.out.println("Response rate: " + responseRate);
            responseRatesByStudyDate.put(studyDate, responseRate);
        }
    	
    	// Store a sorted version of the image annotation names.
        calculationResult.setSortedImageAnnotationNames(asSortedListWithComparator(imageAnnotationNames, new NaturalOrderComparator()));

        // Create a map that groups lesion by their names and study dates.
        calculationResult.setImageAnnotationsByNameAndStudyDate(new HashMap<Date, Map<String, ImageAnnotation>>());
        Map<Date, Map<String, ImageAnnotation>> imageAnnotationsByNameAndStudyDate = calculationResult.getImageAnnotationsByNameAndStudyDate();
		
        for(Date studyDate : sortedStudyDates)
        {
        	if(!imageAnnotationsByNameAndStudyDate.containsKey(studyDate))
        		imageAnnotationsByNameAndStudyDate.put(studyDate, new HashMap<String, ImageAnnotation>());
        	
        	for(ImageAnnotation imageAnnotation : imageAnnotationsByStudyDate.get(studyDate))
        	{
        		Map<String, ImageAnnotation> imageAnnotationsByName= imageAnnotationsByNameAndStudyDate.get(studyDate);
        		String imageAnnotationName = imageAnnotation.getName();
        		if(!imageAnnotationsByName.containsKey(imageAnnotationName) || metricValuesByImageAnnotation.get(imageAnnotationsByName.get(imageAnnotationName)) == null)
        			imageAnnotationsByName.put(imageAnnotationName, imageAnnotation);
        	}
        }
        
        return calculationResult;
    }
//    private float sumLesionMetricValues(CalculationEntityCollection calculationEntityCollection, String targetUnits, String metric)
//    {
//        float sum = 0;
//        //System.out.println("We have this many calculation entities: " + calculationEntityCollection.getNumberOfCalculationEntities());
//        for( int i = 0; i < calculationEntityCollection.getNumberOfCalculationEntities(); i++ )
//        {
//            CalculationEntity calculationEntity = calculationEntityCollection.getCalculationEntity(i);
//
//            /** Only include calculations that match the metric tag. **/
//
//            //System.out.println("We have this many descriptions: " + calculationEntity.getNumberOfDescriptions());
//            
//            if(calculationEntity.getNumberOfDescriptions() > 0)
//            {
//                Description description = calculationEntity.getDescription(0);
//                
//	            if(metric.equalsIgnoreCase(description.getValue()))
//	            {
//	                for( int j = 0; j < calculationEntity.getNumberOfCalculationResultCollections(); j++ )
//	                {
//	                    CalculationResultCollection calculationResultCollection = calculationEntity.getCalculationResultCollection(j);
//	                    for( int k = 0; k < calculationResultCollection.getNumberOfCalculationResults(); k++ )
//	                    {
//	                    	edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationResult calculationResult = calculationResultCollection.getCalculationResult(k);
//	                        UnitConversion unitConversion = new UnitConversion(calculationResult.getUnitOfMeasure(), targetUnits);
//	
//	                        /**
//	                         * This is a backwards compatibility check. If we can't find any CalculationDataCollection
//	                         * objects, we will look for the older type "DataCollection"
//	                         */
//	                        if(calculationResult.getNumberOfCalculationDataCollections() > 0)
//	                        {
//	                            for( int l = 0; l < calculationResult.getNumberOfCalculationDataCollections(); l++ )
//	                            {
//	                                CalculationDataCollection calculationDataCollection = calculationResult.getCalculationDataCollection(l);
//	                                for( int m = 0; m < calculationDataCollection.getNumberOfCalculationDatas(); m++ )
//	                                {
//	                                    CalculationData calculationData = calculationDataCollection.getCalculationData(m);
//	                                    
//	                                    for(int n = 0; n < calculationData.getNumberOfValues(); n++)
//	                                    {
//	                                    	Value value = calculationData.getValue(n);
//	                                    	sum += unitConversion.convertToTargetUnit(Float.parseFloat(value.getValue()));
//	                                    }
//	                                }
//	                            }
//	                        }
//	                        else
//	                        {
//	                            for( int l = 0; l < calculationResult.getNumberOfDataCollections(); l++ )
//	                            {
//	                                DataCollection dataCollection = calculationResult.getDataCollection(l);
//	                                for( int m = 0; m < dataCollection.getNumberOfDatas(); m++ )
//	                                {
//	                                    Data data = dataCollection.getData(m);
//	                                    sum += unitConversion.convertToTargetUnit(Float.parseFloat(data.getValue()));
//	                                }
//	                            }
//	                        }
//	                    }
//	                }
//	            }
//            }
//        }
//        return sum;
//    }
    
    private float sumLesionMetricValues(CalculationCollection calculationCollection, String targetUnits, String metric)
    {
        float sum = 0;
        for( int i = 0; i < calculationCollection.getCalculationList().size(); i++ )
        {
            Calculation calculation = calculationCollection.getCalculationList().get(i);
            /** Only include calculations that match the metric tag. **/
            
            logger.info("metric " + metric + " calc desc " + calculation.getDescription());
            
            if(metric.equalsIgnoreCase(calculation.getDescription()) || metric.equalsIgnoreCase(calculation.getCodeMeaning()))
            {
            	logger.info("Number of cal results " + calculation.getCalculationResultCollection().getCalculationResultList().size() );
                for( int j = 0; j < calculation.getCalculationResultCollection().getCalculationResultList().size(); j++ )
                {
                	
                    CalculationResult calculationResult = calculation.getCalculationResultCollection().getCalculationResultList().get(j);
                    UnitConversion unitConversion = new UnitConversion(calculationResult.getUnitOfMeasure(), targetUnits);

                        logger.info("target units : " + targetUnits + " unit of measure " + calculationResult.getUnitOfMeasure());
                        /**
                         * This is a backwards compatibility check. If we can't find any CalculationDataCollection
                         * objects, we will look for the older type "DataCollection"
                         */
                        for( int l = 0; l < calculationResult.getCalculationDataCollection().getCalculationDataList().size(); l++ )
                            {
                                CalculationDataCollection calculationDataCollection = calculationResult.getCalculationDataCollection();
                                for( int m = 0; m < calculationDataCollection.getCalculationDataList().size(); m++ )
                                {
                                    CalculationData calculationData = calculationDataCollection.getCalculationDataList().get(m);
                                    logger.info("the value in calculation is:"+calculationData.getValue());
                                    sum += unitConversion.convertToTargetUnit(calculationData.getValue());
                                }
                            }
                        
                      
                }
            }
        }
        return sum;
    }
    
    private static <T extends Comparable<? super T>> List<T> asSortedList(Collection<T> c) {
      List<T> list = new ArrayList<T>(c);
      java.util.Collections.sort(list);
      return list;
    }
    
    private static <T> List<T> asSortedListWithComparator(Collection<T> c, Comparator<T> comparator) {
        List<T> list = new ArrayList<T>(c);
        java.util.Collections.sort(list, comparator);
        return list;
      }
}