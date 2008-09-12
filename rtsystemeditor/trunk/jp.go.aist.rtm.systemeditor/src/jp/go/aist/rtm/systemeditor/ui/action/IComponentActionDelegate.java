package jp.go.aist.rtm.systemeditor.ui.action;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import jp.go.aist.rtm.toolscommon.model.component.Component;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * RtcLink�̌X�̃R���|�[�l���g���ꂼ��ɑ΂���A�N�V����
 */
public class IComponentActionDelegate implements IObjectActionDelegate {
	/**
	 * Start�Ɏg�p�����ID�B���̒l���APlugin.xml�Ɏw�肳��Ȃ���΂Ȃ�Ȃ��B
	 */
	public static final String START_ACTION_ID = IComponentActionDelegate.class
			.getName()
			+ ".executioncontext.Start";

	/**
	 * Stop�Ɏg�p�����ID�B���̒l���APlugin.xml�Ɏw�肳��Ȃ���΂Ȃ�Ȃ��B
	 */
	public static final String STOP_ACTION_ID = IComponentActionDelegate.class
			.getName()
			+ ".executioncontext.Stop";

	/**
	 * Activate�Ɏg�p�����ID�B���̒l���APlugin.xml�Ɏw�肳��Ȃ���΂Ȃ�Ȃ��B
	 */
	public static final String ACTIVATE_ACTION_ID = IComponentActionDelegate.class
			.getName()
			+ ".Activate";

	/**
	 * Deactivate�Ɏg�p�����ID�B���̒l���APlugin.xml�Ɏw�肳��Ȃ���΂Ȃ�Ȃ��B
	 */
	public static final String DEACTIVATE_ACTION_ID = IComponentActionDelegate.class
			.getName()
			+ ".Deactivate";

	/**
	 * Reset�Ɏg�p�����ID�B���̒l���APlugin.xml�Ɏw�肳��Ȃ���΂Ȃ�Ȃ��B
	 */
	public static final String RESET_ACTION_ID = IComponentActionDelegate.class
			.getName()
			+ ".Reset";

	/**
	 * Finalize�Ɏg�p�����ID�B���̒l���APlugin.xml�Ɏw�肳��Ȃ���΂Ȃ�Ȃ��B
	 */
	public static final String FINALIZE_ACTION_ID = IComponentActionDelegate.class
			.getName()
			+ ".Finalize";

	/**
	 * Exit�Ɏg�p�����ID�B���̒l���APlugin.xml�Ɏw�肳��Ȃ���΂Ȃ�Ȃ��B
	 */
	public static final String EXIT_ACTION_ID = IComponentActionDelegate.class
			.getName()
			+ ".Exit";

	private ISelection selection;

	private IWorkbenchPart targetPart;

	/**
	 * {@inheritDoc}
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.targetPart = targetPart;
	}

	/**
	 * �A�N�V���������Ŏg�p�����A���b�Z�[�W�ƃR�}���h���܂Ƃ߂��C���^�t�F�[�X
	 */
	public interface MessageAndCommand {
		public String getConfirmMessage();

		public int run();
	}

	/**
	 * {@inheritDoc}
	 */
	public void run(final IAction action) {

		for (Iterator iter = ((IStructuredSelection) selection).iterator(); iter
				.hasNext();) {

			final Component component = (Component) iter.next();

			MessageAndCommand command = null;
			if ((START_ACTION_ID).equals(action.getId())) {
				command = new MessageAndCommand() {
					public String getConfirmMessage() {
						return "Start���Ă��ǂ��ł����H";
					}

					public int run() {
						return component.startR();
					}
				};
			} else if (STOP_ACTION_ID.equals(action.getId())) {
				command = new MessageAndCommand() {
					public String getConfirmMessage() {
						return "Stop���Ă��ǂ��ł����H";
					}

					public int run() {
						return component.stopR();
					}
				};
			} else if (ACTIVATE_ACTION_ID.equals(action.getId())) {
				command = new MessageAndCommand() {
					public String getConfirmMessage() {
						return "Activate���Ă��ǂ��ł����H";
					}

					public int run() {
						return component.activateR();
					}
				};
			} else if (DEACTIVATE_ACTION_ID.equals(action.getId())) {
				command = new MessageAndCommand() {
					public String getConfirmMessage() {
						return "Deactivate���Ă��ǂ��ł����H";
					}

					public int run() {
						return component.deactivateR();
					}
				};
			} else if (RESET_ACTION_ID.equals(action.getId())) {
				command = new MessageAndCommand() {
					public String getConfirmMessage() {
						return "Reset���Ă��ǂ��ł����H";
					}

					public int run() {
						return component.resetR();
					}
				};
			} else if (EXIT_ACTION_ID.equals(action.getId())) {
				command = new MessageAndCommand() {
					public String getConfirmMessage() {
						return "Exit���Ă��ǂ��ł����H";
					}

					public int run() {
						return component.exitR();
					}
				};
			} else if (FINALIZE_ACTION_ID.equals(action.getId())) {
				command = new MessageAndCommand() {
					public String getConfirmMessage() {
						return "Finalize���Ă��ǂ��ł����H";
					}

					public int run() {
						return component.finalizeR();
					}
				};
			} else {
				throw new RuntimeException("�V�X�e���G���[");
			}

			boolean isOK = MessageDialog.openConfirm(targetPart.getSite()
					.getShell(), "�m�F", command.getConfirmMessage());
			if (isOK == false) {
				return;
			}

			final MessageAndCommand finalCommand = command;

			final int[] returnCode = new int[1]; // final�z�񉻂��邱�ƂŁA�N���[�W�����ŕԂ�l��ݒ肷�邱�Ƃ��ł���悤�ɂ���B
			ProgressMonitorDialog dialog = new ProgressMonitorDialog(targetPart
					.getSite().getShell());
			IRunnableWithProgress runable = new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {

					monitor.beginTask("�R���|�[�l���g�̏�Ԃ�ύX���܂�", 100);

					monitor.worked(20);
					monitor.subTask("�R���|�[�l���g�փ��N�G�X�g�𑗂��Ă��܂�...");

					returnCode[0] = finalCommand.run();

					monitor.subTask("�R���|�[�l���g�փ��N�G�X�g�𑗂�܂����B");
					monitor.done();
				}
			};

			try {
				dialog.run(false, false, runable);
			} catch (Exception e) {
				e.printStackTrace(); // system error
			}

			if (Component.RETURNCODE_OK == returnCode[0]) {
				// void
			} else if (Component.RETURNCODE_ERROR == returnCode[0]) {
				MessageDialog.openError(targetPart.getSite().getShell(), "�G���[",
						"�G���[���������܂����B");
			} else if (Component.RETURNCODE_BAD_PARAMETER == returnCode[0]) {
				MessageDialog.openError(targetPart.getSite().getShell(), "�G���[",
						"�s���ȃp�����[�^�ł��B");
			} else if (Component.RETURNCODE_UNSUPPORTED == returnCode[0]) {
				MessageDialog.openError(targetPart.getSite().getShell(), "�G���[",
						"�T�|�[�g����Ă��܂���B");
			} else if (Component.RETURNCODE_OUT_OF_RESOURCES == returnCode[0]) {
				MessageDialog.openError(targetPart.getSite().getShell(), "�G���[",
						"���\�[�X�s���̃G���[���������܂����B");
			} else if (Component.RETURNCODE_PRECONDITION_NOT_MET == returnCode[0]) {
				MessageDialog.openError(targetPart.getSite().getShell(), "�G���[",
						"���O�������s���ł��B");
			}

		}

	}

	/**
	 * {@inheritDoc}
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}
}
