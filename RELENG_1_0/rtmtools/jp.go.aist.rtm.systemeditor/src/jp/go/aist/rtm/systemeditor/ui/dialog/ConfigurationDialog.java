package jp.go.aist.rtm.systemeditor.ui.dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.views.configurationview.ConfigurationView;
import jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper.ComponentConfigurationWrapper;
import jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper.ConfigurationCondition;
import jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper.ConfigurationSetConfigurationWrapper;
import jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper.ConfigurationWidget;
import jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper.NamedValueConfigurationWrapper;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

/**
 * RT�R���|�[�l���g�̃R���t�B�O�f�[�^�ҏW�_�C�A���O
 *
 */
public class ConfigurationDialog extends TitleAreaDialog {

	private static final int NAME_WIDTH = 100;

	private static final String NORMAL_COLOR = "NORMAL_COLOR"; // @jve:decl-index=0: //$NON-NLS-1$
	private static final String MODIFY_COLOR = "MODIFY_COLOR"; // @jve:decl-index=0: //$NON-NLS-1$

	private static final String CANT_MODIFY_COLOR = "CANT_MODIFY_COLOR"; // @jve:decl-index=0: //$NON-NLS-1$

	private static final String ERROR_COLOR = "ERROR_COLOR"; //$NON-NLS-1$

	private static ColorRegistry colorRegistry = null;

	/** �ҏW�p�ɃR�s�[����ComponentConfiguration */
	ComponentConfigurationWrapper copiedConfig;

	/** ���݃^�u�ŊJ���Ă���ConfigurationSet */
	ConfigurationSetConfigurationWrapper selectedConfigSet;

	private TabFolder tabFolder;

	private boolean isValueModified;

	private boolean isApply;

	private ConfigurationView view;
	
	private boolean firstApply;

	private TabItem currentTabItem;
	
	private Label errorLabel;

	public ConfigurationDialog(ConfigurationView view) {
		super(view.getSite().getShell());
		setShellStyle(getShellStyle() | SWT.RESIZE);
		if (colorRegistry == null) {
			colorRegistry = new ColorRegistry();
			colorRegistry.put(NORMAL_COLOR, new RGB(255, 255, 255));
			colorRegistry.put(MODIFY_COLOR, new RGB(255, 192, 192));
			colorRegistry.put(CANT_MODIFY_COLOR, new RGB(198, 198, 198));
			colorRegistry.put(ERROR_COLOR, new RGB(255, 0, 0));
		}
		this.isValueModified = false;
		this.isApply = true;
		this.copiedConfig = view.getComponentConfig().clone();
		this.view = view;
		this.firstApply = true;
	}

	// �ҏW�_�C�A���O����apply�����s���� 2009.11.16
//	/** �����ۑ����w�肳��Ă�����true */
//	public boolean isApply() {
//		return this.isApply;
//	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite mainComposite = (Composite) super.createDialogArea(parent);

		GridLayout gl;
		gl = new GridLayout();
		mainComposite.setLayout(gl);
		mainComposite.setFont(parent.getFont());

		createTabFolder(mainComposite);

		// ����G���[�\���̈��ǉ� 2009.12.09
		errorLabel = new Label(mainComposite, SWT.SINGLE);
		GridData errorLabelGd = new GridData();
		errorLabelGd.horizontalAlignment = GridData.BEGINNING;
		errorLabelGd.grabExcessHorizontalSpace = true;
		errorLabelGd.widthHint = 200;
		errorLabelGd.heightHint = 50;
		errorLabel.setLayoutData(errorLabelGd);
		
		GridData gd = new GridData();
		gd.horizontalAlignment = GridData.END;
		gd.grabExcessHorizontalSpace = true;

		Button applyCheckBox = new Button(mainComposite, SWT.CHECK);
		applyCheckBox.setLayoutData(gd);
		applyCheckBox.setText(Messages.getString("ConfigurationDialog.3")); //$NON-NLS-1$
		applyCheckBox.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
			}

			public void widgetSelected(SelectionEvent e) {
				Button source = (Button) e.getSource();
				isApply = source.getSelection();
				if (!isApply) return;
				if (firstApply) firstApply = !view.confirmActiveApply();
				if (firstApply) {
					isApply = false;
					source.setSelection(false);
					return;
				}
				if (saveData()) {
					view.applyConfiguration(false);
					refreshTabItem();
				}
			}
		});
		applyCheckBox.setSelection(isApply);

		return mainComposite;
	}
	
	protected void refreshTabItem() {
		if (currentTabItem == null) return;
		if (selectedConfigSet == null) return;
		
		// �R���g���[����S����肩���Ȃ�	2009.12.04
//		currentTabItem.setControl(createConfigSetComposite(selectedConfigSet));
		
		// �E�B�W�F�b�g��ҏW���̏�Ԃɖ߂� 2009.12.04
		for (NamedValueConfigurationWrapper nv : selectedConfigSet.getNamedValueList()) {
			nv.loadWidgetValue();
		}
		
		// �C���ς̔w�i�F�̃R���g���[�������ɖ߂� 2009.12.04
		resetBackground(currentTabItem.getControl());
	}

	// �ċA�I��Control�̔w�i�F���C���ς��猳�ɖ߂� 2009.12.04
	private void resetBackground(Control content) {
		if (content instanceof Composite){
			for (Control child : ((Composite)content).getChildren()) {
				resetBackground(child);
			}
		}
		if (content.getBackground().equals(colorRegistry.get(MODIFY_COLOR))){
			content.setBackground(colorRegistry.get(NORMAL_COLOR));
		}
	}

	private void createTabFolder(Composite mainComposite) {
		if (this.copiedConfig == null) {
			return;
		}

		GridData gd;
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.verticalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.grabExcessVerticalSpace = true;

		tabFolder = new TabFolder(mainComposite, SWT.NONE);
		tabFolder.setLayoutData(gd);

		for (ConfigurationSetConfigurationWrapper cs : this.copiedConfig
				.getConfigurationSetList()) {
			TabItem item = new TabItem(tabFolder, SWT.NONE);
			item.setText(cs.getId());
		}
		if (tabFolder.getItemCount() > 0) {
			this.selectConfigSet(0);
		}

		tabFolder.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				selectConfigSet();
			}
		});
	}

	/** ConfigurationSet�P�ʂ�Composite���쐬 */
	private Control createConfigSetComposite(
			ConfigurationSetConfigurationWrapper configSet) {
		GridLayout gl;
		gl = new GridLayout(2, false);

		GridData gd;
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;

		// �X�N���[���ݒ�
		ScrolledComposite scroll = new ScrolledComposite(tabFolder, SWT.V_SCROLL);
		scroll.setLayout(new FillLayout());
		scroll.setExpandHorizontal(true);
		scroll.setExpandVertical(true);

		Composite configSetComposite = new Composite(scroll, SWT.NONE);
		configSetComposite.setLayout(gl);
		configSetComposite.setLayoutData(gd);

		scroll.setContent(configSetComposite);

		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;

		Label nameLabel = new Label(configSetComposite, SWT.NONE);
		nameLabel.setText(Messages.getString("ConfigurationDialog.4")); //$NON-NLS-1$
		Label nameText = new Label(configSetComposite, SWT.SINGLE);
		nameText.setLayoutData(gd);
		nameText.setText(configSet.getId());

		for (NamedValueConfigurationWrapper nv : configSet.getNamedValueList()) {
			createNamedValueComposite(configSetComposite, nv);
		}

		// �X�N���[���̏����T�C�Y
		scroll.setMinHeight(configSetComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y + 10);

		return scroll;
	}

	/** NamedValue�P�ʂ�Composite���쐬 */
	private void createNamedValueComposite(Composite parent,
			final NamedValueConfigurationWrapper namedValue) {
		GridLayout gl;
		gl = new GridLayout(2, false);
		gl.marginHeight = 1;

		GridData gd;
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.horizontalSpan = 2;
		gd.grabExcessHorizontalSpace = true;

		Composite namedValueComposite = new Composite(parent, SWT.NONE);
		namedValueComposite.setLayout(gl);
		namedValueComposite.setLayoutData(gd);

		gd = new GridData();
		gd.widthHint = NAME_WIDTH;

		Label keyLabel = new Label(namedValueComposite, SWT.NONE);
		keyLabel.setText(namedValue.getKey());
		if (namedValue.getKey().length() * 7 <= NAME_WIDTH) {
			keyLabel.setLayoutData(gd);
		}

		gl = new GridLayout(1, false);
		gl.marginHeight = 0;
		gl.marginWidth = 0;

		Composite valueComposite = new Composite(namedValueComposite, SWT.NONE);
		valueComposite.setLayout(gl);
		valueComposite.setLayoutData(createGridData());

		namedValue.loadWidgetValue();
		if (namedValue.widgetKeySet().size() > 0) {
			// �n�b�V���̏ꍇ
			for (String key : new TreeSet<String>(namedValue.widgetKeySet())) {
				ConfigurationWidget widget = namedValue.widget(key);
				createValueComposite(valueComposite, key, widget);
			}
		} else {
			// �z��A�P��
			for (int i = 0; i < namedValue.widgetSize(); i++) {
				ConfigurationWidget widget = namedValue.widget(i);
				createValueComposite(valueComposite, null, widget);
			}
		}
	}

	private GridData createGridData() {
		GridData gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		return gd;
	}
	
	private Composite createComposite(final Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		
		GridLayout gl = new GridLayout(1, false);
		gl.marginHeight = 0;
		gl.marginWidth = 0;
		composite.setLayout(gl);
		
		composite.setLayoutData(createGridData());
		
		return composite;
	}
	
	private Label createLabel(final Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		
		label.setLayoutData(createGridData());
		
		return label;
	}
	
	private Text createText(final Composite parent) {
		Text text = new Text(parent, SWT.SINGLE
				| SWT.BORDER);
		
		text.setLayoutData(createGridData());
		
		return text;
	}

	private Slider createSlider(final Composite parent) {
		Slider slider = new Slider(parent, SWT.BORDER);
		
		slider.setLayoutData(createGridData());
		
		return slider;
	}

	private Spinner createSpinner(final Composite parent) {
		Spinner spinner = new Spinner(parent, SWT.BORDER);
		
		spinner.setLayoutData(createGridData());
		
		return spinner;
	}

	private Group createGroup(final Composite parent) {
		Group group = new Group(parent, SWT.NONE);
		
		GridLayout gl = new GridLayout(3, false);
		gl.marginHeight = 1;
		group.setLayout(gl);
		
		group.setLayoutData(createGridData());
		
		return group;
	}

	private Button createButton(final Composite parent) {
		Button button = new Button(parent, SWT.RADIO);
		
		button.setLayoutData(createGridData());
		
		return button;
	}

	/** NamedValue�P�ʂ�Composite���쐬 */
	private void createValueComposite(final Composite parent, final String key,
			final ConfigurationWidget widget) {

		if (widget != null && widget.isSlider()) {
			// widget��ʂ�slider�̏ꍇ
			Composite valueComposite = createComposite(parent);

			createKeyLabel(key, valueComposite);

			final Text valueSliderText = createText(valueComposite);
			final Slider valueSlider = createSlider(valueComposite);

			valueSlider.setMinimum(0);
			valueSlider.setMaximum(widget.getSliderMaxStep() + 10);
			valueSlider.setIncrement(1);

			// slider�Atext�ɏ����l�ݒ�(���X�i�o�^�O)
			try {
				// �l�𐧖�͈͓��̃X�e�b�v�Ɋ��Z
				int step = widget.getCondition().getStepByValue(
						widget.getValue(), widget);
				valueSlider.setSelection(step);
			} catch (NumberFormatException e) {
				valueSlider.setSelection(0);
			}
			valueSliderText.setText(widget.getValue());
			if (widget.isValueModified()) {
				valueSliderText.setBackground(colorRegistry.get(MODIFY_COLOR));
			}

			valueSliderText.addModifyListener(createSliderModifyListner(widget, valueSliderText, valueSlider));
			valueSlider.addSelectionListener(createSliderSelectionListner(widget, valueSliderText, valueSlider));

		} else if (widget != null && widget.isSpinner()) {
			// widget��ʂ�spinner�̏ꍇ
			Composite valueComposite = createComposite(parent);

			createKeyLabel(key, valueComposite);

			final Spinner valueSpinner = createSpinner(valueComposite);

			if (widget.getCondition().getDigits() > 0) {
				// �����̏ꍇ�͌�����ݒ�
				valueSpinner.setDigits(widget.getCondition().getDigits());
			}
			valueSpinner.setMaximum(widget.getCondition().getMaxByInteger()
					.intValue());
			valueSpinner.setMinimum(widget.getCondition().getMinByInteger()
					.intValue());
			valueSpinner.setIncrement(widget.getSpinIncrement());

			// spinner�ɏ����l�ݒ�
			try {
				Double d = Double.valueOf(widget.getValue());
				Integer i = widget.getCondition().getIntegerByDigits(
						d.doubleValue());
				valueSpinner.setSelection(i.intValue());
			} catch (NumberFormatException e) {
				valueSpinner.setSelection(0);
			}
			if (widget.isValueModified()) {
				valueSpinner.setBackground(colorRegistry.get(MODIFY_COLOR));
			}

			valueSpinner.addModifyListener(createSpinnerModifyListner(widget, valueSpinner));

		} else if (widget != null && widget.isRadio()) {
			// widget��ʂ�radio�̏ꍇ
			Group valueRadioGroup = createGroup(parent);
			if (key != null) {
				// �n�b�V���L�[�̂���ꍇ
				valueRadioGroup.setText(key);
			}

			SelectionListener sl = createButtonSelectionListner(widget);

			// �񋓌^�����������I�����X�g�쐬
			for (String s : widget.getCondition().getEnumList()) {
				Button vb = createButton(valueRadioGroup);
				vb.setText(s);
				vb.addSelectionListener(sl);
				// �����l�ݒ�
				if (vb.getText().equals(widget.getValue())) {
					vb.setSelection(true);
				}
			}

		} else {
			createKeyLabel(key, parent);

			final Text valueText = createText(parent);
			valueText.setTextLimit(255);
			valueText.setEnabled(true);

			// text�ɏ����l�ݒ�
			valueText.setText(widget.getValue());
			if (widget.isValueModified()) {
				valueText.setBackground(colorRegistry.get(MODIFY_COLOR));
			}

			valueText.addModifyListener(createTextModifyListner(widget, valueText));
			valueText.addFocusListener(createFocusListner(valueText));
		}
	}

	private FocusListener createFocusListner(final Text valueText) {
		return new FocusListener(){
			public void focusGained(FocusEvent e) {
			}

			public void focusLost(FocusEvent e) {
				doModify(valueText);
			}};
	}

	private ModifyListener createTextModifyListner(
			final ConfigurationWidget widget, final Text valueText) {
		return new ModifyListener() {
			ConfigurationWidget wd = widget;

			public void modifyText(ModifyEvent e) {
				String value = valueText.getText();
				ConfigurationCondition condition = wd.getCondition();
				if (!condition.validate(value)) {
					valueText.setToolTipText(Messages.getString("ConfigurationDialog.12") + condition + Messages.getString("ConfigurationDialog.13")); //$NON-NLS-1$ //$NON-NLS-2$
					valueText.setBackground(colorRegistry.get(ERROR_COLOR));
				} else {
					valueText.setToolTipText(null);
					wd.setValue(value);
					if (wd.isValueModified()) {
						valueText.setBackground(colorRegistry.get(MODIFY_COLOR));
						isValueModified = true;
					} else {
						valueText.setBackground(colorRegistry.get(NORMAL_COLOR));
					}
				}
			}
		};
	}

	/** Apply��������Ă����瑦���X�V���� */
	private void doModify(Control control) {
		if (control != null)
			control.setBackground(colorRegistry.get(MODIFY_COLOR));
		isValueModified = true;
		
		if (!isApply) return;
		if (!saveData()) return;
		
		view.applyConfiguration(false);
		refreshTabItem();
	}
	
	private SelectionListener createButtonSelectionListner(
			final ConfigurationWidget widget) {
		return new SelectionListener() {
			ConfigurationWidget wd = widget;

			public void widgetDefaultSelected(SelectionEvent e) {
			}

			public void widgetSelected(SelectionEvent e) {
				Button b = (Button) e.widget;
				if (b.getSelection()) {
					String value = b.getText();
					wd.setValue(value);
					doModify(null);
				}
			}
		};
	}

	private ModifyListener createSpinnerModifyListner(
			final ConfigurationWidget widget, final Spinner valueSpinner) {
		return new ModifyListener() {
			ConfigurationWidget wd = widget;

			public void modifyText(ModifyEvent e) {
				String value = valueSpinner.getText();
				ConfigurationCondition condition = wd.getCondition();
				if (!condition.validate(value)) {
					valueSpinner.setToolTipText(Messages.getString("ConfigurationDialog.9") + condition + Messages.getString("ConfigurationDialog.10")); //$NON-NLS-1$ //$NON-NLS-2$
					// �ŏ�/�ő�l�𒴂���l���ۂ߂�
					wd.setValue(condition.adjustMinMaxValue(value));
					if (wd.isValueModified()) {
						doModify(valueSpinner);
					}
					Integer intMax = condition.getMaxByInteger();
					Integer intMin = condition.getMinByInteger();
					Integer intValue = condition.getIntegerByDigits(Double.valueOf(value));
					if (intValue < intMin) {
						valueSpinner.setSelection(intMin);
					} else if (intValue > intMax) {
						valueSpinner.setSelection(intMax);
					}
					valueSpinner.setBackground(colorRegistry.get(ERROR_COLOR));
				} else {
					valueSpinner.setToolTipText(null);
					wd.setValue(value);
					if (wd.isValueModified()) {
						doModify(valueSpinner);
					} else {
						valueSpinner.setBackground(colorRegistry
								.get(NORMAL_COLOR));
					}
				}
			}
		};
	}

	private SelectionAdapter createSliderSelectionListner(
			final ConfigurationWidget widget, final Text valueSliderText,
			final Slider valueSlider) {
		return new SelectionAdapter() {
			ConfigurationWidget wd = widget;

			public void widgetSelected(SelectionEvent e) {
				// �X�e�b�v���琧��͈͓��̒l�Ɋ��Z
				int step = valueSlider.getSelection();
				String value = wd.getCondition().getValueByStep(step, wd,
						valueSliderText.getText());
				if (wd.getCondition().validate(value)) {
					wd.setValue(value);
				}
				valueSliderText.setText(value);
			}
		};
	}

	private ModifyListener createSliderModifyListner(
			final ConfigurationWidget widget, final Text valueSliderText,
			final Slider valueSlider) {
		return new ModifyListener() {
			ConfigurationWidget wd = widget;

			public void modifyText(ModifyEvent e) {
				String value = valueSliderText.getText();
				ConfigurationCondition condition = wd.getCondition();
				try {
					// �l�𐧖�͈͓��̃X�e�b�v�Ɋ��Z
					int step = condition.getStepByValue(value, widget);
					valueSlider.setSelection(step);
				} catch (NumberFormatException ne) {
					valueSlider.setSelection(0);
				}
				if (!condition.validate(value)) {
					valueSliderText.setToolTipText(Messages.getString("ConfigurationDialog.6") + condition + Messages.getString("ConfigurationDialog.7")); //$NON-NLS-1$ //$NON-NLS-2$
					// �ŏ�/�ő�l�𒴂���l���ۂ߂�
					wd.setValue(condition.adjustMinMaxValue(value));
					if (wd.isValueModified()) {
						doModify(valueSliderText);
					}
					valueSliderText.setBackground(colorRegistry.get(ERROR_COLOR));
				} else {
					valueSliderText.setToolTipText(null);
					wd.setValue(value);
					if (wd.isValueModified()) {
						doModify(valueSliderText);
					} else {
						valueSliderText.setBackground(colorRegistry.get(NORMAL_COLOR));
					}
				}
			}
		};
	}

	private void createKeyLabel(final String key, Composite parent) {
		if (key != null) {
			// �n�b�V���L�[�̂���ꍇ
			final Label valueSliderLabel = createLabel(parent);
			valueSliderLabel.setText(key + ":"); //$NON-NLS-1$
		}
	}

	private void selectConfigSet(int index) {
		if (index < 0 || index >= tabFolder.getItemCount()) {
			return;
		}
		currentTabItem = tabFolder.getItem(index);
		// �I���^�u�ɑΉ�����ConfigurationSet������
		selectedConfigSet = null;
		for (ConfigurationSetConfigurationWrapper cs : copiedConfig
				.getConfigurationSetList()) {
			if (cs.getId().equals(currentTabItem.getText())) {
				selectedConfigSet = cs;
				break;
			}
		}
		if (selectedConfigSet != null) {
			if (currentTabItem.getControl() == null) {
				currentTabItem.setControl(createConfigSetComposite(selectedConfigSet));
			}
		}
	}

	/**
	 * �ҏW���ʂ����f�����֕ۑ�����
	 * 
	 * @return �ۑ��G���[�̏ꍇ��false
	 */
	private boolean saveData() {
		if (!this.isValueModified) {
			// �l�ɕύX���Ȃ��ꍇ
			return true;
		}
		// ��������`�F�b�N
		List<String> validateErrors = checkConstraints();
		if (validateErrors.size() > 0) {
			// �����K�p���ɂ̓G���[�_�C�A���O���o���Ȃ� 2009.
			if (isApply){
				errorLabel.setText(Messages.getString("ConfigurationDialog.21") //$NON-NLS-1$ //$NON-NLS-2$
						+ validateErrors.toString());
			} else {
				MessageDialog.openWarning(getShell(), Messages.getString("ConfigurationDialog.20"), Messages.getString("ConfigurationDialog.21") //$NON-NLS-1$ //$NON-NLS-2$
					+ validateErrors.toString());
			}
			return false;
		}
		errorLabel.setText("");
		// �ݒ�l�ۑ�
		List<ConfigurationSetConfigurationWrapper> origSetList = view.getComponentConfig()
				.getConfigurationSetList();
		List<ConfigurationSetConfigurationWrapper> copySetList = this.copiedConfig
				.getConfigurationSetList();
		doSave(origSetList, copySetList);
		isValueModified = false;
		return true;
	}

	private void doSave(List<ConfigurationSetConfigurationWrapper> origSetList,
			List<ConfigurationSetConfigurationWrapper> copySetList) {
		for (int i = 0; i < copySetList.size(); i++) {
			ConfigurationSetConfigurationWrapper origSet = origSetList.get(i);
			ConfigurationSetConfigurationWrapper copySet = copySetList.get(i);
			List<NamedValueConfigurationWrapper> origNvList = origSet
					.getNamedValueList();
			List<NamedValueConfigurationWrapper> copyNvList = copySet
					.getNamedValueList();
			for (int j = 0; j < copyNvList.size(); j++) {
				NamedValueConfigurationWrapper origNv = origNvList.get(j);
				NamedValueConfigurationWrapper copyNv = copyNvList.get(j);
				if (!copyNv.isLoadedWidgetValue()) {
					// �ҏW���łȂ���΃X�L�b�v
					continue;
				}
				// value�̕ύX������A��������̏ꍇ�̂ݕۑ�
				boolean modified = false;
				if (copyNv.widgetKeySet().size() > 0) {
					// �n�b�V���̏ꍇ
					for (String key : copyNv.widgetKeySet()) {
						if (copyNv.widget(key).isValueModified()) {
							modified = true;
							break;
						}
					}
				} else {
					// �z��A�P�̂̏ꍇ
					for (int k = 0; k < copyNv.widgetSize(); k++) {
						if (copyNv.widget(k).isValueModified()) {
							modified = true;
							break;
						}
					}
				}
				if (modified) {
					copyNv.saveWidgetValue();
					origNv.setValue(copyNv.getValue());
				}
			}
		}
	}

	private List<String> checkConstraints() {
		List<String> validateErrors = new ArrayList<String>();
		for (ConfigurationSetConfigurationWrapper cs : this.copiedConfig
				.getConfigurationSetList()) {
			for (NamedValueConfigurationWrapper nv : cs.getNamedValueList()) {
				if (!nv.isLoadedWidgetValue()) {
					// �ҏW���łȂ���΃X�L�b�v
					continue;
				}
				if (nv.widgetKeySet().size() > 0) {
					// �n�b�V���̏ꍇ
					for (String key : nv.widgetKeySet()) {
						ConfigurationWidget wd = nv.widget(key);
						String paramName = cs.getId() + "." + nv.getKey() + "["	+ key + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						validateParam(validateErrors, wd, paramName);
					}
				} else {
					// �z��A�P�̂̏ꍇ
					for (int i = 0; i < nv.widgetSize(); i++) {
						ConfigurationWidget wd = nv.widget(i);
						String paramName = cs.getId() + "." + nv.getKey(); //$NON-NLS-1$
						if (nv.widgetSize() > 1) {
							paramName += "[" + i + "]"; //$NON-NLS-1$ //$NON-NLS-2$
						}
						validateParam(validateErrors, wd, paramName);
					}
				}
			}
		}
		return validateErrors;
	}

	// Configuration�_�C�A���O�ŕۑ����̐�������`�F�b�N�ɂ��G���[���b�Z�[�W�ŁA  �p�����[�^���A��������A�G���[�ɂȂ����l��\���������@2008.12.18
	private void validateParam(List<String> validateErrors, ConfigurationWidget wd, String paramName) {
		ConfigurationCondition cc = wd.getCondition();
		String value = wd.getValue();
		if (!cc.validate(value)) {
			validateErrors.add(paramName + "(" + cc + ":" + value + ")\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(Messages.getString("ConfigurationDialog.25")); //$NON-NLS-1$
	}

	@Override
	protected void okPressed() {
		if (saveData()) {
			super.okPressed();
		}
	}

	/** �R���t�B�O�l���ĕ`�悷�� */
	private void selectConfigSet() {
		int index = tabFolder.getSelectionIndex();
		selectConfigSet((index == -1) ? 0 : index);
	}

}
