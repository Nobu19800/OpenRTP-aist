package RTC;


/**
* RTC/FsmBehaviorProfileListHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008�N12��4�� (�ؗj��) 14��12��47�b JST
*/

public final class FsmBehaviorProfileListHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.FsmBehaviorProfile value[] = null;

  public FsmBehaviorProfileListHolder ()
  {
  }

  public FsmBehaviorProfileListHolder (RTC.FsmBehaviorProfile[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.FsmBehaviorProfileListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.FsmBehaviorProfileListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.FsmBehaviorProfileListHelper.type ();
  }

}
