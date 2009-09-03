package jp.go.aist.rtm.toolscommon.synchronizationframework.mapping;

import jp.go.aist.rtm.toolscommon.synchronizationframework.LocalObject;

/**
 * �N���X�̃}�b�s���O���`���邽�߂̃N���X
 * <p>
 * ConstructorParamMappings�Ƀ}�b�v�\�ȃ����[�g�I�u�W�F�N�g�ƑΉ�����
 */
public class ClassMapping {
	private Class localClass;

	private ConstructorParamMapping[] constructorParamMappings;

	private boolean allowZombie;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param localClass
	 *            ���[�J���I�u�W�F�N�g�̃N���X
	 * @param remoteClass
	 *            �����[�g�I�u�W�F�N�g�̃N���X
	 * @param allowZombie �]���r�i�����[�g�I�u�W�F�N�g�����񂾏�ԁj�ł����݂����邩
	 * 			
	 */
	public ClassMapping(Class localClass,
			ConstructorParamMapping[] constructorParamMappings,boolean allowZombie) {
		this.localClass = localClass;
		this.constructorParamMappings = constructorParamMappings;
		this.allowZombie = allowZombie;
	}

	/**
	 * �R���X�g���N�^
	 * 
	 * @param localClass
	 *            ���[�J���I�u�W�F�N�g�̃N���X
	 * @param remoteClass
	 *            �����[�g�I�u�W�F�N�g�̃N���X
	 */
	public ClassMapping(Class localClass,
			ConstructorParamMapping[] constructorParamMappings) {
		this(localClass,constructorParamMappings,false);
	}

	/**
	 * ���̃N���X�}�b�s���O���A�����[�g�I�u�W�F�N�g�Ɋ֘A�Â����}�b�s���O�ł��邩�ǂ���
	 * <p>
	 * �����[�g�I�u�W�F�N�g�Ɛe���[�J���I�u�W�F�N�g�A����у����N����A ���̃N���X�}�b�s���O���֘A�Â����}�b�s���O�ł��邩�ǂ������f����B<br>
	 * CORBA�I�u�W�F�N�g�̏ꍇ�Anarrow���Ĕ�r����K�v�����邽�߁A�I�[�o�[���C�h���邱�ƁB
	 * 
	 * @param parent
	 *            �e�̃��[�J���I�u�W�F�N�g
	 * @param remoteObjects
	 * @param link
	 *            �֘A������킷�����N(�����[�g�I�u�W�F�N�g���̂ł��邱�Ƃ�����)
	 * @return
	 */
	public boolean isTarget(LocalObject parent, Object[] remoteObjects,
			java.lang.Object link) {
		boolean result = true;
		for (int i = 0; i < remoteObjects.length; i++) {
			if (constructorParamMappings[i].getTargetClass().isAssignableFrom(
					remoteObjects[i].getClass()) == false) {
				result = false;
			}
		}

		return result;
	}

	/**
	 * �����[�g�I�u�W�F�N�g����A���[�J���I�u�W�F�N�g���쐬����B
	 * 
	 * @param parent
	 *            �e�̃��[�J���I�u�W�F�N�g
	 * @param remoteObjects
	 *            �����[�g�I�u�W�F�N�g
	 * @param link
	 *            �֘A������킷�����N(�����[�g�I�u�W�F�N�g���̂ł��邱�Ƃ�����)
	 * @return
	 */
	public LocalObject createLocalObject(LocalObject parent,
			Object[] remoteObjects, java.lang.Object link) {
		try {
			LocalObject result = (LocalObject) localClass.newInstance();
			for (int i = 0; i < constructorParamMappings.length; ++i) {
				result.eSet(constructorParamMappings[i].getFeature(),
						remoteObjects[i]);
			}

			return result;
		} catch (InstantiationException e) {
			throw new RuntimeException(e); // system error
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e); // system error
		}
	}

	/**
	 * ���[�J���I�u�W�F�N�g�̃N���X
	 * 
	 * @return ���[�J���I�u�W�F�N�g�̃N���X
	 */
	public Class getLocalClass() {
		return localClass;
	}

	/**
	 * narrow����
	 * 
	 * @param remoteObjects
	 * @return
	 */
	public Object[] narrow(Object[] remoteObjects) {
		return remoteObjects;
	}

	/**
	 * ConstructorParamMapping���擾����
	 * 
	 * @return
	 */
	public ConstructorParamMapping[] getConstructorParamMappings() {
		return constructorParamMappings;
	}

	/**
	 * �]���r�i�����[�g�I�u�W�F�N�g�����񂾏�ԁj�ł����݂����邩
	 * @return
	 */
	public boolean allowZombie() {
		return allowZombie;
	}

}
