package jp.go.aist.rtm.rtcbuilder.generator.param;

public class RtcParamTest extends ParamTestCase<RtcParam> {

	GeneratorParam genParam;

	RtcParam rtcParam;

	@Override
	protected void setUp() throws Exception {
		genParam = new GeneratorParam();
		rtcParam = new RtcParam(genParam, true);
	}

	public void testIsUpdatedBasic() throws Exception {
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setSchemaVersion("0.1");
			}

			@Override
			void execute2(RtcParam e) {
				e.setSchemaVersion("0.2");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setAbstract("�T�v�P");
			}

			@Override
			void execute2(RtcParam e) {
				e.setAbstract("�T�v�Q");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setCreationDate("2009/10/10");
			}

			@Override
			void execute2(RtcParam e) {
				e.setCreationDate("2009/11/11");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setUpdateDate("2009/10/10");
			}

			@Override
			void execute2(RtcParam e) {
				e.setUpdateDate("2009/11/11");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setName("Component1");
			}

			@Override
			void execute2(RtcParam e) {
				e.setName("Component2");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setCategory("Category1");
			}

			@Override
			void execute2(RtcParam e) {
				e.setCategory("Category2");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setDescription("�����P");
			}

			@Override
			void execute2(RtcParam e) {
				e.setDescription("�����Q");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setVersion("1.0.0");
			}

			@Override
			void execute2(RtcParam e) {
				e.setVersion("1.0.1");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setVender("�x���_�P");
			}

			@Override
			void execute2(RtcParam e) {
				e.setVender("�x���_�Q");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setComponentType("TYPE1");
			}

			@Override
			void execute2(RtcParam e) {
				e.setComponentType("TYPE2");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setActivityType("ACTIVITY1");
			}

			@Override
			void execute2(RtcParam e) {
				e.setActivityType("ACTIVITY2");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setComponentKind("KIND1");
			}

			@Override
			void execute2(RtcParam e) {
				e.setComponentKind("KIND1,KIND2");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setMaxInstance(1);
			}

			@Override
			void execute2(RtcParam e) {
				e.setMaxInstance(2);
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setExecutionType("EXEC1");
			}

			@Override
			void execute2(RtcParam e) {
				e.setExecutionType("EXEC2");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setExecutionRate(0.5);
			}

			@Override
			void execute2(RtcParam e) {
				e.setExecutionRate(1.0);
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setRtcType("RTC1");
			}

			@Override
			void execute2(RtcParam e) {
				e.setRtcType("RTC2");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setCurrentVersionUpLog("VERSION1");
			}

			@Override
			void execute2(RtcParam e) {
				e.setCurrentVersionUpLog("VERSION2");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setOutputProject("PROJ1");
			}

			@Override
			void execute2(RtcParam e) {
				e.setOutputProject("PROJ2");
			}
		});
	}

	public void testIsUpdatedLang() throws Exception {
		//
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		rtcParam.setLanguage("java,python");
		assertTrue(rtcParam.isUpdated());
		//
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		rtcParam.setLanguageArg("Java,Python");
		assertTrue(rtcParam.isUpdated());
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setArchitecture("FreeBSD");
			}

			@Override
			void execute2(RtcParam e) {
				e.setArchitecture("Linux");
			}
		});
	}

	public void testIsUpdatedDocument() throws Exception {
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setDocDescription("�R���|�[�l���g�T�v�P");
			}

			@Override
			void execute2(RtcParam e) {
				e.setDocDescription("�R���|�[�l���g�T�v�Q");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setDocInOut("�R���|�[�l���g���o�͂P");
			}

			@Override
			void execute2(RtcParam e) {
				e.setDocInOut("�R���|�[�l���g���o�͂Q");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setDocAlgorithm("�R���|�[�l���g�A���S���Y���P");
			}

			@Override
			void execute2(RtcParam e) {
				e.setDocAlgorithm("�R���|�[�l���g�A���S���Y���Q");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setDocCreator("�쐬�҂P");
			}

			@Override
			void execute2(RtcParam e) {
				e.setDocCreator("�쐬�҂Q");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setDocLicense("���C�Z���X�P");
			}

			@Override
			void execute2(RtcParam e) {
				e.setDocLicense("���C�Z���X�Q");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setDocReference("�Q�l�����P");
			}

			@Override
			void execute2(RtcParam e) {
				e.setDocReference("�Q�l�����Q");
			}
		});
	}

	public void testIsUpdatedAction() throws Exception {
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setActionImplemented(0, "TRUE");
			}

			@Override
			void execute2(RtcParam e) {
				e.setActionImplemented(0, "FALSE");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setActionImplemented(1, true);
			}

			@Override
			void execute2(RtcParam e) {
				e.setActionImplemented(1, false);
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setDocActionOverView(2, "�A�N�V�����T�v�P");
			}

			@Override
			void execute2(RtcParam e) {
				e.setDocActionOverView(2, "�A�N�V�����T�v�Q");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setDocActionPreCondition(2, "�A�N�V�������O�����P");
			}

			@Override
			void execute2(RtcParam e) {
				e.setDocActionPreCondition(2, "�A�N�V�������O�����Q");
			}
		});
		//
		assertUpdated(rtcParam, new UpdateChecker() {
			@Override
			void execute1(RtcParam e) {
				e.setDocActionPostCondition(2, "�A�N�V������������P");
			}

			@Override
			void execute2(RtcParam e) {
				e.setDocActionPostCondition(2, "�A�N�V������������Q");
			}
		});
	}

	public void testIsUpdatedInDataPort() throws Exception {
		//
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		DataPortParam dp = new DataPortParam("in1", "TimedLong", "in1Port", 0);
		rtcParam.getInports().add(dp);
		assertTrue(rtcParam.isUpdated());
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		dp.setDocUnit("�P�ʂP");
		assertTrue(rtcParam.isUpdated());
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		rtcParam.getInports().remove(0);
		assertTrue(rtcParam.isUpdated());
	}

	public void testIsUpdatedOutDataPort() throws Exception {
		//
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		DataPortParam dp = new DataPortParam("out1", "TimedLong", "out1Port", 1);
		rtcParam.getOutports().add(dp);
		assertTrue(rtcParam.isUpdated());
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		dp.setDocDescription("�T�v�P");
		assertTrue(rtcParam.isUpdated());
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		rtcParam.getOutports().remove(0);
		assertTrue(rtcParam.isUpdated());
	}

	public void testIsUpdatedServicePort() throws Exception {
		//
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		ServicePortParam sp = new ServicePortParam("srv1", 0);
		rtcParam.getServicePorts().add(sp);
		assertTrue(rtcParam.isUpdated());
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		sp.setDocDescription("�T�v�P");
		assertTrue(rtcParam.isUpdated());
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		rtcParam.getServicePorts().remove(0);
		assertTrue(rtcParam.isUpdated());
	}

	public void testIsUpdatedConfigSet() throws Exception {
		//
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		ConfigSetParam csp = new ConfigSetParam("name1", "type1", "0");
		rtcParam.getConfigParams().add(csp);
		assertTrue(rtcParam.isUpdated());
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		csp.setDocConstraint("0�ȏ�̐���");
		assertTrue(rtcParam.isUpdated());
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		rtcParam.getConfigParams().remove(0);
		assertTrue(rtcParam.isUpdated());
	}

	public void testIsUpdatedConfigParameter() throws Exception {
		//
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		ConfigParameterParam cpp = new ConfigParameterParam("name1", "value1");
		rtcParam.getConfigParameterParams().add(cpp);
		assertTrue(rtcParam.isUpdated());
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		cpp.setDefaultVal("value2");
		assertTrue(rtcParam.isUpdated());
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		rtcParam.getConfigParameterParams().remove(0);
		assertTrue(rtcParam.isUpdated());
	}

	public void testIsUpdatedTargetEnv() throws Exception {
		//
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		TargetEnvParam ep = new TargetEnvParam("1.0", "Windows XP", "", "");
		rtcParam.getTargetEnvs().add(ep);
		assertTrue(rtcParam.isUpdated());
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		ep.setCpuOther("���̑�CPU");
		assertTrue(rtcParam.isUpdated());
		rtcParam.resetUpdated();
		assertFalse(rtcParam.isUpdated());
		//
		rtcParam.getTargetEnvs().remove(0);
		assertTrue(rtcParam.isUpdated());
	}

}
