package RTC;

/**
* RTC/ConnectorProfileHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008�N12��4�� (�ؗj��) 14��12��47�b JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief ConnectorProfile
   *
   * @section Description
   *
   * The ConnectorProfile contains information about a connection
   * between the ports of collaborating RTCs.
   *
   * @endif
   */
public final class ConnectorProfileHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.ConnectorProfile value = null;

  public ConnectorProfileHolder ()
  {
  }

  public ConnectorProfileHolder (RTC.ConnectorProfile initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.ConnectorProfileHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.ConnectorProfileHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.ConnectorProfileHelper.type ();
  }

}
