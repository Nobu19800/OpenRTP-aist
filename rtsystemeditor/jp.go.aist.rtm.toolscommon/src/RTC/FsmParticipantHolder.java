package RTC;

/**
* RTC/FsmParticipantHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008�N12��4�� (�ؗj��) 14��12��44�b JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief 
   * @endif
   */
public final class FsmParticipantHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.FsmParticipant value = null;

  public FsmParticipantHolder ()
  {
  }

  public FsmParticipantHolder (RTC.FsmParticipant initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.FsmParticipantHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.FsmParticipantHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.FsmParticipantHelper.type ();
  }

}
