#!/usr/bin/env python
# -*- Python -*-

"""
 \file foo.py
 \brief MDesc
 \date $Date$

 \author Noriaki Ando <n-ando@aist.go.jp>345678941234567895123456789612345678971
 23456789812345

 Copyright (C) 2006-2008 ���C�Z���X123456789012345678901234567890123456789012345
 67890

"""
import sys
import time
sys.path.append(".")

# Import RTM module
import OpenRTM
import RTC

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
		 "conf.default.str_param0", "���{��",
		 "conf.default.str_param1", "dara",
		 ""]
# </rtc-template>

class foo(OpenRTM.DataFlowComponentBase):
	
	"""
	\class foo
	\brief MDesc
	
	�{�R���|�[�l���g�̊T�v����12345678901234567890123456789012345678901234567890123
	45678901234567890
	
	�{�R���|�[�l���g�̓��o��1234567890123456789012345678901234567890123456789012345
	678901234567890
	
	�{�R���|�[�l���g�̃A���S���Y���Ȃ�123456789012345678901234567890123456789012345
	6789012345678901234567890
	
	�Q�l�����̏��12345678901234567890123456789012345678901234567890123456789012345
	67890
	
	"""
	def __init__(self, manager):
		"""
		\brief constructor
		\param manager Maneger Object
		"""
		OpenRTM.DataFlowComponentBase.__init__(self, manager)

		self._d_InP1 = RTC.TimedShort(RTC.Time(0,0),0)
		"""
		InPort1�̊T�v123456789012345678901234567890123456789012345678901234567890123
		4567890
		 - Type: InPort1�̃f�[�^�̌^123456789012345678901234567890123456789012345678
		         9012345678901234567890
		 - Number: InPort1�̃f�[�^�̐�1234567890123456789012345678901234567890123456
		           789012345678901234567890
		 - Semantics: InPort1�̃f�[�^�̈Ӗ�12345678901234567890123456789012345678901
		              23456789012345678901234567890
		 - Unit: InPort1�̃f�[�^�̒P��1234567890123456789012345678901234567890123456
		         789012345678901234567890
		 - Frequency: InPort1�̃f�[�^�̔����p�x1234567890123456789012345678901234567
		              890123456789012345678901234567890
		 - Operation Cycle: InPort1�̃f�[�^�̏�������1234567890123456789012345678901
		                    234567890123456789012345678901234567890
		"""
		self._InP1In = OpenRTM.InPort("InP1", self._d_InP1, OpenRTM.RingBuffer(8))
		self._d_InP2 = RTC.TimedLong(RTC.Time(0,0),0)
		"""
		InPort2�̊T�v123456789012345678901234567890123456789012345678901234567890123
		4567890
		 - Type: InPort2�̃f�[�^�̌^123456789012345678901234567890123456789012345678
		         9012345678901234567890
		 - Number: InPort2�̃f�[�^�̐�1234567890123456789012345678901234567890123456
		           789012345678901234567890
		 - Semantics: InPort2�̃f�[�^�̈Ӗ�12345678901234567890123456789012345678901
		              23456789012345678901234567890
		 - Unit: InPort2�̃f�[�^�̒P��1234567890123456789012345678901234567890123456
		         789012345678901234567890
		 - Frequency: InPort2�̃f�[�^�̔����p�x1234567890123456789012345678901234567
		              890123456789012345678901234567890
		 - Operation Cycle: InPort2�̃f�[�^�̏�������1234567890123456789012345678901
		                    234567890123456789012345678901234567890
		"""
		self._InP2In = OpenRTM.InPort("InP2", self._d_InP2, OpenRTM.RingBuffer(8))
		self._d_OutP1 = RTC.TimedLong(RTC.Time(0,0),0)
		"""
		OutPort1�̊T�v12345678901234567890123456789012345678901234567890123456789012
		34567890
		 - Type: OutPort1�̃f�[�^�̌^12345678901234567890123456789012345678901234567
		         89012345678901234567890
		 - Number: OutPort1�̃f�[�^�̐�123456789012345678901234567890123456789012345
		           6789012345678901234567890
		 - Semantics: OutPort1�̃f�[�^�̈Ӗ�1234567890123456789012345678901234567890
		              123456789012345678901234567890
		 - Unit: OutPort1�̃f�[�^�̒P��123456789012345678901234567890123456789012345
		         6789012345678901234567890
		 - Frequency: OutPort1�̃f�[�^�̔����p�x123456789012345678901234567890123456
		              7890123456789012345678901234567890
		 - Operation Cycle: OutPort1�̃f�[�^�̏�������123456789012345678901234567890
		                    1234567890123456789012345678901234567890
		"""
		self._OutP1Out = OpenRTM.OutPort("OutP1", self._d_OutP1, OpenRTM.RingBuffer(8))
		self._d_OutP2 = RTC.TimedFloat(RTC.Time(0,0),0)
		"""
		OutPort2�̊T�v12345678901234567890123456789012345678901234567890123456789012
		34567890
		 - Type: OutPort2�̃f�[�^�̌^12345678901234567890123456789012345678901234567
		         89012345678901234567890
		 - Number: OutPort2�̃f�[�^�̐�123456789012345678901234567890123456789012345
		           6789012345678901234567890
		 - Semantics: OutPort2�̃f�[�^�̈Ӗ�1234567890123456789012345678901234567890
		              123456789012345678901234567890
		 - Unit: OutPort2�̃f�[�^�̒P��123456789012345678901234567890123456789012345
		         6789012345678901234567890
		 - Frequency: OutPort2�̃f�[�^�̔����p�x123456789012345678901234567890123456
		              7890123456789012345678901234567890
		 - Operation Cycle: OutPort2�̃f�[�^�̏�������123456789012345678901234567890
		                    1234567890123456789012345678901234567890
		"""
		self._OutP2Out = OpenRTM.OutPort("OutP2", self._d_OutP2, OpenRTM.RingBuffer(8))
		

		# Set InPort buffers
		self.registerInPort("InP1",self._InP1In)
		self.registerInPort("InP2",self._InP2In)
		
		# Set OutPort buffers
		self.registerOutPort("OutP1",self._OutP1Out)
		self.registerOutPort("OutP2",self._OutP2Out)
		

		"""
		ServicePort1�̊T�v1234567890123456789012345678901234567890123456789012345678
		901234567890
		Interface: ServicePort1�̃C���^�[�t�F�[�X�̊T�v12345678901234567890123456789
		           01234567890123456789012345678901234567890
		"""
		self._svPortPort = OpenRTM.CorbaPort("svPort")
		"""
		ServicePort2�̊T�v1234567890123456789012345678901234567890123456789012345678
		901234567890
		Interface: ServicePort2�̃C���^�[�t�F�[�X�̊T�v12345678901234567890123456789
		           01234567890123456789012345678901234567890
		"""
		self._cmPortPort = OpenRTM.CorbaPort("cmPort")
		

		"""
		ServiceIF1�̊T�v����12345678901234567890123456789012345678901234567890123456
		78901234567890
		 - Argument:      ServiceIF1�̈���123456789012345678901234567890123456789012
		                  3456789012345678901234567890
		 - Return Value:  ServiceIF1�̕Ԓl123456789012345678901234567890123456789012
		                  3456789012345678901234567890
		 - Exception:     ServiceIF1�̗�O123456789012345678901234567890123456789012
		                  3456789012345678901234567890
		 - PreCondition:  ServiceIF1�̎��O����12345678901234567890123456789012345678
		                  90123456789012345678901234567890
		 - PostCondition: ServiceIF1�̎������12345678901234567890123456789012345678
		                  90123456789012345678901234567890
		"""
		self._acc = MyService_i()
		

		"""
		ServiceIF2�̊T�v����12345678901234567890123456789012345678901234567890123456
		78901234567890
		 - Argument:      ServiceIF2�̈���123456789012345678901234567890123456789012
		                  3456789012345678901234567890
		 - Return Value:  ServiceIF2�̕Ԓl123456789012345678901234567890123456789012
		                  3456789012345678901234567890
		 - Exception:     ServiceIF2�̗�O123456789012345678901234567890123456789012
		                  3456789012345678901234567890
		 - PreCondition:  ServiceIF2�̎��O����12345678901234567890123456789012345678
		                  90123456789012345678901234567890
		 - PostCondition: ServiceIF2�̎������12345678901234567890123456789012345678
		                  90123456789012345678901234567890
		"""
		self._rate = OpenRTM.CorbaConsumer(interfaceType=_GlobalIDL.DAQService)
		
		# Set service provider to Ports
		self._svPortPort.registerProvider("acc", "MyService", self._acc)
		
		# Set service consumers to Ports
		self._cmPortPort.registerConsumer("rate", "DAQService", self._rate)
		
		# Set CORBA Service Ports
		self.registerPort(self._svPortPort)
		self.registerPort(self._cmPortPort)
		

		# initialize of configuration-data.
		# <rtc-template block="init_conf_param">
		"""
		Config1�̊T�v123456789012345678901234567890123456789012345678901234567890123
		4567890
		 - Name: Config1�̖��O int_param0
		 - DefaultValue: 0
		 - Unit: Config1�̒P��123456789012345678901234567890123456789012345678901234
		         5678901234567890
		 - Range: Config1�͈̔�12345678901234567890123456789012345678901234567890123
		          45678901234567890
		 - Constraint: Config1�̐������12345678901234567890123456789012345678901234
		               56789012345678901234567890
		"""
		self._int_param0 = [0]
		"""
		Config2�̊T�v123456789012345678901234567890123456789012345678901234567890123
		4567890
		 - Name: Config2�̖��O int_param1
		 - DefaultValue: 1
		 - Unit: Config2�̒P��123456789012345678901234567890123456789012345678901234
		         5678901234567890
		 - Range: Config2�͈̔�12345678901234567890123456789012345678901234567890123
		          45678901234567890
		 - Constraint: Config2�̐������12345678901234567890123456789012345678901234
		               56789012345678901234567890
		"""
		self._int_param1 = [1]
		"""
		Config3�̊T�v123456789012345678901234567890123456789012345678901234567890123
		4567890
		 - Name: Config3�̖��O double_param0
		 - DefaultValue: 0.11
		 - Unit: Config3�̒P��123456789012345678901234567890123456789012345678901234
		         5678901234567890
		 - Range: Config3�͈̔�12345678901234567890123456789012345678901234567890123
		          45678901234567890
		 - Constraint: Config3�̐������12345678901234567890123456789012345678901234
		               56789012345678901234567890
		"""
		self._double_param0 = [0.11]
		"""
		Config4�̊T�v123456789012345678901234567890123456789012345678901234567890123
		4567890
		 - Name: Config4�̖��O str_param0
		 - DefaultValue: ���{��
		 - Unit: Config4�̒P��123456789012345678901234567890123456789012345678901234
		         5678901234567890
		 - Range: Config4�͈̔�12345678901234567890123456789012345678901234567890123
		          45678901234567890
		 - Constraint: Config4�̐������12345678901234567890123456789012345678901234
		               56789012345678901234567890
		"""
		self._str_param0 = ['���{��']
		"""
		Config5�̊T�v123456789012345678901234567890123456789012345678901234567890123
		4567890
		 - Name: Config5�̖��O str_param1
		 - DefaultValue: dara
		 - Unit: Config5�̒P��123456789012345678901234567890123456789012345678901234
		         5678901234567890
		 - Range: Config5�͈̔�12345678901234567890123456789012345678901234567890123
		          45678901234567890
		 - Constraint: Config5�̐������12345678901234567890123456789012345678901234
		               56789012345678901234567890
		"""
		self._str_param1 = ['dara']
		
		# </rtc-template>


		 
	def onInitialize(self):
		"""
		on_initialize�T�v����1234567890123456789012345678901234567890123456789012345
		678901234567890
		
		The initialize action (on CREATED->ALIVE transition)
		formaer rtc_init_entry() 
		
		\return RTC::ReturnCode_t
		
		\pre on_initialize���O����12345678901234567890123456789012345678901234567890
		     12345678901234567890
		\post on_initialize�������1234567890123456789012345678901234567890123456789
		      012345678901234567890
		"""
		# Bind variables and configuration variable
		self.bindParameter("int_param0", self._int_param0, "0")
		self.bindParameter("int_param1", self._int_param1, "1")
		self.bindParameter("double_param0", self._double_param0, "0.11")
		self.bindParameter("str_param0", self._str_param0, "���{��")
		self.bindParameter("str_param1", self._str_param1, "dara")
		
		return RTC.RTC_OK


	
	#def onFinalize(self, ec_id):
	#	"""
	#	on_finalize�T�v����123456789012345678901234567890123456789012345678901234567
	#	8901234567890
	#
	#	The finalize action (on ALIVE->END transition)
	#	formaer rtc_exiting_entry()
	#
	#	\return RTC::ReturnCode_t
	#
	#	\pre on_finalize���O����1234567890123456789012345678901234567890123456789012
	#	     345678901234567890
	#	\post on_finalize�������123456789012345678901234567890123456789012345678901
	#	      2345678901234567890
	#	"""
	#
	#	return RTC.RTC_OK
	
	#def onStartup(self, ec_id):
	#	"""
	#	on_startup�T�v����1234567890123456789012345678901234567890123456789012345678
	#	901234567890
	#
	#	The startup action when ExecutionContext startup
	#	former rtc_starting_entry()
	#
	#	\param ec_id target ExecutionContext Id
	#
	#	\return RTC::ReturnCode_t
	#
	#	\pre on_startup���O����12345678901234567890123456789012345678901234567890123
	#	     45678901234567890
	#	\post on_startup�������1234567890123456789012345678901234567890123456789012
	#	      345678901234567890
	#	"""
	#
	#	return RTC.RTC_OK
	
	#def onShutdown(self, ec_id):
	#	"""
	#	on_shutdown�T�v����123456789012345678901234567890123456789012345678901234567
	#	8901234567890
	#
	#	The shutdown action when ExecutionContext stop
	#	former rtc_stopping_entry()
	#
	#	\param ec_id target ExecutionContext Id
	#
	#	\return RTC::ReturnCode_t
	#
	#	\pre on_shutdown���O����1234567890123456789012345678901234567890123456789012
	#	     345678901234567890
	#	\post on_shutdown�������123456789012345678901234567890123456789012345678901
	#	      2345678901234567890
	#	"""
	#
	#	return RTC.RTC_OK
	
	#def onActivated(self, ec_id):
	#	"""
	#	on_activated�T�v����12345678901234567890123456789012345678901234567890123456
	#	78901234567890
	#
	#	The activated action (Active state entry action)
	#	former rtc_active_entry()
	#
	#	\param ec_id target ExecutionContext Id
	#
	#	\return RTC::ReturnCode_t
	#
	#	\pre on_activated���O����123456789012345678901234567890123456789012345678901
	#	     2345678901234567890
	#	\post on_activated�������12345678901234567890123456789012345678901234567890
	#	      12345678901234567890
	#	"""
	#
	#	return RTC.RTC_OK
	
	#def onDeactivated(self, ec_id):
	#	"""
	#	on_deactivated�T�v����123456789012345678901234567890123456789012345678901234
	#	5678901234567890
	#
	#	The deactivated action (Active state exit action)
	#	former rtc_active_exit()
	#
	#	\param ec_id target ExecutionContext Id
	#
	#	\return RTC::ReturnCode_t
	#
	#	\pre on_deactivated���O����1234567890123456789012345678901234567890123456789
	#	     012345678901234567890
	#	\post on_deactivated�������123456789012345678901234567890123456789012345678
	#	      9012345678901234567890
	#	"""
	#
	#	return RTC.RTC_OK
	
	#def onExecute(self, ec_id):
	#	"""
	#	on_execute�T�v����1234567890123456789012345678901234567890123456789012345678
	#	901234567890
	#
	#	The execution action that is invoked periodically
	#	former rtc_active_do()
	#
	#	\param ec_id target ExecutionContext Id
	#
	#	\return RTC::ReturnCode_t
	#
	#	\pre on_execute���O����12345678901234567890123456789012345678901234567890123
	#	     45678901234567890
	#	\post on_execute�������1234567890123456789012345678901234567890123456789012
	#	      345678901234567890
	#	"""
	#
	#	return RTC.RTC_OK
	
	#def onAborting(self, ec_id):
	#	"""
	#	on_aborting�T�v����123456789012345678901234567890123456789012345678901234567
	#	8901234567890
	#
	#	The aborting action when main logic error occurred.
	#	former rtc_aborting_entry()
	#
	#	\param ec_id target ExecutionContext Id
	#
	#	\return RTC::ReturnCode_t
	#
	#	\pre on_aborting���O����1234567890123456789012345678901234567890123456789012
	#	     345678901234567890
	#	\post on_aborting�������123456789012345678901234567890123456789012345678901
	#	      2345678901234567890
	#	"""
	#
	#	return RTC.RTC_OK
	
	#def onError(self, ec_id):
	#	"""
	#	on_error�T�v����123456789012345678901234567890123456789012345678901234567890
	#	1234567890
	#
	#	The error action in ERROR state
	#	former rtc_error_do()
	#
	#	\param ec_id target ExecutionContext Id
	#
	#	\return RTC::ReturnCode_t
	#
	#	\pre on_error���O����1234567890123456789012345678901234567890123456789012345
	#	     678901234567890
	#	\post on_error�������123456789012345678901234567890123456789012345678901234
	#	      5678901234567890
	#	"""
	#
	#	return RTC.RTC_OK
	
	#def onReset(self, ec_id):
	#	"""
	#	on_reset�T�v����123456789012345678901234567890123456789012345678901234567890
	#	1234567890
	#
	#	The reset action that is invoked resetting
	#	This is same but different the former rtc_init_entry()
	#
	#	\param ec_id target ExecutionContext Id
	#
	#	\return RTC::ReturnCode_t
	#
	#	\pre on_reset���O����1234567890123456789012345678901234567890123456789012345
	#	     678901234567890
	#	\post on_reset�������123456789012345678901234567890123456789012345678901234
	#	      5678901234567890
	#	"""
	#
	#	return RTC.RTC_OK
	
	#def onStateUpdate(self, ec_id):
	#	"""
	#	on_state_update�T�v����12345678901234567890123456789012345678901234567890123
	#	45678901234567890
	#
	#	The state update action that is invoked after onExecute() action
	#	no corresponding operation exists in OpenRTm-aist-0.2.0
	#
	#	\param ec_id target ExecutionContext Id
	#
	#	\return RTC::ReturnCode_t
	#
	#	\pre on_state_update���O����123456789012345678901234567890123456789012345678
	#	     9012345678901234567890
	#	\post on_state_update�������12345678901234567890123456789012345678901234567
	#	      89012345678901234567890
	#	"""
	#
	#	return RTC.RTC_OK
	
	#def onRateChanged(self, ec_id):
	#	"""
	#	on_rate_changed�T�v����12345678901234567890123456789012345678901234567890123
	#	45678901234567890
	#
	#	The action that is invoked when execution context's rate is changed
	#	no corresponding operation exists in OpenRTm-aist-0.2.0
	#
	#	\param ec_id target ExecutionContext Id
	#
	#	\return RTC::ReturnCode_t
	#
	#	\pre on_rate_changed���O����123456789012345678901234567890123456789012345678
	#	     9012345678901234567890
	#	\post on_rate_changed�������12345678901234567890123456789012345678901234567
	#	      89012345678901234567890
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
	mgr = OpenRTM.Manager.init(len(sys.argv), sys.argv)
	mgr.setModuleInitProc(MyModuleInit)
	mgr.activateManager()
	mgr.runManager()

if __name__ == "__main__":
	main()

