package jp.go.aist.rtm.nameserviceview.ui.dialog;

import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingContextNode;
import jp.go.aist.rtm.nameserviceview.nl.Messages;

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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextPackage.AlreadyBound;

/**
 * �l�[���T�[�o�ɃR���e�L�X�g��ǉ�����_�C�A���O�iCORBA��p�j
 * <P>
 * �I�����ꂽ�R���e�L�X�g�ɓ��͂������O�Akind�ŐV���ȃR���e�L�X�g���쐬����B
 * 
 */
public class AddContextDialog extends TitleAreaDialog {

	private Text nameText;
	private Combo kindCombo;
	private String contextName;
	private String contextKind;
	private IStructuredSelection selection;
	private String errorMessage;

	public AddContextDialog(Shell parentShell, IStructuredSelection selection) {
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

		createContextComposite(mainComposite);
		return mainComposite;
	}

	/**
	 * ���C���ƂȂ�\�������쐬����
	 */
	private void createContextComposite(Composite mainComposite) {
		GridLayout gl;
		GridData gd;
		Composite portProfileEditComposite = new Composite(mainComposite,
				SWT.NONE);
		gl = new GridLayout(3, false);
		portProfileEditComposite.setLayout(gl);
		portProfileEditComposite
				.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label name = new Label(portProfileEditComposite, SWT.NONE);
		name.setText(Messages.getString("AddContextDialog.12")); //$NON-NLS-1$
		nameText = new Text(portProfileEditComposite, SWT.SINGLE | SWT.BORDER);
		gd = new GridData(GridData.GRAB_HORIZONTAL);
		gd.minimumWidth = 180;
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		nameText.setLayoutData(gd);
		contextName = ""; //$NON-NLS-1$
		nameText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				contextName = nameText.getText();
				notifyModified();
			}
		});
		Label kind = new Label(portProfileEditComposite, SWT.NONE);
		kind.setText(Messages.getString("AddContextDialog.13")); //$NON-NLS-1$
		kindCombo = new Combo(portProfileEditComposite, SWT.NONE);
		gd = new GridData(GridData.GRAB_HORIZONTAL);
		gd.minimumWidth = 180;
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		kindCombo.setLayoutData(gd);
		kindCombo.add(NamingContextNode.KIND_HOST);
		kindCombo.add(NamingContextNode.KIND_MANAGER);
		kindCombo.add(NamingContextNode.KIND_CATEGORY);
		kindCombo.add(NamingContextNode.KIND_MODULE);
		contextKind = ""; //$NON-NLS-1$
		kindCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				contextKind = kindCombo.getText();
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
		shell.setText(Messages.getString("AddContextDialog.14")); //$NON-NLS-1$
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void okPressed() {
		if (doAddContext()){
			super.okPressed();	
		} else {
			nameText.setFocus();
		}
	}
	
	/**
	 * �w�肳�ꂽ���O�AKind�ŐV���ȃR���e�L�X�g��I�����ꂽ�R���e�L�X�g�ɍ쐬����B
	 */
	private boolean doAddContext() {
		NamingContextNode parentContext = (NamingContextNode) selection
				.getFirstElement();
		
		NameComponent[] path = new NameComponent[] { new NameComponent(
				contextName, contextKind) };
		try {
			parentContext.createContextR(path);
		} catch (AlreadyBound e) {
			MessageDialog.openWarning(getShell(), Messages.getString("AddContextDialog.15"), //$NON-NLS-1$
					Messages.getString("AddContextDialog.6")); //$NON-NLS-1$
			return false;
		} catch (Exception e) {
			MessageDialog.openWarning(getShell(), Messages.getString("AddContextDialog.16"), //$NON-NLS-1$
			Messages.getString("AddContextDialog.8")); //$NON-NLS-1$
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
		errorMessage = ""; //$NON-NLS-1$
		if (!isHankaku(nameText.getText())) {
			errorMessage = Messages.getString("AddContextDialog.10"); //$NON-NLS-1$
		}
		if (!isHankaku(kindCombo.getText())) {
			errorMessage = errorMessage + Messages.getString("AddContextDialog.11"); //$NON-NLS-1$
		}
		if ("".equals(errorMessage)) { //$NON-NLS-1$
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
