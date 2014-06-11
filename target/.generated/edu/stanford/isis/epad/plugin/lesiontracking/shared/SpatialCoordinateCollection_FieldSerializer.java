package edu.stanford.isis.epad.plugin.lesiontracking.shared;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.impl.ReflectionHelper;

@SuppressWarnings("deprecation")
public class SpatialCoordinateCollection_FieldSerializer implements com.google.gwt.user.client.rpc.impl.TypeHandler {
  public static void deserialize(SerializationStreamReader streamReader, edu.stanford.isis.epad.plugin.lesiontracking.shared.SpatialCoordinateCollection instance) throws SerializationException {
    
    edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMElement_FieldSerializer.deserialize(streamReader, instance);
  }
  
  public static edu.stanford.isis.epad.plugin.lesiontracking.shared.SpatialCoordinateCollection instantiate(SerializationStreamReader streamReader) throws SerializationException {
    return new edu.stanford.isis.epad.plugin.lesiontracking.shared.SpatialCoordinateCollection();
  }
  
  public static void serialize(SerializationStreamWriter streamWriter, edu.stanford.isis.epad.plugin.lesiontracking.shared.SpatialCoordinateCollection instance) throws SerializationException {
    
    edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMElement_FieldSerializer.serialize(streamWriter, instance);
  }
  
  public Object create(SerializationStreamReader reader) throws SerializationException {
    return edu.stanford.isis.epad.plugin.lesiontracking.shared.SpatialCoordinateCollection_FieldSerializer.instantiate(reader);
  }
  
  public void deserial(SerializationStreamReader reader, Object object) throws SerializationException {
    edu.stanford.isis.epad.plugin.lesiontracking.shared.SpatialCoordinateCollection_FieldSerializer.deserialize(reader, (edu.stanford.isis.epad.plugin.lesiontracking.shared.SpatialCoordinateCollection)object);
  }
  
  public void serial(SerializationStreamWriter writer, Object object) throws SerializationException {
    edu.stanford.isis.epad.plugin.lesiontracking.shared.SpatialCoordinateCollection_FieldSerializer.serialize(writer, (edu.stanford.isis.epad.plugin.lesiontracking.shared.SpatialCoordinateCollection)object);
  }
  
}
