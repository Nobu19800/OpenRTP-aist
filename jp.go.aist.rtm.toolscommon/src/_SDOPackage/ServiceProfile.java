package _SDOPackage;


/**
* _SDOPackage/ServiceProfile.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008�N12��4�� (�ؗj��) 12��46��40�b JST
*/

public final class ServiceProfile implements org.omg.CORBA.portable.IDLEntity
{
  public String id = null;
  public String interface_type = null;
  public _SDOPackage.NameValue properties[] = null;
  public _SDOPackage.SDOService service = null;

  public ServiceProfile ()
  {
  } // ctor

  public ServiceProfile (String _id, String _interface_type, _SDOPackage.NameValue[] _properties, _SDOPackage.SDOService _service)
  {
    id = _id;
    interface_type = _interface_type;
    properties = _properties;
    service = _service;
  } // ctor

} // class ServiceProfile
