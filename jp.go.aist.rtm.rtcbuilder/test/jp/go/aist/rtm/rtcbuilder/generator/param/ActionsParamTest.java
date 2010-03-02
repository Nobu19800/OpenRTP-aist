package jp.go.aist.rtm.rtcbuilder.generator.param;

public class ActionsParamTest extends ParamTestCase<ActionsParam> {

	ActionsParam ap;

	@Override
	protected void setUp() throws Exception {
		ap = new ActionsParam();
	}

	public void testIsUpdatedBasic() throws Exception {
		//
		assertUpdated(ap, new UpdateChecker() {
			@Override
			void execute1(ActionsParam e) {
				e.setImplemaented("TRUE");
			}

			@Override
			void execute2(ActionsParam e) {
				e.setImplemaented("FALSE");
			}
		});
		//
		assertUpdated(ap, new UpdateChecker() {
			@Override
			void execute1(ActionsParam e) {
				e.setImplemaented(true);
			}

			@Override
			void execute2(ActionsParam e) {
				e.setImplemaented(false);
			}
		});
		//
		assertUpdated(ap, new UpdateChecker() {
			@Override
			void execute1(ActionsParam e) {
				e.setOverview("�T�v�P");
			}

			@Override
			void execute2(ActionsParam e) {
				e.setOverview("�T�v�Q");
			}
		});
		//
		assertUpdated(ap, new UpdateChecker() {
			@Override
			void execute1(ActionsParam e) {
				e.setPreCondition("���O�����P");
			}

			@Override
			void execute2(ActionsParam e) {
				e.setPreCondition("���O�����Q");
			}
		});
		//
		assertUpdated(ap, new UpdateChecker() {
			@Override
			void execute1(ActionsParam e) {
				e.setPostCondition("��������P");
			}

			@Override
			void execute2(ActionsParam e) {
				e.setPostCondition("��������Q");
			}
		});
	}

}
