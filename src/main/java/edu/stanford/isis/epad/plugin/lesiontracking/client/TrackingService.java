package edu.stanford.isis.epad.plugin.lesiontracking.client;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import edu.stanford.hakan.aim4api.compability.aimv3.ImageAnnotation;
import edu.stanford.hakan.aim4api.project.epad.Aim;


@RemoteServiceRelativePath("trackingService")
public interface TrackingService extends RemoteService {

	public String getPatientNames(String projectID, String username,
			String session, String server) throws Exception;

	public  List<List<String>> getMetricsAndANamesForPatient(
			String projectID, String patientID, String username,
			String session, String server, Boolean isNonTarget) throws Exception;
	
	public  Map<Date, List<ImageAnnotation>> getImageAnnotationsForPatient(
			String projectID, String patientID, String username,
			String session, String server, Boolean isNonTarget) throws Exception;
	
	public String getRECISTHTML(
			String projectID, String patientID, String username,
			String server, String session, String metric) throws Exception;
	
	public String setClient(String username);

}