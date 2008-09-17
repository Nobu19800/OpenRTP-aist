package jp.go.aist.rtm.systemeditor.ui.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import jp.go.aist.rtm.systemeditor.ui.editor.SystemDiagramEditor;
import jp.go.aist.rtm.toolscommon.corba.CorbaUtil;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.LifeCycleState;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.core.ModelElement;
import jp.go.aist.rtm.toolscommon.model.core.Visiter;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;

import RTC.ExecutionContext;
import RTC.ExecutionContextHelper;

/**
 * AllStart,AllStop�����s����A�N�V����
 */
public class AllComponentActionDelegate implements IEditorActionDelegate {

	/**
	 * AllStart�Ɏg�p�����ID�B���̒l���APlugin.XML�Ɏw�肳��Ȃ���΂Ȃ�Ȃ��B
	 */
	public static final String ALL_START_ACTION_ID = AllComponentActionDelegate.class
			.getName()
			+ ".AllStart";

	/**
	 * AllStop�Ɏg�p�����ID�B���̒l���APlugin.XML�Ɏw�肳��Ȃ���΂Ȃ�Ȃ��B
	 */
	public static final String ALL_STOP_ACTION_ID = AllComponentActionDelegate.class
			.getName()
			+ ".AllStop";

	/**
	 * AllActivate�Ɏg�p�����ID�B���̒l���APlugin.XML�Ɏw�肳��Ȃ���΂Ȃ�Ȃ��B
	 */
	public static final String ALL_ACTIVATE_ACTION_ID = AllComponentActionDelegate.class
			.getName()
			+ ".AllActivate";

	/**
	 * AllDeactivate�Ɏg�p�����ID�B���̒l���APlugin.XML�Ɏw�肳��Ȃ���΂Ȃ�Ȃ��B
	 */
	public static final String ALL_DEACTIVATE_ACTION_ID = AllComponentActionDelegate.class
			.getName()
			+ ".AllDeactivate";

	private SystemDiagramEditor targetEditor;

	/**
	 * �A�N�V�����̃��C���̎��s���\�b�h
	 * <p>
	 * ���s�ۂ̊m�F�����߁A���s���s���B
	 */
	public void run(final IAction action) {
		final SystemDiagram systemDiagram = targetEditor.getSystemDiagram();

		String comfirmMessage = "�ǂ��ł����H";
		if (ALL_START_ACTION_ID.equals(action.getId())) {
			comfirmMessage = "���ׂẴR���|�[�l���g��Start���Ă��ǂ��ł����H";
		} else if (ALL_STOP_ACTION_ID.equals(action.getId())) {
			comfirmMessage = "���ׂẴR���|�[�l���g��Stop���Ă��ǂ��ł����H";
		} else if (ALL_ACTIVATE_ACTION_ID.equals(action.getId())) {
			comfirmMessage = "���ׂẴR���|�[�l���g��Activate���Ă��ǂ��ł����H";
		} else if (ALL_DEACTIVATE_ACTION_ID.equals(action.getId())) {
			comfirmMessage = "���ׂẴR���|�[�l���g��Deactivate���Ă��ǂ��ł����H";
		}

		boolean isOk = MessageDialog.openConfirm(targetEditor.getSite()
				.getShell(), "�m�F", comfirmMessage);
		if (isOk) {

			ProgressMonitorDialog dialog = new ProgressMonitorDialog(
					targetEditor.getSite().getShell());
			IRunnableWithProgress runable = new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {

					monitor.beginTask("�R���|�[�l���g�̏�Ԃ�ύX���܂�", 100);

					monitor.subTask("�R���|�[�l���g�փ��N�G�X�g�𑗂��Ă��܂�...");

					try {
						if (ALL_START_ACTION_ID.equals(action.getId())) {
							doAllStart(systemDiagram);
						} else if (ALL_STOP_ACTION_ID.equals(action.getId())) {
							doAllStop(systemDiagram);
						} else if (ALL_ACTIVATE_ACTION_ID
								.equals(action.getId())) {
							doAllActivate(systemDiagram);
						} else if (ALL_DEACTIVATE_ACTION_ID.equals(action
								.getId())) {
							doAllDectivate(systemDiagram);
						}
					} catch (Exception e) {
						throw new InvocationTargetException(e);
					}

					monitor.subTask("�R���|�[�l���g�փ��N�G�X�g�𑗂�܂����B");
					monitor.done();
				}

			};

			try {
				dialog.run(false, false, runable);
			} catch (InvocationTargetException e) {
				MessageDialog.openError(targetEditor.getSite().getShell(),
						"�G���[", "���N�G�X�g�̑��M���ɃG���[���������܂���:\r\n" + e.getMessage());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private void doAllStop(SystemDiagram systemDiagram) {
		List<ExecutionContext> executionContextList = getExecutionContextList(systemDiagram);

		for (ExecutionContext executionContext : executionContextList) {
			try {
				executionContext.stop();
			} catch (Exception e) {
				e.printStackTrace(); // system error
			}
		}
	}

	private void doAllStart(SystemDiagram systemDiagram) {
		List<ExecutionContext> executionContextList = getExecutionContextList(systemDiagram);

		for (ExecutionContext executionContext : executionContextList) {
			try {
				executionContext.start();
			} catch (Exception e) {
				e.printStackTrace(); // system error
			}
		}
	}

	private void doAllActivate(SystemDiagram systemDiagram) {
		systemDiagram.accept(new Visiter() {
			public void visit(ModelElement element) {
				if (element instanceof Component) {
					for (Iterator iter = ((Component) element)
							.getLifeCycleStates().iterator(); iter.hasNext();) {
						LifeCycleState lifeCycleState = (LifeCycleState) iter
								.next();
						lifeCycleState.activateR();
					}
				}
			}
		});
	}

	private void doAllDectivate(SystemDiagram systemDiagram) {
		systemDiagram.accept(new Visiter() {
			public void visit(ModelElement element) {
				if (element instanceof Component) {
					for (Iterator iter = ((Component) element)
							.getLifeCycleStates().iterator(); iter.hasNext();) {
						LifeCycleState lifeCycleState = (LifeCycleState) iter
								.next();
						lifeCycleState.deactivateR();
					}
				}
			}
		});
	}

	/**
	 * ExcutionContext���d�����Ȃ��悤�Ɏ擾����
	 * 
	 * @param systemDiagram
	 * @return
	 */
	private List<ExecutionContext> getExecutionContextList(
			SystemDiagram systemDiagram) {
		Set<String> executionContextStringList = new HashSet<String>();
		for (Iterator iter = systemDiagram.eAllContents(); iter.hasNext();) {
			Object obj = iter.next();
			if (obj instanceof Component) {
				ExecutionContext[] get_contexts = ((Component) obj)
						.getCorbaObjectInterface().get_owned_contexts();
				if (get_contexts != null) {
					for (ExecutionContext context : get_contexts) {
						executionContextStringList.add(context.toString());
					}
				}
			}
		}

		List<ExecutionContext> result = new ArrayList<ExecutionContext>();
		for (String executionContextString : executionContextStringList) {
			result.add(ExecutionContextHelper.narrow(CorbaUtil
					.stringToObject(executionContextString)));
		}

		return result;
	}

	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		this.targetEditor = (SystemDiagramEditor) targetEditor;
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}
}
