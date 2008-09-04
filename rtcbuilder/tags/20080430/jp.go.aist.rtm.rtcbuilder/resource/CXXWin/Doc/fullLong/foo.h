// -*- C++ -*-
/*!
 * @file  foo.h
 * @brief MDesc
 * @date  $Date$
 *
 * @author Noriaki Ando <n-ando@aist.go.jp>3456789412345678951234567896123456789
 * 7123456789812345
 *
 * Copyright (C) 2006-2008 ���C�Z���X1234567890123456789012345678901234567890123
 * 4567890
 *
 * $Id$
 */

#ifndef FOO_H
#define FOO_H

#include <rtm/Manager.h>
#include <rtm/DataFlowComponentBase.h>
#include <rtm/CorbaPort.h>
#include <rtm/DataInPort.h>
#include <rtm/DataOutPort.h>
#include <rtm/idl/BasicDataTypeSkel.h>

// Service implementation headers
// <rtc-template block="service_impl_h">
#include "MyServiceSVC_impl.h"

// </rtc-template>

// Service Consumer stub headers
// <rtc-template block="consumer_stub_h">
#include "DAQServiceStub.h"

// </rtc-template>

using namespace RTC;

/*!
 * @class foo
 * @brief MDesc
 *
 * �{�R���|�[�l���g�̊T�v����123456789012345678901234567890123456789012345678901
 * 2345678901234567890
 *
 * �{�R���|�[�l���g�̓��o��12345678901234567890123456789012345678901234567890123
 * 45678901234567890
 *
 * �{�R���|�[�l���g�̃A���S���Y���Ȃ�1234567890123456789012345678901234567890123
 * 456789012345678901234567890
 *
 * �Q�l�����̏��123456789012345678901234567890123456789012345678901234567890123
 * 4567890
 *
 */
class foo
  : public RTC::DataFlowComponentBase
{
 public:
  /*!
   * @brief constructor
   * @param manager Maneger Object
   */
  foo(RTC::Manager* manager);

  /*!
   * @brief destructor
   */
  ~foo();

  // <rtc-template block="public_attribute">
  
  // </rtc-template>

  // <rtc-template block="public_operation">
  
  // </rtc-template>

  /*!
   * on_initialize�T�v����123456789012345678901234567890123456789012345678901234
   * 5678901234567890
   *
   * The initialize action (on CREATED->ALIVE transition)
   * formaer rtc_init_entry() 
   *
   * @return RTC::ReturnCode_t
   * 
   * @pre on_initialize���O����1234567890123456789012345678901234567890123456789
   * 012345678901234567890
   * @post on_initialize�������123456789012345678901234567890123456789012345678
   * 9012345678901234567890
   * 
   */
   virtual RTC::ReturnCode_t onInitialize();

  /***
   * on_finalize�T�v����12345678901234567890123456789012345678901234567890123456
   * 78901234567890
   *
   * The finalize action (on ALIVE->END transition)
   * formaer rtc_exiting_entry()
   *
   * @return RTC::ReturnCode_t
   * 
   * @pre on_finalize���O����123456789012345678901234567890123456789012345678901
   * 2345678901234567890
   * @post on_finalize�������12345678901234567890123456789012345678901234567890
   * 12345678901234567890
   * 
   */
  // virtual RTC::ReturnCode_t onFinalize();

  /***
   * on_startup�T�v����123456789012345678901234567890123456789012345678901234567
   * 8901234567890
   *
   * The startup action when ExecutionContext startup
   * former rtc_starting_entry()
   *
   * @param ec_id target ExecutionContext Id
   *
   * @return RTC::ReturnCode_t
   * 
   * @pre on_startup���O����1234567890123456789012345678901234567890123456789012
   * 345678901234567890
   * @post on_startup�������123456789012345678901234567890123456789012345678901
   * 2345678901234567890
   * 
   */
  // virtual RTC::ReturnCode_t onStartup(RTC::UniqueId ec_id);

  /***
   * on_shutdown�T�v����12345678901234567890123456789012345678901234567890123456
   * 78901234567890
   *
   * The shutdown action when ExecutionContext stop
   * former rtc_stopping_entry()
   *
   * @param ec_id target ExecutionContext Id
   *
   * @return RTC::ReturnCode_t
   * 
   * @pre on_shutdown���O����123456789012345678901234567890123456789012345678901
   * 2345678901234567890
   * @post on_shutdown�������12345678901234567890123456789012345678901234567890
   * 12345678901234567890
   * 
   */
  // virtual RTC::ReturnCode_t onShutdown(RTC::UniqueId ec_id);

  /***
   * on_activated�T�v����1234567890123456789012345678901234567890123456789012345
   * 678901234567890
   *
   * The activated action (Active state entry action)
   * former rtc_active_entry()
   *
   * @param ec_id target ExecutionContext Id
   *
   * @return RTC::ReturnCode_t
   * 
   * @pre on_activated���O����12345678901234567890123456789012345678901234567890
   * 12345678901234567890
   * @post on_activated�������1234567890123456789012345678901234567890123456789
   * 012345678901234567890
   * 
   */
  // virtual RTC::ReturnCode_t onActivated(RTC::UniqueId ec_id);

  /***
   * on_deactivated�T�v����12345678901234567890123456789012345678901234567890123
   * 45678901234567890
   *
   * The deactivated action (Active state exit action)
   * former rtc_active_exit()
   *
   * @param ec_id target ExecutionContext Id
   *
   * @return RTC::ReturnCode_t
   * 
   * @pre on_deactivated���O����123456789012345678901234567890123456789012345678
   * 9012345678901234567890
   * @post on_deactivated�������12345678901234567890123456789012345678901234567
   * 89012345678901234567890
   * 
   */
  // virtual RTC::ReturnCode_t onDeactivated(RTC::UniqueId ec_id);

  /***
   * on_execute�T�v����123456789012345678901234567890123456789012345678901234567
   * 8901234567890
   *
   * The execution action that is invoked periodically
   * former rtc_active_do()
   *
   * @param ec_id target ExecutionContext Id
   *
   * @return RTC::ReturnCode_t
   * 
   * @pre on_execute���O����1234567890123456789012345678901234567890123456789012
   * 345678901234567890
   * @post on_execute�������123456789012345678901234567890123456789012345678901
   * 2345678901234567890
   * 
   */
  // virtual RTC::ReturnCode_t onExecute(RTC::UniqueId ec_id);

  /***
   * on_aborting�T�v����12345678901234567890123456789012345678901234567890123456
   * 78901234567890
   *
   * The aborting action when main logic error occurred.
   * former rtc_aborting_entry()
   *
   * @param ec_id target ExecutionContext Id
   *
   * @return RTC::ReturnCode_t
   * 
   * @pre on_aborting���O����123456789012345678901234567890123456789012345678901
   * 2345678901234567890
   * @post on_aborting�������12345678901234567890123456789012345678901234567890
   * 12345678901234567890
   * 
   */
  // virtual RTC::ReturnCode_t onAborting(RTC::UniqueId ec_id);

  /***
   * on_error�T�v����12345678901234567890123456789012345678901234567890123456789
   * 01234567890
   *
   * The error action in ERROR state
   * former rtc_error_do()
   *
   * @param ec_id target ExecutionContext Id
   *
   * @return RTC::ReturnCode_t
   * 
   * @pre on_error���O����123456789012345678901234567890123456789012345678901234
   * 5678901234567890
   * @post on_error�������12345678901234567890123456789012345678901234567890123
   * 45678901234567890
   * 
   */
  // virtual RTC::ReturnCode_t onError(RTC::UniqueId ec_id);

  /***
   * on_reset�T�v����12345678901234567890123456789012345678901234567890123456789
   * 01234567890
   *
   * The reset action that is invoked resetting
   * This is same but different the former rtc_init_entry()
   *
   * @param ec_id target ExecutionContext Id
   *
   * @return RTC::ReturnCode_t
   * 
   * @pre on_reset���O����123456789012345678901234567890123456789012345678901234
   * 5678901234567890
   * @post on_reset�������12345678901234567890123456789012345678901234567890123
   * 45678901234567890
   * 
   */
  // virtual RTC::ReturnCode_t onReset(RTC::UniqueId ec_id);
  
  /***
   * on_state_update�T�v����1234567890123456789012345678901234567890123456789012
   * 345678901234567890
   *
   * The state update action that is invoked after onExecute() action
   * no corresponding operation exists in OpenRTm-aist-0.2.0
   *
   * @param ec_id target ExecutionContext Id
   *
   * @return RTC::ReturnCode_t
   * 
   * @pre on_state_update���O����12345678901234567890123456789012345678901234567
   * 89012345678901234567890
   * @post on_state_update�������1234567890123456789012345678901234567890123456
   * 789012345678901234567890
   * 
   */
  // virtual RTC::ReturnCode_t onStateUpdate(RTC::UniqueId ec_id);

  /***
   * on_rate_changed�T�v����1234567890123456789012345678901234567890123456789012
   * 345678901234567890
   *
   * The action that is invoked when execution context's rate is changed
   * no corresponding operation exists in OpenRTm-aist-0.2.0
   *
   * @param ec_id target ExecutionContext Id
   *
   * @return RTC::ReturnCode_t
   * 
   * @pre on_rate_changed���O����12345678901234567890123456789012345678901234567
   * 89012345678901234567890
   * @post on_rate_changed�������1234567890123456789012345678901234567890123456
   * 789012345678901234567890
   * 
   */
  // virtual RTC::ReturnCode_t onRateChanged(RTC::UniqueId ec_id);


 protected:
  // <rtc-template block="protected_attribute">
  
  // </rtc-template>

  // <rtc-template block="protected_operation">
  
  // </rtc-template>

  // Configuration variable declaration
  // <rtc-template block="config_declare">
  /*!
   * Config1�̊T�v12345678901234567890123456789012345678901234567890123456789012
   * 34567890
   * - Name: Config1�̖��O int_param0
   * - DefaultValue: 0
   * - Unit: Config1�̒P��123456789012345678901234567890123456789012345678901234
   *         5678901234567890
   * - Range: Config1�͈̔�12345678901234567890123456789012345678901234567890123
   *          45678901234567890
   * - Constraint: Config1�̐������12345678901234567890123456789012345678901234
   *               56789012345678901234567890
   */
  int m_int_param0;
  /*!
   * Config2�̊T�v12345678901234567890123456789012345678901234567890123456789012
   * 34567890
   * - Name: Config2�̖��O int_param1
   * - DefaultValue: 1
   * - Unit: Config2�̒P��123456789012345678901234567890123456789012345678901234
   *         5678901234567890
   * - Range: Config2�͈̔�12345678901234567890123456789012345678901234567890123
   *          45678901234567890
   * - Constraint: Config2�̐������12345678901234567890123456789012345678901234
   *               56789012345678901234567890
   */
  int m_int_param1;
  /*!
   * Config3�̊T�v12345678901234567890123456789012345678901234567890123456789012
   * 34567890
   * - Name: Config3�̖��O double_param0
   * - DefaultValue: 0.11
   * - Unit: Config3�̒P��123456789012345678901234567890123456789012345678901234
   *         5678901234567890
   * - Range: Config3�͈̔�12345678901234567890123456789012345678901234567890123
   *          45678901234567890
   * - Constraint: Config3�̐������12345678901234567890123456789012345678901234
   *               56789012345678901234567890
   */
  double m_double_param0;
  /*!
   * Config4�̊T�v12345678901234567890123456789012345678901234567890123456789012
   * 34567890
   * - Name: Config4�̖��O str_param0
   * - DefaultValue: hoge
   * - Unit: Config4�̒P��123456789012345678901234567890123456789012345678901234
   *         5678901234567890
   * - Range: Config4�͈̔�12345678901234567890123456789012345678901234567890123
   *          45678901234567890
   * - Constraint: Config4�̐������12345678901234567890123456789012345678901234
   *               56789012345678901234567890
   */
  std::string m_str_param0;
  /*!
   * Config5�̊T�v12345678901234567890123456789012345678901234567890123456789012
   * 34567890
   * - Name: Config5�̖��O str_param1
   * - DefaultValue: dara
   * - Unit: Config5�̒P��123456789012345678901234567890123456789012345678901234
   *         5678901234567890
   * - Range: Config5�͈̔�12345678901234567890123456789012345678901234567890123
   *          45678901234567890
   * - Constraint: Config5�̐������12345678901234567890123456789012345678901234
   *               56789012345678901234567890
   */
  std::string m_str_param1;
  // </rtc-template>

  // DataInPort declaration
  // <rtc-template block="inport_declare">
  TimedShort m_InP1;
  /*!
   * InPort1�̊T�v12345678901234567890123456789012345678901234567890123456789012
   * 34567890
   * - Type: InPort1�̃f�[�^�̌^123456789012345678901234567890123456789012345678
   *         9012345678901234567890
   * - Number: InPort1�̃f�[�^�̐�1234567890123456789012345678901234567890123456
   *           789012345678901234567890
   * - Semantics: InPort1�̃f�[�^�̈Ӗ�12345678901234567890123456789012345678901
   *              23456789012345678901234567890
   * - Unit: InPort1�̃f�[�^�̒P��1234567890123456789012345678901234567890123456
   *         789012345678901234567890
   * - Frequency: InPort1�̃f�[�^�̔����p�x1234567890123456789012345678901234567
   *              890123456789012345678901234567890
   * - Operation Cycle: InPort1�̃f�[�^�̏�������1234567890123456789012345678901
   *                    234567890123456789012345678901234567890
   */
  InPort<TimedShort> m_InName1In;
  TimedLong m_InP2;
  /*!
   * InPort2�̊T�v12345678901234567890123456789012345678901234567890123456789012
   * 34567890
   * - Type: InPort2�̃f�[�^�̌^123456789012345678901234567890123456789012345678
   *         9012345678901234567890
   * - Number: InPort2�̃f�[�^�̐�1234567890123456789012345678901234567890123456
   *           789012345678901234567890
   * - Semantics: InPort2�̃f�[�^�̈Ӗ�12345678901234567890123456789012345678901
   *              23456789012345678901234567890
   * - Unit: InPort2�̃f�[�^�̒P��1234567890123456789012345678901234567890123456
   *         789012345678901234567890
   * - Frequency: InPort2�̃f�[�^�̔����p�x1234567890123456789012345678901234567
   *              890123456789012345678901234567890
   * - Operation Cycle: InPort2�̃f�[�^�̏�������1234567890123456789012345678901
   *                    234567890123456789012345678901234567890
   */
  InPort<TimedLong> m_InNm2In;
  
  // </rtc-template>


  // DataOutPort declaration
  // <rtc-template block="outport_declare">
  TimedLong m_OutP1;
  /*!
   * OutPort1�̊T�v1234567890123456789012345678901234567890123456789012345678901
   * 234567890
   * - Type: OutPort1�̃f�[�^�̌^12345678901234567890123456789012345678901234567
   *         89012345678901234567890
   * - Number: OutPort1�̃f�[�^�̐�123456789012345678901234567890123456789012345
   *           6789012345678901234567890
   * - Semantics: OutPort1�̃f�[�^�̈Ӗ�1234567890123456789012345678901234567890
   *              123456789012345678901234567890
   * - Unit: OutPort1�̃f�[�^�̒P��123456789012345678901234567890123456789012345
   *         6789012345678901234567890
   * - Frequency: OutPort1�̃f�[�^�̔����p�x123456789012345678901234567890123456
   *              7890123456789012345678901234567890
   * - Operation Cycle: OutPort1�̃f�[�^�̏�������123456789012345678901234567890
   *                    1234567890123456789012345678901234567890
   */
  OutPort<TimedLong> m_OutName1Out;
  TimedFloat m_OutP2;
  /*!
   * OutPort2�̊T�v1234567890123456789012345678901234567890123456789012345678901
   * 234567890
   * - Type: OutPort2�̃f�[�^�̌^12345678901234567890123456789012345678901234567
   *         89012345678901234567890
   * - Number: OutPort2�̃f�[�^�̐�123456789012345678901234567890123456789012345
   *           6789012345678901234567890
   * - Semantics: OutPort2�̃f�[�^�̈Ӗ�1234567890123456789012345678901234567890
   *              123456789012345678901234567890
   * - Unit: OutPort2�̃f�[�^�̒P��123456789012345678901234567890123456789012345
   *         6789012345678901234567890
   * - Frequency: OutPort2�̃f�[�^�̔����p�x123456789012345678901234567890123456
   *              7890123456789012345678901234567890
   * - Operation Cycle: OutPort2�̃f�[�^�̏�������123456789012345678901234567890
   *                    1234567890123456789012345678901234567890
   */
  OutPort<TimedFloat> m_OutNme2Out;
  
  // </rtc-template>

  // CORBA Port declaration
  // <rtc-template block="corbaport_declare">
  /*!
   * ServicePort1�̊T�v123456789012345678901234567890123456789012345678901234567
   * 8901234567890
   * Interface: ServicePort1�̃C���^�[�t�F�[�X�̊T�v1234567890123456789012345678
   *            901234567890123456789012345678901234567890
   */
  RTC::CorbaPort m_svPortPort;
  /*!
   * ServicePort2�̊T�v123456789012345678901234567890123456789012345678901234567
   * 8901234567890
   * Interface: ServicePort2�̃C���^�[�t�F�[�X�̊T�v1234567890123456789012345678
   *            901234567890123456789012345678901234567890
   */
  RTC::CorbaPort m_cmPortPort;
  
  // </rtc-template>

  // Service declaration
  // <rtc-template block="service_declare">
  /*!
   * ServiceIF1�̊T�v����1234567890123456789012345678901234567890123456789012345
   * 678901234567890
   * - Argument:      ServiceIF1�̈���123456789012345678901234567890123456789012
   *                  3456789012345678901234567890
   * - Return Value:  ServiceIF1�̕Ԓl123456789012345678901234567890123456789012
   *                  3456789012345678901234567890
   * - Exception:     ServiceIF1�̗�O123456789012345678901234567890123456789012
   *                  3456789012345678901234567890
   * - PreCondition:  ServiceIF1�̎��O����12345678901234567890123456789012345678
   *                  90123456789012345678901234567890
   * - PostCondition: ServiceIF1�̎������12345678901234567890123456789012345678
   *                  90123456789012345678901234567890
   */
  MyServiceSVC_impl m_acc;
  
  // </rtc-template>

  // Consumer declaration
  // <rtc-template block="consumer_declare">
  /*!
   * ServiceIF2�̊T�v����1234567890123456789012345678901234567890123456789012345
   * 678901234567890
   * - Argument:      ServiceIF2�̈���123456789012345678901234567890123456789012
   *                  3456789012345678901234567890
   * - Return Value:  ServiceIF2�̕Ԓl123456789012345678901234567890123456789012
   *                  3456789012345678901234567890
   * - Exception:     ServiceIF2�̗�O123456789012345678901234567890123456789012
   *                  3456789012345678901234567890
   * - PreCondition:  ServiceIF2�̎��O����12345678901234567890123456789012345678
   *                  90123456789012345678901234567890
   * - PostCondition: ServiceIF2�̎������12345678901234567890123456789012345678
   *                  90123456789012345678901234567890
   */
  RTC::CorbaConsumer<DAQService> m_rate;
  
  // </rtc-template>

 private:
  int dummy;
  // <rtc-template block="private_attribute">
  
  // </rtc-template>

  // <rtc-template block="private_operation">
  
  // </rtc-template>

};


extern "C"
{
  void fooInit(RTC::Manager* manager);
};

#endif // FOO_H
