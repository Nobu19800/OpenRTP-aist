package jp.go.aist.rtm.toolscommon.ui.views.propertysheetview;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.Component;

import org.eclipse.emf.ecore.EObject;

/**
 * �R���|�[�l���g�̃��b�p�N���X
 */
public class ComponentWrapper {
	private EObject component;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param component
	 *            �h���C�����f��
	 */
	public ComponentWrapper(EObject component) {
		this.component = component;
	}

	/**
	 * �R���|�[�l���g���擾����
	 * 
	 * @return �R���|�[�l���g
	 */
	public EObject getComponent() {
		return component;
	}

	/**
	 * �R���|�[�l���g��ݒ肷��
	 * 
	 * @param component
	 *            �R���|�[�l���g
	 */
	public void setComponent(EObject component) {
		this.component = component;
	}

	/**
	 * containment=false�̗v�f���擾���� (eAllContents()�ł��ǂ�Ȃ�����)
	 * 
	 * @return containment=false�̗v�f���X�g
	 */
	public List<EObject> getSubContents() {
		List<EObject> result = new ArrayList<EObject>();
		if (component instanceof Component) {
			Component c = (Component) component;
			for (EObject eo : c.getParticipationContexts()) {
				result.add(eo);
			}
		}
		return result;
	}

}
