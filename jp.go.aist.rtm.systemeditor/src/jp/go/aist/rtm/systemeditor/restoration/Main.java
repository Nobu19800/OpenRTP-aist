package jp.go.aist.rtm.systemeditor.restoration;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jp.go.aist.rtm.nameserviceview.model.nameservice.NameservicePackage;
import jp.go.aist.rtm.systemeditor.factory.SystemEditorWrapperFactory;
import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.systemeditor.ui.editor.action.RestoreOption;
import jp.go.aist.rtm.toolscommon.factory.CorbaWrapperFactory;
import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.core.CorePackage;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.MappingRule;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;

public class Main {

	/**
	 * �w�肳�ꂽXML�t�@�C����ǂݍ��݁AXML�t�@�C���̓��e���V�X�e���ɔ��f����B
	 * XML�t�@�C���̏ꏊ�́A�t�@�C���p�X��������URI(Web�T�[�o���\)�Ŏw�肷��B
	 * <p>
	 * [�d�v]�}�b�s���O���[���́A���s�t�@�C�������ɁA�ʓr�쐬�����t�@�C��(MAPPING_RULES)�Ɏw�肷��
	 * 
	 * �R���\�[������RtcLink��XML�t�@�C����ǂݍ��݁AXML�t�@�C���̓��e�ɉ����Ĉȉ����s���B
	 * <LI>�P�DRtcLink��XML�Ɋ܂܂�邷�ׂĂ�RTC�ɃA�N�Z�X�\�ł��邩�m�F����B</LI>
	 * <LI>�Q�DRtcLink��XML�Ɋ܂܂�邷�ׂẴR���t�B�O���[�V�������𕜌�����</LI>
	 * <LI>�R�DRtcLink��XML�Ɋ܂܂�邷�ׂẴR�l�N�V������ڑ�����</LI>
	 * <LI>�S�ERtcLink��XML�Ɋ܂܂�邷�ׂĂ�RTC�ɑ΂��āAStart�v���𑗐M����B</LI>
	 * 
	 * �R�D�ł́A���ɓ����R�l�N�V����ID�����݂���΁A�ڑ��͍s���Ȃ� <br>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Result result = new Result() {
			private boolean success = true;

			public void putResult(String resultPart) {
				System.out.println(resultPart);
			}

			public boolean isSuccess() {
				return success;
			}

			public void setSuccess(boolean success) {
				this.success = success;
			}
		};

		if (args.length < 1) {
			System.out.println(Messages.getString("Main.0")); //$NON-NLS-1$
			result.setSuccess(false);
		}

		URI xmlUri = null;
		if (result.isSuccess()) {
			try {
				xmlUri = URI.createFileURI(args[0]);
			} catch (RuntimeException e) {
				// void
			}
			if (xmlUri == null) {
				xmlUri = URI.createURI(args[0]);
			}

			if (xmlUri == null) {
				result.putResult(Messages.getString("Main.1")); //$NON-NLS-1$
				result.setSuccess(false);
				return;
			}
		}

		if (result.isSuccess()) {
			execute(xmlUri, result);
		}

		if (result.isSuccess()) {
			result.putResult(Messages.getString("Main.2")); //$NON-NLS-1$
		} else {
			result.putResult(Messages.getString("Main.3")); //$NON-NLS-1$
		}

	}

	/**
	 * ���s���C��
	 * 
	 * @param xmlUri
	 * @param result
	 */
	public static void execute(URI xmlUri, Result result) {
		SystemDiagram systemDiagram = loadFile(xmlUri, result);
		if (result.isSuccess()) {
			Restoration.execute(systemDiagram, result);
		}
	}

	public static SystemDiagram loadFile(URI xmlUri, Result result) {
		CorePackage.eINSTANCE.getEFactoryInstance();
		NameservicePackage.eINSTANCE.getEFactoryInstance();
		ComponentPackage.eINSTANCE.getEFactoryInstance();

//		Resource resource = new XMIResourceImpl(xmlUri);
		Resource resource = new XMLResourceImpl(xmlUri);
		try {
			resource.load(Collections.EMPTY_MAP);
		} catch (MalformedURLException e) {
			result.putResult(Messages.getString("Main.4") + xmlUri.toString() + " ]"); //$NON-NLS-1$ //$NON-NLS-2$
			result.setSuccess(false);
			return null;
		} catch (FileNotFoundException e) {
			result.putResult(Messages.getString("Main.6") + xmlUri.toString() //$NON-NLS-1$
					+ " ]"); //$NON-NLS-1$
			result.setSuccess(false);
			return null;
		} catch (IOException e) {
			result.putResult(Messages.getString("Main.8") //$NON-NLS-1$
					+ Messages.getString("Main.9")); //$NON-NLS-1$
			result.setSuccess(false);
			return null;
		}

		MappingRule[] mappingRule = getMappingRuleFromPropertyFile(result);

		SystemEditorWrapperFactory systemEditorWrapperFactory = new SystemEditorWrapperFactory(
				mappingRule);
		SystemEditorWrapperFactory.setInstance(systemEditorWrapperFactory);
		CorbaWrapperFactory corbaWrapperFactory = new CorbaWrapperFactory(mappingRule);
		CorbaWrapperFactory.setInstance(corbaWrapperFactory);
		SystemDiagram systemDiagram = null;
		try {
			systemDiagram = (SystemDiagram) systemEditorWrapperFactory
					.loadContentFromResource(resource.getURI().devicePath(), RestoreOption.NONE);
		} catch (Exception e) {
			throw new RuntimeException(); // system error
		}

		return systemDiagram;
	}

	/**
	 * �t�@�C������}�b�s���O���[�����쐬����
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	private static MappingRule[] getMappingRuleFromPropertyFile(Result result) {

		List<MappingRule> mappingRule = new ArrayList<MappingRule>();

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream("./MAPPING_RULES"))); //$NON-NLS-1$

			while (reader.ready()) {
				String value = reader.readLine();

				int lastIndexOf = value.lastIndexOf("."); //$NON-NLS-1$

				ClassLoader classLoader = Main.class.getClassLoader();
				if (classLoader == null) {

					classLoader = ClassLoader.getSystemClassLoader();

				}

				Class<?> clazz = classLoader.loadClass(value.substring(0,
						lastIndexOf));
				Field field = clazz.getDeclaredField(value
						.substring(lastIndexOf + ".".length())); //$NON-NLS-1$

				mappingRule.add((MappingRule) field.get(clazz.newInstance()));
			}

			reader.close();

		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}

		return mappingRule.toArray(new MappingRule[mappingRule.size()]);
	}
}
