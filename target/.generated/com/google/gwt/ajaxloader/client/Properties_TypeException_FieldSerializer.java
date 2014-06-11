package com.google.gwt.ajaxloader.client;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.impl.ReflectionHelper;

@SuppressWarnings("deprecation")
public class Properties_TypeException_FieldSerializer implements com.google.gwt.user.client.rpc.impl.TypeHandler {
  public static void deserialize(SerializationStreamReader streamReader, com.google.gwt.ajaxloader.client.Properties.TypeException instance) throws SerializationException {
    
    com.google.gwt.user.client.rpc.core.java.lang.Exception_FieldSerializer.deserialize(streamReader, instance);
  }
  
  public static native com.google.gwt.ajaxloader.client.Properties.TypeException instantiate(SerializationStreamReader streamReader) throws SerializationException /*-{
    return @com.google.gwt.ajaxloader.client.Properties.TypeException::new()();
  }-*/;
  
  public static void serialize(SerializationStreamWriter streamWriter, com.google.gwt.ajaxloader.client.Properties.TypeException instance) throws SerializationException {
    
    com.google.gwt.user.client.rpc.core.java.lang.Exception_FieldSerializer.serialize(streamWriter, instance);
  }
  
  public Object create(SerializationStreamReader reader) throws SerializationException {
    return com.google.gwt.ajaxloader.client.Properties_TypeException_FieldSerializer.instantiate(reader);
  }
  
  public void deserial(SerializationStreamReader reader, Object object) throws SerializationException {
    com.google.gwt.ajaxloader.client.Properties_TypeException_FieldSerializer.deserialize(reader, (com.google.gwt.ajaxloader.client.Properties.TypeException)object);
  }
  
  public void serial(SerializationStreamWriter writer, Object object) throws SerializationException {
    com.google.gwt.ajaxloader.client.Properties_TypeException_FieldSerializer.serialize(writer, (com.google.gwt.ajaxloader.client.Properties.TypeException)object);
  }
  
}
