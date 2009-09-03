package jp.go.aist.rtm.systemeditor.ui.dialog;

import java.util.List;

import jp.go.aist.rtm.systemeditor.RTSystemEditorPlugin;
import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.ui.preference.OfflineEditorPreferencePage;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.InPort;
import jp.go.aist.rtm.toolscommon.model.component.OutPort;
import jp.go.aist.rtm.toolscommon.model.component.impl.ConnectorProfileImpl;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * �f�[�^�|�[�g�Ԃ̐ڑ��̃R�l�N�^�v���t�@�C���̑I���_�C�A���O
 * <P>
 * �R�l�N�^�v���t�@�C���̑I���\�ȃ^�C�v���AOutPort�����InPort���画�f���v���_�E���Ƃ��ĕ\������B�i�uAny�v���͕̂\������Ȃ��j(�����̃P�[�X�͖������ă}�b�`���O���A�}�b�`�����ۂɕ\�������̂�OutPort�̕�����Ƃ���)<br>
 * �I���\�ł���̂́A�f�[�^�|�[�g�����ł���A�T�[�r�X�|�[�g�͊܂܂�Ȃ��B<br>
 * OutPort��������InPort�ɁuAny�v���܂܂�Ă���ꍇ�ɂ́A����̂��ׂČ^���󂯓������̂Ƃ���B<br>
 * OutPort�����Inport��Any���܂܂�Ă���ꍇ�ɂ́A�R���{�{�b�N�X�ɔC�ӂ̕��������͉\�Ƃ��A�u*�C�ӓ��͉v��\������B<br>
 * �T�u�X�N���v�V�����^�C�v�́A�f�[�^�t���[�^�C�v���uPush�v�̎��̂ݕ\�������B<br>
 * PushRate�́A�T�u�X�N���v�V�����^�C�v���uPeriodic�v�ł���A���f�[�^�t���[�^�C�v���uPush�v�̎��̂ݕ\�������<br>
 */
public class DataConnectorCreaterDialog extends TitleAreaDialog {

	private static final int COMBO_MIN = 72;

	private Text nameText;

	private Combo dataTypeCombo;

	private Combo interfaceTypeCombo;

	private Combo dataflowTypeCombo;

	private Combo subscriptionTypeCombo;

	private ConnectorProfile connectorProfile;

	private ConnectorProfile dialogResult;

	private Text pushRateText;

	private OutPort outport;

	private InPort inport;

	public DataConnectorCreaterDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(getShellStyle() | SWT.CENTER | SWT.RESIZE);
	}

	/**
	 * ConnectorProfileCreater�C���^�t�F�[�X�̎������\�b�h
	 * <p>
	 * ConnectorProfile�ƂȂ��₪��������ꍇ�ɂ́A�_�C�A���O��\�����AConnectorProfile���쐬����B
	 */
	public ConnectorProfile getConnectorProfile(OutPort outport, InPort inport) {
		this.outport = outport;
		this.inport = inport;

		// if (ConnectorProfile.createOnlyOneCandidateConnectorProfile(outport,
		// inport) != null) {
		// return ConnectorProfile.createOnlyOneCandidateConnectorProfile(
		// first, second);
		// }
		//

		this.connectorProfile = ComponentFactory.eINSTANCE
				.createConnectorProfile();
		this.connectorProfile.setName(outport.getPortProfile().getNameL() + "_"
				+ inport.getPortProfile().getNameL());

		this.setShellStyle(this.getShellStyle() | SWT.RESIZE  );
		open();

		return dialogResult;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected Control createDialogArea(Composite parent) {
		GridLayout gl;
		GridData gd;
		gl = new GridLayout();
		gd = new GridData();

		Composite mainComposite = (Composite) super.createDialogArea(parent);
		mainComposite.setLayout(gl);
		mainComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label label = new Label(mainComposite, SWT.NONE);
		label.setText("ConnectorProfile����͂��Ă��������B");
		GridData labelLayloutData = new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING);
		label.setLayoutData(labelLayloutData);
		labelLayloutData.heightHint = 20;

		createConnectorProfileComposite(mainComposite);

		return mainComposite;
	}

	/**
	 * ���C���ƂȂ�\�������쐬����
	 */
	private void createConnectorProfileComposite(Composite mainComposite) {
		GridLayout gl;
		GridData gd;
		Composite portProfileEditComposite = new Composite(mainComposite,
				SWT.NONE);
		gl = new GridLayout(3, false);
		gl.marginBottom = 0;
		gl.marginHeight = 0;
		gl.marginLeft = 0;
		gl.marginRight = 0;
		gl.marginTop = 0;
		gl.marginWidth = 0;
		portProfileEditComposite.setLayout(gl);
		portProfileEditComposite
				.setLayoutData(new GridData(GridData.FILL_BOTH));

		int style;

		Label name = new Label(portProfileEditComposite, SWT.NONE);
		name.setText("Name : ");
		nameText = new Text(portProfileEditComposite, SWT.SINGLE | SWT.BORDER);
		gd = new GridData(GridData.GRAB_HORIZONTAL);
		gd.minimumWidth = 140;
		gd.horizontalSpan = 2;
		nameText.setLayoutData(gd);
		nameText.setTextLimit(255);
		nameText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				connectorProfile.setName(nameText.getText());
				notifyModified();
			}
		});

		Label dataTypeLabel = new Label(portProfileEditComposite, SWT.NONE);
		dataTypeLabel.setText("Data Type : ");
		style = ConnectorProfileImpl.isAllowAnyDataType(outport, inport) ? SWT.DROP_DOWN
				: SWT.DROP_DOWN | SWT.READ_ONLY;
		dataTypeCombo = new Combo(portProfileEditComposite, style);
		gd = new GridData();
		gd.widthHint = COMBO_MIN;
		dataTypeCombo.setLayoutData(gd);
		dataTypeCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				connectorProfile.setDataType(dataTypeCombo.getText());
				notifyModified();
			}
		});
		Label dataTypeFooterLabel = new Label(portProfileEditComposite,
				SWT.NONE);
		dataTypeFooterLabel.setText(ConnectorProfileImpl.isAllowAnyDataType(
				outport, inport) ? "*�C�ӓ��͉�" : "");

		Label interfaceTypeLabel = new Label(portProfileEditComposite, SWT.NONE);
		interfaceTypeLabel.setText("Interface Type : ");
		style = ConnectorProfileImpl.isAllowAnyInterfaceType(outport, inport) ? SWT.DROP_DOWN
				: SWT.DROP_DOWN | SWT.READ_ONLY;
		interfaceTypeCombo = new Combo(portProfileEditComposite, style);
		gd = new GridData();
		gd.widthHint = COMBO_MIN;
		interfaceTypeCombo.setLayoutData(gd);
		interfaceTypeCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				connectorProfile.setInterfaceType(interfaceTypeCombo.getText());
				notifyModified();
			}
		});
		Label interfaceTypeFooterLabel = new Label(portProfileEditComposite,
				SWT.NONE);
		interfaceTypeFooterLabel.setText(ConnectorProfileImpl
				.isAllowAnyInterfaceType(outport, inport) ? "*�C�ӓ��͉�" : "");

		Label dataflowTypeLabel = new Label(portProfileEditComposite, SWT.NONE);
		dataflowTypeLabel.setText("Dataflow Type : ");
		style = ConnectorProfileImpl.isAllowAnyDataflowType(outport, inport) ? SWT.DROP_DOWN
				: SWT.DROP_DOWN | SWT.READ_ONLY;
		dataflowTypeCombo = new Combo(portProfileEditComposite, style);
		gd = new GridData();
		gd.widthHint = COMBO_MIN;
		dataflowTypeCombo.setLayoutData(gd);
		dataflowTypeCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				connectorProfile.setDataflowType(dataflowTypeCombo.getText());
				notifyModified();
			}
		});
		Label dataflowTypeFooterLabel = new Label(portProfileEditComposite,
				SWT.NONE);
		dataflowTypeFooterLabel.setText(ConnectorProfileImpl
				.isAllowAnyDataflowType(outport, inport) ? "*�C�ӓ��͉�" : "");

		Label subscriptionTypeLabel = new Label(portProfileEditComposite,
				SWT.NONE);
		subscriptionTypeLabel.setText("Subscription Type : ");
		style = ConnectorProfileImpl.isAllowAnySubscriptionType(outport, inport) ? SWT.DROP_DOWN
				: SWT.DROP_DOWN | SWT.READ_ONLY;
		subscriptionTypeCombo = new Combo(portProfileEditComposite, style);
		gd = new GridData();
		gd.widthHint = COMBO_MIN;
		subscriptionTypeCombo.setLayoutData(gd);
		subscriptionTypeCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				connectorProfile.setSubscriptionType(subscriptionTypeCombo
						.getText());
				notifyModified();
			}
		});
		Label subscriptionTypeFooterLabel = new Label(portProfileEditComposite,
				SWT.NONE);
		subscriptionTypeFooterLabel.setText(ConnectorProfileImpl
				.isAllowAnySubscriptionType(outport, inport) ? "*�C�ӓ��͉�" : "");
		subscriptionTypeCombo.setEnabled(false);

		Label pushRate = new Label(portProfileEditComposite, SWT.NONE);
		pushRate.setText("Push Rate(Hz) : ");
		pushRateText = new Text(portProfileEditComposite, SWT.SINGLE
				| SWT.BORDER);
		pushRateText.setEnabled(false);
		gd = new GridData();
		gd.widthHint = COMBO_MIN + 21;
		pushRateText.setLayoutData(gd);
		pushRateText.setTextLimit(9);
		pushRateText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String text = pushRateText.getText();

				boolean isDouble = false;
				try {
					Double.parseDouble(text);
					isDouble = true;
				} catch (Exception ex) {
					// void
				}

				if (isDouble) {
					connectorProfile.setPushRate(Double.parseDouble(text));
				}

				notifyModified();
			}
		});

		loadData();
	}

	/**
	 * ���f�����ɃA�N�Z�X���A�\���ɐݒ肷��
	 */
	private void loadData() {

		nameText.setText(connectorProfile.getName());
		dataTypeCombo.setItems((String[]) ConnectorProfileImpl.getAllowDataTypes(
				outport, inport).toArray(
				new String[ConnectorProfileImpl.getAllowDataTypes(outport, inport)
						.size()]));
		connectorProfile.setDataType(getDefaultValue(ConnectorProfileImpl
				.getAllowDataTypes(outport, inport), ConnectorProfileImpl
				.isAllowAnyDataType(outport, inport)));
		dataTypeCombo.select(ConnectorProfileImpl
				.getAllowDataTypes(outport, inport).indexOf(
						connectorProfile.getDataType()));

		if( inport.eContainer() instanceof ComponentSpecification ) {
			interfaceTypeCombo.setItems(SystemEditorPreferenceManager.getInstance().getInterfaceTypes());
			interfaceTypeCombo.select(0);
			//
			dataflowTypeCombo.setItems(SystemEditorPreferenceManager.getInstance().getDataFlowTypes());
			dataflowTypeCombo.select(0);
			//
			subscriptionTypeCombo.setItems(SystemEditorPreferenceManager.getInstance().getSubscriptionTypes());
			subscriptionTypeCombo.select(0);
		} else {
			interfaceTypeCombo.setItems((String[]) ConnectorProfileImpl
					.getAllowInterfaceTypes(outport, inport).toArray(
							new String[ConnectorProfileImpl.getAllowInterfaceTypes(
									outport, inport).size()]));
			connectorProfile.setInterfaceType(getDefaultValue(ConnectorProfileImpl
					.getAllowInterfaceTypes(outport, inport), ConnectorProfileImpl
					.isAllowAnyInterfaceType(outport, inport)));
			interfaceTypeCombo.select(ConnectorProfileImpl.getAllowInterfaceTypes(
					outport, inport).indexOf(connectorProfile.getInterfaceType()));

			dataflowTypeCombo.setItems((String[]) ConnectorProfileImpl
					.getAllowDataflowTypes(outport, inport).toArray(
							new String[ConnectorProfileImpl.getAllowDataflowTypes(
									outport, inport).size()]));
			connectorProfile.setDataflowType(getDefaultValue(ConnectorProfileImpl
					.getAllowDataflowTypes(outport, inport), ConnectorProfileImpl
					.isAllowAnyDataflowType(outport, inport)));
			dataflowTypeCombo.select(ConnectorProfileImpl.getAllowDataflowTypes(
					outport, inport).indexOf(connectorProfile.getDataflowType()));
	
			subscriptionTypeCombo.setItems((String[]) ConnectorProfileImpl
					.getAllowSubscriptionTypes(outport, inport).toArray(
							new String[ConnectorProfileImpl.getAllowSubscriptionTypes(
									outport, inport).size()]));
			connectorProfile.setSubscriptionType(getDefaultValue(ConnectorProfileImpl
					.getAllowSubscriptionTypes(outport, inport), ConnectorProfileImpl
					.isAllowAnySubscriptionType(outport, inport)));
			subscriptionTypeCombo.select(ConnectorProfileImpl
					.getAllowSubscriptionTypes(outport, inport).indexOf(
							connectorProfile.getSubscriptionType()));
		}
	}

	/**
	 * �R���{�ɂ����āA�u�\�����̃��X�g�v�ƁA�u�ǂ̂悤�ȕ�����ł��ݒ�\�ł��邩�ǂ����v�������Ɏ��A�����\���̕���������肷��
	 * 
	 * @param candidateList
	 *            �\����⃊�X�g
	 * @param isAllowAny
	 *            �ǂ̂悤�ȕ�����ł��ݒ�\�ł��邩�ǂ���
	 * @return �����\���̕�����
	 */
	private String getDefaultValue(List candidateList, boolean isAllowAny) {
		String result = null;

		if (candidateList.size() > 0) {
			result = (String) candidateList.get(0);
		}

		if (result == null) {
			if (isAllowAny) {
				result = ConnectorProfile.ANY;
			}
		}

		return result;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("Connector Profile");
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void okPressed() {
		dialogResult = connectorProfile;
		super.okPressed();
	}

	@Override
	/**
	 * {@inheritDoc}
	 * <p>
	 * ���b�Z�[�W��ݒ肷��B ���b�Z�[�W�Ƃ��Ă̓G���[���b�Z�[�W��z�肵�Ă���A
	 * �G���[���b�Z�[�W�����݂��邩�󕶎����ǂ����ɂ��AOK�{�^����Enable�̐�����s���悤�ɁA�I�[�o�[���C�h�����B
	 */
	public void setMessage(String newMessage, int newType) {
		super.setMessage(newMessage, newType);

		boolean isOkEnable = false;
		if (newMessage.length() == 0) {
			isOkEnable = true;
		}

		getButton(IDialogConstants.OK_ID).setEnabled(isOkEnable);
	}

	/**
	 * �ݒ�ɕύX���������ꍇ�ɌĂяo����邱�Ƃ�z�肵�����\�b�h�B
	 * SubscriptionType�R���{��PushInterbal��Enable���Ǘ�����B
	 * <p>
	 * ���ӁF�ݒ�l�̕ύX������ꍇ�ɂ́A�K�����̃��\�b�h���Ăяo������<br>
	 * ���݂́A�\�����Őݒ��ύX������ɁA���̃��\�b�h��K���Ăяo���悤�Ɏ������Ă��邪�A
	 * ���ڐ���������悤�Ȃ�΁A���f���̕ύX�ʒm�@�\���g�p���Ď�����������ǂ��B
	 */
	public void notifyModified() {
		if (getButton(IDialogConstants.OK_ID) != null) {
			setMessage("", IMessageProvider.NONE);
		}

		if (connectorProfile.isPushIntervalAvailable()) {
			boolean isDouble = false;
			try {
				double value = Double.parseDouble(pushRateText.getText());
				if (value > 0) {
					isDouble = true;
				}
			} catch (Exception ex) {
				// void
			}

			String message = "";
			if (isDouble == false) {
				message = "Push Rate�͐��l�łȂ���΂Ȃ�܂���B";
			}

			if (message.length() != 0) {
				setMessage(message, IMessageProvider.ERROR);
			}
		}

		subscriptionTypeCombo.setEnabled(connectorProfile
				.isSubscriptionTypeAvailable());

		pushRateText.setEnabled(connectorProfile.isPushIntervalAvailable());
	}

	@Override
	protected Point getInitialSize() {
		return getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
	}

}
