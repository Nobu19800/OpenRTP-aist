package RTC;

/**
* RTC/MultiModeObjectHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008�N12��4�� (�ؗj��) 14��12��45�b JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief 
   * @endif
   */
public final class MultiModeObjectHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.MultiModeObject value = null;

  public MultiModeObjectHolder ()
  {
  }

  public MultiModeObjectHolder (RTC.MultiModeObject initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.MultiModeObjectHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.MultiModeObjectHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.MultiModeObjectHelper.type ();
  }

}
