package jp.go.aist.rtm.rtcbuilder.generator.parser;

import java.util.Map;

/**
 * �}�[�W�ꏊ���p�[�X����N���X �^�O���́urtc-template�v�A�������́ublock�v���g�p����
 */
public class MergeBlockParser {

	public static final BlockParser parser = new BlockParser("rtc-template", "block");

	/**
	 * ����������BlockParser���g�p����
	 * 
	 * @param target
	 * @return
	 */
	public static Map<String, String> parse(String target) {
		return parser.parse(target);
	}

	/**
	 * ����������BlockParser���g�p����
	 * 
	 * @param target
	 * @return
	 */
	public static String merge(String target, Map<String, String> mergeStringMap) {
		return parser.merge(target, mergeStringMap, true);
	}
}
