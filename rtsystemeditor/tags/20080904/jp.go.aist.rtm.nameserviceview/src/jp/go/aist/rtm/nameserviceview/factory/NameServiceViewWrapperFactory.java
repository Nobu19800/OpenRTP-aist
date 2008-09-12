package jp.go.aist.rtm.nameserviceview.factory;

import jp.go.aist.rtm.nameserviceview.model.nameservice.NameServerNamingContext;
import jp.go.aist.rtm.nameserviceview.model.nameservice.NameServiceReference;
import jp.go.aist.rtm.nameserviceview.model.nameservice.impl.NameServiceReferenceImpl;
import jp.go.aist.rtm.toolscommon.factory.MappingRuleFactory;
import jp.go.aist.rtm.toolscommon.model.core.WrapperObject;
import jp.go.aist.rtm.toolscommon.synchronizationframework.SynchronizationManager;
import jp.go.aist.rtm.toolscommon.synchronizationframework.mapping.MappingRule;

import org.omg.CosNaming.Binding;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;

/**
 * RtcLink�̓����Ŏg�p�����h���C���I�u�W�F�N�g���쐬����t�@�N�g��
 * <p>
 * �����ł́A�����t���[�����[�N���g�p���Ă���B
 */
public class NameServiceViewWrapperFactory {

    private static NameServiceViewWrapperFactory __instance = null;
    
    private SynchronizationManager synchronizationManager;

    /**
     * �R���X�g���N�^
     * <p>
     * ���̃}�b�s���O���[�����g�p�����t�@�N�g�����쐬���邱�Ƃ��ł���悤�ɃR���X�g���N�^�����J���邪�A��{�I�ɂ�getInstance()�𗘗p���ăV���O���g�����쐬���邱��
     * 
     * @param mappingRules
     */
    public NameServiceViewWrapperFactory(MappingRule[] mappingRules) {
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
    public NameServerNamingContext getNameServiceContextCorbaWrapper(
            NamingContextExt namingContext, String nameServerName) {

        NameServiceReference nameServiceReference = new NameServiceReferenceImpl();
        nameServiceReference.setNameServerName(nameServerName);
        Binding binding = new Binding();
        binding.binding_name = new NameComponent[] {};
        nameServiceReference.setBinding(binding);
        nameServiceReference.setRootNamingContext(namingContext);
        NameServerNamingContext result = (NameServerNamingContext) createWrapperObject(
                namingContext, nameServiceReference);

        return result;
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
