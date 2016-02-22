package jp.go.aist.rtm.nameserviceview.ui.dialog;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import jp.go.aist.rtm.nameserviceview.NameServiceViewPlugin;
import jp.go.aist.rtm.nameserviceview.model.manager.NameServerContext;
import jp.go.aist.rtm.nameserviceview.model.manager.NameServerManager;
import jp.go.aist.rtm.nameserviceview.model.manager.util.AlreadyExistException;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ネームサーバを追加するダイアログ
 * <p>
 * コンボボックスには、接続が成功したことのあるアドレスの一覧が表示され、最後に接続したアドレスが選択される。
 */
public class NameServerSectionsDialog extends Dialog {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(NameServerSectionsDialog.class);

	private enum AddStatus{SUCCESS, CANT_CONNECT, CANT_CREATE_OBJECT_TREE, ALREADY_CONNECT}

	/**
	 * 接続が成功したことのあるアドレスの一覧を保存する、ワークスペース永続文字列へのキー
	 */
	public static final String COMBO_ITEMS_KEY = NameServerSectionsDialog.class
			.getName()
			+ ".combo.items"; //$NON-NLS-1$

	/**
	 * 最後に接続が成功したアドレスのインデックスを保存する、ワークスペース永続文字列へのキー
	 */
	public static final String COMBO_SELECTION_INDEX_KEY = NameServerSectionsDialog.class
			.getName()
			+ ".combo.selectIndex"; //$NON-NLS-1$

	private Combo combo;

	private String value = ""; //$NON-NLS-1$

	private Label message;

	/**
	 * コンストラクタ
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
	 * ワークスペースの永続情報から、コンボのリストと選択インデックスをロードする
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
	 * 成功したアドレスおよび選択インデックスを、永続情報に設定する
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
		if (selectionIndex == -1) { // 新しい入力を行った場合
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
	 * OKボタン押下時に実行を行い、問題ないならば、成功したアドレスを保存する。
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
	 * アドレスのバリデートを行い、問題ない場合にはアドレスを追加する
	 * <P>
	 * バリデートとしては以下を行う
	 * <ol>
	 * <li>必須チェック</li>
	 * <li>接続済みアドレスであるかどうか</li>
	 * <li>接続できるか</li>
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
			LOGGER.error("Fail in dialog (invocation)", e);
		} catch (InterruptedException e) {
			LOGGER.error("Fail in dialog (interrupted", e);
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
	 * ネームサーバへアクセスし、オブジェクト群を構築して、ネームサーバを追加する
	 */
	private final class ConnectToNameServer implements IRunnableWithProgress {
		private final String value;

		private AddStatus result;

		/**
		 * 結果を返す。（CANT_CONNECT,CANT_CREATE_OBJECT_TREE,SUCCESS）
		 * 
		 * @return 結果
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
				LOGGER.error("Fail to add nameserver. value=" + this.value, e);
			}
			monitor.done();
		}
	}
	
	@Override
	protected Point getInitialSize() {
		return getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
	}

}
