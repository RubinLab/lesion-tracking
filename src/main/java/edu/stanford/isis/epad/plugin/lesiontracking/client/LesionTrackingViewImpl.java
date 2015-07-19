package edu.stanford.isis.epad.plugin.lesiontracking.client;

import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.UploadedInfo;
import gwtupload.client.MultiUploader;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class LesionTrackingViewImpl extends Composite {
	public interface LesionTrackingViewImplUiBinder extends UiBinder<Widget, LesionTrackingViewImpl> {}

	private static final LesionTrackingViewImplUiBinder uiBinder = GWT.create(LesionTrackingViewImplUiBinder.class);
	private static final Logger logger = Logger.getLogger("LesionTrackingViewImpl");

	private LesionTracking lesionTracking;

	@UiField(provided = true)
	public ListBox patientNamesListBox = new ListBox(),
				   //annotationsListBox = new ListBox(),
				   metricsListBox = new ListBox();

	@UiField public Button downloadWordDocumentButton;
	@UiField public FlowPanel annotationUploadFlowPanel;
	@UiField public HTML recistHTML;

	public LesionTrackingViewImpl(){}
	
	@SuppressWarnings("deprecation")
	public LesionTrackingViewImpl(LesionTracking lesionTracking) {
		initWidget(uiBinder.createAndBindUi(this));
		this.lesionTracking = lesionTracking;
		//annotationsListBox.setMultipleSelect(false);
		metricsListBox.setMultipleSelect(false);

		MultiUploader defaultUploader = new MultiUploader();
		annotationUploadFlowPanel.add(defaultUploader);
		defaultUploader.setServletPath("annotations");
		logger.info("Servlet Path: " + defaultUploader.getServletPath());

		defaultUploader
				.addOnFinishUploadHandler(new IUploader.OnFinishUploaderHandler() {
					public void onFinish(IUploader uploader) {
						if (uploader.getStatus() == Status.SUCCESS) {

							// The server sends useful information to the client by default
							UploadedInfo info = uploader.getServerInfo();
							logger.info("File name " + info.name);
							logger.info("File content-type " + info.ctype);
							logger.info("File size " + info.size);

							// You can send any customized message and parse it
							logger.info("Server response " + uploader.getServerResponse());
						}
					}
				});
	}

	@UiHandler("patientNamesListBox")
	public void onPatientNameSelected(ChangeEvent changeEvent) {

		int selectedIndex = patientNamesListBox.getSelectedIndex();

		if (selectedIndex != -1)
			lesionTracking.onPatientNameSelected(patientNamesListBox.getValue(selectedIndex));
	}
	
	@UiHandler("metricsListBox")
	public void onMetricSelected(ChangeEvent changeEvent) {

		downloadWordDocumentButton.setVisible(false);

		int selectedIndex = metricsListBox.getSelectedIndex();
		if (selectedIndex != -1) {
			List<String> selectedMetrics = new ArrayList<String>();
			int numberOfItems = metricsListBox.getItemCount();
			for (int i = 0; i < numberOfItems; i++)
				if (metricsListBox.isItemSelected(i))
					selectedMetrics.add(metricsListBox.getItemText(i));
			lesionTracking.onMetricsSelected(selectedMetrics);
		}
		
	}

	@UiHandler("downloadWordDocumentButton")
	public void onDownloadWordDocumentButtonClicked(ClickEvent clickEvent) {
		lesionTracking.onDownloadWordDocumentButtonClicked();
	}

	@Override public Widget asWidget() { return this; }

	public void loadPatientNames(List<String> patientNames, int selectedPatientIndex) {
		recistHTML.setHTML("");
		//annotationsListBox.clear();
		metricsListBox.clear();
		patientNamesListBox.clear();
		for (String patientName : patientNames) {
			String aux[] = patientName.split("\\$");
			patientNamesListBox.addItem(aux[0], aux[1]);
		}
		
		if(selectedPatientIndex != -1)
		{
			patientNamesListBox.setSelectedIndex(selectedPatientIndex);
			onPatientNameSelected(null);
			return;
		}
		
		if(patientNames.size() > 0)
		{
			patientNamesListBox.setSelectedIndex(0);
			onPatientNameSelected(null);
		}
	}
	
	public void loadAnnotationsList(List<String> annotations) {
		//annotationsListBox.clear();

		//for (String annotation : annotations)
		//	annotationsListBox.addItem(annotation);
	}

	public void loadMetricsList(List<String> metrics) {
		metricsListBox.clear();
		for (String metric : metrics)
			metricsListBox.addItem(metric);
		
		if(metrics.size() > 0) // Automatically select the first metric.
		{
			metricsListBox.setSelectedIndex(0);
			onMetricSelected(null);
		}
	}
	
	public void showRECISTHTML(String recistHTML) {
		recistHTML = recistHTML.replaceAll("<html>", "").replaceAll("</html>", "");
		recistHTML = recistHTML.replaceAll("<body>", "").replaceAll("</body>", "");
		this.recistHTML.setHTML(recistHTML);

		downloadWordDocumentButton.setVisible(true);

	}
}
