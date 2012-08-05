package OpenRTMNaming;


/**
* OpenRTMNaming/ObserverProfile.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: ./OpenRTMNaming.idl
* 2011�N2��10�� 18��15��14�b JST
*/

public final class ObserverProfile implements org.omg.CORBA.portable.IDLEntity
{
  public String id = null;
  public String interface_type = null;
  public OpenRTMNaming.NamingObserver observer = null;

  public ObserverProfile ()
  {
  } // ctor

  public ObserverProfile (String _id, String _interface_type, OpenRTMNaming.NamingObserver _observer)
  {
    id = _id;
    interface_type = _interface_type;
    observer = _observer;
  } // ctor

} // class ObserverProfile
