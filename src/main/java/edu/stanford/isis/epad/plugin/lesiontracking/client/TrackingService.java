package edu.stanford.isis.epad.plugin.lesiontracking.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation;

@RemoteServiceRelativePath("trackingService")
public interface TrackingService extends RemoteService {

	public String getPatientNames(String projectID, String username,
			String session, String server) throws Exception;

	public List<ImageAnnotation> getImageAnnotationsForPatient(
			String projectID, String patientID, String username,
			String session, String server) throws Exception;

	public String downloadRECISTTableImage(CalculationResult cr);

	public String downloadRECISTChartImage(CalculationResult cr);

	public String setClient(String username);

	public String requestSessionString(ArrayList<String> login);

}