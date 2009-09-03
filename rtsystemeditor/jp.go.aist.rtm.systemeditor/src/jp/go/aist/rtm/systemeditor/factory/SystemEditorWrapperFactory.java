package jp.go.aist.rtm.systemeditor.factory;

import java.io.IOException;
import java.net.URLDecoder;

import jp.go.aist.rtm.systemeditor.ui.editor.action.RestoreOption;
import jp.go.aist.rtm.systemeditor.ui.util.RtsProfileHandler;
import jp.go.aist.rtm.toolscommon.factory.MappingRuleFactory;
import jp.go.aist.rtm.toolscommon.model.component.Component;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind;
import jp.go.aist.rtm.toolscommon.profiles.util.XmlHandler;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationManager;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.MappingRule;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.openrtp.namespaces.rts.version02.RtsProfileExt;

/**
 * �V�X�e���G�f�B�^�ŕK�v�ƂȂ�WrapperObject�̃t�@�N�g��
 * �����CORBA�p�̃}�b�s���O���[���݂̂ɑΉ�
 *
 */
public class SystemEditorWrapperFactory {

	private static SystemEditorWrapperFactory __instance = null;

	private SynchronizationManager synchronizationManager;

	/**
	 * �R���X�g���N�^
	 * <p>
	 * ���̃}�b�s���O���[�����g�p�����t�@�N�g�����쐬���邱�Ƃ��ł���悤�ɃR���X�g���N�^�����J���邪�A��{�I�ɂ�getInstance()�𗘗p���ăV���O���g�����쐬���邱��
	 * 
	 * @param mappingRules
	 */
	public SystemEditorWrapperFactory(MappingRule[] mappingRules) {
		synchronizationManager = new SynchronizationManager(mappingRules);
	}

	/**
	 * �t�@�N�g���̃V���O���g�����擾����
	 * 
	 * @return �t�@�N�g���̃V���O���g��
	 */
	public static SystemEditorWrapperFactory getInstance() {
		if (__instance == null) {
			__instance = new SystemEditorWrapperFactory(MappingRuleFactory
					.getMappingRule());
		}

		return __instance;
	}

	/**
	 * �V���O���g�����Z�b�g����B�i��{�I�Ɏg�p���Ă͂Ȃ�Ȃ��B�R�}���h���C������̎��s�̂��߂ɒǉ��j
	 * 
	 */
	public static void setInstance(SystemEditorWrapperFactory instance) {
		__instance = instance;
	}

	/**
	 * XML����h���C���I�u�W�F�N�g�c���[�𕜌�����
	 * <p>
	 * �����ł́AEMF�̃I�u�W�F�N�g�����[�h���A�����t���[�����[�N�̃I�u�W�F�N�g�̕������s��
	 * 
	 * @param strPath
	 *            �t�@�C���̃p�X
	 * @param restore	IOR����̕������s�����𔻒f����
	 * @return �h���C���I�u�W�F�N�g���[�g
	 * @throws IOException
	 *             �t�@�C�����ǂݍ��߂Ȃ��ꍇ�Ȃ�
	 */
	@SuppressWarnings("unchecked")
	public EObject loadContentFromResource(String strPath, RestoreOption restore)
			throws Exception {
		RtsProfileHandler handler = new RtsProfileHandler();
		SystemDiagram diagram = handler.load(strPath, SystemDiagramKind.ONLINE_LITERAL);
		if (restore.doQuick()) handler.populateCorbaBaseObject(diagram);

		return postLoad(handler, diagram);
	}

	public EObject postLoad(RtsProfileHandler handler, SystemDiagram diagram) {
		getSynchronizationManager().assignSynchonizationSupportToDiagram(diagram);
		Rehabilitation.rehabilitation(diagram);
		
		// �ǂݍ��ݎ��ɖ����I�ɏ�Ԃ̓��������s
		for (Object obj : diagram.getComponents()) {
			((Component)obj).getSynchronizationSupport().synchronizeLocal();
		}

		handler.restoreCompositeComponentPort(diagram);
		return diagram;
	}

	/**
	 * XML�ɃI�u�W�F�N�g�c���[��ۑ�����
	 * <p>
	 * �����ł́AEMF�̃I�u�W�F�N�g���Z�[�u����B �����t���[�����[�N�̃I�u�W�F�N�g�̓Z�[�u����Ȃ��̂ŁA���[�h���ɕ������s���K�v������B
	 * 
	 * @param resource
	 *            ���\�[�X
	 * @param content
	 *            �h���C���I�u�W�F�N�g���[�g
	 * @throws IOException
	 *             �t�@�C���ɕۑ��ł��Ȃ��ꍇ�Ȃ�
	 */
	public void saveContentsToResource(Resource resource, EObject content)
			throws Exception {
		RtsProfileHandler handler = new RtsProfileHandler();
		SystemDiagram diagram = (SystemDiagram) content;
		RtsProfileExt profile = handler.save(diagram);
		diagram.setProfile(profile);
		
		String targetFileName =	resource.getURI().devicePath();
		XmlHandler xmlHandler = new XmlHandler();
		xmlHandler.saveXmlRts(profile, URLDecoder.decode(targetFileName, "UTF-8"));
	}

	/**
	 * SynchronizationManager���擾����
	 */
	public SynchronizationManager getSynchronizationManager() {
		return synchronizationManager;
	}
}
