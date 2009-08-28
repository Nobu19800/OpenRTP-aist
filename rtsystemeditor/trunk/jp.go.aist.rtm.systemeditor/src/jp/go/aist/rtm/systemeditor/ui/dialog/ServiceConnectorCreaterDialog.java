package jp.go.aist.rtm.systemeditor.ui.dialog;

import java.util.List;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.PortInterfaceProfile;
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
		this.first = first;
		this.second = second;

		this.connectorProfile = ComponentFactory.eINSTANCE
				.createConnectorProfile();
		this.connectorProfile.setName(first.getNameL() + "_" //$NON-NLS-1$
				+ second.getNameL());

		open();

		return connectorProfile;
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * {@inheritDoc}
	 */
	protected Control createDialogArea(Composite parent) {
		GridLayout gl;
		gl = new GridLayout();

		Composite mainComposite = (Composite) super.createDialogArea(parent);
		mainComposite.setLayout(gl);
		mainComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label label = new Label(mainComposite, SWT.NONE);
		label.setText(Messages.getString("ServiceConnectorCreaterDialog.1")); //$NON-NLS-1$
		GridData labelLayloutData = new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING);
		label.setLayoutData(labelLayloutData);
		labelLayloutData.heightHint = 20;

		createConnectorProfileComposite(mainComposite);

		String message = Messages.getString("ServiceConnectorCreaterDialog.2"); //$NON-NLS-1$
		try {
			List<PortInterfaceProfile> interfaces1 = first.getInterfaces();
			List<PortInterfaceProfile> interfaces2 = second.getInterfaces();

			int countMatch = countMatch(interfaces1, interfaces2);
			if (countMatch > 0 && countMatch == countTotal(interfaces1, interfaces2)) {
				message = null;
			} else {
				if (countMatch == 0) {
					message = Messages.getString("ServiceConnectorCreaterDialog.3"); //$NON-NLS-1$
				} else {
					message = Messages.getString("ServiceConnectorCreaterDialog.4"); //$NON-NLS-1$
				}
			}

		} catch (Exception e) {
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


		Label name = new Label(portProfileEditComposite, SWT.NONE);
		name.setText(Messages.getString("ServiceConnectorCreaterDialog.5")); //$NON-NLS-1$
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

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(Messages.getString("ServiceConnectorCreaterDialog.6")); //$NON-NLS-1$
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
	 *    required�������Ώ�
	 * 
	 * @param interfaces1
	 * @param interfaces2
	 * @return
	 */
	private int countMatch(List<PortInterfaceProfile> interfaces1,
			List<PortInterfaceProfile> interfaces2) {

		int result = 0;
		for (PortInterfaceProfile profile : interfaces1) {
			if (profile.isRequiredPolarity()) result += hasMatch(profile, interfaces2);
		}
		for (PortInterfaceProfile profile : interfaces2) {
			if (profile.isRequiredPolarity()) result += hasMatch(profile, interfaces1);
		}

		return result;
	}

	//�@requiring interface �̐���Ԃ�
	private int countTotal(List<PortInterfaceProfile> interfaces1,
			List<PortInterfaceProfile> interfaces2) {
		int result = 0;
		for (PortInterfaceProfile profile : interfaces1) {
			if (profile.isRequiredPolarity()) result++;
		}
		for (PortInterfaceProfile profile : interfaces2) {
			if (profile.isRequiredPolarity()) result++;
		}
		return result;
	}

	private int hasMatch(PortInterfaceProfile profile,
			List<PortInterfaceProfile> profiles2) {
		for (PortInterfaceProfile profile2 : profiles2) {
			if (isMatch(profile, profile2)) {
				return 1;
			}
		}
		return 0;
	}

	/**
	 * PortInterfaceProfile���}�b�`���邩�ǂ���
	 * 
	 * @param profile
	 * @param profile2
	 * @return �}�b�`���邩�ǂ���
	 */
	private boolean isMatch(PortInterfaceProfile profile1,
			PortInterfaceProfile profile2) {
		
		if (!profile1.getTypeName().equals(profile2.getTypeName())) return false;
		if (profile1.isProvidedPolarity()) return profile2.isRequiredPolarity();
		if (profile1.isRequiredPolarity()) return profile2.isProvidedPolarity();
		
		return false;
	}

	@Override
	protected Point getInitialSize() {
		return getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
	}

}
