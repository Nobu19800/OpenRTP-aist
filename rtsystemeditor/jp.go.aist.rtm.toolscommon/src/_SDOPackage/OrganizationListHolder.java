package _SDOPackage;


/**
* _SDOPackage/OrganizationListHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008�N12��4�� (�ؗj��) 12��46��40�b JST
*/

public final class OrganizationListHolder implements org.omg.CORBA.portable.Streamable
{
  public _SDOPackage.Organization value[] = null;

  public OrganizationListHolder ()
  {
  }

  public OrganizationListHolder (_SDOPackage.Organization[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = _SDOPackage.OrganizationListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    _SDOPackage.OrganizationListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return _SDOPackage.OrganizationListHelper.type ();
  }

}
