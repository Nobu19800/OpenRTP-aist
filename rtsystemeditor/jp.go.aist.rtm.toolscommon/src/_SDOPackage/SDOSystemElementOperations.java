package _SDOPackage;


/**
* _SDOPackage/SDOSystemElementOperations.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: svn/jp.go.aist.rtm.systemeditor/idl/SDOPackage11.idl
* 2008�N12��4�� (�ؗj��) 12��46��40�b JST
*/


/** ------- Interfaces -------*/
public interface SDOSystemElementOperations 
{
  _SDOPackage.Organization[] get_owned_organizations () throws _SDOPackage.NotAvailable, _SDOPackage.InternalError;
} // interface SDOSystemElementOperations
