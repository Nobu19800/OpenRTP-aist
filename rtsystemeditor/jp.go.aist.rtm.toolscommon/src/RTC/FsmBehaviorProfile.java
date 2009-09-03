package RTC;


/**
* RTC/FsmBehaviorProfile.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008�N12��4�� (�ؗj��) 14��12��47�b JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief FsmBehaviorProfile
   *
   * @section Description
   *
   * FsmBehaviorProfile represents the association of an FSM
   * participant with a transition, state entry, or state exit in an
   * FSM.
   *
   * @section Semantics
   *
   * The assignment of identifiers to particular transitions, state
   * entries, or state exits is implementation-dependent.
   *
   * @endif
   */
public final class FsmBehaviorProfile implements org.omg.CORBA.portable.IDLEntity
{

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief action_component
     *
     * @section Description
     *
     * This attribute stores a reference to the FSM participant that
     * is invoked when the containing Fsm receives a message
     * distinguished by id.
     *
     * @endif
     */
  public RTC.FsmParticipantAction action_component = null;

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief id
     *
     * @section Description
     *
     * This attribute stores the message identifier.
     *
     * @endif
     */
  public String id = null;

  public FsmBehaviorProfile ()
  {
  } // ctor

  public FsmBehaviorProfile (RTC.FsmParticipantAction _action_component, String _id)
  {
    action_component = _action_component;
    id = _id;
  } // ctor

} // class FsmBehaviorProfile
