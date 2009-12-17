package jp.go.aist.rtm.rtcbuilder.ui.editors;

import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jp.go.aist.rtm.rtcbuilder.RtcBuilderPlugin;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.IDLParser;
import jp.go.aist.rtm.rtcbuilder.generator.IDLParamConverter;
import jp.go.aist.rtm.rtcbuilder.generator.PreProcessor;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceClassParam;
import jp.go.aist.rtm.rtcbuilder.ui.StringUtil;
import jp.go.aist.rtm.rtcbuilder.ui.preference.PortPreferenceManager;
import jp.go.aist.rtm.rtcbuilder.util.FileUtil;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.MasterDetailsBlock;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

/**
 * ServicePort�y�[�W
 */
public class ServicePortEditorFormPage extends AbstractEditorFormPage {

	private static final String IDL_EXTENTION = "idl";

	private Section servicePortMasterBlockSection;
	private TreeViewer servicePortViewer;
	private Button addButton;
	private Button addinterfaceButton;
	private Button deleteButton;
	
	//
	private Text nameText;
	private Combo positionCombo;
	//
	private Text descriptionText;
	private Text ifoverviewText;
	//
	private Text interfaceNameText;
	private Combo directionCombo;
	private Text instanceNameText;
	private Text varNameText;
	private Text idlFileText;
//	private Text interfaceTypeText;
	private Combo interfaceTypeCombo;
	private Text idlPathText;
	//
	private Text ifdetailText;
	private Text ifargumentText;
	private Text ifreturnText;
	private Text ifexceptionText;
	private Text ifpreconditionText;
	private Text ifpostconditionText;
	//
	private String defaultPortName;
	//
	private String defaultIFName;
	private String defaultIFInstanceName;
	private String defaultIFVarName;
	
	/**
	 * �R���X�g���N�^
	 * 
	 * @param editor
	 *            �e�̃G�f�B�^
	 */
	public ServicePortEditorFormPage(RtcBuilderEditor editor) {
		super(editor, "id", IMessageConstants.SERVICEPORT_SECTION);
		updateDefaultValue();
	}

	private void updateDefaultValue() {
		defaultPortName =PortPreferenceManager.getInstance().getServicePort_Name();
		
		defaultIFName =PortPreferenceManager.getInstance().getServiceIF_Name();
		defaultIFInstanceName =PortPreferenceManager.getInstance().getServiceIF_InstanceName();
		defaultIFVarName =PortPreferenceManager.getInstance().getServiceIF_VarName();
	}

	/**
	 * {@inheritDoc}
	 */
	protected void createFormContent(IManagedForm managedForm) {
		GridLayout gl = new GridLayout();
		gl.numColumns = 1;
		FormToolkit toolkit = managedForm.getToolkit();
		Composite composite = toolkit.createComposite(managedForm.getForm().getBody(), SWT.NULL);
		gl = new GridLayout(1, true);
		composite.setLayout(gl);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		composite.setLayoutData(gd);

		Label title = toolkit.createLabel(composite, IMessageConstants.SERVICEPORT_SECTION);
		if( titleFont==null ) {
			titleFont = new Font(managedForm.getForm().getDisplay(), IMessageConstants.TITLE_FONT, 16, SWT.BOLD);
		}
		title.setFont(titleFont);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		title.setLayoutData(gd);
		
		ServicePortMasterBlock block = new ServicePortMasterBlock();
		block.createContent(managedForm);
		
		// ����E���y�[�W����ɂ��̃y�[�W���\�����ꂽ�ꍇ�A�����Ō���𔻒f����
		editor.setEnabledInfoByLang();

		load();
		
		// ������Ԃɉ����āA�{�^���̊�����Ԃ����肷��
		// ���̏����́ARTC.xml�ǂݍ��݂Ɉˑ����邽�߁Aload()����ł���K�v������B
		setButtonEnabled(servicePortViewer.getSelection());
	}
	
	private void setButtonEnabled(ISelection selection){
		if( selection!=null && !selection.isEmpty() ){
			if( addinterfaceButton!=null ) addinterfaceButton.setEnabled(true);
			if( deleteButton!=null ) deleteButton.setEnabled(true);
		}else{
			if( addinterfaceButton!=null ) addinterfaceButton.setEnabled(false);
			if( deleteButton!=null ) deleteButton.setEnabled(false);
		}
	}
	
	private Text createLabelAndFile(FormToolkit toolkit, Composite composite,
			final String extention, String labelString) {
		GridData gd;

		toolkit.createLabel(composite, labelString);
		final Text text = toolkit.createText(composite, "");
		text.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
				update();
			}

			public void keyPressed(KeyEvent e) {
			}
		});
		gd = new GridData(GridData.FILL_HORIZONTAL);
		text.setLayoutData(gd);

		Button checkButton = toolkit.createButton(composite, "Browse...",
				SWT.PUSH);
		gd = new GridData(GridData.HORIZONTAL_ALIGN_END);
		checkButton.setLayoutData(gd);
		checkButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(getEditorSite().getShell());
				dialog.setFilterExtensions(new String[] { "*." + extention });
				if (text.getText().length() > 0)
					dialog.setFileName(text.getText());
				String newPath = dialog.open();
				if (newPath != null) {
					text.setText(newPath);
					update();
				}
			}
		});
		text.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
				update();
			}

			public void keyPressed(KeyEvent e) {
			}
		});

		return text;
	}

	public void update() {
		if(servicePortViewer != null ) {
			servicePortViewer.getTree().setRedraw(false);
			TreeItem[] selections = servicePortViewer.getTree().getSelection();
			if( selections.length > 0 ) {
				TreeItem selection = selections[0];
				if( selection.getData() instanceof ServicePortParam ) {
					if( nameText.getText()==null || nameText.getText().length()==0 ) {
						((ServicePortParam)selection.getData()).setName(" ");
					} else {
						((ServicePortParam)selection.getData()).setName(getText(nameText.getText()));
					}
					((ServicePortParam)selection.getData()).setPositionByIndex(positionCombo.getSelectionIndex());
					//
					((ServicePortParam)selection.getData()).setDocDescription(getDocText(descriptionText.getText()));
					((ServicePortParam)selection.getData()).setDocIfDescription(getDocText(ifoverviewText.getText()));
				} else if( selection.getData() instanceof ServicePortInterfaceParam ) {
					if( !((ServicePortInterfaceParam)selection.getData()).getIdlFile().equals(
							idlFileText.getText()) ) {
						if(idlFileText.getText()!=null && idlFileText.getText().length()>0) {
							String targetContent = PreProcessor.parseAlltoSpace(FileUtil.readFile(idlFileText.getText()));
							IDLParser parser = new IDLParser(new StringReader(targetContent));
							try {
								List<ServiceClassParam> serviceClassParams = IDLParamConverter.convert(parser.specification(), "");
								if( serviceClassParams!=null && serviceClassParams.size()>0 ) {
									interfaceTypeCombo.removeAll();
									for(ServiceClassParam target : serviceClassParams) {
										interfaceTypeCombo.add(target.getName());
									}
								}
							} catch (Throwable e) {
								MessageDialog.openError(getSite().getShell(), "Error", 
										IMessageConstants.PREF_IDLPARSE_ERROR + System.getProperty( "line.separator" ) + System.getProperty( "line.separator" ) + 
										e.getMessage() );
								String selected = interfaceTypeCombo.getText();
								interfaceTypeCombo.removeAll();
								interfaceTypeCombo.setText(selected);
								servicePortViewer.getTree().setRedraw(true);
							}
						}
					}
					((ServicePortInterfaceParam)selection.getData()).setName(interfaceNameText.getText());
					((ServicePortInterfaceParam)selection.getData()).setIndex(directionCombo.getSelectionIndex());
					((ServicePortInterfaceParam)selection.getData()).setInstanceName(instanceNameText.getText());
					((ServicePortInterfaceParam)selection.getData()).setVarName(varNameText.getText());
					((ServicePortInterfaceParam)selection.getData()).setIdlFile(idlFileText.getText());
//					((ServicePortInterfaceParam)selection.getData()).setInterfaceType(interfaceTypeText.getText());
					((ServicePortInterfaceParam)selection.getData()).setInterfaceType(interfaceTypeCombo.getText());
					((ServicePortInterfaceParam)selection.getData()).setIdlSearchPath(idlPathText.getText());
					//
					((ServicePortInterfaceParam)selection.getData()).setDocDescription(getDocText(ifdetailText.getText()));
					((ServicePortInterfaceParam)selection.getData()).setDocArgument(getDocText(ifargumentText.getText()));
					((ServicePortInterfaceParam)selection.getData()).setDocReturn(getDocText(ifreturnText.getText()));
					((ServicePortInterfaceParam)selection.getData()).setDocException(getDocText(ifexceptionText.getText()));
					((ServicePortInterfaceParam)selection.getData()).setDocPreCondition(getDocText(ifpreconditionText.getText()));
					((ServicePortInterfaceParam)selection.getData()).setDocPostCondition(getDocText(ifpostconditionText.getText()));
					//
				}
			}
			Object[] expanded = servicePortViewer.getExpandedElements();
			servicePortViewer.setInput(editor.getRtcParam().getServicePorts());
			servicePortViewer.getTree().setRedraw(true);
			servicePortViewer.setExpandedElements(expanded);
			//
			editor.updateEMFServiceOutPorts(editor.getRtcParam().getServicePorts());
			editor.updateDirty();
		}
	}

	/**
	 * �f�[�^�����[�h����
	 */
	public void load() {
		GeneratorParam generator = editor.getGeneratorParam();
		if( generator.getRtcParams().size() > 0 ) {
			RtcParam rtcParam = generator.getRtcParams().get(0);
			if( servicePortViewer != null )
				servicePortViewer.setInput(rtcParam.getServicePorts());
			editor.updateEMFServiceOutPorts(editor.getRtcParam().getServicePorts());
		}
	}

	public String validateParam() {
		String result = null;

		RtcParam rtcParam = editor.getRtcParam();
		Set<String> checkSet = new HashSet<String>(); 
		
		for(ServicePortParam serviceport : rtcParam.getServicePorts()) {
			//ServicePort name
			if( serviceport.getName()==null || serviceport.getName().trim().length()==0 ) {
				result = IMessageConstants.SERVICEPORT_VALIDATE_PORTNAME1;
				return result;
			}
			if( !StringUtil.checkDigitAlphabet(serviceport.getName()) ) {
				result = IMessageConstants.SERVICEPORT_VALIDATE_PORTNAME2;
				return result;
			}
			//�d��
			if( checkSet.contains(serviceport.getName()) ) {
				result = IMessageConstants.SERVICEPORT_VALIDATE_DUPLICATE;
				return result;
			}
			checkSet.add(serviceport.getName());
			for(ServicePortInterfaceParam ifparam : serviceport.getServicePortInterfaces()) {
				//ServiceInterface name
				if( ifparam.getName()==null || ifparam.getName().length()==0 ) {
					result = IMessageConstants.SERVICEPORT_VALIDATE_IFNAME1;
					return result;
				}
				if( !StringUtil.checkDigitAlphabet(ifparam.getName()) ) {
					result = IMessageConstants.SERVICEPORT_VALIDATE_IFNAME2;
					return result;
				}
				//ServiceInterface Instance name
				if( ifparam.getInstanceName()==null || ifparam.getInstanceName().length()==0 ) {
					result = IMessageConstants.SERVICEPORT_VALIDATE_INSTNAME1;
					return result;
				}
				if( !StringUtil.checkDigitAlphabet(ifparam.getInstanceName()) ) {
					result = IMessageConstants.SERVICEPORT_VALIDATE_INSTNAME2;
					return result;
				}
				//ServiceInterface Instance type
				if( ifparam.getInterfaceType()==null || ifparam.getInterfaceType().length()==0 ) {
					result = IMessageConstants.SERVICEPORT_VALIDATE_IFTYPE1;
					return result;
				}
				if( !StringUtil.checkDigitAlphabet(ifparam.getInterfaceType()) ) {
					result = IMessageConstants.SERVICEPORT_VALIDATE_IFTYPE2;
					return result;
				}
			}
		}
		return null;
	}

	private MenuManager createContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				manager.add(new Action("Open...") {
					@Override
					public void run() { editor.open(); }
				});
				manager.add(new Action("Save ...") {
					@Override
					public void run() { editor.doSave(null); }
					@Override
					public boolean isEnabled() { return editor.isDirty(); }
				});
				manager.add(new Action("Save As...") {
					@Override
					public void run() { editor.doSaveAs(); }
				});
			}
		});
		return menuMgr;
	}

	//Master Block �N���X
	private class ServicePortMasterBlock extends MasterDetailsBlock {

		@Override
		protected void createMasterPart(final IManagedForm managedForm, Composite parent) {
			FormToolkit toolkit = managedForm.getToolkit();
			servicePortMasterBlockSection = toolkit.createSection(parent, Section.TITLE_BAR);
			servicePortMasterBlockSection.setText(IMessageConstants.SERVICEPORT_MAIN_TITLE);
			Composite client = toolkit.createComposite(servicePortMasterBlockSection);
			client.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
			client.setLayout(new GridLayout(2, false));
			//
			MenuManager menuMgr = createContextMenu();
			client.setMenu(menuMgr.createContextMenu(client));
			//////
			Tree tree = toolkit.createTree(client, SWT.BORDER);
			servicePortViewer = new TreeViewer(tree);
			servicePortViewer.setContentProvider(new ServiceParamContentProvider());
			servicePortViewer.setLabelProvider(new ServiceParamLabelProvider());
			servicePortViewer.setInput(editor.getRtcParam().getServicePorts());
			GridData gridData = new GridData(GridData.FILL_BOTH);
			gridData.grabExcessHorizontalSpace = true;
			gridData.verticalSpan  = 4;
			tree.setLayoutData(gridData);
			servicePortViewer.getControl().setMenu(menuMgr.createContextMenu(servicePortViewer.getControl()));
			//
			addButton = managedForm.getToolkit().createButton(client, IMessageConstants.SERVICEPORT_BTN_ADDPORT, SWT.PUSH);
			addButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					updateDefaultValue();
					ServicePortParam selectParam = new ServicePortParam(defaultPortName, 0);
					((List) servicePortViewer.getInput()).add(selectParam);
					update();
				}
			});
			gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
			gridData.verticalAlignment = GridData.VERTICAL_ALIGN_CENTER;
			addButton.setLayoutData(gridData);
			//
			addinterfaceButton = managedForm.getToolkit().createButton(client, IMessageConstants.SERVICEPORT_BTN_ADDIF, SWT.PUSH);
			addinterfaceButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					updateDefaultValue();
					TreeItem[] selections = servicePortViewer.getTree().getSelection();
					if( selections==null || selections.length==0 )
						return;// �����I������Ă��Ȃ��Ƃ��͉������Ȃ�
					TreeItem selection = selections[0];
					if( selection.getData() instanceof ServicePortParam ) {
						servicePortViewer.getTree().setRedraw(false);
						ServicePortInterfaceParam selectParam = new ServicePortInterfaceParam((ServicePortParam)selection.getData() ,
								defaultIFName, defaultIFInstanceName, defaultIFVarName, "", "", "", 0);
						((ServicePortParam)selection.getData()).getServicePortInterfaces().add(selectParam);
						Object[] expanded = servicePortViewer.getExpandedElements();
						List<Object> expanding = new ArrayList<Object>();
						Collections.addAll(expanding, expanded);
						if( !expanding.contains(selection.getData()) ) {
							expanding.add(selection.getData());
							servicePortViewer.setExpandedElements(expanding.toArray());
						}
						update();
						servicePortViewer.getTree().setRedraw(true);
					}
				}
			});
			gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
			gridData.verticalAlignment = GridData.VERTICAL_ALIGN_CENTER;
			addinterfaceButton.setLayoutData(gridData);
			//
			deleteButton = managedForm.getToolkit().createButton(client, IMessageConstants.SERVICEPORT_BTN_DELETE, SWT.PUSH);
			deleteButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					TreeItem[] selections = servicePortViewer.getTree().getSelection();
					if( selections==null || selections.length==0 )
						return;// �����I������Ă��Ȃ��Ƃ��͉������Ȃ�
					TreeItem selection = selections[0];
					if( selection.getData() instanceof ServicePortParam ) {
						((List) servicePortViewer.getInput()).remove(selection.getData());
						update();
					} else	if( selection.getData() instanceof ServicePortInterfaceParam ) {
						((ServicePortInterfaceParam)selection.getData()).getParent().getServicePortInterfaces()
							.remove(selection.getData());
						update();
					}
				}
			});
			gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
			gridData.verticalAlignment = GridData.VERTICAL_ALIGN_CENTER;
			deleteButton.setLayoutData(gridData);
			Label label = toolkit.createLabel(client, "");
			gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
			gridData.verticalAlignment = GridData.VERTICAL_ALIGN_CENTER;
			label.setLayoutData(gridData);
			label.setMenu(menuMgr.createContextMenu(label));
			//
			final SectionPart sectionPart = new SectionPart(servicePortMasterBlockSection);
			managedForm.addPart(sectionPart);
			servicePortViewer.addSelectionChangedListener(new ISelectionChangedListener() {
				public void selectionChanged(SelectionChangedEvent event) {
					managedForm.fireSelectionChanged(sectionPart, event.getSelection());
					setButtonEnabled(event.getSelection());
				}
			});
			servicePortMasterBlockSection.setClient(client);
		}
		
		@Override
		protected void createToolBarActions(IManagedForm managedForm) {
		}

		@Override
		protected void registerPages(DetailsPart detailsPart) {
			detailsPart.registerPage(ServicePortParam.class, new ServicePortDetailsPage());
			detailsPart.registerPage(ServicePortInterfaceParam.class, new ServicePortInterfaceDetailsPage());
		}
	}

	//ServicePort Detail Block �N���X
	private class ServicePortDetailsPage implements IDetailsPage {
		private IManagedForm form;

		public void createContents(Composite parent) {
			parent.setLayout(new FillLayout());
			FormToolkit toolkit = form.getToolkit();
			Section section = toolkit.createSection(parent, Section.TITLE_BAR);
			section.setText(IMessageConstants.SERVICEPORT_PORT_TITLE);
			Composite client = toolkit.createComposite(section, SWT.NULL);
			client.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
			form.getToolkit().paintBordersFor(client);
			client.setLayout(new GridLayout(2, false));
			Label exp = toolkit.createLabel(client, IMessageConstants.SERVICEPORT_PORT_EXPL);
			GridData gd = new GridData();
			gd.horizontalSpan = 2;
			exp.setLayoutData(gd);
			MenuManager menuMgr = createContextMenu();
			client.setMenu(menuMgr.createContextMenu(client));
			//
			nameText = createLabelAndText(toolkit, client, IMessageConstants.SERVICEPORT_LBL_PORTNAME);
			positionCombo = createLabelAndCombo(toolkit, client,
					IMessageConstants.SERVICEPORT_LBL_POSITION, DataPortParam.COMBO_ITEM);
			
			createSrvPortDocumentSection(form, client);
			section.setClient(client);
		}

		private void createSrvPortDocumentSection(IManagedForm managedForm, Composite parent) {

			GridLayout gl;

			Section section = managedForm.getToolkit().createSection(
					parent,
					Section.TITLE_BAR | Section.EXPANDED | Section.TWISTIE);
			section.setText(IMessageConstants.SERVICEPORT_DOCUMENT_TITLE);
			GridData gridData = new GridData();
			gridData.grabExcessHorizontalSpace = true;
			gridData.horizontalAlignment = GridData.FILL;
			gridData.horizontalSpan = 2;
			section.setLayoutData(gridData);
			Composite composite = managedForm.getToolkit().createComposite(section,
					SWT.NULL);
			composite.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
			managedForm.getToolkit().paintBordersFor(composite);
			gl = new GridLayout(2, false);
			composite.setLayout(gl);
			section.setClient(composite);

			descriptionText = createLabelAndText(managedForm.getToolkit(), composite,
					IMessageConstants.SERVICEPORT_LBL_DESCRIPTION, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
			gridData = new GridData(GridData.FILL_HORIZONTAL);
			gridData.heightHint = 70;
			descriptionText.setLayoutData(gridData);
			ifoverviewText = createLabelAndText(managedForm.getToolkit(), composite,
					IMessageConstants.SERVICEPORT_LBL_IFDESCRIPTION, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
			ifoverviewText.setLayoutData(gridData);
		}

		public void commit(boolean onSave) {
		}

		public void dispose() {
		}

		public void initialize(IManagedForm form) {
			this.form = form;
		}

		public boolean isDirty() {
			return false;
		}

		public boolean isStale() {
			return false;
		}

		public void refresh() {
		}

		public void setFocus() {
		}

		public boolean setFormInput(Object input) {
			return false;
		}

		public void selectionChanged(IFormPart part, ISelection selection) {
			IStructuredSelection structuredSelection = (IStructuredSelection)selection;
			ServicePortParam servicePort = (ServicePortParam)structuredSelection.getFirstElement();
			nameText.setText(servicePort.getName());
			positionCombo.select(servicePort.getPositionByIndex());
			//
			descriptionText.setText(getDisplayDocText(servicePort.getDocDescription()));
			ifoverviewText.setText(getDisplayDocText(servicePort.getDocIfDescription()));
		}
	}
	
	//ServicePortInterface Detail Block �N���X
	private class ServicePortInterfaceDetailsPage implements IDetailsPage {
		private IManagedForm form;

		public void createContents(Composite parent) {
			parent.setLayout(new FillLayout());
			FormToolkit toolkit = form.getToolkit();
			Section section = toolkit.createSection(parent, Section.TITLE_BAR);
			section.setText(IMessageConstants.SERVICEPORT_IF_TITLE);
			Composite client = toolkit.createComposite(section, SWT.NULL);
			client.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
			form.getToolkit().paintBordersFor(client);
			client.setLayout(new GridLayout(3, false));
			MenuManager menuMgr = createContextMenu();
			client.setMenu(menuMgr.createContextMenu(client));
			//
			Label exp = toolkit.createLabel(client, IMessageConstants.SERVICEPORT_IF_EXPL);
			GridData gd = new GridData();
			gd.horizontalSpan = 3;
			exp.setLayoutData(gd);
			//
			interfaceNameText = createLabelAndText(toolkit, client, IMessageConstants.SERVICEPORT_LBL_IFNAME);
			toolkit.createLabel(client, "");
			directionCombo = createLabelAndCombo(toolkit, client,
					IMessageConstants.SERVICEPORT_LBL_IFDIRECTION, ServicePortInterfaceParam.COMBO_ITEM);
			toolkit.createLabel(client, "");
			instanceNameText = createLabelAndText(toolkit, client, IMessageConstants.SERVICEPORT_LBL_IFINSTNAME);
			toolkit.createLabel(client, "");
			varNameText = createLabelAndText(toolkit, client, IMessageConstants.SERVICEPORT_LBL_IFVARNAME);
			toolkit.createLabel(client, "");
			idlFileText = createLabelAndFile(toolkit, client, IDL_EXTENTION, IMessageConstants.SERVICEPORT_LBL_IDLFILE);
//			interfaceTypeText = createLabelAndText(toolkit, client, IMessageConstants.SERVICEPORT_LBL_IFTYPE);
			String[] defaultVal = new String[0];
			interfaceTypeCombo = createEditableCombo(toolkit, client, IMessageConstants.SERVICEPORT_LBL_IFTYPE, "", defaultVal);
			toolkit.createLabel(client, "");
			idlPathText = createLabelAndDirectory(toolkit, client, IMessageConstants.SERVICEPORT_LBL_IDLPATH);
			
			createSrvPortIfDocumentSection(form, client);
			section.setClient(client);
		}

		private void createSrvPortIfDocumentSection(IManagedForm managedForm, Composite parent) {

			GridLayout gl;

			Section section = managedForm.getToolkit().createSection(
					parent,
					Section.TITLE_BAR | Section.EXPANDED | Section.TWISTIE);
			section.setText("Documentation");
			GridData gridData = new GridData();
			gridData.grabExcessHorizontalSpace = true;
			gridData.horizontalAlignment = GridData.FILL;
			gridData.horizontalSpan = 2;
			section.setLayoutData(gridData);
			Composite composite = managedForm.getToolkit().createComposite(section,
					SWT.NULL);
			composite.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
			managedForm.getToolkit().paintBordersFor(composite);
			gl = new GridLayout(2, false);
			composite.setLayout(gl);
			section.setClient(composite);

			ifdetailText = createLabelAndText(managedForm.getToolkit(), composite,
					IMessageConstants.SERVICEPORT_LBL_DESCRIPTION, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
			gridData = new GridData(GridData.FILL_HORIZONTAL);
			gridData.heightHint = 70;
			ifdetailText.setLayoutData(gridData);
			ifargumentText = createLabelAndText(managedForm.getToolkit(), composite,
					IMessageConstants.SERVICEPORT_LBL_ARGUMENT, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
			ifargumentText.setLayoutData(gridData);
			ifreturnText = createLabelAndText(managedForm.getToolkit(), composite,
					IMessageConstants.SERVICEPORT_LBL_RETURN, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
			ifreturnText.setLayoutData(gridData);
			ifexceptionText = createLabelAndText(managedForm.getToolkit(), composite,
					IMessageConstants.SERVICEPORT_LBL_EXCEPTION, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
			ifexceptionText.setLayoutData(gridData);
			ifpreconditionText = createLabelAndText(managedForm.getToolkit(), composite,
					IMessageConstants.SERVICEPORT_LBL_PRE_CONDITION, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
			ifpreconditionText.setLayoutData(gridData);
			ifpostconditionText = createLabelAndText(managedForm.getToolkit(), composite,
					IMessageConstants.SERVICEPORT_LBL_POST_CONDITION, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
			ifpostconditionText.setLayoutData(gridData);
		}

		public void commit(boolean onSave) {
		}

		public void dispose() {
		}

		public void initialize(IManagedForm form) {
			this.form = form;
		}

		public boolean isDirty() {
			return false;
		}

		public boolean isStale() {
			return false;
		}

		public void refresh() {
		}

		public void setFocus() {
		}

		public boolean setFormInput(Object input) {
			return false;
		}

		public void selectionChanged(IFormPart part, ISelection selection) {
			IStructuredSelection structuredSelection = (IStructuredSelection)selection;
			ServicePortInterfaceParam serviceInterface = (ServicePortInterfaceParam)structuredSelection.getFirstElement();
			interfaceNameText.setText(serviceInterface.getName());
			directionCombo.select(serviceInterface.getIndex());
			instanceNameText.setText(serviceInterface.getInstanceName());
			varNameText.setText(serviceInterface.getVarName());
			idlFileText.setText(serviceInterface.getIdlFile());
//			interfaceTypeText.setText(serviceInterface.getInterfaceType());
			interfaceTypeCombo.setText(serviceInterface.getInterfaceType());
			idlPathText.setText(serviceInterface.getIdlSearchPath());
			//
			ifdetailText.setText(getDisplayDocText(serviceInterface.getDocDescription()));
			ifargumentText.setText(getDisplayDocText(serviceInterface.getDocArgument()));
			ifreturnText.setText(getDisplayDocText(serviceInterface.getDocReturn()));
			ifexceptionText.setText(getDisplayDocText(serviceInterface.getDocException()));
			ifpreconditionText.setText(getDisplayDocText(serviceInterface.getDocPreCondition()));
			ifpostconditionText.setText(getDisplayDocText(serviceInterface.getDocPostCondition()));
		}
	}

	private class ServiceParamContentProvider implements ITreeContentProvider {

		public Object[] getChildren(Object parentElement) {
			if(parentElement instanceof ServicePortParam) {
				ServicePortParam servicePort = (ServicePortParam)parentElement;
				return servicePort.getServicePortInterfaces().toArray();
			} else if(parentElement instanceof Collection) {
				Collection collection = (Collection)parentElement;
				return collection.toArray();
			}
			return null;
		}

		public Object getParent(Object element) {
			return null;
		}

		public boolean hasChildren(Object element) {
			return !(element instanceof ServicePortInterfaceParam);
		}

		public Object[] getElements(Object inputElement) {
			return getChildren(inputElement);
		}

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}
	
	private class ServiceParamLabelProvider extends LabelProvider {
		Image ImagePort, ImageReqIF, ImageProIF;
		
		public ServiceParamLabelProvider() {
			ImageDescriptor descPort;
			ImageDescriptor descReqIF;
			ImageDescriptor descProIF;
			try {
				URL url = RtcBuilderPlugin.getDefault().getBundle().getEntry("/");
				descPort = ImageDescriptor.createFromURL(new URL(url ,"icons/SrvPort.png"));
				descReqIF = ImageDescriptor.createFromURL(new URL(url ,"icons/ReqIF.png"));
				descProIF = ImageDescriptor.createFromURL(new URL(url ,"icons/ProIF.png"));
				ImageProIF = descProIF.createImage();
				ImagePort = descPort.createImage();
				ImageReqIF = descReqIF.createImage();
			} catch(MalformedURLException e) {
				descPort = ImageDescriptor.getMissingImageDescriptor();
			}
		}

		public String getText(Object element) {
			if( element instanceof ServicePortParam ) {
				ServicePortParam servicePort = (ServicePortParam)element;
				return servicePort.getName();
			} else if( element instanceof ServicePortInterfaceParam ) {
				ServicePortInterfaceParam serviceInterface = (ServicePortInterfaceParam)element;
				return serviceInterface.getName();
			}
			return super.getText(element);
		}
		
		public Image getImage(Object element) {
			if(element instanceof ServicePortParam) {
				return ImagePort;
			} else if(element instanceof ServicePortInterfaceParam) {
				ServicePortInterfaceParam serviceInterface = (ServicePortInterfaceParam)element;
				if(serviceInterface.getDirection().equals(ServicePortInterfaceParam.INTERFACE_DIRECTION_PROVIDED)) {
					return ImageProIF;
				}
				return ImageReqIF;
			}
			return super.getImage(element);
		}

		@Override
		public void dispose() {
			if( !servicePortViewer.getControl().isDisposed() ) {
				ImagePort.dispose();
				ImageReqIF.dispose();
				ImageProIF.dispose();
			}
			super.dispose();
		}
	}

	/**
	 * ServicePort�t�H�[�����̗v�f�̗L��/������ݒ肵�܂��B
	 * <ul>
	 * <li>serviceport.servicePort.table : ServicePort�Z�N�V�����̃e�[�u��</li>
	 * <li>serviceport.servicePort.addButton : ServicePort�Z�N�V������ Add�{�^��</li>
	 * </ul>
	 */
	public void setEnabledInfo(WidgetInfo widgetInfo, boolean enabled) {
		if (widgetInfo.matchSection("servicePort")) {
			if (servicePortViewer != null) {
				if (widgetInfo.matchWidget("table")) {
					setViewerEnabled(servicePortViewer, enabled);
				}
				if (widgetInfo.matchWidget("addButton")) {
					setButtonEnabled(addButton, enabled);
				}
			}
		}
	}

}
