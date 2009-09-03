package jp.go.aist.rtm.nameserviceview.ui.dialog;

import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingContextNode;
import jp.go.aist.rtm.toolscommon.corba.CorbaUtil;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
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
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextPackage.AlreadyBound;

/**
 * �_�C�A���O
 * <P>
 * ���͂���IOR��Object�ɕϊ����A�I�����ꂽ�R���e�L�X�g�ɓo�^����B
 * 
 */
public class AddObjectDialog extends TitleAreaDialog {

	private Text iorText;
	private String ior;
	private Text nameText;
	private Text kindText;
	private String objectName;
	private String objectKind;
	private IStructuredSelection selection;
	private String errorMessage;

	public AddObjectDialog(Shell parentShell, IStructuredSelection selection) {
		super(parentShell);
		setHelpAvailable(false);
		this.selection = selection;
		setShellStyle(getShellStyle() | SWT.CENTER | SWT.RESIZE);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected Control createDialogArea(Composite parent) {
		GridLayout gl = new GridLayout();
		
		Composite mainComposite = (Composite) super.createDialogArea(parent);
		mainComposite.setLayout(gl);
		mainComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		createObjecComposite(mainComposite);
		return mainComposite;
	}

	/**
	 * ���C���ƂȂ�\�������쐬����
	 */
	private void createObjecComposite(Composite mainComposite) {
		GridLayout gl;
		GridData gd;
		Composite portProfileEditComposite = new Composite(mainComposite,
				SWT.NONE);
		gl = new GridLayout(3, false);

		portProfileEditComposite.setLayout(gl);
		portProfileEditComposite
				.setLayoutData(new GridData(GridData.FILL_BOTH));
		Label name = new Label(portProfileEditComposite, SWT.NONE);
		name.setText("Name : ");
		nameText = new Text(portProfileEditComposite, SWT.SINGLE | SWT.BORDER);
		gd = new GridData(GridData.GRAB_HORIZONTAL);
		gd.minimumWidth = 180;
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		nameText.setLayoutData(gd);
		objectName = "";
		nameText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				objectName = nameText.getText();
				notifyModified();
			}
		});
		Label kind = new Label(portProfileEditComposite, SWT.NONE);
		kind.setText("Kind : ");
		kindText = new Text(portProfileEditComposite, SWT.SINGLE | SWT.BORDER);
		gd = new GridData(GridData.GRAB_HORIZONTAL);
		gd.minimumWidth = 180;
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		kindText.setLayoutData(gd);
		objectKind = "";
		kindText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				objectKind = kindText.getText();
				notifyModified();
			}
		});
		Label iorLabel = new Label(portProfileEditComposite, SWT.NONE);
		gd = new GridData(GridData.GRAB_HORIZONTAL);
		gd.horizontalSpan = 3;
		iorLabel.setLayoutData(gd);
		iorLabel.setText("IOR : ");
		iorText = new Text(portProfileEditComposite, SWT.MULTI | SWT.WRAP
				| SWT.V_SCROLL | SWT.BORDER);
		gd = new GridData(GridData.GRAB_HORIZONTAL);
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = 3;
		gd.minimumWidth = 330;
		gd.heightHint = 80;
		iorText.setLayoutData(gd);
		iorText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				ior = iorText.getText();
				notifyModified();
			}
		});
		
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("Add Object");
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void okPressed() {
		if (doAddObject()){
			super.okPressed();	
		}
	}
	
	
	protected void helpPressed(){
		
	}
	/**
	 * IOR��Object�ɕϊ����A�w�肳�ꂽ���O�AKind�ŁA�I�����ꂽ�R���e�L�X�g�ɓo�^����B
	 */
	private boolean doAddObject() {

		NamingContextNode parentContext = (NamingContextNode) selection
				.getFirstElement();
		NameComponent[] path = new NameComponent[] { new NameComponent(
				objectName, objectKind) };
		org.omg.CORBA.Object object = null;
		try {
			object = CorbaUtil.stringToObject(ior);
		} catch (Exception e) {
			MessageDialog.openWarning(getShell(), "Warning", "�w�肵���uIOR�v����I�u�W�F�N�g�ɕϊ��ł��܂���B");
			iorText.setFocus();
			return false;
		}
		
		try {
			parentContext.createNamingObjectR(path, object);
		} catch (AlreadyBound e) {
			MessageDialog.openWarning(getShell(), "Warning",
					"�w�肳�ꂽ�uName�v�ƁuKind�v�����ɑ��݂��Ă��܂��B");
			nameText.setFocus();
			return false;
		} catch (Exception e) {
			MessageDialog.openError(getShell(), "�o�^�G���[",
			"�o�^���s���܂����B");
		} finally {
			
		}
		return true;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void cancelPressed() {
		super.cancelPressed();
	}

	/**
	 * �ݒ�ɕύX���������ꍇ�ɌĂяo����邱�Ƃ�z�肵�����\�b�h�B
	 * <p>
	 * ���ӁF�ݒ�l�̕ύX������ꍇ�ɂ́A�K�����̃��\�b�h���Ăяo������<br>
	 * ���݂́A�\�����Őݒ��ύX������ɁA���̃��\�b�h��K���Ăяo���悤�Ɏ������Ă��邪�A
	 * ���ڐ���������悤�Ȃ�΁A���f���̕ύX�ʒm�@�\���g�p���Ď�����������ǂ��B
	 */
	public void notifyModified() {
		errorMessage = "";
		if (!isHankaku(nameText.getText())) {
			errorMessage = "�uName�v�ɔ��p��������͂��Ă��������B\r\n";
		}
		if (!isHankaku(kindText.getText())) {
			errorMessage = errorMessage + "�uKind�v�ɔ��p��������͂��Ă��������B\r\n";
		}
		
		if ("".equals(errorMessage)) {
			setMessage(null);
			getButton(IDialogConstants.OK_ID).setEnabled(true);
		} else {
			setMessage(errorMessage, IMessageProvider.ERROR);
			getButton(IDialogConstants.OK_ID).setEnabled(false);
		}
	}

	private boolean isHankaku(String value) {
		
		boolean result = false;
		byte[] bytes = value.getBytes();
		int valueLength = value.length();
		if (valueLength == bytes.length) {
			result = true;
		}
		return result;
	}
	
	@Override
	protected Point getInitialSize() {
		return getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
	}
}