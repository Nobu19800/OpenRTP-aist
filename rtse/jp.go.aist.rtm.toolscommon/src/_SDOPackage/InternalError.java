package _SDOPackage;


/**
* _SDOPackage/InternalError.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008�N12��4�� (�ؗj��) 12��46��40�b JST
*/

public final class InternalError extends org.omg.CORBA.UserException
{
  public String description = null;

  public InternalError ()
  {
    super(InternalErrorHelper.id());
  } // ctor

  public InternalError (String _description)
  {
    super(InternalErrorHelper.id());
    description = _description;
  } // ctor


  public InternalError (String $reason, String _description)
  {
    super(InternalErrorHelper.id() + "  " + $reason);
    description = _description;
  } // ctor

} // class InternalError
