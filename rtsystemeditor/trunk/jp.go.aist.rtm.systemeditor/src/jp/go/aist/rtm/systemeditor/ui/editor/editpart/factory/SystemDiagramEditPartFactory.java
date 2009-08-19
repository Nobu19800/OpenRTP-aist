package jp.go.aist.rtm.systemeditor.ui.editor.editpart.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.go.aist.rtm.systemeditor.RTSystemEditorPlugin;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.ui.actions.ActionRegistry;

/**
 * �V�X�e���_�C�A�O������EditPartFactory
 */
public class SystemDiagramEditPartFactory implements EditPartFactory {
    private static final String EXTENTION_POINT_NAME = "editpartfactory";

    private ActionRegistry actionRegistry;

    /**
     * �R���X�g���N�^
     * 
     * @param actionRegistry
     *            ActionRegistry
     */
    public SystemDiagramEditPartFactory(ActionRegistry actionRegistry) {
        this.actionRegistry = actionRegistry;
    }

    private static List<Map.Entry<Class<?>, Class<?>>> editPartMapping = null;

    /**
     * ���f���N���X�ɑΉ�����EditPart�̃N���X���擾����
     * <p>
     * �������쎞�APlugin.xml�ɒ�`���ꂽeditpartfactory�̊g���|�C���g����͂���
     * 
     * @param clazz
     * @return
     */
 	public static Class<?> getMappingEditPartClass(Class<?> clazz) {
        if (editPartMapping == null) {
            Map<Integer, Map.Entry<Class<?>, Class<?>>> tempMappingRules = new HashMap<Integer, Map.Entry<Class<?>, Class<?>>>();
            IExtension[] extensions = Platform.getExtensionRegistry()
                    .getExtensionPoint(
                    		RTSystemEditorPlugin.class.getPackage().getName(),
                            EXTENTION_POINT_NAME).getExtensions();
            for (IExtension extension : extensions) {
                for (IConfigurationElement configurationElement : extension
                        .getConfigurationElements()) {
                    String seq = configurationElement.getAttribute("seq");
                    String targetclassValue = configurationElement
                            .getAttribute("targetclass");
                    String editpartclassValue = configurationElement
                            .getAttribute("editpartclass");

                    try {
                        final Class<?> targetclass = Platform.getBundle(
                                extension.getNamespaceIdentifier()).loadClass(
                                targetclassValue);

                        final Class<?> editpartclass = Platform.getBundle(
                                extension.getNamespaceIdentifier()).loadClass(
                                editpartclassValue);

                        tempMappingRules.put(Integer.parseInt(seq),
                                new Map.Entry<Class<?>, Class<?>>() {
                                    public Class<?> getKey() {
                                        return targetclass;
                                    }

                                    public Class<?> getValue() {
                                        return editpartclass;
                                    }

                                    public Class<?> setValue(Class<?> value) {
                                        return null;
                                    }
                                });
                    } catch (Exception e) {
                        throw new RuntimeException(e); // system error
                    }
                }
            }

            List<Integer> keys = new ArrayList<Integer>(tempMappingRules
                    .keySet());
            Collections.sort(keys);

            editPartMapping = new ArrayList<Map.Entry<Class<?>, Class<?>>>();
            for (Integer key : keys) {
                editPartMapping.add(tempMappingRules.get(key));
            }
        }

        for (Map.Entry<Class<?>, Class<?>> entry : editPartMapping) {
            if (entry.getKey().isAssignableFrom(clazz)) {
                return entry.getValue();
            }
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    public EditPart createEditPart(EditPart editPart, Object object) {
        EditPart result = newEditPart(object);

        if (result != null) {
            result.setModel(object);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
	private EditPart newEditPart(Object object) {
        Class clazz = getMappingEditPartClass(object.getClass());

        EditPart result = null;
        if (clazz != null) {
            try {
                result = (EditPart) clazz.getDeclaredConstructor(
                        new Class[] { ActionRegistry.class }).newInstance(
                        new Object[] { actionRegistry });
            } catch (Exception e) {
                // void
            }
        }

        return result;
    }

}
