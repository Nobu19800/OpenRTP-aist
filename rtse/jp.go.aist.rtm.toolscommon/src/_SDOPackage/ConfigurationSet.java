package _SDOPackage;


/**
* _SDOPackage/ConfigurationSet.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008�N12��4�� (�ؗj��) 12��46��40�b JST
*/

public final class ConfigurationSet implements org.omg.CORBA.portable.IDLEntity
{
  public String id = null;
  public String description = null;
  public _SDOPackage.NameValue configuration_data[] = null;

  public ConfigurationSet ()
  {
  } // ctor

  public ConfigurationSet (String _id, String _description, _SDOPackage.NameValue[] _configuration_data)
  {
    id = _id;
    description = _description;
    configuration_data = _configuration_data;
  } // ctor

} // class ConfigurationSet
