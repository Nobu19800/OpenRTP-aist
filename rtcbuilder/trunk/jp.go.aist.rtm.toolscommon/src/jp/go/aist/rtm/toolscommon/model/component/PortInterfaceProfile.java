package jp.go.aist.rtm.toolscommon.model.component;

import jp.go.aist.rtm.toolscommon.ui.propertysource.PortInterfaceProfilePropertySource;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.views.properties.IPropertySource;

import RTC.PortInterfacePolarity;

/**
 * PortInterfaceProfile��\������N���X ���̃I�u�W�F�N�g�́A�o�����[�I�u�W�F�N�g�ł��邱�Ƃɒ��ӂ��邱�ƁB<br>
 * ���̃I�u�W�F�N�g���͓̂������s���Ȃ����߁A���̃I�u�W�F�N�g�̎Q�Ƃ�ێ��������邱�Ƃ́A�댯�ł���B<br>
 * �����������A�Q�ƌ��̃I�u�W�F�N�g���Q�Ƃ��āA�K�v�ɂȂ邽�тɂ��������ɓ���邱�ƁB
 * 
 * �܂��A���̃N���X��Equals���\�b�h���I�[�o�[���C�h���Ă���̂ŁARTC.PortInterfaceProfile�ւ̃t�B�[���h�̒ǉ��̍ۂɂ́A�ێ��ӂ�Ȃ����ƁB
 * PortInterfacePolarity��equals�͂����Œ�`���Ă���̂ŋC�����邱�ƁB
 */
public class PortInterfaceProfile implements IAdaptable {

	private RTC.PortInterfaceProfile delegate;

	public PortInterfaceProfile(RTC.PortInterfaceProfile delegate) {
		this.delegate = delegate;
	}

	public String getInstanceName() {
		return delegate.instance_name;
	}

	public String getTypeName() {
		return delegate.type_name;
	}

	public PortInterfacePolarity getPortInterfacePolarity() {
		return delegate.polarity;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PortInterfaceProfile == false) {
			return false;
		}

		PortInterfaceProfile p = (PortInterfaceProfile) obj;

		return new EqualsBuilder().append(delegate.instance_name,
				p.delegate.instance_name).append(delegate.type_name,
				p.delegate.type_name).append(delegate.polarity.value(),
				p.delegate.polarity.value()).isEquals();
	}

	public Object getAdapter(Class adapter) {
		java.lang.Object result = null;
		if (IPropertySource.class.equals(adapter)) {
			result = new PortInterfaceProfilePropertySource(this);
		}

		if (result == null) {
			result = Platform.getAdapterManager().getAdapter(this, adapter);
		}

		return result;
	}
}