package edu.stanford.isis.epad.plugin.lesiontracking.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation;

@RemoteServiceRelativePath("lesionTrackingService")
public interface LesionTrackingService extends RemoteService
{
    public String getPatientNames() throws Exception;
    public List<ImageAnnotation> getImageAnnotationsForPatient(String patientName) throws Exception;
    public String downloadRECISTTableImage(CalculationResult cr);
    public String downloadRECISTChartImage(CalculationResult cr);
	public String setClient(String username, String session);
	public String requestSessionString(ArrayList<String> login);
}