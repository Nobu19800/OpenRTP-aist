package RTC;


/**
* RTC/ComponentActionHelper.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008�N12��4�� (�ؗj��) 14��12��44�b JST
*/


/*!
   * @if jp
   * @brief
   * @else
   * @brief ComponentAction
   *
   * @section Description
   *
   * The ComponentAction interface provides callbacks corresponding to
   * the execution of the lifecycle operations of LightweightRTObject
   * (see Section 5.2.2.2) and ExecutionContext (see Section
   * 5.2.2.5). An RTC developer may implement these callback
   * operations in order to execute application-specific logic
   * pointing response to those transitions.
   *
   * @section Semantics
   *
   * Clients of an RTC are not expected to invoke these operations
   * directly; they are provided for the benefit of the RTC middleware
   * implementation.
   *
   * @endif
   */
abstract public class ComponentActionHelper
{
  private static String  _id = "IDL:omg.org/RTC/ComponentAction:1.0";

  public static void insert (org.omg.CORBA.Any a, RTC.ComponentAction that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static RTC.ComponentAction extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (RTC.ComponentActionHelper.id (), "ComponentAction");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static RTC.ComponentAction read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ComponentActionStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, RTC.ComponentAction value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static RTC.ComponentAction narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof RTC.ComponentAction)
      return (RTC.ComponentAction)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      RTC._ComponentActionStub stub = new RTC._ComponentActionStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
