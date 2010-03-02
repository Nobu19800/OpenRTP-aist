package jp.go.aist.rtm.rtcbuilder.generator.param;

public class ConfigSetParamTest extends ParamTestCase<ConfigSetParam> {

	ConfigSetParam csp;

	@Override
	protected void setUp() throws Exception {
		csp = new ConfigSetParam();
	}

	public void testIsUpdatedBasic() throws Exception {
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setName("name1");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setName("name2");
			}
		});
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setType("type1");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setType("type2");
			}
		});
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setVarName("var1");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setVarName("var2");
			}
		});
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setDefaultVal("default1");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setDefaultVal("default2");
			}
		});
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setConstraint("x>10");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setConstraint("x<=10");
			}
		});
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setUnit("msec");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setUnit("usec");
			}
		});
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setWidget("spin");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setWidget("slider");
			}
		});
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setSliderStep("0.1");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setSliderStep("0.01");
			}
		});
	}

	public void testIsUpdatedDocument() throws Exception {
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setDocDataName("�f�[�^���P");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setDocDataName("�f�[�^���Q");
			}
		});
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setDocDefaultVal("�f�t�H���g�l�P");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setDocDefaultVal("�f�t�H���g�l�Q");
			}
		});
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setDocDescription("�T�v�P");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setDocDescription("�T�v�Q");
			}
		});
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setDocUnit("�P�ʂP");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setDocUnit("�P�ʂQ");
			}
		});
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setDocRange("�͈͂P");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setDocRange("�͈͂Q");
			}
		});
		//
		assertUpdated(csp, new UpdateChecker() {
			@Override
			void execute1(ConfigSetParam e) {
				e.setDocConstraint("��������P");
			}

			@Override
			void execute2(ConfigSetParam e) {
				e.setDocConstraint("��������Q");
			}
		});
	}

}
