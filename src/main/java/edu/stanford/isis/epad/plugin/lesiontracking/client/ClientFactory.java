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
//
//import com.google.gwt.core.client.GWT;
//import com.google.gwt.event.shared.EventBus;
//import com.google.gwt.place.shared.PlaceController;
//
////import edu.stanford.isis.epad.plugin.lesiontracking.client.model.DicomPatient;
////import edu.stanford.isis.epad.plugin.lesiontracking.client.model.DicomSeries;
////import edu.stanford.isis.epad.plugin.lesiontracking.client.model.Patients;
////import edu.stanford.isis.epad.plugin.lesiontracking.client.model.Projects;
////import edu.stanford.isis.epad.plugin.lesiontracking.client.undo.Invoker;
////import edu.stanford.isis.epad.plugin.lesiontracking.client.view.AimListView;
////import edu.stanford.isis.epad.plugin.lesiontracking.client.view.AimManagerView;
////import edu.stanford.isis.epad.plugin.lesiontracking.client.view.GridView;
////import edu.stanford.isis.epad.plugin.lesiontracking.client.view.ImageAnnotationView;
////import edu.stanford.isis.epad.plugin.lesiontracking.client.view.MainView;
////import edu.stanford.isis.epad.plugin.lesiontracking.client.view.RecistView;
////import edu.stanford.isis.epad.plugin.lesiontracking.client.view.ViewportView;
////import edu.stanford.isis.epad.plugin.lesiontracking.client.view.impl.Viewports;
////import edu.stanford.isis.epad.plugin.lesiontracking.client.widget.Notification;
//import edu.stanford.isis.epad.plugin.lesiontracking.shared.aimapi.User;
//
///**
// * A factory for creating Client objects.
// */
//public interface ClientFactory {
//
//	static final ClientFactory impl = GWT.create(ClientFactory.class);
//
//	int getActiveViewport();
//
//	boolean hasActiveView();
//
//	void setActiveKey(int key);
//
//	int getMaxKey();
//
//	void removeViewport(int key);
//
//	EventBus getEventBus();
//
//	PlaceController getPlaceController();
//
//	EPadService getRpcService();
//
//	void initFactory();
//
//	User getUser();
//
//	User getUser(boolean b);
//
//}
