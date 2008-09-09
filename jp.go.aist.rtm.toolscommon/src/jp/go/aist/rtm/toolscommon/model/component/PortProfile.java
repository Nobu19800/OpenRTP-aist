package jp.go.aist.rtm.toolscommon.model.component;

import java.util.List;

import jp.go.aist.rtm.toolscommon.model.core.WrapperObject;

import org.eclipse.emf.common.util.EList;

/**
 * PortProfile��\������N���X
 * <p>
 * ���̃I�u�W�F�N�g�́A�o�����[�I�u�W�F�N�g�ł��邱�Ƃɒ��ӂ��邱�ƁB<br>
 * ���̃I�u�W�F�N�g���̂̂ɂ͓������s���Ȃ����߁A���̃I�u�W�F�N�g�̎Q�Ƃ�ێ��������邱�Ƃ́A�댯�ł���B<br>
 * �����������A�Q�ƌ��̃I�u�W�F�N�g���Q�Ƃ��āA�K�v�ɂȂ邽�тɂ��������ɓ���邱�ƁB
 * 
 * �܂��A���̃N���X��Equals���\�b�h���I�[�o�[���C�h���Ă���̂ŁARTC.PortProfile�ւ̃t�B�[���h�̒ǉ��̍ۂɂ́A�ێ��ӂ�Ȃ����ƁB
 * 
 * @model
 */
public interface PortProfile extends WrapperObject{

	/**
	 * @model transient="true"
	 */
	public RTC.PortProfile getRtcPortProfile();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.PortProfile#getRtcPortProfile <em>Rtc Port Profile</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rtc Port Profile</em>' attribute.
	 * @see #getRtcPortProfile()
	 * @generated
	 */
	void setRtcPortProfile(RTC.PortProfile value);

	/**
	 * @model changeable="false" transient="true" volatile="true"
	 */
	public boolean isAllowAnyDataType();

	/**
	 * @model changeable="false" transient="true" volatile="true"
	 */
	public boolean isAllowAnyInterfaceType();

	/**
	 * @model changeable="false" transient="true" volatile="true"
	 */
	public boolean isAllowAnyDataflowType();

	/**
	 * @model changeable="false" transient="true" volatile="true"
	 */
	public boolean isAllowAnySubscriptionType();

	/**
	 * @model
	 */
	public String getNameL();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.PortProfile#getNameL <em>Name L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name L</em>' attribute.
	 * @see #getNameL()
	 * @generated
	 */
	void setNameL(String value);

	/**
	 * 
	 */
	public List<String> getDataflowTypes();

	/**
	 */
	public List<String> getDataTypes();

	/**
	 */
	public List<String> getInterfaceTypes();

	/**
	 */
	public List<String> getSubsciptionTypes();

	/**
	 */
	public String getPortType();

	/**
	 * @model transient="true" containment="true"
	 *        type="jp.go.aist.rtm.toolscommon.model.component.NameValue"
	 * @return
	 */
	public EList getProperties();

	/**
	 * 
	 */
	public List<PortInterfaceProfile> getIterfaces();

	/**
	 * @model type="ConnectorProfile" transient="true" 
	 */
	public EList getConnectorProfiles();
}
