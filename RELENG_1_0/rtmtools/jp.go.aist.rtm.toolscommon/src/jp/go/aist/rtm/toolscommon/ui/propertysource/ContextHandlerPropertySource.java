package jp.go.aist.rtm.toolscommon.ui.propertysource;

import jp.go.aist.rtm.toolscommon.model.component.ContextHandler;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;

/**
 * ContextHandler��IPropertySource�N���X
 */
public class ContextHandlerPropertySource extends AbstractPropertySource {

	/**
	 * @param handler
	 *            ���f��
	 */
	public ContextHandlerPropertySource(ContextHandler handler) {
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return new PropertyDescriptor[] {};
	}

	@Override
	public java.lang.Object getPropertyValue(java.lang.Object id) {
		return null;
	}

}
