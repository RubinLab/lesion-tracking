package edu.stanford.isis.epad.plugin.lesiontracking.client.chart;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.visualization.client.visualizations.corechart.ComboChart.Options;

public class OptionsWithVAxes extends Options
{
    protected OptionsWithVAxes(){}
    public static OptionsWithVAxes create()
    {
          return JavaScriptObject.createObject().cast();
    }

    public final native void setVAxes(int index, VAxes vAxesAtIndex)
    /*-{
        if (!this.vAxes)
        {
              this.vAxes = {};
        }

        this.vAxes[index] = vAxesAtIndex;
    }-*/;
}
