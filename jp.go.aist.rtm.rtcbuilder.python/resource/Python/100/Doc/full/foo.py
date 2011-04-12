#!/usr/bin/env python
# -*- Python -*-
"""
 \file foo.py
 \brief MDesc
 \date $Date$
 \author Noriaki Ando <n-ando@aist.go.jp>
 Copyright (C) 2006-2008 ���C�Z���X
"""
import sys
import time
sys.path.append(".")
# Import RTM module
import RTC
import OpenRTM_aist
# Import Service implementation class
# <rtc-template block="service_impl">
from MyService_idl_example import *
# </rtc-template>
# Import Service stub modules
# <rtc-template block="consumer_import">
import _GlobalIDL, _GlobalIDL__POA
# </rtc-template>
# This module's spesification
# <rtc-template block="module_spec">
foo_spec = ["implementation_id", "foo", 
		 "type_name",         "foo", 
		 "description",       "MDesc", 
		 "version",           "1.0.1", 
		 "vendor",            "TA", 
		 "category",          "Manip", 
		 "activity_type",     "STATIC2", 
		 "max_instance",      "5", 
		 "language",          "Python", 
		 "lang_type",         "SCRIPT",
		 "conf.default.int_param0", "0",
		 "conf.default.int_param1", "1",
		 "conf.default.double_param0", "0.11",
		 "conf.default.str_param0", "hoge",
		 "conf.default.str_param1", "dara",
		 ""]
# </rtc-template>
class foo(OpenRTM_aist.DataFlowComponentBase):
	
	"""
	\class foo
	\brief MDesc
	
	�{�R���|�[�l���g�̊T�v����
	
	�{�R���|�[�l���g�̓��o��
	
	�{�R���|�[�l���g�̃A���S���Y���Ȃ�
	
	�Q�l�����̏��
	
	"""
	def __init__(self, manager):
		"""
		\brief constructor
		\param manager Maneger Object
		"""
		OpenRTM_aist.DataFlowComponentBase.__init__(self, manager)
		self._d_InP1 = RTC.TimedShort(RTC.Time(0,0),0)
		"""
		InPort1�̊T�v
		 - Type: InPort1�̃f�[�^�̌^
		 - Number: InPort1�̃f�[�^�̐�
		 - Semantics: InPort1�̃f�[�^�̈Ӗ�
		 - Unit: InPort1�̃f�[�^�̒P��
		 - Frequency: InPort1�̃f�[�^�̔����p�x
		 - Operation Cycle: InPort1�̃f�[�^�̏�������
		"""
		self._InP1In = OpenRTM_aist.InPort("InP1", self._d_InP1)
		self._d_InP2 = RTC.TimedLong(RTC.Time(0,0),0)
		"""
		InPort2�̊T�v
		 - Type: InPort2�̃f�[�^�̌^
		 - Number: InPort2�̃f�[�^�̐�
		 - Semantics: InPort2�̃f�[�^�̈Ӗ�
		 - Unit: InPort2�̃f�[�^�̒P��
		 - Frequency: InPort2�̃f�[�^�̔����p�x
		 - Operation Cycle: InPort2�̃f�[�^�̏�������
		"""
		self._InP2In = OpenRTM_aist.InPort("InP2", self._d_InP2)
		self._d_OutP1 = RTC.TimedLong(RTC.Time(0,0),0)
		"""
		OutPort1�̊T�v
		 - Type: OutPort1�̃f�[�^�̌^
		 - Number: OutPort1�̃f�[�^�̐�
		 - Semantics: OutPort1�̃f�[�^�̈Ӗ�
		 - Unit: OutPort1�̃f�[�^�̒P��
		 - Frequency: OutPort1�̃f�[�^�̔����p�x
		 - Operation Cycle: OutPort1�̃f�[�^�̏�������
		"""
		self._OutP1Out = OpenRTM_aist.OutPort("OutP1", self._d_OutP1)
		self._d_OutP2 = RTC.TimedFloat(RTC.Time(0,0),0)
		"""
		OutPort2�̊T�v
		 - Type: OutPort2�̃f�[�^�̌^
		 - Number: OutPort2�̃f�[�^�̐�
		 - Semantics: OutPort2�̃f�[�^�̈Ӗ�
		 - Unit: OutPort2�̃f�[�^�̒P��
		 - Frequency: OutPort2�̃f�[�^�̔����p�x
		 - Operation Cycle: OutPort2�̃f�[�^�̏�������
		"""
		self._OutP2Out = OpenRTM_aist.OutPort("OutP2", self._d_OutP2)
		"""
		ServicePort1�̊T�v
		Interface: ServicePort1�̃C���^�[�t�F�[�X�̊T�v
		"""
		self._svPortPort = OpenRTM_aist.CorbaPort("svPort")
		"""
		ServicePort2�̊T�v
		Interface: ServicePort2�̃C���^�[�t�F�[�X�̊T�v
		"""
		self._cmPortPort = OpenRTM_aist.CorbaPort("cmPort")
		"""
		ServiceIF1�̊T�v����
		 - Argument:      ServiceIF1�̈���
		 - Return Value:  ServiceIF1�̕Ԓl
		 - Exception:     ServiceIF1�̗�O
		 - PreCondition:  ServiceIF1�̎��O����
		 - PostCondition: ServiceIF1�̎������
		"""
		self._acc = MyService_i()
		
		"""
		ServiceIF2�̊T�v����
		 - Argument:      ServiceIF2�̈���
		 - Return Value:  ServiceIF2�̕Ԓl
		 - Exception:     ServiceIF2�̗�O
		 - PreCondition:  ServiceIF2�̎��O����
		 - PostCondition: ServiceIF2�̎������
		"""
		self._rate = OpenRTM_aist.CorbaConsumer(interfaceType=_GlobalIDL.DAQService)
		# initialize of configuration-data.
		# <rtc-template block="init_conf_param">
		"""
		Config1�̊T�v
		 - Name: Config1�̖��O int_param0
		 - DefaultValue: 0
		 - Unit: Config1�̒P��
		 - Range: Config1�͈̔�
		 - Constraint: Config1�̐������
		"""
		self._int_param0 = [0]
		"""
		Config2�̊T�v
		 - Name: Config2�̖��O int_param1
		 - DefaultValue: 1
		 - Unit: Config2�̒P��
		 - Range: Config2�͈̔�
		 - Constraint: Config2�̐������
		"""
		self._int_param1 = [1]
		"""
		Config3�̊T�v
		 - Name: Config3�̖��O double_param0
		 - DefaultValue: 0.11
		 - Unit: Config3�̒P��
		 - Range: Config3�͈̔�
		 - Constraint: Config3�̐������
		"""
		self._double_param0 = [0.11]
		"""
		Config4�̊T�v
		 - Name: Config4�̖��O str_param0
		 - DefaultValue: hoge
		 - Unit: Config4�̒P��
		 - Range: Config4�͈̔�
		 - Constraint: Config4�̐������
		"""
		self._str_param0 = ['hoge']
		"""
		Config5�̊T�v
		 - Name: Config5�̖��O str_param1
		 - DefaultValue: dara
		 - Unit: Config5�̒P��
		 - Range: Config5�͈̔�
		 - Constraint: Config5�̐������
		"""
		self._str_param1 = ['dara']
		
		# </rtc-template>
		 
	def onInitialize(self):
		"""
		on_initialize�T�v����
		
		The initialize action (on CREATED->ALIVE transition)
		formaer rtc_init_entry() 
		
		\return RTC::ReturnCode_t
		
		\pre on_initialize���O����
		\post on_initialize�������
		"""
		# Bind variables and configuration variable
		self.bindParameter("int_param0", self._int_param0, "0")
		self.bindParameter("int_param1", self._int_param1, "1")
		self.bindParameter("double_param0", self._double_param0, "0.11")
		self.bindParameter("str_param0", self._str_param0, "hoge")
		self.bindParameter("str_param1", self._str_param1, "dara")
		
		# Set InPort buffers
		self.addInPort("InP1",self._InP1In)
		self.addInPort("InP2",self._InP2In)
		
		# Set OutPort buffers
		self.addOutPort("OutP1",self._OutP1Out)
		self.addOutPort("OutP2",self._OutP2Out)
		
		# Set service provider to Ports
		self._svPortPort.registerProvider("acc", "MyService", self._acc)
		
		# Set service consumers to Ports
		self._cmPortPort.registerConsumer("rate", "DAQService", self._rate)
		
		# Set CORBA Service Ports
		self.addPort(self._svPortPort)
		self.addPort(self._cmPortPort)
		
		return RTC.RTC_OK
	
	#def onFinalize(self, ec_id):
	#	"""
	#	on_finalize�T�v����
	#
	#	The finalize action (on ALIVE->END transition)
	#	formaer rtc_exiting_entry()
	#
	#	\return RTC::ReturnCode_t
	#
	#	\pre on_finalize���O����
	#	\post on_finalize�������
	#	"""
	#
	#	return RTC.RTC_OK
	
	#def onStartup(self, ec_id):
	#	"""
	#	on_startup�T�v����
	#
	#	The startup action when ExecutionContext startup
	#	former rtc_starting_entry()
	#
	#	\param ec_id target ExecutionContext Id
	#
	#	\return RTC::ReturnCode_t
	#
	#	\pre on_startup���O����
	#	\post on_startup�������
	#	"""
	#
	#	return RTC.RTC_OK
	
	#def onShutdown(self, ec_id):
	#	"""
	#	on_shutdown�T�v����
	#
	#	The shutdown action when ExecutionContext stop
	#	former rtc_stopping_entry()
	#
	#	\param ec_id target ExecutionContext Id
	#
	#	\return RTC::ReturnCode_t
	#
	#	\pre on_shutdown���O����
	#	\post on_shutdown�������
	#	"""
	#
	#	return RTC.RTC_OK
	
	#def onActivated(self, ec_id):
	#	"""
	#	on_activated�T�v����
	#
	#	The activated action (Active state entry action)
	#	former rtc_active_entry()
	#
	#	\param ec_id target ExecutionContext Id
	#
	#	\return RTC::ReturnCode_t
	#
	#	\pre on_activated���O����
	#	\post on_activated�������
	#	"""
	#
	#	return RTC.RTC_OK
	
	#def onDeactivated(self, ec_id):
	#	"""
	#	on_deactivated�T�v����
	#
	#	The deactivated action (Active state exit action)
	#	former rtc_active_exit()
	#
	#	\param ec_id target ExecutionContext Id
	#
	#	\return RTC::ReturnCode_t
	#
	#	\pre on_deactivated���O����
	#	\post on_deactivated�������
	#	"""
	#
	#	return RTC.RTC_OK
	
	#def onExecute(self, ec_id):
	#	"""
	#	on_execute�T�v����
	#
	#	The execution action that is invoked periodically
	#	former rtc_active_do()
	#
	#	\param ec_id target ExecutionContext Id
	#
	#	\return RTC::ReturnCode_t
	#
	#	\pre on_execute���O����
	#	\post on_execute�������
	#	"""
	#
	#	return RTC.RTC_OK
	
	#def onAborting(self, ec_id):
	#	"""
	#	on_aborting�T�v����
	#
	#	The aborting action when main logic error occurred.
	#	former rtc_aborting_entry()
	#
	#	\param ec_id target ExecutionContext Id
	#
	#	\return RTC::ReturnCode_t
	#
	#	\pre on_aborting���O����
	#	\post on_aborting�������
	#	"""
	#
	#	return RTC.RTC_OK
	
	#def onError(self, ec_id):
	#	"""
	#	on_error�T�v����
	#
	#	The error action in ERROR state
	#	former rtc_error_do()
	#
	#	\param ec_id target ExecutionContext Id
	#
	#	\return RTC::ReturnCode_t
	#
	#	\pre on_error���O����
	#	\post on_error�������
	#	"""
	#
	#	return RTC.RTC_OK
	
	#def onReset(self, ec_id):
	#	"""
	#	on_reset�T�v����
	#
	#	The reset action that is invoked resetting
	#	This is same but different the former rtc_init_entry()
	#
	#	\param ec_id target ExecutionContext Id
	#
	#	\return RTC::ReturnCode_t
	#
	#	\pre on_reset���O����
	#	\post on_reset�������
	#	"""
	#
	#	return RTC.RTC_OK
	
	#def onStateUpdate(self, ec_id):
	#	"""
	#	on_state_update�T�v����
	#
	#	The state update action that is invoked after onExecute() action
	#	no corresponding operation exists in OpenRTm-aist-0.2.0
	#
	#	\param ec_id target ExecutionContext Id
	#
	#	\return RTC::ReturnCode_t
	#
	#	\pre on_state_update���O����
	#	\post on_state_update�������
	#	"""
	#
	#	return RTC.RTC_OK
	
	#def onRateChanged(self, ec_id):
	#	"""
	#	on_rate_changed�T�v����
	#
	#	The action that is invoked when execution context's rate is changed
	#	no corresponding operation exists in OpenRTm-aist-0.2.0
	#
	#	\param ec_id target ExecutionContext Id
	#
	#	\return RTC::ReturnCode_t
	#
	#	\pre on_rate_changed���O����
	#	\post on_rate_changed�������
	#	"""
	#
	#	return RTC.RTC_OK
	
def fooInit(manager):
    profile = OpenRTM_aist.Properties(defaults_str=foo_spec)
    manager.registerFactory(profile,
                            foo,
                            OpenRTM_aist.Delete)

def MyModuleInit(manager):
    fooInit(manager)

    # Create a component
    comp = manager.createComponent("foo")

def main():
	mgr = OpenRTM_aist.Manager.init(sys.argv)
	mgr.setModuleInitProc(MyModuleInit)
	mgr.activateManager()
	mgr.runManager()
if __name__ == "__main__":
	main()
