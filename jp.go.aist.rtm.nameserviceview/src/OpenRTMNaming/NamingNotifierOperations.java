package OpenRTMNaming;


/**
* OpenRTMNaming/NamingNotifierOperations.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: ./OpenRTMNaming.idl
* 2011�N2��10�� 18��15��14�b JST
*/

public interface NamingNotifierOperations 
{
  void list_all (OpenRTMNaming.TreeBindingListHolder tbl);
  boolean subscribe (OpenRTMNaming.ObserverProfile oprof);
  boolean unsubscribe (String id);
} // interface NamingNotifierOperations
