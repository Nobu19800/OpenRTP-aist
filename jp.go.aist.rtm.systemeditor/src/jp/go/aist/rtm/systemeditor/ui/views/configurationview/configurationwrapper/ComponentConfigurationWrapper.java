package jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;

/**
 * �R���t�B�O�Z�b�g�̕ҏW�p�f�[�^
 *
 */
public class ComponentConfigurationWrapper {

	@SuppressWarnings("unchecked")
	public static ComponentConfigurationWrapper create(Component target) {
		ComponentConfigurationWrapper result = new ComponentConfigurationWrapper();
		List<ConfigurationSetConfigurationWrapper> configurationSetList = result
				.getConfigurationSetList();

		// �p�����[�^���|widget���
		Map<String, String> widgets = new HashMap<String, String>();
		// configurationSet���|��������}�b�v(�p�����[�^���|����)
		Map<String, Map<String, String>> conditions = new HashMap<String, Map<String, String>>();

		for (Object o : target.getConfigurationSets()) {
			ConfigurationSet cs = (ConfigurationSet) o;
			if (cs.getId().equals("__widget__")) {
				for (Object o2 : cs.getConfigurationData()) {
					NameValue nv = (NameValue) o2;
					widgets.put(nv.getName(), nv.getValueAsString());
				}
			} else if (cs.getId().startsWith("_")) {
				String key = cs.getId().substring(1);
				Map<String, String> kc = new HashMap<String, String>();
				for (Object o2 : cs.getConfigurationData()) {
					NameValue nv = (NameValue) o2;
					kc.put(nv.getName(), nv.getValueAsString());
				}
				conditions.put(key, kc);
			}
		}

		result.widgetSetting = widgets;
		result.conditionSetting = conditions;

		for (Iterator iter = target.getConfigurationSets().iterator(); iter
				.hasNext();) {
			ConfigurationSet configurationSet = (ConfigurationSet) iter.next();

			if (configurationSet.getId().startsWith("_"))
				continue;

			ConfigurationSetConfigurationWrapper configurationSetConfigurationWrapper = new ConfigurationSetConfigurationWrapper(
					configurationSet, configurationSet.getId());

			List<NamedValueConfigurationWrapper> namedValueList = configurationSetConfigurationWrapper
					.getNamedValueList();

			// configurationSet�ɑΉ����鐧�����(�Ȃ����default���g�p)
			Map<String, String> conds = conditions.get(configurationSet.getId());
			if (conds == null) 
				conds = conditions.get("_constraints__");

			for (Iterator iterator = configurationSet.getConfigurationData()
					.iterator(); iterator.hasNext();) {

				NameValue nameValue = (NameValue) iterator.next();
				NamedValueConfigurationWrapper namedValueConfigurationWrapper = new NamedValueConfigurationWrapper(
						nameValue.getName(), nameValue.getValue(), nameValue.getTypeName());

				if (conds != null) {
					String type = widgets.get(nameValue.getName());
					String cond = conds.get(nameValue.getName());
					namedValueConfigurationWrapper.setWidgetAndCondition(type, cond);
				}

				namedValueList.add(namedValueConfigurationWrapper);
				Collections.sort(namedValueList);
			}

			if (target.getActiveConfigurationSet() != null
					&& target.getActiveConfigurationSet().getId().equals(
							configurationSet.getId())) {
				result.setActiveConfigSet(configurationSetConfigurationWrapper);
			}

			configurationSetList.add(configurationSetConfigurationWrapper);
			Collections.sort(configurationSetList);
		}
		return result;
	}


	private List<ConfigurationSetConfigurationWrapper> configurationSetList = new ArrayList<ConfigurationSetConfigurationWrapper>();

	private ConfigurationSetConfigurationWrapper activeConfigurationSet;

	private Map<String, String> widgetSetting;

	private Map<String, Map<String, String>> conditionSetting;

	public List<ConfigurationSetConfigurationWrapper> getConfigurationSetList() {
		return configurationSetList;
	}

	public void setActiveConfigSet(
			ConfigurationSetConfigurationWrapper configurationSet) {
		this.activeConfigurationSet = configurationSet;
	}

	public ConfigurationSetConfigurationWrapper getActiveConfigSet() {
		return activeConfigurationSet;
	}

	public void addConfigurationSet(
			ConfigurationSetConfigurationWrapper configurationSet) {
		configurationSetList.add(configurationSet);
	}

	public void removeConfigurationSet(
			ConfigurationSetConfigurationWrapper configurationSet) {
		configurationSetList.remove(configurationSet);
	}

	/**
	 * �p�����[�^����widget�ݒ蕶����̃}�b�v��Ԃ��܂��B
	 */
	public Map<String, String> getWidgetSetting() {
		if (this.widgetSetting == null) {
			return new HashMap<String, String>();
		}
		return this.widgetSetting;
	}

	/**
	 * �f�t�H���g�̃p�����[�^���Ɛ�������ݒ蕶����̃}�b�v��Ԃ��܂��B
	 */
	public Map<String, String> getDefaultConditionSetting() {
		return this.getConditionSetting("default");
	}

	/**
	 * configSet�����w�肵�āA�p�����[�^���Ɛ�������ݒ蕶����̃}�b�v��Ԃ��܂��B
	 */
	public Map<String, String> getConditionSetting(String key) {
		Map<String, String> result = this.conditionSetting.get(key);
		if (result == null) {
			return new HashMap<String, String>();
		}
		return result;
	}

	/**
	 * �f�t�H���g��NameValue�̖��O���X�g���擾���܂��B
	 */
	public Set<String> getDefaultNameSet() {
		Set<String> result = new HashSet<String>();
		if (this.widgetSetting != null) {
			result.addAll(this.widgetSetting.keySet());
		}
		if (this.conditionSetting != null) {
			Map<String, String> dc = this.conditionSetting.get("default");
			if (dc != null) {
				result.addAll(dc.keySet());
			}
		}
		return result;
	}

	@Override
	public ComponentConfigurationWrapper clone() {
		ComponentConfigurationWrapper result = new ComponentConfigurationWrapper();
		List<ConfigurationSetConfigurationWrapper> configurationSetList = result
				.getConfigurationSetList();
		for (ConfigurationSetConfigurationWrapper cs : this.configurationSetList) {
			configurationSetList.add(cs.clone());
		}
		if (this.widgetSetting != null) {
			result.widgetSetting = new HashMap<String, String>();
			for (String name : this.widgetSetting.keySet()) {
				result.widgetSetting.put(name, this.widgetSetting.get(name));
			}
		}
		if (this.conditionSetting != null) {
			result.conditionSetting = new HashMap<String, Map<String, String>>();
			for (String key : this.conditionSetting.keySet()) {
				Map<String, String> c1 = this.conditionSetting.get(key);
				Map<String, String> c2 = new HashMap<String, String>();
				for (String name : c1.keySet()) {
					c2.put(name, c1.get(name));
				}
				result.conditionSetting.put(key, c2);
			}
		}
		return result;
	}
}
