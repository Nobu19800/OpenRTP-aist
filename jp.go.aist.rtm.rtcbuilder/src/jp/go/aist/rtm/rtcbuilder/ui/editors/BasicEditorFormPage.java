package jp.go.aist.rtm.rtcbuilder.ui.editors;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBException;

import jp.go.aist.rtm.rtcbuilder.GuiRtcBuilder;
import jp.go.aist.rtm.rtcbuilder.IRTCBMessageConstants;
import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.RtcBuilderPlugin;
import jp.go.aist.rtm.rtcbuilder.extension.ExportExtension;
import jp.go.aist.rtm.rtcbuilder.extension.ImportExtension;
import jp.go.aist.rtm.rtcbuilder.generator.ProfileHandler;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.manager.GenerateManager;
import jp.go.aist.rtm.rtcbuilder.ui.StringUtil;
import jp.go.aist.rtm.rtcbuilder.ui.Perspective.LanguageProperty;
import jp.go.aist.rtm.rtcbuilder.ui.dialog.ProjectSelectDialog;
import jp.go.aist.rtm.rtcbuilder.ui.preference.ComponentPreferenceManager;
import jp.go.aist.rtm.rtcbuilder.ui.preference.PortPreferenceManager;
import jp.go.aist.rtm.rtcbuilder.ui.wizard.RtcExportWizard;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

/**
 * Basic Profile 設定ページ
 */
public class BasicEditorFormPage extends AbstractEditorFormPage {

	/**
	 * 生成を行ったCategoryの情報を保存するワークスペース永続文字列へのキー
	 */
	private static final String CATEGORY_INDEX_KEY = BasicEditorFormPage.class.getName() + ".category.name";
	private final String CATEGORY_COMPOSITE =  "composite.";

	private Text nameText;
	private Combo categoryCombo;
	private Text descriptionText;
	private Text versionText;
	private Text venderText;
	private Combo activityTypeCombo;
	private Combo typeCombo;
	private Text maxInstanceText;
	private Combo executionTypeCombo;
	private Text executionRateText;
	private Text abstractText;
	private Button dataFlowBtn;
	private Button fsmBtn;
	private Button multiModeBtn;
	private Text rtcTypeText;

	private Text outputProjectText;

	private Button generateButton;
	private Button packageButton;

	private Button profileLoadButton;
	private Button profileSaveButton;

	private Composite generateSection;
	private Composite outputProjectSection;
	private Composite profileSection;

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
	
	/**
	 * コンストラクタ
	 * 
	 * @param editor
	 *            親のエディタ
	 */
	public BasicEditorFormPage(RtcBuilderEditor editor) {
		super(editor, "id", IMessageConstants.BASIC_SECTION);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void createFormContent(IManagedForm managedForm) {
		ScrolledForm form = super.createBase(managedForm);
		FormToolkit toolkit = managedForm.getToolkit();

		Label label = toolkit.createLabel(form.getBody(), IMessageConstants.BASIC_SECTION);
		if( titleFont==null ) {
			titleFont = new Font(form.getDisplay(), IMessageConstants.TITLE_FONT, 16, SWT.BOLD);
		}
		label.setFont(titleFont);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		label.setLayoutData(gd);
		//
		createModuleSection(toolkit, form);
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
		//
		createHintSection(toolkit, form);
		createOutputProjectSection(toolkit, form);
		createGenerateSection(toolkit, form);
		createExportImportSection(toolkit, form);
		//
		// 言語・環境ページより先にこのページが表示された場合、ここで言語を判断する
		editor.setEnabledInfoByLang();
		
		load();
	}
	
	private String getFileExtension(String filename){
		int index = filename.lastIndexOf(".");
		if( index > -1 ) return filename.substring(index + 1);
		return "";
	}
	
	private void switchPerspective() {

		for(RtcParam rtcParam : editor.getGeneratorParam().getRtcParams() ) {
			//Pluginの存在確認
			LanguageProperty langProp = LanguageProperty.checkPlugin(rtcParam);
			String currentPerspectiveId = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
	            							.getActivePage().getPerspective().getId();
			if( langProp != null && !langProp.getPerspectiveId().equals(currentPerspectiveId) ) {
				MessageBox message = new MessageBox(getSite().getShell(), 
						SWT.ICON_QUESTION | SWT.YES | SWT.NO);
				message.setText(IMessageConstants.BASIC_PERSPECTIVE_TEXT);
				message.setMessage(IMessageConstants.BASIC_PERSPECTIVE_MSG1 + langProp.getPerspectiveName() 
						+ IMessageConstants.BASIC_PERSPECTIVE_MSG2);
				if( message.open() == SWT.YES) {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().setPerspective(
							PlatformUI.getWorkbench().getPerspectiveRegistry().findPerspectiveWithId(
									langProp.getPerspectiveId()));
					}
			}
		}
	}

	/**
	 * バリデートを行う。エラーがない場合にはnullを返し、エラーがある場合にはメッセージを返す。
	 * 
	 * @return
	 */
	public String validateParam() {
		String result = null;
		//Module Name
		if (result == null && nameText.getText().length() == 0) {
			result = IMessageConstants.BASIC_VALIDATE_NAME1;
		}
		if( !StringUtil.checkDigitAlphabet(nameText.getText()) ) {
			result = IMessageConstants.BASIC_VALIDATE_NAME2;
		}
		//Module category
		if (result == null && categoryCombo.getText().length() == 0) {
			result = IMessageConstants.BASIC_VALIDATE_CATEGORY;
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
				result = IMessageConstants.BASIC_VALIDATE_MAXINST1;
			}
			if (parseInt != null && parseInt.intValue() < 0) {
				result = IMessageConstants.BASIC_VALIDATE_MAXINST2;
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
			result = IMessageConstants.BASIC_VALIDATE_ECRATE1;
		}
		if (parseDbl != null && parseDbl.intValue() < 0) {
			result = IMessageConstants.BASIC_VALIDATE_ECRATE2;
		}
		//Component Kind
		if( !dataFlowBtn.getSelection() && 
				!fsmBtn.getSelection() &&
				!multiModeBtn.getSelection() ) {
			result = "Please Select Component Kind.";
		}
		//Composite Component
		if( categoryCombo.getText().startsWith(CATEGORY_COMPOSITE) && 
				(editor.getRtcParam().getInports().size()>0 || editor.getRtcParam().getOutports().size()>0 ||
				 editor.getRtcParam().getServicePorts().size()>0) ) {
			result = "Cannot add any Ports to Composite Component.";
		}

		return result;
	}

	private void createModuleSection(FormToolkit toolkit, ScrolledForm form) {
		Composite composite = createSectionBaseWithLabel(toolkit, form, 
				IMessageConstants.BASIC_COMPONENT_TITLE, IMessageConstants.BASIC_COMPONENT_EXPL, 2);
		//

		nameText = createLabelAndText(toolkit, composite, IMessageConstants.BASIC_LBL_MODULENAME);
		descriptionText = createLabelAndText(toolkit, composite, IMessageConstants.BASIC_LBL_DESCRIPTION);
		versionText = createLabelAndText(toolkit, composite, IMessageConstants.BASIC_LBL_VERSION);
		venderText = createLabelAndText(toolkit, composite, IMessageConstants.BASIC_LBL_VENDOR);
//		String[] defaultCategory = {"composite.PeriodicECShared", "composite.PeriodicStateShared", "composite.FsmECShared", "composite.FsmStateShared"};
		String[] defaultCategory = {};
		categoryCombo = createEditableCombo(toolkit, composite,	IMessageConstants.BASIC_LBL_CATEGORY,
				CATEGORY_INDEX_KEY, defaultCategory);
		typeCombo = createLabelAndCombo(toolkit, composite, IMessageConstants.BASIC_LBL_COMPONENT_TYPE,
				IRtcBuilderConstants.COMPONENT_TYPE_ITEMS);
		activityTypeCombo = createLabelAndCombo(toolkit, composite, IMessageConstants.BASIC_LBL_ACTIVITY_TYPE,
				IRtcBuilderConstants.ACTIVITY_TYPE_ITEMS);
		//
		toolkit.createLabel(composite, IMessageConstants.BASIC_LBL_COMPONENT_KIND);
		Group compGroup = new Group(composite, SWT.NONE);
		compGroup.setLayout(new GridLayout(3, false));
		GridData gd = new GridData();
		compGroup.setLayoutData(gd);
		dataFlowBtn = createRadioCheckButton(toolkit, compGroup, "DataFlow", SWT.CHECK);
		fsmBtn = createRadioCheckButton(toolkit, compGroup, "FSM", SWT.CHECK);
		multiModeBtn = createRadioCheckButton(toolkit, compGroup, "MultiMode", SWT.CHECK);
		//
		maxInstanceText = createLabelAndText(toolkit, composite, IMessageConstants.BASIC_LBL_MAX_INSTANCES);
		executionTypeCombo = createLabelAndCombo(toolkit, composite, IMessageConstants.BASIC_LBL_EXECUTION_TYPE,
				IRtcBuilderConstants.EXECUTIONCONTEXT_TYPE_ITEMS);
		executionRateText = createLabelAndText(toolkit,	composite, IMessageConstants.BASIC_LBL_EXECUTION_RATE);
		abstractText = createLabelAndText(toolkit, composite, IMessageConstants.BASIC_LBL_ABSTRACT, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = 50;
		gridData.widthHint = 100;
		abstractText.setLayoutData(gridData);
		rtcTypeText = createLabelAndText(toolkit, composite, IMessageConstants.BASIC_LBL_RTCTYPE);
	}

	private void createHintSection(FormToolkit toolkit, ScrolledForm form) {
		Composite composite = createHintSectionBase(toolkit, form, 4);
		//
		createHintLabel(IMessageConstants.BASIC_HINT_MODULENAME_TITLE, IMessageConstants.BASIC_HINT_MODULENAME_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.BASIC_HINT_DESCRIPTION_TITLE, IMessageConstants.BASIC_HINT_DESCRIPTION_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.BASIC_HINT_VERSION_TITLE, IMessageConstants.BASIC_HINT_VERSION_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.BASIC_HINT_VENDOR_TITLE, IMessageConstants.BASIC_HINT_VENDOR_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.BASIC_HINT_CATEGORY_TITLE, IMessageConstants.BASIC_HINT_CATEGORY_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.BASIC_HINT_COMPTYPE_TITLE, IMessageConstants.BASIC_HINT_COMPTYPE_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.BASIC_HINT_ACTIVITYTYPE_TITLE, IMessageConstants.BASIC_HINT_ACTIVITYTYPE_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.BASIC_HINT_COMPKIND_TITLE, IMessageConstants.BASIC_HINT_COMPKIND_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.BASIC_HINT_MAXINST_TITLE, IMessageConstants.BASIC_HINT_MAXINST_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.BASIC_HINT_EXECUTIONTYPE_TITLE, IMessageConstants.BASIC_HINT_EXECUTIONTYPE_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.BASIC_HINT_EXECUTIONRATE_TITLE, IMessageConstants.BASIC_HINT_EXECUTIONRATE_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.BASIC_HINT_ABSTRACT_TITLE, IMessageConstants.BASIC_HINT_ABSTRACT_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.BASIC_HINT_RTCTYPE_TITLE, IMessageConstants.BASIC_HINT_RTCTYPE_DESC, toolkit, composite);
		//
		createHintSpace(toolkit, composite);
		//
		createHintLabel(IMessageConstants.BASIC_HINT_PROJECT_TITLE, IMessageConstants.BASIC_HINT_PROJECT_DESC, toolkit, composite);
		//
		createHintSpace(toolkit, composite);
		//
		createHintLabel(IMessageConstants.BASIC_HINT_GENERATE_TITLE, IMessageConstants.BASIC_HINT_GENERATE_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.BASIC_HINT_PACKAGE_TITLE, IMessageConstants.BASIC_HINT_PACKAGE_DESC, toolkit, composite);
		//
		createHintSpace(toolkit, composite);
		//
		createHintLabel(IMessageConstants.BASIC_HINT_IMPORT_TITLE, IMessageConstants.BASIC_HINT_IMPORT_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.BASIC_HINT_EXPORT_TITLE, IMessageConstants.BASIC_HINT_EXPORT_DESC, toolkit, composite);
	}

	private void createOutputProjectSection(FormToolkit toolkit, ScrolledForm form) {
		outputProjectSection = createSectionBaseWithLabel(toolkit, form, 
				IMessageConstants.BASIC_PROJECT_TITLE, IMessageConstants.BASIC_PROJECT_EXPL, 2);

		outputProjectText = createLabelAndText(toolkit,	outputProjectSection, "");
		Button refButton = toolkit.createButton(outputProjectSection, IMessageConstants.BASIC_BTN_REF, SWT.NONE);
		refButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ProjectSelectDialog dialog = new ProjectSelectDialog(getSite().getShell());
				int intRet = dialog.open();
				if( intRet == IDialogConstants.OK_ID && dialog.getSelectedProject() != null ) {
					outputProjectText.setText(dialog.getSelectedProject());
				}
				//
//				//出力先ディレクトリ直接選択
//				DirectoryDialog dialog = new DirectoryDialog(getEditorSite()
//						.getShell());
//				dialog.setText(IMessageConstants.SELECT_DIRECTORY);
//				if (outputProjectText.getText().length() > 0)
//					dialog.setFilterPath(outputProjectText.getText());
//				String newPath = dialog.open();
//				if (newPath != null) {
//					outputProjectText.setText(newPath);
//					update();
//				}
			}
		});
	}
	
	private void createGenerateSection(FormToolkit toolkit, ScrolledForm form) {
		generateSection = createSectionBaseWithLabel(toolkit, form, 
				IMessageConstants.BASIC_GENERATE_TITLE, IMessageConstants.BASIC_GENERATE_EXPL, 2);
		//
		generateButton = toolkit.createButton(generateSection, IMessageConstants.BASIC_BTN_GENERATE, SWT.NONE);
		generateButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editor.allUpdates();
				String validateRtcParam = editor.validateParam();
				if (validateRtcParam != null) {
					MessageDialog.openError(getSite().getShell(), "Error", validateRtcParam);
					return;
				}
				//対象プロジェクトの確認
				IProject project = checkTargetProject();
				if( project==null) return;
				// 裏からファイルを削除されている可能性があるため、
				// プロジェクトとファイルシステムの同期を取る
				try {
					project.refreshLocal(IResource.DEPTH_INFINITE, null);
				} catch (CoreException e1) {
					throw new RuntimeException(IRTCBMessageConstants.ERROR_GENERATE_FAILED);
				}
				//
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
				//TODO 複数コンポーネント対応版とする場合には複数設定
				generatorParam.getRtcParams().get(0).getServiceClassParams().clear();
				setPrefixSuffix(generatorParam.getRtcParams().get(0));
				if (rtcBuilder.doGenerateWrite(generatorParam)) {
					// xmlを保存
					ProfileHandler handler = new ProfileHandler();
					try {
						String strXml = handler.convert2XML(editor.getGeneratorParam());

						IFile orgRtcxml = project.getFile(IRtcBuilderConstants.DEFAULT_RTC_XML);
						if (orgRtcxml.exists()) {
							IFile renameFile = project.getFile(IRtcBuilderConstants.DEFAULT_RTC_XML + DATE_FORMAT.format(new GregorianCalendar().getTime()) );
							orgRtcxml.move(renameFile.getFullPath(), true, null);
						}
						IFile saveRtcxml = project.getFile(IRtcBuilderConstants.DEFAULT_RTC_XML);
						saveRtcxml.create(new ByteArrayInputStream(strXml.getBytes("UTF-8")), true, null);
						//
						editor.getRtcParam().resetUpdated();
						editor.updateDirty();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					//
					switchPerspective();
				}
			}

			private IProject checkTargetProject() {
				if( editor.getRtcParam().getOutputProject()==null || "".equals(editor.getRtcParam().getOutputProject()) ){
					MessageDialog.openError(getSite().getShell(), "Error", IRTCBMessageConstants.VALIDATE_ERROR_OUTPUTPROJECT);
					return null;
				}
				IWorkspaceRoot workspaceHandle = ResourcesPlugin.getWorkspace().getRoot();
				IProject project = workspaceHandle.getProject(editor.getRtcParam().getOutputProject());
				if(!project.exists()) {
					IWorkbench workbench = PlatformUI.getWorkbench();
					IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
					Shell shell = window.getShell();
					MessageBox message = new MessageBox(shell, SWT.ICON_QUESTION | SWT.YES | SWT.NO);
					message.setText(IRTCBMessageConstants.CONFIRM_PROJECT_GENERATE_TITLE);
					message.setMessage(IRTCBMessageConstants.CONFIRM_PROJECT_GENERATE);
					if( message.open() != SWT.YES) return null;
					try {
						project.create(null);
						project.open(null);
						LanguageProperty langProp = LanguageProperty.checkPlugin(editor.getRtcParam());
						if(langProp != null) {
							IProjectDescription description = project.getDescription();
							String[] ids = description.getNatureIds();
							String[] newIds = new String[ids.length + langProp.getNatures().size()];
							System.arraycopy(ids, 0, newIds, 0, ids.length);
							for( int intIdx=0; intIdx<langProp.getNatures().size(); intIdx++ ) {
								newIds[ids.length+intIdx] = langProp.getNatures().get(intIdx);
							}
							description.setNatureIds(newIds);
							project.setDescription(description, null);
						}
					} catch (CoreException ex) {
						throw new RuntimeException(IRTCBMessageConstants.ERROR_GENERATE_FAILED);
					}
				}
				return project;
			}
		});
		//
		packageButton = toolkit.createButton(generateSection, IMessageConstants.BASIC_BTN_PACKAGING, SWT.NONE);
		packageButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				WizardDialog dialog = new WizardDialog(getSite().getShell(), new RtcExportWizard());
				dialog.open();
			}
		});
	}
	
	private void setPrefixSuffix(RtcParam param) {
		param.setCommonPrefix(ComponentPreferenceManager.getInstance().getBasic_Prefix());
		param.setCommonSuffix(ComponentPreferenceManager.getInstance().getBasic_Suffix());
		param.setConfigurationPrefix(ComponentPreferenceManager.getInstance().getConfiguration_Prefix());
		param.setConfigurationSuffix(ComponentPreferenceManager.getInstance().getConfiguration_Suffix());
		//
		param.setDataPortPrefix(PortPreferenceManager.getInstance().getDataPort_Prefix());
		param.setDataPortSuffix(PortPreferenceManager.getInstance().getDataPort_Suffix());
		param.setServicePortPrefix(PortPreferenceManager.getInstance().getServicePort_Prefix());
		param.setServicePortSuffix(PortPreferenceManager.getInstance().getServicePort_Suffix());
		param.setServiceIFPrefix(PortPreferenceManager.getInstance().getServiceIF_Prefix());
		param.setServiceIFSuffix(PortPreferenceManager.getInstance().getServiceIF_Suffix());
	}

	private ImportExtension getTargetImportExtension() {
		List list = RtcBuilderPlugin.getDefault().getImportExtensionLoader().getList();
		if( list != null ) {
			String targetLang = editor.getRtcParam().getLanguage();
			for( Iterator iter = list.iterator(); iter.hasNext(); ) {
				ImportExtension extension = (ImportExtension) iter.next();
				if( extension.getManagerKey().equals(targetLang) ){
					return extension;
				}
			}
		}
		return null;
	}
	private ExportExtension getTargetExportExtension() {
		List list = RtcBuilderPlugin.getDefault().getExportExtensionLoader().getList();
		if (list != null) {
			String targetLang = editor.getRtcParam().getLanguage();
			for( Iterator iter = list.iterator(); iter.hasNext(); ) {
				ExportExtension extension = (ExportExtension) iter.next();
				if( extension.getManagerKey().equals(targetLang) ){
					return extension;
				}
			}
		}
		return null;
	}
	
	private void createExportImportSection(FormToolkit toolkit, ScrolledForm form) {
		profileSection = createSectionBaseWithLabel(toolkit, form, 
				IMessageConstants.BASIC_EXPORT_IMPORT_TITLE, IMessageConstants.BASIC_EXPORT_IMPORT_EXPL, 2);
		//
		profileLoadButton = toolkit.createButton(profileSection, IMessageConstants.BASIC_BTN_IMPORT, SWT.NONE);
		profileLoadButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ImportExtension extension = getTargetImportExtension();
				FileDialog dialog = new FileDialog(getSite().getShell(),SWT.OPEN);
		        dialog.setText(IMessageConstants.BASIC_BTN_IMPORT);
		        
				String[] names = extension == null ? new String[] { IMessageConstants.FILETYPE_XML,IMessageConstants.FILETYPE_YAML }
				  					: extension.getFileDialogFilterNames();
				String[] exts = extension == null ? new String[] { "*.xml","*.yaml" }
				 					: extension.getFileDialogFilterExtensions();
				dialog.setFilterNames(names);
				dialog.setFilterExtensions(exts);

				String selectedFileName = dialog.open();
		        if (selectedFileName != null) {
		        	if(extension == null) {
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
							MessageDialog.openError(getSite().getShell(), "Error", IMessageConstants.BASIC_IMPORT_ERROR);
							return;
						}
		        	} else {
			        	try {
		        			extension.setImportData(selectedFileName,editor);
						} catch (Exception e1) {
							String msg = e1.getMessage();
							if (msg == null || msg.equals("")) {
								msg = IMessageConstants.BASIC_IMPORT_ERROR;
							}
							MessageDialog.openError(getSite().getShell(), "Error", msg);
							return;
						}
		        	}
					MessageDialog.openInformation(getSite().getShell(), "Finish",	IMessageConstants.BASIC_IMPORT_DONE);
					//
					editor.allPagesReLoad();
					editor.updateEMFModuleName(editor.getRtcParam().getName());
					editor.updateEMFDataInPorts(editor.getRtcParam().getInports());
					editor.updateEMFDataOutPorts(editor.getRtcParam().getOutports());
					editor.updateEMFServiceOutPorts(editor.getRtcParam().getServicePorts());
					editor.setEnabledInfoByLang();
					load();
					//
					editor.getRtcParam().resetUpdated();
					editor.updateDirty();
		        }
			}
		});
		//
		profileSaveButton = toolkit.createButton(profileSection, IMessageConstants.BASIC_BTN_EXPORT, SWT.NONE);
		profileSaveButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editor.allUpdates();
				String validateRtcParam = editor.validateParam();
				if (validateRtcParam != null) {
					MessageDialog.openError(getSite().getShell(), "Error", validateRtcParam);
					return;
				}
				
				ExportExtension extension = getTargetExportExtension();
				FileDialog dialog = new FileDialog(getSite().getShell(),SWT.SAVE);
		        dialog.setText(IMessageConstants.BASIC_BTN_EXPORT);
				String[] names = extension == null ? new String[] { IMessageConstants.FILETYPE_XML,IMessageConstants.FILETYPE_YAML }
				  					: extension.getFileDialogFilterNames();
				String[] exts = extension == null ? new String[] { "*.xml","*.yaml" }
				 					: extension.getFileDialogFilterExtensions();
				dialog.setFilterNames(names);
				dialog.setFilterExtensions(exts);

				String selectedFileName = dialog.open();
		        if (selectedFileName != null) {
		        	if (extension == null) {
			        	try {
				        	if (getFileExtension(selectedFileName).equals(IRtcBuilderConstants.YAML_EXTENSION)) {
				        		ProfileHandler handler = new ProfileHandler();
				        		handler.createYaml(selectedFileName, editor.getGeneratorParam());
				        	} else {
				        		ProfileHandler handler = new ProfileHandler();
				        		try {
									handler.validateXml(handler.convert2XML(editor.getGeneratorParam()));
								} catch (JAXBException ex) {
				        			if (!MessageDialog.openQuestion(getSite().getShell(),ex.getMessage(),
				        					IMessageConstants.PROFILE_VALIDATE_ERROR_MESSAGE + System.getProperty("line.separator") + ex.getCause().toString()) )
				        				return;// 「いいえ」のときは保存しない
				        		}// 通常のExceptionは外側でcatchする
								handler.storeToXML(selectedFileName, editor.getGeneratorParam());
				        	}
							editor.getRtcParam().resetUpdated();
							editor.updateDirty();
						} catch (Exception e1) {
							MessageDialog.openError(getSite().getShell(), "Error", IMessageConstants.BASIC_EXPORT_ERROR);
							return;
						}
		        	} else {
						try {
							extension.export(selectedFileName, editor);
							editor.getRtcParam().resetUpdated();
							editor.updateDirty();
						} catch (Exception e1) {
							String msg = e1.getMessage();
							if (msg == null || msg.equals("")) {
								msg = IMessageConstants.BASIC_EXPORT_ERROR;
							}
							MessageDialog.openError(getSite().getShell(), "Error", msg);
							return;
						}
					}
					MessageDialog.openInformation(getSite().getShell(), "Finish", IMessageConstants.BASIC_EXPORT_DONE);
		        }
			}
		});
		//
	}

	protected void update() {
		RtcParam rtcParam = editor.getRtcParam();

		rtcParam.setName(getText(nameText.getText()));
		rtcParam.setDescription(getText(descriptionText.getText()));
		rtcParam.setVersion(getText(versionText.getText()));
		rtcParam.setVender(getText(venderText.getText()));
		rtcParam.setCategory(getText(categoryCombo.getText()));
		rtcParam.setActivityType(getText(activityTypeCombo.getText()));
		rtcParam.setComponentType(getText(typeCombo.getText()));
		rtcParam.setComponentKind(getSelectedCompKind());
		rtcParam.setAbstract(getText(abstractText.getText()));
		rtcParam.setRtcType(getText(rtcTypeText.getText()));

		try {
			int maxInstance = Integer.parseInt(getText(maxInstanceText
					.getText()));
			rtcParam.setMaxInstance(maxInstance);
		} catch (Exception e) {
			// 例外の場合、画面の値を現在の値に戻す
			maxInstanceText.setText(String.valueOf(rtcParam.getMaxInstance()));
		}

		rtcParam.setExecutionType(getText(executionTypeCombo.getText()));
		try {
			double exec_rate = Double.parseDouble(getText(executionRateText
					.getText()));
			rtcParam.setExecutionRate(exec_rate);
		} catch (Exception e) {
			// 例外の場合、画面の値を現在の値に戻す
			executionRateText.setText(String.valueOf(rtcParam.getExecutionRate()));
		}

		rtcParam.setOutputProject(getText(outputProjectText.getText()));

		editor.updateEMFModuleName(getText(nameText.getText()));
		editor.updateDirty();
	}

	/**
	 * データをロードする
	 */
	public void load() {
		RtcParam rtcParam = editor.getRtcParam();

		nameText.setText(getValue(rtcParam.getName()));
		descriptionText.setText(getValue(rtcParam.getDescription()));
		versionText.setText(getValue(rtcParam.getVersion()));
		venderText.setText(getValue(rtcParam.getVender()));
		categoryCombo.setText(getValue(rtcParam.getCategory()));
		loadSelectedCompKind(rtcParam.getComponentKind());
		activityTypeCombo.setText(getValue(rtcParam.getActivityType()));
		typeCombo.setText(getValue(rtcParam.getComponentType()));
		maxInstanceText.setText(getValue(String.valueOf(rtcParam
				.getMaxInstance())));
		executionTypeCombo.setText(getValue(rtcParam.getExecutionType()));
		executionRateText.setText(getValue(String.valueOf(rtcParam
				.getExecutionRate())));
		abstractText.setText(getValue(rtcParam.getAbstract()));
		rtcTypeText.setText(getValue(rtcParam.getRtcType()));
		outputProjectText.setText(getValue(rtcParam.getOutputProject()));
		//
		editor.updateEMFModuleName(rtcParam.getName());
	}

	protected void addDefaultComboValue(){
		addDefaultComboValue(categoryCombo, CATEGORY_INDEX_KEY);
	}
	
	private void loadSelectedCompKind(String type) {
		if( type.contains("DataFlow") ) {
			dataFlowBtn.setSelection(true);
		}
		if( type.contains("FiniteStateMachine") ) {
			fsmBtn.setSelection(true);
		}
		if( type.contains("MultiMode") ) {
			multiModeBtn.setSelection(true);
		}
	}
	private String getSelectedCompKind() {
		StringBuffer result = new StringBuffer();
		
		if(dataFlowBtn.getSelection()) {
			result.append("DataFlow");
		}
		if(fsmBtn.getSelection()) {
			result.append("FiniteStateMachine");
		}
		if(multiModeBtn.getSelection()) {
			result.append("MultiMode");
		}
		if( result.length() > 0 ) {
			result.append("Component");
		}
		
		return result.toString();
	}

	@Override
	protected Text createLabelAndText(FormToolkit toolkit, Composite composite, String labelString) {
		Text text = super.createLabelAndText(toolkit, composite, labelString);
		GridData gd = (GridData)text.getLayoutData();
		gd.widthHint = 100;
		return text;
	}

	@Override
	protected Combo createEditableCombo(FormToolkit toolkit, Composite composite, String labelString, String key, String[] defaultValue) {
		Combo combo = super.createEditableCombo(toolkit, composite, labelString, key, defaultValue); 
		GridData gd = (GridData)combo.getLayoutData();
		gd.widthHint = 100;
		return combo;
	}

	/**
	 * BasicInfoフォーム内の要素の有効/無効を設定します。
	 * <ul>
	 * <li>basic.info.moduleName : BasicInfoセクションの Module name</li>
	 * <li>basic.info.moduleDescription : BasicInfoセクションの Module description</li>
	 * <li>basic.info.moduleVersion : BasicInfoセクションの Module version</li>
	 * <li>basic.info.moduleVendor : BasicInfoセクションの Module vendor</li>
	 * <li>basic.info.moduleCategory : BasicInfoセクションの Module category</li>
	 * <li>basic.info.componentType : BasicInfoセクションの Component type</li>
	 * <li>basic.info.activityType : BasicInfoセクションの Component's activity type</li>
	 * <li>basic.info.dataFlow : BasicInfoセクションの Component kind の DataFlow</li>
	 * <li>basic.info.fsm : BasicInfoセクションの Component kind の FSM</li>
	 * <li>basic.info.multiMode : BasicInfoセクションの Component kind の MultiMode</li>
	 * <li>basic.info.maxInstances : BasicInfoセクションの maximum instances</li>
	 * <li>basic.info.executionType : BasicInfoセクションの Execution type</li>
	 * <li>basic.info.executionRate : BasicInfoセクションの Execution rate</li>
	 * <li>basic.info.abstract : BasicInfoセクションの Abstract</li>
	 * <li>basic.info.rtcType : BasicInfoセクションの RTC type</li>
	 * <li>basic.outputProject.* : OutputProjectセクション全体</li>
	 * <li>basic.generate.code : Generateセクションのコード生成ボタン</li>
	 * <li>basic.generate.package : Generateセクションのパッケージ化ボタン</li>
	 * <li>basic.profile.import : Profileセクションのインポートボタン</li>
	 * <li>basic.profile.export : Profileセクションのエクスポートボタン</li>
	 * </ul>
	 */
	public void setEnabledInfo(WidgetInfo widgetInfo, boolean enabled) {
		if (widgetInfo.matchSection("info")) {
			if (nameText != null) {
				if (widgetInfo.matchWidget("moduleName")) {
					setControlEnabled(nameText, enabled);
				}
				if (widgetInfo.matchWidget("moduleDescription")) {
					setControlEnabled(descriptionText, enabled);
				}
				if (widgetInfo.matchWidget("moduleVersion")) {
					setControlEnabled(versionText, enabled);
				}
				if (widgetInfo.matchWidget("moduleVendor")) {
					setControlEnabled(venderText, enabled);
				}
				if (widgetInfo.matchWidget("moduleCategory")) {
					setControlEnabled(categoryCombo, enabled);
				}
				if (widgetInfo.matchWidget("componentType")) {
					setControlEnabled(typeCombo, enabled);
				}
				if (widgetInfo.matchWidget("activityType")) {
					setControlEnabled(activityTypeCombo, enabled);
				}
				if (widgetInfo.matchWidget("dataFlow")) {
					setControlEnabled(dataFlowBtn, enabled);
				}
				if (widgetInfo.matchWidget("fsm")) {
					setControlEnabled(fsmBtn, enabled);
				}
				if (widgetInfo.matchWidget("multiMode")) {
					setControlEnabled(multiModeBtn, enabled);
				}
				if (widgetInfo.matchWidget("maxInstances")) {
					setControlEnabled(maxInstanceText, enabled);
				}
				if (widgetInfo.matchWidget("executionType")) {
					setControlEnabled(executionTypeCombo, enabled);
				}
				if (widgetInfo.matchWidget("executionRate")) {
					setControlEnabled(executionRateText, enabled);
				}
				if (widgetInfo.matchWidget("abstract")) {
					setControlEnabled(abstractText, enabled);
				}
				if (widgetInfo.matchWidget("rtcType")) {
					setControlEnabled(rtcTypeText, enabled);
				}
			}
		}
		if (widgetInfo.matchSection("outputProject")) {
			if (outputProjectSection != null) {
				outputProjectSection.setEnabled(enabled);
				setEnableBackground(outputProjectSection,
						getBackgroundByEnabled(enabled));
			}
		}
		if (widgetInfo.matchSection("generate")) {
			if (generateSection != null) {
				if (widgetInfo.matchWidget("code")) {
					setButtonEnabled(generateButton, enabled);
				}
				if (widgetInfo.matchWidget("package")) {
					setButtonEnabled(packageButton, enabled);
				}
				boolean genEnable = false;
				if (generateButton.getEnabled() || packageButton.getEnabled()) {
					genEnable = true;
				}
				generateSection.setEnabled(genEnable);
				setEnableBackground(generateSection,
						getBackgroundByEnabled(genEnable));
			}
		}
		if (widgetInfo.matchSection("profile")) {
			if (profileSection != null) {
				if (widgetInfo.matchWidget("import")) {
					setButtonEnabled(profileLoadButton, enabled);
				}
				if (widgetInfo.matchWidget("export")) {
					setButtonEnabled(profileSaveButton, enabled);
				}
				boolean profEnable = false;
				if (profileLoadButton.getEnabled()
						|| profileSaveButton.getEnabled()) {
					profEnable = true;
				}
				profileSection.setEnabled(profEnable);
				setEnableBackground(profileSection,
						getBackgroundByEnabled(profEnable));
			}
		}
	}

}
