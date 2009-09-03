package RTC;


/**
* RTC/ComponentProfileListHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008�N12��4�� (�ؗj��) 14��12��47�b JST
*/

public final class ComponentProfileListHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.ComponentProfile value[] = null;

  public ComponentProfileListHolder ()
  {
  }

  public ComponentProfileListHolder (RTC.ComponentProfile[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.ComponentProfileListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.ComponentProfileListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.ComponentProfileListHelper.type ();
  }

}
