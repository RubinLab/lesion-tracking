package edu.stanford.isis.epad.plugin.lesiontracking.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.restlet.resource.ResourceException;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

import edu.stanford.isis.epad.plugin.lesiontracking.client.chart.RECISTChart;
import edu.stanford.isis.epad.plugin.lesiontracking.client.chart.RECISTChartViewImpl;
import edu.stanford.isis.epad.plugin.lesiontracking.client.recist.CalculationResult;
import edu.stanford.isis.epad.plugin.lesiontracking.client.recist.ImageAnnotationUtility;
import edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion;
import edu.stanford.isis.epad.plugin.lesiontracking.client.recist.RECISTCalculator;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.CalculationCollection;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.ImageAnnotation;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.SharedNumberFormat;
import edu.stanford.isis.epad.plugin.lesiontracking.client.widget.LoginForm;

public class LesionTracking implements EntryPoint {
	private final LesionTrackingViewImpl lesionTrackingViewImpl = new LesionTrackingViewImpl(this);
//	private final LoginForm loginForm = new LoginForm();
	private static LesionTrackingServiceAsync lesionTrackingServiceAsync = (LesionTrackingServiceAsync) GWT
			.create(LesionTrackingService.class);
	private LoginForm loginForm;

	private CalculationResult cr;
	private List<ImageAnnotation> imageAnnotations;

	public void onModuleLoad() {
		loginForm = new LoginForm(this);

		if (!loginForm.haveUser()) {
			loginForm.showAndClear();
		} else {
			loginForm.onSetClient();
		}
	}
	
	public void startModule() {
		RootPanel.get("lesionTracking").add(lesionTrackingViewImpl.asWidget());
		onGetPatientNames();
	}
	
	public void onGetPatientNames() {
		lesionTrackingServiceAsync.getPatientNames(new AsyncCallback<String>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("getPatientNames error: : " + caught.toString());
			}

			@Override
			public void onSuccess(String result) {
				// parse the json string into a jsArray
				ResultSet<PatientOverlay> resultSet = JsonUtils
						.safeEval(result);

				List<String> patientNames = new ArrayList<String>();
				for (int i = 0; i < resultSet.length(); i++) {
					patientNames.add(resultSet.get(i).getSubjectName() + "$"
							+ resultSet.get(i).getSubjectID());
				}

				lesionTrackingViewImpl.loadPatientNames(patientNames);
			}
		});
	}

	public void onPatientNameSelected(String patientName) {
		lesionTrackingServiceAsync.getImageAnnotationsForPatient(patientName,
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
	
	public static LesionTrackingServiceAsync getLesionTrackingServiceAsync() {
		 return lesionTrackingServiceAsync;
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

	public void onMetricsSelected(List<String> selectedMetrics) {
		ImageAnnotation[][] imageAnnotationsByStudyDate = RECISTCalculator
				.loadAndSortAIMFilesByStudyDate(imageAnnotations);
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
		lesionTrackingServiceAsync.downloadRECISTTableImage(cr,
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
		lesionTrackingServiceAsync.downloadRECISTChartImage(cr,
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
}