package OpenRTMNaming;


/**
* OpenRTMNaming/_NamingObserverStub.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: ./OpenRTMNaming.idl
* 2011�N2��10�� 18��15��14�b JST
*/

public class _NamingObserverStub extends org.omg.CORBA.portable.ObjectImpl implements OpenRTMNaming.NamingObserver
{

  public void update ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("update", true);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                update (        );
            } finally {
                _releaseReply ($in);
            }
  } // update

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:OpenRTMNaming/NamingObserver:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.Object obj = org.omg.CORBA.ORB.init (args, props).string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     String str = org.omg.CORBA.ORB.init (args, props).object_to_string (this);
     s.writeUTF (str);
  }
} // class _NamingObserverStub
