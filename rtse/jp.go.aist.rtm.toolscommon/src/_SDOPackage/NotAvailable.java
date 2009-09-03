package _SDOPackage;


/**
* _SDOPackage/NotAvailable.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008�N12��4�� (�ؗj��) 12��46��40�b JST
*/

public final class NotAvailable extends org.omg.CORBA.UserException
{
  public String description = null;

  public NotAvailable ()
  {
    super(NotAvailableHelper.id());
  } // ctor

  public NotAvailable (String _description)
  {
    super(NotAvailableHelper.id());
    description = _description;
  } // ctor


  public NotAvailable (String $reason, String _description)
  {
    super(NotAvailableHelper.id() + "  " + $reason);
    description = _description;
  } // ctor

} // class NotAvailable
