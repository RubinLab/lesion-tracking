package edu.stanford.isis.epad.plugin.lesiontracking.client;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.stanford.hakan.aim4api.project.epad.Aim;

public interface TrackingServiceAsync {

	public void getPatientNames(String projectID, String username,
			String session, String server, AsyncCallback<String> asyncCallback);

	
	
	void getMetricsAndANamesForPatient(String projectID, String patientID,
			String username, String session, String server,  Boolean isNonTarget,
			AsyncCallback<List<List<String>>> asyncCallback);
	
	void getImageAnnotationsForPatient(String projectID, String patientID,
			String username, String session, String server,  Boolean isNonTarget,
			AsyncCallback<Map<Date, List<Aim>>> asyncCallback);

	
	void getRECISTHTML(String projectID, String patientID, String username,
			String server, String session, String metric,
			AsyncCallback<String> callback);

	public void setClient(String username, AsyncCallback<String> asyncCallback);
}