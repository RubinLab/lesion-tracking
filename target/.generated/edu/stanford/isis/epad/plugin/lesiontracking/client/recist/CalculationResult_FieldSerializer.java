package edu.stanford.isis.epad.plugin.lesiontracking.client.recist;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.impl.ReflectionHelper;

@SuppressWarnings("deprecation")
public class CalculationResult_FieldSerializer implements com.google.gwt.user.client.rpc.impl.TypeHandler {
  private static native java.lang.String getAnalysisImageURL(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult instance) /*-{
    return instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult::analysisImageURL;
  }-*/;
  
  private static native void setAnalysisImageURL(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult instance, java.lang.String value) 
  /*-{
    instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult::analysisImageURL = value;
  }-*/;
  
  private static native java.lang.String[] getImageURLs(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult instance) /*-{
    return instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult::imageURLs;
  }-*/;
  
  private static native void setImageURLs(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult instance, java.lang.String[] value) 
  /*-{
    instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult::imageURLs = value;
  }-*/;
  
  private static native edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion[] getLesions(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult instance) /*-{
    return instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult::lesions;
  }-*/;
  
  private static native void setLesions(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult instance, edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion[] value) 
  /*-{
    instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult::lesions = value;
  }-*/;
  
  private static native java.lang.String[][] getMetricSums(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult instance) /*-{
    return instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult::metricSums;
  }-*/;
  
  private static native void setMetricSums(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult instance, java.lang.String[][] value) 
  /*-{
    instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult::metricSums = value;
  }-*/;
  
  private static native java.lang.String[] getMetricUnits(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult instance) /*-{
    return instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult::metricUnits;
  }-*/;
  
  private static native void setMetricUnits(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult instance, java.lang.String[] value) 
  /*-{
    instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult::metricUnits = value;
  }-*/;
  
  private static native java.lang.String[] getMetrics(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult instance) /*-{
    return instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult::metrics;
  }-*/;
  
  private static native void setMetrics(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult instance, java.lang.String[] value) 
  /*-{
    instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult::metrics = value;
  }-*/;
  
  private static native int getNumberOfTimePointsInStudy(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult instance) /*-{
    return instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult::numberOfTimePointsInStudy;
  }-*/;
  
  private static native void setNumberOfTimePointsInStudy(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult instance, int value) 
  /*-{
    instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult::numberOfTimePointsInStudy = value;
  }-*/;
  
  private static native java.lang.String[][] getResponseCategories(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult instance) /*-{
    return instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult::responseCategories;
  }-*/;
  
  private static native void setResponseCategories(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult instance, java.lang.String[][] value) 
  /*-{
    instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult::responseCategories = value;
  }-*/;
  
  private static native java.lang.String[][] getResponseRates(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult instance) /*-{
    return instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult::responseRates;
  }-*/;
  
  private static native void setResponseRates(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult instance, java.lang.String[][] value) 
  /*-{
    instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult::responseRates = value;
  }-*/;
  
  private static native java.lang.String[][] getResponseRatesSinceBaseline(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult instance) /*-{
    return instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult::responseRatesSinceBaseline;
  }-*/;
  
  private static native void setResponseRatesSinceBaseline(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult instance, java.lang.String[][] value) 
  /*-{
    instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult::responseRatesSinceBaseline = value;
  }-*/;
  
  private static native java.lang.String[][] getResponseRatesSinceNadir(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult instance) /*-{
    return instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult::responseRatesSinceNadir;
  }-*/;
  
  private static native void setResponseRatesSinceNadir(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult instance, java.lang.String[][] value) 
  /*-{
    instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult::responseRatesSinceNadir = value;
  }-*/;
  
  private static native java.lang.String[][] getStudyDates(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult instance) /*-{
    return instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult::studyDates;
  }-*/;
  
  private static native void setStudyDates(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult instance, java.lang.String[][] value) 
  /*-{
    instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult::studyDates = value;
  }-*/;
  
  public static void deserialize(SerializationStreamReader streamReader, edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult instance) throws SerializationException {
    setAnalysisImageURL(instance, streamReader.readString());
    setImageURLs(instance, (java.lang.String[]) streamReader.readObject());
    setLesions(instance, (edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion[]) streamReader.readObject());
    setMetricSums(instance, (java.lang.String[][]) streamReader.readObject());
    setMetricUnits(instance, (java.lang.String[]) streamReader.readObject());
    setMetrics(instance, (java.lang.String[]) streamReader.readObject());
    setNumberOfTimePointsInStudy(instance, streamReader.readInt());
    setResponseCategories(instance, (java.lang.String[][]) streamReader.readObject());
    setResponseRates(instance, (java.lang.String[][]) streamReader.readObject());
    setResponseRatesSinceBaseline(instance, (java.lang.String[][]) streamReader.readObject());
    setResponseRatesSinceNadir(instance, (java.lang.String[][]) streamReader.readObject());
    setStudyDates(instance, (java.lang.String[][]) streamReader.readObject());
    
  }
  
  public static edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult instantiate(SerializationStreamReader streamReader) throws SerializationException {
    return new edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult();
  }
  
  public static void serialize(SerializationStreamWriter streamWriter, edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult instance) throws SerializationException {
    streamWriter.writeString(getAnalysisImageURL(instance));
    streamWriter.writeObject(getImageURLs(instance));
    streamWriter.writeObject(getLesions(instance));
    streamWriter.writeObject(getMetricSums(instance));
    streamWriter.writeObject(getMetricUnits(instance));
    streamWriter.writeObject(getMetrics(instance));
    streamWriter.writeInt(getNumberOfTimePointsInStudy(instance));
    streamWriter.writeObject(getResponseCategories(instance));
    streamWriter.writeObject(getResponseRates(instance));
    streamWriter.writeObject(getResponseRatesSinceBaseline(instance));
    streamWriter.writeObject(getResponseRatesSinceNadir(instance));
    streamWriter.writeObject(getStudyDates(instance));
    
  }
  
  public Object create(SerializationStreamReader reader) throws SerializationException {
    return edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult_FieldSerializer.instantiate(reader);
  }
  
  public void deserial(SerializationStreamReader reader, Object object) throws SerializationException {
    edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult_FieldSerializer.deserialize(reader, (edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult)object);
  }
  
  public void serial(SerializationStreamWriter writer, Object object) throws SerializationException {
    edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult_FieldSerializer.serialize(writer, (edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult)object);
  }
  
}
