package RTC;

/**
* RTC/ExecutionKindHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008�N12��4�� (�ؗj��) 14��12��46�b JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief ExecutionKind
   * 
   * @sectioni Description
   *
   * The ExecutionKind enumeration defines the execution semantics
   * (see Section 5.3) of the RTCs that participate in an execution
   * context.
   *
   * @endif
   */
public final class ExecutionKindHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.ExecutionKind value = null;

  public ExecutionKindHolder ()
  {
  }

  public ExecutionKindHolder (RTC.ExecutionKind initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.ExecutionKindHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.ExecutionKindHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.ExecutionKindHelper.type ();
  }

}
