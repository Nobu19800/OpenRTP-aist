package RTC;


/**
* RTC/ComponentAction.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008�N12��4�� (�ؗj��) 14��12��46�b JST
*/


/*!
   * @if jp
   * @brief
   * @else
   * @brief ComponentAction
   *
   * @section Description
   *
   * The ComponentAction interface provides callbacks corresponding to
   * the execution of the lifecycle operations of LightweightRTObject
   * (see Section 5.2.2.2) and ExecutionContext (see Section
   * 5.2.2.5). An RTC developer may implement these callback
   * operations in order to execute application-specific logic
   * pointing response to those transitions.
   *
   * @section Semantics
   *
   * Clients of an RTC are not expected to invoke these operations
   * directly; they are provided for the benefit of the RTC middleware
   * implementation.
   *
   * @endif
   */
public interface ComponentAction extends ComponentActionOperations, org.omg.CORBA.Object, org.omg.CORBA.portable.IDLEntity 
{
} // interface ComponentAction
