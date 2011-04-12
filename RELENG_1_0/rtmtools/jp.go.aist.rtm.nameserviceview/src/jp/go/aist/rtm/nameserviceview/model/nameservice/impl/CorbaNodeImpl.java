/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package jp.go.aist.rtm.nameserviceview.model.nameservice.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import jp.go.aist.rtm.nameserviceview.manager.ManagerPackage;
import jp.go.aist.rtm.nameserviceview.manager.Node;
import jp.go.aist.rtm.nameserviceview.model.nameservice.CorbaNode;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NameServiceReference;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NameservicePackage;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingContextNode;
import jp.go.aist.rtm.toolscommon.model.core.impl.CorbaWrapperObjectImpl;
import jp.go.aist.rtm.toolscommon.model.manager.RTCManager;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Corba Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link jp.go.aist.rtm.nameserviceview.model.nameservice.impl.CorbaNodeImpl#getNodes <em>Nodes</em>}</li>
 *   <li>{@link jp.go.aist.rtm.nameserviceview.model.nameservice.impl.CorbaNodeImpl#getNameServiceReference <em>Name Service Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CorbaNodeImpl extends CorbaWrapperObjectImpl implements CorbaNode {
	/**
	 * The cached value of the '{@link #getNodes() <em>Nodes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodes()
	 * @generated
	 * @ordered
	 */
	protected EList nodes;

	/**
	 * The cached value of the '{@link #getNameServiceReference() <em>Name Service Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNameServiceReference()
	 * @generated
	 * @ordered
	 */
	protected NameServiceReference nameServiceReference;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CorbaNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return NameservicePackage.Literals.CORBA_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getNodes() {
		if (nodes == null) {
			nodes = new EObjectContainmentEList(Node.class, this, NameservicePackage.CORBA_NODE__NODES);
		}
		return nodes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NameServiceReference getNameServiceReference() {
		if (nameServiceReference != null && nameServiceReference.eIsProxy()) {
			InternalEObject oldNameServiceReference = (InternalEObject)nameServiceReference;
			nameServiceReference = (NameServiceReference)eResolveProxy(oldNameServiceReference);
			if (nameServiceReference != oldNameServiceReference) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, NameservicePackage.CORBA_NODE__NAME_SERVICE_REFERENCE, oldNameServiceReference, nameServiceReference));
			}
		}
		return nameServiceReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NameServiceReference basicGetNameServiceReference() {
		return nameServiceReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNameServiceReference(NameServiceReference newNameServiceReference) {
		NameServiceReference oldNameServiceReference = nameServiceReference;
		nameServiceReference = newNameServiceReference;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, NameservicePackage.CORBA_NODE__NAME_SERVICE_REFERENCE, oldNameServiceReference, nameServiceReference));
	}

	/**
	 * <!-- begin-user-doc --> 
	 * ���Y�m�[�h���l�[���T�[�o����폜����iCORBA��p�j
	 * <!-- end-user-doc -->
	 * 
	 */
	public void deleteR() throws NotFound, CannotProceed, InvalidName {
		NameComponent[] nameComponents = getNameServiceReference().getBinding().binding_name;
		if (eContainer() instanceof NamingContextNode) {
			((NamingContextNode)eContainer())
			.getCorbaObjectInterface()
			.unbind(
					new NameComponent[] { nameComponents[nameComponents.length - 1] });
			
		}
		// �m�[�h�������͓̂����X���b�h�ɔC���� 2009.4.2
//		EcoreUtil.remove(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case NameservicePackage.CORBA_NODE__NODES:
				return ((InternalEList)getNodes()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NameservicePackage.CORBA_NODE__NODES:
				return getNodes();
			case NameservicePackage.CORBA_NODE__NAME_SERVICE_REFERENCE:
				if (resolve) return getNameServiceReference();
				return basicGetNameServiceReference();
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
			case NameservicePackage.CORBA_NODE__NODES:
				getNodes().clear();
				getNodes().addAll((Collection)newValue);
				return;
			case NameservicePackage.CORBA_NODE__NAME_SERVICE_REFERENCE:
				setNameServiceReference((NameServiceReference)newValue);
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
			case NameservicePackage.CORBA_NODE__NODES:
				getNodes().clear();
				return;
			case NameservicePackage.CORBA_NODE__NAME_SERVICE_REFERENCE:
				setNameServiceReference((NameServiceReference)null);
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
			case NameservicePackage.CORBA_NODE__NODES:
				return nodes != null && !nodes.isEmpty();
			case NameservicePackage.CORBA_NODE__NAME_SERVICE_REFERENCE:
				return nameServiceReference != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
		if (baseClass == Node.class) {
			switch (derivedFeatureID) {
				case NameservicePackage.CORBA_NODE__NODES: return ManagerPackage.NODE__NODES;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass) {
		if (baseClass == Node.class) {
			switch (baseFeatureID) {
				case ManagerPackage.NODE__NODES: return NameservicePackage.CORBA_NODE__NODES;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}
	
	/* (non-Javadoc)
	 * @see jp.go.aist.rtm.toolscommon.model.core.CorbaWrapperObject#getCorbaObjectInterface()
	 */
	public org.omg.CORBA.Object getCorbaObjectInterface() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Binding�̔�r���s��
	 */
	public static Comparator<org.omg.CosNaming.Binding> COMARATOR = new Comparator<org.omg.CosNaming.Binding>() {
		public int compare(org.omg.CosNaming.Binding o1,
				org.omg.CosNaming.Binding o2) {
			for (int i = 0; i < o1.binding_name.length
					&& i < o2.binding_name.length; i++) {
				int compareId = o1.binding_name[i].id
						.compareTo(o2.binding_name[i].id);
				if (compareId != 0) {
					return compareId;
				}

				int compareKind = o1.binding_name[i].kind
						.compareTo(o2.binding_name[i].kind);
				if (compareKind != 0) {
					return compareKind;
				}
			}

			return o1.binding_name.length - o2.binding_name.length;
		}
	};
	
	/**
	 * �l�[���T�[�r�X�ɓo�^����Ă���RTCManager�̃��X�g��Ԃ��܂��B
	 * NodeImpl�Ɠ��������ł͂��邪�A���傤���Ȃ�
	 */
	public List<RTCManager> getRTCManagerList() {
		List<RTCManager> list = new ArrayList<RTCManager>();
		for (Object obj : getNodes()) {
			Node nc = (Node) obj;
			list.addAll(nc.getRTCManagerList());
		}
		return list;
	}

} //CorbaNodeImpl
