package jp.go.aist.rtm.rtcbuilder.generator.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * �u���b�N���p�[�X����N���X
 */
public class BlockParser {

	/**
	 * �u���b�N�̐��K�\���i�����s�j
	 */
	private Pattern multiLineBlockPattern;

	/**
	 * �u���b�N�̐��K�\���i�P�s�j
	 */
	private Pattern singleLineBlockPattern;

	/**
	 * �}�[�W�ꏊ �X�^�[�g�^�O�̃O���[�v
	 */
	private static final int START_TAG_GROUP = 1;

	/**
	 * �}�[�W�ꏊ �����̃O���[�v
	 */
	private static final int ATTRIBUTE_GROUP = 2;

	/**
	 * �}�[�W�ꏊ �^�O�̃{�f�B�̃O���[�v
	 */
	private static final int TAG_BODY_GROUP = 4;

	/**
	 * �}�[�W�ꏊ �G���h�^�O�̃O���[�v
	 */
	private static final int END_TAG_GROUP = 5;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param tagName
	 *            �u���b�N�̃^�O��
	 * @param attributeName
	 *            �u���b�N�̑�����
	 */
	public BlockParser(String tagName, String attributeName) {
		this.singleLineBlockPattern = Pattern.compile("(<" + tagName + "\\s+"
				+ attributeName + "\\s*=\\s*\"([^\"]*)\"\\s*>())(.*?)(</\\s*"
				+ tagName + "\\s*>)", Pattern.CASE_INSENSITIVE);

		this.multiLineBlockPattern = Pattern
				.compile(
						"(<"
								+ tagName
								+ "\\s+"
								+ attributeName
								+ "\\s*=\\s*\"([^\"]*)\"\\s*>(\\s*?\\n))(.*?)((^[^\\n]*)?</\\s*"
								+ tagName + "\\s*>)", Pattern.MULTILINE
								| Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	}

	/**
	 * �����̕����񂩂�}�[�W�ꏊ����肵�Ablock���������L�[/�{�f�B�̓��e��l�Ƃ���Map��Ԃ�
	 * 
	 * @param target
	 *            �Ώە�����
	 * @return block���������L�[�E�{�f�B�̓��e��l�Ƃ���Map
	 */
	public Map<String, String> parse(String target) {
		Matcher singleLineMatcher = singleLineBlockPattern.matcher(target);

		Map<String, String> result = new HashMap<String, String>();

		while (singleLineMatcher.find()) {
			String body = singleLineMatcher.group(TAG_BODY_GROUP);
			if (body == null) {
				body = "";
			}

			result.put(singleLineMatcher.group(ATTRIBUTE_GROUP), body);
		}

		Matcher multiLineMatcher = multiLineBlockPattern.matcher(target);

		while (multiLineMatcher.find()) {
			String body = multiLineMatcher.group(TAG_BODY_GROUP);
			if (body == null) {
				body = "";
			}

			result.put(multiLineMatcher.group(ATTRIBUTE_GROUP), body);
		}

		return result;
	}

	/**
	 * �����̕�����ɑ΂��āA�}�[�W���s���B
	 * <p>
	 * �����̕�����ɑ΂��ă}�[�W�ꏊ����肵�A������Map����ɒl�����ւ���B
	 * 
	 * @param target
	 *            �Ώە�����
	 * @param mergeStringMap
	 *            �u��
	 * @param includeTag
	 *            �^�O���܂߂Ēu�����邩�ǂ���
	 * @return
	 */
	public String merge(String target, Map<String, String> mergeStringMap,
			boolean includeTag) {
		StringBuffer multiLineMergeResult = new StringBuffer();
		Matcher multiLineMatcher = multiLineBlockPattern.matcher(target);
		while (multiLineMatcher.find()) {
			String attributeString = mergeStringMap.get(multiLineMatcher
					.group(ATTRIBUTE_GROUP));
			if (attributeString == null) {
				attributeString = "\n\n";
			}

			StringBuffer replaceString = new StringBuffer();
			if (includeTag) {
				replaceString.append(multiLineMatcher.group(START_TAG_GROUP));
			}
			replaceString.append(Matcher.quoteReplacement(attributeString));
			if (includeTag) {
				replaceString.append(multiLineMatcher.group(END_TAG_GROUP));
			}

			multiLineMatcher.appendReplacement(multiLineMergeResult, replaceString
					.toString());
		}
		multiLineMatcher.appendTail(multiLineMergeResult);

		StringBuffer singleLineMergeResult = new StringBuffer();
		Matcher singleLineMatcher = singleLineBlockPattern
				.matcher(multiLineMergeResult.toString());
		while (singleLineMatcher.find()) {
			String attributeString = mergeStringMap.get(singleLineMatcher
					.group(ATTRIBUTE_GROUP));
			if (attributeString == null) {
				attributeString = "";
			}

			StringBuffer replaceString = new StringBuffer();
			if (includeTag) {
				replaceString.append(singleLineMatcher.group(START_TAG_GROUP));
			}
			replaceString.append(Matcher.quoteReplacement(attributeString));
			if (includeTag) {
				replaceString.append(singleLineMatcher.group(END_TAG_GROUP));
			}

			singleLineMatcher.appendReplacement(singleLineMergeResult,
					replaceString.toString());
		}
		singleLineMatcher.appendTail(singleLineMergeResult);

		return singleLineMergeResult.toString();
	}
}
