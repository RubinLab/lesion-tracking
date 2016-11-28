package edu.stanford.isis.epad.plugin.lesiontracking.server;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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
import org.xml.sax.SAXException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibm.icu.text.DecimalFormat;

import edu.stanford.isis.epad.plugin.lesiontracking.client.TrackingService;
import edu.stanford.isis.epad.plugin.lesiontracking.server.TumorAnalysisCalculator.CalculationResultL;
import edu.stanford.hakan.aim4api.base.AimException;
import edu.stanford.hakan.aim4api.base.ImageAnnotationCollection;
import edu.stanford.hakan.aim4api.compability.aimv3.CalculationCollection;
//import edu.stanford.hakan.aim4api.compability.aimv3.Aim;
import edu.stanford.hakan.aim4api.project.epad.Aim;
import edu.stanford.hakan.aim4api.usage.AnnotationGetter;

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
			PERSON = "Person", IMAGE_ANNOTATION = "Aim";
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

		setSessionCookie(username, server, client, session);

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
	
	/**
	 * @param result
	 * @param isNonTarget
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static Map<Date, List<Aim>> parseXMLStringForImageAnnotations(String result, Boolean isNonTarget) throws SAXException, IOException, ParserConfigurationException
	{
		

		List<Aim> imageAnnotations=new ArrayList<Aim>(); 
		
		try {
			imageAnnotations = parseAnnotations(result);
		} catch (AimException e) {
			logger.warning("Couldn't parse annotation : " + e.getMessage(),e);
		}

		Map<Date, List<Aim>> targetImageAnnotationsByStudyDate = new HashMap<Date, List<Aim>>();
		logger.info("Number of image annotations : " + imageAnnotations.size());
		for (Aim aim : imageAnnotations)
		{
			if (!aim.getCodeValue().equals("RECIST")) //not recist skip
				continue;
			Date studyDate;
			
			studyDate=aim.getFirstStudyDate();
			logger.info("Got studyDate from ImageReferenceCollection: " + studyDate);
								
			
			if(!targetImageAnnotationsByStudyDate.containsKey(studyDate))
				targetImageAnnotationsByStudyDate.put(studyDate, new ArrayList<Aim>());

			String targetLesionFlag = "";
			if (!aim.getImagingObservationCollection().getImagingObservationList().isEmpty()){
				targetLesionFlag = aim.getImagingObservationCollection().getImagingObservationList().get(0).getImagingObservationCharacteristicCollection().getImagingObservationCharacteristicList().get(0).getCodeMeaning();
				if (targetLesionFlag ==null || targetLesionFlag.equals(""))
					targetLesionFlag = aim.getImagingObservationCollection().getImagingObservationList().get(0).getImagingObservationCharacteristicCollection().getImagingObservationCharacteristicList().get(0).getCodingSchemeDesignator();
				
				if (targetLesionFlag ==null || targetLesionFlag.equals(""))
					targetLesionFlag = aim.getImagingObservationCollection().getImagingObservationList().get(0).getImagingObservationCharacteristicCollection().getImagingObservationCharacteristicList().get(0).getAllowedTerm().getCodeMeaning();
				
				logger.info("targetLesionFlag "+targetLesionFlag);
				
				//logger.info("TARGET LESION STRING: " + targetLesionFlag);
				logger.info("image annotation :" + aim.getName());
				
				if(isNonTarget == null) {
					logger.info("TARGET flag is null" + " Study date" + studyDate);
					targetImageAnnotationsByStudyDate.get(studyDate).add(aim);
				}
				else
				{
					logger.info("TARGET flag is not null");
					String targetString = targetLesionFlag.toLowerCase();
					if(isNonTarget && targetString.contains("non-target"))
						targetImageAnnotationsByStudyDate.get(studyDate).add(aim);
					else if(!isNonTarget && !targetString.contains("non-target") && targetString.contains("target"))
						targetImageAnnotationsByStudyDate.get(studyDate).add(aim);
				}
			}
			else {
				logger.info("No targetLesionFlag for aim " + aim.getName());
			}
//			aim.outputAIMHeirarchy(2);
//			logger.info("GET from the map: ----");
//			Aim ia = targetImageAnnotationsByStudyDate.get(studyDate).get(0);
//			ia.outputAIMHeirarchy(2);
		}
		
		logger.info("The numner of annotations" + targetImageAnnotationsByStudyDate.size());
		
		return targetImageAnnotationsByStudyDate;
	}
	
	private static List<Aim> parseAnnotations(String text) throws AimException {

		List<Aim> aims = new ArrayList<Aim>();
		List<ImageAnnotationCollection> imageAnnotationCollections = new ArrayList<ImageAnnotationCollection>();

		imageAnnotationCollections = AnnotationGetter
				.getImageAnnotationCollectionsFromString(text, "");

		// convert them to Aims
		for (ImageAnnotationCollection iac : imageAnnotationCollections) {
			aims.add(new Aim(new edu.stanford.hakan.aim4api.compability.aimv3.ImageAnnotation(iac)));
		}

		return aims;
	}
	
	public List<List<String>> getMetricsAndANamesForPatient(
			String projectID, String patientID, String username,
			String session, String server, Boolean isNonTarget) throws Exception {
		Map<Date, List<Aim>> imageAnnotations = getImageAnnotationsForPatient(projectID, patientID, username, session, server, isNonTarget);
		// Extract the unique identifiers and metrics for these
		// annotations.
		List<String> annotations = new ArrayList<String>();
		List<String> metrics = new ArrayList<String>();

		for(Date studyDate : imageAnnotations.keySet())
			
		for (Aim ia : imageAnnotations.get(studyDate)) {
			String uid = ia.getUniqueIdentifier();
			String name = ia.getName();
			logger.info("Annotation name" + name + " " +ia.getName());

			if (ia.getImageReferenceCollection().getImageReferenceList().size() == 0 )
				continue;

			// String date = ImageAnnotationUtility.getStudyDate(ia.getImageReferenceCollection(0)).toString();
			logger.info("calculations size:"+ia.getCalculationCollection().getCalculationList().size());
			
			if (ia.getCalculationCollection().getCalculationList().size()== 0 && ia.getCalculations().size()==0)
				continue;

			// Find all of the metrics in this image annotation.
			if(ia.getCalculationCollection().getCalculationList().size() > 0)
			{
				CalculationCollection calculationCollection = ia.getCalculationCollection();
				for (int i = 0; i < calculationCollection.getCalculationList().size(); i++)
				{
					String metric = calculationCollection.getCalculationList().get(i).getDescription();
					logger.info("metric desc:"+metric);
					if (metric == null || metric.isEmpty())
						metric = calculationCollection.getCalculationList().get(i).getCodeMeaning();
					logger.info("metric:"+metric);
					if (metric != null && !metrics.contains(metric) && !metric.isEmpty())
						metrics.add(metric);
					
					
				}
			}
			
//			if(ia.getNumberOfCalculationEntityCollections() > 0)
//			{
//				CalculationEntityCollection calculationEntityCollection = ia.getCalculationEntityCollection(0);
//				for (int i = 0; i < calculationEntityCollection.getNumberOfCalculationEntities(); i++)
//				{
//					CalculationEntity calculationEntity = calculationEntityCollection.getCalculationEntity(i);
//					
//					if(calculationEntity.getNumberOfDescriptions() > 0)
//					{
//						Description description = calculationEntity.getDescription(0);
//						String metric = description.getValue();
//						
//						if (metric != null && !metrics.contains(metric) && !metric.isEmpty())
//							metrics.add(metric);
//					}
//				}
//			}

			// annotations.add("Annotation UID: " + uid +
			// " ------ Study Date: " + date);
			annotations.add(name);
		}
		List<List<String>> results=new ArrayList<List<String>>();
		results.add(annotations);
		results.add(metrics);
		return results;
	}
	
	

	public Map<Date, List<Aim>> getImageAnnotationsForPatient(
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
			//System.out.println("RESPONSE: " + response);
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
				//System.out.println("Result AIM: " + result);

			} else {
				//System.out.println("Result AIM: Empty");
			}

		} catch (Exception e) {
			//System.out.println(e);
		}
		
		return parseXMLStringForImageAnnotations(result, isNonTarget);
	}

	public String renderDocument(String patientName, String physicianName, Date date, CalculationResultL targetCalculationResult, CalculationResultL nonTargetCalculationResult)
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
       
        if(nonTargetCalculationResult != null)
        {
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
        }
        
        
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
	
	static String readFile(String path, Charset encoding) 
			  throws IOException 
			{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return new String(encoded, encoding);
			}
	
	public static void main(String[] args) throws Exception {
		TrackingServiceImpl trackingServiceImpl = new TrackingServiceImpl();
		
		//trackingServiceImpl.getRECISTHTML("aaron", "7", "admin", "http://epad-dev5.stanford.edu:8080", "68873466F59BFFD24339B20EC5F5C97D", "LineLength");
		//trackingServiceImpl.getRECISTHTML("RECIST", "7", "admin", "https://epad-public.stanford.edu", "FD18E22C7A2A98C2446453D397C6F803", "LineLength");
		//trackingServiceImpl.getRECISTHTML("RECIST", "7", "admin", "http://epad-public.stanford.edu:8080", "FD18E22C7A2A98C2446453D397C6F803", "LineLength");
		
		String result = readFile("image_annotations.xml", Charset.defaultCharset());
		Map<Date, List<Aim>> targetImageAnnotationsByStudyDate = trackingServiceImpl.parseXMLStringForImageAnnotations(result, false);
		trackingServiceImpl.getRECISTHTML(targetImageAnnotationsByStudyDate, new HashMap<Date, List<Aim>>(), "linelength", "Aaron");
		
		//System.out.println(targetImageAnnotationsByStudyDate.size());
	}

	
	private String getRECISTHTML(Map<Date,List<Aim>> targetImageAnnotationsByStudyDate,
										Map<Date,List<Aim>> nonTargetImageAnnotationsByStudyDate,
										String selectedMetric, String patientID)
	{
		GWT.log("In the getRECISTHTML from the tracking service");
		// Target lesion calculation.
		Map<String, CalculationResultL> targetCalculationResultsByMetric = new HashMap<String, CalculationResultL>();
		TumorAnalysisCalculator targetTumorAnalysisCalculator = new TumorAnalysisCalculator(targetImageAnnotationsByStudyDate);
		for(String metric : new String[] { selectedMetric }) targetCalculationResultsByMetric.put(metric, targetTumorAnalysisCalculator.calculateRECIST(metric, "mm"));
		
		// Non-target lesion calculation.

		Map<String, CalculationResultL> nonTargetCalculationResultsByMetric = new HashMap<String, CalculationResultL>();
		if(nonTargetImageAnnotationsByStudyDate != null && !nonTargetImageAnnotationsByStudyDate.isEmpty())
		{
			TumorAnalysisCalculator nonTargetTumorAnalysisCalculator = new TumorAnalysisCalculator(nonTargetImageAnnotationsByStudyDate);
			for(String metric : new String[] { selectedMetric }) nonTargetCalculationResultsByMetric.put(metric, nonTargetTumorAnalysisCalculator.calculateRECIST(metric, "mm"));
		}

		logger.info("About to render.");
		return renderDocument(patientID, "Dr. _____________________________", new Date(),
				targetCalculationResultsByMetric.get(selectedMetric),
				nonTargetCalculationResultsByMetric.get(selectedMetric));
	}
	
	public String getRECISTHTML(String projectID, String patientID, String username,
								String server, String session, String selectedMetric) throws Exception
	{
		/*
    	Map<Date, List<Aim>> imageAnnotationsByStudyDate = getImageAnnotationsForPatient(null);
    	
		String username  = "admin",
			   projectID = "f",
			   server    = "http://epad-dev5.stanford.edu:8080",
			   session   = "D6FCA63C3F7772E412FA05FD173542F2",
			   patientID = "7";
		*/
	
		logger.info("Getting RECIST HTML");
		
		getPatientNames(projectID, username, session, server);

		Map<Date,List<Aim>> targetImageAnnotationsByStudyDate = getImageAnnotationsForPatient(projectID, patientID, username, session, server, false);
		Map<Date,List<Aim>> nonTargetImageAnnotationsByStudyDate = getImageAnnotationsForPatient(projectID, patientID, username, session, server, true);
		
		return getRECISTHTML(targetImageAnnotationsByStudyDate, nonTargetImageAnnotationsByStudyDate, selectedMetric, patientID);
	}
	
	// set the session cookie in the http client
	private void setSessionCookie(String username, String serverProxy, DefaultHttpClient client, String session) {

		//String serverProxy = "http://epad-dev5.stanford.edu:8080";
		
		String server;
		if(serverProxy.contains("http://"))
			server = serverProxy.replace("http://", "").replace(":8080", "");
		else
			server = serverProxy.replace("https://", "").replace(":8080", "");
		
		

		String path = "/epad/";

		CookieStore cookieStore = client.getCookieStore();

		BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", session);
		cookie.setVersion(0);

		cookie.setDomain(server);
		cookie.setPath(path);

		cookieStore.addCookie(cookie);
		
		BasicClientCookie admincookie = new BasicClientCookie("ePADLoggedInUser", username);
		admincookie.setVersion(0);

		admincookie.setDomain(server);
		admincookie.setPath(path);

		cookieStore.addCookie(admincookie);
		client.setCookieStore(cookieStore);
	}
}
