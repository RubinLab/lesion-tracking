package edu.stanford.isis.epad.plugin.lesiontracking.shared;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.impl.ReflectionHelper;

@SuppressWarnings("deprecation")
public class AIMElement_FieldSerializer {
  private static native java.util.ArrayList getAimAttributes(edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMElement instance) /*-{
    return instance.@edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMElement::aimAttributes;
  }-*/;
  
  private static native void setAimAttributes(edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMElement instance, java.util.ArrayList value) 
  /*-{
    instance.@edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMElement::aimAttributes = value;
  }-*/;
  
  private static native java.util.ArrayList getAimElements(edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMElement instance) /*-{
    return instance.@edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMElement::aimElements;
  }-*/;
  
  private static native void setAimElements(edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMElement instance, java.util.ArrayList value) 
  /*-{
    instance.@edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMElement::aimElements = value;
  }-*/;
  
  public static void deserialize(SerializationStreamReader streamReader, edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMElement instance) throws SerializationException {
    setAimAttributes(instance, (java.util.ArrayList) streamReader.readObject());
    setAimElements(instance, (java.util.ArrayList) streamReader.readObject());
    
  }
  
  public static void serialize(SerializationStreamWriter streamWriter, edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMElement instance) throws SerializationException {
    streamWriter.writeObject(getAimAttributes(instance));
    streamWriter.writeObject(getAimElements(instance));
    
  }
  
}
