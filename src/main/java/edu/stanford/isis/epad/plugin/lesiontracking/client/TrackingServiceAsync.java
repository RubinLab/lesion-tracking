package edu.stanford.isis.epad.plugin.lesiontracking.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation;

public interface TrackingServiceAsync {

	public void getPatientNames(String projectID, String username,
			String session, String server, AsyncCallback<String> asyncCallback);

	public void getImageAnnotationsForPatient(String projectID,
			String patientID, String username, String session, String server,
			AsyncCallback<List<ImageAnnotation>> asyncCallback);

	public void downloadRECISTTableImage(CalculationResult cr,
			AsyncCallback<String> asyncCallback);

	public void downloadRECISTChartImage(CalculationResult cr,
			AsyncCallback<String> asyncCallback);

	public void setClient(String username, AsyncCallback<String> asyncCallback);

	public void requestSessionString(ArrayList<String> login,
			AsyncCallback<String> asyncCallback);
}