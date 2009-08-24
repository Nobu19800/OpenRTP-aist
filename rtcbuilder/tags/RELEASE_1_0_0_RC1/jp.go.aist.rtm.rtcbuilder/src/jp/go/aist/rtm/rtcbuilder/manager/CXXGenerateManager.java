package jp.go.aist.rtm.rtcbuilder.manager;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.IdlFileParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceClassParam;
import jp.go.aist.rtm.rtcbuilder.template.TemplateHelper;
import jp.go.aist.rtm.rtcbuilder.template.TemplateUtil;
import jp.go.aist.rtm.rtcbuilder.template.cpp.CXXConverter;

/**
 * CXX�t�@�C���̏o�͂𐧌䂷��}�l�[�W��
 */
public class CXXGenerateManager extends GenerateManager {

	@Override
	public String getManagerKey() {
		return IRtcBuilderConstants.LANG_CPP;
	}
	@Override
	public String getLangArgList() {
		return IRtcBuilderConstants.LANG_CPP_ARG;
	}

	/**
	 * �t�@�C�����o�͂���
	 * 
	 * @param generatorParam	�����p�p�����[�^
	 * @return �o�͌��ʂ̃��X�g
	 */
	public List<GeneratedResult> generateTemplateCode(RtcParam rtcParam) {

		InputStream ins = null;
		List<GeneratedResult> result = new ArrayList<GeneratedResult>();
		if( !rtcParam.isLanguageExist(IRtcBuilderConstants.LANG_CPP) ) return result;
		if( rtcParam.getName() == null) return result;

		Map<String, Object> contextMap = new HashMap<String, Object>();
		contextMap.put("rtcParam", rtcParam);
		if( rtcParam.getRtmVersion().equals(IRtcBuilderConstants.DEFAULT_RTM_VERSION) ) {
			contextMap.put("cXXConv", new CXXConverter());
		} else {
			contextMap.put("cXXConv", new jp.go.aist.rtm.rtcbuilder.template._042.cpp.CXXConverter());
		}
		contextMap.put("tmpltHelper", new TemplateHelper());

		result = generateCompSource(contextMap, result);

		result = generateMakeFile(contextMap, result);
		result = generateRTCHeader(contextMap, result);
		result = generateRTCSource(contextMap, result);
		result = generateRTCExtend(contextMap, result);

		//IDL�t�@�C�����ɋL�q����Ă���ServiceClassParam��ݒ肷��
		for( IdlFileParam idlFileParam : rtcParam.getProviderIdlPathes() ) {
			for (ServiceClassParam serviceClassParam : rtcParam.getServiceClassParams()) {
				if( idlFileParam.getIdlPath().equals(serviceClassParam.getIdlPath()) )
					idlFileParam.addServiceClassParams(serviceClassParam);
			}
		}

		//Provider�ɎQ�Ƃ���Ă���ServiceClassParam���쐬����
		Set<ServiceClassParam> providerRefenencedServiceClassParam = new HashSet<ServiceClassParam>();
		for( ServicePortParam servicePort : rtcParam.getServicePorts() ) {
			for( ServicePortInterfaceParam serviceInterface : servicePort.getServicePortInterfaces() ) {
				if( serviceInterface.getDirection().equals(ServicePortInterfaceParam.INTERFACE_DIRECTION_PROVIDED) ) {
					ServiceClassParam find = null;
					for (ServiceClassParam serviceClassParam : rtcParam	.getServiceClassParams()) {
						if (serviceInterface.getInterfaceType().equals(
								serviceClassParam.getName())) {
							find = serviceClassParam;
							break;
						}
					}
					if (find != null) {
						providerRefenencedServiceClassParam.add(find);
					}
				}
			}
		}
	
		for (IdlFileParam idlFileParm : rtcParam.getProviderIdlPathes()) {
			contextMap = new HashMap<String, Object>();
			contextMap.put("rtcParam", rtcParam);
			contextMap.put("idlFileParam", idlFileParm);
			contextMap.put("cXXConv", new CXXConverter());
			contextMap.put("tmpltHelper", new TemplateHelper());
			
			result = generateSVCHeader(contextMap, result);
			result = generateSVCSource(contextMap, result);
			result = generateSVCExtend(contextMap, result);
		}
	
		try {
			if( ins != null) ins.close();
		} catch (Exception e) {
			throw new RuntimeException(e); // system error
		}

		return result;
	}
	
	/**
	 * Standalone component�𐶐�����
	 * 
	 * @param rtcParam	�����p�p�����[�^
	 * @param result	�������ʊi�[��
	 * @return �o�͌��ʂ̃��X�g
	 */
	protected List<GeneratedResult> generateCompSource(Map<String, Object> contextMap, List<GeneratedResult> result) {
		InputStream ins = null;

		if( ((RtcParam)contextMap.get("rtcParam")).getRtmVersion().equals(IRtcBuilderConstants.RTM_VERSION_100) ) {
			ins = CXXGenerateManager.class.getClassLoader()	
				.getResourceAsStream("jp/go/aist/rtm/rtcbuilder/template/_100/cpp/CXX_Comp_src.template");
		} else {
			ins = CXXGenerateManager.class.getClassLoader()	
				.getResourceAsStream("jp/go/aist/rtm/rtcbuilder/template/cpp/CXX_Comp_src.template");
		}
		result.add(TemplateUtil.createGeneratedResult(ins, contextMap, 
				((RtcParam)contextMap.get("rtcParam")).getName() + "Comp.cpp"));

		try {
			if( ins != null) ins.close();
		} catch (Exception e) {
			throw new RuntimeException(e); // system error
		}

		return result;
	}
	
	/**
	 * Makefile�𐶐�����
	 * 
	 * @param contextMap	�����p�p�����[�^
	 * @param result	�������ʊi�[��
	 * @return �o�͌��ʂ̃��X�g
	 */
	protected List<GeneratedResult> generateMakeFile(Map<String, Object> contextMap, List<GeneratedResult> result) {
		InputStream ins = null;
		String tmpltPath = null;

		if( ((RtcParam)contextMap.get("rtcParam")).getRtmVersion().equals(IRtcBuilderConstants.DEFAULT_RTM_VERSION) ) {
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/cpp/CXX_Makefile_src.template";
		} else {
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/_042/cpp/CXX_Makefile_src.template";
		}
		ins = CXXGenerateManager.class.getClassLoader().getResourceAsStream(tmpltPath);
		result.add(TemplateUtil.createGeneratedResult(ins, contextMap, "Makefile." + 
				((RtcParam)contextMap.get("rtcParam")).getName()));

		try {
			if( ins != null) ins.close();
		} catch (Exception e) {
			throw new RuntimeException(e); // system error
		}

		return result;
	}
	
	/**
	 * RTC�̃w�b�_�E�t�@�C���𐶐�����
	 * 
	 * @param contextMap	�����p�p�����[�^
	 * @param result	�������ʊi�[��
	 * @return �o�͌��ʂ̃��X�g
	 */
	protected List<GeneratedResult> generateRTCHeader(Map<String, Object> contextMap, List<GeneratedResult> result) {
		InputStream ins = null;
		String tmpltPath = null;

		if( ((RtcParam)contextMap.get("rtcParam")).getRtmVersion().equals(IRtcBuilderConstants.RTM_VERSION_100) ) {
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/_100/cpp/CXX_RTC_Header_src.template";
		} else if( ((RtcParam)contextMap.get("rtcParam")).getRtmVersion().equals(IRtcBuilderConstants.RTM_VERSION_042) ) {
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/_042/cpp/CXX_RTC_Header_src.template";
		} else {
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/cpp/CXX_RTC_Header_src.template";
		}
		ins = CXXGenerateManager.class.getClassLoader().getResourceAsStream(tmpltPath);
		result.add(TemplateUtil.createGeneratedResult(ins, contextMap, 
				((RtcParam)contextMap.get("rtcParam")).getName() + ".h"));

		try {
			if( ins != null) ins.close();
		} catch (Exception e) {
			throw new RuntimeException(e); // system error
		}

		return result;
	}
	
	/**
	 * RTC�̃\�[�X�E�t�@�C���𐶐�����
	 * 
	 * @param rtcParam	�����p�p�����[�^
	 * @param result	�������ʊi�[��
	 * @return �o�͌��ʂ̃��X�g
	 */
	protected List<GeneratedResult> generateRTCSource(Map<String, Object> contextMap, List<GeneratedResult> result) {
		InputStream ins = null;
		String tmpltPath = null;

		if( ((RtcParam)contextMap.get("rtcParam")).getRtmVersion().equals(IRtcBuilderConstants.RTM_VERSION_100) ) {
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/_100/cpp/CXX_RTC_Source_src.template";
		} else if( ((RtcParam)contextMap.get("rtcParam")).getRtmVersion().equals(IRtcBuilderConstants.RTM_VERSION_042) ) {
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/_042/cpp/CXX_RTC_Source_src.template";
		} else {
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/cpp/CXX_RTC_Source_src.template";
		}
		ins = CXXGenerateManager.class.getClassLoader().getResourceAsStream(tmpltPath);
		result.add(TemplateUtil.createGeneratedResult(ins, contextMap, 
				((RtcParam)contextMap.get("rtcParam")).getName() + ".cpp"));

		try {
			if( ins != null) ins.close();
		} catch (Exception e) {
			throw new RuntimeException(e); // system error
		}

		return result;
	}
	
	protected List<GeneratedResult> generateRTCExtend(Map<String, Object> contextMap, List<GeneratedResult> result) {
		InputStream ins = null;

		if( !((RtcParam)contextMap.get("rtcParam")).getRtmVersion().equals(IRtcBuilderConstants.DEFAULT_RTM_VERSION) ) {
			String tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/_042/cpp/CXX_Sln_vc8.template";
			ins = CXXGenerateManager.class.getClassLoader().getResourceAsStream(tmpltPath);
			result.add(TemplateUtil.createGeneratedResult(ins, contextMap, ((RtcParam)contextMap.get("rtcParam")).getName() + "_vc8.sln"));
			//
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/_042/cpp/CXX_VcProj_vc8.template";
			ins = CXXGenerateManager.class.getClassLoader().getResourceAsStream(tmpltPath);
			result.add(TemplateUtil.createGeneratedResult(ins, contextMap, ((RtcParam)contextMap.get("rtcParam")).getName() + "_vc8.vcproj"));
			//
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/_042/cpp/CXX_Comp_VcProj_vc8.template";
			ins = CXXGenerateManager.class.getClassLoader().getResourceAsStream(tmpltPath);
			result.add(TemplateUtil.createGeneratedResult(ins, contextMap, ((RtcParam)contextMap.get("rtcParam")).getName() + "Comp_vc8.vcproj"));
			//
			//
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/_042/cpp/CXX_Sln_vc9.template";
			ins = CXXGenerateManager.class.getClassLoader().getResourceAsStream(tmpltPath);
			result.add(TemplateUtil.createGeneratedResult(ins, contextMap, ((RtcParam)contextMap.get("rtcParam")).getName() + "_vc9.sln"));
			//
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/_042/cpp/CXX_VcProj_vc9.template";
			ins = CXXGenerateManager.class.getClassLoader().getResourceAsStream(tmpltPath);
			result.add(TemplateUtil.createGeneratedResult(ins, contextMap, ((RtcParam)contextMap.get("rtcParam")).getName() + "_vc9.vcproj"));
			//
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/_042/cpp/CXX_Comp_VcProj_vc9.template";
			ins = CXXGenerateManager.class.getClassLoader().getResourceAsStream(tmpltPath);
			result.add(TemplateUtil.createGeneratedResult(ins, contextMap, ((RtcParam)contextMap.get("rtcParam")).getName() + "Comp_vc9.vcproj"));
			//
			//
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/_042/cpp/CXX_Copyprops_Bat.template";
			ins = CXXGenerateManager.class.getClassLoader().getResourceAsStream(tmpltPath);
			result.add(TemplateUtil.createGeneratedResult(ins, contextMap, "copyprops.bat"));
			//
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/_042/cpp/CXX_User_Config_Vsprops.template";
			ins = CXXGenerateManager.class.getClassLoader().getResourceAsStream(tmpltPath);
			result.add(TemplateUtil.createGeneratedResult(ins, contextMap, "user_config.vsprops"));

			try {
				if( ins != null) ins.close();
			} catch (Exception e) {
				throw new RuntimeException(e); // system error
			}
		}
		return result;		
	}
	/**
	 * Service implementation header�𐶐�����
	 * 
	 * @param contextMap	�����p�p�����[�^
	 * @param result	�������ʊi�[��
	 * @return �o�͌��ʂ̃��X�g
	 */
	protected List<GeneratedResult> generateSVCHeader(Map<String, Object> contextMap, List<GeneratedResult> result) {
		InputStream ins = null;

		ins = CXXGenerateManager.class.getClassLoader()	
			.getResourceAsStream("jp/go/aist/rtm/rtcbuilder/template/cpp/CXX_SVC_Header_src.template");
		result.add(TemplateUtil.createGeneratedResult(ins, contextMap, 
								TemplateHelper.getBasename(
										((IdlFileParam)contextMap.get("idlFileParam")).getIdlFileNoExt())
									+ TemplateHelper.getServiceImplSuffix() + ".h"));

		try {
			if( ins != null) ins.close();
		} catch (Exception e) {
			throw new RuntimeException(e); // system error
		}

		return result;
	}

	/**
	 * Service implementation code�𐶐�����
	 * 
	 * @param contextMap	�����p�p�����[�^
	 * @param result	�������ʊi�[��
	 * @return �o�͌��ʂ̃��X�g
	 */
	protected List<GeneratedResult> generateSVCSource(Map<String, Object> contextMap, List<GeneratedResult> result) {
		InputStream ins = null;
		String tmpltPath = null;

		if( ((RtcParam)contextMap.get("rtcParam")).getRtmVersion().equals(IRtcBuilderConstants.DEFAULT_RTM_VERSION) ) {
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/cpp/CXX_SVC_Source_src.template";
		} else {
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/_042/cpp/CXX_SVC_Source_src.template";
		}
		ins = CXXGenerateManager.class.getClassLoader().getResourceAsStream(tmpltPath);
		result.add(TemplateUtil.createGeneratedResult(ins, contextMap, 
								TemplateHelper.getBasename(
										((IdlFileParam)contextMap.get("idlFileParam")).getIdlFileNoExt())
									+ TemplateHelper.getServiceImplSuffix() + ".cpp"));

		try {
			if( ins != null) ins.close();
		} catch (Exception e) {
			throw new RuntimeException(e); // system error
		}

		return result;
	}
	protected List<GeneratedResult> generateSVCExtend(Map<String, Object> contextMap, List<GeneratedResult> result) {
		return result;		
	}
}
