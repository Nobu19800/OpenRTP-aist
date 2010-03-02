package jp.go.aist.rtm.nameserviceview.ui.dialog;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import jp.go.aist.rtm.nameserviceview.NameServiceViewPlugin;
import jp.go.aist.rtm.nameserviceview.manager.AlreadyExistException;
import jp.go.aist.rtm.nameserviceview.manager.NameServerContext;
import jp.go.aist.rtm.nameserviceview.manager.NameServerManager;
import jp.go.aist.rtm.nameserviceview.nl.Messages;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * �l�[���T�[�o��ǉ�����_�C�A���O
 * <p>
 * �R���{�{�b�N�X�ɂ́A�ڑ��������������Ƃ̂���A�h���X�̈ꗗ���\������A�Ō�ɐڑ������A�h���X���I�������B
 */
public class NameServerSectionsDialog extends Dialog {
	private enum AddStatus{SUCCESS, CANT_CONNECT, CANT_CREATE_OBJECT_TREE, ALREADY_CONNECT}

	/**
	 * �ڑ��������������Ƃ̂���A�h���X�̈ꗗ��ۑ�����A���[�N�X�y�[�X�i��������ւ̃L�[
	 */
	public static final String COMBO_ITEMS_KEY = NameServerSectionsDialog.class
			.getName()
			+ ".combo.items"; //$NON-NLS-1$

	/**
	 * �Ō�ɐڑ������������A�h���X�̃C���f�b�N�X��ۑ�����A���[�N�X�y�[�X�i��������ւ̃L�[
	 */
	public static final String COMBO_SELECTION_INDEX_KEY = NameServerSectionsDialog.class
			.getName()
			+ ".combo.selectIndex"; //$NON-NLS-1$

	private Combo combo;

	private String value = ""; //$NON-NLS-1$

	private Label message;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param shell
	 */
	public NameServerSectionsDialog(Shell shell) {
		super(shell);
		setShellStyle(getShellStyle() | SWT.CENTER | SWT.RESIZE);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected Control createDialogArea(Composite parent) {
		GridLayout gridLayout = new GridLayout();

		Composite mainComposite = (Composite) super.createDialogArea(parent);
		mainComposite.setLayout(gridLayout);
		mainComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label label = new Label(mainComposite, SWT.NONE);
		label.setText(Messages.getString("NameServerSectionsDialog.3")); //$NON-NLS-1$
		GridData labelLayloutData = new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING);
		label.setLayoutData(labelLayloutData);
		labelLayloutData.heightHint = 20;

		GridLayout comboLayout = new GridLayout(2, false);
		Composite comboComposite = new Composite(mainComposite, SWT.NONE);
		comboComposite.setLayout(comboLayout);
		comboComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		combo = new Combo(comboComposite, SWT.NONE);
		GridData comboGridData = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
		comboGridData.minimumWidth = 180;
		comboGridData.horizontalAlignment = GridData.FILL;
		comboGridData.grabExcessHorizontalSpace = true;
		combo.setLayoutData(comboGridData);
		Label comboLabel = new Label(comboComposite, SWT.NONE);
		comboLabel.setText(Messages.getString("NameServerSectionsDialog.17")); //$NON-NLS-1$
		comboLabel
				.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER));

		message = new Label(mainComposite, SWT.NONE);
		message.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label line = new Label(mainComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
		line.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));

		combo.setFocus();
		loadDefaultComboValue(combo);

		return mainComposite;
	}

	/**
	 * ���[�N�X�y�[�X�̉i����񂩂�A�R���{�̃��X�g�ƑI���C���f�b�N�X�����[�h����
	 * 
	 * @param combo
	 */
	private void loadDefaultComboValue(Combo combo) {
		String defaultString = NameServiceViewPlugin.getDefault().getPreferenceStore()
				.getString(COMBO_ITEMS_KEY);
		StringTokenizer tokenize = new StringTokenizer(defaultString, ","); //$NON-NLS-1$
		while (tokenize.hasMoreTokens()) {
			combo.add(tokenize.nextToken());
		}

		combo.select(NameServiceViewPlugin.getDefault().getPreferenceStore().getInt(
				COMBO_SELECTION_INDEX_KEY));
	}

	/**
	 * ���������A�h���X����ёI���C���f�b�N�X���A�i�����ɐݒ肷��
	 * 
	 * @param combo
	 */
	private void addDefaultComboValue(Combo combo) {
		String value = combo.getText(); // local

		List<String> valueList = Arrays.asList(combo.getItems());
		if (valueList.contains(value) == false) {
			String defaultString = NameServiceViewPlugin.getDefault().getPreferenceStore()
					.getString(COMBO_ITEMS_KEY);

			String newString = ""; //$NON-NLS-1$
			if ("".equals(defaultString)) { //$NON-NLS-1$
				newString = value;
			} else {
				newString = value + "," + defaultString; //$NON-NLS-1$
			}

			NameServiceViewPlugin.getDefault().getPreferenceStore().setValue(
					COMBO_ITEMS_KEY, newString);
		}

		int selectionIndex = valueList.indexOf(value);
		if (selectionIndex == -1) { // �V�������͂��s�����ꍇ
			selectionIndex = 0;
		}
		NameServiceViewPlugin.getDefault().getPreferenceStore().setValue(
				COMBO_SELECTION_INDEX_KEY, selectionIndex);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(Messages.getString("NameServerSectionsDialog.18")); //$NON-NLS-1$
	}

	@Override
	/**
	 * {@inheritDoc}
	 * <p>
	 * OK�{�^���������Ɏ��s���s���A���Ȃ��Ȃ�΁A���������A�h���X��ۑ�����B
	 */
	protected void okPressed() {
		value = combo.getText();
		if (execute(value)) {
			addDefaultComboValue(combo);
			super.okPressed();
		} else {
			combo.setFocus();
		}
	}

	/**
	 * �A�h���X�̃o���f�[�g���s���A���Ȃ��ꍇ�ɂ̓A�h���X��ǉ�����
	 * <P>
	 * �o���f�[�g�Ƃ��Ă͈ȉ����s��
	 * <ol>
	 * <li>�K�{�`�F�b�N</li>
	 * <li>�ڑ��ς݃A�h���X�ł��邩�ǂ���</li>
	 * <li>�ڑ��ł��邩</li>
	 * </ol>
	 * 
	 * @param address
	 * @return
	 */
	private boolean execute(final String address) {
		if ("".equals(address)) { //$NON-NLS-1$
			message.setText(Messages.getString("NameServerSectionsDialog.11")); //$NON-NLS-1$
			return false;
		}

		boolean isExist = NameServerManager.eInstance.isExist(address);
		if (isExist) {
			message.setText(Messages.getString("NameServerSectionsDialog.12")); //$NON-NLS-1$
			return false;
		}

		ProgressMonitorDialog dialog = new ProgressMonitorDialog(getShell());
		ConnectToNameServer runable = new ConnectToNameServer(address);
		try {
			dialog.run(false, false, runable);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (runable.getResult() == AddStatus.CANT_CONNECT) {
			message.setText(Messages.getString("NameServerSectionsDialog.13")); //$NON-NLS-1$
			return false;
		} else if (runable.getResult() == AddStatus.CANT_CREATE_OBJECT_TREE) {
			message.setText(Messages.getString("NameServerSectionsDialog.14")); //$NON-NLS-1$
			return false;
		} else if (runable.getResult() == AddStatus.ALREADY_CONNECT) {
			message.setText(Messages.getString("NameServerSectionsDialog.12")); //$NON-NLS-1$
			return false;
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * �l�[���T�[�o�փA�N�Z�X���A�I�u�W�F�N�g�Q���\�z���āA�l�[���T�[�o��ǉ�����
	 */
	private final class ConnectToNameServer implements IRunnableWithProgress {
		private final String value;

		private AddStatus result;

		/**
		 * ���ʂ�Ԃ��B�iCANT_CONNECT,CANT_CREATE_OBJECT_TREE,SUCCESS�j
		 * 
		 * @return ����
		 */
		public AddStatus getResult() {
			return result;
		}

		private ConnectToNameServer(String value) {
			super();
			this.value = value;
		}

		/**
		 * {@inheritDoc}
		 */
		public void run(IProgressMonitor monitor)
				throws InvocationTargetException, InterruptedException {
			result = AddStatus.CANT_CONNECT;
			try {
				monitor.beginTask(Messages.getString("NameServerSectionsDialog.15"), 100); //$NON-NLS-1$
				monitor.worked(20);
				NameServerContext server = NameServerManager.eInstance.addNameServer(value);
				if (server != null)	result = AddStatus.SUCCESS;
			} catch (AlreadyExistException e) {
				result = AddStatus.ALREADY_CONNECT;
			} catch (Exception e) {
				e.printStackTrace();
			}
			monitor.done();
		}
	}
	
	@Override
	protected Point getInitialSize() {
		return getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
	}

}
