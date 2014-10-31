package edu.stanford.isis.epad.plugin.lesiontracking.client;

import edu.stanford.isis.epad.plugin.lesiontracking.client.chart.RECISTChartViewImpl;
import edu.stanford.isis.epad.plugin.lesiontracking.client.recist.Lesion;
import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.UploadedInfo;
import gwtupload.client.MultiUploader;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.gargoylesoftware.htmlunit.javascript.host.Window;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class LesionTrackingViewImpl extends Composite {
	public interface LesionTrackingViewImplUiBinder extends
			UiBinder<Widget, LesionTrackingViewImpl> {
	}

	private static final LesionTrackingViewImplUiBinder uiBinder = GWT
			.create(LesionTrackingViewImplUiBinder.class);

	private static final Logger logger = Logger
			.getLogger("LesionTrackingViewImpl");

	private LesionTracking lesionTracking;

	@UiField(provided = true)
	public ListBox patientNamesListBox = new ListBox(),
	// studiesListBox = new ListBox(false),
	// seriesListBox = new ListBox(false),
			annotationsListBox = new ListBox(), metricsListBox = new ListBox();

	//@UiField public FlexTable recistFlexTable, radiologyImagesFlexTable, recistChartsFlexTable;
	
	//@UiField public Button downloadRECISTTableAsImageButton, downloadRECISTChartAsImageButton, downloadWordDocumentButton;
	@UiField public Button downloadWordDocumentButton;
	@UiField public FlowPanel annotationUploadFlowPanel;
	@UiField public HTML recistHTML;

	@SuppressWarnings("deprecation")
	public LesionTrackingViewImpl(LesionTracking lesionTracking) {
		initWidget(uiBinder.createAndBindUi(this));
		this.lesionTracking = lesionTracking;
		// studiesListBox.setMultipleSelect(false);
		// seriesListBox.setMultipleSelect(false);
		annotationsListBox.setMultipleSelect(false);
		metricsListBox.setMultipleSelect(false);

		MultiUploader defaultUploader = new MultiUploader();
		annotationUploadFlowPanel.add(defaultUploader);
		defaultUploader.setServletPath("annotations");
		logger.info("Servlet Path: " + defaultUploader.getServletPath());

		defaultUploader
				.addOnFinishUploadHandler(new IUploader.OnFinishUploaderHandler() {
					public void onFinish(IUploader uploader) {
						if (uploader.getStatus() == Status.SUCCESS) {

							// The server sends useful information to the client
							// by default
							UploadedInfo info = uploader.getServerInfo();
							logger.info("File name " + info.name);
							logger.info("File content-type " + info.ctype);
							logger.info("File size " + info.size);

							// You can send any customized message and parse it
							logger.info("Server response "
									+ uploader.getServerResponse());
						}
					}
				});
	}

	@UiHandler("patientNamesListBox")
	public void onPatientNameSelected(ChangeEvent changeEvent) {

		int selectedIndex = patientNamesListBox.getSelectedIndex();
		String itemText = patientNamesListBox.getItemText(selectedIndex);
		String value = patientNamesListBox.getValue(selectedIndex);

		if (selectedIndex != -1)
			lesionTracking.onPatientNameSelected(patientNamesListBox
					.getValue(selectedIndex));
	}

	/*
	 * @UiHandler("studiesListBox") public void onStudySelected(ChangeEvent
	 * changeEvent) { int selectedIndex = studiesListBox.getSelectedIndex();
	 * if(selectedIndex != -1)
	 * lesionTracking.onStudySelected(studiesListBox.getItemText
	 * (selectedIndex)); }
	 */
	/*
	 * @UiHandler("seriesListBox") public void onSeriesSelected(ChangeEvent
	 * changeEvent) { int selectedIndex = seriesListBox.getSelectedIndex();
	 * if(selectedIndex != -1)
	 * lesionTracking.onSeriesSelected(seriesListBox.getItemText
	 * (selectedIndex)); }
	 */

	@UiHandler("metricsListBox")
	public void onMetricSelected(ChangeEvent changeEvent) {

		downloadWordDocumentButton.setVisible(true);
		/*
		downloadRECISTTableAsImageButton.setVisible(true);
		downloadRECISTChartAsImageButton.setVisible(true);
		*/
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

	/*
	@UiHandler("downloadRECISTTableAsImageButton")
	public void onDownloadAsRECISTTableButtonClicked(ClickEvent clickEvent) {
		lesionTracking.onDownloadAsRECISTTableButtonClicked();
	}

	@UiHandler("downloadRECISTChartAsImageButton")
	public void onDownloadAsRECISTChartButtonClicked(ClickEvent clickEvent) {
		lesionTracking.onDownloadAsRECISTTableButtonClicked();
	}
	*/
	@UiHandler("downloadWordDocumentButton")
	public void onDownloadWordDocumentButtonClicked(ClickEvent clickEvent) {
		lesionTracking.onDownloadWordDocumentButtonClicked();
	}
	
	public void setLabelText(String labelText) {
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	public void loadPatientNames(List<String> patientNames) {
		patientNamesListBox.clear();
		for (String patientName : patientNames) {
			String aux[] = patientName.split("\\$");
			patientNamesListBox.addItem(aux[0], aux[1]);
		}
	}

	/*
	 * public void loadStudiesList(List<String> studies) {
	 * studiesListBox.clear(); for(String study : studies)
	 * studiesListBox.addItem(study); }
	 */
	/*
	 * public void loadSeriesList(List<String> seriess) { seriesListBox.clear();
	 * for(String series : seriess) seriesListBox.addItem(series); }
	 */

	public void loadAnnotationsList(List<String> annotations) {
		annotationsListBox.clear();
		//radiologyImagesFlexTable.clear();
		for (String annotation : annotations)
			annotationsListBox.addItem(annotation);
	}

	public void loadMetricsList(List<String> metrics) {
		metricsListBox.clear();
		
		/*
		recistFlexTable.clear();
		recistChartsFlexTable.clear();
		downloadRECISTTableAsImageButton.setVisible(false);
		downloadRECISTChartAsImageButton.setVisible(false);
		*/
		
		//radiologyImagesFlexTable.clear();
		for (String metric : metrics)
			metricsListBox.addItem(metric);
	}

	public void loadRECISTTable(String[][] recistTable) {
		/*
		recistFlexTable.removeAllRows();
		for (String[] rows : recistTable) {
			int rowIndex = recistFlexTable.insertRow(recistFlexTable
					.getRowCount());
			for (int colIndex = 0; colIndex < rows.length; colIndex++) {
				String col = rows[colIndex];
				recistFlexTable.setWidget(rowIndex, colIndex, new Label(col));
			}
		}
		*/
	}

	public void setNumberTimePointsInStudy(String metricUnits,
			int numberOfTimePoints) {
		/*
		recistFlexTable.removeAllRows();
		recistFlexTable.insertRow(recistFlexTable.getRowCount());

		recistFlexTable.setWidget(0, 0, new RECISTTableTitleRowLabel(
				"Lesion ID"));
		recistFlexTable.setWidget(0, 1,
				new RECISTTableTitleRowLabel("Location"));
		recistFlexTable.setWidget(0, 2, new RECISTTableTitleRowLabel(
				"Baseline (" + metricUnits + ")"));

		for (int i = 0; i < numberOfTimePoints - 1; i++)
			recistFlexTable.setWidget(0, 3 + i, new RECISTTableTitleRowLabel(
					"Follow Up " + (i + 1) + " (" + metricUnits + ")"));
		*/
	}

	public void addLesion(Lesion lesion) {
		/*
		int row = recistFlexTable.insertRow(recistFlexTable.getRowCount());

		Label lesionIDLabel = new RECISTTableVariableEntryLabel(
				lesion.getLesionID(), row);
		Label lesionLocationLabel = new RECISTTableVariableEntryLabel(
				lesion.getLocation(), row);

		lesionIDLabel.addClickHandler(new LesionIDClickHandler(lesion));
		lesionLocationLabel.addClickHandler(new LesionIDClickHandler(lesion));

		recistFlexTable.setWidget(row, 0, lesionIDLabel);
		recistFlexTable.setWidget(row, 1, lesionLocationLabel);

		List<String> metrics = lesion.getMetrics();

		if (metrics.size() < 0)
			return;

		for (int i = 0; i < lesion.getNumberOfTemporalMeasurements(); i++) {
			RECISTTableVariableEntryLabel label = new RECISTTableVariableEntryLabel(
					lesion.getMeasurementsForMetric(metrics.get(0)).get(i), row);
			label.addClickHandler(new LesionClickHandler(lesion, i));
			recistFlexTable.setWidget(recistFlexTable.getRowCount() - 1, i + 2,
					label);
		}
		*/
	}

	public void addResponseRows(String metric, String metricUnits,
			String[] metricSums, String[] responseRates,
			String[] responseRatesSinceBaseline,
			String[] responseRatesSinceNadir, String[] responseCategories) {
		/*
		int metricNameRow = recistFlexTable.insertRow(recistFlexTable
				.getRowCount());
		int sumOfMetricsRow = recistFlexTable.insertRow(recistFlexTable
				.getRowCount());
		// int responseRateRow =
		// recistFlexTable.insertRow(recistFlexTable.getRowCount());
		int responseRateSinceBaselineRow = recistFlexTable
				.insertRow(recistFlexTable.getRowCount());
		int responseRateSinceNadirRow = recistFlexTable
				.insertRow(recistFlexTable.getRowCount());
		int responseCategoryRow = recistFlexTable.insertRow(recistFlexTable
				.getRowCount());

		// Metric name row.
		recistFlexTable.setWidget(metricNameRow, 0,
				new RECISTTableTitleRowLabel(metric));
		recistFlexTable.getFlexCellFormatter().setColSpan(metricNameRow, 0, 5);

		// The sum of metrics row.
		recistFlexTable.setWidget(sumOfMetricsRow, 0,
				new RECISTTableResponseRowLabel("Sum of " + metric + "s ("
						+ metricUnits + ")"));
		recistFlexTable.getFlexCellFormatter()
				.setColSpan(sumOfMetricsRow, 0, 2);

		for (int i = 0; i < metricSums.length; i++)
			recistFlexTable.setWidget(sumOfMetricsRow, i + 1,
					new RECISTTableTitleRowLabel(metricSums[i]));

		// The response rate row title.
		// recistFlexTable.setWidget(responseRateRow, 0, new
		// RECISTTableResponseRowLabel("Response Rate (%)"));
		// recistFlexTable.getFlexCellFormatter().setColSpan(responseRateRow, 0,
		// 2);

		// The response rate since baseline title.
		recistFlexTable.setWidget(responseRateSinceBaselineRow, 0,
				new RECISTTableResponseRowLabel("Change from Baseline (%)"));
		recistFlexTable.getFlexCellFormatter().setColSpan(
				responseRateSinceBaselineRow, 0, 2);

		// The response rate since nadir title.
		recistFlexTable.setWidget(responseRateSinceNadirRow, 0,
				new RECISTTableResponseRowLabel("Change from Nadir (%)"));
		recistFlexTable.getFlexCellFormatter().setColSpan(
				responseRateSinceNadirRow, 0, 2);

		// The response category row title.
		recistFlexTable.setWidget(responseCategoryRow, 0,
				new RECISTTableResponseRowLabel(
						"Target Lesion Response Category"));
		recistFlexTable.getFlexCellFormatter().setColSpan(responseCategoryRow,
				0, 2);

		for (int i = 1; i < responseRates.length; i++) {
			// recistFlexTable.setWidget(responseRateRow, i + 1, new
			// RECISTTableTitleRowLabel(responseRates[i]));
			recistFlexTable
					.setWidget(responseRateSinceBaselineRow, i + 1,
							new RECISTTableTitleRowLabel(
									responseRatesSinceBaseline[i]));
			recistFlexTable.setWidget(responseRateSinceNadirRow, i + 1,
					new RECISTTableTitleRowLabel(responseRatesSinceNadir[i]));
			recistFlexTable.setWidget(responseCategoryRow, i + 1,
					new RECISTTableTitleRowLabel(responseCategories[i]));
		}
		*/
	}

	private class RECISTTableTitleRowLabel extends Label {
		public RECISTTableTitleRowLabel(String text) {
			super(text);
			setStyleName("recistTableConstantLabels");
		}
	}

	private class RECISTTableResponseRowLabel extends Label {
		public RECISTTableResponseRowLabel(String text) {
			super(text);
			setStyleName("recistTableResponseLabels");
		}
	}

	private class RECISTTableVariableEntryLabel extends Label {
		private int row;

		public RECISTTableVariableEntryLabel(String text, int row) {
			super(text);

			this.row = row;
			setStyleName("recistTableVariableEntries-unselected");
		}

		public void setSelected(boolean selected) {
			if (selected)
				setStyleName("recistTableVariableEntries-selected");
			else
				setStyleName("recistTableVariableEntries-unselected");
		}

		public int getRow() {
			return row;
		}
	}

	private class LesionIDClickHandler implements ClickHandler {
		private Lesion lesion;

		public LesionIDClickHandler(Lesion lesion) {
			this.lesion = lesion;
		}

		@Override
		public void onClick(ClickEvent clickEvent) {
			RECISTTableVariableEntryLabel recistTableVariableEntryLabel = (RECISTTableVariableEntryLabel) clickEvent
					.getSource();

			/*
			int row = recistTableVariableEntryLabel.getRow();

			for (int i = 1; i < recistFlexTable.getRowCount() - 2; i++)
				for (int j = 2; j < recistFlexTable.getCellCount(i); j++) {
					Widget label = recistFlexTable.getWidget(i, j);

					if (label instanceof RECISTTableVariableEntryLabel) {
						recistTableVariableEntryLabel = (RECISTTableVariableEntryLabel) label;
						if (recistTableVariableEntryLabel != null)
							recistTableVariableEntryLabel.setSelected(i == row);
					}
				}

			int numWADOURLS = lesion.getNumberOfWADOURLs();
			String[] wadoURLs = new String[numWADOURLS];

			for (int i = 0; i < numWADOURLS; i++)
				wadoURLs[i] = lesion.getWADOURL(i);

			displayRadiologyImages(wadoURLs);
			*/
		}
	}

	private class LesionClickHandler implements ClickHandler {
		private Lesion lesion;
		private int temporalIndex;

		public LesionClickHandler(Lesion lesion, int temporalIndex) {
			this.lesion = lesion;
			this.temporalIndex = temporalIndex;
		}

		@Override
		public void onClick(ClickEvent clickEvent) {
			RECISTTableVariableEntryLabel recistTableVariableEntryLabel = (RECISTTableVariableEntryLabel) clickEvent
					.getSource();
			/*
			int row = recistTableVariableEntryLabel.getRow();

			for (int i = 1; i < recistFlexTable.getRowCount() - 2; i++)
				for (int j = 2; j < recistFlexTable.getCellCount(i); j++) {
					Widget label = recistFlexTable.getWidget(i, j);

					if (label instanceof RECISTTableVariableEntryLabel) {
						recistTableVariableEntryLabel = (RECISTTableVariableEntryLabel) label;
						if (recistTableVariableEntryLabel != null)
							recistTableVariableEntryLabel.setSelected(i == row
									&& j == temporalIndex + 2);
					}
				}

			String wadoURL = lesion.getWADOURL(temporalIndex);
			displayRadiologyImages(new String[] { wadoURL });
			*/
		}
	}

	public void displayRadiologyImages(String[] radiologyImageURLs) {
		
		/*
		radiologyImagesFlexTable.removeAllRows();
		radiologyImagesFlexTable.insertRow(radiologyImagesFlexTable
				.getRowCount());
		int colIndex = 0;
		for (String radiologyImageURL : radiologyImageURLs) {
			Image image = new Image(radiologyImageURL);
			image.setStyleName("radiologyImage");
			radiologyImagesFlexTable.setWidget(0, colIndex, image);
			colIndex++;
		}
		*/
	}

	public void showRECISTCharts(List<RECISTChartViewImpl> recistChartViewImpls) {
		/*
		recistChartsFlexTable.removeAllRows();
		recistChartsFlexTable.insertRow(recistChartsFlexTable.getRowCount());
		int rowIndex = 0;
		for (RECISTChartViewImpl recistChartViewImpl : recistChartViewImpls) {
			recistChartsFlexTable.setWidget(rowIndex, 0, recistChartViewImpl);
			rowIndex++;
		}
		*/
	}

	public void showRECISTHTML(String recistHTML) {
		recistHTML = recistHTML.replaceAll("<html>", "").replaceAll("</html>", "");
		recistHTML = recistHTML.replaceAll("<body>", "").replaceAll("</body>", "");
		this.recistHTML.setHTML(recistHTML);
	}
}
