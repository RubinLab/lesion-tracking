package edu.stanford.isis.epad.plugin.lesiontracking.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class LoginEvent extends GwtEvent<LoginEvent.Handler> {

	private static Type<Handler> TYPE = new Type<LoginEvent.Handler>();

	public interface Handler extends EventHandler {
		public void onLoginEvent(LoginEvent loginEvent);
	}

	private final boolean loginResults;
	private final String cookieFileName;
	private final String userFilePath;

	public LoginEvent(boolean loginResults, String cookieFileName,
			String userFilePath) {
		super();
		this.loginResults = loginResults;
		this.cookieFileName = cookieFileName;
		this.userFilePath = userFilePath;
	}

	public LoginEvent(boolean loginResults) {
		super();
		this.loginResults = loginResults;
		this.cookieFileName = "";
		this.userFilePath = "";
	}

	public static Type<Handler> getType() {
		return TYPE;
	}

	@Override
	public Type<Handler> getAssociatedType() {
		return TYPE;
	}

	protected void dispatch(Handler handler) {
		handler.onLoginEvent(this);
	}

	public boolean getLoginResults() {
		return this.loginResults;
	}

	public String getCookieFileName() {
		return this.cookieFileName;
	}

	public String getUserFilePath() {
		return this.userFilePath;
	}

}
