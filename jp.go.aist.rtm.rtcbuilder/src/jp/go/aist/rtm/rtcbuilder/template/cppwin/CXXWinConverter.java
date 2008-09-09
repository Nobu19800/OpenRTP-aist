package jp.go.aist.rtm.rtcbuilder.template.cppwin;

import java.util.UUID;

import jp.go.aist.rtm.rtcbuilder.template.cpp.CXXConverter;

/**
 * CXX�\�[�X���o�͂���ۂɎg�p����郆�[�e�B���e�B
 */
public class CXXWinConverter extends CXXConverter{
	private final String idlLongDouble = "longdouble";

	private final String cppLongDouble = "CORBA::Double";

	public CXXWinConverter() {
		super();
		mapType.put(idlLongDouble, cppLongDouble);
	}
	/**
	 * Random UUID���擾����
	 * 
	 * @return UUID
	 */
	public static String getRandomUUID(boolean isTest) {
		if( isTest ) return "ef7bf9c2-3efa-11dc-8b67-000c297a69c0";
		return UUID.randomUUID().toString();
	}

	/**
	 * CPP�^����CORBA�^�ւ̃}�b�s���O���`����
	 * 
	 * @param strcpp
	 *            CPP�^
	 * @return CORBA�^
	 */
	public String convCpp2CORBA(String strcpp) {
		String result = mapType.get(strcpp);
		if( result == null ) result = strcpp + "*";
		return result;
	}
}
