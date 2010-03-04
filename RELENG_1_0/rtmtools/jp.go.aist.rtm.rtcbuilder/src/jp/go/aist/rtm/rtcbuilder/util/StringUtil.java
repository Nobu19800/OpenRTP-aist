package jp.go.aist.rtm.rtcbuilder.util;

import java.util.ArrayList;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;


public class StringUtil {

	private static final String START_MARK = "<";
	private static final String END_MARK = ">";

	public static boolean checkDigitAlphabet(String source) {
		if(source==null) return true;
	    for(int intIdx = 0; intIdx < source.length(); intIdx++) {
	        char target = source.charAt(intIdx);
	        if( (target < '0' || target > '9') &&    //�����`�F�b�N
	            (target < 'a' || target > 'z') &&    //�������A���t�@�x�b�g�`�F�b�N
	            (target < 'A' || target > 'Z') &&    //�啶���A���t�@�x�b�g�`�F�b�N
	            (target != '_') && (target != '-') && (target != ':')) {
	             return false;
	        }
	    }
	    return true;
	}

	public static String splitString(String source, int width, String prefix, int offset) {
		if( source==null || source.equals("") || width<=0 ) return "";

		String sep = System.getProperty("line.separator");
		String lines[] = source.split(IRtcBuilderConstants.NEWLINE_CODE);

		// �ꕶ�����ێ�
		StringBuffer strBuf = new StringBuffer();
		// �߂�l�p
		StringBuffer result = new StringBuffer();
		
		// result�ɓ�������O��work
		ArrayList<StringBuffer> workResult = new ArrayList<StringBuffer>();
		ArrayList<StringBuffer> temp = new ArrayList<StringBuffer>();
		
		int length = offset;
		boolean bolFlg = false;
		
		for( int intline=0; intline<lines.length; intline++ ) {
			source = lines[intline];
			for( int intIdx=0; intIdx<source.length(); intIdx++ ) {
				length += String.valueOf(source.charAt(intIdx)).getBytes().length;
				// �ꕶ�����擾����
				strBuf.append(source.charAt(intIdx));
				// ���s�����̏ꍇ�́C���̑O�܂ł𓊓�
				if (String.valueOf(source.charAt(intIdx)).equals(sep)) {
					workResult.add(strBuf);
					strBuf = new StringBuffer();
					length = 0;
				}
				
				if (String.valueOf(source.charAt(intIdx)).equals(START_MARK)) {
					// temp�̒l��work�ɓ���
					if (temp.size() > 0) {
						workResult.addAll(temp);
						temp = new ArrayList<StringBuffer>();
					}
					bolFlg = false;
				}
				
				if (String.valueOf(source.charAt(intIdx)).equals(END_MARK)) {
					bolFlg = true;
					if (temp.size() > 0) {
						// �I�������܂ł�StringBuffer�ɂ��߁Awork�ɓ���
						StringBuffer workBuffer = new StringBuffer();
						for (int intIdx2=0; intIdx2 < temp.size(); intIdx2++) {
							workBuffer.append(temp.get(intIdx2));
						}
						workBuffer.append(strBuf);
						workResult.add(workBuffer);
						// ������
						bolFlg = false;
						temp = new ArrayList<StringBuffer>();
						strBuf = new StringBuffer();
						length = 0;
					}
				}
				
				if(length >= width) {
					// width����������擾�������ɏI���������܂܂�Ă��Ȃ����temp��
					// �܂܂�Ă�����work�ցB
					if (bolFlg == false) {
						temp.add(strBuf);
					} else {
						workResult.add(strBuf);
					}
					strBuf = new StringBuffer();
					length = 0;
				}
				
			}
	
			// temp�Ɏc���Ă��镶�����work��
			if (temp.size() > 0) workResult.addAll(temp);
			// strBuf�Ɏc���Ă��镶�����work��
			if (strBuf.length() > 0) workResult.add(strBuf);
			temp = new ArrayList<StringBuffer>();
			strBuf = new StringBuffer();
			length = 0;
		}
		
		// workResult����result�𐬌`����
		for (int intIdx=0; intIdx < workResult.size();intIdx++){
			if (intIdx > 0)	result.append(prefix);
			result.append(workResult.get(intIdx));
			if (intIdx+1 < workResult.size() ) result.append("\r\n");
		}

		return result.toString();
	}

//		if( source==null || source.equals("") || width<=0 ) return "";
//		StringBuffer result = new StringBuffer();
//		int length = offset;
//		int startpos = 0;
//		for( int intIdx=0; intIdx<source.length(); intIdx++ ) {
//			length += String.valueOf(source.charAt(intIdx)).getBytes().length;
//			if( width<=length ) {
//				if( result.length() > 0 )
//					result.append(prefix);
//				result.append(source.substring(startpos, intIdx+1));
//				if( intIdx+1 < source.length() )
//					result.append("\r\n");
//				startpos = intIdx+1;
//				length = 0;
//			}
//		}
//		if( startpos != source.length() ) {
//			if( result.length()>0 )
//				result.append(prefix);
//			result.append(source.substring(startpos));
//		}
//		return result.toString();
//	}
//
	
	public static String connectMessageWithSepalator(String[] ss){
		if( ss==null ) return "";
		
		String result = "";
		for( int i=0; i<ss.length; i++ ){
			result += ss[i];
			if( !"".equals(ss[i]) )
				result += System.getProperty("line.separator");
		}
		
		return result;
	}
}
