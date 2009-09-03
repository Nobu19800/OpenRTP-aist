package RTC;

/**
* RTC/LifeCycleStateHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008�N12��4�� (�ؗj��) 14��12��46�b JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief LifeCycleState
   *
   * @section Description
   * LifeCycleState is an enumeration of the states in the lifecycle above.
   *
   * @endif
   */
public final class LifeCycleStateHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.LifeCycleState value = null;

  public LifeCycleStateHolder ()
  {
  }

  public LifeCycleStateHolder (RTC.LifeCycleState initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.LifeCycleStateHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.LifeCycleStateHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.LifeCycleStateHelper.type ();
  }

}
