package _SDOPackage;

/**
* _SDOPackage/EnumerationTypeHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008�N12��4�� (�ؗj��) 12��46��40�b JST
*/

public final class EnumerationTypeHolder implements org.omg.CORBA.portable.Streamable
{
  public _SDOPackage.EnumerationType value = null;

  public EnumerationTypeHolder ()
  {
  }

  public EnumerationTypeHolder (_SDOPackage.EnumerationType initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = _SDOPackage.EnumerationTypeHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    _SDOPackage.EnumerationTypeHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return _SDOPackage.EnumerationTypeHelper.type ();
  }

}
