package _SDOPackage;


/**
* _SDOPackage/SDOListHelper.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008�N12��4�� (�ؗj��) 12��46��40�b JST
*/

abstract public class SDOListHelper
{
  private static String  _id = "IDL:org.omg/SDOPackage/SDOList:1.0";

  public static void insert (org.omg.CORBA.Any a, _SDOPackage.SDO[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static _SDOPackage.SDO[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = _SDOPackage.SDOHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (_SDOPackage.SDOListHelper.id (), "SDOList", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static _SDOPackage.SDO[] read (org.omg.CORBA.portable.InputStream istream)
  {
    _SDOPackage.SDO value[] = null;
    int _len0 = istream.read_long ();
    value = new _SDOPackage.SDO[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = _SDOPackage.SDOHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, _SDOPackage.SDO[] value)
  {
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
      _SDOPackage.SDOHelper.write (ostream, value[_i0]);
  }

}