package edu.stanford.isis.epad.plugin.lesiontracking.client.util;

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

import java.util.Date;
import java.util.logging.Logger;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Cookies;

//import edu.stanford.isis.epad.plugin.lesiontracking.client.ClientFactory;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.Preferences;
//import edu.stanford.isis.epad.plugin.lesiontracking.shared.aimapi.User;

/**
 * Manage ePAD cookies. There are three cookies, ePADUserPreferences{user name},
 * ePADLoggedInUser, and JSESSIONID
 * 
 * @author Debra Willrett
 * 
 */
public class Cookie {

	private static final Logger logger = Logger.getLogger("Cookie");

	final int COOKIE_EXPIRE_DAYS = 30;
	final long MILLISECS_PER_DAY = 86400000;
	final DateTimeFormat df = DateTimeFormat.getFormat("MM-dd-yyyy");

	/**
	 * save the user preferences as a cookie.
	 */
//	public void saveUserPreferences() {
//
//		// get the cookie name
//		String name = getPreferencesCookieName();
//		String value = getPreferencesString();
//
//		if (value == null) {
//			Cookies.removeCookie(name);
//			return;
//		}
//		String v = Cookies.getCookie(name);
//		if (!value.equals(v)) {
//			// Now
//			Date d = new Date();
//			// Now + days
//			d.setTime(d.getTime() + (MILLISECS_PER_DAY * COOKIE_EXPIRE_DAYS));
//			Cookies.setCookie(name, value.toString(), d);
//		}
//	}

	/**
	 * read in any saved user preference cookie for this logged in user.
	 */
//	public void getUserPreferences() {
//
//		// get the cookie value
//		String name = getPreferencesCookieName();
//		String value = Cookies.getCookie(name);
//		if (value != null) {
//
//			JSONValue jsonValue = JSONParser.parseLenient(value);
//			JSONObject jsonObject = jsonValue.isObject();
//			if (jsonObject != null) {
//
//				// save them in the preferences
//				Preferences.setViewOptions(
//						getBoolean("propagation", true, jsonObject),
//						getBoolean("transformations", true, jsonObject),
//						getBoolean("overlay", true, jsonObject),
//						getBoolean("labels", true, jsonObject),
//						getBoolean("override", false, jsonObject),
//						getInt("width", 0, jsonObject),
//						getInt("center", 0, jsonObject),
//						getBoolean("webgl", false, jsonObject),
//						getBoolean("xtk", false, jsonObject),
//						getBoolean("spline", false, jsonObject));
//				
//				Preferences.setAimEditorSize(getInt("aimeditorwidth", 300, jsonObject), getInt("aimeditorheight",300, jsonObject));
//
//				// update the acceptance date if they have one
//				String acceptance = getString("acceptance", "", jsonObject);
//				if (!acceptance.isEmpty()) {
//					Preferences.setAcceptanceDate(acceptance);
//				}
//
//				Preferences.setAimOptions(
//						getString("template", "", jsonObject),
//						getBoolean("autofill", false, jsonObject),
//						getString("name", "Lesion", jsonObject),
//						getBoolean("aim", true, jsonObject),
//						getBoolean("allaim", false, jsonObject));
//
//			}
//		}
//	}

	public boolean isLicenseValid(String user) {

		boolean result = false;
		String name = Preferences.epadUserPreferences + user;

		// get the cookie value
		String value = Cookies.getCookie(name);
		if (value != null) {

			try {

				JSONValue jsonValue = JSONParser.parseLenient(value);
				JSONObject jsonObject = jsonValue.isObject();
				if (jsonObject != null) {

					String acceptString = getString("acceptance", "", jsonObject);

					try {

						Date accept = df.parse(acceptString);
						Date now = new Date();
						Date expires = new Date();

						long acceptTime = accept.getTime();
						acceptTime += COOKIE_EXPIRE_DAYS * MILLISECS_PER_DAY;
						expires.setTime(acceptTime);

						if (expires.after(now)) {
							result = true;
						} 
					} catch (Exception e) {
						logger.info("LoginForm: date expiration parsing error");
					}

				}
			} catch (Exception e) {
				logger.info("Error parsing json" + e.getMessage());
			}
		} 
		return result;
	}

	// get a boolean object from the cookie string
	private boolean getBoolean(String name, boolean value, JSONObject cookie) {
		boolean result = value;
		try {
			result = cookie.get(name).isBoolean().booleanValue();
		} catch (Exception e) {
			logger.info("Error getBoolean failed for " + name);
		}

		return result;
	}

	// get a string object from the cookie string
	private String getString(String name, String value, JSONObject cookie) {
		String result = value;
		try {
			result = cookie.get(name).isString().stringValue();
		} catch (Exception e) {
			logger.info("error getString failed for " + name);
		}
		return result;
	}

	// get an int object from the cookie string
	private int getInt(String name, int value, JSONObject cookie) {
		int result = value;
		try {
			result = (int) cookie.get(name).isNumber().doubleValue();
		} catch (Exception e) {
			logger.info("error getInt failed for " + name);
		}
		return result;
	}

	// get a double object from the cookie string
	private double getDouble(String name, double value, JSONObject cookie) {
		double result = value;
		try {
			result = cookie.get(name).isNumber().doubleValue();
		} catch (Exception e) {
			logger.info("error " + e.getMessage());
		}
		return result;
	}

//	// get the current users preferences cookie name
//	private String getPreferencesCookieName() {
//
//		String cookie = Preferences.epadUserPreferences;
//		User user = ClientFactory.impl.getUser();
//		if (user != null) {
//			cookie += user.getLoginName();
//		}
//		return cookie;
//	}

	// stuff them into one string
	private String getPreferencesString() {

		logger.info("getViewOptionsString acceptance="
				+ Preferences.getAcceptanceDate());

		String result = "{";
		result += "\"propagation\"" + ":"
				+ Preferences.hasWindowingPropagation() + ",";
		result += "\"transformations\"" + ":"
				+ Preferences.isPropagatingTransformations() + ",";
		result += "\"overlay\"" + ":" + Preferences.isShowingOverlay() + ",";
		result += "\"labels\"" + ":" + Preferences.isShowingLabels() + ",";
		result += "\"override\"" + ":" + Preferences.hasWindowOverride() + ",";
		result += "\"width\"" + ":" + Preferences.getOverrideWidth() + ",";
		result += "\"center\"" + ":" + Preferences.getOverrideCenter() + ",";
		result += "\"acceptance\"" + ":" + "\""
				+ Preferences.getAcceptanceDate() + "\"" + ",";
		result += "\"template\"" + ":" + "\""
				+ Preferences.getDefaultTemplate() + "\"" + ",";
		result += "\"name\"" + ":" + "\"" + Preferences.getDefaultName() + "\""
				+ ",";
		result += "\"aim\"" + ":" + Preferences.getShowAim() + ",";
		result += "\"allaim\"" + ":" + Preferences.showingAllAim() + ",";
		result += "\"messaging\"" + ":" + Preferences.useEventMessaging() + ",";
		result += "\"autofill\"" + ":" + Preferences.hasAutoFill() + ",";
		result += "\"spline\"" + ":" + Preferences.useSpline() + ",";
		result += "\"aimeditorwidth\"" + ":" + Preferences.getAimEditorWidth() + ",";
		result += "\"aimeditorheight\"" + ":" + Preferences.getAimEditorHeight() + ",";
		result += "\"xtk\"" + ":" + Preferences.isXTK() + ",";

		result += "\"webgl\"" + ":" + Preferences.isWebGl();
		result += "}";

		return result;
	}

}
