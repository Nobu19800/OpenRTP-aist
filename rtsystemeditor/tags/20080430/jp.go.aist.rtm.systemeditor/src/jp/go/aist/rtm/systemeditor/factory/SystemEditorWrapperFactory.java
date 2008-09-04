package jp.go.aist.rtm.systemeditor.factory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.systemeditor.ui.util.ProfileHandler;
import jp.go.aist.rtm.toolscommon.factory.MappingRuleFactory;
import jp.go.aist.rtm.toolscommon.model.component.Connector;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagram;
import jp.go.aist.rtm.toolscommon.model.component.SystemDiagramKind;
import jp.go.aist.rtm.toolscommon.model.core.WrapperObject;
import jp.go.aist.rtm.toolscommon.profiles.util.XmlHandler;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationManager;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.MappingRule;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.openrtp.namespaces.rts.RtsProfile;

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
	 * @param resource
	 *            ���\�[�X
	 * @return �h���C���I�u�W�F�N�g���[�g
	 * @throws IOException
	 *             �t�@�C�����ǂݍ��߂Ȃ��ꍇ�Ȃ�
	 */
	public EObject loadContentFromResource(Resource resource)
			throws IOException {
		resource.load(Collections.EMPTY_MAP);
		EObject object = (EObject) resource.getContents().get(0);
		getSynchronizationManager().assignSynchonizationSupport(object);

		Rehabilitation.rehabilitation(object);

		return object;
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
		if( ((SystemDiagram)content).getKind() == SystemDiagramKind.OFFLINE_LITERAL ) {
			ProfileHandler handler = new ProfileHandler();
			RtsProfile profile = handler.convertToProfile(content);
			XmlHandler xmlHandler = new XmlHandler();
			String targetFileName =	resource.getURI().devicePath();
			xmlHandler.saveXmlRts(profile, targetFileName);
		} else {
			resource.getContents().add(content);
			HashMap options = new HashMap();
			// options.put(XMLResource.OPTION_ENCODING, "ISO-8859-1");
			options.put(XMLResource.OPTION_ENCODING, "UTF-8");
			resource.save(options);
		}
	}

	/**
	 * �Ώۂ̃I�u�W�F�N�g���R�s�[���܂�
	 * 
	 * @param component
	 * @return
	 */
	public WrapperObject copy(EObject obj) {
		WrapperObject copy = (WrapperObject) EcoreUtil.copy(obj);
		List<Connector> connectors = new ArrayList<Connector>();
		for (Iterator iter = copy.eAllContents(); iter.hasNext();) {
			Object e = iter.next();
			if (e instanceof Connector) {
				connectors.add((Connector) e);
			}
		}
		for (Connector connector : connectors) {
			EcoreUtil.remove(connector);
		}
		getSynchronizationManager().assignSynchonizationSupport(copy);

		return copy;
	}

	/**
	 * �����[�g�I�u�W�F�N�g��n���A�h���C���I�u�W�F�N�g���쐬����
	 * 
	 * @param remoteObject
	 *            �����[�g�I�u�W�F�N�g
	 * @return �쐬���ꂽ�h���C���I�u�W�F�N�g
	 */
	public WrapperObject createWrapperObject(Object... remoteObjects) {
		return (WrapperObject) synchronizationManager
				.createLocalObject(remoteObjects);
	}

	/**
	 * SynchronizationManager���擾����
	 */
	public SynchronizationManager getSynchronizationManager() {
		return synchronizationManager;
	}
}
