package RTM;

/**
* RTM/ModuleProfileHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/Manager.idl
* 2008�N12��5�� (���j��) 12��20��51�b JST
*/

public final class ModuleProfileHolder implements org.omg.CORBA.portable.Streamable
{
  public RTM.ModuleProfile value = null;

  public ModuleProfileHolder ()
  {
  }

  public ModuleProfileHolder (RTM.ModuleProfile initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTM.ModuleProfileHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTM.ModuleProfileHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTM.ModuleProfileHelper.type ();
  }

}
