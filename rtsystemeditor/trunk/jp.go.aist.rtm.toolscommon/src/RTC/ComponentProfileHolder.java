package RTC;

/**
* RTC/ComponentProfileHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008�N12��4�� (�ؗj��) 14��12��47�b JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief ComponentProfile
   *
   * @section Description
   *
   * ComponentProfile represents the static state of an RTC that is
   * referred to here as the "target" RTC.
   *
   * @endif
   */
public final class ComponentProfileHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.ComponentProfile value = null;

  public ComponentProfileHolder ()
  {
  }

  public ComponentProfileHolder (RTC.ComponentProfile initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.ComponentProfileHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.ComponentProfileHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.ComponentProfileHelper.type ();
  }

}
