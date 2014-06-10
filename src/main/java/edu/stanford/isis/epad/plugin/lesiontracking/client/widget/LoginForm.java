package edu.stanford.isis.epad.plugin.lesiontracking.client.widget;

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

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

//import org.restlet.client.resource.Result;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

//import edu.stanford.isis.epad.plugin.lesiontracking.client.ClientFactory;
import edu.stanford.isis.epad.plugin.lesiontracking.client.events.LoginEvent;
import edu.stanford.isis.epad.plugin.lesiontracking.client.util.Cookie;
import edu.stanford.isis.epad.plugin.lesiontracking.shared.Preferences;
//import edu.stanford.isis.epad.plugin.lesiontracking.shared.aimapi.User;

/**
 * 
 * @author Debra Willrett
 * 
 */
public class LoginForm extends DialogBox {

	private static LoginFormUiBinder uiBinder = GWT
			.create(LoginFormUiBinder.class);

	interface LoginFormUiBinder extends UiBinder<Widget, LoginForm> {
	}

	final int COOKIE_EXPIRE_DAYS = 365;
	final long MILLISECS_PER_DAY = 86400000;
	final String ePadLicenseAgreement = "ePadLicenseAgreementFieldset";

	// declare local static variables
	private final String LICENSE_ERROR = "Please review and accept the ePAD license agreement terms.";
	private final String COOKIE_DISABLED = "Please enable cookies in your browser to access this web site.";
	private final String SERVER_ERROR = "An unexpected error occurred on the server:";
	private final String USER_ERROR = "The user name or password is invalid. Please contact your site administrator.";


	@UiField
	TextBox username;
	@UiField
	PasswordTextBox password;
	@UiField
	Button okButton;
	@UiField
	Button cancelButton;
	@UiField
	LIElement loginErrorMessage;
	@UiField
	TextBox emailAddress;
	@UiField
	Button submitRecoverButton;
	@UiField
	LIElement recoverGreetingMessage;
	@UiField
	LIElement recoverErrorMessage;
	@UiField
	CheckBox agree;

	private static final Logger logger = Logger.getLogger("LoginForm");

	public LoginForm() {

		setWidget(uiBinder.createAndBindUi(this));
		addStyleName("transparency");
		setAnimationEnabled(false);
		setGlassEnabled(true);
		setModal(true);
		setAutoHideEnabled(true);

		clearError();
		scheduleFocus();
		haveUser();

		username.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
//				agree.setValue(isLicenseValid(username.getText()));
			}
		});

		username.addKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {

				clearError();
				NativeEvent ne = event.getNativeEvent();
				if (ne.getKeyCode() == KeyCodes.KEY_ENTER) {
//					onSubmitClick();
				}
			}
		});

		password.addKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {

				clearError();
				NativeEvent ne = event.getNativeEvent();
				if (ne.getKeyCode() == KeyCodes.KEY_ENTER) {
//					onSubmitClick();
				}
			}
		});
	}

	@UiHandler("okButton")
	void onOkButtonClick(ClickEvent e) {
//		onSubmitClick();
	}

	@UiHandler("username")
	void onUserNameClick(ClickEvent e) {
		clearError();
	}

	@UiHandler("password")
	void onPasswordClick(ClickEvent e) {
		clearError();
	}

	@Override
	public Widget asWidget() {
		return this;
	}

//	// users submitted the login form
//	private void onSubmitClick() {
//
//		final int USERNAME = 0;
//		final int PASSWORD = 1;
//
//		final ArrayList<String> login = new ArrayList<String>();
//		login.add(username.getText());
//		login.add(password.getText());
//
//		if (login.get(USERNAME).equals("") || login.get(PASSWORD).equals("")) {
//
//			// user didn't supplied a user name and password
//			showError(USER_ERROR);
//
//		} else if (!Cookies.isCookieEnabled()) {
//
//			// user can't possibly log in here until
//			// cookies
//			// are enabled
//			showError(COOKIE_DISABLED);
//		} else if (!isLicenseValid(login.get(USERNAME))) {
//
//			// user can't possibly log in here until
//			// cookies
//			// are enabled
//			showError(LICENSE_ERROR);
//		} else {
//
//			// server side login
//			ClientFactory.impl.getRpcService().login(login,
//					new Result<String>() {
//
//						public void onFailure(Throwable caught) {
//							if (caught.getMessage().contains("authorized")) {
//								onLoginFailure(USER_ERROR);
//							} else {
//								onLoginFailure(SERVER_ERROR + caught.getMessage());
//							}
//						}
//
//						public void onSuccess(String result) {
//
//							// did we get back a jesssionid?
//							if (result == null) {
//								showError(SERVER_ERROR);
//
//							} else {
//
//								onLoginSuccess(result, login.get(USERNAME),
//										(agree.getValue() == true));
//
//							}
//						}
//					});
//
//		}
//	}

//	// has the user agreed to the license terms?
//	private boolean isLicenseValid(String user) {
//
//		boolean agreeChecked = agree.getValue();
//		boolean cookieChecked = false;
//
//		if (user != null && !user.isEmpty()) {
//			Cookie cookie = new Cookie();
//			cookieChecked = cookie.isLicenseValid(user);
//		}
//
//		return (agreeChecked || cookieChecked);
//	}
//
//	public void setUserName(String userName) {
//		
//		User user = ClientFactory.impl.getUser(false);
//		user.setLoginName(userName);
//		user.setName(userName);
//		user.setCagridId(0);
//		
//	}
//
	public String getUserName() {
		
//		User user = ClientFactory.impl.getUser(false);
		return "admin";//user.getLoginName();
		
	}

//	private void updatePreferences() {
//		
//		Cookie cookie = new Cookie();
//		cookie.getUserPreferences();
//		cookie.saveUserPreferences();
//		
//	}

//	 native void clientSideLogin(String proxy, String username, String
//	 password)/*-{
//	
//	 var xhr = new XMLHttpRequest();
//	
//	 var url = proxy + "/session/";
//	 console.debug("clientSideLogin url " + url);
//	
//	 xhr.open("post", url, true);
//	
//	 // This will generate a CORE prelight request if cross domain
//	 xhr.setRequestHeader("Authorization", "Basic "
//	 + btoa(username + ":" + password));
//	
//	 xhr.onload = function() {
//	 console.debug("JSESSIONID=" + xhr.responseText);
//	 // call onloginSuccess
//	 };
//	
//	 xhr.onerror = function(e) {
//	 console.debug("Error");
//	 };
//	
//	 xhr.withCredentials = "true"; // Required if we want return cookie set!
//	 xhr.send();
//	
//	 }-*/;

//	private void onLoginSuccess(String result, String username, boolean accept) {
//	
//		setUserName(username);
//
//		// if the user just clicked the accept checkbox, then save this date
//		if (accept) {
//			Date now = new Date();
//			DateTimeFormat df = DateTimeFormat.getFormat("MM-dd-yyyy");
//			String nowString = df.format(now);
//			Preferences.setAcceptanceDate(nowString);
//		}
//
//		// set the cookies with a long expiration
//		Date expires = new Date();
//		expires.setTime(expires.getTime()
//				+ (MILLISECS_PER_DAY * COOKIE_EXPIRE_DAYS));
//
//		// just in case, remove the prior cookies first
//		Cookies.removeCookie("JSESSIONID");
//		Cookies.removeCookie(Preferences.epadLoggedinUser);
//
//		// write the new cookies
//		Cookies.setCookie("JSESSIONID", result, expires);
//		Cookies.setCookie(Preferences.epadLoggedinUser, username, expires);
//
//		// update the preferences cookie if there is one or create one if there
//		// is not one already
//		Cookie cookie = new Cookie();
//		cookie.getUserPreferences();
//		cookie.saveUserPreferences();
//
//		// tell the world we have somebody logged in
//		ClientFactory.impl.getEventBus().fireEvent(
//				new LoginEvent(true, username, ""));
//
//		hide();
//	}

//	private void onLoginFailure(String error) {
//		
//		showError(error);
//		clearUser();
//
//		// tell the world we do not have anybody logged in
//		ClientFactory.impl.getEventBus().fireEvent(new LoginEvent(false));
//	}

	@UiHandler("cancelButton")
	protected void onCancel(ClickEvent e) {
		
		hide();
	}

	private void scheduleFocus() {
		
		Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
			public void execute() {
				username.setFocus(true);
				username.selectAll();
			}
		});
	}

	// are we logged in?
	public ArrayList<String> haveUser() {

//		boolean result = false;

		// do we think we have a user logged in, with a session?
		String newUser = Cookies.getCookie(Preferences.epadLoggedinUser);
		String session = Cookies.getCookie("JSESSIONID");

		String currentUser = getUserName();
		
		ArrayList<String> values = new ArrayList<String>();
		

		// it looks like there is someone logged in
		if (newUser != null && !newUser.isEmpty() && session != null
				&& !session.isEmpty()) {

			if (currentUser == null || !currentUser.equals(newUser)) {

				// we think we have somebody else logged in so update who we
				// think is logged in
//				setUserName(newUser);
//				updatePreferences();
			}

//			result = true;
			values.add(newUser);
			values.add(session);

		}
		
		return values;
	}

	// are we logged in?
	public boolean isLoggedIn() {

		String userName = getUserName();
		boolean result = (userName != null && !userName.isEmpty());
		return result;

	}

	// clear the fields and show the form
	public void showAndClear() {

		clearError();

		username.setText("");
		password.setText("");

		show();

		username.setFocus(true);
		username.selectAll();

		agree.setValue(false);

	}

	@Override
	public void onLoad() {
		super.onLoad();

		int clientHeight = Window.getClientHeight();
		int clientWidth = Window.getClientWidth();

		// int width = this.getOffsetWidth(); this should work but does not the
		// first time only
		int width = 400;

		int height = this.getOffsetHeight();
		setPopupPosition(clientWidth / 2 - width / 2, clientHeight / 2 - height
				/ 2);
	}

	private void clearError() {
		
		loginErrorMessage.setInnerHTML("");
		loginErrorMessage.addClassName("hidden");
		recoverErrorMessage.setInnerHTML("");
	}

	private void showError(String error) {
		
		loginErrorMessage.setInnerHTML(error);
		loginErrorMessage.removeClassName("hidden");
		recoverErrorMessage.setInnerHTML("");
		password.setText("");
	}

//	public void clearUser() {
//
//		setUserName("");
//
//		// remove the logged in user cookie
//		Cookies.removeCookie(Preferences.epadLoggedinUser);
//		Cookies.removeCookie("JSESSIONID");
//
//		ClientFactory.impl.getRpcService().logout(new Result<String>() {
//
//			@Override
//			public void onSuccess(String result) {
//			}
//
//			@Override
//			public void onFailure(Throwable caught) {
//				logger.info("Logout onFailure " + caught.getMessage());
//			}
//		});
//	}

}
