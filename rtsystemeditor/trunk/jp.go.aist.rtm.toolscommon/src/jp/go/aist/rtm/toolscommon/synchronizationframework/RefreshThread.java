package jp.go.aist.rtm.toolscommon.synchronizationframework;

/**
 * ����I�ɃT�[�o�̏������[�J���ɔ��f������N���X
 *
 */
public abstract class RefreshThread extends Thread {
	private long milliSecond;
	private long lastExceutedTime;
	
	public RefreshThread(long milliSecond) {
		this.milliSecond = milliSecond;
	}
	
	/**
	 * �w�肵���Ԋu���ƂɎ��s����铯���R�}���h
	 */
	abstract protected void executeCommand();

	/**
	 * @param milliSecond	�T�[�o����������W����Ԋu
	 */
	public void setSynchronizeInterval(long milliSecond) {
		if (this.milliSecond == milliSecond) return;
		this.milliSecond = milliSecond;
		synchronized (this) {
			notify();
		}
	}

	@Override
	public void run() {
		while (milliSecond >= 0) {
			sleepInterval();
			execute();
		}
	}

	private void execute() {
		waitIfNotSync();
		lastExceutedTime = System.currentTimeMillis();
//		System.out.println(lastExceutedTime);
		executeCommand();
//		System.out.println(System.currentTimeMillis() - lastExceutedTime);
	}

	private void sleepInterval() {
		waitIfNotSync();
		if (milliSecond > 0) {
			long interval = System.currentTimeMillis() - lastExceutedTime;
			if (interval <  milliSecond) {
				try {
					Thread.sleep(milliSecond - interval);
				} catch (InterruptedException e) {
					// NOOP
				}
			}
		}
	}

	private void waitIfNotSync() {
		if (milliSecond == 0) {
			try {
				synchronized (this) {
					wait();
				}
			} catch (InterruptedException e) {
				// NOOP
			}
		}
	}

	/**
	 * @return	�����X���b�h�����s���ł��邩�ǂ���
	 */
	public boolean isRunning() {
		return milliSecond > 0;
	}
}
