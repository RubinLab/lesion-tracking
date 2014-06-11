package edu.stanford.isis.epad.plugin.lesiontracking.shared;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.impl.ReflectionHelper;

@SuppressWarnings("deprecation")
public class AIMAttribute_FieldSerializer implements com.google.gwt.user.client.rpc.impl.TypeHandler {
  private static native java.lang.String getName(edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMAttribute instance) /*-{
    return instance.@edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMAttribute::name;
  }-*/;
  
  private static native void setName(edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMAttribute instance, java.lang.String value) 
  /*-{
    instance.@edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMAttribute::name = value;
  }-*/;
  
  private static native java.lang.String getValue(edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMAttribute instance) /*-{
    return instance.@edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMAttribute::value;
  }-*/;
  
  private static native void setValue(edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMAttribute instance, java.lang.String value) 
  /*-{
    instance.@edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMAttribute::value = value;
  }-*/;
  
  public static void deserialize(SerializationStreamReader streamReader, edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMAttribute instance) throws SerializationException {
    setName(instance, streamReader.readString());
    setValue(instance, streamReader.readString());
    
  }
  
  public static edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMAttribute instantiate(SerializationStreamReader streamReader) throws SerializationException {
    return new edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMAttribute();
  }
  
  public static void serialize(SerializationStreamWriter streamWriter, edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMAttribute instance) throws SerializationException {
    streamWriter.writeString(getName(instance));
    streamWriter.writeString(getValue(instance));
    
  }
  
  public Object create(SerializationStreamReader reader) throws SerializationException {
    return edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMAttribute_FieldSerializer.instantiate(reader);
  }
  
  public void deserial(SerializationStreamReader reader, Object object) throws SerializationException {
    edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMAttribute_FieldSerializer.deserialize(reader, (edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMAttribute)object);
  }
  
  public void serial(SerializationStreamWriter writer, Object object) throws SerializationException {
    edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMAttribute_FieldSerializer.serialize(writer, (edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMAttribute)object);
  }
  
}
