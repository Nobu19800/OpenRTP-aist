package RTC;


/**
* RTC/PortInterfacePolarity.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008�N12��4�� (�ؗj��) 14��12��47�b JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief PortInterfacePolarity
   *
   * @section Description
   *
   * The PortInterfacePolarity enumeration identifies exposed
   * interface instances as provided or required.  @endif
   */
public class PortInterfacePolarity implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 2;
  private static RTC.PortInterfacePolarity[] __array = new RTC.PortInterfacePolarity [__size];

  public static final int _PROVIDED = 0;
  public static final RTC.PortInterfacePolarity PROVIDED = new RTC.PortInterfacePolarity(_PROVIDED);
  public static final int _REQUIRED = 1;
  public static final RTC.PortInterfacePolarity REQUIRED = new RTC.PortInterfacePolarity(_REQUIRED);

  public int value ()
  {
    return __value;
  }

  public static RTC.PortInterfacePolarity from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  protected PortInterfacePolarity (int value)
  {
    __value = value;
    __array[__value] = this;
  }
} // class PortInterfacePolarity
