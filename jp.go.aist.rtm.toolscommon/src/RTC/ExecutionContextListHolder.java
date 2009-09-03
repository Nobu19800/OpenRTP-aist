package RTC;


/**
* RTC/ExecutionContextListHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008�N12��4�� (�ؗj��) 14��12��46�b JST
*/

public final class ExecutionContextListHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.ExecutionContext value[] = null;

  public ExecutionContextListHolder ()
  {
  }

  public ExecutionContextListHolder (RTC.ExecutionContext[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.ExecutionContextListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.ExecutionContextListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.ExecutionContextListHelper.type ();
  }

}
