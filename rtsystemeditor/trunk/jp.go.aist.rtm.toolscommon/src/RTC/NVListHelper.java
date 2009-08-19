package RTC;


/**
* RTC/NVListHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008年12月4日 (木曜日) 14時12分46秒 JST
*/

abstract public class NVListHelper
{
  private static String  _id = "IDL:omg.org/RTC/NVList:1.0";

  public static void insert (org.omg.CORBA.Any a, _SDOPackage.NameValue[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static _SDOPackage.NameValue[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = _SDOPackage.NameValueHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (_SDOPackage.NVListHelper.id (), "NVList", __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (RTC.NVListHelper.id (), "NVList", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static _SDOPackage.NameValue[] read (org.omg.CORBA.portable.InputStream istream)
  {
    _SDOPackage.NameValue value[] = null;
    value = _SDOPackage.NVListHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, _SDOPackage.NameValue[] value)
  {
    _SDOPackage.NVListHelper.write (ostream, value);
  }

}
