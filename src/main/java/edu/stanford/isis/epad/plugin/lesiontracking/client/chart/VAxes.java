package edu.stanford.isis.epad.plugin.lesiontracking.client.chart;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.visualization.client.visualizations.corechart.TextStyle;

public class VAxes extends JavaScriptObject
{
    protected VAxes()
    {
    }

    public static VAxes create()
    {
        return JavaScriptObject.createObject().cast();
    }

    public final native void setTitle(String title)
    /*-{
        this.title = title;
    }-*/;

    public final native void setTextStyle(TextStyle textStyle)
    /*-{
        this.textStyle = textStyle;
    }-*/;

    public final native void setTitleTextStyle(TitleTextStyle titleTextStyle)
    /*-{
        this.titleTextStyle = titleTextStyle;
    }-*/;
}
