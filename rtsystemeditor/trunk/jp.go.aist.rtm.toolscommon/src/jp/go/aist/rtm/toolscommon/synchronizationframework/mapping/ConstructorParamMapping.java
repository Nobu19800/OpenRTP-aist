package jp.go.aist.rtm.toolscommon.synchronizationframework.mapping;

import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * �R���X�g���N�^�̈�����\���N���X
 * <p>
 * �R���X�g���N�^�Ƃ����Ȃ���A���ۂɂ�DI�X�^�C���ŃZ�b�^���g�p���ăZ�b�g����B
 * �܂��Z�b�^��ݒ肷���targetClass�̓Z�b�^�̌^���玩���I�ɔ��f����B
 */
public class ConstructorParamMapping {
	private EStructuralFeature feature;

	private Class targetClass;

	public ConstructorParamMapping(EStructuralFeature feature) {
		this.feature = feature;
	}

	public ConstructorParamMapping(Class targetClass, EStructuralFeature feature) {
		this.feature = feature;
		this.targetClass = targetClass;
	}

	public EStructuralFeature getFeature() {
		return feature;
	}

	public void setFeature(EStructuralFeature feature) {
		this.feature = feature;
	}

	public Class getTargetClass() {
		Class result = targetClass;
		if (result == null) {
			result = feature.getEType().getInstanceClass();
		}
		
		return result;
	}
}
