package jp.go.aist.rtm.toolscommon.ui.propertysource;

/**
 * PropertySource�p�̃_�C�i�~�b�N��ID��\���N���X
 * <p>
 * �t�B�[���h�̓p�u���b�N�Ƃ���
 */
public class DynamicID {
	public DynamicID(String categoryId, String subId) {
		this.categoryId = categoryId;
		this.subId = subId;
	}

	public String categoryId;

	public String subId;
}
