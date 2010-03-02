package jp.go.aist.rtm.toolscommon.synchronizationframework.mapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.go.aist.rtm.toolscommon.synchronizationframework.LocalObject;

/**
 * �}�b�s���O���[�����`����N���X
 */
public class MappingRule {
	private MappingRule superMappingRule;

	private ClassMapping classMapping;

	private AttributeMapping[] attributeMappings;

	private ReferenceMapping[] referenceMappings;

	/**
	 * �R���X�g���N�^
	 * <p>
	 * �p�����̃}�b�s���O���[�����w�肷��ƁA�����ƎQ�Ƃ��p�������B�p���������݂��Ȃ��ꍇ�ɂ�null�B
	 * 
	 * @param superMappingRule
	 *            �p�����̃}�b�s���O���[��
	 * @param classMapping
	 *            �N���X�}�b�s���O
	 * @param attributeMappings
	 *            �����}�b�s���O
	 * @param referenceMappings
	 *            �Q�ƃ}�b�s���O
	 */
	public MappingRule(MappingRule superMappingRule, ClassMapping classMapping,
			AttributeMapping[] attributeMappings,
			ReferenceMapping[] referenceMappings) {
		this.superMappingRule = superMappingRule;
		this.classMapping = classMapping;
		this.attributeMappings = attributeMappings;
		this.referenceMappings = referenceMappings;
	}

	/**
	 * �����}�b�s���O���擾����
	 * 
	 * @return �����}�b�s���O
	 */
	public AttributeMapping[] getAttributeMappings() {
		return attributeMappings;
	}

	/**
	 * �N���X�}�b�s���O���擾����
	 * 
	 * @return �N���X�}�b�s���O
	 */
	public ClassMapping getClassMapping() {
		return classMapping;
	}

	/**
	 * �Q�ƃ}�b�s���O���擾����
	 * 
	 * @return �Q�ƃ}�b�s���O
	 */
	public ReferenceMapping[] getReferenceMappings() {
		return referenceMappings;
	}

	/**
	 * �����}�b�s���O���擾����
	 * 
	 * @return �����}�b�s���O
	 */
	public AttributeMapping[] getAllAttributeMappings() {
		List<AttributeMapping> result = new ArrayList<AttributeMapping>();

		MappingRule temp = this;
		while (temp != null) {
			result.addAll(Arrays.asList(temp.getAttributeMappings()));
			temp = temp.getSuperMappingRule();
		}

		return (AttributeMapping[]) result.toArray(new AttributeMapping[result
				.size()]);
	}

	/**
	 * �p�������܂ނ��ׂĂ̎Q�ƃ}�b�s���O���擾����
	 * @return �Q�ƃ}�b�s���O
	 */
	public ReferenceMapping[] getAllReferenceMappings() {
		List<ReferenceMapping> result = new ArrayList<ReferenceMapping>();

		MappingRule temp = this;
		while (temp != null) {
			result.addAll(Arrays.asList(temp.getReferenceMappings()));
			temp = temp.getSuperMappingRule();
		}

		return (ReferenceMapping[]) result.toArray(new ReferenceMapping[result
				.size()]);
	}

	/**
	 * �p�����̃}�b�s���O���[�����擾����
	 * @return �p�����̃}�b�s���O���[��
	 */
	public MappingRule getSuperMappingRule() {
		return superMappingRule;
	}

	/**
	 * �p�������܂ނ��ׂĂ̑����}�b�s���O���擾����
	 * @return �����}�b�s���O
	 */
	public ReferenceMapping[] getAllContainerReferenceMappings() {
		List<ReferenceMapping> result = new ArrayList<ReferenceMapping>();
		for (ReferenceMapping referrenceMapping : getAllReferenceMappings()) {
			if (referrenceMapping.getLocalFeature().isContainment()) {
				result.add(referrenceMapping);
			}
		}

		return (ReferenceMapping[]) result.toArray(new ReferenceMapping[result
				.size()]);
	}

	/**
	 * localObject�̃N���X��ClassMapping�̃��[�J���N���X�ƈ�v���邱�ƈȊO�Ƀ}�b�s���O�̏�����
	 * ���݂���ꍇ�ɃI�[�o���C�h����
	 * @param localObject
	 * @return
	 */
	public boolean isTarget(LocalObject localObject) {
		return true;
	}

}
