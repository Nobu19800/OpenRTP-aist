package RTC;

/**
* RTC/ModeHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008�N12��4�� (�ؗj��) 14��12��44�b JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief Mode
   *
   * @section Description
   *
   * Each mode defined by a given RTC shall be represented by an
   * instance of Mode.
   *
   * @endif
   */
public final class ModeHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.Mode value = null;

  public ModeHolder ()
  {
  }

  public ModeHolder (RTC.Mode initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.ModeHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.ModeHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.ModeHelper.type ();
  }

}
