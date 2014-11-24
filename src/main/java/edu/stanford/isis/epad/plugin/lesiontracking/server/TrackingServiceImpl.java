package edu.stanford.isis.epad.plugin.lesiontracking.server;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibm.icu.text.DecimalFormat;

import edu.stanford.isis.epad.plugin.lesiontracking.client.TrackingService;
import edu.stanford.isis.epad.plugin.lesiontracking.server.TumorAnalysisCalculator.CalculationResult;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImagingObservation;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImagingObservationCharacteristicCollection;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImagingObservationCollection;

public class TrackingServiceImpl extends RemoteServiceServlet implements
		TrackingService {

	private static final String	TARGET_LESIONS = "Target Lesion Present Lesion",
			 			 		NON_TARGET_LESIONS = "Non-Target";
	private static final DecimalFormat df = new DecimalFormat();
	private static final DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	static
	{
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
	}
	private static final long serialVersionUID = 1830906991196368571L;
	public static final String LIST = "list", VALUES = "value",
			PERSON = "Person", IMAGE_ANNOTATION = "ImageAnnotation";
	private DefaultHttpClient client;

	static final TempLogger logger = TempLogger.getInstance();

	@Override
	public String setClient(String username) {
		this.client = new DefaultHttpClient();
		return "success";
	}

	@Override
	public String getPatientNames(String projectID, String username,
			String session, String server) throws Exception {

		String request = server + "/epad/v2/projects/" + projectID + "/subjects/?username=" + username;

		logger.info("getPatientNames " + projectID + " " + username + " " + session + " " + server);
		logger.info("getPatientNames request " + request);

		client = new DefaultHttpClient();

		setSessionCookie(server, client, session);

		HttpGet get = new HttpGet(request);

		get.addHeader("accept", "application/json");
		Header[] headers = get.getAllHeaders();
		for (int i = 0; i < headers.length; i++) logger.info("Header: " + headers[i]);

		List<Cookie> cookies = client.getCookieStore().getCookies();
		for (int i = 0; i < cookies.size(); i++)
		{
			logger.info("Cookie: " + cookies.get(i).getName() + " "
					+ cookies.get(i).getPath() + " "
					+ cookies.get(i).getValue() + " " +
					  cookies.get(i).getDomain());
		}

		HttpResponse response;
		String result = "";
		try {
			// make the call
			response = client.execute(get);
			if (response != null) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream instream = entity.getContent();

					BufferedReader rd = new BufferedReader(
							new InputStreamReader(response.getEntity()
									.getContent()));
					String line;
					while ((line = rd.readLine()) != null) {
						result = line;
					}
					instream.close();
				}
				logger.info("Result Patient Name: " + result);
			}else {
				logger.info("Result Patient Name: Empty");
			}

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
 
		logger.info("result" + result);
		return result;
	}

	public Map<Date, List<ImageAnnotation>> getImageAnnotationsForPatient(
			String projectID, String patientID, String username,
			String session, String server, Boolean isNonTarget) throws Exception {

		logger.info("Calling new getImageAnnotationsForPatient");
		logger.info("getImageAnnotationsForPatient " + projectID + " " + username + " " + session + " " + server);
		
		String request = server + "/epad/v2/projects/" + projectID + "/subjects/" + patientID + "/aims/?username=" + username;
		logger.info("getImageAnnotationsForPatient request " + request);

		HttpGet get = new HttpGet(request);

		get.addHeader("accept", "application/xml");
		HttpResponse response;
		String result = "";
		try {
			// make the call
			response = client.execute(get);
			System.out.println("RESPONSE: " + response);
			if (response != null) {

				// reflect the status line
				// StatusLine statusLine = response.getStatusLine();

				HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream instream = entity.getContent();

					BufferedReader rd = new BufferedReader(
							new InputStreamReader(response.getEntity()
									.getContent()));
					String line;
					while ((line = rd.readLine()) != null) {
						result += line;
					}
					instream.close();
				}
				System.out.println("Result AIM: " + result);

			} else {
				System.out.println("Result AIM: Empty");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db
				.parse(new ByteArrayInputStream(result.getBytes()));
		Element element = document.getDocumentElement();
		NodeList nodeList = element.getChildNodes();

		List<ImageAnnotation> imageAnnotations = new ArrayList<ImageAnnotation>();

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node instanceof Element) {
				element = (Element) nodeList.item(i);
				String nodeName = element.getNodeName();

				if ("ImageAnnotation".equals(nodeName)) {
					imageAnnotations.add(AIMFileReader
							.parseImageAnnotationFromNode(element, ""));
				}
			}
		}
		
		Map<Date, List<ImageAnnotation>> targetImageAnnotationsByStudyDate = new HashMap<Date, List<ImageAnnotation>>();

		for (ImageAnnotation imageAnnotation : imageAnnotations)
		{
			Date studyDate;
			try
			{
				studyDate = ImageAnnotationUtility.getStudyDate(imageAnnotation.getImageReferenceCollection(0));
				
			}
			catch(ParseException parseException)
			{
				studyDate = new Date(0l);
			}
			
			if(!targetImageAnnotationsByStudyDate.containsKey(studyDate))
				targetImageAnnotationsByStudyDate.put(studyDate, new ArrayList<ImageAnnotation>());

			String targetLesionFlag = null;
			if(imageAnnotation.getNumberOfImagingObservationCollections() > 0)
			{
				ImagingObservationCollection imagingObservationCollection = imageAnnotation.getImagingObservationCollection(0);
				
				if(imagingObservationCollection.getNumberOfImagingObservations() > 0)
				{
					ImagingObservation imagingObservation = imagingObservationCollection.getImagingObservation(0);
					
					if(imagingObservation.getNumberOfImagingObservationCharacteristicCollections() > 0)
					{
						ImagingObservationCharacteristicCollection imagingObservationCharacteristicCollection = imagingObservation.getImagingObservationCharacteristicCollection(0);
						
						if(imagingObservationCharacteristicCollection.getNumberOfImagingObservationCharacteristics() > 0)
						{
							targetLesionFlag = imagingObservationCharacteristicCollection.getImagingObservationCharacteristic(0).getCodeMeaning();
						}
					}
				}
			}
			
			System.out.println(targetLesionFlag);
			
			if(isNonTarget == null)
				targetImageAnnotationsByStudyDate.get(studyDate).add(imageAnnotation);
			else
			{
				String targetString = targetLesionFlag.toLowerCase();
				if(isNonTarget && targetString.contains("non-target"))
					targetImageAnnotationsByStudyDate.get(studyDate).add(imageAnnotation);
				else if(!isNonTarget && !targetString.contains("non-target") && targetString.contains("target"))
					targetImageAnnotationsByStudyDate.get(studyDate).add(imageAnnotation);
			}
		}

		return targetImageAnnotationsByStudyDate;
	}

	public String renderDocument(String patientName, String physicianName, Date date, CalculationResult targetCalculationResult, CalculationResult nonTargetCalculationResult)
	{
        try {

        logger.info("Loading Velocity engine.");
        VelocityEngine ve = new VelocityEngine();
        ve.init();
        
        logger.info("Loading Velocity template.");
        StringWriter writer = new StringWriter();
        IOUtils.copy(TrackingServiceImpl.class.getResourceAsStream("/META-INF/resources/RECISTVelocityTemplate.vm"), writer, "UTF-8");
        String theString = writer.toString();
        
        PrintWriter out = new PrintWriter("RECISTVelocityTemplate.vm");
        out.println(theString);
        out.close();
        Template t = ve.getTemplate("RECISTVelocityTemplate.vm");
            
        logger.info("Setting up Velocity context.");
        VelocityContext context = new VelocityContext();
        context.put("df", df); // Decimal formatter.
        context.put("sdf", sdf); // Date formatter.
        context.put("TARGET_LESIONS", TARGET_LESIONS);
        context.put("NON_TARGET_LESIONS", NON_TARGET_LESIONS);
        
        context.put("patient_name", patientName);
        context.put("physician_name", physicianName);
        context.put("date", date);
        context.put("imageAnnotationsByNameAndStudyDate", targetCalculationResult.getImageAnnotationsByNameAndStudyDate());
        context.put("metricValuesByImageAnnotation", targetCalculationResult.getMetricValuesByImageAnnotation());
        context.put("studyDates", targetCalculationResult.getSortedStudyDates());
        context.put("sortedImageAnnotationNames", targetCalculationResult.getSortedImageAnnotationNames());
        context.put("anatomicEntityNamesByImageAnnotationName", targetCalculationResult.getAnatomicEntityNamesByImageAnnotationName());
        context.put("metricValuesByImageAnnotation", targetCalculationResult.getMetricValuesByImageAnnotation());
        context.put("metricSumsByStudyDate", targetCalculationResult.getMetricSumsByStudyDate());
        context.put("anatomicEntitiesByImageAnnotation", targetCalculationResult.getAnatomicEntitiesByImageAnnotation());
        context.put("units", targetCalculationResult.getUnitOfMeasure());
        context.put("responseRatesByStudyDate", targetCalculationResult.getResponseRatesByStudyDate());
        context.put("cr", targetCalculationResult);
        
        context.put("nt_imageAnnotationsByNameAndStudyDate", nonTargetCalculationResult.getImageAnnotationsByNameAndStudyDate());
        context.put("nt_metricValuesByImageAnnotation", nonTargetCalculationResult.getMetricValuesByImageAnnotation());
        context.put("nt_studyDates", nonTargetCalculationResult.getSortedStudyDates());
        context.put("nt_sortedImageAnnotationNames", nonTargetCalculationResult.getSortedImageAnnotationNames());
        context.put("nt_anatomicEntityNamesByImageAnnotationName", nonTargetCalculationResult.getAnatomicEntityNamesByImageAnnotationName());
        context.put("nt_metricValuesByImageAnnotation", nonTargetCalculationResult.getMetricValuesByImageAnnotation());
        context.put("nt_metricSumsByStudyDate", nonTargetCalculationResult.getMetricSumsByStudyDate());
        context.put("nt_anatomicEntitiesByImageAnnotation", nonTargetCalculationResult.getAnatomicEntitiesByImageAnnotation());
        context.put("nt_units", nonTargetCalculationResult.getUnitOfMeasure());
        context.put("nt_responseRatesByStudyDate", nonTargetCalculationResult.getResponseRatesByStudyDate());
        context.put("nt_cr", targetCalculationResult);
        
        
        writer = new StringWriter();
        t.merge( context, writer );

		try{
			logger.info("Writing recist_out.doc");
			
			try
			{
				ServletContext servletContext = getServletContext();
		        PrintWriter printWriter = new PrintWriter(servletContext.getRealPath("/") + "/recist_out.doc", "UTF-8");
		        printWriter.println(writer.toString());
		        printWriter.close();
			}
			catch(NullPointerException npe)
			{
		        PrintWriter printWriter = new PrintWriter("recist_out.doc", "UTF-8");
		        printWriter.println(writer.toString());
		        printWriter.close();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

        return writer.toString();
		} catch (Exception e) {
			logger.info(ExceptionUtils.getStackTrace(e));
			return null;
		}
		
	}
	
	
	public static void main(String[] args) throws Exception {
		TrackingServiceImpl trackingServiceImpl = new TrackingServiceImpl();
		
		trackingServiceImpl.getRECISTHTML("aaron", "7", "admin", "http://epad-dev5.stanford.edu:8080", "68873466F59BFFD24339B20EC5F5C97D", "LineLength");
	}

	
	public String getRECISTHTML(String projectID, String patientID, String username,
								String server, String session, String selectedMetric) throws Exception
	{
		/*
    	Map<Date, List<ImageAnnotation>> imageAnnotationsByStudyDate = getImageAnnotationsForPatient(null);
    	
		String username  = "admin",
			   projectID = "f",
			   server    = "http://epad-dev5.stanford.edu:8080",
			   session   = "D6FCA63C3F7772E412FA05FD173542F2",
			   patientID = "7";
		*/
	
		logger.info("Getting RECIST HTML");
		
		getPatientNames(projectID, username, session, server);
				
		// Target lesion calculation.
		Map<Date,List<ImageAnnotation>> targetImageAnnotationsByStudyDate = getImageAnnotationsForPatient(projectID, patientID, username, session, server, false);
		Map<String, CalculationResult> targetCalculationResultsByMetric = new HashMap<String, CalculationResult>();
		TumorAnalysisCalculator targetTumorAnalysisCalculator = new TumorAnalysisCalculator(targetImageAnnotationsByStudyDate);
		for(String metric : new String[] { selectedMetric }) targetCalculationResultsByMetric.put(metric, targetTumorAnalysisCalculator.calculateRECIST(metric, "mm"));
		
		// Non-target lesion calculation.
		Map<Date,List<ImageAnnotation>> nonTargetImageAnnotationsByStudyDate = getImageAnnotationsForPatient(projectID, patientID, username, session, server, true);
		Map<String, CalculationResult> nonTargetCalculationResultsByMetric = new HashMap<String, CalculationResult>();
		TumorAnalysisCalculator nonTargetTumorAnalysisCalculator = new TumorAnalysisCalculator(nonTargetImageAnnotationsByStudyDate);
		for(String metric : new String[] { selectedMetric }) nonTargetCalculationResultsByMetric.put(metric, nonTargetTumorAnalysisCalculator.calculateRECIST(metric, "mm"));

		logger.info("About to render.");
		return renderDocument(patientID, "Dr. _____________________________", new Date(), targetCalculationResultsByMetric.get(selectedMetric), nonTargetCalculationResultsByMetric.get(selectedMetric));
	}
	
	// set the session cookie in the http client
	private void setSessionCookie(String serverProxy, DefaultHttpClient client, String session) {

		//String serverProxy = "http://epad-dev5.stanford.edu:8080";
		
		String server = serverProxy.replace("http://", "")
				.replace(":8080", "");

		String path = "/epad";

		CookieStore cookieStore = client.getCookieStore();

		BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", session);
		cookie.setVersion(0);

		cookie.setDomain(server);
		cookie.setPath(path);

		cookieStore.addCookie(cookie);
		
		BasicClientCookie admincookie = new BasicClientCookie("ePADLoggedInUser", "admin");
		admincookie.setVersion(0);

		admincookie.setDomain(server);
		admincookie.setPath(path);

		cookieStore.addCookie(admincookie);
		client.setCookieStore(cookieStore);
	}
}
