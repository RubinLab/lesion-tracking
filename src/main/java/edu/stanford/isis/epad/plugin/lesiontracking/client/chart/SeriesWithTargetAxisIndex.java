package edu.stanford.isis.epad.plugin.lesiontracking.client.chart;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.visualization.client.visualizations.corechart.Series;

public class SeriesWithTargetAxisIndex extends Series
{
    protected SeriesWithTargetAxisIndex(){ }
      public static SeriesWithTargetAxisIndex create() {
            return JavaScriptObject.createObject().cast();
          }

      public final native void setTargetAxisIndex(int targetAxisIndex) /*-{
        this.targetAxisIndex = targetAxisIndex;
      }-*/;
}
