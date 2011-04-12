package jp.go.aist.rtm.rtcbuilder.generator.param;

public class DataPortParamTest extends ParamTestCase<DataPortParam> {

	DataPortParam dp;

	@Override
	protected void setUp() throws Exception {
		dp = new DataPortParam();
	}

	public void testIsUpdatedPosision() throws Exception {
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setPositionByIndex(1);
			}

			@Override
			void execute2(DataPortParam e) {
				e.setPositionByIndex(2);
			}
		});
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setPosition("BOTTOM");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setPosition("RIGHT");
			}
		});
	}

	public void testIsUpdatedBasic() throws Exception {
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setName("in1");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setName("in2");
			}
		});
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setType("TimedFloat");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setType("TimedLong");
			}
		});
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setVarName("in1Port");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setVarName("in2Port");
			}
		});
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setIdlFile("IDLFile1");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setIdlFile("IDLFile2");
			}
		});
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setDataFlowType("TYPE1");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setDataFlowType("TYPE2");
			}
		});
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setInterfaceType("INTERFACE1");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setInterfaceType("INTERFACE2");
			}
		});
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setSubscriptionType("SUBSCRIPTION1");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setSubscriptionType("SUBSCRIPTION2");
			}
		});
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setConstraint("CONSTRAINT1");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setConstraint("CONSTRAINT2");
			}
		});
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setUnit("UNIT1");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setUnit("UNIT2");
			}
		});
	}

	public void testIsUpdatedDocument() throws Exception {
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setDocDescription("�|�[�g�T�v�P");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setDocDescription("�|�[�g�T�v�Q");
			}
		});
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setDocType("�|�[�g��ʂP");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setDocType("�|�[�g��ʂQ");
			}
		});
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setDocNum("�f�[�^���P");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setDocNum("�f�[�^���Q");
			}
		});
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setDocSemantics("�|�[�g�����P");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setDocSemantics("�|�[�g�����Q");
			}
		});
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setDocUnit("�|�[�g�P�ʂP");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setDocUnit("�|�[�g�P�ʂQ");
			}
		});
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setDocOccurrence("�����p�x�P");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setDocOccurrence("�����p�x�Q");
			}
		});
		//
		assertUpdated(dp, new UpdateChecker() {
			@Override
			void execute1(DataPortParam e) {
				e.setDocOperation("���s�p�x�P");
			}

			@Override
			void execute2(DataPortParam e) {
				e.setDocOperation("���s�p�x�Q");
			}
		});
	}

}
