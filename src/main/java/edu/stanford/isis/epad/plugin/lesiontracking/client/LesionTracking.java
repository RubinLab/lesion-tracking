package edu.stanford.isis.epad.plugin.lesiontracking.client;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import edu.stanford.isis.epad.plugin.lesiontracking.client.chart.RECISTChart;
import edu.stanford.isis.epad.plugin.lesiontracking.client.chart.RECISTChartViewImpl;
import edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult;
import edu.stanford.isis.epad.plugin.lesiontracking.client.recist.ImageAnnotationUtility;
import edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion;
import edu.stanford.isis.epad.plugin.lesiontracking.client.recist.RECISTCalculator;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationCollection;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.SharedNumberFormat;

public class LesionTracking implements EntryPoint {

	private final LesionTrackingViewImpl lesionTrackingViewImpl = new LesionTrackingViewImpl(
			this);

	private static TrackingServiceAsync trackingServiceAsync = (TrackingServiceAsync) GWT
			.create(TrackingService.class);

	private static final Logger logger = Logger.getLogger("LesionTracking");

	private CalculationResult cr;
	private List<ImageAnnotation> imageAnnotations;
	String username, session, server, projectID, patientID;

	public void onModuleLoad() {
		logger.info("onModuleLoad");
	}

	public void startModule() {
		logger.info("startModule");
	}

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

	// call the server to get the list of patients in a project
	public void onGetPatientNames() {
		trackingServiceAsync.getPatientNames(projectID, username, session,
				server, new AsyncCallback<String>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("getPatientNames error: : "
								+ caught.toString());
					}

					@Override
					public void onSuccess(String result) {
						if (result != null) {

							try {
								// parse the json string into a jsArray
								ResultSet<PatientOverlay> resultSet = JsonUtils
										.safeEval(result);

								List<String> patientNames = new ArrayList<String>();
								for (int i = 0; i < resultSet.length(); i++) {
									patientNames.add(resultSet.get(i)
											.getSubjectName()
											.replaceAll("^", "")
											+ "$"
											+ resultSet.get(i).getSubjectID());
								}
								lesionTrackingViewImpl
										.loadPatientNames(patientNames);
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
		trackingServiceAsync.getImageAnnotationsForPatient(projectID,
				patientID, username, session, server,
				new AsyncCallback<List<ImageAnnotation>>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("getImageAnn error: : "
								+ caught.toString());
					}

					@Override
					public void onSuccess(List<ImageAnnotation> imageAnnotations) {
						LesionTracking.this.imageAnnotations = imageAnnotations;

						// Extract the unique identifiers and metrics for these
						// annotations.
						List<String> annotations = new ArrayList<String>();
						List<String> metrics = new ArrayList<String>();

						for (ImageAnnotation ia : imageAnnotations) {
							String uid = ia.getUniqueIdentifier();
							String name = ia.getNameAttribute();
							if (ia.getNumberOfImageReferenceCollections() == 0)
								continue;

							String date = ImageAnnotationUtility
									.getStudyDate(ia
											.getImageReferenceCollection(0));

							if (ia.getNumberOfCalculationCollections() == 0)
								continue;

							// Find all of the metrics in this image annotation.
							CalculationCollection calculationCollection = ia
									.getCalculationCollection(0);
							for (int i = 0; i < calculationCollection
									.getNumberOfCalculations(); i++) {
								String metric = calculationCollection
										.getCalculation(i).getDescription();

								if (metric == null || metric.isEmpty())
									metric = calculationCollection
											.getCalculation(i).getType();

								if (metric != null && !metrics.contains(metric)
										&& !metric.isEmpty())
									metrics.add(metric);
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

	// user hit a metric item
	public void onMetricsSelected(List<String> selectedMetrics) {
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
	}

	public void onDownloadAsRECISTTableButtonClicked() {
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
	}

	public void onDownloadAsRECISTChartButtonClicked() {
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

	public void setServer(String server) {
		this.server = server;
	}

	public void activate(String projectID, String username, String session,
			String server) {

		this.projectID = projectID;
		this.username = username;
		this.session = session;
		this.server = server;
		RECISTCalculator.setServer(server);
		logger.info("activate" + projectID + " " + username + " " + session
				+ " " + server);
		onGetPatientNames();
	}

	public void deactivate() {
	}

	public void setPatients(List<String> result) {
		lesionTrackingViewImpl.loadPatientNames(result);

	}

}