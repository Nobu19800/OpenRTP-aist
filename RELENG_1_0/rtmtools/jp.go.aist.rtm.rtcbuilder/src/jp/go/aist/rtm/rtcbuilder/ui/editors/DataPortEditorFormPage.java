package jp.go.aist.rtm.rtcbuilder.ui.editors;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jp.go.aist.rtm.rtcbuilder.RtcBuilderPlugin;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.ui.preference.ComponentPreferenceManager;
import jp.go.aist.rtm.rtcbuilder.util.ValidationUtil;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

/**
 * DataPort�y�[�W
 */
public class DataPortEditorFormPage extends AbstractEditorFormPage {

	private TableViewer inportTableViewer;
	private Button inportAddButton;
	private Button inportDeleteButton;
	//
	private TableViewer outportTableViewer;
	private Button outportAddButton;
	private Button outportDeleteButton;
	//
	private Text portNameText;
	private Combo typeCombo;
	private Text varNameText;
	private Combo positionCombo;
	private Text descriptionText;
	private Text typeText;
	private Text numberText;
	private Text semanticsText;
	private Text unitText;
	private Text occurrenceText;
	private Text operationText;
	//
	private DataPortParam preSelection;
	private DataPortParam selectParam;
	//
	private String defaultPortName;
	private String defaultPortType;
	private String defaultPortVarName;
	private String[] defaultTypeList;
	
	/**
	 * �R���X�g���N�^
	 * 
	 * @param editor
	 *            �e�̃G�f�B�^
	 */
	public DataPortEditorFormPage(RtcBuilderEditor editor) {
		super(editor, "id", IMessageConstants.DATAPORT_SECTION);
		//
		preSelection = null;
		updateDefaultValue();
	}

	private void updateDefaultValue() {
		IPreferenceStore store = RtcBuilderPlugin.getDefault().getPreferenceStore();
		defaultPortName = ComponentPreferenceManager.getInstance().getDataPort_Name();
		defaultPortType = store.getString(ComponentPreferenceManager.Generate_DataPort_Type);
		defaultPortVarName = store.getString(ComponentPreferenceManager.Generate_DataPort_VarName);
		//
		defaultTypeList = super.extractDataTypes();
	}

	/**
	 * {@inheritDoc}
	 */
	protected void createFormContent(IManagedForm managedForm) {
		ScrolledForm form = super.createBase(managedForm, IMessageConstants.DATAPORT_SECTION);
		FormToolkit toolkit = managedForm.getToolkit();
		//
		final Composite composite = createSectionBaseWithLabel(toolkit, form, 
				IMessageConstants.DATAPORT_TITLE, IMessageConstants.DATAPORT_EXPL, 4);
		inportTableViewer = createPortSection(toolkit, composite,
				IMessageConstants.REQUIRED + IMessageConstants.DATAPORT_TBLLBL_INPORTNAME, 0, true);
		createHintSection(toolkit, form);
		outportTableViewer = createPortSection(toolkit, composite,
				IMessageConstants.REQUIRED + IMessageConstants.DATAPORT_TBLLBL_OUTPORTNAME, 1, false);

		createDocumentSection(toolkit, form);
		//
		// ����E���y�[�W����ɂ��̃y�[�W���\�����ꂽ�ꍇ�A�����Ō���𔻒f����
		editor.setEnabledInfoByLang();

		load();
	}

	private void createHintSection(FormToolkit toolkit, ScrolledForm form) {
		Composite composite = createHintSectionBase(toolkit, form, 3);
		//
		createHintLabel(IMessageConstants.DATAPORT_HINT_DATAPORT_TITLE, IMessageConstants.DATAPORT_HINT_DATAPORT_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.DATAPORT_HINT_INPORT_TITLE, IMessageConstants.DATAPORT_HINT_INPORT_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.DATAPORT_HINT_OUTPORT_TITLE, IMessageConstants.DATAPORT_HINT_OUTPORT_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.DATAPORT_HINT_PORTNAME_TITLE, IMessageConstants.DATAPORT_HINT_PORTNAME_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.DATAPORT_HINT_DATATYPE_TITLE, IMessageConstants.DATAPORT_HINT_DATATYPE_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.DATAPORT_HINT_VARNAME_TITLE, IMessageConstants.DATAPORT_HINT_VARNAME_DESC, toolkit, composite);
		createHintLabel(IMessageConstants.DATAPORT_HINT_POSITION_TITLE, IMessageConstants.DATAPORT_HINT_POSITION_DESC, toolkit, composite);
		//
		createHintLabel(IMessageConstants.DATAPORT_HINT_DOCUMENT_TITLE, IMessageConstants.DATAPORT_HINT_DOCUMENT_DESC, toolkit, composite);
	}
	
	private void createDocumentSection(FormToolkit toolkit, ScrolledForm form) {

		Composite composite = createSectionBaseWithLabel(toolkit, form, 
				"Documentation", IMessageConstants.DATAPORT_DOCUMENT_EXPL, 2);
		//
		portNameText = createLabelAndText(toolkit, composite,
				IMessageConstants.DATAPORT_LBL_PORTNAME, SWT.BORDER);
		portNameText.setEditable(false);
		portNameText.setBackground(getSite().getShell().getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		typeCombo = createLabelAndCombo(toolkit, composite,
				IMessageConstants.REQUIRED + IMessageConstants.DATAPORT_TBLLBL_DATATYPE, defaultTypeList, SWT.COLOR_RED);
		varNameText = createLabelAndText(toolkit, composite, IMessageConstants.DATAPORT_TBLLBL_VARNAME);
		positionCombo = createLabelAndCombo(toolkit, composite, IMessageConstants.DATAPORT_TBLLBL_POSITION, DataPortParam.COMBO_ITEM);
		descriptionText = createLabelAndText(toolkit, composite,
				IMessageConstants.DATAPORT_LBL_DESCRIPTION, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = 50;
		descriptionText.setLayoutData(gridData);
		typeText = createLabelAndText(toolkit, composite,
				IMessageConstants.DATAPORT_LBL_PORTTYPE);
		numberText = createLabelAndText(toolkit, composite,	
				IMessageConstants.DATAPORT_LBL_DATANUM);
		semanticsText = createLabelAndText(toolkit, composite,
				IMessageConstants.DATAPORT_LBL_SEMANTICS, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
		semanticsText.setLayoutData(gridData);
		unitText = createLabelAndText(toolkit, composite,
				IMessageConstants.DATAPORT_LBL_UNIT);
		occurrenceText = createLabelAndText(toolkit, composite,
				IMessageConstants.DATAPORT_LBL_OCCUR, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
		occurrenceText.setLayoutData(gridData);
		operationText = createLabelAndText(toolkit, composite,
				IMessageConstants.DATAPORT_LBL_OPERAT, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
		operationText.setLayoutData(gridData);
	}

	private TableViewer createPortSection(FormToolkit toolkit, Composite parent,
			String columnLabel, final int initSel, boolean isInPort) {

		final TableViewer portParamTableViewer = createTableViewer(toolkit,	parent, 70);

		final TableViewerColumn col = super.createColumn(portParamTableViewer, columnLabel, 200);
		col.setEditingSupport(new DataPortEditingSuport(portParamTableViewer));
//		col.getColumn().setResizable(false);
		portParamTableViewer.setLabelProvider(new DataPortParamLabelProvider());
		//
		parent.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				Point size = portParamTableViewer.getControl().getSize();
				ScrollBar vBar = portParamTableViewer.getTable().getVerticalBar();
				col.getColumn().setWidth(size.x- vBar.getSize().x*2);
			}
		});
		//
		Composite buttonComposite = toolkit.createComposite(parent, SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.marginWidth = 1;
		buttonComposite.setLayout(gl);
		GridData gd = new GridData();
		gd.verticalAlignment = SWT.BEGINNING;
		gd.widthHint = 50;
		buttonComposite.setLayoutData(gd);

		Button addButton = toolkit.createButton(buttonComposite, "Add", SWT.PUSH);
		addButton.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateDefaultValue();
				DataPortParam selectParam = new DataPortParam(defaultPortName, defaultPortType, defaultPortVarName, initSel);
				((List) portParamTableViewer.getInput()).add(selectParam);
				portParamTableViewer.refresh();
				update();
				portParamTableViewer.setSelection(new StructuredSelection(selectParam), true);
			}
		});
		gd = new GridData(GridData.FILL_HORIZONTAL);
		addButton.setLayoutData(gd);
		//
		Button deleteButton = toolkit.createButton(buttonComposite, "Delete", SWT.PUSH);
		deleteButton.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectionIndex = portParamTableViewer.getTable()
						.getSelectionIndex();
				if (selectionIndex >= 0
						&& ((List) portParamTableViewer.getInput()).size() >= selectionIndex + 1) {
					((List) portParamTableViewer.getInput())
							.remove(selectionIndex);
					portParamTableViewer.refresh();
					preSelection = null;
					clearText();
					update();
				}
			}
		});
		gd = new GridData(GridData.FILL_HORIZONTAL);
		deleteButton.setLayoutData(gd);
		//
		portParamTableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				setDocumentContents();
				StructuredSelection selection = (StructuredSelection)event.getSelection();
				selectParam = (DataPortParam)selection.getFirstElement();
				if( selectParam != null ) {
					StringBuffer portName = new StringBuffer(selectParam.getName());
					if(event.getSource().equals(inportTableViewer)) {
						portName.append(" (InPort)");
					} else {
						portName.append(" (OutPort)");
					}
					portNameText.setText(portName.toString());
					typeCombo.setText(selectParam.getType());
					varNameText.setText(getDisplayDocText(selectParam.getVarName()));
					positionCombo.setText(selectParam.getPosition());
					descriptionText.setText(getDisplayDocText(selectParam.getDocDescription()));
					typeText.setText(getDisplayDocText(selectParam.getDocType()));
					numberText.setText(getDisplayDocText(selectParam.getDocNum()));
					semanticsText.setText(getDisplayDocText(selectParam.getDocSemantics()));
					unitText.setText(getDisplayDocText(selectParam.getDocUnit()));
					occurrenceText.setText(getDisplayDocText(selectParam.getDocOccurrence()));
					operationText.setText(getDisplayDocText(selectParam.getDocOperation()));
					preSelection = selectParam;
				}
			}
		});

		if( isInPort ) {
			inportAddButton = addButton;
			inportDeleteButton = deleteButton;
		} else {
			outportAddButton = addButton;
			outportDeleteButton = deleteButton;
		}

		return portParamTableViewer;
	}

	public void update() {
		if (selectParam != null) {
			selectParam.setType(typeCombo.getText());
			selectParam.setVarName(getDocText(varNameText.getText()));
			selectParam.setPosition(positionCombo.getText());
			selectParam.setDocDescription(getDocText(descriptionText.getText()));
			selectParam.setDocType(getDocText(typeText.getText()));
			selectParam.setDocNum(getDocText(numberText.getText()));
			selectParam.setDocSemantics(getDocText(semanticsText.getText()));
			selectParam.setDocUnit(getDocText(unitText.getText()));
			selectParam.setDocOccurrence(getDocText(occurrenceText.getText()));
			selectParam.setDocOperation(getDocText(operationText.getText()));
		}
		//
		editor.updateEMFDataPorts(
				editor.getRtcParam().getInports(), editor.getRtcParam().getOutports(),
				editor.getRtcParam().getServicePorts());
		editor.updateDirty();
	}

	public void updateForOutput() {
		update();
		setDocumentContents();
	}

	private void setDocumentContents() {
		if( preSelection != null ) {
			preSelection.setType(typeCombo.getText());
			preSelection.setVarName(getDocText(varNameText.getText()));
			preSelection.setPosition(positionCombo.getText());
			//
			preSelection.setDocDescription(getDocText(descriptionText.getText()));
			preSelection.setDocType(getDocText(typeText.getText()));
			preSelection.setDocNum(getDocText(numberText.getText()));
			preSelection.setDocSemantics(getDocText(semanticsText.getText()));
			preSelection.setDocUnit(getDocText(unitText.getText()));
			preSelection.setDocOccurrence(getDocText(occurrenceText.getText()));
			preSelection.setDocOperation(getDocText(operationText.getText()));
		}
	}
	
	private void clearText() {
		portNameText.setText("");
		typeCombo.select(0);
		varNameText.setText("");
		positionCombo.select(0);
		descriptionText.setText("");
		typeText.setText("");
		numberText.setText("");
		semanticsText.setText("");
		unitText.setText("");
		occurrenceText.setText("");
		operationText.setText("");
	}

	/**
	 * �f�[�^�����[�h����
	 */
	public void load() {
		if (inportTableViewer == null) return;
		RtcParam rtcParam = editor.getRtcParam();
		outportTableViewer.setInput(rtcParam.getOutports());
		inportTableViewer.setInput(rtcParam.getInports());
		//
		StructuredSelection selection = (StructuredSelection) outportTableViewer
				.getSelection();
		DataPortParam outParam = (DataPortParam) selection.getFirstElement();
		selection = (StructuredSelection) inportTableViewer.getSelection();
		DataPortParam inParam = (DataPortParam) selection.getFirstElement();
		if (outParam == null && inParam == null) clearText();
		//
		editor.updateEMFDataPorts(
				editor.getRtcParam().getInports(), editor.getRtcParam().getOutports(),
				editor.getRtcParam().getServicePorts());
	}

	public String validateParam() {
		String result = null;

		RtcParam rtcParam = editor.getRtcParam();
		Set<String> checkSet = new HashSet<String>(); 
		
		for(DataPortParam dataport : rtcParam.getInports()) {
			result = checkDataPort(dataport, checkSet);
			if( result != null) return result;
		}
		//
		for(DataPortParam dataport : rtcParam.getOutports()) {
			result = checkDataPort(dataport, checkSet);
			if( result != null) return result;
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private String checkDataPort(DataPortParam dataport, Set checkSet) {
		String result = ValidationUtil.validateDataPort(dataport);
		if( result!=null ) return result;
		//�d��
		if( checkSet.contains(dataport.getName()) ) {
			result = IMessageConstants.DATAPORT_VALIDATE_DUPLICATE;
			return result;
		}

		checkSet.add(dataport.getName());
		return null;
	}

	private class DataPortParamLabelProvider extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			if (element instanceof DataPortParam == false) return null;
			DataPortParam dataPortParam = (DataPortParam) element;
			return dataPortParam.getName();
		}
	}

	private class DataPortEditingSuport extends EditingSupport {
		private CellEditor editor;

		public DataPortEditingSuport(ColumnViewer viewer) {
			super(viewer);
			editor = new TextCellEditor(((TableViewer) viewer).getTable());
		}

		@Override
		protected boolean canEdit(Object element) {
			return true;
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			return editor;
		}

		@Override
		protected Object getValue(Object element) {
			if (element instanceof DataPortParam == false) return null;
			DataPortParam dataPortParam = (DataPortParam) element;
			return dataPortParam.getName();
		}

		@Override
		protected void setValue(Object element, Object value) {
			if (element instanceof DataPortParam == false) return;
			DataPortParam dataPortParam = (DataPortParam) element;

			dataPortParam.setName((String) value);
			StringBuffer portName = new StringBuffer(dataPortParam.getName());
			if( this.getViewer()==inportTableViewer ) {
				portName.append(" (InPort)");
			} else {
				portName.append(" (OutPort)");
			}
			portNameText.setText(portName.toString());

			getViewer().update(element, null);
			update();
		}
	}

	/**
	 * DataPort�t�H�[�����̗v�f�̗L��/������ݒ肵�܂��B
	 * <ul>
	 * <li>dataport.inPort.table : InPort�Z�N�V�����̃e�[�u��</li>
	 * <li>dataport.inPort.addButton : InPort�Z�N�V������ Add�{�^��</li>
	 * <li>dataport.inPort.deleteButton : InPort�Z�N�V������ Delete�{�^��</li>
	 * <li>dataport.outPort.table : OutPort�Z�N�V�����̃e�[�u��</li>
	 * <li>dataport.outPort.addButton : OutPort�Z�N�V������ Add�{�^��</li>
	 * <li>dataport.outPort.deleteButton : OutPort�Z�N�V������ Delete�{�^��</li>
	 * </ul>
	 */
	public void setEnabledInfo(WidgetInfo widgetInfo, boolean enabled) {
		if (widgetInfo.matchSection("inPort")) {
			if (inportTableViewer != null) {
				if (widgetInfo.matchWidget("table"))        setViewerEnabled(inportTableViewer, enabled);
				if (widgetInfo.matchWidget("addButton"))    setButtonEnabled(inportAddButton, enabled);
				if (widgetInfo.matchWidget("deleteButton")) setButtonEnabled(inportDeleteButton, enabled);
			}
		}
		if (widgetInfo.matchSection("outPort")) {
			if (outportTableViewer != null) {
				if (widgetInfo.matchWidget("table"))        setViewerEnabled(outportTableViewer, enabled);
				if (widgetInfo.matchWidget("addButton"))    setButtonEnabled(outportAddButton, enabled);
				if (widgetInfo.matchWidget("deleteButton")) setButtonEnabled(outportDeleteButton, enabled);
			}
		}
	}
}
