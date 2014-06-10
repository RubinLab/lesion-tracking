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
 * generic overlay for json read in from the server
 * 
 * @author debra willrett
 *
 * @param <E>
 */
public class ResultSet<E extends JavaScriptObject>  extends JavaScriptObject {
		
		  // Overlay types always have protected, zero-arg ctors
		  protected ResultSet() { }

		  // Typically, methods on overlay types are JSNI
		  //public final native String getResult() /*-{ return this.Result; }-*/;
		  public final native String getTotalRecords()  /*-{ return this.ResultSet.totalRecords;  }-*/;
		  public final native int length() /*-{return this.ResultSet.Result.length;}-*/;
		  public final native E get(int i) /*-{return this.ResultSet.Result[i];}-*/;

//		  // Note, though, that methods aren't required to be JSNI
//		  public final String getFullName() {
//		    return getFirstName() + " " + getLastName();
//		  }

}
