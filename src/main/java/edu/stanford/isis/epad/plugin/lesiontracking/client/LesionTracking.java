package edu.stanford.isis.epad.plugin.lesiontracking.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationCollection;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationEntity;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationEntityCollection;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.Description;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation;

public class LesionTracking implements EntryPoint {

	private final LesionTrackingViewImpl lesionTrackingViewImpl = new LesionTrackingViewImpl(
			this);

	private static TrackingServiceAsync trackingServiceAsync = (TrackingServiceAsync) GWT
			.create(TrackingService.class);

	private static final Logger logger = Logger.getLogger("LesionTracking");

	private Map<Date, List<ImageAnnotation>> imageAnnotations;
	String username, session, server, projectID, patientID;

	private String recistHTML;

	public void onModuleLoad() {
		logger.info("onModuleLoad");
		
		/*
		RootPanel.get().add(lesionTrackingViewImpl);
		
		username = "admin";
		projectID = "f";
		server = "http://epad-dev7.stanford.edu:8080/epad/";
		session = "55F7B9821F68658AAC9892A86F7BBBD1";
		*/
		
		
		onGetPatientNames();
		
	}

	public void startModule() {
		logger.info("startModule");
	}
/*
	// TODO don't need this
	public void onSetClient() {
		username = Cookies.getCookie("ePADLoggedinUser");
		session = Cookies.getCookie("JSESSIONID");
		server = GWT.getHostPageBaseURL();

		projectID = "unassigned";
		logger.info("onSetClient" + projectID + " " + username + " " + session
				+ " " + server);

		trackingServiceAsync.setClient(username, new AsyncCallback<String>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("setClient error: : " + caught.toString());
			}

			@Override
			public void onSuccess(String result) {
				Window.alert("setClient success: : " + username + " " + result);
				startModule();
			}
		});
	}
*/
	// call the server to get the list of patients in a project
	public void onGetPatientNames() {
		
		if(username == null)
			return;
		
		trackingServiceAsync.getPatientNames(projectID, username, session,
				server, new AsyncCallback<String>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("getPatientNames error: : " + caught.toString());
					}

					@Override
					public void onSuccess(String result) {
						if (result != null) {

							try {
								// parse the json string into a jsArray
								ResultSet<PatientOverlay> resultSet = JsonUtils
										.safeEval(result);

								List<String> patientNames = new ArrayList<String>();
								int selectedPatientIndex = -1;
								for (int i = 0; i < resultSet.length(); i++)
								{
									PatientOverlay patientOverlay = resultSet.get(i);
									String patientName = patientOverlay.getSubjectName();
									String patientID   = patientOverlay.getSubjectID();
									
									if(LesionTracking.this.patientID != null && 
									   LesionTracking.this.patientID.equals(patientID))
									{
										selectedPatientIndex = i;
									}
									patientNames.add(patientName.replaceAll("^", "") + "$" + patientID);
								}
								lesionTrackingViewImpl.loadPatientNames(patientNames, selectedPatientIndex);
							} catch (Exception e) {
								logger.info("Error: " + e.getMessage());
							}

						} else {
							logger.info("Error: " + result);
						}

					}
				});
	}

	// user hit a patient name
	public void onPatientNameSelected(String patientID) {

		this.patientID = patientID;
		
		trackingServiceAsync.getImageAnnotationsForPatient(projectID,
				patientID, username, session, server, null,
				new AsyncCallback<Map<Date, List<ImageAnnotation>>>() {
					@Override
					public void onFailure(Throwable caught) {
						//System.out.println("getImageAnn error: : " + caught.toString());
					}

					@Override
					public void onSuccess(Map<Date, List<ImageAnnotation>> imageAnnotations) {
						
						logger.info("NUMBER OF IMAGE ANNOTATIONS: " + imageAnnotations.size());
						
						LesionTracking.this.imageAnnotations = imageAnnotations;

						// Extract the unique identifiers and metrics for these
						// annotations.
						List<String> annotations = new ArrayList<String>();
						List<String> metrics = new ArrayList<String>();

						for(Date studyDate : imageAnnotations.keySet())
							
						for (ImageAnnotation ia : imageAnnotations.get(studyDate)) {
							String uid = ia.getUniqueIdentifier();
							String name = ia.getNameAttribute();
							if (ia.getNumberOfImageReferenceCollections() == 0 && ia.getNumberOfImageReferenceEntityCollections() == 0)
								continue;

							// String date = ImageAnnotationUtility.getStudyDate(ia.getImageReferenceCollection(0)).toString();

							if (ia.getNumberOfCalculationCollections() == 0 && ia.getNumberOfCalculationEntityCollections() == 0)
								continue;

							// Find all of the metrics in this image annotation.
							
							if(ia.getNumberOfCalculationCollections() > 0)
							{
								CalculationCollection calculationCollection = ia.getCalculationCollection(0);
								for (int i = 0; i < calculationCollection.getNumberOfCalculations(); i++)
								{
									String metric = calculationCollection.getCalculation(i).getDescription();
	
									if (metric == null || metric.isEmpty())
										metric = calculationCollection.getCalculation(i).getType();
	
									if (metric != null && !metrics.contains(metric) && !metric.isEmpty())
										metrics.add(metric);
								}
							}
							
							if(ia.getNumberOfCalculationEntityCollections() > 0)
							{
								CalculationEntityCollection calculationEntityCollection = ia.getCalculationEntityCollection(0);
								for (int i = 0; i < calculationEntityCollection.getNumberOfCalculationEntities(); i++)
								{
									CalculationEntity calculationEntity = calculationEntityCollection.getCalculationEntity(i);
									
									if(calculationEntity.getNumberOfDescriptions() > 0)
									{
										Description description = calculationEntity.getDescription(0);
										String metric = description.getValue();
										
										if (metric != null && !metrics.contains(metric) && !metric.isEmpty())
											metrics.add(metric);
									}
								}
							}

							// annotations.add("Annotation UID: " + uid +
							// " ------ Study Date: " + date);
							annotations.add(name);
						}

						lesionTrackingViewImpl.loadAnnotationsList(annotations);
						lesionTrackingViewImpl.loadMetricsList(metrics);
					}
				});

		/*
		 * lesionTrackingServiceAsync.getStudies(patientName, new
		 * AsyncCallback<List<String>>() {
		 * 
		 * @Override public void onFailure(Throwable caught) {
		 * System.out.println(caught); }
		 * 
		 * @Override public void onSuccess(List<String> studies) {
		 * lesionTrackingViewImpl.loadStudiesList(studies); } });
		 */
	}

	public static TrackingServiceAsync getTrackingServiceAsync() {
		return trackingServiceAsync;
	}

	/*
	 * public void onStudySelected(String studyUID) {
	 * lesionTrackingServiceAsync.getSeries(patientName, studyUID, new
	 * AsyncCallback<List<String>>() {
	 * 
	 * @Override public void onFailure(Throwable caught){}
	 * 
	 * @Override public void onSuccess(List<String> series) {
	 * lesionTrackingViewImpl.loadSeriesList(series); } }); }
	 */
	/*
	 * public void onSeriesSelected(String seriesUID) {
	 * lesionTrackingServiceAsync.getAnnotations(seriesUID, new
	 * AsyncCallback<List<ImageAnnotation>>() {
	 * 
	 * @Override public void onFailure(Throwable caught){}
	 * 
	 * @Override public void onSuccess(List<ImageAnnotation> imageAnnotations) {
	 * LesionTracking.this.imageAnnotations = imageAnnotations;
	 * 
	 * // Extract the unique identifiers and metrics for these annotations.
	 * List<String> annotations = new ArrayList<String>(); List<String> metrics
	 * = new ArrayList<String>();
	 * 
	 * for(ImageAnnotation ia : imageAnnotations) { String uid =
	 * ia.getUniqueIdentifier(); String date =
	 * ImageAnnotationUtility.getStudyDate(ia.getImageReferenceCollection(0));
	 * 
	 * CalculationCollection calculationCollection =
	 * ia.getCalculationCollection(0);
	 * 
	 * for(int i = 0; i < calculationCollection.getNumberOfCalculations(); i++)
	 * { String metric =
	 * calculationCollection.getCalculation(i).getDescription(); if(metric ==
	 * null || metric.isEmpty()) metric =
	 * calculationCollection.getCalculation(i).getType();
	 * 
	 * if(metric != null && !metrics.contains(metric) && !metric.isEmpty())
	 * metrics.add(metric); }
	 * 
	 * annotations.add("Annotation UID: " + uid + " ------ Study Date: " +
	 * date); }
	 * 
	 * lesionTrackingViewImpl.loadAnnotationsList(annotations);
	 * lesionTrackingViewImpl.loadMetricsList(metrics); } }); }
	 */
	
	private static String getMessage (Throwable throwable) {
	    String ret="";
	    while (throwable!=null) {
	            if (throwable instanceof com.google.gwt.event.shared.UmbrellaException){
	                    for (Throwable thr2 :((com.google.gwt.event.shared.UmbrellaException)throwable).getCauses()){
	                            if (ret != "")
	                                    ret += "\nCaused by: ";
	                            ret += thr2.toString();
	                            ret += "\n  at "+getMessage(thr2);
	                    }
	            } else if (throwable instanceof com.google.web.bindery.event.shared.UmbrellaException){
	                    for (Throwable thr2 :((com.google.web.bindery.event.shared.UmbrellaException)throwable).getCauses()){
	                            if (ret != "")
	                                    ret += "\nCaused by: ";
	                            ret += thr2.toString();
	                            ret += "\n  at "+getMessage(thr2);
	                    }
	            } else {
	                    if (ret != "")
	                            ret += "\nCaused by: ";
	                    ret += throwable.toString();
	                    for (StackTraceElement sTE : throwable.getStackTrace())
	                            ret += "\n  at "+sTE;
	            }
	            throwable = throwable.getCause();
	    }

	    return ret;
	}

	// user hit a metric item
	public void onMetricsSelected(List<String> selectedMetrics) {

		if(selectedMetrics.isEmpty())
			return;
		String metric = selectedMetrics.get(0);
		
		trackingServiceAsync.getRECISTHTML(projectID,
				patientID, username, server, session, metric,
				new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(getMessage(caught));
					}

					@Override
					public void onSuccess(String recistHTML) {
						LesionTracking.this.recistHTML = recistHTML;
						lesionTrackingViewImpl.showRECISTHTML(recistHTML);
					}
				});

		/*
		ImageAnnotation[][] imageAnnotationsByStudyDate = RECISTCalculator
				.loadAndSortAIMFilesByStudyDate(imageAnnotations, server);
		cr = RECISTCalculator.calculateRECIST(imageAnnotationsByStudyDate,
				selectedMetrics.toArray(new String[selectedMetrics.size()]),
				new SharedNumberFormat() {
					@Override
					public String format(double d) {
						return NumberFormat.getDecimalFormat().format(d);
					}
				});

		int numberOfTimePointsInStudy = cr.getNumberOfTimePointsInStudy();
		lesionTrackingViewImpl.setNumberTimePointsInStudy(
				cr.getMetricUnits()[0], numberOfTimePointsInStudy);

		Lesion[] lesions = cr.getLesions();
		for (int i = 0; i < lesions.length; i++)
			lesionTrackingViewImpl.addLesion(lesions[i]);

		String[] metricsArray = cr.getMetrics();
		String[] metricUnits = cr.getMetricUnits();
		String[][] metricSums = cr.getMetricSums();
		String[][] responseRates = cr.getResponseRates(), responseRatesSinceBaseline = cr
				.getResponseRatesSinceBaseline(), responseRatesSinceNadir = cr
				.getResponseRatesSinceNadir(), responseCategories = cr
				.getResponseCategories();

		for (int i = 0; i < metricsArray.length; i++)
			lesionTrackingViewImpl.addResponseRows(metricsArray[i],
					metricUnits[i], metricSums[i], responseRates[i],
					responseRatesSinceBaseline[i], responseRatesSinceNadir[i],
					responseCategories[i]);

		String[] metrics = cr.getMetrics();
		List<RECISTChartViewImpl> recistChartViewImpls = new ArrayList<RECISTChartViewImpl>();
		for (int i = 0; i < metrics.length; i++) {
			System.out.println(metrics[i]);
			RECISTChartViewImpl recistChartViewImpl = new RECISTChartViewImpl();
			recistChartViewImpls.add(recistChartViewImpl);
			RECISTChart recistChart = new RECISTChart(recistChartViewImpl);
			recistChart.drawRECISTChart(cr.getStudyDates()[0],
					cr.getMetrics()[i], cr.getMetricSums()[i]);
			lesionTrackingViewImpl.showRECISTCharts(recistChartViewImpls);
		}
		*/
	}

	public void onDownloadAsRECISTTableButtonClicked() {
		/*
		trackingServiceAsync.downloadRECISTTableImage(cr,
				new AsyncCallback<String>() {
					@Override
					public void onSuccess(String result) {
						System.out.println("result: " + result);
						Window.open(GWT.getHostPageBaseURL() + "images/"
								+ result, "Download RECIST Table as JPEG", "");
					}

					@Override
					public void onFailure(Throwable caught) {
					}
				});
		*/
	}

	public void onDownloadAsRECISTChartButtonClicked() {
		/*
		trackingServiceAsync.downloadRECISTChartImage(cr,
				new AsyncCallback<String>() {
					@Override
					public void onSuccess(String result) {
						Window.open(GWT.getHostPageBaseURL() + "images/"
								+ result, "Download RECIST Chart as JPEG", "");
					}

					@Override
					public void onFailure(Throwable caught) {
					}
				});
		*/
	}

	public void onDownloadWordDocumentButtonClicked() {
		Window.open( "recist_out.doc", "_blank", "status=0,toolbar=0,menubar=0,location=0");
	}


	public Widget getView() {
		return lesionTrackingViewImpl.asWidget();
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}
	public void activate(String projectID, String patientID, String username, String session, String server) {

		this.projectID = projectID;
		this.username = username;
		this.session = session;
		this.server = server;
		this.patientID = patientID;
		logger.info("activate" + projectID + " " + username + " " + session + " " + server);
		onGetPatientNames();
	}

	public void deactivate() {
	}
}