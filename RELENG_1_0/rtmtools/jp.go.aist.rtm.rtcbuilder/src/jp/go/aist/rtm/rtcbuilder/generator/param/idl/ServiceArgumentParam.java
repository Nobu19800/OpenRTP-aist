package jp.go.aist.rtm.rtcbuilder.generator.param.idl;

import java.io.Serializable;

/**
 * �T�[�r�X�̃��\�b�h����������킷�N���X
 */
public class ServiceArgumentParam implements Serializable {
	private static final long serialVersionUID = 5610882091160166120L;

	private String type;
	private String name;
	private String direction;

	public String getName() {
		return name;
	}

	public void setName(String argName) {
		this.name = argName;
	}

	public String getType() {
		return type;
	}

	public void setType(String argType) {
		this.type = argType;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String argDirection) {
		this.direction = argDirection;
	}
}
