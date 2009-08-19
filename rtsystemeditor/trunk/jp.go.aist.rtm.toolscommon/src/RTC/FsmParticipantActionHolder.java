package RTC;

/**
* RTC/FsmParticipantActionHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008�N12��4�� (�ؗj��) 14��12��44�b JST
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
public final class FsmParticipantActionHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.FsmParticipantAction value = null;

  public FsmParticipantActionHolder ()
  {
  }

  public FsmParticipantActionHolder (RTC.FsmParticipantAction initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.FsmParticipantActionHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.FsmParticipantActionHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.FsmParticipantActionHelper.type ();
  }

}
