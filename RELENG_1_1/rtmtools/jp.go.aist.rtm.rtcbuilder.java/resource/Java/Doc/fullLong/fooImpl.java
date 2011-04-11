// -*- Java -*-
/*!
 * @file  fooImpl.java
 * @brief MDesc
 * @date  $Date$
 *
 * @author Noriaki Ando <n-ando@aist.go.jp>3456789412345678951234567896123456789
 * 7123456789812345
 *
 * Copyright (C) 2006-2008 ライセンス1234567890123456789012345678901234567890123
 * 4567890
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
 * 本コンポーネントの概要説明123456789012345678901234567890123456789012345678901
 * 2345678901234567890
 *
 * 本コンポーネントの入出力12345678901234567890123456789012345678901234567890123
 * 45678901234567890
 *
 * 本コンポーネントのアルゴリズムなど1234567890123456789012345678901234567890123
 * 456789012345678901234567890
 *
 * 参考文献の情報123456789012345678901234567890123456789012345678901234567890123
 * 4567890
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
     * on_initialize概要説明1234567890123456789012345678901234567890123456789012
     * 345678901234567890
     *
     * The initialize action (on CREATED->ALIVE transition)
     * formaer rtc_init_entry() 
     *
     * @return RTC::ReturnCode_t
     * 
     * @pre on_initialize事前条件12345678901234567890123456789012345678901234567
     * 89012345678901234567890
     * @post on_initialize事後条件1234567890123456789012345678901234567890123456
     * 789012345678901234567890
     * 
     */
    @Override
    protected ReturnCode_t onInitialize() {
        bindParameter("int_param0", m_int_param0, "0");
        bindParameter("int_param1", m_int_param1, "1");
        bindParameter("double_param0", m_double_param0, "0.11");
        bindParameter("str_param0", m_str_param0, "日本語");
        bindParameter("str_param1", m_str_param1, "dara");
        return ReturnCode_t.RTC_OK;
    }

    /***
     * on_finalize概要説明123456789012345678901234567890123456789012345678901234
     * 5678901234567890
     *
     * The finalize action (on ALIVE->END transition)
     * formaer rtc_exiting_entry()
     *
     * @return RTC::ReturnCode_t
     * 
     * @pre on_finalize事前条件1234567890123456789012345678901234567890123456789
     * 012345678901234567890
     * @post on_finalize事後条件123456789012345678901234567890123456789012345678
     * 9012345678901234567890
     * 
     */
//    @Override
//    protected ReturnCode_t onFinalize() {
//        return super.onFinalize();
//    }

    /***
     * on_startup概要説明1234567890123456789012345678901234567890123456789012345
     * 678901234567890
     *
     * The startup action when ExecutionContext startup
     * former rtc_starting_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * @pre on_startup事前条件12345678901234567890123456789012345678901234567890
     * 12345678901234567890
     * @post on_startup事後条件1234567890123456789012345678901234567890123456789
     * 012345678901234567890
     * 
     */
//    @Override
//    protected ReturnCode_t onStartup(int ec_id) {
//        return super.onStartup(ec_id);
//    }

    /***
     * on_shutdown概要説明123456789012345678901234567890123456789012345678901234
     * 5678901234567890
     *
     * The shutdown action when ExecutionContext stop
     * former rtc_stopping_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * @pre on_shutdown事前条件1234567890123456789012345678901234567890123456789
     * 012345678901234567890
     * @post on_shutdown事後条件123456789012345678901234567890123456789012345678
     * 9012345678901234567890
     * 
     */
//    @Override
//    protected ReturnCode_t onShutdown(int ec_id) {
//        return super.onShutdown(ec_id);
//    }

    /***
     * on_activated概要説明12345678901234567890123456789012345678901234567890123
     * 45678901234567890
     *
     * The activated action (Active state entry action)
     * former rtc_active_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * @pre on_activated事前条件123456789012345678901234567890123456789012345678
     * 9012345678901234567890
     * @post on_activated事後条件12345678901234567890123456789012345678901234567
     * 89012345678901234567890
     * 
     */
//    @Override
//    protected ReturnCode_t onActivated(int ec_id) {
//        return super.onActivated(ec_id);
//    }

    /***
     * on_deactivated概要説明123456789012345678901234567890123456789012345678901
     * 2345678901234567890
     *
     * The deactivated action (Active state exit action)
     * former rtc_active_exit()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * @pre on_deactivated事前条件1234567890123456789012345678901234567890123456
     * 789012345678901234567890
     * @post on_deactivated事後条件123456789012345678901234567890123456789012345
     * 6789012345678901234567890
     * 
     */
//    @Override
//    protected ReturnCode_t onDeactivated(int ec_id) {
//        return super.onDeactivated(ec_id);
//    }

    /***
     * on_execute概要説明1234567890123456789012345678901234567890123456789012345
     * 678901234567890
     *
     * The execution action that is invoked periodically
     * former rtc_active_do()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * @pre on_execute事前条件12345678901234567890123456789012345678901234567890
     * 12345678901234567890
     * @post on_execute事後条件1234567890123456789012345678901234567890123456789
     * 012345678901234567890
     * 
     */
//    @Override
//    protected ReturnCode_t onExecute(int ec_id) {
//        return super.onExecute(ec_id);
//    }

    /***
     * on_aborting概要説明123456789012345678901234567890123456789012345678901234
     * 5678901234567890
     *
     * The aborting action when main logic error occurred.
     * former rtc_aborting_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * @pre on_aborting事前条件1234567890123456789012345678901234567890123456789
     * 012345678901234567890
     * @post on_aborting事後条件123456789012345678901234567890123456789012345678
     * 9012345678901234567890
     * 
     */
//  @Override
//  public ReturnCode_t onAborting(int ec_id) {
//      return super.onAborting(ec_id);
//  }

    /***
     * on_error概要説明123456789012345678901234567890123456789012345678901234567
     * 8901234567890
     *
     * The error action in ERROR state
     * former rtc_error_do()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * @pre on_error事前条件1234567890123456789012345678901234567890123456789012
     * 345678901234567890
     * @post on_error事後条件123456789012345678901234567890123456789012345678901
     * 2345678901234567890
     * 
     */
//    @Override
//    public ReturnCode_t onError(int ec_id) {
//        return super.onError(ec_id);
//    }

    /***
     * on_reset概要説明123456789012345678901234567890123456789012345678901234567
     * 8901234567890
     *
     * The reset action that is invoked resetting
     * This is same but different the former rtc_init_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * @pre on_reset事前条件1234567890123456789012345678901234567890123456789012
     * 345678901234567890
     * @post on_reset事後条件123456789012345678901234567890123456789012345678901
     * 2345678901234567890
     * 
     */
//    @Override
//    protected ReturnCode_t onReset(int ec_id) {
//        return super.onReset(ec_id);
//    }

    /***
     * on_state_update概要説明12345678901234567890123456789012345678901234567890
     * 12345678901234567890
     *
     * The state update action that is invoked after onExecute() action
     * no corresponding operation exists in OpenRTm-aist-0.2.0
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * @pre on_state_update事前条件123456789012345678901234567890123456789012345
     * 6789012345678901234567890
     * @post on_state_update事後条件12345678901234567890123456789012345678901234
     * 56789012345678901234567890
     * 
     */
//    @Override
//    protected ReturnCode_t onStateUpdate(int ec_id) {
//        return super.onStateUpdate(ec_id);
//    }

    /***
     * on_rate_changed概要説明12345678901234567890123456789012345678901234567890
     * 12345678901234567890
     *
     * The action that is invoked when execution context's rate is changed
     * no corresponding operation exists in OpenRTm-aist-0.2.0
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * @pre on_rate_changed事前条件123456789012345678901234567890123456789012345
     * 6789012345678901234567890
     * @post on_rate_changed事後条件12345678901234567890123456789012345678901234
     * 56789012345678901234567890
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
     * Config1の概要123456789012345678901234567890123456789012345678901234567890
     * 1234567890
     * - Name: Config1の名前 int_param0
     * - DefaultValue: 0
     * - Unit: Config1の単位1234567890123456789012345678901234567890123456789012
     *         345678901234567890
     * - Range: Config1の範囲123456789012345678901234567890123456789012345678901
     *          2345678901234567890
     * - Constraint: Config1の制約条件123456789012345678901234567890123456789012
     *               3456789012345678901234567890
     */
    protected IntegerHolder m_int_param0 = new IntegerHolder();
    /*!
     * Config2の概要123456789012345678901234567890123456789012345678901234567890
     * 1234567890
     * - Name: Config2の名前 int_param1
     * - DefaultValue: 1
     * - Unit: Config2の単位1234567890123456789012345678901234567890123456789012
     *         345678901234567890
     * - Range: Config2の範囲123456789012345678901234567890123456789012345678901
     *          2345678901234567890
     * - Constraint: Config2の制約条件123456789012345678901234567890123456789012
     *               3456789012345678901234567890
     */
    protected IntegerHolder m_int_param1 = new IntegerHolder();
    /*!
     * Config3の概要123456789012345678901234567890123456789012345678901234567890
     * 1234567890
     * - Name: Config3の名前 double_param0
     * - DefaultValue: 0.11
     * - Unit: Config3の単位1234567890123456789012345678901234567890123456789012
     *         345678901234567890
     * - Range: Config3の範囲123456789012345678901234567890123456789012345678901
     *          2345678901234567890
     * - Constraint: Config3の制約条件123456789012345678901234567890123456789012
     *               3456789012345678901234567890
     */
    protected DoubleHolder m_double_param0 = new DoubleHolder();
    /*!
     * Config4の概要123456789012345678901234567890123456789012345678901234567890
     * 1234567890
     * - Name: Config4の名前 str_param0
     * - DefaultValue: 日本語
     * - Unit: Config4の単位1234567890123456789012345678901234567890123456789012
     *         345678901234567890
     * - Range: Config4の範囲123456789012345678901234567890123456789012345678901
     *          2345678901234567890
     * - Constraint: Config4の制約条件123456789012345678901234567890123456789012
     *               3456789012345678901234567890
     */
    protected StringHolder m_str_param0 = new StringHolder();
    /*!
     * Config5の概要123456789012345678901234567890123456789012345678901234567890
     * 1234567890
     * - Name: Config5の名前 str_param1
     * - DefaultValue: dara
     * - Unit: Config5の単位1234567890123456789012345678901234567890123456789012
     *         345678901234567890
     * - Range: Config5の範囲123456789012345678901234567890123456789012345678901
     *          2345678901234567890
     * - Constraint: Config5の制約条件123456789012345678901234567890123456789012
     *               3456789012345678901234567890
     */
    protected StringHolder m_str_param1 = new StringHolder();
	// </rtc-template>

    // DataInPort declaration
    // <rtc-template block="inport_declare">
    protected TimedShort m_InP1_val;
    protected DataRef<TimedShort> m_InP1;
    /*!
     * InPort1の概要123456789012345678901234567890123456789012345678901234567890
     * 1234567890
     * - Type: InPort1のデータの型1234567890123456789012345678901234567890123456
     *         789012345678901234567890
     * - Number: InPort1のデータの数12345678901234567890123456789012345678901234
     *           56789012345678901234567890
     * - Semantics: InPort1のデータの意味123456789012345678901234567890123456789
     *              0123456789012345678901234567890
     * - Unit: InPort1のデータの単位12345678901234567890123456789012345678901234
     *         56789012345678901234567890
     * - Frequency: InPort1のデータの発生頻度12345678901234567890123456789012345
     *              67890123456789012345678901234567890
     * - Operation Cycle: InPort1のデータの処理周期12345678901234567890123456789
     *                    01234567890123456789012345678901234567890
     */
    protected InPort<TimedShort> m_InP1In;

    protected TimedLong m_InP2_val;
    protected DataRef<TimedLong> m_InP2;
    /*!
     * InPort2の概要123456789012345678901234567890123456789012345678901234567890
     * 1234567890
     * - Type: InPort2のデータの型1234567890123456789012345678901234567890123456
     *         789012345678901234567890
     * - Number: InPort2のデータの数12345678901234567890123456789012345678901234
     *           56789012345678901234567890
     * - Semantics: InPort2のデータの意味123456789012345678901234567890123456789
     *              0123456789012345678901234567890
     * - Unit: InPort2のデータの単位12345678901234567890123456789012345678901234
     *         56789012345678901234567890
     * - Frequency: InPort2のデータの発生頻度12345678901234567890123456789012345
     *              67890123456789012345678901234567890
     * - Operation Cycle: InPort2のデータの処理周期12345678901234567890123456789
     *                    01234567890123456789012345678901234567890
     */
    protected InPort<TimedLong> m_InP2In;

    
    // </rtc-template>

    // DataOutPort declaration
    // <rtc-template block="outport_declare">
    protected TimedLong m_OutP1_val;
    protected DataRef<TimedLong> m_OutP1;
    /*!
     * OutPort1の概要12345678901234567890123456789012345678901234567890123456789
     * 01234567890
     * - Type: OutPort1のデータの型123456789012345678901234567890123456789012345
     *         6789012345678901234567890
     * - Number: OutPort1のデータの数1234567890123456789012345678901234567890123
     *           456789012345678901234567890
     * - Semantics: OutPort1のデータの意味12345678901234567890123456789012345678
     *              90123456789012345678901234567890
     * - Unit: OutPort1のデータの単位1234567890123456789012345678901234567890123
     *         456789012345678901234567890
     * - Frequency: OutPort1のデータの発生頻度1234567890123456789012345678901234
     *              567890123456789012345678901234567890
     * - Operation Cycle: OutPort1のデータの処理周期1234567890123456789012345678
     *                    901234567890123456789012345678901234567890
     */
    protected OutPort<TimedLong> m_OutP1Out;

    protected TimedFloat m_OutP2_val;
    protected DataRef<TimedFloat> m_OutP2;
    /*!
     * OutPort2の概要12345678901234567890123456789012345678901234567890123456789
     * 01234567890
     * - Type: OutPort2のデータの型123456789012345678901234567890123456789012345
     *         6789012345678901234567890
     * - Number: OutPort2のデータの数1234567890123456789012345678901234567890123
     *           456789012345678901234567890
     * - Semantics: OutPort2のデータの意味12345678901234567890123456789012345678
     *              90123456789012345678901234567890
     * - Unit: OutPort2のデータの単位1234567890123456789012345678901234567890123
     *         456789012345678901234567890
     * - Frequency: OutPort2のデータの発生頻度1234567890123456789012345678901234
     *              567890123456789012345678901234567890
     * - Operation Cycle: OutPort2のデータの処理周期1234567890123456789012345678
     *                    901234567890123456789012345678901234567890
     */
    protected OutPort<TimedFloat> m_OutP2Out;

    
    // </rtc-template>

    // CORBA Port declaration
    // <rtc-template block="corbaport_declare">
    /*!
     * ServicePort1の概要1234567890123456789012345678901234567890123456789012345
     * 678901234567890
     * Interface: ServicePort1のインターフェースの概要12345678901234567890123456
     *            78901234567890123456789012345678901234567890
     */
    protected CorbaPort m_svPortPort;
    /*!
     * ServicePort2の概要1234567890123456789012345678901234567890123456789012345
     * 678901234567890
     * Interface: ServicePort2のインターフェースの概要12345678901234567890123456
     *            78901234567890123456789012345678901234567890
     */
    protected CorbaPort m_cmPortPort;
    
    // </rtc-template>

    // Service declaration
    // <rtc-template block="service_declare">
    /*!
     * ServiceIF1の概要説明12345678901234567890123456789012345678901234567890123
     * 45678901234567890
     * - Argument:      ServiceIF1の引数1234567890123456789012345678901234567890
     *                  123456789012345678901234567890
     * - Return Value:  ServiceIF1の返値1234567890123456789012345678901234567890
     *                  123456789012345678901234567890
     * - Exception:     ServiceIF1の例外1234567890123456789012345678901234567890
     *                  123456789012345678901234567890
     * - PreCondition:  ServiceIF1の事前条件123456789012345678901234567890123456
     *                  7890123456789012345678901234567890
     * - PostCondition: ServiceIF1の事後条件123456789012345678901234567890123456
     *                  7890123456789012345678901234567890
     */
    protected MyServiceSVC_impl m_acc = new MyServiceSVC_impl();
    
    // </rtc-template>

    // Consumer declaration
    // <rtc-template block="consumer_declare">
    protected CorbaConsumer<DAQService> m_rateBase = new CorbaConsumer<DAQService>(DAQService.class);
    /*!
     * ServiceIF2の概要説明12345678901234567890123456789012345678901234567890123
     * 45678901234567890
     * - Argument:      ServiceIF2の引数1234567890123456789012345678901234567890
     *                  123456789012345678901234567890
     * - Return Value:  ServiceIF2の返値1234567890123456789012345678901234567890
     *                  123456789012345678901234567890
     * - Exception:     ServiceIF2の例外1234567890123456789012345678901234567890
     *                  123456789012345678901234567890
     * - PreCondition:  ServiceIF2の事前条件123456789012345678901234567890123456
     *                  7890123456789012345678901234567890
     * - PostCondition: ServiceIF2の事後条件123456789012345678901234567890123456
     *                  7890123456789012345678901234567890
     */
    protected DAQService m_rate;
    
    // </rtc-template>


}
