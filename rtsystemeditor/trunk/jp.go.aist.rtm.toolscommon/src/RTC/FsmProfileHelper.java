package RTC;


/**
* RTC/FsmProfileHelper.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/RTC10.idl
* 2008�N12��4�� (�ؗj��) 14��12��47�b JST
*/


/*!
   * @if jp
   * @brief 
   * @else
   * @brief FsmProfile
   *
   * @section Description
   *
   * The FsmProfile describes the correspondence between an FSM and
   * its contained FSM participants. This Profile is necessary for
   * Stimulus Response Processing.
   *
   * @endif
   */
abstract public class FsmProfileHelper
{
  private static String  _id = "IDL:omg.org/RTC/FsmProfile:1.0";

  public static void insert (org.omg.CORBA.Any a, RTC.FsmProfile that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static RTC.FsmProfile extract (org.omg.CORBA.Any a)
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
          _tcOf_members0 = RTC.FsmBehaviorProfileHelper.type ();
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_sequence_tc (0, _tcOf_members0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (RTC.FsmBehaviorProfileListHelper.id (), "FsmBehaviorProfileList", _tcOf_members0);
          _members0[0] = new org.omg.CORBA.StructMember (
            "behavior_profiles",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (RTC.FsmProfileHelper.id (), "FsmProfile", _members0);
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

  public static RTC.FsmProfile read (org.omg.CORBA.portable.InputStream istream)
  {
    RTC.FsmProfile value = new RTC.FsmProfile ();
    value.behavior_profiles = RTC.FsmBehaviorProfileListHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, RTC.FsmProfile value)
  {
    RTC.FsmBehaviorProfileListHelper.write (ostream, value.behavior_profiles);
  }

}
