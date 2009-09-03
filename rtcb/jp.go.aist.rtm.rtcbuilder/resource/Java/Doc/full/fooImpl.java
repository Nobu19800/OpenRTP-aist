// -*- Java -*-
/*!
 * @file  fooImpl.java
 * @brief MDesc
 * @date  $Date$
 *
 * @author Noriaki Ando <n-ando@aist.go.jp>
 *
 * Copyright (C) 2006-2008 ���C�Z���X
 *
 * $Id$
 */

import RTC.TimedShort;
import RTC.TimedLong;
import RTC.TimedFloat;
import jp.go.aist.rtm.RTC.DataFlowComponentBase;
import jp.go.aist.rtm.RTC.Manager;
import jp.go.aist.rtm.RTC.port.InPort;
import jp.go.aist.rtm.RTC.port.OutPort;
import jp.go.aist.rtm.RTC.util.DataRef;
import jp.go.aist.rtm.RTC.port.CorbaConsumer;
import jp.go.aist.rtm.RTC.port.CorbaPort;
import org.omg.PortableServer.POAPackage.ObjectNotActive;
import org.omg.PortableServer.POAPackage.ServantAlreadyActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;
import jp.go.aist.rtm.RTC.util.IntegerHolder;
import jp.go.aist.rtm.RTC.util.DoubleHolder;
import jp.go.aist.rtm.RTC.util.StringHolder;
import RTC.ReturnCode_t;

/*!
 * @class fooImpl
 * @brief MDesc
 *
 * �{�R���|�[�l���g�̊T�v����
 *
 * �{�R���|�[�l���g�̓��o��
 *
 * �{�R���|�[�l���g�̃A���S���Y���Ȃ�
 *
 * �Q�l�����̏��
 *
 */
public class fooImpl extends DataFlowComponentBase {

  /*!
   * @brief constructor
   * @param manager Maneger Object
   */
	public fooImpl(Manager manager) {  
        super(manager);
        // <rtc-template block="initializer">
        m_InP1_val = new TimedShort();
        m_InP1 = new DataRef<TimedShort>(m_InP1_val);
        m_InP1In = new InPort<TimedShort>("InP1", m_InP1);
        m_InP2_val = new TimedLong();
        m_InP2 = new DataRef<TimedLong>(m_InP2_val);
        m_InP2In = new InPort<TimedLong>("InP2", m_InP2);
        m_OutP1_val = new TimedLong();
        m_OutP1 = new DataRef<TimedLong>(m_OutP1_val);
        m_OutP1Out = new OutPort<TimedLong>("OutP1", m_OutP1);
        m_OutP2_val = new TimedFloat();
        m_OutP2 = new DataRef<TimedFloat>(m_OutP2_val);
        m_OutP2Out = new OutPort<TimedFloat>("OutP2", m_OutP2);
        m_svPortPort = new CorbaPort("svPort");
        m_cmPortPort = new CorbaPort("cmPort");
        // </rtc-template>

        // Registration: InPort/OutPort/Service
        // <rtc-template block="registration">
        // Set InPort buffers
        try {
			registerInPort(TimedShort.class, "InP1", m_InP1In);
			registerInPort(TimedLong.class, "InP2", m_InP2In);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        // Set OutPort buffer
        try {
			registerOutPort(TimedLong.class, "OutP1", m_OutP1Out);
			registerOutPort(TimedFloat.class, "OutP2", m_OutP2Out);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        // Set service provider to Ports
        try {
        	m_svPortPort.registerProvider("acc", "MyService", m_acc);
        } catch (ServantAlreadyActive e) {
            e.printStackTrace();
        } catch (WrongPolicy e) {
            e.printStackTrace();
        } catch (ObjectNotActive e) {
            e.printStackTrace();
        }
        
        // Set service consumers to Ports
        m_cmPortPort.registerConsumer("rate", "DAQService", m_rateBase);
        
        // Set CORBA Service Ports
        registerPort(m_svPortPort);
        registerPort(m_cmPortPort);
        
        // </rtc-template>
    }

    /*!
     * on_initialize�T�v����
     *
     * The initialize action (on CREATED->ALIVE transition)
     * formaer rtc_init_entry() 
     *
     * @return RTC::ReturnCode_t
     * 
     * @pre on_initialize���O����
     * @post on_initialize�������
     * 
     */
    @Override
    protected ReturnCode_t onInitialize() {
        bindParameter("int_param0", m_int_param0, "0");
        bindParameter("int_param1", m_int_param1, "1");
        bindParameter("double_param0", m_double_param0, "0.11");
        bindParameter("str_param0", m_str_param0, "���{��");
        bindParameter("str_param1", m_str_param1, "dara");
        return ReturnCode_t.RTC_OK;
    }

    /***
     * on_finalize�T�v����
     *
     * The finalize action (on ALIVE->END transition)
     * formaer rtc_exiting_entry()
     *
     * @return RTC::ReturnCode_t
     * 
     * @pre on_finalize���O����
     * @post on_finalize�������
     * 
     */
//    @Override
//    protected ReturnCode_t onFinalize() {
//        return super.onFinalize();
//    }

    /***
     * on_startup�T�v����
     *
     * The startup action when ExecutionContext startup
     * former rtc_starting_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * @pre on_startup���O����
     * @post on_startup�������
     * 
     */
//    @Override
//    protected ReturnCode_t onStartup(int ec_id) {
//        return super.onStartup(ec_id);
//    }

    /***
     * on_shutdown�T�v����
     *
     * The shutdown action when ExecutionContext stop
     * former rtc_stopping_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * @pre on_shutdown���O����
     * @post on_shutdown�������
     * 
     */
//    @Override
//    protected ReturnCode_t onShutdown(int ec_id) {
//        return super.onShutdown(ec_id);
//    }

    /***
     * on_activated�T�v����
     *
     * The activated action (Active state entry action)
     * former rtc_active_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * @pre on_activated���O����
     * @post on_activated�������
     * 
     */
//    @Override
//    protected ReturnCode_t onActivated(int ec_id) {
//        return super.onActivated(ec_id);
//    }

    /***
     * on_deactivated�T�v����
     *
     * The deactivated action (Active state exit action)
     * former rtc_active_exit()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * @pre on_deactivated���O����
     * @post on_deactivated�������
     * 
     */
//    @Override
//    protected ReturnCode_t onDeactivated(int ec_id) {
//        return super.onDeactivated(ec_id);
//    }

    /***
     * on_execute�T�v����
     *
     * The execution action that is invoked periodically
     * former rtc_active_do()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * @pre on_execute���O����
     * @post on_execute�������
     * 
     */
//    @Override
//    protected ReturnCode_t onExecute(int ec_id) {
//        return super.onExecute(ec_id);
//    }

    /***
     * on_aborting�T�v����
     *
     * The aborting action when main logic error occurred.
     * former rtc_aborting_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * @pre on_aborting���O����
     * @post on_aborting�������
     * 
     */
//  @Override
//  public ReturnCode_t onAborting(int ec_id) {
//      return super.onAborting(ec_id);
//  }

    /***
     * on_error�T�v����
     *
     * The error action in ERROR state
     * former rtc_error_do()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * @pre on_error���O����
     * @post on_error�������
     * 
     */
//    @Override
//    public ReturnCode_t onError(int ec_id) {
//        return super.onError(ec_id);
//    }

    /***
     * on_reset�T�v����
     *
     * The reset action that is invoked resetting
     * This is same but different the former rtc_init_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * @pre on_reset���O����
     * @post on_reset�������
     * 
     */
//    @Override
//    protected ReturnCode_t onReset(int ec_id) {
//        return super.onReset(ec_id);
//    }

    /***
     * on_state_update�T�v����
     *
     * The state update action that is invoked after onExecute() action
     * no corresponding operation exists in OpenRTm-aist-0.2.0
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * @pre on_state_update���O����
     * @post on_state_update�������
     * 
     */
//    @Override
//    protected ReturnCode_t onStateUpdate(int ec_id) {
//        return super.onStateUpdate(ec_id);
//    }

    /***
     * on_rate_changed�T�v����
     *
     * The action that is invoked when execution context's rate is changed
     * no corresponding operation exists in OpenRTm-aist-0.2.0
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * @pre on_rate_changed���O����
     * @post on_rate_changed�������
     * 
     */
//    @Override
//    protected ReturnCode_t onRateChanged(int ec_id) {
//        return super.onRateChanged(ec_id);
//    }
//
	// Configuration variable declaration
	// <rtc-template block="config_declare">
    /*!
     * Config1�̊T�v
     * - Name: Config1�̖��O int_param0
     * - DefaultValue: 0
     * - Unit: Config1�̒P��
     * - Range: Config1�͈̔�
     * - Constraint: Config1�̐������
     */
    protected IntegerHolder m_int_param0 = new IntegerHolder();
    /*!
     * Config2�̊T�v
     * - Name: Config2�̖��O int_param1
     * - DefaultValue: 1
     * - Unit: Config2�̒P��
     * - Range: Config2�͈̔�
     * - Constraint: Config2�̐������
     */
    protected IntegerHolder m_int_param1 = new IntegerHolder();
    /*!
     * Config3�̊T�v
     * - Name: Config3�̖��O double_param0
     * - DefaultValue: 0.11
     * - Unit: Config3�̒P��
     * - Range: Config3�͈̔�
     * - Constraint: Config3�̐������
     */
    protected DoubleHolder m_double_param0 = new DoubleHolder();
    /*!
     * Config4�̊T�v
     * - Name: Config4�̖��O str_param0
     * - DefaultValue: ���{��
     * - Unit: Config4�̒P��
     * - Range: Config4�͈̔�
     * - Constraint: Config4�̐������
     */
    protected StringHolder m_str_param0 = new StringHolder();
    /*!
     * Config5�̊T�v
     * - Name: Config5�̖��O str_param1
     * - DefaultValue: dara
     * - Unit: Config5�̒P��
     * - Range: Config5�͈̔�
     * - Constraint: Config5�̐������
     */
    protected StringHolder m_str_param1 = new StringHolder();
	// </rtc-template>

    // DataInPort declaration
    // <rtc-template block="inport_declare">
    protected TimedShort m_InP1_val;
    protected DataRef<TimedShort> m_InP1;
    /*!
     * InPort1�̊T�v
     * - Type: InPort1�̃f�[�^�̌^
     * - Number: InPort1�̃f�[�^�̐�
     * - Semantics: InPort1�̃f�[�^�̈Ӗ�
     * - Unit: InPort1�̃f�[�^�̒P��
     * - Frequency: InPort1�̃f�[�^�̔����p�x
     * - Operation Cycle: InPort1�̃f�[�^�̏�������
     */
    protected InPort<TimedShort> m_InP1In;

    protected TimedLong m_InP2_val;
    protected DataRef<TimedLong> m_InP2;
    /*!
     * InPort2�̊T�v
     * - Type: InPort2�̃f�[�^�̌^
     * - Number: InPort2�̃f�[�^�̐�
     * - Semantics: InPort2�̃f�[�^�̈Ӗ�
     * - Unit: InPort2�̃f�[�^�̒P��
     * - Frequency: InPort2�̃f�[�^�̔����p�x
     * - Operation Cycle: InPort2�̃f�[�^�̏�������
     */
    protected InPort<TimedLong> m_InP2In;

    
    // </rtc-template>

    // DataOutPort declaration
    // <rtc-template block="outport_declare">
    protected TimedLong m_OutP1_val;
    protected DataRef<TimedLong> m_OutP1;
    /*!
     * OutPort1�̊T�v
     * - Type: OutPort1�̃f�[�^�̌^
     * - Number: OutPort1�̃f�[�^�̐�
     * - Semantics: OutPort1�̃f�[�^�̈Ӗ�
     * - Unit: OutPort1�̃f�[�^�̒P��
     * - Frequency: OutPort1�̃f�[�^�̔����p�x
     * - Operation Cycle: OutPort1�̃f�[�^�̏�������
     */
    protected OutPort<TimedLong> m_OutP1Out;

    protected TimedFloat m_OutP2_val;
    protected DataRef<TimedFloat> m_OutP2;
    /*!
     * OutPort2�̊T�v
     * - Type: OutPort2�̃f�[�^�̌^
     * - Number: OutPort2�̃f�[�^�̐�
     * - Semantics: OutPort2�̃f�[�^�̈Ӗ�
     * - Unit: OutPort2�̃f�[�^�̒P��
     * - Frequency: OutPort2�̃f�[�^�̔����p�x
     * - Operation Cycle: OutPort2�̃f�[�^�̏�������
     */
    protected OutPort<TimedFloat> m_OutP2Out;

    
    // </rtc-template>

    // CORBA Port declaration
    // <rtc-template block="corbaport_declare">
    /*!
     * ServicePort1�̊T�v
     * Interface: ServicePort1�̃C���^�[�t�F�[�X�̊T�v
     */
    protected CorbaPort m_svPortPort;
    /*!
     * ServicePort2�̊T�v
     * Interface: ServicePort2�̃C���^�[�t�F�[�X�̊T�v
     */
    protected CorbaPort m_cmPortPort;
    
    // </rtc-template>

    // Service declaration
    // <rtc-template block="service_declare">
    /*!
     * ServiceIF1�̊T�v����
     * - Argument:      ServiceIF1�̈���
     * - Return Value:  ServiceIF1�̕Ԓl
     * - Exception:     ServiceIF1�̗�O
     * - PreCondition:  ServiceIF1�̎��O����
     * - PostCondition: ServiceIF1�̎������
     */
    protected MyServiceSVC_impl m_acc = new MyServiceSVC_impl();
    
    // </rtc-template>

    // Consumer declaration
    // <rtc-template block="consumer_declare">
    protected CorbaConsumer<DAQService> m_rateBase = new CorbaConsumer<DAQService>(DAQService.class);
    /*!
     * ServiceIF2�̊T�v����
     * - Argument:      ServiceIF2�̈���
     * - Return Value:  ServiceIF2�̕Ԓl
     * - Exception:     ServiceIF2�̗�O
     * - PreCondition:  ServiceIF2�̎��O����
     * - PostCondition: ServiceIF2�̎������
     */
    protected DAQService m_rate;
    
    // </rtc-template>


}
