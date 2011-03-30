package OpenRTM;


/**
* OpenRTM/ComponentObserverHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: ./OpenRTM.idl
* 2011年2月16日 17時08分39秒 JST
*/

abstract public class ComponentObserverHelper
{
  private static String  _id = "IDL:OpenRTM/ComponentObserver:1.0";

  public static void insert (org.omg.CORBA.Any a, OpenRTM.ComponentObserver that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static OpenRTM.ComponentObserver extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (OpenRTM.ComponentObserverHelper.id (), "ComponentObserver");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static OpenRTM.ComponentObserver read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ComponentObserverStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, OpenRTM.ComponentObserver value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static OpenRTM.ComponentObserver narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof OpenRTM.ComponentObserver)
      return (OpenRTM.ComponentObserver)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      OpenRTM._ComponentObserverStub stub = new OpenRTM._ComponentObserverStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static OpenRTM.ComponentObserver unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof OpenRTM.ComponentObserver)
      return (OpenRTM.ComponentObserver)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      OpenRTM._ComponentObserverStub stub = new OpenRTM._ComponentObserverStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
