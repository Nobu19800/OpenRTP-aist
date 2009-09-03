package RTC;

/**
* RTC/FsmProfileHolder.java .
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
public final class FsmProfileHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.FsmProfile value = null;

  public FsmProfileHolder ()
  {
  }

  public FsmProfileHolder (RTC.FsmProfile initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.FsmProfileHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.FsmProfileHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.FsmProfileHelper.type ();
  }

}
