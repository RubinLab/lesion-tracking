package edu.stanford.isis.epad.plugin.lesiontracking.client;

import com.google.gwt.user.client.rpc.impl.RemoteServiceProxy;
import com.google.gwt.user.client.rpc.impl.ClientSerializationStreamWriter;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.impl.RequestCallbackAdapter.ResponseReader;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.RpcToken;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.core.client.impl.Impl;
import com.google.gwt.user.client.rpc.impl.RpcStatsContext;

public class LesionTrackingService_Proxy extends RemoteServiceProxy implements edu.stanford.isis.epad.plugin.lesiontracking.client.LesionTrackingServiceAsync {
  private static final String REMOTE_SERVICE_INTERFACE_NAME = "edu.stanford.isis.epad.plugin.lesiontracking.client.LesionTrackingService";
  private static final String SERIALIZATION_POLICY ="C71B281C0CC978B2BCDFB9ED6BE1552B";
  private static final edu.stanford.isis.epad.plugin.lesiontracking.client.LesionTrackingService_TypeSerializer SERIALIZER = new edu.stanford.isis.epad.plugin.lesiontracking.client.LesionTrackingService_TypeSerializer();
  
  public LesionTrackingService_Proxy() {
    super(GWT.getModuleBaseURL(),
      "lesionTrackingService", 
      SERIALIZATION_POLICY, 
      SERIALIZER);
  }
  
  public void downloadRECISTChartImage(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult cr, com.google.gwt.user.client.rpc.AsyncCallback asyncCallback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("LesionTrackingService_Proxy", "downloadRECISTChartImage");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 1);
      streamWriter.writeString("edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult/2956891268");
      streamWriter.writeObject(cr);
      helper.finish(asyncCallback, ResponseReader.STRING);
    } catch (SerializationException ex) {
      asyncCallback.onFailure(ex);
    }
  }
  
  public void downloadRECISTTableImage(edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult cr, com.google.gwt.user.client.rpc.AsyncCallback asyncCallback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("LesionTrackingService_Proxy", "downloadRECISTTableImage");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 1);
      streamWriter.writeString("edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult/2956891268");
      streamWriter.writeObject(cr);
      helper.finish(asyncCallback, ResponseReader.STRING);
    } catch (SerializationException ex) {
      asyncCallback.onFailure(ex);
    }
  }
  
  public void getImageAnnotationsForPatient(java.lang.String patientName, com.google.gwt.user.client.rpc.AsyncCallback asyncCallback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("LesionTrackingService_Proxy", "getImageAnnotationsForPatient");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 1);
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString(patientName);
      helper.finish(asyncCallback, ResponseReader.OBJECT);
    } catch (SerializationException ex) {
      asyncCallback.onFailure(ex);
    }
  }
  
  public void getPatientNames(com.google.gwt.user.client.rpc.AsyncCallback asyncCallback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("LesionTrackingService_Proxy", "getPatientNames");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 0);
      helper.finish(asyncCallback, ResponseReader.STRING);
    } catch (SerializationException ex) {
      asyncCallback.onFailure(ex);
    }
  }
  
  public void requestSessionString(com.google.gwt.user.client.rpc.AsyncCallback asyncCallback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("LesionTrackingService_Proxy", "requestSessionString");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 0);
      helper.finish(asyncCallback, ResponseReader.STRING);
    } catch (SerializationException ex) {
      asyncCallback.onFailure(ex);
    }
  }
  
  public void setClient(java.lang.String username, java.lang.String session, com.google.gwt.user.client.rpc.AsyncCallback asyncCallback) {
    com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper helper = new com.google.gwt.user.client.rpc.impl.RemoteServiceProxy.ServiceHelper("LesionTrackingService_Proxy", "setClient");
    try {
      SerializationStreamWriter streamWriter = helper.start(REMOTE_SERVICE_INTERFACE_NAME, 2);
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString("java.lang.String/2004016611");
      streamWriter.writeString(username);
      streamWriter.writeString(session);
      helper.finish(asyncCallback, ResponseReader.STRING);
    } catch (SerializationException ex) {
      asyncCallback.onFailure(ex);
    }
  }
  @Override
  public SerializationStreamWriter createStreamWriter() {
    ClientSerializationStreamWriter toReturn =
      (ClientSerializationStreamWriter) super.createStreamWriter();
    if (getRpcToken() != null) {
      toReturn.addFlags(ClientSerializationStreamWriter.FLAG_RPC_TOKEN_INCLUDED);
    }
    return toReturn;
  }
  @Override
  protected void checkRpcTokenType(RpcToken token) {
    if (!(token instanceof com.google.gwt.user.client.rpc.XsrfToken)) {
      throw new RpcTokenException("Invalid RpcToken type: expected 'com.google.gwt.user.client.rpc.XsrfToken' but got '" + token.getClass() + "'");
    }
  }
}
