package jp.go.aist.rtm.rtcbuilder;

public interface IRTCBMessageConstantsOld {
	
	public static final String VALIDATE_ERROR_OUTPUTPROJECT = "OutputProject���w�肳��Ă��܂���B";
	public static final String VALIDATE_ERROR_COMPONENTNAME = "Component Name���w�肳��Ă��܂���B";
	public static final String VALIDATE_ERROR_PORTSAMENAME = "Port�ɓ������O�����݂��܂��B :";
	public static final String VALIDATE_ERROR_INTERFACESAMENAME = "Provider��������Consumer�ɓ������O�����݂��܂��B :";
	//
	public static final String ERROR_IDL_DIRECTORY_NOT_FOUND = "Include IDL �̃f�B���N�g����������܂���B";
	public static final String ERROR_GENERATE_FAILED = "�v���W�F�N�g�̐����Ɏ��s���܂���";
	//
	public static final String ERROR_PREPROCESSOR = "#include����IDL�̃f�B���N�g�����w�肵�Ă��������B" + System.getProperty("line.separator") + "path�������ł��܂���@:";
	public static final String ERROR_IDLPARSE = " : is undefined in idl";
	public static final String ERROR_IDLTYPEDUPLICAT = "Type definition is duplicated.";
	//
	public static final String ERROR_PROFILE_RESTORE = "�t�@�C���̓ǂݍ��݂Ɏ��s���܂����B" + System.getProperty("line.separator") + "RtcBuilder�ȊO�̃t�@�C�����ǂݍ��܂�Ă��Ȃ����m�F���Ă�������";
	//
	//
	public static final String CONFIRM_PROJECT_GENERATE_TITLE = "�v���W�F�N�g����";
	public static final String CONFIRM_PROJECT_GENERATE = "�w�肳�ꂽ�v���W�F�N�g��Workspace���ɑ��݂��܂���B" + System.getProperty("line.separator") + 
															"�V�K�ɐ������Ă���낵���ł����H";

}
