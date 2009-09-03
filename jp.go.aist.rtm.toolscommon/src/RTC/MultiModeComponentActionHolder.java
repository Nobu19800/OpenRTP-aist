package RTC;

/**
* RTC/MultiModeComponentActionHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008�N12��4�� (�ؗj��) 14��12��45�b JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief MultiModeComponentAction
   *
   * MultiModeComponentAction is a companion to ComponentAction that
   is realized by RTCs that support multiple modes.
   *
   * @endif
   */
public final class MultiModeComponentActionHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.MultiModeComponentAction value = null;

  public MultiModeComponentActionHolder ()
  {
  }

  public MultiModeComponentActionHolder (RTC.MultiModeComponentAction initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.MultiModeComponentActionHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.MultiModeComponentActionHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.MultiModeComponentActionHelper.type ();
  }

}
