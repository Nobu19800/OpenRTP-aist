package jp.go.aist.rtm.systemeditor.ui.util;

/**
 * ���Ԃ������鏈���p�̃f�[�����X���b�h
 *
 */
public abstract class TimeoutWrappedJob extends Thread {
	private boolean done;
	private Object result;
	
	public boolean isDone() {
		return done;
	}

	public Object getResult() {
		return result;
	}

	abstract protected Object executeCommand();
	
	@Override
	public void run() {
		result = executeCommand();
		done = true;
		synchronized (this) {
			notify();
		}
	}

}
