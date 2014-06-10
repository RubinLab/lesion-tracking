package edu.stanford.isis.epad.plugin.lesiontracking.client;

import java.util.List;

import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation;

public interface LesionTrackingServiceAsync
{
//    public void setClient(String session, AsyncCallback<String> asyncCallback);
	public void getPatientNames(AsyncCallback<String> asyncCallback);
    public void getImageAnnotationsForPatient(String patientName, AsyncCallback<List<ImageAnnotation>> asyncCallback);
    public void downloadRECISTTableImage(CalculationResult cr, AsyncCallback<String> asyncCallback);
    public void downloadRECISTChartImage(CalculationResult cr, AsyncCallback<String> asyncCallback);
}