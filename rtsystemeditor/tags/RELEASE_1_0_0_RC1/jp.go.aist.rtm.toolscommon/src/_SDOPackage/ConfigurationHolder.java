package _SDOPackage;

/**
* _SDOPackage/ConfigurationHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008�N12��4�� (�ؗj��) 12��46��39�b JST
*/

public final class ConfigurationHolder implements org.omg.CORBA.portable.Streamable
{
  public _SDOPackage.Configuration value = null;

  public ConfigurationHolder ()
  {
  }

  public ConfigurationHolder (_SDOPackage.Configuration initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = _SDOPackage.ConfigurationHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    _SDOPackage.ConfigurationHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return _SDOPackage.ConfigurationHelper.type ();
  }

}
