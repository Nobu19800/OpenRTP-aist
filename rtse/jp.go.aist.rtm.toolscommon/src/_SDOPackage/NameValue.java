package _SDOPackage;


/**
* _SDOPackage/NameValue.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008�N12��4�� (�ؗj��) 12��46��40�b JST
*/

public final class NameValue implements org.omg.CORBA.portable.IDLEntity
{
  public String name = null;
  public org.omg.CORBA.Any value = null;

  public NameValue ()
  {
  } // ctor

  public NameValue (String _name, org.omg.CORBA.Any _value)
  {
    name = _name;
    value = _value;
  } // ctor

} // class NameValue
