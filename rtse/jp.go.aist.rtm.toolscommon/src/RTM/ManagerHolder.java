package RTM;

/**
* RTM/ManagerHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/Manager.idl
* 2008�N12��5�� (���j��) 12��20��51�b JST
*/

public final class ManagerHolder implements org.omg.CORBA.portable.Streamable
{
  public RTM.Manager value = null;

  public ManagerHolder ()
  {
  }

  public ManagerHolder (RTM.Manager initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTM.ManagerHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTM.ManagerHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTM.ManagerHelper.type ();
  }

}
