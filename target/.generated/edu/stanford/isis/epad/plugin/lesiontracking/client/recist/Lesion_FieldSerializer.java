package edu.stanford.isis.epad.plugin.lesiontracking.client.recist;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.impl.ReflectionHelper;

@SuppressWarnings("deprecation")
public class Lesion_FieldSerializer implements com.google.gwt.user.client.rpc.impl.TypeHandler {
  private static native java.lang.String getLesionID(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion instance) /*-{
    return instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion::lesionID;
  }-*/;
  
  private static native void setLesionID(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion instance, java.lang.String value) 
  /*-{
    instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion::lesionID = value;
  }-*/;
  
  private static native java.lang.String getLocation(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion instance) /*-{
    return instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion::location;
  }-*/;
  
  private static native void setLocation(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion instance, java.lang.String value) 
  /*-{
    instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion::location = value;
  }-*/;
  
  private static native java.util.ArrayList getMetrics(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion instance) /*-{
    return instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion::metrics;
  }-*/;
  
  private static native void setMetrics(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion instance, java.util.ArrayList value) 
  /*-{
    instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion::metrics = value;
  }-*/;
  
  private static native java.util.HashMap getTemporalMeasurements(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion instance) /*-{
    return instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion::temporalMeasurements;
  }-*/;
  
  private static native void setTemporalMeasurements(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion instance, java.util.HashMap value) 
  /*-{
    instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion::temporalMeasurements = value;
  }-*/;
  
  private static native java.util.ArrayList getWadoURLs(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion instance) /*-{
    return instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion::wadoURLs;
  }-*/;
  
  private static native void setWadoURLs(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion instance, java.util.ArrayList value) 
  /*-{
    instance.@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion::wadoURLs = value;
  }-*/;
  
  public static void deserialize(SerializationStreamReader streamReader, edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion instance) throws SerializationException {
    setLesionID(instance, streamReader.readString());
    setLocation(instance, streamReader.readString());
    setMetrics(instance, (java.util.ArrayList) streamReader.readObject());
    setTemporalMeasurements(instance, (java.util.HashMap) streamReader.readObject());
    setWadoURLs(instance, (java.util.ArrayList) streamReader.readObject());
    
  }
  
  public static edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion instantiate(SerializationStreamReader streamReader) throws SerializationException {
    return new edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion();
  }
  
  public static void serialize(SerializationStreamWriter streamWriter, edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion instance) throws SerializationException {
    streamWriter.writeString(getLesionID(instance));
    streamWriter.writeString(getLocation(instance));
    streamWriter.writeObject(getMetrics(instance));
    streamWriter.writeObject(getTemporalMeasurements(instance));
    streamWriter.writeObject(getWadoURLs(instance));
    
  }
  
  public Object create(SerializationStreamReader reader) throws SerializationException {
    return edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion_FieldSerializer.instantiate(reader);
  }
  
  public void deserial(SerializationStreamReader reader, Object object) throws SerializationException {
    edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion_FieldSerializer.deserialize(reader, (edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion)object);
  }
  
  public void serial(SerializationStreamWriter writer, Object object) throws SerializationException {
    edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion_FieldSerializer.serialize(writer, (edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion)object);
  }
  
}
