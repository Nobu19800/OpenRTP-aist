package jp.go.aist.rtm.repositoryView.ui.dialog;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import jp.go.aist.rtm.repositoryView.RepositoryViewPlugin;
import jp.go.aist.rtm.repositoryView.manager.RTRepositoryManager;
import jp.go.aist.rtm.repositoryView.model.RepositoryViewItem;
import jp.go.aist.rtm.repositoryView.nl.Messages;
import jp.go.aist.rtm.repositoryView.repository.RTRepositoryAccesser;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * RT���|�W�g���ւ̐ڑ��_�C�A���O
 *
 */
public class RTRepositoryConnectDialog extends Dialog {

	/**
	 * �ڑ��v������ �ڑ��Ɏ��s�������Ƃ������萔
	 */
	private static final int CANT_CONNECT = 1;

	/**
	 * �ڑ��v������ �ڑ��ɐ������ART���|�W�g���̈ꗗ�c���[�쐬�Ɏ��s�������Ƃ������萔
	 */
	private static final int CANT_CREATE_RTC_TREE = 2;

	/**
	 * �ڑ��v������ ����
	 */
	private static final int SUCCESS = 0;

	/**
	 * �ڑ��������������Ƃ̂���A�h���X�̈ꗗ��ۑ�����A���[�N�X�y�[�X�i��������ւ̃L�[
	 */
	private static final String COMBO_ITEMS_KEY = RTRepositoryConnectDialog.class
			.getName() + ".combo.items"; //$NON-NLS-1$

	/**
	 * �Ō�ɐڑ������������A�h���X�̃C���f�b�N�X��ۑ�����A���[�N�X�y�[�X�i��������ւ̃L�[
	 */
	private static final String COMBO_SELECTION_INDEX_KEY = RTRepositoryConnectDialog.class
			.getName() + ".combo.selectIndex"; //$NON-NLS-1$

	private Combo combo;
	private String value = ""; //$NON-NLS-1$
	private Label message;
	//
	private RepositoryViewItem resultItem;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param shell
	 */
	public RTRepositoryConnectDialog(Shell shell) {
		super(shell);
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
		label.setText(Messages.getString("RTRepositoryConnectDialog.3")); //$NON-NLS-1$
		GridData labelLayloutData = new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING);
		label.setLayoutData(labelLayloutData);
		labelLayloutData.heightHint = 20;

		GridLayout comboLayout = new GridLayout(1, false);
		Composite comboComposite = new Composite(mainComposite, SWT.NONE);
		comboComposite.setLayout(comboLayout);
		comboComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		combo = new Combo(comboComposite, SWT.NONE);
		GridData comboGridData = new GridData(GridData.FILL_HORIZONTAL);
		comboGridData.grabExcessHorizontalSpace = true;
		combo.setLayoutData(comboGridData);

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
		String defaultString = RepositoryViewPlugin.getDefault().getPreferenceStore()
				.getString(COMBO_ITEMS_KEY);
		StringTokenizer tokenize = new StringTokenizer(defaultString, ","); //$NON-NLS-1$
		while (tokenize.hasMoreTokens()) {
			combo.add(tokenize.nextToken());
		}

		combo.select(RepositoryViewPlugin.getDefault().getPreferenceStore().getInt(
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
			String defaultString = RepositoryViewPlugin.getDefault()
					.getPreferenceStore().getString(COMBO_ITEMS_KEY);

			String newString = ""; //$NON-NLS-1$
			if ("".equals(defaultString)) { //$NON-NLS-1$
				newString = value;
			} else {
				newString = value + "," + defaultString; //$NON-NLS-1$
			}

			RepositoryViewPlugin.getDefault().getPreferenceStore().setValue(
					COMBO_ITEMS_KEY, newString);
		}

		int selectionIndex = valueList.indexOf(value);
		if (selectionIndex == -1) { // �V�������͂��s�����ꍇ
			selectionIndex = 0;
		}
		RepositoryViewPlugin.getDefault().getPreferenceStore().setValue(
				COMBO_SELECTION_INDEX_KEY, selectionIndex);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(Messages.getString("RTRepositoryConnectDialog.8")); //$NON-NLS-1$
		int x = 500;
		int y = 200;

		shell.setBounds(shell.getDisplay().getBounds().width / 2 - x / 2, shell
				.getDisplay().getBounds().height
				/ 2 - y / 2, x, y);
	}

	@Override
	/**
	 * {@inheritDoc}
	 * <p>
	 * OK�{�^���������Ɏ��s���s���A���Ȃ��Ȃ�΁A��������URI��ۑ�����B
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

	public RepositoryViewItem getResultItem() {
		return resultItem;
	}

	/**
	 * URI�̃o���f�[�g���s���A���Ȃ��ꍇ�ɂ̓A�h���X��ǉ�����
	 * <P>
	 * �o���f�[�g�Ƃ��Ă͈ȉ����s��
	 * <ol>
	 * <li>�K�{�`�F�b�N</li>
	 * <li>�ڑ��ς݃A�h���X�ł��邩�ǂ���</li>
	 * <li>�ڑ��ł��邩</li>
	 * <li>RTC�c���[���쐬�ł��邩</li>
	 * </ol>
	 * 
	 * @param address
	 * @return
	 */
	private boolean execute(final String address) {
		if ("".equals(address)) { //$NON-NLS-1$
			message.setText(Messages.getString("RTRepositoryConnectDialog.10")); //$NON-NLS-1$
			return false;
		}

		boolean isExist = RTRepositoryManager.getInstance().isExist(address);
		if (isExist) {
			message.setText(Messages.getString("RTRepositoryConnectDialog.11")); //$NON-NLS-1$
			return false;
		}
		//
		ProgressMonitorDialog dialog = new ProgressMonitorDialog(getShell());
		ConnectToRTRepository runable = new ConnectToRTRepository(address);
		try {
			dialog.run(false, false, runable);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (runable.getResult() == CANT_CONNECT) {
			message.setText(Messages.getString("RTRepositoryConnectDialog.12")); //$NON-NLS-1$
			return false;
		} else if (runable.getResult() == CANT_CREATE_RTC_TREE) {
			message.setText(Messages.getString("RTRepositoryConnectDialog.13")); //$NON-NLS-1$
			return false;
		}

		resultItem = runable.getResultItem();

		return true;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * RT���|�W�g���փA�N�Z�X���ARTC�Q���\�z���āART���|�W�g����ǉ�����
	 */
	private final class ConnectToRTRepository implements IRunnableWithProgress {
		private final String value;

		private int result;
		private RepositoryViewItem resultItem;

		/**
		 * ���ʂ�Ԃ��B�iCANT_CONNECT,CANT_CREATE_RTC_TREE,SUCCESS�j
		 * 
		 * @return ����
		 */
		public int getResult() {
			return result;
		}

		public RepositoryViewItem getResultItem() {
			return resultItem;
		}

		private ConnectToRTRepository(String value) {
			super();
			this.value = value;
		}

		/**
		 * {@inheritDoc}
		 */
		public void run(IProgressMonitor monitor)
				throws InvocationTargetException, InterruptedException {
			result = CANT_CONNECT;
			try {
				monitor.beginTask(Messages.getString("RTRepositoryConnectDialog.14"), 100); //$NON-NLS-1$
				monitor.worked(20);
				boolean validateNameService = RTRepositoryAccesser.getInstance()
						.validateNameServerAddress(value);

				if (validateNameService) {
					result = CANT_CREATE_RTC_TREE;
					monitor.worked(50);
					monitor.setTaskName(Messages.getString("RTRepositoryConnectDialog.15")); //$NON-NLS-1$

					resultItem = RTRepositoryManager.getInstance().addRepository(value);

					result = SUCCESS;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			monitor.done();
		}
	}
}
