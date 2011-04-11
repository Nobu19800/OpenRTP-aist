package OpenRTMNaming;


/**
* OpenRTMNaming/TreeBindingListHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: ./OpenRTMNaming.idl
* 2011�N2��10�� 18��15��14�b JST
*/

public final class TreeBindingListHolder implements org.omg.CORBA.portable.Streamable
{
  public OpenRTMNaming.TreeBinding value[] = null;

  public TreeBindingListHolder ()
  {
  }

  public TreeBindingListHolder (OpenRTMNaming.TreeBinding[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = OpenRTMNaming.TreeBindingListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    OpenRTMNaming.TreeBindingListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return OpenRTMNaming.TreeBindingListHelper.type ();
  }

}
