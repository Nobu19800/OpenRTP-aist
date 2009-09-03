package _SDOPackage;


/**
* _SDOPackage/NumericType.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008�N12��4�� (�ؗj��) 12��46��40�b JST
*/

public class NumericType implements org.omg.CORBA.portable.IDLEntity
{
  private        int __value;
  private static int __size = 4;
  private static _SDOPackage.NumericType[] __array = new _SDOPackage.NumericType [__size];

  public static final int _SHORT_TYPE = 0;
  public static final _SDOPackage.NumericType SHORT_TYPE = new _SDOPackage.NumericType(_SHORT_TYPE);
  public static final int _LONG_TYPE = 1;
  public static final _SDOPackage.NumericType LONG_TYPE = new _SDOPackage.NumericType(_LONG_TYPE);
  public static final int _FLOAT_TYPE = 2;
  public static final _SDOPackage.NumericType FLOAT_TYPE = new _SDOPackage.NumericType(_FLOAT_TYPE);
  public static final int _DOUBLE_TYPE = 3;
  public static final _SDOPackage.NumericType DOUBLE_TYPE = new _SDOPackage.NumericType(_DOUBLE_TYPE);

  public int value ()
  {
    return __value;
  }

  public static _SDOPackage.NumericType from_int (int value)
  {
    if (value >= 0 && value < __size)
      return __array[value];
    else
      throw new org.omg.CORBA.BAD_PARAM ();
  }

  protected NumericType (int value)
  {
    __value = value;
    __array[__value] = this;
  }
} // class NumericType
