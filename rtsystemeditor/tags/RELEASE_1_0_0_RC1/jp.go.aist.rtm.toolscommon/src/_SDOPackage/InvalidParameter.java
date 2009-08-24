package _SDOPackage;


/**
* _SDOPackage/InvalidParameter.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008�N12��4�� (�ؗj��) 12��46��40�b JST
*/

public final class InvalidParameter extends org.omg.CORBA.UserException
{
  public String description = null;

  public InvalidParameter ()
  {
    super(InvalidParameterHelper.id());
  } // ctor

  public InvalidParameter (String _description)
  {
    super(InvalidParameterHelper.id());
    description = _description;
  } // ctor


  public InvalidParameter (String $reason, String _description)
  {
    super(InvalidParameterHelper.id() + "  " + $reason);
    description = _description;
  } // ctor

} // class InvalidParameter
