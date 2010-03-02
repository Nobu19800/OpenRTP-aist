package jp.go.aist.rtm.rtcbuilder._test.com;

import jp.go.aist.rtm.rtcbuilder.ui.StringUtil;
import junit.framework.TestCase;

public class StringUtilTest extends TestCase {
	public void testSplit() throws Exception{
		String original = "1234567890";
		String result = StringUtil.splitString(original, 5, " * ", 0);
		String expected = "12345\r\n * 67890";
		assertEquals(expected, result);
	}

	public void testSplit2() throws Exception{
		String original = "12345678901";
		String result = StringUtil.splitString(original, 5, " * ", 0);
		String expected = "12345\r\n * 67890\r\n * 1";
		assertEquals(expected, result);
	}

	public void testSplit3() throws Exception{
		String original = "�P�Q�R�S�T�U�V�W�X";
		String result = StringUtil.splitString(original, 5, " * ", 0);
		String expected = "�P�Q�R\r\n * �S�T�U\r\n * �V�W�X";
		assertEquals(expected, result);
	}

	public void testSplit4() throws Exception{
		String original = "�P�Q�R�S�T�U�V�W�X�O";
		String result = StringUtil.splitString(original, 5, " * ", 0);
		String expected = "�P�Q�R\r\n * �S�T�U\r\n * �V�W�X\r\n * �O";
		assertEquals(expected, result);
	}

	public void testSplit5() throws Exception{
		String original = "1�Q�R4�T�U7�W�X�O";
		String result = StringUtil.splitString(original, 5, " * ", 0);
		String expected = "1�Q�R\r\n * 4�T�U\r\n * 7�W�X\r\n * �O";
		assertEquals(expected, result);
	}

	public void testSplit6() throws Exception{
		String original = "12�R�S�T67�W�X�O";
		String result = StringUtil.splitString(original, 5, " * ", 0);
		String expected = "12�R�S\r\n * �T67�W\r\n * �X�O";
		assertEquals(expected, result);
	}

	public void testSplit7() throws Exception{
		String original = "12�R�S�T67�W�X�O";
		String result = StringUtil.splitString(original, 5, " * ", 3);
		String expected = "12\r\n * �R�S�T\r\n * 67�W�X\r\n * �O";
		assertEquals(expected, result);
	}

	public void testSplit8() throws Exception{
		String original = "1234567";
		String result = StringUtil.splitString(original, 10, " * ", 1);
		String expected = "1234567";
		assertEquals(expected, result);
	}

	public void testSplit9() throws Exception{
		String original = "123456<br>";
		String result = StringUtil.splitString(original, 8, " * ", 0);
		String expected = "123456<br>";
		assertEquals(expected, result);
	}
	
	public void testSplit10() throws Exception{
		String original = "<li><a href=\"/news-and-topics/\" title=\"�V�����\">�V�����</a></li>";
		String result = StringUtil.splitString(original, 5, " * ", 0);
		String expected = "<li><a href=\"/news-and-topics/\" title=\"�V�����\">\r\n * �V����\r\n * ��</a>\r\n * </li>";
		assertEquals(expected, result);
	}

//	public void testSplit11() throws Exception{
//		String original = "<li><a href=\"/news-and-topics/\" title=\"�V�����\">�V�����</a></li>";
//		String result = StringUtil.splitString(original, 50, " * ", 0);
//		String expected = "<li><a href=\"/news-and-topics/\" title=\"�V�����\">\r\n * �V�����</a></li>";
//		assertEquals(expected, result);
//	}

	public void testSplit12() throws Exception{
		String original = "<li><a href=\"/news-and-topics/\" title=\"�V�����\">�V�����</a></li>";
		String result = StringUtil.splitString(original, 4, " * ", 0);
		String expected = "<li>\r\n * <a href=\"/news-and-topics/\" title=\"�V�����\">\r\n * �V��\r\n * ���\r\n * </a>\r\n * </li>";
		assertEquals(expected, result);
	}
	
	public void testSplit13() throws Exception{
		String original = "<li><span><br></span></li>";
		String result = StringUtil.splitString(original, 15, " * ", 0);
		String expected = "<li><span><br></span>\r\n * </li>";
		assertEquals(expected, result);
	}
	
	public void testSplit14() throws Exception{
		String original = "<li><span><br></span></li>";
		String result = StringUtil.splitString(original, 14, " * ", 0);
		String expected = "<li><span><br>\r\n * </span></li>";
		assertEquals(expected, result);
	}
	
	public void testSplit15() throws Exception{
		String original = "<li><span><br></span></li>";
		String result = StringUtil.splitString(original, 2, " * ", 0);
		String expected = "<li>\r\n * <span>\r\n * <br>\r\n * </span>\r\n * </li>";
		assertEquals(expected, result);
	}

	public void testSplit9_2() throws Exception{
		String original = "123456<br>";
		String result = StringUtil.splitString(original, 8, " * ", 3);
		String expected = "12345\r\n * 6<br>";
		assertEquals(expected, result);
	}

	public void testSplit10_2() throws Exception{
	String original = "<li><a href=\"/news-and-topics/\" title=\"�V�����\">�V�����</a></li>";
	String result = StringUtil.splitString(original, 5, " * ", 1);
	String expected = "<li>\r\n * <a href=\"/news-and-topics/\" title=\"�V�����\">\r\n * �V����\r\n * ��</a>\r\n * </li>";
	assertEquals(expected, result);
}
	
	public void testSplit11_2() throws Exception{
		String original = "<li><a href=\"/news-and-topics/\" title=\"�V�����\">�V�����</a></li>";
		String result = StringUtil.splitString(original, 50, " * ", 45);
		String expected = "<li><a href=\"/news-and-topics/\" title=\"�V�����\">\r\n * �V�����</a></li>";
		assertEquals(expected, result);
	}
	
	public void testSplit13_2() throws Exception{
		String original = "<li><span><br></span></li>";
		String result = StringUtil.splitString(original, 15, " * ", 5);
		String expected = "<li><span>\r\n * <br></span></li>";
		System.out.println(expected);
		System.out.println(result);
		assertEquals(expected, result);
	}
	
	public void testSplit14_2() throws Exception{
		String original = "<li><span><br></span></li>";
		String result = StringUtil.splitString(original, 14, " * ", 4);
		String expected = "<li><span>\r\n * <br></span></li>";
		System.out.println(expected);
		System.out.println(result);
		assertEquals(expected, result);
	}
	
	public void testSplit15_2() throws Exception{
		String original = "<li><span><br></span></li>";
		String result = StringUtil.splitString(original, 2, " * ", 1);
		String expected = "<li>\r\n * <span>\r\n * <br>\r\n * </span>\r\n * </li>";
		assertEquals(expected, result);
	}
	
	
	public void testSplit16() throws Exception{
		String original = "�P�Q�R�S�T�U�V�W�X�O�P�Q�R�S�T�U�V�W�X�O<br>";
		String result = StringUtil.splitString(original, 16, " * ", 0);
		String expected = "�P�Q�R�S�T�U�V�W\r\n * �X�O�P�Q�R�S�T�U\r\n * �V�W�X�O<br>";
		assertEquals(expected, result);
	}
	public void testSplit17() throws Exception{
		String original = "�P�Q�R�S�T<br/>�U�V�W�X�O�P�Q�R�S�T�U�V�W�X�O<br>";
		String result = StringUtil.splitString(original, 16, " * ", 0);
		String expected = "�P�Q�R�S�T\r\n * �U�V�W�X�O�P�Q�R\r\n * �S�T�U�V�W�X�O<br>";
		assertEquals(expected, result);
	}
}
