package OpenRTMNaming;

/**
* OpenRTMNaming/NamingNotifierHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: ./OpenRTMNaming.idl
* 2011�N2��10�� 18��15��14�b JST
*/

public final class NamingNotifierHolder implements org.omg.CORBA.portable.Streamable
{
  public OpenRTMNaming.NamingNotifier value = null;

  public NamingNotifierHolder ()
  {
  }

  public NamingNotifierHolder (OpenRTMNaming.NamingNotifier initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = OpenRTMNaming.NamingNotifierHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    OpenRTMNaming.NamingNotifierHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return OpenRTMNaming.NamingNotifierHelper.type ();
  }

}
