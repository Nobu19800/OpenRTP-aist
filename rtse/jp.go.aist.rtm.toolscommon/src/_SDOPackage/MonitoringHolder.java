package _SDOPackage;

/**
* _SDOPackage/MonitoringHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008�N12��4�� (�ؗj��) 12��46��39�b JST
*/

public final class MonitoringHolder implements org.omg.CORBA.portable.Streamable
{
  public _SDOPackage.Monitoring value = null;

  public MonitoringHolder ()
  {
  }

  public MonitoringHolder (_SDOPackage.Monitoring initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = _SDOPackage.MonitoringHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    _SDOPackage.MonitoringHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return _SDOPackage.MonitoringHelper.type ();
  }

}
