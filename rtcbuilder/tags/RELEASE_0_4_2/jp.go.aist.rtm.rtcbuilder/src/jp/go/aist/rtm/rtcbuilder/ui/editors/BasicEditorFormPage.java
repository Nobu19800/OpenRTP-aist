package jp.go.aist.rtm.rtcbuilder.ui.editors;

import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.GuiRtcBuilder;
import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.RtcBuilderPlugin;
import jp.go.aist.rtm.rtcbuilder.generator.ProfileHandler;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.manager.GenerateManager;
import jp.go.aist.rtm.rtcbuilder.ui.StringUtil;
import jp.go.aist.rtm.rtcbuilder.ui.Perspective.LanguageProperty;
import jp.go.aist.rtm.rtcbuilder.ui.wizard.RtcExportWizard;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

/**
 * Basic Profile �ݒ�y�[�W
 */
public class BasicEditorFormPage extends AbstractEditorFormPage {

	/**
	 * �������s����Category�̏���ۑ����郏�[�N�X�y�[�X�i��������ւ̃L�[
	 */
	private static final String CATEGORY_INDEX_KEY = BasicEditorFormPage.class.getName() + ".category.name";

	private Text nameText;
	private Combo categoryCombo;
	private Text descriptionText;
	private Text versionText;
	private Text venderText;
	private Combo typeCombo;
	private Combo activityTypeCombo;
	private Combo kindCombo;
	private Text maxInstanceText;
	private Combo executionTypeCombo;
	private Text executionRateText;

	private Text outputProjectText;
	/**
	 * �R���X�g���N�^
	 * 
	 * @param editor
	 *            �e�̃G�f�B�^
	 */
	public BasicEditorFormPage(RtcBuilderEditor editor) {
		super(editor, "id", "��{");
	}

	/**
	 * {@inheritDoc}
	 */
	protected void createFormContent(IManagedForm managedForm) {
		GridLayout gl;
		GridData gd;
		gl = new GridLayout();
		gl.numColumns = 1;

		managedForm.getForm().getBody().setLayout(gl);

		ScrolledForm form = managedForm.getToolkit().createScrolledForm(
				managedForm.getForm().getBody());
		gl = new GridLayout(1, false);
		form.setLayout(gl);

		form.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		managedForm.getToolkit().paintBordersFor(form.getBody());

		form.getBody().setLayout(gl);

		createModuleSection(managedForm, form);

		//
		getSite().setSelectionProvider(new ISelectionProvider() {
			public void addSelectionChangedListener(
				ISelectionChangedListener listener) {
			}
			public ISelection getSelection() {
				return new StructuredSelection(buildview);
			}
			public void removeSelectionChangedListener(
				ISelectionChangedListener listener) {
			}
			public void setSelection(ISelection selection) {
			}
		});

		createOutputProjectSection(managedForm, form);

		Composite buttonComposite = managedForm.getToolkit().createComposite(
				form.getBody(), SWT.NULL);
		gl = new GridLayout(2, false);
		buttonComposite.setLayout(gl);
		gd = new GridData();
		gd.horizontalSpan = 2;
		gd.grabExcessHorizontalSpace = true;
		buttonComposite.setLayoutData(gd);

		Button generateButton = managedForm.getToolkit().createButton(
				buttonComposite, "Generate", SWT.NONE);
		generateButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editor.allUpdates();
				String validateRtcParam = editor.validateParam();
				if (validateRtcParam != null) {
					MessageDialog.openError(getSite().getShell(), "�G���[",
							validateRtcParam);
					return;
				}

				editor.addDefaultComboValue();
				GuiRtcBuilder rtcBuilder = new GuiRtcBuilder();
				List<GenerateManager> managerList = RtcBuilderPlugin.getDefault().getLoader().getManagerList();
				if( managerList != null ) {
					for( Iterator<GenerateManager> iter = managerList.iterator(); iter.hasNext(); ) {
						GenerateManager manager = iter.next();
						rtcBuilder.addGenerateManager(manager);
					}
				}
				GeneratorParam generatorParam = editor.getGeneratorParam();
				//TODO �����R���|�[�l���g�Ή��łƂ���ꍇ�ɂ͕����ݒ�
				generatorParam.getRtcParams().get(0).getServiceClassParams().clear();
				if( rtcBuilder.doGenerateWrite(generatorParam) ) {
					switchPerspective();
				}
			}
		});
		gd = new GridData();
		gd.horizontalSpan = 2;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalAlignment = GridData.FILL;
		generateButton.setLayoutData(gd);
		
		Button exportButton = managedForm.getToolkit().createButton(
				buttonComposite, "Export", SWT.NONE);
		exportButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				WizardDialog dialog = new WizardDialog(getSite().getShell(), new RtcExportWizard());
				dialog.open();
			}
		});
		gd = new GridData();
		gd.horizontalSpan = 2;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalAlignment = GridData.FILL;
		exportButton.setLayoutData(gd);
		//
		Button profileSaveButton = managedForm.getToolkit().createButton(
				buttonComposite, "Profile Export", SWT.NONE);
		profileSaveButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editor.allUpdates();
				FileDialog dialog = new FileDialog(getSite().getShell(),SWT.SAVE);
		        dialog.setText("Profile Export");
				dialog.setFilterNames(new String[] { "XML�`��","YAML�`��" });
				dialog.setFilterExtensions(new String[] { "*.xml","*.yaml" });
		        String selectedFileName = dialog.open();
		        if (selectedFileName != null) {
		        	try {
			        	if( getFileExtension(selectedFileName).equals(IRtcBuilderConstants.YAML_EXTENSION) ) {
			        		ProfileHandler handler = new ProfileHandler();
			        		handler.createYaml(selectedFileName, editor.getGeneratorParam());
			        	} else {
			        		ProfileHandler handler = new ProfileHandler();
			        		handler.storeToXML(selectedFileName, editor.getGeneratorParam());
			        	}
					} catch (Exception e1) {
						MessageDialog.openError(getSite().getShell(), "�G���[",	
								"Profile Export�Ɏ��s���܂���");
						return;
					}
					MessageDialog.openInformation(getSite().getShell(), "�I��",	
							"Profile Export���I�����܂���");
		        }
			}
		});
		//
		Button profileLoadButton = managedForm.getToolkit().createButton(
				buttonComposite, "Profile Import", SWT.NONE);
		profileLoadButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(getSite().getShell(),SWT.OPEN);
		        dialog.setText("Profile Import");
				dialog.setFilterNames(new String[] { "XML�`��","YAML�`��" });
				dialog.setFilterExtensions(new String[] { "*.xml","*.yaml" });
		        String selectedFileName = dialog.open();
		        if (selectedFileName != null) {
		        	try {
			        	if( getFileExtension(selectedFileName).equals(IRtcBuilderConstants.YAML_EXTENSION) ) {
			        		ProfileHandler handler = new ProfileHandler();
							editor.setGeneratorParam(handler.readYaml(selectedFileName));
							String xmlFile = handler.convert2XML(editor.getGeneratorParam());
							editor.getRtcParam().setRtcXml(xmlFile);
			        	} else {
			        		ProfileHandler handler = new ProfileHandler();
			        		editor.setGeneratorParam(handler.restorefromXMLFile(selectedFileName));
			        	}
					} catch (Exception e1) {
						MessageDialog.openError(getSite().getShell(), "�G���[",	
								"Profile Import�Ɏ��s���܂���");
						return;
					}
					MessageDialog.openInformation(getSite().getShell(), "�I��",	
							"Profile Import���I�����܂���");
					editor.allPagesReLoad();
					editor.updateEMFModuleName(editor.getRtcParam().getName());
					editor.updateEMFDataInPorts(editor.getRtcParam().getInports());
					editor.updateEMFDataOutPorts(editor.getRtcParam().getOutports());
					editor.updateEMFServiceOutPorts(editor.getRtcParam().getServicePorts());
					load();
//				editor.EMFLoaded();
		        }
			}
		});
		//
		load();
	}
	
	private String getFileExtension(String filename){
		int index = filename.lastIndexOf(".");
		if( index > -1 ) return filename.substring(index + 1);
		return "";
	}
	
	private void switchPerspective() {

		for(RtcParam rtcParam : editor.getGeneratorParam().getRtcParams() ) {
			//Plugin�̑��݊m�F
			LanguageProperty langProp = LanguageProperty.checkPlugin(rtcParam);
			String currentPerspectiveId = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
	            							.getActivePage().getPerspective().getId();
			if( langProp != null && !langProp.getPerspectiveId().equals(currentPerspectiveId) ) {
				MessageBox message = new MessageBox(getSite().getShell(), 
						SWT.ICON_QUESTION | SWT.YES | SWT.NO);
				message.setText("�֘A�t����ꂽ�p�[�X�y�N�e�B�u���J���܂����H");
				message.setMessage("���̃v���W�F�N�g�� " + langProp.getPerspectiveName() 
						+ " �p�[�X�y�N�e�B�u�Ɋ֘A�t�����܂��B���̃p�[�X�y�N�e�B�u���J���܂����H");
				if( message.open() == SWT.YES) {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().setPerspective(
							PlatformUI.getWorkbench().getPerspectiveRegistry().findPerspectiveWithId(
									langProp.getPerspectiveId()));
					}
			}
		}
	}

	/**
	 * �o���f�[�g���s���B�G���[���Ȃ��ꍇ�ɂ�null��Ԃ��A�G���[������ꍇ�ɂ̓��b�Z�[�W��Ԃ��B
	 * 
	 * @return
	 */
	public String validateParam() {
		String result = null;
		//Module Name
		if (result == null && nameText.getText().length() == 0) {
			result = "Module name ����͂��Ă��������B";
		}
		if( !StringUtil.checkDigitAlphabet(nameText.getText()) ) {
			result = "Module name �͔��p�p��������͂��Ă��������B";
		}
		//Module category
		if (result == null && categoryCombo.getText().length() == 0) {
			result = "Module category ����͂��Ă��������B";
		}
		//Number of maximum instance
		String maxText = maxInstanceText.getText();
		if( maxText != null ) {
			Integer parseInt = null;
			try {
				parseInt = new Integer(Integer.parseInt(maxInstanceText.getText()));
			} catch (Exception e) {
				// void
			}
			if (parseInt == null) {
				result = "Number of maximum instance �͐��l�łȂ���΂Ȃ�܂���B";
			}
			if (parseInt != null && parseInt.intValue() < 0) {
				result = "Number of maximum instance �͐��̐��łȂ���΂Ȃ�܂���B";
			}
		}
		//Execution Rate
		Double parseDbl = null;
		try {
			parseDbl = new Double(Double.parseDouble(executionRateText.getText()));
		} catch (Exception e) {
			// void
		}
		if (parseDbl == null) {
			result = "Execution Rate �͐��l�łȂ���΂Ȃ�܂���B";
		}
		if (parseDbl != null && parseDbl.intValue() < 0) {
			result = "Execution Rate �͐��̒l�łȂ���΂Ȃ�܂���B";
		}

		return result;
	}

	private void createModuleSection(IManagedForm managedForm,
			ScrolledForm form) {
		Composite composite = createSectionBase(managedForm, form, 
				"RT-Component Basic Profile", 2);

		nameText = createLabelAndText(managedForm.getToolkit(), composite,
				"Module name :");
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.widthHint = 400;
		nameText.setLayoutData(gridData);

		descriptionText = createLabelAndText(managedForm.getToolkit(),
				composite, "Module description :");
		versionText = createLabelAndText(managedForm.getToolkit(), composite,
				"Module version :");
		venderText = createLabelAndText(managedForm.getToolkit(), composite,
				"Module vender :");
		categoryCombo = createEditableCombo(managedForm.getToolkit(), composite,
				"Module category :", CATEGORY_INDEX_KEY);
		typeCombo = createLabelAndCombo(managedForm.getToolkit(), composite,
				"Component type :", IRtcBuilderConstants.COMPONENT_TYPE_ITEMS);
		activityTypeCombo = createLabelAndCombo(managedForm.getToolkit(),
				composite, "Component's activity type :",
				IRtcBuilderConstants.ACTIVITY_TYPE_ITEMS);
		kindCombo = createLabelAndCombo(managedForm.getToolkit(), composite,
				"Component kind :", IRtcBuilderConstants.COMPONENT_KIND_ITEMS);
		maxInstanceText = createLabelAndText(managedForm.getToolkit(),
				composite, "Number of maximum instance :");
		executionTypeCombo = createLabelAndCombo(managedForm.getToolkit(), composite,
				"Execution type :", IRtcBuilderConstants.EXECUTIONCONTEXT_TYPE_ITEMS);
		executionRateText = createLabelAndText(managedForm.getToolkit(),
				composite, "Execution Rate :");
	}

	private void createOutputProjectSection(IManagedForm managedForm,
			ScrolledForm form) {
		Composite composite = createSectionBase(managedForm, form, "Output Project", 2);
		outputProjectText = createLabelAndText(managedForm.getToolkit(),
				composite, "");
	}
	
	protected void update() {
		RtcParam rtcParam = editor.getRtcParam();

		rtcParam.setName(getText(nameText.getText()));
		rtcParam.setDescription(getText(descriptionText.getText()));
		rtcParam.setVersion(getText(versionText.getText()));
		rtcParam.setVender(getText(venderText.getText()));
		rtcParam.setCategory(getText(categoryCombo.getText()));
		rtcParam.setComponentType(getText(typeCombo.getText()));
		rtcParam.setActivityType(getText(activityTypeCombo.getText()));
		rtcParam.setComponentKind(getText(kindCombo.getText()));

		try {
			int maxInstance = Integer.parseInt(getText(maxInstanceText
					.getText()));
			rtcParam.setMaxInstance(maxInstance);
		} catch (Exception e) {
			// ��O�̏ꍇ�A��ʂ̒l�����݂̒l�ɖ߂�
			maxInstanceText.setText(String.valueOf(rtcParam.getMaxInstance()));
		}

		rtcParam.setExecutionType(getText(executionTypeCombo.getText()));
		try {
			double exec_rate = Double.parseDouble(getText(executionRateText
					.getText()));
			rtcParam.setExecutionRate(exec_rate);
		} catch (Exception e) {
			// ��O�̏ꍇ�A��ʂ̒l�����݂̒l�ɖ߂�
			executionRateText.setText(String.valueOf(rtcParam.getExecutionRate()));
		}

		rtcParam.setOutputProject(getText(outputProjectText.getText()));

//		if( !((Component)build.getComponents().get(0)).getComponent_Name()
//		.equals(getText(nameText.getText())) ) {
		editor.updateEMFModuleName(getText(nameText.getText()));
		editor.updateDirty();
	}

	/**
	 * �f�[�^�����[�h����
	 */
	public void load() {
		RtcParam rtcParam = editor.getRtcParam();

		nameText.setText(getValue(rtcParam.getName()));
//		((Component)buildview.getComponents().get(0)).setComponent_Name(rtcParam.getName());
		descriptionText.setText(getValue(rtcParam.getDescription()));
		versionText.setText(getValue(rtcParam.getVersion()));
		venderText.setText(getValue(rtcParam.getVender()));
		categoryCombo.setText(getValue(rtcParam.getCategory()));
		typeCombo.setText(getValue(rtcParam.getComponentType()));
		activityTypeCombo.setText(getValue(rtcParam.getActivityType()));
		kindCombo.setText(getValue(rtcParam.getComponentKind()));
		maxInstanceText.setText(getValue(String.valueOf(rtcParam
				.getMaxInstance())));
		executionTypeCombo.setText(getValue(rtcParam.getExecutionType()));
		executionRateText.setText(getValue(String.valueOf(rtcParam
				.getExecutionRate())));
		outputProjectText.setText(getValue(rtcParam.getOutputProject()));
		//
		editor.updateEMFModuleName(rtcParam.getName());
	}

	protected void addDefaultComboValue(){
		addDefaultComboValue(categoryCombo, CATEGORY_INDEX_KEY);
	}

}
