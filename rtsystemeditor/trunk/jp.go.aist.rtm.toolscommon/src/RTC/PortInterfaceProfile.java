package RTC;


/**
* RTC/PortInterfaceProfile.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008�N12��4�� (�ؗj��) 14��12��47�b JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief PortInterfaceProfile
   *
   * @section Description
   *
   * PortInterfaceProfile describes an instance of a particular
   * interface as it is exposed by a particular port. These objects
   * are referred to below as the "target interface" and "target
   * port" respectively.
   *
   * @endif
   */
public final class PortInterfaceProfile implements org.omg.CORBA.portable.IDLEntity
{

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief instance_name
     *
     * @section Description
     *
     * This attribute stores the name of the target interface instance.
     *
     * @endif
     */
  public String instance_name = null;

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief type_name
     *
     * @section Description
     *
     * This attribute stores the name of the target interface type.
     *
     * @endif
     */
  public String type_name = null;

  /*!
     * @if jp
     * @brief 
     * @else
     * @brief polarity
     *
     * @section Description
     *
     * This attribute indicates whether the target interface instance
     * is provided or required by the RTC.
     *
     * @endif
     */
  public RTC.PortInterfacePolarity polarity = null;

  public PortInterfaceProfile ()
  {
  } // ctor

  public PortInterfaceProfile (String _instance_name, String _type_name, RTC.PortInterfacePolarity _polarity)
  {
    instance_name = _instance_name;
    type_name = _type_name;
    polarity = _polarity;
  } // ctor

} // class PortInterfaceProfile
