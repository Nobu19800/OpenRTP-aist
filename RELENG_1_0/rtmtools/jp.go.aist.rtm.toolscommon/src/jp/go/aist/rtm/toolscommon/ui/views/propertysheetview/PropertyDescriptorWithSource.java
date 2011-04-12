package jp.go.aist.rtm.toolscommon.ui.views.propertysheetview;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;


/**
 * Property��Descriptor��Source���܂Ƃ߂��N���X
 */
public class PropertyDescriptorWithSource {
	private IPropertyDescriptor propertyDescriptor;

	private IPropertySource source;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param propertyDescriptor
	 *            IPropertyDescriptor
	 * @param source
	 *            IPropertySource
	 */
	public PropertyDescriptorWithSource(IPropertyDescriptor propertyDescriptor,
			IPropertySource source) {
		this.propertyDescriptor = propertyDescriptor;
		this.source = source;
	}

	/**
	 * IPropertyDescriptor���擾����
	 * 
	 * @return IPropertyDescriptor
	 */
	public IPropertyDescriptor getPropertyDescriptor() {
		return propertyDescriptor;
	}

	/**
	 * IPropertySource���擾����
	 * 
	 * @return IPropertySource
	 */
	public IPropertySource getSource() {
		return source;
	}

}
