package jp.go.aist.rtm.systemeditor.ui.dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import jp.go.aist.rtm.systemeditor.nl.Messages;
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

	private static final String MODIFY_COLOR = "MODIFY_COLOR"; // @jve:decl-index=0: //$NON-NLS-1$

	private static final String CANT_MODIFY_COLOR = "CANT_MODIFY_COLOR"; // @jve:decl-index=0: //$NON-NLS-1$

	private static final String ERROR_COLOR = "ERROR_COLOR"; //$NON-NLS-1$

	private static ColorRegistry colorRegistry = null;

	/** �I���W�i����ComponentConfiguration */
	ComponentConfigurationWrapper componentConfig;

	/** �ҏW�p�ɃR�s�[����ComponentConfiguration */
	ComponentConfigurationWrapper copiedConfig;

	/** ���݃^�u�ŊJ���Ă���ConfigurationSet */
	ConfigurationSetConfigurationWrapper selectedConfigSet;

	private TabFolder tabFolder;

	private boolean isValueModified;

	private boolean isApply;

	public ConfigurationDialog(Shell parentShell,
			ComponentConfigurationWrapper componentConfig) {
		super(parentShell);
		setShellStyle(getShellStyle() | SWT.RESIZE);
		if (colorRegistry == null) {
			colorRegistry = new ColorRegistry();
			colorRegistry.put(MODIFY_COLOR, new RGB(255, 192, 192));
			colorRegistry.put(CANT_MODIFY_COLOR, new RGB(198, 198, 198));
			colorRegistry.put(ERROR_COLOR, new RGB(255, 0, 0));
		}
		this.isValueModified = false;
		this.isApply = false;
		this.componentConfig = componentConfig;
		this.copiedConfig = this.componentConfig.clone();
	}

	/** �����ۑ����w�肳��Ă�����true */
	public boolean isApply() {
		return this.isApply;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite mainComposite = (Composite) super.createDialogArea(parent);

		GridLayout gl;
		gl = new GridLayout();
		mainComposite.setLayout(gl);
		mainComposite.setFont(parent.getFont());

		createTabFolder(mainComposite);

		GridData gd;
		gd = new GridData();
		gd.horizontalAlignment = GridData.END;
		gd.grabExcessHorizontalSpace = true;

		Button applyCheckBox = new Button(mainComposite, SWT.CHECK);
		applyCheckBox.setLayoutData(gd);
		applyCheckBox.setText(Messages.getString("ConfigurationDialog.3")); //$NON-NLS-1$
		applyCheckBox.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
			}

			public void widgetSelected(SelectionEvent e) {
				isApply = true;
			}
		});

		return mainComposite;
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
				int index = tabFolder.getSelectionIndex();
				selectConfigSet((index == -1) ? 0 : index);
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

		Composite namedValueComposite = new Composite(parent, SWT.BORDER);
		namedValueComposite.setLayout(gl);
		namedValueComposite.setLayoutData(gd);

		gd = new GridData();
		gd.widthHint = NAME_WIDTH;

		Label keyLabel = new Label(namedValueComposite, SWT.NONE);
		keyLabel.setLayoutData(gd);
		keyLabel.setText(namedValue.getKey());

		gl = new GridLayout(1, false);
		gl.marginHeight = 0;
		gl.marginWidth = 0;

		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;

		Composite valueComposite = new Composite(namedValueComposite, SWT.NONE);
		valueComposite.setLayout(gl);
		valueComposite.setLayoutData(gd);

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

	/** NamedValue�P�ʂ�Composite���쐬 */
	private void createValueComposite(Composite parent, final String key,
			final ConfigurationWidget widget) {

		GridLayout gl;
		GridData gd;

		if (widget != null && widget.isSlider()) {
			// widget��ʂ�slider�̏ꍇ
			gl = new GridLayout(1, false);
			gl.marginHeight = 0;
			gl.marginWidth = 0;
			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.grabExcessHorizontalSpace = true;

			Composite valueComposite = new Composite(parent, SWT.NONE);
			valueComposite.setLayout(gl);
			valueComposite.setLayoutData(gd);

			if (key != null) {
				// �n�b�V���L�[�̂���ꍇ
				gd = new GridData();
				gd.horizontalAlignment = GridData.FILL;
				gd.grabExcessHorizontalSpace = true;
				final Label valueSliderLabel = new Label(valueComposite,
						SWT.NONE);
				valueSliderLabel.setLayoutData(gd);
				valueSliderLabel.setText(key + ":"); //$NON-NLS-1$
			}

			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.grabExcessHorizontalSpace = true;

			final Text valueSliderText = new Text(valueComposite, SWT.SINGLE
					| SWT.BORDER);
			final Slider valueSlider = new Slider(valueComposite, SWT.BORDER);

			// slider�Atext�ɏ����l�ݒ�(���X�i�o�^�O)
			try {
				// �l�𐧖�͈͓��̃X�e�b�v�Ɋ��Z
				int step = widget.getCondition().getStepByValue(
						widget.getValue(), widget.getSliderMaxStep());
				valueSlider.setSelection(step);
			} catch (NumberFormatException e) {
				valueSlider.setSelection(0);
			}
			valueSliderText.setText(widget.getValue());
			if (widget.isValueModified()) {
				valueSliderText.setBackground(colorRegistry.get(MODIFY_COLOR));
			}

			valueSliderText.setLayoutData(gd);
			valueSliderText.addModifyListener(new ModifyListener() {
				ConfigurationWidget wd = widget;

				public void modifyText(ModifyEvent e) {
					String value = valueSliderText.getText();
					ConfigurationCondition condition = wd.getCondition();
					if (!condition.validate(value)) {
						valueSliderText.setToolTipText(Messages.getString("ConfigurationDialog.6") + condition + Messages.getString("ConfigurationDialog.7")); //$NON-NLS-1$ //$NON-NLS-2$
						valueSliderText.setBackground(colorRegistry
								.get(ERROR_COLOR));
					} else {
						valueSliderText.setToolTipText(null);
						wd.setValue(value);
						try {
							// �l�𐧖�͈͓��̃X�e�b�v�Ɋ��Z
							int step = condition.getStepByValue(value,
									widget.getSliderMaxStep());
							valueSlider.setSelection(step);
						} catch (NumberFormatException ne) {
							valueSlider.setSelection(0);
						}
						if (wd.isValueModified()) {
							valueSliderText.setBackground(colorRegistry
									.get(MODIFY_COLOR));
							isValueModified = true;
						}
					}
				}
			});

			valueSlider.setLayoutData(gd);
			valueSlider.setMinimum(0);
			valueSlider.setMaximum(widget.getSliderMaxStep() + 10);
			valueSlider.setIncrement(1);
			valueSlider.addSelectionListener(new SelectionAdapter() {
				ConfigurationWidget wd = widget;

				public void widgetSelected(SelectionEvent e) {
					// �X�e�b�v���琧��͈͓��̒l�Ɋ��Z
					int step = valueSlider.getSelection();
					String value = wd.getCondition().getValueByStep(step,
							wd.getSliderMaxStep());
					if (wd.getCondition().validate(value)) {
						wd.setValue(value);
					}
					valueSliderText.setText(value);
				}
			});

		} else if (widget != null && widget.isSpinner()) {
			// widget��ʂ�spinner�̏ꍇ
			gl = new GridLayout(1, false);
			gl.marginHeight = 0;
			gl.marginWidth = 0;
			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.grabExcessHorizontalSpace = true;

			Composite valueComposite = new Composite(parent, SWT.NONE);
			valueComposite.setLayout(gl);
			valueComposite.setLayoutData(gd);

			if (key != null) {
				// �n�b�V���L�[�̂���ꍇ
				gd = new GridData();
				gd.horizontalAlignment = GridData.FILL;
				gd.grabExcessHorizontalSpace = true;
				final Label valueSpinnerLabel = new Label(valueComposite,
						SWT.NONE);
				valueSpinnerLabel.setLayoutData(gd);
				valueSpinnerLabel.setText(key + ":"); //$NON-NLS-1$
			}

			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.grabExcessHorizontalSpace = true;

			final Spinner valueSpinner = new Spinner(valueComposite, SWT.BORDER);

			valueSpinner.setLayoutData(gd);
			if (widget.getCondition().getDigits() > 0) {
				// �����̏ꍇ�͌�����ݒ�
				valueSpinner.setDigits(widget.getCondition().getDigits());
			}
			valueSpinner.setMaximum(widget.getCondition().getMaxByInteger()
					.intValue());
			valueSpinner.setMinimum(widget.getCondition().getMinByInteger()
					.intValue());

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

			valueSpinner.addSelectionListener(new SelectionAdapter() {
				ConfigurationWidget wd = widget;

				public void widgetSelected(SelectionEvent e) {
					int i = valueSpinner.getSelection();
					ConfigurationCondition condition = wd.getCondition();
					String value = String.valueOf(condition.getDecimalByDigits(i));
					valueSpinner.setSelection(i);
					if (!condition.validate(value)) {
						valueSpinner.setToolTipText(Messages.getString("ConfigurationDialog.9") + condition + Messages.getString("ConfigurationDialog.10")); //$NON-NLS-1$ //$NON-NLS-2$
						valueSpinner.setBackground(colorRegistry
								.get(ERROR_COLOR));
					} else {
						valueSpinner.setToolTipText(null);
						wd.setValue(value);
						if (wd.isValueModified()) {
							valueSpinner.setBackground(colorRegistry
									.get(MODIFY_COLOR));
							isValueModified = true;
						}
					}
				}
			});

		} else if (widget != null && widget.isRadio()) {
			// widget��ʂ�radio�̏ꍇ
			gl = new GridLayout(3, false);
			gl.marginHeight = 1;
			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.grabExcessHorizontalSpace = true;

			Group valueRadioGroup = new Group(parent, SWT.NONE);
			valueRadioGroup.setLayout(gl);
			valueRadioGroup.setLayoutData(gd);

			if (key != null) {
				// �n�b�V���L�[�̂���ꍇ
				valueRadioGroup.setText(key);
			}

			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.grabExcessHorizontalSpace = true;

			SelectionListener sl = new SelectionListener() {
				ConfigurationWidget wd = widget;

				public void widgetDefaultSelected(SelectionEvent e) {
				}

				public void widgetSelected(SelectionEvent e) {
					Button b = (Button) e.widget;
					if (b.getSelection()) {
						String value = b.getText();
						wd.setValue(value);
						isValueModified = true;
					}
				}
			};

			// �񋓌^�����������I�����X�g�쐬
			for (String s : widget.getCondition().getEnumList()) {
				Button vb = new Button(valueRadioGroup, SWT.RADIO);
				vb.setText(s);
				vb.setLayoutData(gd);
				vb.addSelectionListener(sl);
				// �����l�ݒ�
				if (vb.getText().equals(widget.getValue())) {
					vb.setSelection(true);
				}
			}

		} else {
			// �f�t�H���g�̓e�L�X�g�{�b�N�X
			if (key != null) {
				// �n�b�V���L�[�̂���ꍇ
				gd = new GridData();
				gd.horizontalAlignment = GridData.FILL;
				gd.grabExcessHorizontalSpace = true;
				final Label valueSpinnerLabel = new Label(parent, SWT.NONE);
				valueSpinnerLabel.setLayoutData(gd);
				valueSpinnerLabel.setText(key + ":"); //$NON-NLS-1$
			}

			gd = new GridData();
			gd.horizontalAlignment = GridData.FILL;
			gd.grabExcessHorizontalSpace = true;

			final Text valueText = new Text(parent, SWT.SINGLE | SWT.BORDER);
			valueText.setLayoutData(gd);
			valueText.setTextLimit(255);
			valueText.setEnabled(true);

			// text�ɏ����l�ݒ�
			valueText.setText(widget.getValue());
			if (widget.isValueModified()) {
				valueText.setBackground(colorRegistry.get(MODIFY_COLOR));
			}

			valueText.addModifyListener(new ModifyListener() {
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
							valueText.setBackground(colorRegistry
									.get(MODIFY_COLOR));
							isValueModified = true;
						}
					}
				}
			});
		}
	}

	private void selectConfigSet(int index) {
		if (index < 0 || index >= tabFolder.getItemCount()) {
			return;
		}
		TabItem item = tabFolder.getItem(index);
		// �I���^�u�ɑΉ�����ConfigurationSet������
		selectedConfigSet = null;
		for (ConfigurationSetConfigurationWrapper cs : copiedConfig
				.getConfigurationSetList()) {
			if (cs.getId().equals(item.getText())) {
				selectedConfigSet = cs;
				break;
			}
		}
		if (selectedConfigSet != null) {
			if (item.getControl() == null) {
				item.setControl(createConfigSetComposite(selectedConfigSet));
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
		if (validateErrors.size() > 0) {
			MessageDialog.openWarning(getShell(), Messages.getString("ConfigurationDialog.20"), Messages.getString("ConfigurationDialog.21") //$NON-NLS-1$ //$NON-NLS-2$
					+ validateErrors.toString());
			return false;
		}
		// �ݒ�l�ۑ�
		List<ConfigurationSetConfigurationWrapper> origSetList = this.componentConfig
				.getConfigurationSetList();
		List<ConfigurationSetConfigurationWrapper> copySetList = this.copiedConfig
				.getConfigurationSetList();
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
		return true;
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

}
