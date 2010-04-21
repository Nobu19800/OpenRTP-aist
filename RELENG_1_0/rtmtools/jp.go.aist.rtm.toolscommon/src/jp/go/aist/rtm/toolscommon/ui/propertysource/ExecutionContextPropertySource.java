package jp.go.aist.rtm.toolscommon.ui.propertysource;

import jp.go.aist.rtm.toolscommon.model.component.ContextHandler;
import jp.go.aist.rtm.toolscommon.model.component.CorbaExecutionContext;
import jp.go.aist.rtm.toolscommon.model.component.ExecutionContext;
import jp.go.aist.rtm.toolscommon.nl.Messages;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * ExecutionContext��PropertySource�N���X
 */
public class ExecutionContextPropertySource extends AbstractPropertySource {

	static final String DISP_EC_ID = "ID";

	static final String DISP_STATE = Messages.getString("ExecutionContextPropertySource.disp.state");

	static final String DISP_KIND = Messages.getString("ExecutionContextPropertySource.disp.kind");

	static final String DISP_RATE = Messages.getString("ExecutionContextPropertySource.disp.rate");

	static final String EC_ID = "ID";

	static final String STATE = "STATE";

	static final String KIND = "KIND";

	static final String RATE = "RATE";

	static final String UNKNOWN = Messages.getString("ExecutionContextPropertySource.unknown");

	static final String STATE_RUNNING_LABEL = Messages.getString("ExecutionContextPropertySource.state.running");

	static final String STATE_STOPPED_LABEL = Messages.getString("ExecutionContextPropertySource.state.stopped");

	static final String STATE_UNKNOWN_LABEL = Messages.getString("ExecutionContextPropertySource.state.unknown");

	static final String KIND_EVENT_DRIVEN_LABEL = Messages.getString("ExecutionContextPropertySource.kind.event_driven");

	static final String KIND_PERIODIC_LABEL = Messages.getString("ExecutionContextPropertySource.kind.periodic");

	static final String KIND_OTHER_LABEL = Messages.getString("ExecutionContextPropertySource.kind.other");

	private ExecutionContext delegate;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param delegate
	 *            ���f��
	 */
	public ExecutionContextPropertySource(ExecutionContext delegate) {
		this.delegate = delegate;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		IPropertyDescriptor[] result = new IPropertyDescriptor[0];
		if (this.delegate instanceof CorbaExecutionContext) {
			result = new IPropertyDescriptor[] {
					new TextPropertyDescriptor(EC_ID, DISP_EC_ID),
					new TextPropertyDescriptor(STATE, DISP_STATE),
					new TextPropertyDescriptor(KIND, DISP_KIND),
					new TextPropertyDescriptor(RATE, DISP_RATE) };
		} else {
			result = new IPropertyDescriptor[] {
					new TextPropertyDescriptor(EC_ID, DISP_EC_ID),
					new TextPropertyDescriptor(KIND, DISP_KIND),
					new TextPropertyDescriptor(RATE, DISP_RATE) };
		}
		return result;
	}

	@Override
	public java.lang.Object getPropertyValue(java.lang.Object id) {
		String result = UNKNOWN;
		try {
			if (STATE.equals(id)) {
				int value = delegate.getStateL();
				if (value == ExecutionContext.STATE_RUNNING) {
					result = STATE_RUNNING_LABEL;
				} else if (value == ExecutionContext.STATE_STOPPED) {
					result = STATE_STOPPED_LABEL;
				} else if (value == ExecutionContext.STATE_UNKNOWN) {
					result = STATE_UNKNOWN_LABEL;
				}
			} else if (KIND.equals(id)) {
				int value = delegate.getKindL();
				if (value == ExecutionContext.KIND_EVENT_DRIVEN) {
					result = KIND_EVENT_DRIVEN_LABEL;
				} else if (value == ExecutionContext.KIND_PERIODIC) {
					result = KIND_PERIODIC_LABEL;
				} else if (value == ExecutionContext.KIND_OTHER) {
					result = KIND_OTHER_LABEL;
				}
			} else if (RATE.equals(id)) {
				result = String.valueOf(delegate.getRateL());
			} else if (EC_ID.equals(id)) {
				if (getParent() instanceof ContextHandler) {
					ContextHandler ch = (ContextHandler) getParent();
					String ecid = ch.getId(delegate);
					if (ecid != null) {
						result = ecid;
					}
				}
			}
		} catch (Exception e) {
			// void
		}

		return result;
	}

}
