package jp.go.aist.rtm.rtcbuilder;

import jp.go.aist.rtm.rtcbuilder.Generator.MergeHandler;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.parser.MergeBlockParser;
import jp.go.aist.rtm.rtcbuilder.manager.GenerateManager;
import jp.go.aist.rtm.rtcbuilder.ui.compare.CompareResultDialog;
import jp.go.aist.rtm.rtcbuilder.ui.compare.CompareTarget;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * GUI��RtcBuilder�����s����ۂ̃��C���ƂȂ�N���X
 */
public class GuiRtcBuilder {

	Generator generator = new Generator();
	/**
	 * �W�F�l���[�g�E�}�l�[�W����ǉ�����
	 * 
	 * @param genManager�@�����Ώۂ̃W�F�l���[�g�E�}�l�[�W��
	 */
	public void addGenerateManager(GenerateManager genManager) {
		generator.addGenerateManager(genManager);
	}
	/**
	 * �W�F�l���[�g�E�}�l�[�W�����N���A����
	 */
	public void clearGenerateManager() {
		generator.clearGenerateManager();
	}
	/**
	 * �W�F�l���[�g���s���A�t�@�C���o�͂��s��
	 * 
	 * @param generatorParam
	 *            �p�����[�^
	 */
	public boolean doGenerateWrite(GeneratorParam generatorParam) {
		return this.doGenerateWrite(generatorParam, true);
	}
	/**
	 * �W�F�l���[�g���s���A�t�@�C���o�͂��s��
	 * 
	 * @param generatorParam   �p�����[�^
	 * @param isShowDialog     �������Ƀ_�C�A���O��\�����邩
	 */
	public boolean doGenerateWrite(GeneratorParam generatorParam, boolean isShowDialog) {

		try {
			generator.doGenerateWrite(generatorParam, new MergeHandler() {
				public int getSelectedProcess(GeneratedResult generatedResult,
						String originalFileContents) {
					return compareByDialog(generatedResult,
							originalFileContents);
				}
			});

			if( isShowDialog ) {
				MessageDialog.openInformation(PlatformUI.getWorkbench()
						.getDisplay().getActiveShell(), "Information",
						"Generate success.");
			}
			return true;
		} catch (Exception e) {
			MessageDialog.openError(PlatformUI.getWorkbench().getDisplay()
					.getActiveShell(), "Error", e.getMessage());
			return false;
		}
	}

	private int compareByDialog(GeneratedResult generatedResult,
			String originalFileContents) {
		CompareTarget target = new CompareTarget();
		target.setTargetName(generatedResult.getName());
		target.setOriginalSrc(originalFileContents);
		target.setGenerateSrc(generatedResult.getCode());
		target.setCanMerge(MergeBlockParser.parse(generatedResult.getCode())
				.equals(MergeBlockParser.parse(originalFileContents)) == false);

		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		Shell shell = window.getShell();

		return new CompareResultDialog(shell, target).open();
	}

}
