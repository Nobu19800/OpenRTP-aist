package jp.go.aist.rtm.systemeditor.ui.editor.editpart;

import jp.go.aist.rtm.toolscommon.model.core.ModelElement;
import jp.go.aist.rtm.toolscommon.util.AdapterUtil;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.ui.actions.ActionRegistry;

/**
 * EditPart�̒��ۃN���X�B�A�_�v�^�@�\�ƃh���C�����f������̕ύX�ʒm�̋@�\��ǉ����Ă���
 */
public abstract class AbstractEditPart extends AbstractGraphicalEditPart
		implements Adapter {
	private ActionRegistry actionRegistry;

	Adapter defaultAdapterDelegate = new AdapterImpl();

	/**
	 * �R���X�g���N�^
	 * 
	 * @param actionRegistry
	 *            ActionRegistry
	 */
	public AbstractEditPart(ActionRegistry actionRegistry) {
		this.actionRegistry = actionRegistry;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Notifier getTarget() {
		return defaultAdapterDelegate.getTarget();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isAdapterForType(Object type) {
		return defaultAdapterDelegate.isAdapterForType(type);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setTarget(Notifier newTarget) {
		defaultAdapterDelegate.setTarget(newTarget);
	}

	/**
	 * ActionRegistry���擾����
	 * 
	 * @return ActionRegistry
	 */
	public ActionRegistry getActionRegistry() {
		return actionRegistry;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void activate() {
		super.activate();
		((ModelElement) getModel()).eAdapters().add(this);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void deactivate() {
		super.deactivate();
		((ModelElement) getModel()).eAdapters().remove(this);
	}

	@Override
	/**
	 * {@inheritDoc}
	 * <p>
	 * ���f���̃I�u�W�F�N�g�ɈϏ����Ă���
	 */
	public Object getAdapter(Class key) {
		Object result = AdapterUtil.getAdapter(getModel(), key);
		if (result == null) {
			result = super.getAdapter(key);
		}

		return result;
	}
}
