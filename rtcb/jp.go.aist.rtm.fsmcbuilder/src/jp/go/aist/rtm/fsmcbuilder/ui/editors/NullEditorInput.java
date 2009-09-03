package jp.go.aist.rtm.fsmcbuilder.ui.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

/**
 * �V�K�G�f�B�^���Ӗ�����G�f�B�^�C���v�b�g
 */
public class NullEditorInput implements IEditorInput {

	public boolean exists() {
		return false;
	}

	public ImageDescriptor getImageDescriptor() {
		return ImageDescriptor.getMissingImageDescriptor();
	}

	public String getName() {
		return ""; //$NON-NLS-1$
	}

	public IPersistableElement getPersistable() {
		return null;
	}

	public String getToolTipText() {
		return ""; //$NON-NLS-1$
	}

	public Object getAdapter(Class adapter) {
		return null;
	}

}
