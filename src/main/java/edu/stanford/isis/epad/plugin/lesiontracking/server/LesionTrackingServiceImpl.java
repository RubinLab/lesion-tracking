package edu.stanford.isis.epad.plugin.lesiontracking.server;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.restlet.data.Status;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.stanford.isis.epad.plugin.lesiontracking.client.LesionTrackingService;
import edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult;
import edu.stanford.isis.epad.plugin.lesiontracking.client.recist.ImageAnnotationUtility;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation;
//import edu.stanford_usp.server.resources.Utils;
//import edu.stanford_usp.server.EPadServicesApplication;

import edu.stanford.isis.epad.plugin.lesiontracking.client.PatientOverlay;
import edu.stanford.isis.epad.plugin.lesiontracking.client.ResultSet;
//import edu.stanford_usp.client.model.DicomPatient;
//import edu.stanford_usp.client.view.impl.ProjectTreeModelPatient;

public class LesionTrackingServiceImpl extends RemoteServiceServlet implements LesionTrackingService
{
	public static final String LIST = "list",
							   VALUES = "value",
							   PERSON = "Person",
							   IMAGE_ANNOTATION = "ImageAnnotation";
	private DefaultHttpClient client;
	private String username;

	public static void main(String[] args) throws Exception
	{
		
	}
	
	@Override
	public String setClient(String username, String session)
	{
		this.client = new DefaultHttpClient();
		this.username = username;
		setCookie(client, session);
		return "success";
	}
	
	@Override
    public String getPatientNames() throws Exception
    {
		// here's the result
		String result = "";
		
		String request = "http://epad-dev2.stanford.edu:8080/epad/v2/projects/unassigned/subjects/?username=" + username;
		
		HttpGet get = new HttpGet(request);

		get.addHeader("accept", "application/json");
		HttpResponse response;
		try {
			// make the call
			response = client.execute(get);
			if (response != null) {
				// reflect the status line
				//StatusLine statusLine = response.getStatusLine();
				
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream instream = entity.getContent();

					BufferedReader rd = new BufferedReader(new InputStreamReader(
							response.getEntity().getContent()));
					String line;
					while ((line = rd.readLine()) != null) {
						result = line;
					}
					instream.close();
				}
				System.out.println("Result Patient Name: " + result);

			} else {
				System.out.println("Result Patient Name: Empty");
			}
		
		} catch (Exception e) {
			System.out.println(e);
		}
		
		List<String> patientNames = new ArrayList<String>();
		patientNames.add(result);
		
//		System.out.println("requestSessionString: " + requestSessionString());
		
		return result;
    }

	public List<ImageAnnotation> getImageAnnotationsForPatient(String patientId) throws Exception
	{
		// here's the result
		String result = "";

		String request = "http://epad-dev2.stanford.edu:8080/epad/aimresource/?patientId=" + patientId + "&user=admin";

		HttpGet get = new HttpGet(request);

		get.addHeader("accept", "application/xml");
		HttpResponse response;
		try {
			// make the call
			response = client.execute(get);
			if (response != null) {
				
				// reflect the status line
				//StatusLine statusLine = response.getStatusLine();
				
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream instream = entity.getContent();

					BufferedReader rd = new BufferedReader(new InputStreamReader(
							response.getEntity().getContent()));
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
		Document document = db.parse(new ByteArrayInputStream(result.getBytes()));
		Element element = document.getDocumentElement();
		NodeList nodeList = element.getChildNodes();
		
		List<ImageAnnotation> imageAnnotations = new ArrayList<ImageAnnotation>();
		
		for(int i = 0; i < nodeList.getLength(); i++)
		{
			Node node = nodeList.item(i);
			if(node instanceof Element)
			{
				element = (Element)nodeList.item(i);
				String nodeName = element.getNodeName();
				
				if("ImageAnnotation".equals(nodeName))
				{
					imageAnnotations.add(AIMFileReader.parseImageAnnotationFromNode(element, ""));
				}
			}
		}
		
		return imageAnnotations;
	}
	
	public static void setCookie(DefaultHttpClient client, String session){
		
		String server = "http://epad-dev2.stanford.edu:8080".replace("http://", ".").replace(":8080", "");
		String path = "/epad";

		CookieStore cookieStore = client.getCookieStore();
		
		BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", session);
		
		cookie.setVersion(0);
		cookie.setDomain(server);
		cookie.setPath(path);
		
		cookieStore.addCookie(cookie); 
		client.setCookieStore(cookieStore);
	}
    
@Override
    public String downloadRECISTTableImage(CalculationResult cr)
    {
    	return RECISTTableServlet.downloadRECISTTableImage(cr, getServletContext().getRealPath("/")).getName();
    }

    @Override
    public String downloadRECISTChartImage(CalculationResult cr)
    {
        return RECISTChartServlet.drawSeriesGraphToFile(cr, getServletContext().getRealPath("/") + "/images/recist_chart_" + (int)Math.ceil(Math.random() * 10000) + ".jpg").getName();
    }
    
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
}
