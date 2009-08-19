package jp.go.aist.rtm.nameserviceview.factory;

import jp.go.aist.rtm.nameserviceview.model.nameservice.NameServiceReference;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NameserviceFactory;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NamingContextNode;
import jp.go.aist.rtm.toolscommon.factory.MappingRuleFactory;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationManager;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.MappingRule;

import org.omg.CosNaming.Binding;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;

/**
 * RtcLink�̓����Ŏg�p�����h���C���I�u�W�F�N�g���쐬����t�@�N�g��
 * <p>
 * �����ł́A�����t���[�����[�N���g�p���Ă���B
 * NameServerNamingContext(CORBA��p�j���쐬����p�r�ɂ����A���̂Ƃ���p�����Ă��Ȃ�
 */
public class NameServiceViewWrapperFactory {

    private static NameServiceViewWrapperFactory __instance = null;
    
    private SynchronizationManager synchronizationManager;

    /**
     * �R���X�g���N�^
     * 
     * @param mappingRules
     */
    private NameServiceViewWrapperFactory(MappingRule[] mappingRules) {
    	 synchronizationManager = new SynchronizationManager(mappingRules);
    }

    /**
     * �t�@�N�g���̃V���O���g�����擾����
     * 
     * @return �t�@�N�g���̃V���O���g��
     */
    public static NameServiceViewWrapperFactory getInstance() {
        if (__instance == null) {
            __instance = new NameServiceViewWrapperFactory(MappingRuleFactory
                    .getMappingRule());
        }

        return __instance;
    }


    /**
     * �l�[���R���e�N�X�g�I�u�W�F�N�g�ƃl�[���T�[�o������A�l�[���T�[�o�̃h���C���I�u�W�F�N�g���쐬����
     * 
     * @param namingContext
     *            �l�[�~���O�R���e�N�X�g
     * @param nameServerName
     *            �l�[���T�[�o��
     * @return �l�[���T�[�o�̃h���C���I�u�W�F�N�g
     */
    public NamingContextNode getNameServiceContextCorbaWrapper(
            NamingContextExt namingContext, String nameServerName) {

        NameServiceReference nameServiceReference = NameserviceFactory.eINSTANCE.createNameServiceReference();
        nameServiceReference.setNameServerName(nameServerName);
        Binding binding = new Binding();
        binding.binding_name = new NameComponent[] {};
        nameServiceReference.setBinding(binding);
        nameServiceReference.setRootNamingContext(namingContext);
        
        return (NamingContextNode) synchronizationManager.createLocalObject(null,
                new Object[]{namingContext, nameServiceReference}, null, false);
    }
}
