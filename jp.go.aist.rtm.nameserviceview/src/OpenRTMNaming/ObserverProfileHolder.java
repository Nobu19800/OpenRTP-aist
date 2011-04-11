package OpenRTMNaming;

/**
* OpenRTMNaming/ObserverProfileHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: ./OpenRTMNaming.idl
* 2011�N2��10�� 18��15��14�b JST
*/

public final class ObserverProfileHolder implements org.omg.CORBA.portable.Streamable
{
  public OpenRTMNaming.ObserverProfile value = null;

  public ObserverProfileHolder ()
  {
  }

  public ObserverProfileHolder (OpenRTMNaming.ObserverProfile initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = OpenRTMNaming.ObserverProfileHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    OpenRTMNaming.ObserverProfileHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return OpenRTMNaming.ObserverProfileHelper.type ();
  }

}
