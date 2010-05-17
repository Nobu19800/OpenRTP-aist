package jp.go.aist.rtm.rtcbuilder.manager;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.template.TemplateHelper;
import jp.go.aist.rtm.rtcbuilder.template.TemplateUtil;

/**
 * ��ʃt�@�C���̏o�͂𐧌䂷��}�l�[�W��
 */
public class CommonGenerateManager extends GenerateManager {

	@Override
	public String getManagerKey() {
		return "Common";
	}
	@Override
	public String getLangArgList() {
		return null;
	}

	/**
	 * �t�@�C�����o�͂���
	 * 
	 * @param generatorParam
	 * @return �o�͌��ʂ̃��X�g
	 */
	public List<GeneratedResult> generateTemplateCode(RtcParam rtcParam) {
		List<GeneratedResult> result = new ArrayList<GeneratedResult>();

		result = generateReadMe(rtcParam, result);
		if(rtcParam.getExecutionRate() > 0.0 || rtcParam.checkConstraint() || rtcParam.getConfigParameterParams().size()>0 ) {
			result = generateRTCConf(rtcParam, result);				
		}
		result = generateCommonExtend(rtcParam, result);

		return result;
	}
	
	/**
	 * ReadMe�𐶐�����
	 * 
	 * @param rtcParam	�����p�p�����[�^
	 * @param contextMap	���������
	 * @param result	�������ʊi�[��
	 * @return �o�͌��ʂ̃��X�g
	 */
	protected List<GeneratedResult> generateReadMe(RtcParam rtcParam, List<GeneratedResult> result) {
		InputStream ins = null;
		String tmpltPath = null;

		Map<String, Object> contextMap = new HashMap<String, Object>();
		contextMap.put("rtcParam", rtcParam);
		contextMap.put("tmpltHelper", new TemplateHelper());

		if( rtcParam.getRtmVersion().equals(IRtcBuilderConstants.RTM_VERSION_100) ) {
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/_100/common/README_src.template";
		} else if( rtcParam.getRtmVersion().equals(IRtcBuilderConstants.RTM_VERSION_042) ) {
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/_042/common/README_src.template";
		} else {
			tmpltPath = "jp/go/aist/rtm/rtcbuilder/template/common/README_src.template";
		}
		ins = CommonGenerateManager.class.getClassLoader().getResourceAsStream(tmpltPath);
		result.add(TemplateUtil.createGeneratedResult(ins, contextMap, "README." + rtcParam.getName()));

		try {
			if( ins != null) ins.close();
		} catch (Exception e) {
			throw new RuntimeException(e); // system error
		}

		return result;
	}
	
	protected List<GeneratedResult> generateRTCConf(RtcParam rtcParam, List<GeneratedResult> result) {
		InputStream ins = null;

		ins = CommonGenerateManager.class.getClassLoader()	
			.getResourceAsStream("jp/go/aist/rtm/rtcbuilder/template/common/rtc_conf.template");
		result.add(TemplateUtil.createGeneratedResult(ins, "rtcParam", rtcParam, "rtc.conf"));

		try {
			if( ins != null) ins.close();
		} catch (Exception e) {
			throw new RuntimeException(e); // system error
		}

		return result;
	}

	protected List<GeneratedResult> generateCommonExtend(RtcParam rtcParam, List<GeneratedResult> result) {
		InputStream ins = null;

		if( rtcParam.getRtmVersion().equals(IRtcBuilderConstants.RTM_VERSION_100) ) {
			ins = CommonGenerateManager.class.getClassLoader()	
					.getResourceAsStream("jp/go/aist/rtm/rtcbuilder/template/_100/common/Component_conf.template");
			result.add(TemplateUtil.createGeneratedResult(ins, "rtcParam", rtcParam, rtcParam.getName() + ".conf"));
		}
		try {
			if( ins != null) ins.close();
		} catch (Exception e) {
			throw new RuntimeException(e); // system error
		}

		return result;		
	}
}
