package jp.go.aist.rtm.rtcbuilder.generator;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.go.aist.rtm.rtcbuilder.util.FileUtil;

/**
 * �v���v���Z�b�T
 * <p>
 * �p�[�X�̑O�ɁA�Ώە�����ɑ΂��Ď��s����B <br>
 * �u#include<hoge>("")�v�̂ݑΉ��B���̑��͋󕶎��ɕϊ�����
 */
public class PreProcessor {
	private static final Pattern PREPROSESSOR_PATTERN = Pattern.compile(
			"^#.*$", Pattern.MULTILINE);

	private static final Pattern INCLUDE_PATTERN = Pattern
			.compile("^#include\\s*(<|\")(.*)(>|\").*$");

	private static final int INCLUDE_FILE_INDEX = 2;

	/**
	 * �Ώە�����ɑ΂��ăv���v���Z�b�T�����s����B
	 * 
	 * @param target
	 *            �Ώە�����
	 * @return ���s�㕶����
	 */
	public static String parse(String target, File includeBaseDir) {
		StringBuffer result = new StringBuffer();
		Matcher matcher = PREPROSESSOR_PATTERN.matcher(target);
		while (matcher.find()) {
			String replateString = "";
			String includeFileContent = getIncludeFileContentThoroughgoing(
					matcher.group(), includeBaseDir);
			if (includeFileContent != null) {
				replateString = includeFileContent;
			}

			matcher.appendReplacement(result, Matcher
					.quoteReplacement(replateString));
		}
		matcher.appendTail(result);

		return result.toString();
	}

	public static String getIncludeFileContentThoroughgoing(String directive,
			File includeBaseDir) {
		String result = getIncludeFileContent(directive, includeBaseDir);
		if (result != null) {
			result = parse(result, includeBaseDir);
		}

		return result;
	}

	/**
	 * �C���N���[�h�ł������ꍇ�ɁA�t�@�C���̃R���e���c���擾����
	 * 
	 * @param directive
	 * @param includeBaseDir
	 * @return
	 */
	public static String getIncludeFileContent(String directive,
			File includeBaseDir) {
		String result = null;
		
		Matcher matcher = INCLUDE_PATTERN.matcher(directive);
		if (matcher.find()) {
			String filePath = matcher.group(INCLUDE_FILE_INDEX);
			if (includeBaseDir == null) {
				throw new RuntimeException("#include����IDL�̃f�B���N�g�����w�肵�Ă��������B\r\npath�������ł��܂���@:" + filePath);
			}
			result = FileUtil.readFile(new File(includeBaseDir, filePath)
					.getAbsolutePath());
		}

		return result;
	}
}
