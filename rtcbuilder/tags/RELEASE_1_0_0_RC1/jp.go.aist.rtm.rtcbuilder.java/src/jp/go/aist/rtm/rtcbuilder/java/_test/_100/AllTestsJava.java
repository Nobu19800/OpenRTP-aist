package jp.go.aist.rtm.rtcbuilder.java._test._100;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTestsJava {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for jp.go.aist.rtm.rtcbuilder._test");
		//$JUnit-BEGIN$
		suite.addTestSuite(ConfigSetTest.class);
		suite.addTestSuite(MultiTest.class);
		suite.addTestSuite(BaseTest.class);
		//$JUnit-END$
		return suite;
	}

}