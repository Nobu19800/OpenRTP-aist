package jp.go.aist.rtm.rtcbuilder.generator.param.idl;

import java.io.Serializable;

/**
 * �ϐ���`������킷�N���X
 */
public class TypeDefParam implements Serializable {
	private String originalDef;
	private String targetDef;

	public TypeDefParam() {
	}

	public String getOriginalDef() {
		return originalDef;
	}

	public void setOriginalDef(String originalDef) {
		this.originalDef = originalDef;
	}

	public String getTargetDef() {
		return this.targetDef;
	}

	public void setTargetDef(String targetDef) {
		this.targetDef = targetDef;
	}
}
