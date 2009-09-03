package jp.go.aist.rtm.toolscommon.factory;

import jp.go.aist.rtm.toolscommon.model.core.WrapperObject;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationManager;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.MappingRule;

/**
 * RtcLink�̓����Ŏg�p�����h���C���I�u�W�F�N�g���쐬����t�@�N�g��
 * <p>
 * �����ł́A�����t���[�����[�N���g�p���Ă���B
 */
public class CorbaWrapperFactory {

    private static CorbaWrapperFactory __instance = null;

    private SynchronizationManager synchronizationManager;

    /**
     * �R���X�g���N�^
     * <p>
     * ���̃}�b�s���O���[�����g�p�����t�@�N�g�����쐬���邱�Ƃ��ł���悤�ɃR���X�g���N�^�����J���邪�A��{�I�ɂ�getInstance()�𗘗p���ăV���O���g�����쐬���邱��
     * 
     * @param mappingRules
     */
    public CorbaWrapperFactory(MappingRule[] mappingRules) {
        synchronizationManager = new SynchronizationManager(mappingRules);
        
    }
    
	/**
	 * �V���O���g�����Z�b�g����B�i��{�I�Ɏg�p���Ă͂Ȃ�Ȃ��B�R�}���h���C������̎��s�̂��߂ɒǉ��j
	 * 
	 */
	public static void setInstance(CorbaWrapperFactory instance) {
		__instance = instance;
	}

    /**
     * �t�@�N�g���̃V���O���g�����擾����
     * 
     * @return �t�@�N�g���̃V���O���g��
     */
    public static CorbaWrapperFactory getInstance() {
        if (__instance == null) {
            __instance = new CorbaWrapperFactory(MappingRuleFactory
                    .getMappingRule());
        }

        return __instance;
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
