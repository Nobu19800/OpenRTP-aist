package jp.go.aist.rtm.rtcbuilder.generator;

/**
 * �e���v���[�g���琶�����ꂽ�A�t�@�C�����ƃR���e���c���i�[����N���X
 */
public class GeneratedResult {
	private String name;
	private String code = "";

	public GeneratedResult() {
		this.name = "";
		this.code = "";
	}


	public GeneratedResult(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
