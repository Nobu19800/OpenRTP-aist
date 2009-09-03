package RTC;


/**
* RTC/PortInterfaceProfileListHelper.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008�N12��4�� (�ؗj��) 14��12��47�b JST
*/

abstract public class PortInterfaceProfileListHelper
{
  private static String  _id = "IDL:omg.org/RTC/PortInterfaceProfileList:1.0";

  public static void insert (org.omg.CORBA.Any a, RTC.PortInterfaceProfile[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static RTC.PortInterfaceProfile[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = RTC.PortInterfaceProfileHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (RTC.PortInterfaceProfileListHelper.id (), "PortInterfaceProfileList", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static RTC.PortInterfaceProfile[] read (org.omg.CORBA.portable.InputStream istream)
  {
    RTC.PortInterfaceProfile value[] = null;
    int _len0 = istream.read_long ();
    value = new RTC.PortInterfaceProfile[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = RTC.PortInterfaceProfileHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, RTC.PortInterfaceProfile[] value)
  {
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
      RTC.PortInterfaceProfileHelper.write (ostream, value[_i0]);
  }

}
