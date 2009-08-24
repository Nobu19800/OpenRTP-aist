package jp.go.aist.rtm.toolscommon.model.component;

import jp.go.aist.rtm.toolscommon.model.core.WrapperObject;

import RTC.ExecutionKind;

/**
 * ExecutionContext��\������N���X
 * @model
 */
public interface ExecutionContext extends WrapperObject {
	public static final int STATE_UNKNOWN = (0);
	public static final int STATE_STOPPED = (1);
	public static final int STATE_RUNNING = (2);

	public static final int KIND_UNKNOWN = -1;
	public static final int KIND_PERIODIC = ExecutionKind.PERIODIC.value();
	public static final int KIND_EVENT_DRIVEN = ExecutionKind.EVENT_DRIVEN
			.value();
	public static final int KIND_OTHER = ExecutionKind.OTHER.value();
	
	public static final int RTC_UNCERTAIN = -2;
	public static final int RTC_UNKNOWN = -1;
	public static final int RTC_CREATED = RTC.LifeCycleState.CREATED_STATE.value();
	public static final int RTC_INACTIVE = RTC.LifeCycleState.INACTIVE_STATE
			.value();
	public static final int RTC_ACTIVE = RTC.LifeCycleState.ACTIVE_STATE
			.value();
	public static final int RTC_ERROR = RTC.LifeCycleState.ERROR_STATE.value();


	/**
	 * @model
	 * @return ExecutionContext�̎�ށiUNKNOWN/PERIODIC/EVENT_DRIVEN/OTHER)
	 */
	public int getKindL();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ExecutionContext#getKindL <em>Kind L</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind L</em>' attribute.
	 * @see #getKindL()
	 * @generated
	 */
	void setKindL(int value);

	/**
	 * @model ExcetuionContext�̎��s�Ԋu
	 * @return
	 */
	public Double getRateL();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ExecutionContext#getRateL <em>Rate L</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rate L</em>' attribute.
	 * @see #getRateL()
	 * @generated
	 */
	void setRateL(Double value);

	/**
	 * @model
	 * @return�@ExecutionContext�̏��(UNKNOWN/STOPPED/RUNNING)
	 */
	public int getStateL();

	/**
	 * Sets the value of the '{@link jp.go.aist.rtm.toolscommon.model.component.ExecutionContext#getStateL <em>State L</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>State L</em>' attribute.
	 * @see #getStateL()
	 * @generated
	 */
	void setStateL(int value);

	/**
	 * <!-- begin-user-doc -->
	 * ExecutionContext�̎�ނ�\���������Ԃ��܂��B(UNKNOWN/PERIODIC/EVENT_DRIVEN/OTHER)
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	String getKindName();

	/**
	 * <!-- begin-user-doc -->
	 * ExecutionContext�̏�Ԃ�\���������Ԃ��܂��B(UNKNOWN/STOPPED/RUNNING)
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	String getStateName();

}
