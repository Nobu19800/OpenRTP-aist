package _SDOPackage;

/**
* _SDOPackage/SDOSystemElementHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008�N12��4�� (�ؗj��) 12��46��39�b JST
*/


/** ------- Interfaces -------*/
public final class SDOSystemElementHolder implements org.omg.CORBA.portable.Streamable
{
  public _SDOPackage.SDOSystemElement value = null;

  public SDOSystemElementHolder ()
  {
  }

  public SDOSystemElementHolder (_SDOPackage.SDOSystemElement initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = _SDOPackage.SDOSystemElementHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    _SDOPackage.SDOSystemElementHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return _SDOPackage.SDOSystemElementHelper.type ();
  }

}
