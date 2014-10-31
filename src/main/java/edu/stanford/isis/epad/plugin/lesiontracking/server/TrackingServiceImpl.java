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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.restlet.resource.ResourceException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.stanford.isis.epad.plugin.lesiontracking.client.TrackingService;
import edu.stanford.isis.epad.plugin.lesiontracking.server.TumorAnalysisCalculator.CalculationResult;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation;

public class TrackingServiceImpl extends RemoteServiceServlet implements
		TrackingService {
	public static final String LIST = "list", VALUES = "value",
			PERSON = "Person", IMAGE_ANNOTATION = "ImageAnnotation";
	private DefaultHttpClient client;
	private String username, session;

	static final TempLogger logger = TempLogger.getInstance();

	public static void main(String[] args) throws Exception {

	}

	@Override
	public String setClient(String username) {
		this.client = new DefaultHttpClient();
		return "success";
	}

	@Override
	public String getPatientNames(String projectID, String username,
			String session, String server) throws Exception {

		logger.info("Calling new getPatientNames");
		//logger.info("getPatientNames " + this.getServletContext() + " "
		//		+ this.getServletName() + " " + this.getServletInfo());

		// here's the result
		logger.info("getPatientNames " + projectID + " " + username + " "
				+ session + " " + server);
		String result = "";

		String request = server + "/epad/v2/projects/" + projectID
				+ "/subjects/?username=" + username;

		logger.info("getPatientNames request " + request);

		client = new DefaultHttpClient();

		setSessionCookie(server, client, session);

		HttpGet get = new HttpGet(request);

		get.addHeader("accept", "application/json");
		Header[] headers = get.getAllHeaders();
		for (int i = 0; i < headers.length; i++) {
			logger.info("Header: " + headers[i]);
		}

		List<Cookie> cookies = client.getCookieStore().getCookies();
		for (int i = 0; i < cookies.size(); i++) {
			logger.info("Cookie: " + cookies.get(i).getName() + " "
					+ cookies.get(i).getPath() + " "
					+ cookies.get(i).getValue() + " " +
					  cookies.get(i).getDomain());
		}

		HttpResponse response;
		try {
			// make the call
			response = client.execute(get);
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
						result = line;
					}
					instream.close();
				}
				logger.info("Result Patient Name: " + result);

			} else {
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
			String session, String server) throws Exception {

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
		
		return RECISTCalculator.loadAndSortAIMFilesByStudyDate(imageAnnotations);
	}

	public String renderDocument(String patientName, String physicianName, Date date, CalculationResult calculationResult)
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
        context.put("patient_name", patientName);
        context.put("physician_name", physicianName);
        context.put("date", date);
        context.put("imageAnnotationsByNameAndStudyDate", calculationResult.getImageAnnotationsByNameAndStudyDate());
        context.put("metricValuesByImageAnnotation", calculationResult.getMetricValuesByImageAnnotation());
        context.put("studyDates", calculationResult.getSortedStudyDates());
        context.put("sortedImageAnnotationNames", calculationResult.getSortedImageAnnotationNames());
        context.put("anatomicEntityNamesByImageAnnotationName", calculationResult.getAnatomicEntityNamesByImageAnnotationName());
        context.put("metricValuesByImageAnnotation", calculationResult.getMetricValuesByImageAnnotation());
        context.put("metricSumsByStudyDate", calculationResult.getMetricSumsByStudyDate());
        context.put("anatomicEntitiesByImageAnnotation", calculationResult.getAnatomicEntitiesByImageAnnotation());
        context.put("units", calculationResult.getUnitOfMeasure());
        context.put("responseRatesByStudyDate", calculationResult.getResponseRatesByStudyDate());
        context.put("cr", calculationResult);
        
        
        writer = new StringWriter();
        t.merge( context, writer );

		try{
			logger.info("Writing recist_out.doc");
	        PrintWriter printWriter = new PrintWriter(getServletContext().getRealPath("/") + "/recist_out.doc", "UTF-8");
	        printWriter.println(writer.toString());
	        printWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		logger.info("Returning [" + writer.toString() + "].");
        return writer.toString();
		} catch (Exception e) {
			logger.info(ExceptionUtils.getStackTrace(e));
			return null;
		}
		
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
		Map<Date,List<ImageAnnotation>> imageAnnotationsByStudyDate = getImageAnnotationsForPatient(projectID, patientID, username, session, server);
		// Perform a calculation for each metric.
		Map<String, CalculationResult> calculationResultsByMetric = new HashMap<String, CalculationResult>();
		TumorAnalysisCalculator tumorAnalysisCalculator = new TumorAnalysisCalculator(imageAnnotationsByStudyDate);
		for(String metric : new String[] { selectedMetric }) calculationResultsByMetric.put(metric, tumorAnalysisCalculator.calculateRECIST(metric, "mm"));

		logger.info("About to render.");
		return renderDocument(patientID, "Dr. _____________________________", new Date(), calculationResultsByMetric.get(selectedMetric));
	}

	// public static void setCookie(DefaultHttpClient client, String session,
	// String server) {
	//
	// server = server.replace("http://", ".").replace(":8080", "");
	// String path = "/epad";
	//
	// CookieStore cookieStore = client.getCookieStore();
	//
	// BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", session);
	//
	// cookie.setVersion(0);
	// cookie.setDomain(server);
	// cookie.setPath(path);
	//
	// cookieStore.addCookie(cookie);
	// client.setCookieStore(cookieStore);
	// }

	/*
	@Override
	public String downloadRECISTTableImage(CalculationResult cr) {
		return RECISTTableServlet.downloadRECISTTableImage(cr,
				getServletContext().getRealPath("/")).getName();
	}

	@Override
	public String downloadRECISTChartImage(CalculationResult cr) {
		return RECISTChartServlet.drawSeriesGraphToFile(
				cr,
				getServletContext().getRealPath("/") + "/images/recist_chart_"
						+ (int) Math.ceil(Math.random() * 10000) + ".jpg")
				.getName();
	}
	*/

	@Override
	public String requestSessionString(ArrayList<String> login) {
		final int USERNAME = 0;
		final int PASSWORD = 1;

		String username = login.get(USERNAME);
		String password = login.get(PASSWORD);

		String result = null;

		String authString = buildAuthorizatonString(username, password);
		String url = "http://epad-dev2.stanford.edu:8080/epad/session/";

		// get the http client and post the file
		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpPost httppost = new HttpPost(url);

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("username", "admin"));
			nameValuePairs.add(new BasicNameValuePair("password", "admin"));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			httppost.setHeader("Authorization", "Basic " + authString);

			HttpResponse response = httpclient.execute(httppost);

			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				result = line;
			}

			if (response.getStatusLine().getStatusCode() != 200) {

				result = response.getStatusLine().getReasonPhrase();
				throw new ResourceException(response.getStatusLine()
						.getStatusCode());

			}
		} catch (IOException e) {
		} finally {
			try {
				httpclient.getConnectionManager().shutdown();
			} catch (Exception ignore) {
			}
		}

		return result;
	}

	private String buildAuthorizatonString(String username, String password) {
		String authString = username + ":" + password;
		byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
		String authStringEnc = new String(authEncBytes);
		return authStringEnc;
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
