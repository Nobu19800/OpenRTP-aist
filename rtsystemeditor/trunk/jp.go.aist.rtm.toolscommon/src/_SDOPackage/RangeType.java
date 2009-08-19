package _SDOPackage;


/**
* _SDOPackage/RangeType.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008�N12��4�� (�ؗj��) 12��46��40�b JST
*/

public final class RangeType implements org.omg.CORBA.portable.IDLEntity
{
  public _SDOPackage.Numeric min = null;
  public _SDOPackage.Numeric max = null;
  public boolean min_inclusive = false;
  public boolean max_inclusive = false;

  public RangeType ()
  {
  } // ctor

  public RangeType (_SDOPackage.Numeric _min, _SDOPackage.Numeric _max, boolean _min_inclusive, boolean _max_inclusive)
  {
    min = _min;
    max = _max;
    min_inclusive = _min_inclusive;
    max_inclusive = _max_inclusive;
  } // ctor

} // class RangeType
