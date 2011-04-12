/**
 * <copyright>
 * </copyright>
 *
 * $Id: NamingObjectNodeImpl.java,v 1.7 2008/03/27 06:58:52 yamashita Exp $
 */
package jp.go.aist.rtm.nameserviceview.model.nameservice.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import jp.go.aist.rtm.nameserviceview.model.nameservice.CorbaNode;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NameServiceReference;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NameservicePackage;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingContextNode;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingObjectNode;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.core.CorePackage;
import jp.go.aist.rtm.toolscommon.model.core.WrapperObject;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;
import jp.go.aist.rtm.toolscommon.synchronizationframework.LocalObject;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationManager;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.AttributeMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ClassMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ConstructorParamMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.MappingRule;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.OneReferenceMapping;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.ReferenceMapping;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.omg.CosNaming.Binding;
import org.omg.CosNaming.BindingType;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Naming Object Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link jp.go.aist.rtm.nameserviceview.model.nameservice.impl.NamingObjectNodeImpl#getEntry <em>Entry</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NamingObjectNodeImpl extends CorbaNodeImpl implements NamingObjectNode {
	/**
	 * The cached value of the '{@link #getEntry() <em>Entry</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getEntry()
	 * @generated
	 * @ordered
	 */
	protected WrapperObject entry;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public NamingObjectNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NameservicePackage.Literals.NAMING_OBJECT_NODE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isZombie() {
		Binding bind = getNameServiceReference().getBinding();
		NameComponent nc = bind.binding_name[bind.binding_name.length - 1];
		String kind = (nc == null) ? "" : nc.kind;
		List<String> targetZombies = Arrays.asList("rtc", "mgr");
		return (targetZombies.contains(kind) && getEntry() == null);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public WrapperObject getEntry() {
		return entry;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setEntry(WrapperObject newEntry) {
		WrapperObject oldEntry = entry;
		entry = newEntry;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NameservicePackage.NAMING_OBJECT_NODE__ENTRY, oldEntry, entry));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NameservicePackage.NAMING_OBJECT_NODE__ENTRY:
				return getEntry();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case NameservicePackage.NAMING_OBJECT_NODE__ENTRY:
				setEntry((WrapperObject)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case NameservicePackage.NAMING_OBJECT_NODE__ENTRY:
				setEntry((WrapperObject)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case NameservicePackage.NAMING_OBJECT_NODE__ENTRY:
				return entry != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * �l�[���T�[�r�X�ɓo�^����Ă���RTCManager�̃��X�g��Ԃ��܂��B
	 */
	public List<RTCManager> getRTCManagerList() {
		if (!(getEntry() instanceof RTCManager))
			return Collections.emptyList();
		
		RTCManager mgr = (RTCManager) getEntry();
		mgr.setPathId(getBindPath());
		List<RTCManager> list = new ArrayList<RTCManager>();
		list.add(mgr);
		return list;
	}

	private String getBindPath() {
		NameComponent[] cs = getNameServiceReference().getBinding().binding_name;
		String path = "";
		for (NameComponent c : cs) {
			if (path.length() > 0) {
				path += "/";
			}
			path += c.id;
		}
		return path;
	}

	public static final ThreadLocal<NamingObjectNode> PROXY_THREAD_LOCAL = new ThreadLocal<NamingObjectNode>();

	public static final MappingRule MAPPING_RULE = new MappingRule(
			null,
			new ClassMapping(
					NamingObjectNodeImpl.class,
					new ConstructorParamMapping[] { new ConstructorParamMapping(
							Object.class, CorePackage.eINSTANCE
									.getCorbaWrapperObject_CorbaObject()) },
					true) {
				@Override
				public boolean isTarget(LocalObject parent,
						Object[] remoteObjects, java.lang.Object link) {
					return link instanceof Binding 
							&& ((Binding) link).binding_type == BindingType.nobject;
				}

				@Override
				public LocalObject createLocalObject(LocalObject parent,
						Object[] remoteObjects, java.lang.Object link) {
					NamingObjectNode result = (NamingObjectNode) super
							.createLocalObject(parent, remoteObjects, link);

					NameServiceReference createMergedNameServiceReference = ((CorbaNode) parent)
							.getNameServiceReference()
							.createMergedNameServiceReference((Binding) link);

					((CorbaNode) result)
							.setNameServiceReference(createMergedNameServiceReference);
					return result;
				}

			}, new AttributeMapping[] { new AttributeMapping(
					CorePackage.eINSTANCE.getCorbaWrapperObject_CorbaObject()) {
				// �]���r�̏ꍇ�ɁA�V���ȃI�u�W�F�N�g�ɍX�V����Ă����o�ł���悤�ɂ��邽�߂̓����B
				// �����Ŏ�������̂͂��܂肫�ꂢ�ł͂Ȃ��݌v
				// �{���Ȃ�΁ANamingContextExt�Ō��o����ׂ������A
				// �]���r�����ĐV���ȃI�u�W�F�N�g�ɍX�V����Ă�link���S�������ł��邽�߁A
				// �ႤNamingObject�ł���Ɣ��f����Ȃ����߁A�����Ɏ������邱�Ƃɂ����B

				public Object getRemoteAttributeValue(LocalObject localObject,
						Object[] remoteObjects) {

					NamingObjectNode namingObjectNode = ((NamingObjectNode) localObject);

					Object result = null;
					if (namingObjectNode.eContainer() != null) {
						Binding binding = namingObjectNode
								.getNameServiceReference().getBinding();
						try {
							result = ((NamingContextNode)namingObjectNode
									.eContainer())
									.getCorbaObjectInterface()
									.resolve(
											new NameComponent[] { binding.binding_name[binding.binding_name.length - 1] });
						} catch (NotFound e) {
						} catch (CannotProceed e) {
						} catch (InvalidName e) {
						}
					} else {
						result = namingObjectNode.getCorbaObject();
					}

					return result;
				}
			}

			},// null
			new ReferenceMapping[] {
					new OneReferenceMapping(NameservicePackage.eINSTANCE
							.getNamingObjectNode_Entry()) {
						@Override
						public Object getNewRemoteLink(LocalObject localObject,
								Object[] remoteObjects) {
							return ((NamingObjectNode) localObject)
									.getCorbaObject();
							// return null;
						}

						@Override
						public boolean isLinkEquals(Object link1, Object link2) {
							return false;
						}

						@Override
						public LocalObject loadLocalObjectByRemoteObject(
								LocalObject localObject,
								SynchronizationManager synchronizationManager,
								java.lang.Object link, Object[] remoteObject) {
							LocalObject result = super.loadLocalObjectByRemoteObject(
									localObject, synchronizationManager, link,
									remoteObject);

							if (result instanceof Component) {
								String pathId = ((NamingObjectNode) localObject)
										.getNameServiceReference().getPathId();
								((Component) result)
										.setPathId(pathId);
							}
							return result;
						}
					}
			});
} // NamingObjectNodeImpl
