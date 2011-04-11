package OpenRTMNaming;

/**
* OpenRTMNaming/NamingObserverHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: ./OpenRTMNaming.idl
* 2011�N2��10�� 18��15��14�b JST
*/

public final class NamingObserverHolder implements org.omg.CORBA.portable.Streamable
{
  public OpenRTMNaming.NamingObserver value = null;

  public NamingObserverHolder ()
  {
  }

  public NamingObserverHolder (OpenRTMNaming.NamingObserver initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = OpenRTMNaming.NamingObserverHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    OpenRTMNaming.NamingObserverHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return OpenRTMNaming.NamingObserverHelper.type ();
  }

}
