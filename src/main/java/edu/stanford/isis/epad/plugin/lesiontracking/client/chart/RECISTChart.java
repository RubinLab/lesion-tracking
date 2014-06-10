package edu.stanford.isis.epad.plugin.lesiontracking.client.chart;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.visualization.client.ChartArea;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.LegendPosition;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.visualizations.corechart.AxisOptions;
import com.google.gwt.visualization.client.visualizations.corechart.ComboChart;
import com.google.gwt.visualization.client.visualizations.corechart.TextStyle;
import com.google.gwt.visualization.client.visualizations.corechart.Series.Type;

public class RECISTChart
{
    private static final int CHART_WIDTH = 500;
    private RECISTChartViewImpl recistChartViewImpl;

    private OptionsWithVAxes options = OptionsWithVAxes.create();
    private ComboChart comboChart;
    private VAxes vAxes0;//, vAxes1;

    public RECISTChart(RECISTChartViewImpl recistChartViewImpl)
    {
        this.recistChartViewImpl = recistChartViewImpl;

        options.setBackgroundColor("none");
        options.setWidth(CHART_WIDTH);
        options.setLegend(LegendPosition.NONE);
        options.setHeight(300);

        ChartArea chartArea = ChartArea.create();
        chartArea.setWidth(CHART_WIDTH - 150);
        chartArea.setHeight(200);
        options.setChartArea(chartArea);

        SeriesWithTargetAxisIndex series0 = SeriesWithTargetAxisIndex.create();
//        SeriesWithTargetAxisIndex series1 = SeriesWithTargetAxisIndex.create();

        series0.setTargetAxisIndex(0);
//        series1.setTargetAxisIndex(1);

        series0.setColor("#990000");
//        series1.setColor("#0F4D92");

        series0.setType(Type.AREA);
//        series1.setType(Type.AREA);

        options.setSeries(0, series0);
//        options.setSeries(1, series1);

        vAxes0 = VAxes.create();
//        vAxes1 = VAxes.create();

        TextStyle textStyle0 = TextStyle.create();
//        TextStyle textStyle1 = TextStyle.create();

        TitleTextStyle titleTextStyle0 = TitleTextStyle.create();
//        TitleTextStyle titleTextStyle1 = TitleTextStyle.create();

        textStyle0.setColor("#990000");
//        textStyle1.setColor("#0F4D92");

        titleTextStyle0.setColor("#990000");
//        titleTextStyle1.setColor("#0F4D92");

        vAxes0.setTextStyle(textStyle0);
//        vAxes1.setTextStyle(textStyle1);

        vAxes0.setTitleTextStyle(titleTextStyle0);
//        vAxes1.setTitleTextStyle(titleTextStyle1);

        AxisOptions hAxisOptions = AxisOptions.create();
        hAxisOptions.setTitle("Study Date");

        options.setVAxes(0, vAxes0);
//        options.setVAxes(1, vAxes1);

        options.setHAxisOptions(hAxisOptions);
    }

    public void drawRECISTChart(final String[] studyDates, final String metricName, final String[] metricSums)
    {
        VisualizationUtils.loadVisualizationApi(new Runnable()
        {
            public void run()
            {
                // Create DataTable representation of the data
                DataTable data = DataTable.create();
                data.addColumn(ColumnType.DATE, "Study Date");
                data.addColumn(ColumnType.NUMBER, metricName);
//                data.addColumn(ColumnType.NUMBER, metricNames.length > 1 ? metricNames[1] : "");

                vAxes0.setTitle(metricName);
//                vAxes1.setTitle(metricNames.length > 1 ? metricNames[1] : "");

                DateTimeFormat dtf = DateTimeFormat.getFormat("yyyy-MM-dd");

                for(int studyDate = 0; studyDate < studyDates.length; studyDate++)
                {
                    int row = data.addRow();
                    data.setValue(row, 0, dtf.parse(studyDates[studyDate]));
                    String metricValue = metricSums[studyDate].replaceAll(",", "").trim();
                    data.setValue(row, 1, Double.parseDouble(metricValue));
/*
                    if(metricSums.length > 1)
                    {

                        String secondMetricValue = metricSums[1][studyDate].replaceAll(",", "").trim();
                        data.setValue(row, 2, Double.parseDouble(secondMetricValue));
                    }
*/
                    row++;
                }

                comboChart = new ComboChart(data, options);
                recistChartViewImpl.showChart(comboChart);
            }
        }, ComboChart.PACKAGE);
    }
}