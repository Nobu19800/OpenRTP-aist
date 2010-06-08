package jp.go.aist.rtm.rtcbuilder.vbdotnet.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceClassParam;

public class VbDotNetConverter {
	protected Map<String, String> mapType;

	private final String dirIn = "in";
//	private final String dirOut = "out";
//	private final String dirInOut = "inout";

	private final String idlShort = "short";
	private final String idlLong = "long";
	private final String idlLongLong = "longlong";
	private final String idlUnsignedLong = "unsignedlong";
	private final String idlUnsignedLongLong = "unsignedlonglong";
	private final String idlUnsignedShort = "unsignedshort";
	private final String idlFloat = "float";
	private final String idlDouble = "double";

	private final String idlChar = "char";
	private final String idlWChar = "wchar";
	private final String idlString = "string";
	private final String idlWstring = "wstring";

	private final String idlOctet = "octet";
	private final String idlBoolean = "boolean";
	private final String idlAny = "any";

//	private final String idlVoid= "void";
	//
	private final String typeShort = "System.Int16";
	private final String typeLong = "System.Int32";
	private final String typeLongLong = "System.Int64";
	private final String typeString = "System.String";
	private final String typeFloat = "System.Single";
	private final String typeDouble = "System.Double";
	private final String typeChar = "System.Char";
	private final String typeOctet = "System.Byte";
	private final String typeBoolean = "System.Boolean";
	private final String typeAny = "System.Object";
	
	private final String[] escapeName = {"set"};

	//
	private static String projectGUID = null;
	private static String assemblyGUID = null;
	

	public VbDotNetConverter() {
		mapType = new HashMap<String, String>();
		mapType.put(idlShort, typeShort);
		mapType.put(idlLong, typeLong);
		mapType.put(idlUnsignedShort, typeShort);
		mapType.put(idlFloat, typeFloat);
		mapType.put(idlDouble, typeDouble);
		mapType.put(idlLongLong, typeLongLong);
		mapType.put(idlUnsignedLong, typeLong);
		mapType.put(idlUnsignedLongLong, typeLongLong);
		//
		mapType.put(idlString, typeString);
		mapType.put(idlWstring, typeString);
		mapType.put(idlChar, typeChar);
		mapType.put(idlWChar, typeChar);
		mapType.put(idlBoolean, typeBoolean);
		mapType.put(idlOctet, typeOctet);
		mapType.put(idlAny, typeAny);
		//
	}

	public static String getProjectGUID(boolean isTest) {
		if( isTest ) return "e68a065c-3654-4319-9973-1df7c31e45e0";
		if( assemblyGUID==null ) assemblyGUID = UUID.randomUUID().toString();
		return assemblyGUID;
	}

	public static String getAssemblyGUID(boolean isTest) {
		if( isTest ) return "42415f99-e546-4ad7-b017-3c2e6260e83d";
		if( projectGUID==null ) projectGUID = UUID.randomUUID().toString();
		return projectGUID;
	}

	/**
	 * C#�^�Ŏg�p�ł��Ȃ��֐�����escape
	 * 
	 * @param source �Ώۊ֐���
	 * @return escape����
	 */
	public String escapeName(String source) {
		for(int index=0;index<escapeName.length;index++) {
			if(escapeName[index].toLowerCase().equals(source.toLowerCase())) {
				return "_" + source;
			}
		}
		return source;
	}
	
	/**
	 * CORBA�^����C#�^�֌^��ϊ�����(TypeDef�l��)
	 * 
	 * @param strCorba CORBA�^
	 * @return C#�^
	 */
	public String convCORBA2VBdotnetTypeDef(String strCorba, ServiceClassParam scp) {
		String target = strCorba;
		if( target.contains("::")) {
			String[] type = target.split("::");
			target = type[type.length-1];
		}
		String strType = scp.getTypeDef().get(target).getOriginalDef();
		if(strType == null) strType = strCorba;
		strType = convCORBA2VBdotnet(strType);
			
		if(strType.endsWith("[]")) {
			strType = strType.substring(0, strType.length()-2);
			strType = convCORBA2VBdotnet(strType) + "()";
		}
		return strType;
	}
	/**
	 * CORBA�^����C#�^�֌^��ϊ�����
	 * 
	 * @param strCorba CORBA�^
	 * @param scp �T�[�r�X�N���X
	 * @return C#�^
	 */
	public String convCORBA2VBdotnet(String strCorba) {
		String result = mapType.get(strCorba);
		if( result == null ) result = strCorba;

		return result;
	}
	/**
	 * CORBA�^����C#�^�֌^��ϊ�����(�����p,TypeDef�l��)
	 * 
	 * @param strCorba CORBA�^
	 * @param strDirection ���o�͕���
	 * @param scp �T�[�r�X�N���X
	 * @return C#�^
	 */
	public String convCORBA2VBdotnetArg(String strCorba, String strDirection, ServiceClassParam scp) {
		String result = "";
		String target = strCorba;
		if( target.contains("::")) {
			String[] type = target.split("::");
			target = type[type.length-1];
		}
		String strType = scp.getTypeDef().get(target).getOriginalDef();
		if( strType==null ) {
			if( strDirection.equals(dirIn) ) {
				result = mapType.get(strCorba);
				if( result == null ) result = strCorba;
			} else {
				result = mapType.get(strCorba);
				if( result == null ) result = strCorba;
				result += "&";
			}
		} else {
			boolean blnSequence = false;
			if(strType.endsWith("[]")) {
				blnSequence = true;
				strType = strType.substring(0, strType.length()-2);
			}
			result = mapType.get(strType);
			if( result == null ) {
				result = strType;
			}
			if( blnSequence ) result = result + "()";
		}
		return result;
	}
	/**
	 * XML�֑��������G�X�P�[�v����
	 * 
	 * @param type �Ώە�����
	 * @return �擾����
	 */
	public String escapeString(String type) {
		String result = type;
		result = result.replace("<", "&lt;");
		result = result.replace(">", "&gt;");
		return result;
	}
	/**
	 * List�^�̒��g���擾����
	 * 
	 * @param type ���ؑΏی^
	 * @return �擾����
	 */
	public String getListType(String type) {
		int start = type.indexOf('<');
		int end = type.indexOf('>');
		return type.substring(start+1, end);
	}
	/**
	 * List�^�����f����
	 * 
	 * @param type ���ؑΏی^
	 * @return ���،���
	 */
	public boolean isList(String type) {
		if( type.toLowerCase().startsWith("list") )
			return true;
		return false;
	}
	/**
	 * String�^�����f����
	 * 
	 * @param type ���ؑΏی^
	 * @return ���،���
	 */
	public boolean isString(String type) {
		if( type.toLowerCase().equals(idlString) )
			return true;
		return false;
	}
	
	/**
	 * Port�ɐݒ肳�ꂽ�^�̈ꗗ���擾����
	 * 
	 * @param param  RtcParam
	 * @return �^�ꗗ���X�g
	 */
	public static List<String> getPortTypes(RtcParam param) {
		List<String> portTypes = new ArrayList<String>();
		for( DataPortParam dataPort : param.getInports() ) {
			if( !portTypes.contains(dataPort.getType()) ) {
				portTypes.add(dataPort.getType());
			}
		}
		for( DataPortParam dataPort : param.getOutports() ) {
			if( !portTypes.contains(dataPort.getType()) ) {
				portTypes.add(dataPort.getType());
			}
		}
		return portTypes;
	}
	
	/**
	 * �f�[�^�|�[�g�p�̃f�[�^�^imports����Ԃ�
	 * 
	 * @param rtcType �|�[�g�̌^
	 * @return import������
	 */
	public String getDataportPackageName(String rtcType) {
		//module�����t���Ă��Ȃ��f�[�^�^�i::���t���Ă��Ȃ��j��imports���𐶐����Ȃ�
		if(!rtcType.matches(".*::.*")) return "";
		
		//module��=�p�b�P�[�W��
		//struct��=�N���X��
		String importDef = "Imports " + rtcType.replace("::", ".") + ";";
		return importDef;
	}
	
	/**
	 * �f�[�^�|�[�g�������p��module�����J�b�g�����f�[�^�^�N���X����Ԃ�
	 * 
	 * @param rtcType �|�[�g�̌^
	 * @return �N���X��
	 */
	public String getDataTypeName(String rtcType) {
		
		//module�����t���Ă��Ȃ��f�[�^�^�i::���t���Ă��Ȃ��j�͂��̂܂ܕԂ�
		if(!rtcType.matches(".*::.*")) return rtcType;

		String dataTypeNames[] = rtcType.split("::", 0);
		return dataTypeNames[1];
	}

}
