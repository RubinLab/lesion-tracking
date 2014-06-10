package edu.stanford.isis.epad.plugin.lesiontracking.shared;

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

/**
 * Manages user preferences and configuration settings. This includes managing the cookie to save user viewing options.
 * 
 * @author Debra Willrett
 * 
 */
public class Preferences
{

	// params to control ePAD behavior
	private static boolean useBrowser = true;
	private static boolean viewAsGrid = true;
	private static boolean usePNG = true;
	private static boolean useAIM = true;
	private static boolean showAim = true;
	private static boolean useAutoFill = false;
	private static boolean propagateTransformations = false;
	private static boolean propagateWindowing = true;
	private static boolean useSpline = true;
	private static boolean showOverlay = true;
	private static boolean showLabels = true;
	private static boolean showAllAim = false; // show other users' aim files
	private static String defaultTemplate = "";
	private static final int maxViewCount = 6;
	public static int gridColumns = 3;
	public static int gridRows = 2;

	private static boolean preloadDicom = false;
	private static boolean useAimDropdown = true;
	private static double windowingMultiplier = 1.0; // 1 pixel == % width
	private static boolean overrideWindow = false;
	private static int overrideWidth = 0;
	private static int overrideCenter = 0;
	private static boolean webServiceForTags = true;
	private static String defaultWidth = "400";
	private static String defaultCenter = "40";
	private static String defaultName = "Lesion";
	private static int nextEventMessage = 1;
	private static boolean useEventMessaging = true;
	private static boolean pageSlide = false;

	public static int aimEditorWidth = 300;
	public static int aimEditorHeight = 300;

	private static String acceptanceDate = "";

	// location of folders
	private static String resourcesFolder = "../resources/";
	private static String imagesFolder = resourcesFolder + "dicom/";
	private static String imagesBaseUrl = "/epad/resources/dicom/";

	// compile for epad context
	private static boolean hasProxy = false;
	private static String proxyBase = "/proxy";

	// compile in debug mode
	private static boolean debugOn = false;

	// epad cookies
	public static String epadUserPreferences = "ePADUserPreferences";
	public static String epadLoggedinUser = "ePADLoggedinUser";

	private static String version = "1.1.4";

	private static boolean isWebGl = false;
	private static boolean isXTK = false;

	private static String serverProxy = "";

	public static String getImagesFolder()
	{
		return imagesFolder;
	}

	public static String getImagesBaseUrl()
	{
		// Debug never uses proxy
		if (debugOn)
			return imagesBaseUrl;
		return (hasProxy ? proxyBase : "") + imagesBaseUrl;
	}

	public static int getGridColumns()
	{
		return gridColumns;
	}

	public static int getGridRows()
	{
		return gridRows;
	}

	public static boolean isUsingBrowser()
	{
		return useBrowser;
	}

	public static boolean hasViewAsGrid()
	{
		return viewAsGrid;
	}

	public static boolean hasPNG()
	{
		return usePNG;
	}

	public static boolean hasAIM()
	{
		return useAIM;
	}

	public static boolean showingAllAim()
	{
		return showAllAim;
	}

	public static boolean hasAutoFill()
	{
		return useAutoFill;
	}

	public static boolean useAimDropdown()
	{
		return useAimDropdown;
	}

	public void setPropagateTransformations(boolean value)
	{
		propagateTransformations = value;
	}

	public static boolean hasWindowOverride()
	{
		return overrideWindow;
	}

	public static int getOverrideWidth()
	{
		return overrideWidth;
	}

	public static int getOverrideCenter()
	{
		return overrideCenter;
	}

	public static void setAimDropdown(boolean aimDropdown)
	{
		useAimDropdown = aimDropdown;
	}

	public static void setOverrideWindow(boolean value)
	{
		overrideWindow = value;
	}

	public static void setOverrideWidth(int value)
	{
		overrideWidth = value;
	}

	public static void setOverrideCenter(int value)
	{
		overrideCenter = value;
	}

	public static boolean isPropagatingTransformations()
	{
		return propagateTransformations;
	}

	public static boolean hasWindowingPropagation()
	{
		return propagateWindowing;
	}

	public static void setSpline(boolean b)
	{
		useSpline = b;
	}

	public static void setShowOverlay(boolean value)
	{
		showOverlay = value;
	}

	public static boolean isShowingOverlay()
	{
		return showOverlay;
	}

	public static boolean isShowingLabels()
	{
		return showLabels;
	}

	public static void setShowLabels(boolean value)
	{
		showLabels = value;
	}

	public static int getNextEventMessage()
	{
		return nextEventMessage;
	}

	public static void setNextEventMessage(int next)
	{
		nextEventMessage = next;
	}

	public static boolean useEventMessaging()
	{
		return useEventMessaging;
	}

	public static void setEventMessaging(boolean eventMessaging)
	{
		useEventMessaging = eventMessaging;
	}

	/**
	 * reset some user options for aim
	 * 
	 * @param showAim
	 */
	public static void setAimOptions(String template, boolean autoFill, String name, boolean show, boolean showAll)
	{

		defaultTemplate = template;
		useAutoFill = autoFill;
		defaultName = name;
		showAim = show;
		showAllAim = showAll;
	}

	/**
	 * reset some view settings and possibly redraw when things change
	 * 
	 * @param aimEditorHeight
	 * @param aimEditorWidth
	 * @param
	 */
	public static void setViewOptions(boolean propagate, boolean transformations, boolean overlay, boolean labels,
			boolean window, int width, int center, boolean webGl, boolean xtk, boolean spline)
	{

		propagateWindowing = propagate;
		propagateTransformations = transformations;
		showOverlay = overlay;
		showLabels = labels;
		overrideWindow = window;
		overrideWidth = width;
		overrideCenter = center;
		isWebGl = webGl;
		isXTK = xtk;
		useSpline = spline;
	}

	public static String getDefaultTemplate()
	{
		return defaultTemplate;
	}

	public static int getNextEvent()
	{
		return nextEventMessage;
	}

	public static double getWindowingMultiplier()
	{
		return windowingMultiplier;
	}

	public static int getMaxViewCount()
	{
		return maxViewCount;
	}

	public static boolean hasDicomPreloading()
	{
		return preloadDicom;
	}

	public static String getDefaultWidth()
	{
		return defaultWidth;
	}

	public static String getDefaultName()
	{
		return defaultName;
	}

	public static String getDefaultCenter()
	{
		return defaultCenter;
	}

	public static boolean useWebServiceForTags()
	{
		return webServiceForTags;
	}

	public static boolean getPageSlide()
	{
		return pageSlide;
	}

	/**
	 * ePAD version number
	 * 
	 * @return
	 */
	public static String getVersion()
	{
		return version;
	}

	public static boolean getDebugOn()
	{
		return debugOn;
	}

	public static boolean getShowAim()
	{
		return showAim;
	}

	public static void setShowAim(boolean show)
	{
		showAim = show;
	}

	public static String getResourcesFolder()
	{
		return resourcesFolder;
	}

	public static boolean isWebGl()
	{
		return isWebGl;
	}

	public static void setAcceptanceDate(String accept)
	{
		acceptanceDate = accept;

	}

	public static String getAcceptanceDate()
	{
		return acceptanceDate;

	}

	public static void setAimEditorSize(int width, int height)
	{
		aimEditorWidth = width;
		aimEditorHeight = height;

	}

	public static int getAimEditorWidth()
	{
		return aimEditorWidth;
	}

	public static int getAimEditorHeight()
	{
		return aimEditorHeight;
	}

	public static boolean isXTK()
	{
		return isXTK;
	}

	public static boolean useSpline()
	{
		return useSpline;
	}

	// come up with the url for our lossless images
	// not great to have this hard coded in here, but okay for now
	public static String getLosslessUrl(String studyId, String seriesId, String imageId)
	{

		return serverProxy + "/epad/resources/dicom/" + studyId.replace(".", "_") + "/" + seriesId.replace(".", "_") + "/"
				+ imageId.replace(".", "_") + ".png";

	}

	// come up with the url for our lossless images
	// not great to have this hard coded in here, but okay for now
	public static String getMaskUrl()
	{

		return serverProxy + "/epad/resources/dicom/mask2.png";
	}

	// come up with the url for our lossy images
	// not great to have this hard coded in here, but okay for now
	public static String getLossyUrl(String studyId, String seriesId, String imageId)
	{

		return serverProxy + "/epad/wado/?requestType=WADO&studyUID=" + studyId + "&seriesUID=" + seriesId + "&objectUID="
				+ imageId;
	}

	public static void setServerProxy(String proxy)
	{
		serverProxy = proxy;

	}

	public static String getServerProxy()
	{
		return serverProxy;
	}

}
