package _SDOPackage;

/**
* _SDOPackage/SDOHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008�N12��4�� (�ؗj��) 12��46��39�b JST
*/

public final class SDOHolder implements org.omg.CORBA.portable.Streamable
{
  public _SDOPackage.SDO value = null;

  public SDOHolder ()
  {
  }

  public SDOHolder (_SDOPackage.SDO initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = _SDOPackage.SDOHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    _SDOPackage.SDOHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return _SDOPackage.SDOHelper.type ();
  }

}
