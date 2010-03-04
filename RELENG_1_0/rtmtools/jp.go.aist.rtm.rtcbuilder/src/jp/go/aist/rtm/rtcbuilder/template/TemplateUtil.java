package jp.go.aist.rtm.rtcbuilder.template;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 * �e���v���[�g���o�͂���ۂɎg�p����郆�[�e�B���e�B
 */
public class TemplateUtil {

	/**
	 * �N���X�p�X���\�[�X������e����ɓ����
	 * 
	 * @param path
	 *            �p�X
	 * @return ���e
	 */
	public static String getResourceContents(String path) {
		InputStream input = TemplateUtil.class.getClassLoader()
				.getResourceAsStream(path);

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		byte[] buff = new byte[1024];

		int count;
		try {
			while ((count = input.read(buff)) != -1) {
				out.write(buff, 0, count);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return out.toString();
	}

	/**
	 * �e���v���[�g����GeneratedResult���쐬����
	 * 
	 * @param templatePath�@�e���v���[�g�̃p�X
	 * @param contextRootName �R���e�N�X�g�̃��[�g�ƂȂ閼�O
	 * @param contextRoot �R���e�N�X�g�̃��[�g
	 * @param fileName �o�̓t�@�C����
	 * @return
	 */
	public static GeneratedResult createGeneratedResult(InputStream in,
			String contextRootName, Object contextRoot, String fileName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(contextRootName, contextRoot);

		return createGeneratedResult(in, map, fileName);
	}

	/**
	 * �e���v���[�g����GeneratedResult���쐬����
	 * 
	 * @param templatePath�@�e���v���[�g�̃p�X
	 * @param contextMap �R���e�N�X�g�̃}�b�v
	 * @param fileName �o�̓t�@�C����
	 * 
	 * @return GeneratedResult
	 */
	@SuppressWarnings("unchecked")
	public static GeneratedResult createGeneratedResult(InputStream in,
			Map contextMap, String fileName) {
		return new GeneratedResult(fileName, generate(in, contextMap));
	}

	/**
	 * �ݒ�ς݂�VelocityEngine���擾����
	 * 
	 * @return
	 */
	public static VelocityEngine getEngine() {
		VelocityEngine result = new VelocityEngine();
		result.setProperty(VelocityEngine.RESOURCE_LOADER, "class");
		result.setProperty(VelocityEngine.VM_LIBRARY, "");
		result.setProperty("class.resource.loader.description",
				"Velocity Classpath Resource Loader");
		result
				.setProperty("class.resource.loader.class",
						"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		try {
			result.init();
		} catch (Exception e) {
			throw new RuntimeException(e); // system error
		}

		return result;
	}

	public static VelocityContext getDefaultVelocityContext() {
		VelocityContext result = new VelocityContext();
		result.put("sharp", "#");
		result.put("dol", "$");
		result.put("def", "def");
		result.put("yen", "\\");

		return result;
	}

	/**
	 * �}�[�W���s�����ʂ�Ԃ�
	 * 
	 * @param template
	 * @param vc
	 * @return
	 */
	public static String merge(Template template, VelocityContext vc) {
		StringWriter result = new StringWriter();
		try {
			template.merge(vc, result);
		} catch (Exception e) {
			throw new RuntimeException(e); // system error
		}

		return result.toString();
	}

	/**
	 * �������s��
	 * 
	 * @param contextRoot
	 * @param templatePath
	 * @param contextRootName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String generate(InputStream in, Map contextMap) {
		VelocityEngine ve = TemplateUtil.getEngine();
		ve.setProperty(VelocityEngine.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.NullLogSystem");
		VelocityContext vc = TemplateUtil.getDefaultVelocityContext();
		for (Iterator iter = contextMap.entrySet().iterator(); iter.hasNext();) {
			Map.Entry element = (Map.Entry) iter.next();
			vc.put((String) element.getKey(), element.getValue());
		}

		StringWriter result = new StringWriter();
		try {
			ve.evaluate(vc, result, "", new InputStreamReader(in));
			result.close();
		} catch (Exception e) {
			throw new RuntimeException(e); // system error
		}

		return result.toString().replace("\r\n", System.getProperty( "line.separator" ));
	}

}
