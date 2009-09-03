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
import jp.go.aist.rtm.rtcbuilder.template.cppwin.CXXWinConverter;

/**
 * CXX�t�@�C���̏o�͂𐧌䂷��}�l�[�W��
 */
public class CXXWinGenerateManager extends CXXGenerateManager {

	@Override
	public String getManagerKey() {
		return IRtcBuilderConstants.LANG_CPPWIN;
	}
	@Override
	public String getLangArgList() {
		return IRtcBuilderConstants.LANG_CPPWIN_ARG;
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

		if( rtcParam.isLanguageExist(IRtcBuilderConstants.LANG_CPPWIN) && rtcParam.getName() != null ) {
			Map<String, Object> contextMap = new HashMap<String, Object>();
			contextMap.put("rtcParam", rtcParam);
			contextMap.put("cXXConv", new CXXWinConverter());
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
						for (ServiceClassParam serviceClassParam : rtcParam.getServiceClassParams()) {
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
				contextMap.put("cXXConv", new CXXWinConverter());
				contextMap.put("tmpltHelper", new TemplateHelper());
				
				result = generateSVCHeader(contextMap, result);
				result = generateSVCSource(contextMap, result);
			}
		
			try {
				if( ins != null) ins.close();
			} catch (Exception e) {
				throw new RuntimeException(e); // system error
			}
		}

		return result;
	}
	
	/**
	 * �v���W�F�N�g�E�t�@�C���𐶐�����
	 * 
	 * @param rtcParam	�����p�p�����[�^
	 * @param result	�������ʊi�[��
	 * @return �o�͌��ʂ̃��X�g
	 */
	protected List<GeneratedResult> generateRTCExtend(Map<String, Object> contextMap, List<GeneratedResult> result) {
		InputStream ins = null;

		ins = CXXWinGenerateManager.class.getClassLoader()	
			.getResourceAsStream("jp/go/aist/rtm/rtcbuilder/template/cppwin/CXX_RTM_Project_src.template");
		result.add(TemplateUtil.createGeneratedResult(ins, contextMap, "OpenRTM-aist.vsprops"));

		ins = CXXWinGenerateManager.class.getClassLoader()	
			.getResourceAsStream("jp/go/aist/rtm/rtcbuilder/template/cppwin/CXX_Project_src.template");
		result.add(TemplateUtil.createGeneratedResult(ins, contextMap, 
				((RtcParam)contextMap.get("rtcParam")).getName() + ".vcproj"));
		
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

		ins = CXXWinGenerateManager.class.getClassLoader()	
			.getResourceAsStream("jp/go/aist/rtm/rtcbuilder/template/cppwin/CXX_SVC_Source_src.template");
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
}
