package _SDOPackage;


/**
* _SDOPackage/InterfaceNotImplemented.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008�N12��4�� (�ؗj��) 12��46��40�b JST
*/

public final class InterfaceNotImplemented extends org.omg.CORBA.UserException
{
  public String description = null;

  public InterfaceNotImplemented ()
  {
    super(InterfaceNotImplementedHelper.id());
  } // ctor

  public InterfaceNotImplemented (String _description)
  {
    super(InterfaceNotImplementedHelper.id());
    description = _description;
  } // ctor


  public InterfaceNotImplemented (String $reason, String _description)
  {
    super(InterfaceNotImplementedHelper.id() + "  " + $reason);
    description = _description;
  } // ctor

} // class InterfaceNotImplemented
