package jp.go.aist.rtm.systemeditor.ui.dialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.ServicePort;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import RTC.PortInterfacePolarity;
import RTC.PortInterfaceProfile;

/**
 * �T�[�r�X�|�[�g�Ԃ̐ڑ��̃R�l�N�^�v���t�@�C���̑I���_�C�A���O
 * <P>
 * �|�[�g������͂��� �ڑ����悤�Ƃ��Ă���ServicePort�ԂŃ}�b�`���O���s���A�K�v�ɉ����Čx����\������B
 * �����ł����}�b�`���O�́A�uPortInterfaceProfile.type�v�������ŁA�uPortInterfaceProfile.polarity�v��PROVIDED��REQUIRED�őΉ����邱�Ƃ������B
 * �E���S��v�����ꍇ �� �x���Ȃ� �E�ꕔ��v�����ꍇ �� �x�� �uPort interfaces do not match completely.�v
 * �E���S�s��v�����ꍇ �� �x�� �uNo corresponding port interface.�v
 * 
 */
public class ServiceConnectorCreaterDialog extends TitleAreaDialog {

	private Text nameText;

	private ConnectorProfile connectorProfile;

	private ServicePort first;

	private ServicePort second;

	public ServiceConnectorCreaterDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(getShellStyle() | SWT.CENTER | SWT.RESIZE);
	}

	/**
	 * ConnectorProfileCreater�C���^�t�F�[�X�̎������\�b�h
	 * <p>
	 * ConnectorProfile�ƂȂ��₪��������ꍇ�ɂ́A�_�C�A���O��\�����AConnectorProfile���쐬����B
	 */
	public ConnectorProfile getConnectorProfile(ServicePort first,
			ServicePort second) {
		if (first.getPortProfile() == null) {
			return null;
		}
		this.first = first;
		this.second = second;

		this.connectorProfile = ComponentFactory.eINSTANCE
				.createConnectorProfile();
		this.connectorProfile.setName(first.getPortProfile().getNameL() + "_"
				+ second.getPortProfile().getNameL());

		open();

		return connectorProfile;
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
		label.setText("�|�[�g�v���t�@�C������͂��Ă��������B");
		GridData labelLayloutData = new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING);
		label.setLayoutData(labelLayloutData);
		labelLayloutData.heightHint = 20;

		createConnectorProfileComposite(mainComposite);

		String message = "Error";
		try {
			RTC.PortInterfaceProfile[] interfaces1 = first
					.getCorbaObjectInterface().get_port_profile().interfaces;
			RTC.PortInterfaceProfile[] interfaces2 = second
					.getCorbaObjectInterface().get_port_profile().interfaces;

			int countMatch = countMatch(interfaces1, interfaces2);
			if (countMatch == Math.max(interfaces1.length, interfaces2.length)) {
				message = null;
			} else {
				if (countMatch == 0) {
					message = "No corresponding port interface.";
				} else {
					message = "Port interfaces do not match completely.";
				}
			}

		} catch (Exception e) {
		}

		if (first.eContainer() instanceof ComponentSpecification){
			message = null;
			
		}
		if (message != null) {
			setMessage(message, IMessageProvider.WARNING);
		}

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

		loadData();
	}

	/**
	 * ���f�����ɃA�N�Z�X���A�\���ɐݒ肷��
	 */
	private void loadData() {
		nameText.setText(connectorProfile.getName());
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
		shell.setText("Port Profile");
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void okPressed() {
		super.okPressed();
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void cancelPressed() {
		connectorProfile = null;

		super.cancelPressed();
	}

	@Override
	/**
	 * {@inheritDoc}
	 * <p>
	 * ���b�Z�[�W��ݒ肷��B
	 */
	public void setMessage(String newMessage, int newType) {
		super.setMessage(newMessage, newType);
	}

	/**
	 * �ݒ�ɕύX���������ꍇ�ɌĂяo����邱�Ƃ�z�肵�����\�b�h�B
	 * <p>
	 * ���ӁF�ݒ�l�̕ύX������ꍇ�ɂ́A�K�����̃��\�b�h���Ăяo������<br>
	 * ���݂́A�\�����Őݒ��ύX������ɁA���̃��\�b�h��K���Ăяo���悤�Ɏ������Ă��邪�A
	 * ���ڐ���������悤�Ȃ�΁A���f���̕ύX�ʒm�@�\���g�p���Ď�����������ǂ��B
	 */
	public void notifyModified() {
	}

	/**
	 * PortInterfaceProfile�̃}�b�`���𐔂���
	 * 
	 * @param profile1
	 * @param profile2
	 * @return
	 */
	public static int countMatch(PortInterfaceProfile[] profiles1,
			PortInterfaceProfile[] profiles2) {
		List<PortInterfaceProfile> list1 = Arrays.asList(profiles1);
		List<PortInterfaceProfile> list2 = new ArrayList<PortInterfaceProfile>(
				Arrays.asList(profiles2));

		int result = 0;
		for (PortInterfaceProfile profile1 : list1) {
			for (PortInterfaceProfile profile2 : list2) {
				if (isMatch(profile1, profile2)) {
					++result;
					list2.remove(profile2);
					break;
				}
			}
		}

		return result;
	}

	/**
	 * PortInterfaceProfile���}�b�`���邩�ǂ���
	 * 
	 * @param profile
	 * @param profile2
	 * @return �}�b�`���邩�ǂ���
	 */
	private static boolean isMatch(PortInterfaceProfile profile1,
			PortInterfaceProfile profile2) {
		boolean result = false;
		if (profile1.type_name.equals(profile2.type_name)
				&& ((profile1.polarity.value() == PortInterfacePolarity.PROVIDED
						.value() && profile2.polarity.value() == PortInterfacePolarity.REQUIRED
						.value()) || (profile1.polarity.value() == PortInterfacePolarity.REQUIRED
						.value() && profile2.polarity.value() == PortInterfacePolarity.PROVIDED
						.value()))) {
			result = true;
		}
		return result;
	}

	@Override
	protected Point getInitialSize() {
		return getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
	}

}
