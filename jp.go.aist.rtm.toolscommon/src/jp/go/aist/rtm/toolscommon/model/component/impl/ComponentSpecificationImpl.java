/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.toolscommon.model.component.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.Port;
import jp.go.aist.rtm.toolscommon.synchronizationframework.LocalObject;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.AttributeMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ClassMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ConstructorParamMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ManyReferenceMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.MappingRule;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ReferenceMapping;
import jp.go.aist.rtm.toolscommon.ui.propertysource.ComponentSpecificationPropertySource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.views.properties.IPropertySource;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ComponentSpecificationImpl#getAliasName <em>Alias Name</em>}</li>
 *   <li>{@link jp.go.aist.rtm.toolscommon.model.component.impl.ComponentSpecificationImpl#isSpecUnLoad <em>Spec Un Load</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentSpecificationImpl extends ComponentImpl implements ComponentSpecification {
	/**
	 * The default value of the '{@link #getAliasName() <em>Alias Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAliasName()
	 * @generated
	 * @ordered
	 */
	protected static final String ALIAS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAliasName() <em>Alias Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAliasName()
	 * @generated
	 * @ordered
	 */
	protected String aliasName = ALIAS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #isSpecUnLoad() <em>Spec Un Load</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSpecUnLoad()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SPEC_UN_LOAD_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSpecUnLoad() <em>Spec Un Load</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSpecUnLoad()
	 * @generated
	 * @ordered
	 */
	protected boolean specUnLoad = SPEC_UN_LOAD_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ComponentSpecificationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ComponentPackage.Literals.COMPONENT_SPECIFICATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAliasName() {
		return aliasName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAliasName(String newAliasName) {
		String oldAliasName = aliasName;
		aliasName = newAliasName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT_SPECIFICATION__ALIAS_NAME, oldAliasName, aliasName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSpecUnLoad() {
		return specUnLoad;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpecUnLoad(boolean newSpecUnLoad) {
		boolean oldSpecUnLoad = specUnLoad;
		specUnLoad = newSpecUnLoad;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.COMPONENT_SPECIFICATION__SPEC_UN_LOAD, oldSpecUnLoad, specUnLoad));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ComponentPackage.COMPONENT_SPECIFICATION__ALIAS_NAME:
				return getAliasName();
			case ComponentPackage.COMPONENT_SPECIFICATION__SPEC_UN_LOAD:
				return isSpecUnLoad() ? Boolean.TRUE : Boolean.FALSE;
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ComponentPackage.COMPONENT_SPECIFICATION__ALIAS_NAME:
				setAliasName((String)newValue);
				return;
			case ComponentPackage.COMPONENT_SPECIFICATION__SPEC_UN_LOAD:
				setSpecUnLoad(((Boolean)newValue).booleanValue());
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case ComponentPackage.COMPONENT_SPECIFICATION__ALIAS_NAME:
				setAliasName(ALIAS_NAME_EDEFAULT);
				return;
			case ComponentPackage.COMPONENT_SPECIFICATION__SPEC_UN_LOAD:
				setSpecUnLoad(SPEC_UN_LOAD_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ComponentPackage.COMPONENT_SPECIFICATION__ALIAS_NAME:
				return ALIAS_NAME_EDEFAULT == null ? aliasName != null : !ALIAS_NAME_EDEFAULT.equals(aliasName);
			case ComponentPackage.COMPONENT_SPECIFICATION__SPEC_UN_LOAD:
				return specUnLoad != SPEC_UN_LOAD_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (aliasName: ");
		result.append(aliasName);
		result.append(", specUnLoad: ");
		result.append(specUnLoad);
		result.append(')');
		return result.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateConfigurationSetListR(List list,
			ConfigurationSet activeConfigurationSet, List originallist) {
		
		getConfigurationSets().clear();
		getConfigurationSets().addAll(list);
		setActiveConfigurationSet(activeConfigurationSet);

		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateConfigurationSetR(ConfigurationSet configSet, boolean isActive) {
		EList list = this.getConfigurationSets();
		int index = -1;
		for (int i = 0; i < list.size(); i++) {
			ConfigurationSet cs = (ConfigurationSet) list.get(i);
			if (cs.getId().equals(configSet.getId())) {
				index = i;
				break;
			}
		}
		if (index == -1) {
			list.add(configSet);
		} else {
			ConfigurationSet acs = (ConfigurationSet) this
					.getActiveConfigurationSet();
			ConfigurationSet cs = (ConfigurationSet) list.get(index);
			if (acs != null && acs.getId().equals(cs.getId())) {
				this.setActiveConfigurationSet(configSet);
			}
			Object o = list.get(index);
			list.set(index, configSet);
			if (!o.equals(configSet)) {
				list.remove(o);
			}
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean addComponentsR(List componentList) {
		return doAddComponents(componentList);			
	}

	@SuppressWarnings("unchecked")
	private boolean doAddComponents(Collection componentList) {
		getComponents().addAll(componentList);
		for (Object o : componentList) {
			if (((Component) o).inOnlineSystemDiagram()) {
				// �I�����C���̏ꍇ�͓��������Ń|�[�g�ݒ�
				return true;
			}
		}
		// �I�t���C���̏ꍇ�͂����Ń|�[�g�ݒ�
		_setWrappingPorts(_getWrappedPorts());
		return true;
	}

	@Override
	public boolean setComponentsR(List<Component> componentList) {
		getComponents().clear();
		return doAddComponents(componentList);			
	}

	@Override
	public boolean removeComponentR(Component component) {
		removeByEqual(getComponents(), component);
		if (component.inOnlineSystemDiagram()) {
			// �I�����C���̏ꍇ�͓��������Ń|�[�g�ݒ�
			return true;
		}
		// �I�t���C���̏ꍇ�͎蓮�Őݒ�
		List<Port> ports = this._getWrappedPorts();
		this._setWrappingPorts(ports);
		return true;
	}

	@SuppressWarnings("unchecked")
	private void removeByEqual(EList components, Component component) {
		for (Iterator iterate = components.iterator(); iterate.hasNext();) {
			if (iterate.next().equals(component)) iterate.remove();
		}
	}

	@SuppressWarnings("unchecked")
	void _setWrappingPorts(List<Port> ports) {
		// �qRTC�̃|�[�g�̃R�s�[�Ń|�[�g���X�g���X�V
		getPorts().clear();
		for (Port port : ports) {
			getPorts().add(port.proxy());
		}
	}

	@SuppressWarnings("unchecked")
	List<Port> _getWrappedPorts() {
		// �v���p�e�B����\������|�[�g���ꗗ���쐬
		List<String> names = new ArrayList<String>(getExportedPorts());

		// �v���L�V�Ώۂ̎q�R���|�[�l���g�̃|�[�g�ꗗ�𐶐�
		List<Port> ports = new ArrayList<Port>();
		for (Object obj : getAllComponents()) {
			Component cp = (Component) obj;
			for (Object o : cp.getPorts()) {
				Port port = (Port) o;
				String name = cp.getInstanceNameL() + "."
						+ port.getNameL();
				if (names.contains(name)) {
					ports.add(port);
				}
			}
		}
		return ports;
	}

//	@Override
	public boolean isGroupingCompositeComponent() {
		return (getCompositeTypeL().equals(
				Component.COMPOSITETYPE_GROUPING)) ;
	}

	@SuppressWarnings("unchecked")
	public java.lang.Object getAdapter(Class adapter) {
		java.lang.Object result = null;
		if (IPropertySource.class.equals(adapter)) {
			result = new ComponentSpecificationPropertySource(this);
		}

		if (result == null) {
			result = super.getAdapter(adapter);
		}
		return result;
	}

	/** Grouping����RTC�p�̃}�b�s���O���[�� */
	public static final MappingRule MAPPING_RULE = new MappingRule(null,
			new ClassMapping(ComponentSpecificationImpl.class,
					new ConstructorParamMapping[] {}, true) {
				@Override
				public boolean isTarget(LocalObject parent,
						Object[] remoteObjects, java.lang.Object link) {
					return false;
				}
			}, new AttributeMapping[] {}, new ReferenceMapping[] {});


	private static ReferenceMapping[] getReferenceMappings() {
		return new ReferenceMapping[] { new ManyReferenceMapping(
				ComponentPackage.eINSTANCE.getComponent_Ports()) {
			@SuppressWarnings("unchecked")
			@Override
			public void syncronizeLocal(LocalObject localObject) {
				if (!(localObject instanceof ComponentSpecificationImpl)) {
					return;
				}
				ComponentSpecificationImpl comp = (ComponentSpecificationImpl) localObject;
				if (!comp.inOnlineSystemDiagram() || !comp.isGroupingCompositeComponent()) {
					return;
				}
				
				// �q�R���|�[�l���g�̐����m�F���s��
				comp.removeDeadChild();
				
				// �|�[�g�̍X�V���s��
				// �V�����|�[�g�̃����N���X�g
				List<Port> newPorts = comp._getWrappedPorts();
				// ���݂̃|�[�g���X�V����
				for (Iterator iterate = comp.getPorts().iterator();iterate.hasNext();){
					Port port = (Port) iterate.next();
					if (!isExist(port, newPorts)) {
						iterate.remove();
					}
				}
				// �ǉ��Ώۂ̃|�[�g��ǉ�
				for (Port port : newPorts) {
					comp.getPorts().add(port.proxy());
				}
			}

			private boolean isExist(Port port, List<Port> newPorts) {
				String target = port.getOriginalPortString();
				for (Iterator<Port> iterate = newPorts.iterator();iterate.hasNext();){
					Port temp = iterate.next();
					if (temp.getOriginalPortString().equals(target)) {
						iterate.remove();
						return true;
					}
				}
				return false;
			}
		}
		};
	}

	public void synchronizeLocalAttribute(EStructuralFeature reference) {
		// Nothing to do
	}

	public void synchronizeLocalReference() {
		if (!inOnlineSystemDiagram()) return;
		if (!isGroupingCompositeComponent()) return;
		
		for (ReferenceMapping refMap : getReferenceMappings()) {
			try {
				refMap.syncronizeLocal(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

//	@Override
	public String getPath() {
		if (getPathId() == null) return null;
		int index = getPathId().lastIndexOf(File.separator);
		if (index < 0) return getPathId();
		return getPathId().substring(0, index);
	}

//	@Override
	public void synchronizeManually() {
		//Nothing to do
	}
	
	@SuppressWarnings("unchecked")
//	@Override
	public Component copy() {
		Component copy = (Component) EcoreUtil.copy(this);
		// ExecutionContext��ID�̊֘A�t���𕡐�
		for (int i = 0; i < this.getExecutionContexts().size(); i++) {
			ExecutionContext orgEc = (ExecutionContext) this
					.getExecutionContexts().get(i);
			ExecutionContext newEc = (ExecutionContext) copy
					.getExecutionContexts().get(i);
			if (newEc == null) {
				continue;
			}
			String ecid = this.getExecutionContextId(orgEc);
			if (ecid != null) {
				copy.setExecutionContext(ecid, newEc);
			}
		}
		adjustPathId(copy.getAllComponents());
		return copy;
	}

//	@Override
	public boolean isDead() {
		return true;
	}


} // ComponentSpecificationImpl
