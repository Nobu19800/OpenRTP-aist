package RTC;


/**
* RTC/FsmOperations.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008�N12��4�� (�ؗj��) 14��12��46�b JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief fsm
   *
   * @section Description
   *
   * Applying the fsm stereotype to a component implies the ability to
   * define component-specific states and transitions.
   *
   * @section Semantics
   *
   * In creating a state machine such as is depicted in Figure 5.22,
   * the RTC developer is implicitly defining the Active state to be a
   * submachine state.  * The BehaviorStateMachines package described
   * in [UML] is considered the normative definition of a state
   * machine.
   *
   * @endif
   */
public interface FsmOperations  extends RTC.LightweightRTObjectOperations
{
} // interface FsmOperations
