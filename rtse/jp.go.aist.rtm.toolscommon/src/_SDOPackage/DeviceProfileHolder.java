package _SDOPackage;

/**
* _SDOPackage/DeviceProfileHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008�N12��4�� (�ؗj��) 12��46��40�b JST
*/

public final class DeviceProfileHolder implements org.omg.CORBA.portable.Streamable
{
  public _SDOPackage.DeviceProfile value = null;

  public DeviceProfileHolder ()
  {
  }

  public DeviceProfileHolder (_SDOPackage.DeviceProfile initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = _SDOPackage.DeviceProfileHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    _SDOPackage.DeviceProfileHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return _SDOPackage.DeviceProfileHelper.type ();
  }

}
