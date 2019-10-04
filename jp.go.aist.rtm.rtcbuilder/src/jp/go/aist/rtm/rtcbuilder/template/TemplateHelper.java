package jp.go.aist.rtm.rtcbuilder.template;

import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.DEFAULT_SVC_IMPL_SUFFIX;
import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.DEFAULT_SVC_SKEL_SUFFIX;
import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.DEFAULT_SVC_STUB_SUFFIX;
import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.DOC_AUTHOR_OFFSET;
import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.DOC_CONSTRAINT_OFFSET;
import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.DOC_CONSTRAINT_PREFIX;
import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.DOC_CYCLE_OFFSET;
import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.DOC_CYCLE_PREFIX;
import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.DOC_DEFAULT_OFFSET;
import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.DOC_DEFAULT_PREFIX;
import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.DOC_DEFAULT_WIDTH;
import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.DOC_DESC_OFFSET;
import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.DOC_DESC_PREFIX;
import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.DOC_INTERFACE_DETAIL_OFFSET;
import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.DOC_INTERFACE_DETAIL_PREFIX;
import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.DOC_INTERFACE_OFFSET;
import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.DOC_INTERFACE_PREFIX;
import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.DOC_NUMBER_OFFSET;
import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.DOC_NUMBER_PREFIX;
import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.DOC_POST_OFFSET;
import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.DOC_PRE_OFFSET;
import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.DOC_RANGE_OFFSET;
import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.DOC_RANGE_PREFIX;
import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.DOC_SEMANTICS_OFFSET;
import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.DOC_SEMANTICS_PREFIX;
import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.DOC_UNIT_OFFSET;
import static jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants.DOC_UNIT_PREFIX;
import static jp.go.aist.rtm.rtcbuilder.util.StringUtil.splitString;

import java.io.File;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.fsm.EventParam;
import jp.go.aist.rtm.rtcbuilder.fsm.StateParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ConfigSetParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.EventPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.PropertyParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.IdlFileParam;
import jp.go.aist.rtm.rtcbuilder.util.RTCUtil;

/**
 * テンプレートを出力する際に使用されるヘルパー
 */
public class TemplateHelper {

	/**
	 * ベース名を取得する
	 * 
	 * @param fullName
	 * @return
	 */
	public static String getBasename(String fullName) {
		String[] split = fullName.split("::");
		return split[split.length - 1];
	}

	/*
	 *  Appebd By I.Hara 2015/06
	 **/
	public static String getBasename2(String fullName) {
		return fullName.replaceAll("::", "_");
	}
	/**
	 * ファイル名を取得する
	 * 
	 * @param fullPath
	 * @return
	 */
	public static String getFileName(String fullPath) {
		if (fullPath == null)
			return "";
		File file = new File(fullPath);
		return file.getName();
	}

	/**
	 * 拡張子無しファイル名を取得する
	 * 
	 * @param fullPath
	 * @return
	 */
	public static String getFilenameNoExt(String fullPath) {
		String fileName = getFileName(fullPath);
		if (fileName == null)
			return "";
		int index = fileName.lastIndexOf('.');
		if (index > 0 && index < fileName.length() - 1) {
			return fileName.substring(0, index);
		}
		return "";
	}

	public static String getIDLFilesforIDLCMake(RtcParam source) {
		StringBuilder builder = new StringBuilder();
		
		for(IdlFileParam target : source.getProviderIdlPathes() ) {
			if(RTCUtil.checkDefault(target.getIdlPath(), source.getParent().getDataTypeParams())) continue;
			builder.append("${CMAKE_CURRENT_SOURCE_DIR}/");
			builder.append(getFilenameNoExt(target.getIdlFile()));
			builder.append(".idl ");
		}
		for(IdlFileParam target : source.getConsumerIdlPathes() ) {
			if(RTCUtil.checkDefault(target.getIdlPath(), source.getParent().getDataTypeParams())) continue;
			builder.append("${CMAKE_CURRENT_SOURCE_DIR}/");
			builder.append(getFilenameNoExt(target.getIdlFile()));
			builder.append(".idl ");
		}
		for(IdlFileParam target : source.getIncludedIdlPathes() ) {
			if(RTCUtil.checkDefault(target.getIdlPath(), source.getParent().getDataTypeParams())) continue;
			builder.append("${CMAKE_CURRENT_SOURCE_DIR}/");
			builder.append(getFilenameNoExt(target.getIdlFile()));
			builder.append(".idl ");
		}
		return builder.toString();
	}
	
	public static String getIDLFiles(RtcParam source) {
		StringBuilder builder = new StringBuilder();
		
		for(IdlFileParam target : source.getProviderIdlPathes() ) {
			if(RTCUtil.checkDefault(target.getIdlPath(), source.getParent().getDataTypeParams())) continue;
			builder.append(getFilenameNoExt(target.getIdlFile()));
			builder.append(".idl ");
		}
		for(IdlFileParam target : source.getConsumerIdlPathes() ) {
			if(RTCUtil.checkDefault(target.getIdlPath(), source.getParent().getDataTypeParams())) continue;
			builder.append(getFilenameNoExt(target.getIdlFile()));
			builder.append(".idl ");
		}
		for(IdlFileParam target : source.getIncludedIdlPathes() ) {
			if(RTCUtil.checkDefault(target.getIdlPath(), source.getParent().getDataTypeParams())) continue;
			builder.append(getFilenameNoExt(target.getIdlFile()));
			builder.append(".idl ");
		}
		return builder.toString();
	}
	
	public static String toSvcImpl(String fullPath) {
		String name = getFilenameNoExt(fullPath);
		if (name.isEmpty()) {
			return name;
		}
		return name + getServiceImplSuffix();
	}

	public static String toSvcSkel(String fullPath) {
		String name = getFilenameNoExt(fullPath);
		if (name.isEmpty()) {
			return name;
		}
		return name + getServiceSkelSuffix();
	}

	public static String toSvcStub(String fullPath) {
		String name = getFilenameNoExt(fullPath);
		if (name.isEmpty()) {
			return name;
		}
		return name + getServiceStubSuffix();
	}

	public String convFormat(String source) {
		return source.replace("::", ".");
	}

	public boolean isModule(String source) {
		return source.contains("::");
	}

	public boolean isCpp(RtcParam source) {
		return source.getLangList().contains(IRtcBuilderConstants.LANG_CPP);
	}
	
	public static String getServiceImplSuffix() {
		return DEFAULT_SVC_IMPL_SUFFIX;
	}

	public static String getServiceSkelSuffix() {
		return DEFAULT_SVC_SKEL_SUFFIX;
	}

	public static String getServiceStubSuffix() {
		return DEFAULT_SVC_STUB_SUFFIX;
	}

	public String convertDoc(String source) {
		return splitString(source, DOC_DEFAULT_WIDTH, DOC_DEFAULT_PREFIX,
				DOC_DEFAULT_OFFSET);
	}

	public String convertDescDoc(String source) {
		return splitString(source, DOC_DEFAULT_WIDTH, DOC_DESC_PREFIX,
				DOC_DESC_OFFSET);
	}

	public String convertPreDoc(String source) {
		return splitString(source, DOC_DEFAULT_WIDTH, DOC_DESC_PREFIX,
				DOC_PRE_OFFSET);
	}

	public String convertPostDoc(String source) {
		return splitString(source, DOC_DEFAULT_WIDTH, DOC_DESC_PREFIX,
				DOC_POST_OFFSET);
	}

	public String convertUnitDoc(String source) {
		return splitString(source, DOC_DEFAULT_WIDTH, DOC_UNIT_PREFIX,
				DOC_UNIT_OFFSET);
	}

	public String convertRangeDoc(String source) {
		return splitString(source, DOC_DEFAULT_WIDTH, DOC_RANGE_PREFIX,
				DOC_RANGE_OFFSET);
	}

	public String convertConstraintDoc(String source) {
		return splitString(source, DOC_DEFAULT_WIDTH, DOC_CONSTRAINT_PREFIX,
				DOC_CONSTRAINT_OFFSET);
	}

	public String convertTypeDoc(String source) {
		return splitString(source, DOC_DEFAULT_WIDTH, DOC_UNIT_PREFIX,
				DOC_UNIT_OFFSET);
	}

	public String convertNumberDoc(String source) {
		return splitString(source, DOC_DEFAULT_WIDTH, DOC_NUMBER_PREFIX,
				DOC_NUMBER_OFFSET);
	}

	public String convertSemanticsDoc(String source) {
		return splitString(source, DOC_DEFAULT_WIDTH, DOC_SEMANTICS_PREFIX,
				DOC_SEMANTICS_OFFSET);
	}

	public String convertFrequencyDoc(String source) {
		return splitString(source, DOC_DEFAULT_WIDTH, DOC_SEMANTICS_PREFIX,
				DOC_SEMANTICS_OFFSET);
	}

	public String convertCycleDoc(String source) {
		return splitString(source, DOC_DEFAULT_WIDTH, DOC_CYCLE_PREFIX,
				DOC_CYCLE_OFFSET);
	}

	public String convertInterfaceDoc(String source) {
		return splitString(source, DOC_DEFAULT_WIDTH, DOC_INTERFACE_PREFIX,
				DOC_INTERFACE_OFFSET);
	}

	public String convertInterfaceDetailDoc(String source) {
		return splitString(source, DOC_DEFAULT_WIDTH,
				DOC_INTERFACE_DETAIL_PREFIX, DOC_INTERFACE_DETAIL_OFFSET);
	}

	public String convertAuthorDoc(String source) {
		return splitString(source, DOC_DEFAULT_WIDTH, DOC_DEFAULT_PREFIX,
				DOC_AUTHOR_OFFSET);
	}

	public static String toLower(String s) {
		return (s == null) ? "" : s.toLowerCase();
	}

	public static String getVerMajor(String ver) {
		if (ver == null || ver.isEmpty()) {
			return "";
		}
		String[] vers = ver.split("\\.");
		return (vers.length > 0) ? vers[0] : "0";
	}

	public static String getVerMinor(String ver) {
		if (ver == null || ver.isEmpty()) {
			return "";
		}
		String[] vers = ver.split("\\.");
		return (vers.length > 1) ? vers[1] : "0";
	}

	public static String getVerPatch(String ver) {
		if (ver == null || ver.isEmpty()) {
			return "";
		}
		String[] vers = ver.split("\\.");
		return (vers.length > 2) ? vers[2] : "0";
	}
	
	public static String convFormatted(String source, int len) {
		int clen = source.length();
		StringBuilder builder = new StringBuilder();
		builder.append(source);
		for(int index=0;index<(len-clen);index++) {
			builder.append(" ");
		}
		return builder.toString();
	}
	
	public boolean checkNotWidget(RtcParam param) {
		for(ConfigSetParam target : param.getConfigParams()) {
			if( target.getWidget()!=null && 0<target.getWidget().length() ) {
				return false;
			}
		}
		return true;
	}
	
	public boolean checkNotConstraint(RtcParam param) {
		for(ConfigSetParam target : param.getConfigParams()) {
			if( target.getConstraint()!=null && 0<target.getConstraint().length() ) {
				return false;
			}
		}
		return true;
	}
	
	public boolean checkDetailContent(int index, RtcParam param) {
		if(param.getDetailContent(index)==null || param.getDetailContent(index).length()==0)
			return false;
		return true;
	}
	
	public boolean checkContents(String target) {
		if( target==null || target.length()==0 )
			return false;
		return true;
	}
	
	public String getHistory(StateParam param) {
		if(param.getHistory()==2) {
			return "    DEEPHISTORY()";
		} else if(param.getHistory()==1) {
			return "    HISTORY()";
		}
		return "  ";
	}
	
	public boolean checkFSM(RtcParam param) {
		PropertyParam fsm = param.getProperty(IRtcBuilderConstants.PROP_TYPE_FSM);
		if(fsm==null) return false;
		
		if(Boolean.valueOf(fsm.getValue())) {
			PropertyParam fsmType = param.getProperty(IRtcBuilderConstants.PROP_TYPE_FSMTYTPE);
			if(fsmType==null) return false;
			String strType = fsmType.getValue();
			if(strType.equals(IRtcBuilderConstants.FSMTYTPE_STATIC)) return true;
		}
		return false;
	}
	
	public String getTopFSMName(RtcParam param) {
		StateParam state = param.getFsmParam();
		if(state==null) return "";
		return state.getName();
	}
	
	public String getConnectorString(RtcParam param) {
		StringBuilder builder = new StringBuilder();
		
		for(DataPortParam port : param.getInports() ) {
			if(port.getType().length()==0) continue;
			if(0<builder.length()) builder.append(",");
            builder.append("${PROJECT_NAME}0.").append(port.getName());
            builder.append("?port=${PROJECT_NAME}Test0.").append(port.getName());
		}
		for(DataPortParam port : param.getOutports() ) {
			if(port.getType().length()==0) continue;
			if(0<builder.length()) builder.append(",");
            builder.append("${PROJECT_NAME}0.").append(port.getName());
            builder.append("?port=${PROJECT_NAME}Test0.").append(port.getName());
		}
		//
		for(ServicePortParam port : param.getServicePorts() ) {
			if(0<builder.length()) builder.append(",");
            builder.append("${PROJECT_NAME}0.").append(port.getName());
            builder.append("?port=${PROJECT_NAME}Test0.").append(port.getName());
		}
		
		EventPortParam evPort = param.getEventport();
		if(evPort!=null) {
			for(EventParam event : evPort.getEvents() ) {
				if(0<builder.length()) builder.append(",");
				
				builder.append("${PROJECT_NAME}0.event");
				builder.append("?port=${PROJECT_NAME}Test0.").append(event.getName());
				builder.append("&fsm_event_name=").append(event.getName());
			}
		}
		
		return builder.toString();
	}
}
