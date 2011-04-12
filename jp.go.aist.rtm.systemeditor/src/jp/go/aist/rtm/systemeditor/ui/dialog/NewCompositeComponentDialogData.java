package jp.go.aist.rtm.systemeditor.ui.dialog;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;
import jp.go.aist.rtm.toolscommon.model.component.Port;

/**
 * �V�K�����R���|�[�l���g�쐬�_�C�A���O�ɕ\������f�[�^��p�ӂ��郆�[�e�B�e�B�N���X
 * 
 */
public class NewCompositeComponentDialogData {
	static String[] getPathComboItems(List<Component> selectedComponents) {
		List<String> paths = new ArrayList<String>();
		for (Component obj : selectedComponents) {
			String path = obj.getPath();
			if (path != null && !paths.contains(path)) {
				paths.add(path);
			}
		}
		return paths.toArray(new String[] {});
	}

	static List<String> getPorts(List<Component> selectedComponents) {
		List<String> ports = new ArrayList<String>();
		for (Component obj : selectedComponents) {
			for (Object element : obj.getPorts()) {
				Port port = (Port) element;
				ports.add(port.getNameL());
				// ports.add(obj.getInstanceNameL()+ "." + port.getNameL());
			}
		}
		return ports;
	}

	// �����R���|�[�l���g�������Ƀ}�l�[�W���ɓn���p�����[�^��Ԃ�
	public static String getParam(String compositeType, String instanceName,
			String exportedPortString) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(compositeType).append("Composite?instance_name=");
		buffer.append(instanceName);
		buffer.append("&exported_ports=");
		buffer.append(exportedPortString);
		return buffer.toString();
	}

	/**
	 * @param dialog
	 *            �V�K�����R���|�[�l���g�쐬�_�C�A���O
	 * @return �I�t���C���G�f�B�^�ō쐬���ꂽ�����R���|�[�l���g
	 */
	public static Component createCompositeComponentSpecification(
			NewCompositeComponentDialog dialog) {
		Component compositeComponent = ComponentFactory.eINSTANCE
				.createComponentSpecification();
		// �v���t�@�C���ݒ�(category�̂�)
		compositeComponent.setInstanceNameL(dialog.getInstanceName());
		compositeComponent.setVenderL("");
		compositeComponent.setCategoryL("composite."
				+ dialog.getCompositeType());
		compositeComponent
				.setTypeNameL(dialog.getCompositeType() + "Composite");
		compositeComponent.setVersionL("");
		String compId = "RTC:" + compositeComponent.getVenderL() + "."
				+ compositeComponent.getCategoryL() + "."
				+ compositeComponent.getTypeNameL() + ":"
				+ compositeComponent.getVersionL();
		compositeComponent.setComponentId(compId);
		compositeComponent.setPathId(dialog.getPathId());
		// �I�t���C����Grouping����RTC�ȊO�̏ꍇ��EC��ǉ�
		if (!Component.COMPOSITETYPE_GROUPING.equals(dialog.getCompositeType())) {
			ExecutionContext ec = ComponentFactory.eINSTANCE
					.createExecutionContext();
			ec.setKindL(ExecutionContext.KIND_PERIODIC);
			ec.setRateL(1000.0);
			ec.setOwner(compositeComponent);
			compositeComponent.getExecutionContexts().add(ec);
			compositeComponent.getExecutionContextHandler().sync();
		}
		// ���ConfigurationSet�ݒ�
		ConfigurationSet configSet = ComponentFactory.eINSTANCE
				.createConfigurationSet();
		compositeComponent.getConfigurationSets().add(configSet);
		compositeComponent.setActiveConfigurationSet(configSet);
		configSet.setId("default");
		populateExportPorts(configSet, dialog.getExportedPortString());

		return compositeComponent;
	}

	private static void populateExportPorts(ConfigurationSet configSet,
			String exportedPortString) {
		NameValue nv = ComponentFactory.eINSTANCE.createNameValue();
		nv.setName("exported_ports");
		nv.setValue(exportedPortString);
		configSet.getConfigurationData().add(nv);
	}

}
