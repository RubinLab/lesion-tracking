package edu.stanford.isis.epad.plugin.lesiontracking.shared;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.impl.ReflectionHelper;

@SuppressWarnings("deprecation")
public class ImageAnnotation_FieldSerializer implements com.google.gwt.user.client.rpc.impl.TypeHandler {
  private static native java.lang.String getAimFilename(edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation instance) /*-{
    return instance.@edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation::aimFilename;
  }-*/;
  
  private static native void setAimFilename(edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation instance, java.lang.String value) 
  /*-{
    instance.@edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation::aimFilename = value;
  }-*/;
  
  public static void deserialize(SerializationStreamReader streamReader, edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation instance) throws SerializationException {
    setAimFilename(instance, streamReader.readString());
    
    edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMElement_FieldSerializer.deserialize(streamReader, instance);
  }
  
  public static edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation instantiate(SerializationStreamReader streamReader) throws SerializationException {
    return new edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation();
  }
  
  public static void serialize(SerializationStreamWriter streamWriter, edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation instance) throws SerializationException {
    streamWriter.writeString(getAimFilename(instance));
    
    edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMElement_FieldSerializer.serialize(streamWriter, instance);
  }
  
  public Object create(SerializationStreamReader reader) throws SerializationException {
    return edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation_FieldSerializer.instantiate(reader);
  }
  
  public void deserial(SerializationStreamReader reader, Object object) throws SerializationException {
    edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation_FieldSerializer.deserialize(reader, (edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation)object);
  }
  
  public void serial(SerializationStreamWriter writer, Object object) throws SerializationException {
    edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation_FieldSerializer.serialize(writer, (edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation)object);
  }
  
}
