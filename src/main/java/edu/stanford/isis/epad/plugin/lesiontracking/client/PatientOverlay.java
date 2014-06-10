package edu.stanford.isis.epad.plugin.lesiontracking.client;

//Copyright (c) 2013 The Board of Trustees of the Leland Stanford Junior University
//All rights reserved.
//
//Redistribution and use in source and binary forms, with or without modification, are permitted provided that 
//the following conditions are met:
//
//Redistributions of source code must retain the above copyright notice, this list of conditions and the following 
//disclaimer.
//
//Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the 
//following disclaimer in the documentation and/or other materials provided with the distribution.
//
//Neither the name of The Board of Trustees of the Leland Stanford Junior University nor the names of its 
//contributors (Daniel Rubin, et al) may be used to endorse or promote products derived from this software without 
//specific prior written permission.
//
//THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
//INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
//DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
//SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
//SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
//WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE 
//USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Overlays the Json for the study 
 * 
 * @author debra willrett
 *
 */
public class PatientOverlay extends JavaScriptObject {
	
	  // Overlay types always have protected, zero-arg ctors
	  protected PatientOverlay() { }
	  
	  // Typically, methods on overlay types are JSNI
	  public final native String getProjectID()  /*-{ return this.projectID;  }-*/;
	  public final native String getSubjectName()  /*-{ return this.subjectName;  }-*/;  // have hats, direct copy of dicom patient name
	  public final native String getSubjectID()  /*-{ return this.subjectID;  }-*/;  // direct copy of dicom patient id
	  public final native String getInsertUser()  /*-{ return this.insertUser;  }-*/;
	  public final native String getXnatID() /*-{ return this.xnatID; }-*/;  // xnat internal subject id
	  public final native String getInsertDate()  /*-{ return this.insertDate;  }-*/;	  
	  public final native String getURI()  /*-{ return this.uri;  }-*/;
	  public final native int getNumberOfStudies()  /*-{ return this.numberOfStudies;  }-*/;
	  public final native int getNumberOfAnnotations()  /*-{ return this.numberOfAnnotations;  }-*/;
	  public final native String getExamType(int i)  /*-{ return this.examTypes[i];  }-*/;
	  public final native int getNumberOfExamTypes()  /*-{ return this.examTypes.length; }-*/;
 
}
