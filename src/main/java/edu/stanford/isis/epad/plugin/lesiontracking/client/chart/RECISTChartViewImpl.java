package edu.stanford.isis.epad.plugin.lesiontracking.client.chart;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.visualizations.corechart.ComboChart;

public class RECISTChartViewImpl extends Composite
{
    public interface RECISTChartViewImplUiBinder extends UiBinder<Widget, RECISTChartViewImpl>{}
    private static final RECISTChartViewImplUiBinder uiBinder = GWT.create(RECISTChartViewImplUiBinder.class);

    private Widget widget;

    @UiField public HTMLPanel htmlPanel;

    public RECISTChartViewImpl()
    {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public void showChart(ComboChart comboChart)
    {
        if(widget != null) htmlPanel.remove(widget);
        htmlPanel.add(comboChart);
        widget = comboChart;
    }
}
