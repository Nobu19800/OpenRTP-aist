package jp.go.aist.rtm.nameserviceview;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class NameServiceViewPlugin extends AbstractUIPlugin {

	// The plug-in ID
	private static final String PLUGIN_ID = "jp.go.aist.rtm.nameserviceview";

	// The shared instance
	private static NameServiceViewPlugin plugin;

	/**
	 * The constructor
	 */
	public NameServiceViewPlugin() {
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/*
	 * (non-Javadoc)
	 * 
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
	public static NameServiceViewPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path.
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
	
	/**
	 * ImageRegistry�ɃL���b�V�������C���[�W��Ԃ�
	 * @param path �v���O�C���̑��΃p�X
	 * @return�@�C���[�W
	 */
	public static Image getCachedImage(String path) {
		return getCachedImage(getImageDescriptor(path));
	}

	/**
	 * ImageRegistry�ɃL���b�V�������C���[�W��Ԃ�
	 * @param descriptor �C���[�W�f�B�X�N���v�^
	 * @return�@�C���[�W
	 */
	public static Image getCachedImage(ImageDescriptor descriptor) {
		if (descriptor == null) return null;
		Image result = getDefault().getImageRegistry().get(descriptor.toString());
		if (result == null) {
			result = descriptor.createImage();
			getDefault().getImageRegistry().put(
					descriptor.toString(), result);
		}
		return result;
	}
}
