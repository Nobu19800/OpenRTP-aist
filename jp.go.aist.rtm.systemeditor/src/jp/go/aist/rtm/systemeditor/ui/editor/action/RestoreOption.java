package jp.go.aist.rtm.systemeditor.ui.editor.action;

/**
 * �I�����C���V�X�e���_�C�A�O������RTS�v���t�@�C�������[�h����Ƃ��̕����I�v�V����
 *
 */
public enum RestoreOption {
	NONE(), NORMAL(false, true), QUICK(true, true);

	private final boolean doQuick;
	private final boolean doRelace;
	
	RestoreOption(){
		this(true, false);
	}
	RestoreOption(boolean doQuick, boolean doReplace) {
		this.doQuick = doQuick;
		this.doRelace = doReplace;
	}
	
	public boolean doQuick() {
		return doQuick;
	}

	public boolean doReplace() {
		return doRelace;
	}
}
