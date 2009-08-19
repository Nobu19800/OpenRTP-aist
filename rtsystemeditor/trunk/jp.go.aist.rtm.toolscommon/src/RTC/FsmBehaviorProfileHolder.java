package RTC;

/**
* RTC/FsmBehaviorProfileHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008�N12��4�� (�ؗj��) 14��12��47�b JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief FsmBehaviorProfile
   *
   * @section Description
   *
   * FsmBehaviorProfile represents the association of an FSM
   * participant with a transition, state entry, or state exit in an
   * FSM.
   *
   * @section Semantics
   *
   * The assignment of identifiers to particular transitions, state
   * entries, or state exits is implementation-dependent.
   *
   * @endif
   */
public final class FsmBehaviorProfileHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.FsmBehaviorProfile value = null;

  public FsmBehaviorProfileHolder ()
  {
  }

  public FsmBehaviorProfileHolder (RTC.FsmBehaviorProfile initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.FsmBehaviorProfileHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.FsmBehaviorProfileHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.FsmBehaviorProfileHelper.type ();
  }

}
