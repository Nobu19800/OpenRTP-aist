package jp.go.aist.rtm.rtcbuilder.generator;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.go.aist.rtm.rtcbuilder.IRTCBMessageConstants;
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

	private static final Pattern COMMENT_PATTERN = Pattern
			.compile("/\\*(.*?)(\\*/)", Pattern.DOTALL);

	private static final int INCLUDE_FILE_INDEX = 2;

	/**
	 * �Ώە�����ɑ΂��ăv���v���Z�b�T�����s����B
	 * �S�v���v���Z�b�T���폜����
	 * 
	 * @param target
	 *            �Ώە�����
	 * @return ���s�㕶����
	 */
	public static String parseAlltoSpace(String target) {
		String targetNoCmt = eraseComments(target);
		//
		StringBuffer result = new StringBuffer();
		Matcher matcher = PREPROSESSOR_PATTERN.matcher(targetNoCmt);
		while (matcher.find()) {
			matcher.appendReplacement(result, Matcher.quoteReplacement(""));
		}
		matcher.appendTail(result);

		return result.toString();
	}
	
	private static String eraseComments(String target) {
		StringBuffer result = new StringBuffer();
		Matcher matcher = COMMENT_PATTERN.matcher(target);
		while (matcher.find()) {
			matcher.appendReplacement(result, Matcher.quoteReplacement(""));
		}
		matcher.appendTail(result);

		return result.toString();
	}

	/**
	 * �Ώە�����ɑ΂��ăv���v���Z�b�T�����s����B
	 * 
	 * @param target
	 *            �Ώە�����
	 * @return ���s�㕶����
	 */
	public static String parse(String target, File includeBaseDir, List<String> includeFiles) {
		String targetNoCmt = eraseComments(target);
		/////
		StringBuffer result = new StringBuffer();
		Matcher matcher = PREPROSESSOR_PATTERN.matcher(targetNoCmt);
		while (matcher.find()) {
			String replateString = "";
			String includeFileContent = getIncludeFileContentThoroughgoing(
					matcher.group(), includeBaseDir, includeFiles);
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
			File includeBaseDir, List<String> includeFiles) {
		String result = getIncludeFileContent(directive, includeBaseDir, includeFiles);
		if (result != null) {
			result = parse(result, includeBaseDir, includeFiles);
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
			File includeBaseDir, List<String> includeFiles) {
		String result = null;
		
		Matcher matcher = INCLUDE_PATTERN.matcher(directive);
		if (matcher.find()) {
			String filePath = matcher.group(INCLUDE_FILE_INDEX);
			if (includeBaseDir == null) {
				throw new RuntimeException(IRTCBMessageConstants.ERROR_PREPROCESSOR + filePath);
			}
			String includeFilePath = new File(includeBaseDir, filePath).getAbsolutePath();
			result = FileUtil.readFile(includeFilePath);
			if(includeFiles!=null) {
				if( !includeFiles.contains(includeFilePath) ) {
					includeFiles.add(includeFilePath);
				}
			}
		}

		return result;
	}
}
