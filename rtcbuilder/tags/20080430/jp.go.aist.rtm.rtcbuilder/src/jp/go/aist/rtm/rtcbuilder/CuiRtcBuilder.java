package jp.go.aist.rtm.rtcbuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import jp.go.aist.rtm.rtcbuilder.Generator.MergeHandler;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;

/**
 * Java�R�}���h���C�����s��Main�ƂȂ�N���X
 */
public class CuiRtcBuilder {

	/**
	 * �R�}���h���C�����C��
	 * 
	 * @param args
	 *            �R�}���h���C������
	 */
	public static void main(String[] args) {
		CommandLineParser parser = new CommandLineParser();

		try {
			GeneratorParam generatorParam = parser.parse(args);

			if (generatorParam != null) { // --help�̏ꍇ�Ȃǂ͒��ɓ���Ȃ�
				Generator generator = new Generator();
				generator.doGenerateWrite(generatorParam, new MergeHandler() {
					public int getSelectedProcess(
							GeneratedResult generatedResult,
							String originalFileContents) {
						String inputString = null;
						do {
							System.out
									.print("'" + generatedResult.getName()
											+ "' already exists. Overwrite or merge? (y/n/m)");
							BufferedReader reader = new BufferedReader(
									new InputStreamReader(System.in));
							try {
								inputString = reader.readLine().trim();
							} catch (IOException e) {
								// void
							}
						} while (getMergeHandlerId(inputString) == null);

						return getMergeHandlerId(inputString).intValue();
					}

					/**
					 * ���͂���MergeHandler�̃v���Z�X�̂h�c���擾����
					 * ����ł��Ȃ��l�ł������ꍇ�ɂ́Anull��Ԃ�
					 * 
					 * @param inputString
					 * @return
					 */
					private Integer getMergeHandlerId(String inputString) {
						Integer result = null;
						if ("y".equals(inputString)) {
							result = new Integer(
									MergeHandler.PROCESS_GENERATE_ID);
						} else if ("n".equals(inputString)) {
							result = new Integer(
									MergeHandler.PROCESS_ORIGINAL_ID);
						} else if ("m".equals(inputString)) {
							result = new Integer(MergeHandler.PROCESS_MERGE_ID);
						}

						return result;
					}
				});

				System.out.println("Generate success.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Generate fail.");
		}
	}
	
}
