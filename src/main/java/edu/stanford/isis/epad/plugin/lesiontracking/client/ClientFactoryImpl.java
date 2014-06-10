//package edu.stanford.isis.epad.plugin.lesiontracking.client;
//
////Copyright (c) 2013 The Board of Trustees of the Leland Stanford Junior University
////All rights reserved.
////
////Redistribution and use in source and binary forms, with or without modification, are permitted provided that 
////the following conditions are met:
////
////Redistributions of source code must retain the above copyright notice, this list of conditions and the following 
////disclaimer.
////
////Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the 
////following disclaimer in the documentation and/or other materials provided with the distribution.
////
////Neither the name of The Board of Trustees of the Leland Stanford Junior University nor the names of its 
////contributors (Daniel Rubin, et al) may be used to endorse or promote products derived from this software without 
////specific prior written permission.
////
////THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
////INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
////DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
////SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
////SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
////WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE 
////USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//
//import java.util.Map;
//import java.util.logging.Logger;
//
//import com.google.gwt.event.shared.EventBus;
//import com.google.gwt.place.shared.PlaceController;
//import com.google.gwt.view.client.ListDataProvider;
//
////import edu.stanford_usp.client.model.DicomPatient;
////import edu.stanford_usp.client.model.DicomSeries;
////import edu.stanford_usp.client.model.Patients;
////import edu.stanford_usp.client.model.Projects;
////import edu.stanford_usp.client.undo.Invoker;
////import edu.stanford_usp.client.util.SimpleEventBus;
////import edu.stanford_usp.client.view.AimListView;
////import edu.stanford_usp.client.view.AimManagerView;
////import edu.stanford_usp.client.view.GridView;
////import edu.stanford_usp.client.view.ImageAnnotationView;
////import edu.stanford_usp.client.view.MainView;
////import edu.stanford_usp.client.view.RecistView;
////import edu.stanford_usp.client.view.ToolbarView;
////import edu.stanford_usp.client.view.ViewportView;
////import edu.stanford_usp.client.view.ViewportView.Handler;
////import edu.stanford_usp.client.view.impl.AimListViewImpl;
////import edu.stanford_usp.client.view.ismpl.AimManagerViewImpl;
////import edu.stanford_usp.client.view.impl.GridViewImpl;
////import edu.stanford_usp.client.view.impl.ImageAnnotationViewImpl;
////import edu.stanford_usp.client.view.impl.MainViewImpl;
////import edu.stanford_usp.client.view.impl.RecistViewImpl;
////import edu.stanford_usp.client.view.impl.Viewports;
////import edu.stanford_usp.client.widget.Notification;
//import edu.stanford.isis.epad.plugin.lesiontracking.shared.Preferences;
////import edu.stanford_usp.shared.aimapi.Aim;
//import edu.stanford.isis.epad.plugin.lesiontracking.shared.aimapi.User;
//
///**
// * 
// * Client Factory instantiates and manages access to the resources such as the
// * event bus, the different image views, the view container, the annotation pop
// * up and such.
// * 
// */
//class ClientFactoryImpl implements ClientFactory {
//
////	private static final Logger logger = Logger.getLogger("ClientFactoryImpl");
////
////	// basic stuff
////	private final EventBus eventBus = new SimpleEventBus();
////	private final Invoker invoker = new Invoker(eventBus, false);
////
////	@SuppressWarnings("deprecation")
////	private final PlaceController placeController = new PlaceController(
////			eventBus);
////
////	// Create a data provider for ROI's
////	private final ListDataProvider<Aim> dataProvider = new ListDataProvider<Aim>();
////
////	// manage the viewports, patients, user and preferences
////	private int activeKey = -1;
////	private int maxKey = -1;
////	private Viewports viewports = new Viewports();
////	private Patients patients = new Patients();
////	private Projects projects = new Projects();
//	private User user = new User();
//	private Preferences preferences = new Preferences();
////
////	// instantiate our views
////	private GridView gridView;
////	private ToolbarView toolbarView;
////	private RecistView recistView;
////	private AimManagerView aimManagerView;
////	private Notification notification;
////
////	private EPadService rpcService = new EPadService();
////
////	private AimListView aimListView;
////
////	private ImageAnnotationView imageAnnotationView;
////
////	private MainView mainView;
////
//	private static int numInstances = 0;
//
//	ClientFactoryImpl() {
//		if (numInstances != 0)
//			throw new RuntimeException(
//					"Trying to instantiate more than one instance of ClientFactory");
//		numInstances++;
//	}
//
////	/*
////	 * (non-Javadoc)
////	 * 
////	 * @see edu.stanford_usp.client.TestFactory#getEventBus()
////	 */
////	@Override
////	public void initFactory() {
////
////		//toolbarView = new ToolbarViewImpl();
////		recistView = new RecistViewImpl();
////		aimManagerView = new AimManagerViewImpl();
////
////		notification = new Notification();
////		aimListView = new AimListViewImpl(dataProvider);
////		imageAnnotationView = new ImageAnnotationViewImpl();
////		imageAnnotationView.setAimListView(aimListView);
////		imageAnnotationView.setRecistView(recistView);
////
////		mainView = new MainViewImpl();
////		gridView = new GridViewImpl();
////
////		mainView.setGridView(gridView);
////		mainView.setAimManagerView(aimManagerView);
////
////	}
//
////
////	@Override
////	public EventBus getEventBus() {
////		return eventBus;
////	}
////
////
////	@Override
////	public PlaceController getPlaceController() {
////		return placeController;
////	}
////
////
////	@Override
////	public EPadService getRpcService() {
////		return rpcService;
////	}
////
////
////	@Override
////	public int getActiveViewport() {
////		return activeKey;
////	}
////
////
////	@Override
////	public void setActiveKey(int key) {
////		activeKey = key;
////	}
////
////
////	@Override
////	public void removeViewport(int key) {
////		// remove all the data associated with this key: view, series and dicom
////		// buffers
////		// TODO free up all the references to the dicomSeries, the imageView and
////		// the webPad.js buffers
////		viewports.remove(key);
////	}
////
////
////	@Override
////	public Viewports getViewports() {
////		return this.viewports;
////	}
////
////	// get the active series
////	@Override
////	public DicomSeries getActiveSeries() {
////		return viewports.getSeries(this.activeKey);
////	}
////
////	// get the image views map
////	@Override
////	public Map<Integer, ViewportView> getViewportViews() {
////		return viewports.getViewportViews();
////	}
//
////	// create a new viewport with the series selected by the user
////	// this means it needs an imageView, invoker, pixelBuffer, maybe patient
////	/*
////	 * (non-Javadoc)
////	 * 
////	 * @see
////	 * edu.stanford_usp.client.TestFactory#addActiveKey(edu.stanford_usp.shared
////	 * .DicomSeries, edu.stanford_usp.shared.DicomPatient)
////	 */
////	@Override
////	public int addActiveKey(DicomSeries series, DicomPatient patient) {
////		// get the next available key
////		activeKey = ++maxKey;
////		ViewportView view = getViewportViewImpl();
////		view.setHandler((Handler) getGridView());
////		int pixelBufferIndex = view.getPixelBufferIndexValue();
////		Invoker invoker = new Invoker(eventBus, true);
////		viewports.addViewport(activeKey, view, series, invoker,
////				pixelBufferIndex, patient);
////		return activeKey;
////	}
////	
////	// create a new viewport with the series selected by the user
////	// this means it needs an imageView, invoker, pixelBuffer, maybe patient
////	@Override
////	public int addViewport(DicomSeries series, DicomPatient patient) {
////		// get the next available key
////		int newKey = ++maxKey;
////		ViewportView view = getViewportViewImpl();
////		view.setHandler((Handler) getGridView());
////		int pixelBufferIndex = view.getPixelBufferIndexValue();
////		Invoker invoker = new Invoker(eventBus, true);
////		viewports.addViewport(newKey, view, series, invoker,
////				pixelBufferIndex, patient);
////		return newKey;
////	}
////
////
////	// what is the max view right now?
////	@Override
////	public int getMaxKey() {
////		// get the current highest key
////		return maxKey;
////	}
////
////	@Override
////	public GridView getGridView() {
////
////		if (gridView == null) {
////			gridView = new GridViewImpl();
////			mainView.setGridView(gridView);
////		}
////		return gridView;
////	}
////
////	@Override
////	public ImageAnnotationView getImageAnnotationView() {
////		return imageAnnotationView;
////	}
//
//	@Override
//	public User getUser() {
//		return user;
//	}
//
//	@Override
//	public User getUser(boolean b) {
//		return user;
//	}
//
////	@Override
////	public Map<Integer, Invoker> getInvokers() {
////		return viewports.getInvokerMap();
////	}
////
////
////	@Override
////	public Invoker getInvoker() {
////		return viewports.getInvokerMap().get(activeKey);
////	}
////
////
////	@Override
////	public Invoker getInvoker(boolean undo) {
////		return invoker;
////	}
////
////
////	@Override
////	public AimManagerView getAimManagerView() {
////		return aimManagerView;
////	}
////
////
////	@Override
////	public RecistView getRecistView() {
////		return recistView;
////	}
////
////
////	@Override
////	public MainView getMainView() {
////		return mainView;
////	}
////
////
////	@Override
////	public Patients getPatients() {
////		return patients;
////	}
////
////
////	@Override
////	public Projects getProjects() {
////		return projects;
////	}
////
////	// get the active image view
////	@Override
////	public ViewportView getActiveView() {
////		if (hasActiveView()) {
////			return viewports.getViewportView(activeKey);
////		} else {
////			logger.info("Error: has no active view");
////			return null;
////		}
////	}
////
////	// is there an active view?
////	@Override
////	public boolean hasActiveView() {
////		return activeKey > -1;
////	}
////
////	// get the active image view
////	@Override
////	public Notification getNotification() {
////		return notification;
////	}
////
////	@Override
////	public AimListView getAimListView() {
////		return aimListView;
////	}
////
////	@Override
////	public ViewportView getViewportViewImpl() {
////		
////		
////		return (Preferences.isXTK() ? new edu.stanford_usp.client.view.impl.xtk.ViewportViewImpl()
////				: new edu.stanford_usp.client.view.impl.ViewportViewImpl());
////		
//////		return (Preferences.isWebGlCanvas() ? new edu.stanford_usp.client.view.impl.d3.ViewportViewImpl()
//////		: new edu.stanford_usp.client.twoD.view.impl.ViewportViewImpl());
////	}
//
//}
