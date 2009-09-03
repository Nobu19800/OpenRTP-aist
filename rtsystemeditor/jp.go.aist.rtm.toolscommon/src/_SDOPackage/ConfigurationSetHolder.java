package _SDOPackage;

/**
* _SDOPackage/ConfigurationSetHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008�N12��4�� (�ؗj��) 12��46��40�b JST
*/

public final class ConfigurationSetHolder implements org.omg.CORBA.portable.Streamable
{
  public _SDOPackage.ConfigurationSet value = null;

  public ConfigurationSetHolder ()
  {
  }

  public ConfigurationSetHolder (_SDOPackage.ConfigurationSet initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = _SDOPackage.ConfigurationSetHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    _SDOPackage.ConfigurationSetHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return _SDOPackage.ConfigurationSetHelper.type ();
  }

}
