package RTC;


/**
* RTC/ConnectorProfileListHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008�N12��4�� (�ؗj��) 14��12��47�b JST
*/

public final class ConnectorProfileListHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.ConnectorProfile value[] = null;

  public ConnectorProfileListHolder ()
  {
  }

  public ConnectorProfileListHolder (RTC.ConnectorProfile[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.ConnectorProfileListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.ConnectorProfileListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.ConnectorProfileListHelper.type ();
  }

}
