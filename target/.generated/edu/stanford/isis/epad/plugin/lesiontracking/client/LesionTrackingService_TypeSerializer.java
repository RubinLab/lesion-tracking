package edu.stanford.isis.epad.plugin.lesiontracking.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.user.client.rpc.impl.TypeHandler;
import java.util.HashMap;
import java.util.Map;
import com.google.gwt.core.client.GwtScriptOnly;

public class LesionTrackingService_TypeSerializer extends com.google.gwt.user.client.rpc.impl.SerializerBase {
  private static final MethodMap methodMapNative;
  private static final JsArrayString signatureMapNative;
  
  static {
    methodMapNative = loadMethodsNative();
    signatureMapNative = loadSignaturesNative();
  }
  
  @SuppressWarnings("deprecation")
  @GwtScriptOnly
  private static native MethodMap loadMethodsNative() /*-{
    var result = {};
    result["com.google.gwt.ajaxloader.client.Properties$TypeException/806639172"] = [
        @com.google.gwt.ajaxloader.client.Properties_TypeException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.ajaxloader.client.Properties_TypeException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Lcom/google/gwt/ajaxloader/client/Properties$TypeException;),
      ];
    
    result["com.google.gwt.event.shared.UmbrellaException/3104463596"] = [
        @com.google.gwt.event.shared.UmbrellaException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.event.shared.UmbrellaException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Lcom/google/gwt/event/shared/UmbrellaException;),
      ];
    
    result["com.google.gwt.http.client.RequestException/190587325"] = [
        @com.google.gwt.http.client.RequestException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.http.client.RequestException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Lcom/google/gwt/http/client/RequestException;),
      ];
    
    result["com.google.gwt.json.client.JSONException/2941795468"] = [
        @com.google.gwt.json.client.JSONException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.json.client.JSONException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Lcom/google/gwt/json/client/JSONException;),
      ];
    
    result["com.google.gwt.jsonp.client.TimeoutException/1112787596"] = [
        @com.google.gwt.jsonp.client.TimeoutException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.jsonp.client.TimeoutException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Lcom/google/gwt/jsonp/client/TimeoutException;),
      ];
    
    result["com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException/3936916533"] = [
        @com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Lcom/google/gwt/user/client/rpc/IncompatibleRemoteServiceException;),
        @com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException_FieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Lcom/google/gwt/user/client/rpc/IncompatibleRemoteServiceException;)
      ];
    
    result["com.google.gwt.user.client.rpc.RpcTokenException/2345075298"] = [
        @com.google.gwt.user.client.rpc.RpcTokenException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.RpcTokenException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Lcom/google/gwt/user/client/rpc/RpcTokenException;),
      ];
    
    result["com.google.gwt.user.client.rpc.SerializableException/3047383460"] = [
        @com.google.gwt.user.client.rpc.SerializableException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.SerializableException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Lcom/google/gwt/user/client/rpc/SerializableException;),
      ];
    
    result["com.google.gwt.user.client.rpc.SerializationException/2836333220"] = [
        @com.google.gwt.user.client.rpc.SerializationException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.SerializationException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Lcom/google/gwt/user/client/rpc/SerializationException;),
      ];
    
    result["com.google.gwt.user.client.rpc.SerializedTypeViolationException/914601580"] = [
        @com.google.gwt.user.client.rpc.SerializedTypeViolationException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.SerializedTypeViolationException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Lcom/google/gwt/user/client/rpc/SerializedTypeViolationException;),
      ];
    
    result["com.google.gwt.user.client.rpc.ServiceDefTarget$NoServiceEntryPointSpecifiedException/3408313447"] = [
        @com.google.gwt.user.client.rpc.ServiceDefTarget_NoServiceEntryPointSpecifiedException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.ServiceDefTarget_NoServiceEntryPointSpecifiedException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Lcom/google/gwt/user/client/rpc/ServiceDefTarget$NoServiceEntryPointSpecifiedException;),
      ];
    
    result["com.google.gwt.user.client.rpc.XsrfToken/4254043109"] = [
        ,
        ,
        @com.google.gwt.user.client.rpc.XsrfToken_FieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Lcom/google/gwt/user/client/rpc/XsrfToken;)
      ];
    
    result["com.google.gwt.user.client.ui.ChangeListenerCollection/287642957"] = [
        @com.google.gwt.user.client.ui.ChangeListenerCollection_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.ui.ChangeListenerCollection_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Lcom/google/gwt/user/client/ui/ChangeListenerCollection;),
      ];
    
    result["com.google.gwt.user.client.ui.ClickListenerCollection/2152455986"] = [
        @com.google.gwt.user.client.ui.ClickListenerCollection_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.ui.ClickListenerCollection_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Lcom/google/gwt/user/client/ui/ClickListenerCollection;),
      ];
    
    result["com.google.gwt.user.client.ui.FocusListenerCollection/119490835"] = [
        @com.google.gwt.user.client.ui.FocusListenerCollection_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.ui.FocusListenerCollection_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Lcom/google/gwt/user/client/ui/FocusListenerCollection;),
      ];
    
    result["com.google.gwt.user.client.ui.KeyboardListenerCollection/1040442242"] = [
        @com.google.gwt.user.client.ui.KeyboardListenerCollection_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.ui.KeyboardListenerCollection_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Lcom/google/gwt/user/client/ui/KeyboardListenerCollection;),
      ];
    
    result["com.google.gwt.xml.client.impl.DOMParseException/3799120635"] = [
        @com.google.gwt.xml.client.impl.DOMParseException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.xml.client.impl.DOMParseException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Lcom/google/gwt/xml/client/impl/DOMParseException;),
      ];
    
    result["com.google.web.bindery.event.shared.UmbrellaException/1025846929"] = [
        @com.google.web.bindery.event.shared.UmbrellaException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.web.bindery.event.shared.UmbrellaException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Lcom/google/web/bindery/event/shared/UmbrellaException;),
      ];
    
    result["edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult/2956891268"] = [
        ,
        ,
        @edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult_FieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Ledu/stanford/isis/epad/plugin/lesiontracking/client/recist/CalculationResult;)
      ];
    
    result["edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion/1448412761"] = [
        ,
        ,
        @edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion_FieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Ledu/stanford/isis/epad/plugin/lesiontracking/client/recist/Lesion;)
      ];
    
    result["[Ledu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion;/485178529"] = [
        ,
        ,
        @edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion_Array_Rank_1_FieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;[Ledu/stanford/isis/epad/plugin/lesiontracking/client/recist/Lesion;)
      ];
    
    result["edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMAttribute/3290996244"] = [
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMAttribute_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMAttribute_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ledu/stanford/isis/epad/plugin/lesiontracking/shared/AIMAttribute;),
      ];
    
    result["edu.stanford.isis.epad.plugin.lesiontracking.shared.AnatomicEntity/4198021387"] = [
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.AnatomicEntity_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.AnatomicEntity_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ledu/stanford/isis/epad/plugin/lesiontracking/shared/AnatomicEntity;),
      ];
    
    result["edu.stanford.isis.epad.plugin.lesiontracking.shared.AnatomicEntityCollection/4026145024"] = [
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.AnatomicEntityCollection_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.AnatomicEntityCollection_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ledu/stanford/isis/epad/plugin/lesiontracking/shared/AnatomicEntityCollection;),
      ];
    
    result["edu.stanford.isis.epad.plugin.lesiontracking.shared.Calculation/3460049048"] = [
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.Calculation_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.Calculation_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ledu/stanford/isis/epad/plugin/lesiontracking/shared/Calculation;),
      ];
    
    result["edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationCollection/3236885292"] = [
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationCollection_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationCollection_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ledu/stanford/isis/epad/plugin/lesiontracking/shared/CalculationCollection;),
      ];
    
    result["edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationData/1567349957"] = [
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationData_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationData_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ledu/stanford/isis/epad/plugin/lesiontracking/shared/CalculationData;),
      ];
    
    result["edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationDataCollection/3336436284"] = [
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationDataCollection_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationDataCollection_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ledu/stanford/isis/epad/plugin/lesiontracking/shared/CalculationDataCollection;),
      ];
    
    result["edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationResult/4196447181"] = [
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationResult_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationResult_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ledu/stanford/isis/epad/plugin/lesiontracking/shared/CalculationResult;),
      ];
    
    result["edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationResultCollection/2687223782"] = [
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationResultCollection_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationResultCollection_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ledu/stanford/isis/epad/plugin/lesiontracking/shared/CalculationResultCollection;),
      ];
    
    result["edu.stanford.isis.epad.plugin.lesiontracking.shared.Data/1555976698"] = [
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.Data_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.Data_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ledu/stanford/isis/epad/plugin/lesiontracking/shared/Data;),
      ];
    
    result["edu.stanford.isis.epad.plugin.lesiontracking.shared.DataCollection/548779869"] = [
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.DataCollection_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.DataCollection_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ledu/stanford/isis/epad/plugin/lesiontracking/shared/DataCollection;),
      ];
    
    result["edu.stanford.isis.epad.plugin.lesiontracking.shared.GeometricShape/1738800861"] = [
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.GeometricShape_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.GeometricShape_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ledu/stanford/isis/epad/plugin/lesiontracking/shared/GeometricShape;),
      ];
    
    result["edu.stanford.isis.epad.plugin.lesiontracking.shared.GeometricShapeCollection/4227020699"] = [
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.GeometricShapeCollection_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.GeometricShapeCollection_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ledu/stanford/isis/epad/plugin/lesiontracking/shared/GeometricShapeCollection;),
      ];
    
    result["edu.stanford.isis.epad.plugin.lesiontracking.shared.Image/3943571302"] = [
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.Image_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.Image_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ledu/stanford/isis/epad/plugin/lesiontracking/shared/Image;),
      ];
    
    result["edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation/451469749"] = [
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ledu/stanford/isis/epad/plugin/lesiontracking/shared/ImageAnnotation;),
      ];
    
    result["[Ledu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation;/2679643088"] = [
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation_Array_Rank_1_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation_Array_Rank_1_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;[Ledu/stanford/isis/epad/plugin/lesiontracking/shared/ImageAnnotation;),
      ];
    
    result["edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageCollection/3730219046"] = [
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageCollection_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageCollection_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ledu/stanford/isis/epad/plugin/lesiontracking/shared/ImageCollection;),
      ];
    
    result["edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageReference/3396144851"] = [
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageReference_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageReference_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ledu/stanford/isis/epad/plugin/lesiontracking/shared/ImageReference;),
      ];
    
    result["edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageReferenceCollection/983311129"] = [
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageReferenceCollection_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageReferenceCollection_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ledu/stanford/isis/epad/plugin/lesiontracking/shared/ImageReferenceCollection;),
      ];
    
    result["edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageSeries/17402537"] = [
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageSeries_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageSeries_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ledu/stanford/isis/epad/plugin/lesiontracking/shared/ImageSeries;),
      ];
    
    result["edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageStudy/3103614696"] = [
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageStudy_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageStudy_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ledu/stanford/isis/epad/plugin/lesiontracking/shared/ImageStudy;),
      ];
    
    result["edu.stanford.isis.epad.plugin.lesiontracking.shared.Patient/4013093398"] = [
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.Patient_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.Patient_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ledu/stanford/isis/epad/plugin/lesiontracking/shared/Patient;),
      ];
    
    result["edu.stanford.isis.epad.plugin.lesiontracking.shared.Person/3540634780"] = [
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.Person_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.Person_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ledu/stanford/isis/epad/plugin/lesiontracking/shared/Person;),
      ];
    
    result["edu.stanford.isis.epad.plugin.lesiontracking.shared.Series/2464413187"] = [
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.Series_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.Series_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ledu/stanford/isis/epad/plugin/lesiontracking/shared/Series;),
      ];
    
    result["edu.stanford.isis.epad.plugin.lesiontracking.shared.SpatialCoordinate/1897180355"] = [
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.SpatialCoordinate_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.SpatialCoordinate_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ledu/stanford/isis/epad/plugin/lesiontracking/shared/SpatialCoordinate;),
      ];
    
    result["edu.stanford.isis.epad.plugin.lesiontracking.shared.SpatialCoordinateCollection/1175296378"] = [
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.SpatialCoordinateCollection_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.SpatialCoordinateCollection_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ledu/stanford/isis/epad/plugin/lesiontracking/shared/SpatialCoordinateCollection;),
      ];
    
    result["edu.stanford.isis.epad.plugin.lesiontracking.shared.Study/1535005029"] = [
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.Study_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @edu.stanford.isis.epad.plugin.lesiontracking.shared.Study_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ledu/stanford/isis/epad/plugin/lesiontracking/shared/Study;),
      ];
    
    result["java.io.IOException/1159940531"] = [
        @com.google.gwt.user.client.rpc.core.java.io.IOException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.io.IOException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/io/IOException;),
      ];
    
    result["java.io.UnsupportedEncodingException/1526756933"] = [
        @com.google.gwt.user.client.rpc.core.java.io.UnsupportedEncodingException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.io.UnsupportedEncodingException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/io/UnsupportedEncodingException;),
      ];
    
    result["java.lang.ArithmeticException/1539622151"] = [
        @com.google.gwt.user.client.rpc.core.java.lang.ArithmeticException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.lang.ArithmeticException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/lang/ArithmeticException;),
      ];
    
    result["java.lang.ArrayIndexOutOfBoundsException/600550433"] = [
        @com.google.gwt.user.client.rpc.core.java.lang.ArrayIndexOutOfBoundsException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.lang.ArrayIndexOutOfBoundsException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/lang/ArrayIndexOutOfBoundsException;),
      ];
    
    result["java.lang.ArrayStoreException/3540507190"] = [
        @com.google.gwt.user.client.rpc.core.java.lang.ArrayStoreException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.lang.ArrayStoreException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/lang/ArrayStoreException;),
      ];
    
    result["java.lang.AssertionError/3490171458"] = [
        @com.google.gwt.user.client.rpc.core.java.lang.AssertionError_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.lang.AssertionError_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/lang/AssertionError;),
      ];
    
    result["java.lang.Boolean/476441737"] = [
        @com.google.gwt.user.client.rpc.core.java.lang.Boolean_CustomFieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.lang.Boolean_CustomFieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/lang/Boolean;),
      ];
    
    result["java.lang.ClassCastException/702295179"] = [
        @com.google.gwt.user.client.rpc.core.java.lang.ClassCastException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.lang.ClassCastException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/lang/ClassCastException;),
      ];
    
    result["java.lang.Error/1331973429"] = [
        @com.google.gwt.user.client.rpc.core.java.lang.Error_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.lang.Error_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/lang/Error;),
      ];
    
    result["java.lang.Exception/1920171873"] = [
        @com.google.gwt.user.client.rpc.core.java.lang.Exception_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.lang.Exception_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/lang/Exception;),
        @com.google.gwt.user.client.rpc.core.java.lang.Exception_FieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Ljava/lang/Exception;)
      ];
    
    result["java.lang.IllegalArgumentException/1755012560"] = [
        @com.google.gwt.user.client.rpc.core.java.lang.IllegalArgumentException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.lang.IllegalArgumentException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/lang/IllegalArgumentException;),
      ];
    
    result["java.lang.IllegalStateException/1972187323"] = [
        @com.google.gwt.user.client.rpc.core.java.lang.IllegalStateException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.lang.IllegalStateException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/lang/IllegalStateException;),
      ];
    
    result["java.lang.IndexOutOfBoundsException/2489527753"] = [
        @com.google.gwt.user.client.rpc.core.java.lang.IndexOutOfBoundsException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.lang.IndexOutOfBoundsException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/lang/IndexOutOfBoundsException;),
      ];
    
    result["java.lang.NegativeArraySizeException/3846860241"] = [
        @com.google.gwt.user.client.rpc.core.java.lang.NegativeArraySizeException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.lang.NegativeArraySizeException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/lang/NegativeArraySizeException;),
      ];
    
    result["java.lang.NoSuchMethodException/260969707"] = [
        @com.google.gwt.user.client.rpc.core.java.lang.NoSuchMethodException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.lang.NoSuchMethodException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/lang/NoSuchMethodException;),
      ];
    
    result["java.lang.NullPointerException/1463492344"] = [
        @com.google.gwt.user.client.rpc.core.java.lang.NullPointerException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.lang.NullPointerException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/lang/NullPointerException;),
      ];
    
    result["java.lang.NumberFormatException/3305228476"] = [
        @com.google.gwt.user.client.rpc.core.java.lang.NumberFormatException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.lang.NumberFormatException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/lang/NumberFormatException;),
      ];
    
    result["java.lang.RuntimeException/515124647"] = [
        @com.google.gwt.user.client.rpc.core.java.lang.RuntimeException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.lang.RuntimeException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/lang/RuntimeException;),
        @com.google.gwt.user.client.rpc.core.java.lang.RuntimeException_FieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Ljava/lang/RuntimeException;)
      ];
    
    result["java.lang.String/2004016611"] = [
        @com.google.gwt.user.client.rpc.core.java.lang.String_CustomFieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.lang.String_CustomFieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/lang/String;),
        @com.google.gwt.user.client.rpc.core.java.lang.String_CustomFieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Ljava/lang/String;)
      ];
    
    result["java.lang.StringIndexOutOfBoundsException/500777603"] = [
        @com.google.gwt.user.client.rpc.core.java.lang.StringIndexOutOfBoundsException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.lang.StringIndexOutOfBoundsException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/lang/StringIndexOutOfBoundsException;),
      ];
    
    result["[Ljava.lang.String;/2600011424"] = [
        ,
        ,
        @com.google.gwt.user.client.rpc.core.java.lang.String_Array_Rank_1_FieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;[Ljava/lang/String;)
      ];
    
    result["[[Ljava.lang.String;/4182515373"] = [
        ,
        ,
        @com.google.gwt.user.client.rpc.core.java.lang.String_Array_Rank_2_FieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;[[Ljava/lang/String;)
      ];
    
    result["java.lang.Throwable/2953622131"] = [
        @com.google.gwt.user.client.rpc.core.java.lang.Throwable_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.lang.Throwable_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/lang/Throwable;),
        @com.google.gwt.user.client.rpc.core.java.lang.Throwable_FieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Ljava/lang/Throwable;)
      ];
    
    result["java.lang.UnsupportedOperationException/3744010015"] = [
        @com.google.gwt.user.client.rpc.core.java.lang.UnsupportedOperationException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.lang.UnsupportedOperationException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/lang/UnsupportedOperationException;),
      ];
    
    result["java.lang.annotation.AnnotationFormatError/2257184627"] = [
        @com.google.gwt.user.client.rpc.core.java.lang.annotation.AnnotationFormatError_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.lang.annotation.AnnotationFormatError_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/lang/annotation/AnnotationFormatError;),
      ];
    
    result["java.lang.annotation.AnnotationTypeMismatchException/976205828"] = [
        @com.google.gwt.user.client.rpc.core.java.lang.annotation.AnnotationTypeMismatchException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.lang.annotation.AnnotationTypeMismatchException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/lang/annotation/AnnotationTypeMismatchException;),
      ];
    
    result["java.security.DigestException/629316798"] = [
        @com.google.gwt.user.client.rpc.core.java.security.DigestException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.security.DigestException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/security/DigestException;),
      ];
    
    result["java.security.GeneralSecurityException/2669239907"] = [
        @com.google.gwt.user.client.rpc.core.java.security.GeneralSecurityException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.security.GeneralSecurityException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/security/GeneralSecurityException;),
      ];
    
    result["java.security.NoSuchAlgorithmException/2892037213"] = [
        @com.google.gwt.user.client.rpc.core.java.security.NoSuchAlgorithmException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.security.NoSuchAlgorithmException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/security/NoSuchAlgorithmException;),
      ];
    
    result["java.util.ArrayList/4159755760"] = [
        @com.google.gwt.user.client.rpc.core.java.util.ArrayList_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.util.ArrayList_CustomFieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/util/ArrayList;),
        @com.google.gwt.user.client.rpc.core.java.util.ArrayList_CustomFieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Ljava/util/ArrayList;)
      ];
    
    result["java.util.Arrays$ArrayList/2507071751"] = [
        @com.google.gwt.user.client.rpc.core.java.util.Arrays.ArrayList_CustomFieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.util.Arrays.ArrayList_CustomFieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/util/List;),
      ];
    
    result["java.util.Collections$EmptyList/4157118744"] = [
        @com.google.gwt.user.client.rpc.core.java.util.Collections.EmptyList_CustomFieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.util.Collections.EmptyList_CustomFieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/util/List;),
      ];
    
    result["java.util.Collections$EmptySet/3523698179"] = [
        @com.google.gwt.user.client.rpc.core.java.util.Collections.EmptySet_CustomFieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.util.Collections.EmptySet_CustomFieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/util/Set;),
      ];
    
    result["java.util.Collections$SingletonList/1586180994"] = [
        @com.google.gwt.user.client.rpc.core.java.util.Collections.SingletonList_CustomFieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.util.Collections.SingletonList_CustomFieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/util/List;),
      ];
    
    result["java.util.ConcurrentModificationException/2717383897"] = [
        @com.google.gwt.user.client.rpc.core.java.util.ConcurrentModificationException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.util.ConcurrentModificationException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/util/ConcurrentModificationException;),
      ];
    
    result["java.util.EmptyStackException/89438517"] = [
        @com.google.gwt.user.client.rpc.core.java.util.EmptyStackException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.util.EmptyStackException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/util/EmptyStackException;),
      ];
    
    result["java.util.HashMap/1797211028"] = [
        ,
        ,
        @com.google.gwt.user.client.rpc.core.java.util.HashMap_CustomFieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Ljava/util/HashMap;)
      ];
    
    result["java.util.HashSet/3273092938"] = [
        @com.google.gwt.user.client.rpc.core.java.util.HashSet_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.util.HashSet_CustomFieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/util/HashSet;),
      ];
    
    result["java.util.LinkedHashMap/3008245022"] = [
        ,
        ,
        @com.google.gwt.user.client.rpc.core.java.util.LinkedHashMap_CustomFieldSerializer::serialize(Lcom/google/gwt/user/client/rpc/SerializationStreamWriter;Ljava/util/LinkedHashMap;)
      ];
    
    result["java.util.LinkedHashSet/1826081506"] = [
        @com.google.gwt.user.client.rpc.core.java.util.LinkedHashSet_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.util.LinkedHashSet_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/util/LinkedHashSet;),
      ];
    
    result["java.util.LinkedList/3953877921"] = [
        @com.google.gwt.user.client.rpc.core.java.util.LinkedList_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.util.LinkedList_CustomFieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/util/LinkedList;),
      ];
    
    result["java.util.NoSuchElementException/1559248883"] = [
        @com.google.gwt.user.client.rpc.core.java.util.NoSuchElementException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.util.NoSuchElementException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/util/NoSuchElementException;),
      ];
    
    result["java.util.Stack/1346942793"] = [
        @com.google.gwt.user.client.rpc.core.java.util.Stack_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.util.Stack_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/util/Stack;),
      ];
    
    result["java.util.TooManyListenersException/2023078032"] = [
        @com.google.gwt.user.client.rpc.core.java.util.TooManyListenersException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.util.TooManyListenersException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/util/TooManyListenersException;),
      ];
    
    result["java.util.TreeMap/1493889780"] = [
        @com.google.gwt.user.client.rpc.core.java.util.TreeMap_CustomFieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.util.TreeMap_CustomFieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/util/TreeMap;),
      ];
    
    result["java.util.TreeSet/4043497002"] = [
        @com.google.gwt.user.client.rpc.core.java.util.TreeSet_CustomFieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.util.TreeSet_CustomFieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/util/TreeSet;),
      ];
    
    result["java.util.Vector/3057315478"] = [
        @com.google.gwt.user.client.rpc.core.java.util.Vector_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.java.util.Vector_CustomFieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljava/util/Vector;),
      ];
    
    result["javax.validation.ConstraintDeclarationException/3610544007"] = [
        @com.google.gwt.user.client.rpc.core.javax.validation.ConstraintDeclarationException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.javax.validation.ConstraintDeclarationException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljavax/validation/ConstraintDeclarationException;),
      ];
    
    result["javax.validation.ConstraintDefinitionException/3732439488"] = [
        @com.google.gwt.user.client.rpc.core.javax.validation.ConstraintDefinitionException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.javax.validation.ConstraintDefinitionException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljavax/validation/ConstraintDefinitionException;),
      ];
    
    result["javax.validation.ConstraintViolationException/1185386591"] = [
        @javax.validation.ConstraintViolationException_CustomFieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @javax.validation.ConstraintViolationException_CustomFieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljavax/validation/ConstraintViolationException;),
      ];
    
    result["javax.validation.GroupDefinitionException/4024780846"] = [
        @com.google.gwt.user.client.rpc.core.javax.validation.GroupDefinitionException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.javax.validation.GroupDefinitionException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljavax/validation/GroupDefinitionException;),
      ];
    
    result["javax.validation.UnexpectedTypeException/593026390"] = [
        @com.google.gwt.user.client.rpc.core.javax.validation.UnexpectedTypeException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.javax.validation.UnexpectedTypeException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljavax/validation/UnexpectedTypeException;),
      ];
    
    result["javax.validation.ValidationException/1570221831"] = [
        @com.google.gwt.user.client.rpc.core.javax.validation.ValidationException_FieldSerializer::instantiate(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;),
        @com.google.gwt.user.client.rpc.core.javax.validation.ValidationException_FieldSerializer::deserialize(Lcom/google/gwt/user/client/rpc/SerializationStreamReader;Ljavax/validation/ValidationException;),
      ];
    
    return result;
  }-*/;
  
  @SuppressWarnings("deprecation")
  @GwtScriptOnly
  private static native JsArrayString loadSignaturesNative() /*-{
    var result = [];
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@com.google.gwt.ajaxloader.client.Properties.TypeException::class)] = "com.google.gwt.ajaxloader.client.Properties$TypeException/806639172";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@com.google.gwt.event.shared.UmbrellaException::class)] = "com.google.gwt.event.shared.UmbrellaException/3104463596";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@com.google.gwt.http.client.RequestException::class)] = "com.google.gwt.http.client.RequestException/190587325";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@com.google.gwt.json.client.JSONException::class)] = "com.google.gwt.json.client.JSONException/2941795468";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@com.google.gwt.jsonp.client.TimeoutException::class)] = "com.google.gwt.jsonp.client.TimeoutException/1112787596";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException::class)] = "com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException/3936916533";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@com.google.gwt.user.client.rpc.RpcTokenException::class)] = "com.google.gwt.user.client.rpc.RpcTokenException/2345075298";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@com.google.gwt.user.client.rpc.SerializableException::class)] = "com.google.gwt.user.client.rpc.SerializableException/3047383460";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@com.google.gwt.user.client.rpc.SerializationException::class)] = "com.google.gwt.user.client.rpc.SerializationException/2836333220";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@com.google.gwt.user.client.rpc.SerializedTypeViolationException::class)] = "com.google.gwt.user.client.rpc.SerializedTypeViolationException/914601580";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@com.google.gwt.user.client.rpc.ServiceDefTarget.NoServiceEntryPointSpecifiedException::class)] = "com.google.gwt.user.client.rpc.ServiceDefTarget$NoServiceEntryPointSpecifiedException/3408313447";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@com.google.gwt.user.client.rpc.XsrfToken::class)] = "com.google.gwt.user.client.rpc.XsrfToken/4254043109";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@com.google.gwt.user.client.ui.ChangeListenerCollection::class)] = "com.google.gwt.user.client.ui.ChangeListenerCollection/287642957";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@com.google.gwt.user.client.ui.ClickListenerCollection::class)] = "com.google.gwt.user.client.ui.ClickListenerCollection/2152455986";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@com.google.gwt.user.client.ui.FocusListenerCollection::class)] = "com.google.gwt.user.client.ui.FocusListenerCollection/119490835";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@com.google.gwt.user.client.ui.KeyboardListenerCollection::class)] = "com.google.gwt.user.client.ui.KeyboardListenerCollection/1040442242";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@com.google.gwt.xml.client.impl.DOMParseException::class)] = "com.google.gwt.xml.client.impl.DOMParseException/3799120635";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@com.google.web.bindery.event.shared.UmbrellaException::class)] = "com.google.web.bindery.event.shared.UmbrellaException/1025846929";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult::class)] = "edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult/2956891268";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion::class)] = "edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion/1448412761";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion[]::class)] = "[Ledu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion;/485178529";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMAttribute::class)] = "edu.stanford.isis.epad.plugin.lesiontracking.shared.AIMAttribute/3290996244";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@edu.stanford.isis.epad.plugin.lesiontracking.shared.AnatomicEntity::class)] = "edu.stanford.isis.epad.plugin.lesiontracking.shared.AnatomicEntity/4198021387";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@edu.stanford.isis.epad.plugin.lesiontracking.shared.AnatomicEntityCollection::class)] = "edu.stanford.isis.epad.plugin.lesiontracking.shared.AnatomicEntityCollection/4026145024";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@edu.stanford.isis.epad.plugin.lesiontracking.shared.Calculation::class)] = "edu.stanford.isis.epad.plugin.lesiontracking.shared.Calculation/3460049048";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationCollection::class)] = "edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationCollection/3236885292";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationData::class)] = "edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationData/1567349957";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationDataCollection::class)] = "edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationDataCollection/3336436284";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationResult::class)] = "edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationResult/4196447181";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationResultCollection::class)] = "edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationResultCollection/2687223782";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@edu.stanford.isis.epad.plugin.lesiontracking.shared.Data::class)] = "edu.stanford.isis.epad.plugin.lesiontracking.shared.Data/1555976698";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@edu.stanford.isis.epad.plugin.lesiontracking.shared.DataCollection::class)] = "edu.stanford.isis.epad.plugin.lesiontracking.shared.DataCollection/548779869";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@edu.stanford.isis.epad.plugin.lesiontracking.shared.GeometricShape::class)] = "edu.stanford.isis.epad.plugin.lesiontracking.shared.GeometricShape/1738800861";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@edu.stanford.isis.epad.plugin.lesiontracking.shared.GeometricShapeCollection::class)] = "edu.stanford.isis.epad.plugin.lesiontracking.shared.GeometricShapeCollection/4227020699";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@edu.stanford.isis.epad.plugin.lesiontracking.shared.Image::class)] = "edu.stanford.isis.epad.plugin.lesiontracking.shared.Image/3943571302";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation::class)] = "edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation/451469749";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation[]::class)] = "[Ledu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation;/2679643088";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageCollection::class)] = "edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageCollection/3730219046";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageReference::class)] = "edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageReference/3396144851";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageReferenceCollection::class)] = "edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageReferenceCollection/983311129";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageSeries::class)] = "edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageSeries/17402537";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageStudy::class)] = "edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageStudy/3103614696";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@edu.stanford.isis.epad.plugin.lesiontracking.shared.Patient::class)] = "edu.stanford.isis.epad.plugin.lesiontracking.shared.Patient/4013093398";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@edu.stanford.isis.epad.plugin.lesiontracking.shared.Person::class)] = "edu.stanford.isis.epad.plugin.lesiontracking.shared.Person/3540634780";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@edu.stanford.isis.epad.plugin.lesiontracking.shared.Series::class)] = "edu.stanford.isis.epad.plugin.lesiontracking.shared.Series/2464413187";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@edu.stanford.isis.epad.plugin.lesiontracking.shared.SpatialCoordinate::class)] = "edu.stanford.isis.epad.plugin.lesiontracking.shared.SpatialCoordinate/1897180355";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@edu.stanford.isis.epad.plugin.lesiontracking.shared.SpatialCoordinateCollection::class)] = "edu.stanford.isis.epad.plugin.lesiontracking.shared.SpatialCoordinateCollection/1175296378";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@edu.stanford.isis.epad.plugin.lesiontracking.shared.Study::class)] = "edu.stanford.isis.epad.plugin.lesiontracking.shared.Study/1535005029";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.io.IOException::class)] = "java.io.IOException/1159940531";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.io.UnsupportedEncodingException::class)] = "java.io.UnsupportedEncodingException/1526756933";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.lang.ArithmeticException::class)] = "java.lang.ArithmeticException/1539622151";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.lang.ArrayIndexOutOfBoundsException::class)] = "java.lang.ArrayIndexOutOfBoundsException/600550433";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.lang.ArrayStoreException::class)] = "java.lang.ArrayStoreException/3540507190";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.lang.AssertionError::class)] = "java.lang.AssertionError/3490171458";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.lang.Boolean::class)] = "java.lang.Boolean/476441737";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.lang.ClassCastException::class)] = "java.lang.ClassCastException/702295179";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.lang.Error::class)] = "java.lang.Error/1331973429";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.lang.Exception::class)] = "java.lang.Exception/1920171873";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.lang.IllegalArgumentException::class)] = "java.lang.IllegalArgumentException/1755012560";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.lang.IllegalStateException::class)] = "java.lang.IllegalStateException/1972187323";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.lang.IndexOutOfBoundsException::class)] = "java.lang.IndexOutOfBoundsException/2489527753";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.lang.NegativeArraySizeException::class)] = "java.lang.NegativeArraySizeException/3846860241";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.lang.NoSuchMethodException::class)] = "java.lang.NoSuchMethodException/260969707";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.lang.NullPointerException::class)] = "java.lang.NullPointerException/1463492344";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.lang.NumberFormatException::class)] = "java.lang.NumberFormatException/3305228476";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.lang.RuntimeException::class)] = "java.lang.RuntimeException/515124647";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.lang.String::class)] = "java.lang.String/2004016611";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.lang.StringIndexOutOfBoundsException::class)] = "java.lang.StringIndexOutOfBoundsException/500777603";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.lang.String[]::class)] = "[Ljava.lang.String;/2600011424";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.lang.String[][]::class)] = "[[Ljava.lang.String;/4182515373";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.lang.Throwable::class)] = "java.lang.Throwable/2953622131";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.lang.UnsupportedOperationException::class)] = "java.lang.UnsupportedOperationException/3744010015";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.lang.annotation.AnnotationFormatError::class)] = "java.lang.annotation.AnnotationFormatError/2257184627";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.lang.annotation.AnnotationTypeMismatchException::class)] = "java.lang.annotation.AnnotationTypeMismatchException/976205828";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.security.DigestException::class)] = "java.security.DigestException/629316798";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.security.GeneralSecurityException::class)] = "java.security.GeneralSecurityException/2669239907";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.security.NoSuchAlgorithmException::class)] = "java.security.NoSuchAlgorithmException/2892037213";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.util.ArrayList::class)] = "java.util.ArrayList/4159755760";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.util.Arrays.ArrayList::class)] = "java.util.Arrays$ArrayList/2507071751";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.util.Collections.EmptyList::class)] = "java.util.Collections$EmptyList/4157118744";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.util.Collections.EmptySet::class)] = "java.util.Collections$EmptySet/3523698179";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.util.Collections.SingletonList::class)] = "java.util.Collections$SingletonList/1586180994";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.util.ConcurrentModificationException::class)] = "java.util.ConcurrentModificationException/2717383897";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.util.EmptyStackException::class)] = "java.util.EmptyStackException/89438517";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.util.HashMap::class)] = "java.util.HashMap/1797211028";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.util.HashSet::class)] = "java.util.HashSet/3273092938";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.util.LinkedHashMap::class)] = "java.util.LinkedHashMap/3008245022";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.util.LinkedHashSet::class)] = "java.util.LinkedHashSet/1826081506";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.util.LinkedList::class)] = "java.util.LinkedList/3953877921";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.util.NoSuchElementException::class)] = "java.util.NoSuchElementException/1559248883";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.util.Stack::class)] = "java.util.Stack/1346942793";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.util.TooManyListenersException::class)] = "java.util.TooManyListenersException/2023078032";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.util.TreeMap::class)] = "java.util.TreeMap/1493889780";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.util.TreeSet::class)] = "java.util.TreeSet/4043497002";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@java.util.Vector::class)] = "java.util.Vector/3057315478";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@javax.validation.ConstraintDeclarationException::class)] = "javax.validation.ConstraintDeclarationException/3610544007";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@javax.validation.ConstraintDefinitionException::class)] = "javax.validation.ConstraintDefinitionException/3732439488";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@javax.validation.ConstraintViolationException::class)] = "javax.validation.ConstraintViolationException/1185386591";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@javax.validation.GroupDefinitionException::class)] = "javax.validation.GroupDefinitionException/4024780846";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@javax.validation.UnexpectedTypeException::class)] = "javax.validation.UnexpectedTypeException/593026390";
    result[@com.google.gwt.core.client.impl.Impl::getHashCode(Ljava/lang/Object;)(@javax.validation.ValidationException::class)] = "javax.validation.ValidationException/1570221831";
    return result;
  }-*/;
  
  public LesionTrackingService_TypeSerializer() {
    super(null, methodMapNative, null, signatureMapNative);
  }
  
}
