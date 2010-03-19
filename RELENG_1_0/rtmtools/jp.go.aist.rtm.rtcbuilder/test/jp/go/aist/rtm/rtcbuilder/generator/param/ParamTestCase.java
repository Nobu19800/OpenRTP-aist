package jp.go.aist.rtm.rtcbuilder.generator.param;

import junit.framework.TestCase;

public class ParamTestCase<E> extends TestCase {

	/** Updated�̃`�F�b�J */
	abstract class UpdateChecker {
		abstract void execute1(E e);

		abstract void execute2(E e);
	}

	/** Updated�`�F�b�N��assert */
	void assertUpdated(E e, UpdateChecker checker) {
		UpdateRecordable ur = (UpdateRecordable) e;
		ur.resetUpdated();
		assertFalse(ur.isUpdated());
		//
		checker.execute1(e);
		assertTrue(ur.isUpdated());
		ur.resetUpdated();
		assertFalse(ur.isUpdated());
		//
		checker.execute1(e);
		assertFalse(ur.isUpdated());
		//
		checker.execute2(e);
		assertTrue(ur.isUpdated());
	}

}
