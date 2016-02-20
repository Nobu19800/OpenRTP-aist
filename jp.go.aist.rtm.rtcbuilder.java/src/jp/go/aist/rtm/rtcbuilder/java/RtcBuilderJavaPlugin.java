package jp.go.aist.rtm.rtcbuilder.java;

import jp.go.aist.rtm.toolscommon.profiles.util.LoggerUtil;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The activator class controls the plug-in life cycle
 */
public class RtcBuilderJavaPlugin extends AbstractUIPlugin {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RtcBuilderJavaPlugin.class);

	// The plug-in ID
	public static final String PLUGIN_ID = "jp.go.aist.rtm.rtcbuilder.java";

	// The shared instance
	private static RtcBuilderJavaPlugin plugin;
	
	/**
	 * The constructor
	 */
	public RtcBuilderJavaPlugin() {
		LoggerUtil.setup();
		LOGGER.trace("RtcBuilderJavaPlugin: START");

		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static RtcBuilderJavaPlugin getDefault() {
		return plugin;
	}

}
