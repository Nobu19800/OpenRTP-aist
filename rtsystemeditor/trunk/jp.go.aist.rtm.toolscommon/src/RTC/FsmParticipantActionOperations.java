package RTC;


/**
* RTC/FsmParticipantActionOperations.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008�N12��4�� (�ؗj��) 14��12��46�b JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief FsmParticipantAction
   *
   * @section Description
   *
   * FsmParticipantAction is companion to ComponentAction (see Section
   * 5.2.2.4) that is intended for use with FSM participant RTCs. It
   * adds a callback for the interception of state transitions, state
   * entries, and state exits.
   *
   * @endif
   */
public interface FsmParticipantActionOperations 
{

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief on_action
     *
     * @section Description
     *
     * The indicated FSM participant RTC has been invoked as a result
     * of a transition, state entry, or state exit in its containing
     * FSM.
     *
     * @section Constraints
     *
     * - The given execution context shall be of kind EVENT_DRIVEN.
        *
     * @endif
     */
  RTC.ReturnCode_t on_action (int exec_handle);
} // interface FsmParticipantActionOperations
