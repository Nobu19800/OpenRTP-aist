package jp.go.aist.rtm.systemeditor.ui.editor.editpart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.ui.action.OpenCompositeComponentAction;
import jp.go.aist.rtm.systemeditor.ui.editor.action.ChangeComponentDirectionAction;
import jp.go.aist.rtm.systemeditor.ui.editor.editpolicy.ChangeDirectionEditPolicy;
import jp.go.aist.rtm.systemeditor.ui.editor.editpolicy.ComponentComponentEditPolicy;
import jp.go.aist.rtm.systemeditor.ui.editor.editpolicy.EditPolicyConstraint;
import jp.go.aist.rtm.systemeditor.ui.editor.figure.ComponentLayout;
import jp.go.aist.rtm.systemeditor.ui.util.Draw2dUtil;
import jp.go.aist.rtm.toolscommon.model.component.AbstractComponent;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.LifeCycleState;
import jp.go.aist.rtm.toolscommon.model.core.CorePackage;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.Panel;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.PlatformUI;

/**
 * �R���|�[�l���g��EditPart
 * <p>
 * GEF�̎d�l�ł͎q����EditPart�͐e��EditPart�Ɋ܂܂�Ȃ���΂Ȃ�Ȃ����A�|�[�g���R���|�[�l���g����͂ݏo���ĕ\�����Ȃ���΂Ȃ�Ȃ��B
 * ����𖞂����Ȃ���A�ꌩ�R���|�[�l���g�̊O�Ƀ|�[�g���o�Ă���悤�Ɍ����邽�߂ɁA�R���|�[�l���g�̃{�f�B�̃h���[�C���O�͈̔͂����߂邱�ƂŎ������Ă��邽�߁A����Ȏ����ɂȂ��Ă���B
 */
public class ComponentEditPart extends AbstractEditPart {

	/**
	 * �R���|�[�l���g�̎���ƃR���|�[�l���g�̃{�f�B�܂ł̃X�y�[�X
	 */
	public static final int SPACE = 7;

	private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	private ComponentFloatingLabel componentLabel;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param actionRegistry
	 *            ActionRegistry
	 */
	public ComponentEditPart(ActionRegistry actionRegistry) {
		super(actionRegistry);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected IFigure createFigure() {
		AbstractComponent component = getModel();

		Figure result = new Panel() {

			@Override
			/**
			 * {@inheritDoc}
			 */
			protected boolean useLocalCoordinates() {
				return true;
			}

			@Override
			/**
			 * {@inheritDoc}
			 * <p>
			 * �R���|�[�l���g�̊O�Ƀ|�[�g���o�Ă���悤�Ɍ����邽�߂ɁA�R���|�[�l���g�̃{�f�B�̃h���[�C���O�͈̔͂����߂Ă���
			 */
			protected void paintFigure(Graphics graphics) {
				if (isOpaque()) {
					Rectangle bound = new Rectangle(getBounds());
					graphics.fillRectangle(bound.expand(-SPACE, -SPACE));

					Color saveForegroundColor = graphics.getForegroundColor();
					// graphics.setForegroundColor(ColorConstants.black);
					graphics.drawRectangle(bound);
					graphics.setForegroundColor(saveForegroundColor);
				}
			}

			@Override
			/**
			 * {@inheritDoc}
			 * <p>
			 * �R���|�[�l���g�̊O�Ƀ|�[�g���o�Ă���悤�Ɍ����邽�߁A�����
			 */
			protected void paintBorder(Graphics graphics) {
				// void
			}

			@Override
			/**
			 * {@inheritDoc}
			 * <p>
			 * �R���|�[�l���g�̐��񂪕ύX����邽�тɁA���x�����ړ�������B
			 * �i�Ӗ��̕������炷��΂��܂�悭�Ȃ����A�t�@�C�����ɕ��Ă���̂ł����Ɏ����ɂ����j
			 */
			public void setBounds(Rectangle rect) {
				super.setBounds(rect);

				Rectangle newComponentLabelRectangle = componentLabel
						.getTextBounds().getCopy();
				newComponentLabelRectangle.x = rect.getCenter().x
						- componentLabel.getTextBounds().width / 2;
				newComponentLabelRectangle.y = rect.getBottom().y;

				componentLabel.setBounds(newComponentLabelRectangle);

				propertyChangeSupport.firePropertyChange("Bounds", null, rect);
			}

		};

		result.addMouseListener(new MouseListener.Stub() {
			@Override
			/**
			 * {@inheritDoc}
			 * <p>
			 * �R���|�[�l���g���E�N���b�N�i+Shift�j���āA������ϊ�����@�\�̎���
			 */
			public void mousePressed(MouseEvent me) {
				if (me.button == 2) { // right click
					IAction action;
					if (me.getState() == MouseEvent.SHIFT) {
						action = getActionRegistry()
								.getAction(
										ChangeComponentDirectionAction.VERTICAL_DIRECTION_ACTION_ID);
					} else {
						action = getActionRegistry()
								.getAction(
										ChangeComponentDirectionAction.HORIZON_DIRECTION_ACTION_ID);
					}

					action.run();
				}
			}
		});

		ComponentLayout layout = new ComponentLayout(getModel());
		result.setLayoutManager(layout);

		result.setBackgroundColor(ColorConstants.orange);

		// ���ӁFComponentLabel�̐e��SystemDiagram
		componentLabel = new ComponentFloatingLabel(
				((AbstractGraphicalEditPart) getParent()).getFigure());
		componentLabel.setText(getModel().getInstanceNameL());
		componentLabel.setSize(30, 10);

		return result;
	}

	/**
	 * �ݒ�}�l�[�W�����Ď����郊�X�i
	 */
	PropertyChangeListener preferenceChangeListener = new PropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent evt) {
			refreshComponent();
		}
	};

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void activate() {
		super.activate();
		if (getModel().isCompositeComponent()) {
			for (Iterator iterator = getModel().getAllComponents().iterator(); iterator
					.hasNext();) {
				AbstractComponent component = (AbstractComponent) iterator.next();
				component.eAdapters().add(this);
			}
		}
		SystemEditorPreferenceManager.getInstance().addPropertyChangeListener(
				preferenceChangeListener);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public void deactivate() {
		componentLabel.deactivate();
		super.deactivate();
		if (getModel().isCompositeComponent()) {
			for (Iterator iterator = getModel().getAllComponents().iterator(); iterator
					.hasNext();) {
				AbstractComponent component = (AbstractComponent) iterator.next();
				component.eAdapters().remove(this);
			}
		}
		SystemEditorPreferenceManager.getInstance()
				.removePropertyChangeListener(preferenceChangeListener);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE,
				new ComponentComponentEditPolicy());

		installEditPolicy(EditPolicyConstraint.CHANGE_DIRECTION_ROLE,
				new ChangeDirectionEditPolicy());
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void refreshVisuals() {
		getFigure().setBackgroundColor(getNewBodyColor());

		getFigure().setForegroundColor(getNewBorderColor());

		Rectangle modelRec = Draw2dUtil.toDraw2dRectangle(getModel()
				.getConstraint());

		Rectangle newRectangle = modelRec.getCopy();

		((GraphicalEditPart) getParent()).setLayoutConstraint(this,
				getFigure(), newRectangle);
	}

	/**
	 * �ŐV�̃{�[�_�[���̐F���擾����
	 * 
	 * @return
	 */
	public Color getNewBorderColor() {
		Color exexucitonContextColor = SystemEditorPreferenceManager
				.getInstance().getColor(
						SystemEditorPreferenceManager.COLOR_RTC_STATE_UNKNOWN);
		if (getModel() instanceof Component) {
			Component component = (Component) getModel();
			if (component.getExecutionContextState() == ExecutionContext.STATE_RUNNING) {
				exexucitonContextColor = SystemEditorPreferenceManager
						.getInstance()
						.getColor(
								SystemEditorPreferenceManager.COLOR_RTC_EXECUTION_CONTEXT_RUNNING);
			} else if (component.getExecutionContextState() == ExecutionContext.STATE_STOPPED) {
				exexucitonContextColor = SystemEditorPreferenceManager
						.getInstance()
						.getColor(
								SystemEditorPreferenceManager.COLOR_RTC_EXECUTION_CONTEXT_STOPPED);
			} else if (component.getExecutionContextState() == ExecutionContext.STATE_UNKNOWN) {
				exexucitonContextColor = SystemEditorPreferenceManager
						.getInstance()
						.getColor(
								SystemEditorPreferenceManager.COLOR_RTC_STATE_UNKNOWN);
			}
		}
		

		return exexucitonContextColor;
	}

	/**
	 * �ŐV�̃{�f�B���̐F���擾����
	 * 
	 * @return
	 */
	public Color getNewBodyColor() {
		Color stateColor = SystemEditorPreferenceManager
				.getInstance()
				.getColor(SystemEditorPreferenceManager.COLOR_RTC_STATE_UNKNOWN);
		if (getModel() instanceof Component) {
			Component component = (Component) getModel();
			if (component.isCompositeComponent()) {
				boolean isError = false;
				int componentCount = 0;
				int activeCount = 0;
				stateColor = SystemEditorPreferenceManager.getInstance().getColor(
						SystemEditorPreferenceManager.COLOR_RTC_STATE_INACTIVE);
				for (Iterator<Component> iterator = component.getAllComponents()
						.iterator(); iterator.hasNext();) {
					Component temp = iterator.next();
					if (!temp.isCompositeComponent()){
						componentCount++;
						if (temp.getComponentState() == LifeCycleState.RTC_ERROR) {
							isError = true;
							break;
						} else if (temp.getComponentState() == LifeCycleState.RTC_ACTIVE) {
							activeCount++;
						}
					}
				}
				if (isError) {
					stateColor = SystemEditorPreferenceManager.getInstance().getColor(
							SystemEditorPreferenceManager.COLOR_RTC_STATE_ERROR);
				}else if (componentCount > 0
						&& componentCount == activeCount){
					stateColor = SystemEditorPreferenceManager.getInstance().getColor(
							SystemEditorPreferenceManager.COLOR_RTC_STATE_ACTIVE);
				}
			} else {
				if (component.getComponentState() == LifeCycleState.RTC_ACTIVE) {
					stateColor = SystemEditorPreferenceManager.getInstance().getColor(
							SystemEditorPreferenceManager.COLOR_RTC_STATE_ACTIVE);
				} else if (component.getComponentState() == LifeCycleState.RTC_CREATED) {
					stateColor = SystemEditorPreferenceManager.getInstance().getColor(
							SystemEditorPreferenceManager.COLOR_RTC_STATE_CREATED);
				} else if (component.getComponentState() == LifeCycleState.RTC_ERROR) {
					stateColor = SystemEditorPreferenceManager.getInstance().getColor(
							SystemEditorPreferenceManager.COLOR_RTC_STATE_ERROR);
				} else if (component.getComponentState() == LifeCycleState.RTC_INACTIVE) {
					stateColor = SystemEditorPreferenceManager.getInstance().getColor(
							SystemEditorPreferenceManager.COLOR_RTC_STATE_INACTIVE);
				} else if (component.getComponentState() == LifeCycleState.RTC_UNKNOWN) {
					stateColor = SystemEditorPreferenceManager.getInstance().getColor(
							SystemEditorPreferenceManager.COLOR_RTC_STATE_UNKNOWN);
				}
			}
			
		} else if (getModel() instanceof ComponentSpecification){
			stateColor = SystemEditorPreferenceManager.getInstance().getColor(
					SystemEditorPreferenceManager.COLOR_RTC_STATE_INACTIVE);
		}
		

		return stateColor;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public AbstractComponent getModel() {
		return (AbstractComponent) super.getModel();
	}

	/**
	 * {@inheritDoc}component.eAdapters().add(this);
	 */
	public void notifyChanged(Notification notification) {
		if (ComponentPackage.eINSTANCE.getAbstractComponent_Components().equals(
						notification.getFeature())) {
			if (notification.getEventType() == Notification.ADD) {
				AbstractComponent component = (AbstractComponent)notification.getNewValue();
				component.eAdapters().add(this);
				if (component.isCompositeComponent()) {
					for (Iterator iterator = component.getAllComponents()
							.iterator(); iterator.hasNext();) {
						((AbstractComponent) iterator.next()).eAdapters().add(this);
					}
				}
			}else if (notification.getEventType() == Notification.REMOVE) {
				((AbstractComponent)notification.getOldValue()).eAdapters().remove(this);
			}
			refreshComponent();
			((SystemDiagramEditPart)getParent()).refreshSystemDiagram();
		} else if (ComponentPackage.eINSTANCE
				.getAbstractComponent_CompositeComponent().equals(
						notification.getFeature())
				|| CorePackage.eINSTANCE.getModelElement_Constraint().equals(
						notification.getFeature())
				|| ComponentPackage.eINSTANCE.getComponent_ComponentState()
						.equals(notification.getFeature())
				|| ComponentPackage.eINSTANCE
						.getComponent_ExecutionContextState().equals(
								notification.getFeature())
				|| ComponentPackage.eINSTANCE
						.getAbstractComponent_OutportDirection().equals(
								notification.getFeature())) {
			refreshComponent();
		}else if (getModel() instanceof ComponentSpecification) {
			refreshComponent();
		}
	}

	private void refreshComponent() {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			public void run() {
				if (isActive()) {
					refresh();
					refreshChildren();
					getFigure().invalidate();
				}
			}
		});
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected List getModelChildren() {
		List result = new ArrayList();
		result.addAll(getModel().getAllInPorts());
		result.addAll(getModel().getAllOutPorts());
		result.addAll(getModel().getAllServiceports());

		return result;
	}

	/**
	 * �R���|�[�l���gFigure�̕ύX�̒ʒm���s�����X�i��o�^����
	 * 
	 * @param listener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	/**
	 * �R���|�[�l���gFigure�̕ύX�̒ʒm���s�����X�i���폜����
	 * 
	 * @param listener
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	/**
	 * �V�X�e���_�C�A�O�����̃R���|�[�l���g�ɕ\������郉�x��
	 */
	public class ComponentFloatingLabel extends Label {

		/**
		 * �R���X�g���N�^
		 * 
		 * @param parentFigure
		 *            �e�t�B�M���A
		 */
		public ComponentFloatingLabel(IFigure parentFigure) {
			setParent(parentFigure);
			parentFigure.add(this);
		}

		/**
		 * �폜����ꍇ�ɌĂяo����邱�Ƃ��Ӑ}����
		 */
		public void deactivate() {
			getParent().remove(this);
		}

		@Override
		/**
		 * {@inheritDoc}
		 */
		public boolean isFocusTraversable() {
			return false;
		}

		@Override
		/**
		 * {@inheritDoc}
		 */
		public boolean isRequestFocusEnabled() {
			return false;
		}

		@Override
		/**
		 * {@inheritDoc}
		 */
		protected boolean isMouseEventTarget() {
			return false;
		}
	}

	@Override
	public void performRequest(Request req) {
		if (req.getType().equals(RequestConstants.REQ_OPEN)) {
			IAction action = getActionRegistry().getAction(
					OpenCompositeComponentAction.ACTION_ID);
			OpenCompositeComponentAction openAction = null;
			if (getModel().isCompositeComponent()) {
				openAction = (OpenCompositeComponentAction) getModel().getOpenCompositeComponentAction();
				if (openAction == null) {
					openAction = new OpenCompositeComponentAction(
							((OpenCompositeComponentAction) action)
									.getParentSystemDiagramEditor());
				}
				getModel().setOpenCompositeComponentAction(openAction);
				openAction.setCompositeComponent(getModel());
				openAction.run();
			}
		}
		super.performRequest(req);
	}
}
