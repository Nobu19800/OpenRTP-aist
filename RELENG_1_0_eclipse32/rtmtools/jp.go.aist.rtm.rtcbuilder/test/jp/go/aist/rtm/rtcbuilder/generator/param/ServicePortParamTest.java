package jp.go.aist.rtm.rtcbuilder.generator.param;

public class ServicePortParamTest extends ParamTestCase<ServicePortParam> {

	ServicePortParam sp;

	@Override
	protected void setUp() throws Exception {
		sp = new ServicePortParam();
	}

	public void testIsUpdatedBasic() throws Exception {
		//
		assertUpdated(sp, new UpdateChecker() {
			@Override
			void execute1(ServicePortParam e) {
				e.setName("srv1");
			}

			@Override
			void execute2(ServicePortParam e) {
				e.setName("srv2");
			}
		});
	}

	public void testIsUpdatedInterface() throws Exception {
		//
		sp.resetUpdated();
		assertFalse(sp.isUpdated());
		//
		ServicePortInterfaceParam sip = new ServicePortInterfaceParam(sp);
		sip.setName("interface1");
		sip.resetUpdated();
		sp.getServicePortInterfaces().add(sip);
		assertTrue(sp.isUpdated());
		sp.resetUpdated();
		assertFalse(sp.isUpdated());
		//
		sip.setDocPreCondition("���O�����P");
		assertTrue(sp.isUpdated());
		sp.resetUpdated();
		assertFalse(sp.isUpdated());
		//
		sp.getServicePortInterfaces().remove(0);
		assertTrue(sp.isUpdated());
	}

	public void testIsUpdatedDocument() throws Exception {
		//
		assertUpdated(sp, new UpdateChecker() {
			@Override
			void execute1(ServicePortParam e) {
				e.setDocDescription("�|�[�g�T�v�P");
			}

			@Override
			void execute2(ServicePortParam e) {
				e.setDocDescription("�|�[�g�T�v�Q");
			}
		});
		//
		assertUpdated(sp, new UpdateChecker() {
			@Override
			void execute1(ServicePortParam e) {
				e.setDocIfDescription("�C���^�[�t�F�[�X�T�v�P");
			}

			@Override
			void execute2(ServicePortParam e) {
				e.setDocIfDescription("�C���^�[�t�F�[�X�T�v�Q");
			}
		});
	}

}
