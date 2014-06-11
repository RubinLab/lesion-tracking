package edu.stanford.isis.epad.plugin.lesiontracking.client;

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

public class LesionTrackingViewImpl_LesionTrackingViewImplUiBinderImpl implements UiBinder<com.google.gwt.user.client.ui.Widget, edu.stanford.isis.epad.plugin.lesiontracking.client.LesionTrackingViewImpl>, edu.stanford.isis.epad.plugin.lesiontracking.client.LesionTrackingViewImpl.LesionTrackingViewImplUiBinder {

  interface Template extends SafeHtmlTemplates {
    @Template("Download as Image")
    SafeHtml html1();
     
    @Template("Download as Image")
    SafeHtml html2();
     
    @Template("<div class='{0}'> <div class='{1}'> <span class='{2}'>Select Patient:</span> <span id='{3}'></span>   <span class='{4}'>List of Annotations:</span> <span id='{5}'></span> <span class='{6}'>Select Metric(s):</span> <span id='{7}'></span> </div> <div class='{8}'> <div class='{9}'> <span class='{10}'>RECIST Table</span> <div><span id='{11}'></span></div>  <span id='{12}'></span>  </div> <div class='{13}'> <div class='{14}'> <span class='{15}'>RECIST Chart</span> <div><span id='{16}'></span></div>  <span id='{17}'></span>  </div> </div> </div> <div class='{18}'> <div class='{19}'> <span class='{20}'>Radiology Images</span> <span id='{21}'></span> </div> </div> </div>")
    SafeHtml html3(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9, String arg10, String arg11, String arg12, String arg13, String arg14, String arg15, String arg16, String arg17, String arg18, String arg19, String arg20, String arg21);
     
    @Template("<div class='{0}'> <div class='{1}'> <span class='{2}'>Upload Annotation File in XML format</span> <span id='{3}'></span> </div> </div>")
    SafeHtml html4(String arg0, String arg1, String arg2, String arg3);
     
    @Template("<span id='{0}'></span> <span id='{1}'></span>")
    SafeHtml html5(String arg0, String arg1);
     
  }

  Template template = GWT.create(Template.class);


  public com.google.gwt.user.client.ui.Widget createAndBindUi(final edu.stanford.isis.epad.plugin.lesiontracking.client.LesionTrackingViewImpl owner) {


    return new Widgets(owner).get_f_HTMLPanel1();
  }

  /**
   * Encapsulates the access to all inner widgets
   */
  class Widgets {
    private final edu.stanford.isis.epad.plugin.lesiontracking.client.LesionTrackingViewImpl owner;


    final com.google.gwt.event.dom.client.ChangeHandler handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames1 = new com.google.gwt.event.dom.client.ChangeHandler() {
      public void onChange(com.google.gwt.event.dom.client.ChangeEvent event) {
        owner.onPatientNameSelected(event);
      }
    };

    final com.google.gwt.event.dom.client.ChangeHandler handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames2 = new com.google.gwt.event.dom.client.ChangeHandler() {
      public void onChange(com.google.gwt.event.dom.client.ChangeEvent event) {
        owner.onMetricSelected(event);
      }
    };

    final com.google.gwt.event.dom.client.ClickHandler handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames3 = new com.google.gwt.event.dom.client.ClickHandler() {
      public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
        owner.onDownloadAsRECISTTableButtonClicked(event);
      }
    };

    final com.google.gwt.event.dom.client.ClickHandler handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames4 = new com.google.gwt.event.dom.client.ClickHandler() {
      public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
        owner.onDownloadAsRECISTChartButtonClicked(event);
      }
    };

    public Widgets(final edu.stanford.isis.epad.plugin.lesiontracking.client.LesionTrackingViewImpl owner) {
      this.owner = owner;
      build_style();  // generated css resource must be always created. Type: GENERATED_CSS. Precedence: 1
      build_domId1();  // more than one getter call detected. Type: DOM_ID_HOLDER, precedence: 3
      build_domId2();  // more than one getter call detected. Type: DOM_ID_HOLDER, precedence: 3
      build_domId3();  // more than one getter call detected. Type: DOM_ID_HOLDER, precedence: 3
      build_domId4();  // more than one getter call detected. Type: DOM_ID_HOLDER, precedence: 3
      build_domId5();  // more than one getter call detected. Type: DOM_ID_HOLDER, precedence: 3
      build_domId6();  // more than one getter call detected. Type: DOM_ID_HOLDER, precedence: 3
      build_domId7();  // more than one getter call detected. Type: DOM_ID_HOLDER, precedence: 3
      build_domId8();  // more than one getter call detected. Type: DOM_ID_HOLDER, precedence: 3
      build_domId10();  // more than one getter call detected. Type: DOM_ID_HOLDER, precedence: 3
      build_domId0();  // more than one getter call detected. Type: DOM_ID_HOLDER, precedence: 2
      build_domId9();  // more than one getter call detected. Type: DOM_ID_HOLDER, precedence: 2
      build_domId1Element();  // more than one getter call detected. Type: DEFAULT, precedence: 3
      build_domId2Element();  // more than one getter call detected. Type: DEFAULT, precedence: 3
      build_domId3Element();  // more than one getter call detected. Type: DEFAULT, precedence: 3
      build_domId4Element();  // more than one getter call detected. Type: DEFAULT, precedence: 3
      build_domId5Element();  // more than one getter call detected. Type: DEFAULT, precedence: 3
      build_domId6Element();  // more than one getter call detected. Type: DEFAULT, precedence: 3
      build_domId7Element();  // more than one getter call detected. Type: DEFAULT, precedence: 3
      build_domId8Element();  // more than one getter call detected. Type: DEFAULT, precedence: 3
      build_domId10Element();  // more than one getter call detected. Type: DEFAULT, precedence: 3
      build_domId0Element();  // more than one getter call detected. Type: DEFAULT, precedence: 2
      build_domId9Element();  // more than one getter call detected. Type: DEFAULT, precedence: 2
    }

    SafeHtml template_html1() {
      return template.html1();
    }
    SafeHtml template_html2() {
      return template.html2();
    }
    SafeHtml template_html3() {
      return template.html3("" + get_style().containingBox() + "", "" + get_style().fL() + " " + get_style().innerColumnLeft() + "", "" + get_style().label() + "", get_domId1(), "" + get_style().label() + "", get_domId2(), "" + get_style().label() + "", get_domId3(), "" + get_style().fR() + " " + get_style().innerColumnRight() + "", "" + get_style().fL() + " " + get_style().innerColumnOfRightColumn() + "", "" + get_style().label() + "", get_domId4(), get_domId5(), "" + get_style().fR() + " " + get_style().innerColumnOfRightColumn() + "", "" + get_style().fL() + " " + get_style().recistChart() + "", "" + get_style().label() + "", get_domId6(), get_domId7(), "" + get_style().fR() + " " + get_style().innerColumnRight() + "", "" + get_style().radiologyImagesDiv() + "", "" + get_style().label() + "", get_domId8());
    }
    SafeHtml template_html4() {
      return template.html4("" + get_style().containingBox() + "", "" + get_style().fL() + "", "" + get_style().label() + "", get_domId10());
    }
    SafeHtml template_html5() {
      return template.html5(get_domId0(), get_domId9());
    }

    /**
     * Getter for clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay called 1 times. Type: GENERATED_BUNDLE. Build precedence: 1.
     */
    private edu.stanford.isis.epad.plugin.lesiontracking.client.LesionTrackingViewImpl_LesionTrackingViewImplUiBinderImpl_GenBundle get_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay() {
      return build_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay();
    }
    private edu.stanford.isis.epad.plugin.lesiontracking.client.LesionTrackingViewImpl_LesionTrackingViewImplUiBinderImpl_GenBundle build_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay() {
      // Creation section.
      final edu.stanford.isis.epad.plugin.lesiontracking.client.LesionTrackingViewImpl_LesionTrackingViewImplUiBinderImpl_GenBundle clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay = (edu.stanford.isis.epad.plugin.lesiontracking.client.LesionTrackingViewImpl_LesionTrackingViewImplUiBinderImpl_GenBundle) GWT.create(edu.stanford.isis.epad.plugin.lesiontracking.client.LesionTrackingViewImpl_LesionTrackingViewImplUiBinderImpl_GenBundle.class);
      // Setup section.


      return clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay;
    }

    /**
     * Getter for style called 29 times. Type: GENERATED_CSS. Build precedence: 1.
     */
    private edu.stanford.isis.epad.plugin.lesiontracking.client.LesionTrackingViewImpl_LesionTrackingViewImplUiBinderImpl_GenCss_style style;
    private edu.stanford.isis.epad.plugin.lesiontracking.client.LesionTrackingViewImpl_LesionTrackingViewImplUiBinderImpl_GenCss_style get_style() {
      return style;
    }
    private edu.stanford.isis.epad.plugin.lesiontracking.client.LesionTrackingViewImpl_LesionTrackingViewImplUiBinderImpl_GenCss_style build_style() {
      // Creation section.
      style = get_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay().style();
      // Setup section.
      style.ensureInjected();


      return style;
    }

    /**
     * Getter for f_HTMLPanel1 called 1 times. Type: DEFAULT. Build precedence: 1.
     */
    private com.google.gwt.user.client.ui.HTMLPanel get_f_HTMLPanel1() {
      return build_f_HTMLPanel1();
    }
    private com.google.gwt.user.client.ui.HTMLPanel build_f_HTMLPanel1() {
      // Creation section.
      final com.google.gwt.user.client.ui.HTMLPanel f_HTMLPanel1 = new com.google.gwt.user.client.ui.HTMLPanel(template_html5().asString());
      // Setup section.

      // Attach section.
      UiBinderUtil.TempAttachment attachRecord0 = UiBinderUtil.attachToDom(f_HTMLPanel1.getElement());
      get_domId0Element().get();
      get_domId9Element().get();

      // Detach section.
      attachRecord0.detach();
      f_HTMLPanel1.addAndReplaceElement(get_f_HTMLPanel2(), get_domId0Element().get());
      f_HTMLPanel1.addAndReplaceElement(get_f_HTMLPanel3(), get_domId9Element().get());

      return f_HTMLPanel1;
    }

    /**
     * Getter for domId0 called 2 times. Type: DOM_ID_HOLDER. Build precedence: 2.
     */
    private java.lang.String domId0;
    private java.lang.String get_domId0() {
      return domId0;
    }
    private java.lang.String build_domId0() {
      // Creation section.
      domId0 = com.google.gwt.dom.client.Document.get().createUniqueId();
      // Setup section.


      return domId0;
    }

    /**
     * Getter for f_HTMLPanel2 called 1 times. Type: DEFAULT. Build precedence: 2.
     */
    private com.google.gwt.user.client.ui.HTMLPanel get_f_HTMLPanel2() {
      return build_f_HTMLPanel2();
    }
    private com.google.gwt.user.client.ui.HTMLPanel build_f_HTMLPanel2() {
      // Creation section.
      final com.google.gwt.user.client.ui.HTMLPanel f_HTMLPanel2 = new com.google.gwt.user.client.ui.HTMLPanel(template_html3().asString());
      // Setup section.
      f_HTMLPanel2.setStyleName("" + get_style().recistHTMLPanel() + "");

      // Attach section.
      UiBinderUtil.TempAttachment attachRecord1 = UiBinderUtil.attachToDom(f_HTMLPanel2.getElement());
      get_domId1Element().get();
      get_domId2Element().get();
      get_domId3Element().get();
      get_domId4Element().get();
      get_domId5Element().get();
      get_domId6Element().get();
      get_domId7Element().get();
      get_domId8Element().get();

      // Detach section.
      attachRecord1.detach();
      f_HTMLPanel2.addAndReplaceElement(get_patientNamesListBox(), get_domId1Element().get());
      f_HTMLPanel2.addAndReplaceElement(get_annotationsListBox(), get_domId2Element().get());
      f_HTMLPanel2.addAndReplaceElement(get_metricsListBox(), get_domId3Element().get());
      f_HTMLPanel2.addAndReplaceElement(get_downloadRECISTTableAsImageButton(), get_domId4Element().get());
      f_HTMLPanel2.addAndReplaceElement(get_recistFlexTable(), get_domId5Element().get());
      f_HTMLPanel2.addAndReplaceElement(get_downloadRECISTChartAsImageButton(), get_domId6Element().get());
      f_HTMLPanel2.addAndReplaceElement(get_recistChartsFlexTable(), get_domId7Element().get());
      f_HTMLPanel2.addAndReplaceElement(get_radiologyImagesFlexTable(), get_domId8Element().get());

      return f_HTMLPanel2;
    }

    /**
     * Getter for domId1 called 2 times. Type: DOM_ID_HOLDER. Build precedence: 3.
     */
    private java.lang.String domId1;
    private java.lang.String get_domId1() {
      return domId1;
    }
    private java.lang.String build_domId1() {
      // Creation section.
      domId1 = com.google.gwt.dom.client.Document.get().createUniqueId();
      // Setup section.


      return domId1;
    }

    /**
     * Getter for patientNamesListBox called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.ListBox get_patientNamesListBox() {
      return build_patientNamesListBox();
    }
    private com.google.gwt.user.client.ui.ListBox build_patientNamesListBox() {
      // Creation section.
      final com.google.gwt.user.client.ui.ListBox patientNamesListBox = owner.patientNamesListBox;
      assert patientNamesListBox != null : "UiField patientNamesListBox with 'provided = true' was null";
      // Setup section.
      patientNamesListBox.setStyleName("" + get_style().listBox() + "");
      patientNamesListBox.setMultipleSelect(false);
      patientNamesListBox.setVisibleItemCount(3);
      patientNamesListBox.addChangeHandler(handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames1);


      return patientNamesListBox;
    }

    /**
     * Getter for domId1Element called 2 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.uibinder.client.LazyDomElement domId1Element;
    private com.google.gwt.uibinder.client.LazyDomElement get_domId1Element() {
      return domId1Element;
    }
    private com.google.gwt.uibinder.client.LazyDomElement build_domId1Element() {
      // Creation section.
      domId1Element = new com.google.gwt.uibinder.client.LazyDomElement<Element>(get_domId1());
      // Setup section.


      return domId1Element;
    }

    /**
     * Getter for domId2 called 2 times. Type: DOM_ID_HOLDER. Build precedence: 3.
     */
    private java.lang.String domId2;
    private java.lang.String get_domId2() {
      return domId2;
    }
    private java.lang.String build_domId2() {
      // Creation section.
      domId2 = com.google.gwt.dom.client.Document.get().createUniqueId();
      // Setup section.


      return domId2;
    }

    /**
     * Getter for annotationsListBox called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.ListBox get_annotationsListBox() {
      return build_annotationsListBox();
    }
    private com.google.gwt.user.client.ui.ListBox build_annotationsListBox() {
      // Creation section.
      final com.google.gwt.user.client.ui.ListBox annotationsListBox = owner.annotationsListBox;
      assert annotationsListBox != null : "UiField annotationsListBox with 'provided = true' was null";
      // Setup section.
      annotationsListBox.setStyleName("" + get_style().listBox() + "");
      annotationsListBox.setEnabled(false);
      annotationsListBox.setMultipleSelect(false);
      annotationsListBox.setVisibleItemCount(3);


      return annotationsListBox;
    }

    /**
     * Getter for domId2Element called 2 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.uibinder.client.LazyDomElement domId2Element;
    private com.google.gwt.uibinder.client.LazyDomElement get_domId2Element() {
      return domId2Element;
    }
    private com.google.gwt.uibinder.client.LazyDomElement build_domId2Element() {
      // Creation section.
      domId2Element = new com.google.gwt.uibinder.client.LazyDomElement<Element>(get_domId2());
      // Setup section.


      return domId2Element;
    }

    /**
     * Getter for domId3 called 2 times. Type: DOM_ID_HOLDER. Build precedence: 3.
     */
    private java.lang.String domId3;
    private java.lang.String get_domId3() {
      return domId3;
    }
    private java.lang.String build_domId3() {
      // Creation section.
      domId3 = com.google.gwt.dom.client.Document.get().createUniqueId();
      // Setup section.


      return domId3;
    }

    /**
     * Getter for metricsListBox called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.ListBox get_metricsListBox() {
      return build_metricsListBox();
    }
    private com.google.gwt.user.client.ui.ListBox build_metricsListBox() {
      // Creation section.
      final com.google.gwt.user.client.ui.ListBox metricsListBox = owner.metricsListBox;
      assert metricsListBox != null : "UiField metricsListBox with 'provided = true' was null";
      // Setup section.
      metricsListBox.setStyleName("" + get_style().listBox() + "");
      metricsListBox.setMultipleSelect(true);
      metricsListBox.setVisibleItemCount(3);
      metricsListBox.addChangeHandler(handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames2);


      return metricsListBox;
    }

    /**
     * Getter for domId3Element called 2 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.uibinder.client.LazyDomElement domId3Element;
    private com.google.gwt.uibinder.client.LazyDomElement get_domId3Element() {
      return domId3Element;
    }
    private com.google.gwt.uibinder.client.LazyDomElement build_domId3Element() {
      // Creation section.
      domId3Element = new com.google.gwt.uibinder.client.LazyDomElement<Element>(get_domId3());
      // Setup section.


      return domId3Element;
    }

    /**
     * Getter for domId4 called 2 times. Type: DOM_ID_HOLDER. Build precedence: 3.
     */
    private java.lang.String domId4;
    private java.lang.String get_domId4() {
      return domId4;
    }
    private java.lang.String build_domId4() {
      // Creation section.
      domId4 = com.google.gwt.dom.client.Document.get().createUniqueId();
      // Setup section.


      return domId4;
    }

    /**
     * Getter for downloadRECISTTableAsImageButton called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Button get_downloadRECISTTableAsImageButton() {
      return build_downloadRECISTTableAsImageButton();
    }
    private com.google.gwt.user.client.ui.Button build_downloadRECISTTableAsImageButton() {
      // Creation section.
      final com.google.gwt.user.client.ui.Button downloadRECISTTableAsImageButton = (com.google.gwt.user.client.ui.Button) GWT.create(com.google.gwt.user.client.ui.Button.class);
      // Setup section.
      downloadRECISTTableAsImageButton.setHTML(template_html1().asString());
      downloadRECISTTableAsImageButton.setVisible(false);
      downloadRECISTTableAsImageButton.addClickHandler(handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames3);


      owner.downloadRECISTTableAsImageButton = downloadRECISTTableAsImageButton;

      return downloadRECISTTableAsImageButton;
    }

    /**
     * Getter for domId4Element called 2 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.uibinder.client.LazyDomElement domId4Element;
    private com.google.gwt.uibinder.client.LazyDomElement get_domId4Element() {
      return domId4Element;
    }
    private com.google.gwt.uibinder.client.LazyDomElement build_domId4Element() {
      // Creation section.
      domId4Element = new com.google.gwt.uibinder.client.LazyDomElement<Element>(get_domId4());
      // Setup section.


      return domId4Element;
    }

    /**
     * Getter for domId5 called 2 times. Type: DOM_ID_HOLDER. Build precedence: 3.
     */
    private java.lang.String domId5;
    private java.lang.String get_domId5() {
      return domId5;
    }
    private java.lang.String build_domId5() {
      // Creation section.
      domId5 = com.google.gwt.dom.client.Document.get().createUniqueId();
      // Setup section.


      return domId5;
    }

    /**
     * Getter for recistFlexTable called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.FlexTable get_recistFlexTable() {
      return build_recistFlexTable();
    }
    private com.google.gwt.user.client.ui.FlexTable build_recistFlexTable() {
      // Creation section.
      final com.google.gwt.user.client.ui.FlexTable recistFlexTable = (com.google.gwt.user.client.ui.FlexTable) GWT.create(com.google.gwt.user.client.ui.FlexTable.class);
      // Setup section.
      recistFlexTable.setStyleName("" + get_style().recistFlexTable() + "");


      owner.recistFlexTable = recistFlexTable;

      return recistFlexTable;
    }

    /**
     * Getter for domId5Element called 2 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.uibinder.client.LazyDomElement domId5Element;
    private com.google.gwt.uibinder.client.LazyDomElement get_domId5Element() {
      return domId5Element;
    }
    private com.google.gwt.uibinder.client.LazyDomElement build_domId5Element() {
      // Creation section.
      domId5Element = new com.google.gwt.uibinder.client.LazyDomElement<Element>(get_domId5());
      // Setup section.


      return domId5Element;
    }

    /**
     * Getter for domId6 called 2 times. Type: DOM_ID_HOLDER. Build precedence: 3.
     */
    private java.lang.String domId6;
    private java.lang.String get_domId6() {
      return domId6;
    }
    private java.lang.String build_domId6() {
      // Creation section.
      domId6 = com.google.gwt.dom.client.Document.get().createUniqueId();
      // Setup section.


      return domId6;
    }

    /**
     * Getter for downloadRECISTChartAsImageButton called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.Button get_downloadRECISTChartAsImageButton() {
      return build_downloadRECISTChartAsImageButton();
    }
    private com.google.gwt.user.client.ui.Button build_downloadRECISTChartAsImageButton() {
      // Creation section.
      final com.google.gwt.user.client.ui.Button downloadRECISTChartAsImageButton = (com.google.gwt.user.client.ui.Button) GWT.create(com.google.gwt.user.client.ui.Button.class);
      // Setup section.
      downloadRECISTChartAsImageButton.setHTML(template_html2().asString());
      downloadRECISTChartAsImageButton.setVisible(false);
      downloadRECISTChartAsImageButton.addClickHandler(handlerMethodWithNameVeryUnlikelyToCollideWithUserFieldNames4);


      owner.downloadRECISTChartAsImageButton = downloadRECISTChartAsImageButton;

      return downloadRECISTChartAsImageButton;
    }

    /**
     * Getter for domId6Element called 2 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.uibinder.client.LazyDomElement domId6Element;
    private com.google.gwt.uibinder.client.LazyDomElement get_domId6Element() {
      return domId6Element;
    }
    private com.google.gwt.uibinder.client.LazyDomElement build_domId6Element() {
      // Creation section.
      domId6Element = new com.google.gwt.uibinder.client.LazyDomElement<Element>(get_domId6());
      // Setup section.


      return domId6Element;
    }

    /**
     * Getter for domId7 called 2 times. Type: DOM_ID_HOLDER. Build precedence: 3.
     */
    private java.lang.String domId7;
    private java.lang.String get_domId7() {
      return domId7;
    }
    private java.lang.String build_domId7() {
      // Creation section.
      domId7 = com.google.gwt.dom.client.Document.get().createUniqueId();
      // Setup section.


      return domId7;
    }

    /**
     * Getter for recistChartsFlexTable called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.FlexTable get_recistChartsFlexTable() {
      return build_recistChartsFlexTable();
    }
    private com.google.gwt.user.client.ui.FlexTable build_recistChartsFlexTable() {
      // Creation section.
      final com.google.gwt.user.client.ui.FlexTable recistChartsFlexTable = (com.google.gwt.user.client.ui.FlexTable) GWT.create(com.google.gwt.user.client.ui.FlexTable.class);
      // Setup section.


      owner.recistChartsFlexTable = recistChartsFlexTable;

      return recistChartsFlexTable;
    }

    /**
     * Getter for domId7Element called 2 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.uibinder.client.LazyDomElement domId7Element;
    private com.google.gwt.uibinder.client.LazyDomElement get_domId7Element() {
      return domId7Element;
    }
    private com.google.gwt.uibinder.client.LazyDomElement build_domId7Element() {
      // Creation section.
      domId7Element = new com.google.gwt.uibinder.client.LazyDomElement<Element>(get_domId7());
      // Setup section.


      return domId7Element;
    }

    /**
     * Getter for domId8 called 2 times. Type: DOM_ID_HOLDER. Build precedence: 3.
     */
    private java.lang.String domId8;
    private java.lang.String get_domId8() {
      return domId8;
    }
    private java.lang.String build_domId8() {
      // Creation section.
      domId8 = com.google.gwt.dom.client.Document.get().createUniqueId();
      // Setup section.


      return domId8;
    }

    /**
     * Getter for radiologyImagesFlexTable called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.FlexTable get_radiologyImagesFlexTable() {
      return build_radiologyImagesFlexTable();
    }
    private com.google.gwt.user.client.ui.FlexTable build_radiologyImagesFlexTable() {
      // Creation section.
      final com.google.gwt.user.client.ui.FlexTable radiologyImagesFlexTable = (com.google.gwt.user.client.ui.FlexTable) GWT.create(com.google.gwt.user.client.ui.FlexTable.class);
      // Setup section.


      owner.radiologyImagesFlexTable = radiologyImagesFlexTable;

      return radiologyImagesFlexTable;
    }

    /**
     * Getter for domId8Element called 2 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.uibinder.client.LazyDomElement domId8Element;
    private com.google.gwt.uibinder.client.LazyDomElement get_domId8Element() {
      return domId8Element;
    }
    private com.google.gwt.uibinder.client.LazyDomElement build_domId8Element() {
      // Creation section.
      domId8Element = new com.google.gwt.uibinder.client.LazyDomElement<Element>(get_domId8());
      // Setup section.


      return domId8Element;
    }

    /**
     * Getter for domId0Element called 2 times. Type: DEFAULT. Build precedence: 2.
     */
    private com.google.gwt.uibinder.client.LazyDomElement domId0Element;
    private com.google.gwt.uibinder.client.LazyDomElement get_domId0Element() {
      return domId0Element;
    }
    private com.google.gwt.uibinder.client.LazyDomElement build_domId0Element() {
      // Creation section.
      domId0Element = new com.google.gwt.uibinder.client.LazyDomElement<Element>(get_domId0());
      // Setup section.


      return domId0Element;
    }

    /**
     * Getter for domId9 called 2 times. Type: DOM_ID_HOLDER. Build precedence: 2.
     */
    private java.lang.String domId9;
    private java.lang.String get_domId9() {
      return domId9;
    }
    private java.lang.String build_domId9() {
      // Creation section.
      domId9 = com.google.gwt.dom.client.Document.get().createUniqueId();
      // Setup section.


      return domId9;
    }

    /**
     * Getter for f_HTMLPanel3 called 1 times. Type: DEFAULT. Build precedence: 2.
     */
    private com.google.gwt.user.client.ui.HTMLPanel get_f_HTMLPanel3() {
      return build_f_HTMLPanel3();
    }
    private com.google.gwt.user.client.ui.HTMLPanel build_f_HTMLPanel3() {
      // Creation section.
      final com.google.gwt.user.client.ui.HTMLPanel f_HTMLPanel3 = new com.google.gwt.user.client.ui.HTMLPanel(template_html4().asString());
      // Setup section.
      f_HTMLPanel3.setStyleName("" + get_style().annotationUploadHTMLPanel() + "");
      f_HTMLPanel3.setVisible(false);

      // Attach section.
      UiBinderUtil.TempAttachment attachRecord2 = UiBinderUtil.attachToDom(f_HTMLPanel3.getElement());
      get_domId10Element().get();

      // Detach section.
      attachRecord2.detach();
      f_HTMLPanel3.addAndReplaceElement(get_annotationUploadFlowPanel(), get_domId10Element().get());

      return f_HTMLPanel3;
    }

    /**
     * Getter for domId10 called 2 times. Type: DOM_ID_HOLDER. Build precedence: 3.
     */
    private java.lang.String domId10;
    private java.lang.String get_domId10() {
      return domId10;
    }
    private java.lang.String build_domId10() {
      // Creation section.
      domId10 = com.google.gwt.dom.client.Document.get().createUniqueId();
      // Setup section.


      return domId10;
    }

    /**
     * Getter for annotationUploadFlowPanel called 1 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.user.client.ui.FlowPanel get_annotationUploadFlowPanel() {
      return build_annotationUploadFlowPanel();
    }
    private com.google.gwt.user.client.ui.FlowPanel build_annotationUploadFlowPanel() {
      // Creation section.
      final com.google.gwt.user.client.ui.FlowPanel annotationUploadFlowPanel = (com.google.gwt.user.client.ui.FlowPanel) GWT.create(com.google.gwt.user.client.ui.FlowPanel.class);
      // Setup section.


      owner.annotationUploadFlowPanel = annotationUploadFlowPanel;

      return annotationUploadFlowPanel;
    }

    /**
     * Getter for domId10Element called 2 times. Type: DEFAULT. Build precedence: 3.
     */
    private com.google.gwt.uibinder.client.LazyDomElement domId10Element;
    private com.google.gwt.uibinder.client.LazyDomElement get_domId10Element() {
      return domId10Element;
    }
    private com.google.gwt.uibinder.client.LazyDomElement build_domId10Element() {
      // Creation section.
      domId10Element = new com.google.gwt.uibinder.client.LazyDomElement<Element>(get_domId10());
      // Setup section.


      return domId10Element;
    }

    /**
     * Getter for domId9Element called 2 times. Type: DEFAULT. Build precedence: 2.
     */
    private com.google.gwt.uibinder.client.LazyDomElement domId9Element;
    private com.google.gwt.uibinder.client.LazyDomElement get_domId9Element() {
      return domId9Element;
    }
    private com.google.gwt.uibinder.client.LazyDomElement build_domId9Element() {
      // Creation section.
      domId9Element = new com.google.gwt.uibinder.client.LazyDomElement<Element>(get_domId9());
      // Setup section.


      return domId9Element;
    }
  }
}
