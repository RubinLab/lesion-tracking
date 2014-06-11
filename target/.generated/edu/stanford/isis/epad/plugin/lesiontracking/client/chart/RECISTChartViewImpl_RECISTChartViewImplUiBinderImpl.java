package edu.stanford.isis.epad.plugin.lesiontracking.client.chart;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.uibinder.client.UiBinderUtil;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiBinderUtil;
import com.google.gwt.user.client.ui.Widget;

public class RECISTChartViewImpl_RECISTChartViewImplUiBinderImpl implements UiBinder<com.google.gwt.user.client.ui.Widget, edu.stanford.isis.epad.plugin.lesiontracking.client.chart.RECISTChartViewImpl>, edu.stanford.isis.epad.plugin.lesiontracking.client.chart.RECISTChartViewImpl.RECISTChartViewImplUiBinder {

  interface Template extends SafeHtmlTemplates {
    @Template("")
    SafeHtml html1();
     
  }

  Template template = GWT.create(Template.class);


  public com.google.gwt.user.client.ui.Widget createAndBindUi(final edu.stanford.isis.epad.plugin.lesiontracking.client.chart.RECISTChartViewImpl owner) {


    return new Widgets(owner).get_htmlPanel();
  }

  /**
   * Encapsulates the access to all inner widgets
   */
  class Widgets {
    private final edu.stanford.isis.epad.plugin.lesiontracking.client.chart.RECISTChartViewImpl owner;


    public Widgets(final edu.stanford.isis.epad.plugin.lesiontracking.client.chart.RECISTChartViewImpl owner) {
      this.owner = owner;
    }

    SafeHtml template_html1() {
      return template.html1();
    }

    /**
     * Getter for clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay called 0 times. Type: GENERATED_BUNDLE. Build precedence: 1.
     */
    private edu.stanford.isis.epad.plugin.lesiontracking.client.chart.RECISTChartViewImpl_RECISTChartViewImplUiBinderImpl_GenBundle get_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay() {
      return build_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay();
    }
    private edu.stanford.isis.epad.plugin.lesiontracking.client.chart.RECISTChartViewImpl_RECISTChartViewImplUiBinderImpl_GenBundle build_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay() {
      // Creation section.
      final edu.stanford.isis.epad.plugin.lesiontracking.client.chart.RECISTChartViewImpl_RECISTChartViewImplUiBinderImpl_GenBundle clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay = (edu.stanford.isis.epad.plugin.lesiontracking.client.chart.RECISTChartViewImpl_RECISTChartViewImplUiBinderImpl_GenBundle) GWT.create(edu.stanford.isis.epad.plugin.lesiontracking.client.chart.RECISTChartViewImpl_RECISTChartViewImplUiBinderImpl_GenBundle.class);
      // Setup section.


      return clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay;
    }

    /**
     * Getter for htmlPanel called 1 times. Type: DEFAULT. Build precedence: 1.
     */
    private com.google.gwt.user.client.ui.HTMLPanel get_htmlPanel() {
      return build_htmlPanel();
    }
    private com.google.gwt.user.client.ui.HTMLPanel build_htmlPanel() {
      // Creation section.
      final com.google.gwt.user.client.ui.HTMLPanel htmlPanel = new com.google.gwt.user.client.ui.HTMLPanel(template_html1().asString());
      // Setup section.


      owner.htmlPanel = htmlPanel;

      return htmlPanel;
    }
  }
}
