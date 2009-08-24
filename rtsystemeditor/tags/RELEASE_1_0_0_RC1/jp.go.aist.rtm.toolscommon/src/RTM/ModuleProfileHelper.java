package RTM;


/**
* RTM/ModuleProfileHelper.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: svn/jp.go.aist.rtm.systemeditor/idl/Manager.idl
* 2008年12月5日 (金曜日) 12時20分51秒 JST
*/

abstract public class ModuleProfileHelper
{
  private static String  _id = "IDL:RTM/ModuleProfile:1.0";

  public static void insert (org.omg.CORBA.Any a, RTM.ModuleProfile that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static RTM.ModuleProfile extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [1];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = _SDOPackage.NameValueHelper.type ();
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_sequence_tc (0, _tcOf_members0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (_SDOPackage.NVListHelper.id (), "NVList", _tcOf_members0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (RTM.NVListHelper.id (), "NVList", _tcOf_members0);
          _members0[0] = new org.omg.CORBA.StructMember (
            "properties",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (RTM.ModuleProfileHelper.id (), "ModuleProfile", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static RTM.ModuleProfile read (org.omg.CORBA.portable.InputStream istream)
  {
    RTM.ModuleProfile value = new RTM.ModuleProfile ();
    value.properties = _SDOPackage.NVListHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, RTM.ModuleProfile value)
  {
    _SDOPackage.NVListHelper.write (ostream, value.properties);
  }

}
