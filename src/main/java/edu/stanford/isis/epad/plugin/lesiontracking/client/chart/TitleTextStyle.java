package edu.stanford.isis.epad.plugin.lesiontracking.client.chart;

import com.google.gwt.ajaxloader.client.Properties;
import com.google.gwt.core.client.JavaScriptObject;

public class TitleTextStyle extends Properties {

  protected TitleTextStyle() {}
  public static TitleTextStyle create() {
    return JavaScriptObject.createObject().cast();
  }

  public final native void setColor(String color) /*-{
    this.color = color;
  }-*/;

  public final native void setFontName(String fontName) /*-{
    this.fontName = fontName;
  }-*/;

  public final native void setFontSize(int fontSize) /*-{
    this.fontSize = fontSize;
  }-*/;
}
