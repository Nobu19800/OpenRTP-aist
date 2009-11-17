package jp.go.aist.rtm.rtcbuilder.template.cpp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceArgumentParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceClassParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceMethodParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.TypeDefParam;
import jp.go.aist.rtm.rtcbuilder.template.TemplateHelper;

/**
 * CXX�\�[�X���o�͂���ۂɎg�p����郆�[�e�B���e�B
 */
public class CXXConverter {
	protected Map<String, String> mapType;
	
	private final String idlLongLong = "longlong";
	private final String idlLong = "long";
	private final String idlUnsignedLong = "unsignedlong";
	private final String idlUnsignedLongLong = "unsignedlonglong";
	private final String idlShort = "short";
	private final String idlUnsignedShort = "unsignedshort";
	private final String idlFloat = "float";
	private final String idlDouble = "double";
	private final String idlLongDouble = "longdouble";
	private final String idlChar = "char";
	private final String idlWchar = "wchar";
	private final String idlOctet = "octet";
	private final String idlBoolean = "boolean";
	private final String idlString = "string";
	private final String idlWstring = "wstring";
	private final String idlAny = "any";
	private final String idlVoid= "void";

	private final String cppLongLong = "CORBA::LongLong";
	private final String cppLong = "CORBA::Long";
	private final String cppUnsignedLong = "CORBA::ULong";
	private final String cppUnsignedLongLong = "CORBA::ULongLong";
	private final String cppShort = "CORBA::Short";
	private final String cppUnsignedShort = "CORBA::UShort";
	private final String cppFloat = "CORBA::Float";
	private final String cppDouble = "CORBA::Double";
	private final String cppLongDouble = "CORBA::LongDouble";
	private final String cppChar = "CORBA::Char";
	private final String cppWchar = "CORBA::WChar";
	private final String cppOctet = "CORBA::Octet";
	private final String cppBoolean = "CORBA::Boolean";
	private final String cppString = "char*";
	private final String cppWstring = "CORBA::WChar*";
	private final String cppAny = "CORBA::Any*";
	private final String cppVoid= "void";

	public CXXConverter() {
		mapType = new HashMap<String, String>();
		mapType.put(idlLongLong, cppLongLong);
		mapType.put(idlLong, cppLong);
		mapType.put(idlUnsignedLong, cppUnsignedLong);
		mapType.put(idlUnsignedLongLong, cppUnsignedLongLong);
		mapType.put(idlShort, cppShort);
		mapType.put(idlUnsignedShort, cppUnsignedShort);
		mapType.put(idlFloat, cppFloat);
		mapType.put(idlDouble, cppDouble);
		mapType.put(idlLongDouble, cppLongDouble);
		mapType.put(idlChar, cppChar);
		mapType.put(idlWchar, cppWchar);
		mapType.put(idlOctet, cppOctet);
		mapType.put(idlBoolean, cppBoolean);
		mapType.put(idlString, cppString);
		mapType.put(idlWstring, cppWstring);
		mapType.put(idlAny, cppAny);
		mapType.put(idlVoid, cppVoid);
	}

	/**
	 * �T�[�r�X�X�^�u�����擾����
	 * 
	 * @param serviceClassParam
	 *            ServiceClassParam
	 * @return
	 */
	public static StringBuffer getSvcStubName(
			ServiceClassParam serviceClassParam) {
		StringBuffer result = new StringBuffer();

		result.append(serviceClassParam.getName());
		if (TemplateHelper.getServiceStubSuffix() != null
				&& !TemplateHelper.getServiceStubSuffix().equals(""))
			result.append(TemplateHelper.getServiceStubSuffix());
		else
			result.append(IRtcBuilderConstants.DEFAULT_SVC_STUB_SUFFIX);
		return result;
	}

	/**
	 * �T�[�r�X�X�P���g�������擾����
	 * 
	 * @param serviceClassParam
	 *            ServiceClassParam
	 * @return
	 */
	public static StringBuffer getSvcSkelName(
			ServiceClassParam serviceClassParam) {
		StringBuffer result = new StringBuffer();

		result.append(serviceClassParam.getName());
		if (TemplateHelper.getServiceSkelSuffix() != null
				&& !TemplateHelper.getServiceSkelSuffix().equals(""))
			result.append(TemplateHelper.getServiceSkelSuffix());
		else
			result.append(IRtcBuilderConstants.DEFAULT_SVC_SKEL_SUFFIX);
		return result;
	}

	/**
	 * �T�[�r�X�C���v�������擾����
	 * 
	 * @param serviceClassParam
	 *            ServiceClassParam
	 * @return
	 */
	public static StringBuffer getSvcImplName(
			ServiceClassParam serviceClassParam) {
		StringBuffer result = new StringBuffer();

		result.append(serviceClassParam.getName());
		if (!TemplateHelper.getServiceImplSuffix().equals(""))
			result.append(TemplateHelper.getServiceImplSuffix());
		else
			result.append(IRtcBuilderConstants.DEFAULT_SVC_IMPL_SUFFIX);
		return result;
	}

	/**
	 * CPP�^����CORBA�^�ւ̃}�b�s���O���`����
	 * 
	 * @param strcpp
	 *            CPP�^
	 * @return CORBA�^
	 */
	public String convCpp2CORBA(ServiceMethodParam typeDef) {
		String result = mapType.get(typeDef.getType());
		if( result == null ) {
			result = typeDef.getType();
			if( !typeDef.getType().contains("::") && typeDef.isSequence()) {
				result = result + "*";
			}
		}
		return result;
	}

	/**
	 * CPP�^����CORBA�^�ւ̃}�b�s���O���`����(�����p)
	 * 
	 * @param strType
	 *            CPP�^
	 * @param strDir
	 *            ���o��
	 * @return CORBA�^
	 */
	public String convCpp2CORBAforArg(ServiceArgumentParam typeDef) {
		String result = null;
		
		result = mapType.get(typeDef.getType());
		if( result == null ) {
			result = typeDef.getType();
//			if( !typeDef.getType().contains("::") && typeDef.isSequence()) {
//				result = result + "*";
//			}
		}
		
		if(typeDef.getType().equals("string")) {
			if(typeDef.getDirection().equals("in"))
				result = "const char*";
			else if(typeDef.getDirection().equals("out"))
				result = "CORBA::String_out";
			else if(typeDef.getDirection().equals("inout"))
				result = "char*&";
		} else if(typeDef.getType().equals("wstring")) {
			if(typeDef.getDirection().equals("in"))
				result = "const CORBA::WChar*";
			else if(typeDef.getDirection().equals("out"))
				result = "CORBA::WString_out";
			else if(typeDef.getDirection().equals("inout"))
				result = "CORBA::WChar*&";
		} else if(typeDef.getType().equals("any")) {
			if(typeDef.getDirection().equals("in"))
				result = "const CORBA::Any&";
			else if(typeDef.getDirection().equals("out"))
				result = "CORBA::Any_OUT_arg";
			else if(typeDef.getDirection().equals("inout"))
				result = "CORBA::Any&";
		} else {
			if(typeDef.getDirection().equals("out") || typeDef.getDirection().equals("inout"))
				result = result + "&";
			if( !result.contains("CORBA::") ) {
				if(result.contains("::")) result = "const " + result + "&";
			}
		}

		return result;
	}

	/**
	 * �p�b�P�[�W�̋�؂蕶�����u.�v����u::�v�ɕύX����
	 * @param fullName
	 * @return
	 */
	public static String convertDelimiter(String fullName) {
		return fullName.replaceAll("\\.", "::");
	}
	
	/**
	 * Port�ɐݒ肳�ꂽModule�̈ꗗ���擾����
	 * 
	 * @param param  RtcParam
	 * @return Module�ꗗ���X�g
	 */
	public static List<String> getPortModules(RtcParam param) {
		List<String> modules = new ArrayList<String>();
		String dataTypeString[];
		for( DataPortParam dataPort : param.getInports() ) {
			dataTypeString = dataPort.getType().split("::", 0);
			if( !dataPort.getType().contains("::")) dataTypeString[0] = "RTC";
			if( !modules.contains(dataTypeString[0]) && !dataTypeString[0].equals("RTC") ) {
				modules.add(dataTypeString[0]);
			}
		}
		for( DataPortParam dataPort : param.getOutports() ) {
			dataTypeString = dataPort.getType().split("::", 0);
			if( !dataPort.getType().contains("::")) dataTypeString[0] = "RTC";
			if( !modules.contains(dataTypeString[0]) && !dataTypeString[0].equals("RTC") ) {
				modules.add(dataTypeString[0]);
			}
		}
		return modules;
	}
	
	/**
	 * �f�[�^�|�[�g�p�̃f�[�^�^include����Ԃ�
	 * 
	 * @param rtcType �|�[�g�̌^
	 * @return using������
	 */
	public String getDataportIncludeName(String rtcType) {
		if(rtcType.matches("RTC.*")) return "";
		return "#include <rtm/idl/" + rtcType + ".h>";
	}

	/**
	 * �f�[�^�|�[�g�p�̃f�[�^�^using namespace ����Ԃ�
	 * 
	 * @param rtcType �|�[�g�̌^
	 * @return using������
	 */
	public String getDataportUsingNamespace(String rtcType) {
		if(rtcType.matches("RTC.*")) return "";
		return "using namespace " + rtcType + ";";
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
