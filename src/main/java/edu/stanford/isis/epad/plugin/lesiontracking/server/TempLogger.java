package edu.stanford.isis.epad.plugin.lesiontracking.server;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Temporary logger for the server-side code to put information into server-log
 * file.
 * 
 * @author alansnyder
 */
public class TempLogger {
	private static TempLogger ourInstance = new TempLogger();

	final Logger log;
	boolean useDebug = false;

	public static TempLogger getInstance() {
		return ourInstance;
	}

	private TempLogger() {

		log = Logger.getAnonymousLogger();
		log.setLevel(Level.FINE);

		String home = System.getProperty("user.home");

		// setup logging.
		try {
			// FileHandler fh = new FileHandler("../log/epad-server.log");
			FileHandler fh = new FileHandler(home
					+ "/DicomProxy/log/epad-lesiontracking.log");
			fh.setFormatter(new LogFormatter());
			log.addHandler(fh);
		} catch (Exception e) {
			System.out.println("Failed to setup logging!" + e.getMessage());
		}

	}

	/**
	 * Log a debug string.
	 * 
	 * @param message
	 */
	public void debug(String message) {
		if (useDebug) {
			info(message);
		}
	}

	/**
	 * To turn debugging on, set to true.
	 * 
	 * @param value
	 *            true to turn on debug logging.
	 */
	public void setDebug(boolean value) {
		useDebug = value;
	}

	/**
	 * Log a standard INFO level message.
	 * 
	 * @param message
	 */
	public void info(String message) {
		log.info(message);
	}

	/**
	 * Log a warning with a message and the exception
	 * 
	 * @param message
	 *            String to write to the log file.
	 * @param t
	 *            Throwable
	 */
	public void warning(String message, Throwable t) {
		log.log(Level.WARNING, message, t);
	}

	/**
	 * Log a sever error with a message and a stack trace
	 * 
	 * @param message
	 *            String to write to the log
	 * @param t
	 *            Throwable exception with Stack Trace.
	 */
	public void sever(String message, Throwable t) {
		log.log(Level.SEVERE, message, t);
		t.printStackTrace();
	}

}
