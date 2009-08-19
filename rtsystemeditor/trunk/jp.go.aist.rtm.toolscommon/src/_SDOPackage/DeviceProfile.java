package _SDOPackage;


/**
* _SDOPackage/DeviceProfile.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008�N12��4�� (�ؗj��) 12��46��40�b JST
*/

public final class DeviceProfile implements org.omg.CORBA.portable.IDLEntity
{
  public String device_type = null;
  public String manufacturer = null;
  public String model = null;
  public String version = null;
  public _SDOPackage.NameValue properties[] = null;

  public DeviceProfile ()
  {
  } // ctor

  public DeviceProfile (String _device_type, String _manufacturer, String _model, String _version, _SDOPackage.NameValue[] _properties)
  {
    device_type = _device_type;
    manufacturer = _manufacturer;
    model = _model;
    version = _version;
    properties = _properties;
  } // ctor

} // class DeviceProfile
