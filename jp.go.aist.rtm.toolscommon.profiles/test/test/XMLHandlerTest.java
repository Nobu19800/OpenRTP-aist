package test;

import java.io.FileOutputStream;
import java.net.URLDecoder;

import junit.framework.TestCase;

public class XMLHandlerTest extends TestCase {
	// /RTSystemEditor/a�@b��/RTSystemEditor/a%20b�f�B���N�g�������݂���Ƃ����O��̃e�X�g
	public void testHasSpace() throws Exception {
		String fileName = "/RTSystemEditor/a%20b/test.xml";
		new FileOutputStream(fileName);
	}
	public void testHasSpace2() throws Exception {
		String fileName = "/RTSystemEditor/a b/test.xml";
		new FileOutputStream(fileName);
	}
	public void testHasSpace3() throws Exception {
		String fileName = "/RTSystemEditor/a%20b/test.xml";
		new FileOutputStream(URLDecoder.decode(fileName, "UTF-8"));
	}
	public void testHasSpace4() throws Exception {
		String fileName = "/RTSystemEditor/a%2520b/test.xml";
		new FileOutputStream(URLDecoder.decode(fileName, "UTF-8"));
	}
}
