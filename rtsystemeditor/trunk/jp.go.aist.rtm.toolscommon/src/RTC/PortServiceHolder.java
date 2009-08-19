package RTC;

/**
* RTC/PortServiceHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008�N12��4�� (�ؗj��) 14��12��45�b JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief PortService
   *
   * @section Description
   *
   * An instance of the PortService interface represents a port (i.e.,
   * UML::Composite Structures::Ports::Port) of an RTC. It provides
   * operations that allow it to be connected to and disconnected from
   * other ports.
   *
   * @section Semantics
   *
   * A port service can support unidirectional or bidirectional
   * communication.  A port service may allow for a service-oriented
   * connection, in which other connected ports, invoke methods on
   * it. It may also allow for a data-centric connection, in which
   * data values are streamed in or out. In either case, the
   * connection is described by an instance of
   * ConnectorProfile. However, the behavioral contracts of such
   * connections are dependent on the interfaces exposed by the ports
   * and are not described normatively by this specification.
   *
   * @endif
   */
public final class PortServiceHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.PortService value = null;

  public PortServiceHolder ()
  {
  }

  public PortServiceHolder (RTC.PortService initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.PortServiceHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.PortServiceHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.PortServiceHelper.type ();
  }

}
