package jp.go.aist.rtm.systemeditor.ui.editor.dnd;

import jp.go.aist.rtm.toolscommon.model.component.Component;

import org.eclipse.gef.requests.CreationFactory;

/**
 * �h���b�O���h���b�v���A�R���|�[�l���g���쐬����t�@�N�g��
 */
public class ComponentFactory implements CreationFactory {
	private Component component;

	/**
	 * {@inheritDoc}
	 */
	public Object getObjectType() {
		return component.getClass();
	}

	/**
	 * �R���|�[�l���g�̃����[�g�I�u�W�F�N�g��ݒ肷��
	 * 
	 * @param remoteObject
	 *            �R���|�[�l���g�̃����[�g�I�u�W�F�N�g
	 */
	public void setComponent(Component component) {
		this.component = component;
	}
	
	/**
	 * �R���|�[�l���g�̃����[�g�I�u�W�F�N�g��ݒ肷��
	 * 
	 * @param remoteObject
	 *            �R���|�[�l���g�̃����[�g�I�u�W�F�N�g
	 */
	protected Component getComponent() {
		return this.component;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public Object getNewObject() {
		if (getComponent() != null) return getComponent().copy();
		return null;
	}
}
