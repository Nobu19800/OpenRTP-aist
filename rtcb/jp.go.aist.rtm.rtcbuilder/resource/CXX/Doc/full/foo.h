// -*- C++ -*-
/*!
 * @file  foo.h
 * @brief MDesc
 * @date  $Date$
 *
 * @author Noriaki Ando <n-ando@aist.go.jp>
 *
 * Copyright (C) 2006-2008 ���C�Z���X
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
 * �{�R���|�[�l���g�̊T�v����
 *
 * �{�R���|�[�l���g�̓��o��
 *
 * �{�R���|�[�l���g�̃A���S���Y���Ȃ�
 *
 * �Q�l�����̏��
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
   virtual RTC::ReturnCode_t onInitialize();

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
  // virtual RTC::ReturnCode_t onFinalize();

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
  // virtual RTC::ReturnCode_t onStartup(RTC::UniqueId ec_id);

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
  // virtual RTC::ReturnCode_t onShutdown(RTC::UniqueId ec_id);

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
  // virtual RTC::ReturnCode_t onActivated(RTC::UniqueId ec_id);

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
  // virtual RTC::ReturnCode_t onDeactivated(RTC::UniqueId ec_id);

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
  // virtual RTC::ReturnCode_t onExecute(RTC::UniqueId ec_id);

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
  // virtual RTC::ReturnCode_t onAborting(RTC::UniqueId ec_id);

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
  // virtual RTC::ReturnCode_t onError(RTC::UniqueId ec_id);

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
  // virtual RTC::ReturnCode_t onReset(RTC::UniqueId ec_id);
  
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
  // virtual RTC::ReturnCode_t onStateUpdate(RTC::UniqueId ec_id);

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
  // virtual RTC::ReturnCode_t onRateChanged(RTC::UniqueId ec_id);


 protected:
  // <rtc-template block="protected_attribute">
  
  // </rtc-template>

  // <rtc-template block="protected_operation">
  
  // </rtc-template>

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
  int m_int_param0;
  /*!
   * Config2�̊T�v
   * - Name: Config2�̖��O int_param1
   * - DefaultValue: 1
   * - Unit: Config2�̒P��
   * - Range: Config2�͈̔�
   * - Constraint: Config2�̐������
   */
  int m_int_param1;
  /*!
   * Config3�̊T�v
   * - Name: Config3�̖��O double_param0
   * - DefaultValue: 0.11
   * - Unit: Config3�̒P��
   * - Range: Config3�͈̔�
   * - Constraint: Config3�̐������
   */
  double m_double_param0;
  /*!
   * Config4�̊T�v
   * - Name: Config4�̖��O str_param0
   * - DefaultValue: hoge
   * - Unit: Config4�̒P��
   * - Range: Config4�͈̔�
   * - Constraint: Config4�̐������
   */
  std::string m_str_param0;
  /*!
   * Config5�̊T�v
   * - Name: Config5�̖��O str_param1
   * - DefaultValue: dara
   * - Unit: Config5�̒P��
   * - Range: Config5�͈̔�
   * - Constraint: Config5�̐������
   */
  std::string m_str_param1;
  // </rtc-template>

  // DataInPort declaration
  // <rtc-template block="inport_declare">
  TimedShort m_InName1;
  /*!
   * InPort1�̊T�v
   * - Type: InPort1�̃f�[�^�̌^
   * - Number: InPort1�̃f�[�^�̐�
   * - Semantics: InPort1�̃f�[�^�̈Ӗ�
   * - Unit: InPort1�̃f�[�^�̒P��
   * - Frequency: InPort1�̃f�[�^�̔����p�x
   * - Operation Cycle: InPort1�̃f�[�^�̏�������
   */
  InPort<TimedShort> m_InName1In;
  TimedLong m_InNm2;
  /*!
   * InPort2�̊T�v
   * - Type: InPort2�̃f�[�^�̌^
   * - Number: InPort2�̃f�[�^�̐�
   * - Semantics: InPort2�̃f�[�^�̈Ӗ�
   * - Unit: InPort2�̃f�[�^�̒P��
   * - Frequency: InPort2�̃f�[�^�̔����p�x
   * - Operation Cycle: InPort2�̃f�[�^�̏�������
   */
  InPort<TimedLong> m_InNm2In;
  
  // </rtc-template>


  // DataOutPort declaration
  // <rtc-template block="outport_declare">
  TimedLong m_OutName1;
  /*!
   * OutPort1�̊T�v
   * - Type: OutPort1�̃f�[�^�̌^
   * - Number: OutPort1�̃f�[�^�̐�
   * - Semantics: OutPort1�̃f�[�^�̈Ӗ�
   * - Unit: OutPort1�̃f�[�^�̒P��
   * - Frequency: OutPort1�̃f�[�^�̔����p�x
   * - Operation Cycle: OutPort1�̃f�[�^�̏�������
   */
  OutPort<TimedLong> m_OutName1Out;
  TimedFloat m_OutNme2;
  /*!
   * OutPort2�̊T�v
   * - Type: OutPort2�̃f�[�^�̌^
   * - Number: OutPort2�̃f�[�^�̐�
   * - Semantics: OutPort2�̃f�[�^�̈Ӗ�
   * - Unit: OutPort2�̃f�[�^�̒P��
   * - Frequency: OutPort2�̃f�[�^�̔����p�x
   * - Operation Cycle: OutPort2�̃f�[�^�̏�������
   */
  OutPort<TimedFloat> m_OutNme2Out;
  
  // </rtc-template>

  // CORBA Port declaration
  // <rtc-template block="corbaport_declare">
  /*!
   * ServicePort1�̊T�v
   * Interface: ServicePort1�̃C���^�[�t�F�[�X�̊T�v
   */
  RTC::CorbaPort m_svPortPort;
  /*!
   * ServicePort2�̊T�v
   * Interface: ServicePort2�̃C���^�[�t�F�[�X�̊T�v
   */
  RTC::CorbaPort m_cmPortPort;
  
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
  MyServiceSVC_impl m_acc;
  
  // </rtc-template>

  // Consumer declaration
  // <rtc-template block="consumer_declare">
  /*!
   * ServiceIF2�̊T�v����
   * - Argument:      ServiceIF2�̈���
   * - Return Value:  ServiceIF2�̕Ԓl
   * - Exception:     ServiceIF2�̗�O
   * - PreCondition:  ServiceIF2�̎��O����
   * - PostCondition: ServiceIF2�̎������
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
