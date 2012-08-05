package OpenRTMNaming;

/**
* OpenRTMNaming/TreeBindingHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: ./OpenRTMNaming.idl
* 2011�N2��10�� 18��15��14�b JST
*/

public final class TreeBindingHolder implements org.omg.CORBA.portable.Streamable
{
  public OpenRTMNaming.TreeBinding value = null;

  public TreeBindingHolder ()
  {
  }

  public TreeBindingHolder (OpenRTMNaming.TreeBinding initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = OpenRTMNaming.TreeBindingHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    OpenRTMNaming.TreeBindingHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return OpenRTMNaming.TreeBindingHelper.type ();
  }

}
