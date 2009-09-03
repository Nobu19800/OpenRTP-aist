package RTC;


/**
* RTC/FsmProfile.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008�N12��4�� (�ؗj��) 14��12��47�b JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief FsmProfile
   *
   * @section Description
   *
   * The FsmProfile describes the correspondence between an FSM and
   * its contained FSM participants. This Profile is necessary for
   * Stimulus Response Processing.
   *
   * @endif
   */
public final class FsmProfile implements org.omg.CORBA.portable.IDLEntity
{

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief behavior_profiles
     *
     * @section Description
     *
     * This attribute lists the correspondences between an FSM and its
     * contained FSM participants.
     *
     * @endif
     */
  public RTC.FsmBehaviorProfile behavior_profiles[] = null;

  public FsmProfile ()
  {
  } // ctor

  public FsmProfile (RTC.FsmBehaviorProfile[] _behavior_profiles)
  {
    behavior_profiles = _behavior_profiles;
  } // ctor

} // class FsmProfile
