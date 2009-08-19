package jp.go.aist.rtm.systemeditor.ui.util;

import jp.go.aist.rtm.systemeditor.nl.Messages;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

/**
 * ���Ԃ̂����鏈����ʃX���b�h�ōs���A�w�肳�ꂽ���ԓ��ɏI���Ȃ���΁A�G���[���b�Z�[�W��\������N���X
 *
 */
public class TimeoutWrapper {
	private long milliSecond;
	private TimeoutWrappedJob job;

	public TimeoutWrapper(long milliSecond) {
		this.milliSecond = milliSecond;
	}
	
	public void setJob(TimeoutWrappedJob job) {
		this.job = job;
	}
	
	public Object start() {
		job.setDaemon(true);
		job.start();
		synchronized (job) {
			try {
				job.wait(milliSecond);
			} catch (InterruptedException e) {
			}
		}
		if (job.isDone()) return job.getResult();
		MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell()
				, Messages.getString("TimeoutWrapper.0"), //$NON-NLS-1$
				Messages.getString("TimeoutWrapper.1"));
		return null;
	}
}
