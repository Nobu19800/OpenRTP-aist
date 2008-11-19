package jp.go.aist.rtm.rtcbuilder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.IDLParser;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.ParseException;
import jp.go.aist.rtm.rtcbuilder.corba.idl.parser.syntaxtree.specification;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.IDLParamConverter;
import jp.go.aist.rtm.rtcbuilder.generator.PreProcessor;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceArgumentParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceClassParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.ServiceMethodParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.idl.TypeDefParam;
import jp.go.aist.rtm.rtcbuilder.generator.parser.MergeBlockParser;
import jp.go.aist.rtm.rtcbuilder.manager.CXXGenerateManager;
import jp.go.aist.rtm.rtcbuilder.manager.CXXWinGenerateManager;
import jp.go.aist.rtm.rtcbuilder.manager.CommonGenerateManager;
import jp.go.aist.rtm.rtcbuilder.manager.GenerateManager;
import jp.go.aist.rtm.rtcbuilder.ui.Perspective.LanguageProperty;
import jp.go.aist.rtm.rtcbuilder.util.FileUtil;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.IDialogConstants;

/**
 * �W�F�l���[�^�N���X
 */
public class Generator {
	
	private HashMap<String, GenerateManager> generateManagerList = new HashMap<String, GenerateManager>();

	public Generator() {
		this.addGenerateManager(new CommonGenerateManager());
		this.addGenerateManager(new CXXGenerateManager());
		this.addGenerateManager(new CXXWinGenerateManager());
	}
	
	/**
	 * �W�F�l���[�g�E�}�l�[�W����ǉ�����
	 * 
	 * @param genManager�@�����Ώۂ̃W�F�l���[�g�E�}�l�[�W��
	 */
	public void addGenerateManager(GenerateManager genManager) {
		generateManagerList.put(genManager.getManagerKey(), genManager);
	}
	/**
	 * �W�F�l���[�g�E�}�l�[�W�����N���A����
	 */
	public void clearGenerateManager() {
		generateManagerList = new HashMap<String, GenerateManager>();
	}
	public List<GeneratedResult> generateTemplateCode(GeneratorParam generatorParam)	throws Exception {
		return generateTemplateCode(generatorParam, true);
	}
	/**
	 * �W�F�l���[�g����
	 * 
	 * @param generatorParam
	 *            �p�����[�^
	 * @return GeneratedResult�̃��X�g
	 * @throws ParseException
	 *             IDL�̃p�[�X�Ɏ��s�����ꍇ�Ȃ�
	 */
	public List<GeneratedResult> generateTemplateCode(GeneratorParam generatorParam, boolean validateFlag)	 
					throws Exception {

		if( validateFlag ) {
			for( RtcParam rtcParam : generatorParam.getRtcParams() ) {
				validate(rtcParam);
			}
		}

		List<ServiceClassParam> rtcServiceClasses = new ArrayList<ServiceClassParam>();
		//IDL�d���`�F�b�N�p
		List<String> IDLPathes = new ArrayList<String>();
		//IDL�ǂݍ��ݗp
		List<ServiceClassParam> IDLPathParams = new ArrayList<ServiceClassParam>();
		List<GeneratedResult> result = new ArrayList<GeneratedResult>();
		for( RtcParam rtcParam : generatorParam.getRtcParams() ) {
			rtcParam.checkAndSetParameter();
			for( ServicePortParam serviceport : rtcParam.getServicePorts() ) {
				for( ServicePortInterfaceParam serviceInterfaces : serviceport.getServicePortInterfaces() ) {
					if( !IDLPathes.contains(serviceInterfaces.getIdlFullPath()) )
						IDLPathes.add(serviceInterfaces.getIdlFullPath());
						IDLPathParams.add(new ServiceClassParam(serviceInterfaces.getIdlFullPath(),
																 serviceInterfaces.getIdlSearchPath()));
				}
			}
			rtcServiceClasses.addAll(getRtcServiceClass(rtcParam, IDLPathParams));
			checkReferencedServiceParam(rtcServiceClasses, rtcParam);
			List<ServiceClassParam> serviceClassParamList = new ArrayList<ServiceClassParam>();
			List<String> serviceClassNameList = new ArrayList<String>();
			for( ServiceClassParam serviceClassParam : rtcServiceClasses ) {
				if( !serviceClassNameList.contains(serviceClassParam.getName()) ) {
					serviceClassNameList.add(serviceClassParam.getName());
					serviceClassParamList.add(serviceClassParam);
				}
			}
	
			rtcParam.getServiceClassParams().clear();
	
			for( ServiceClassParam param : serviceClassParamList ) {
				param.setParent(rtcParam);
				rtcParam.getServiceClassParams().add(param);
			}
			List<GeneratedResult> resultEach = new ArrayList<GeneratedResult>();
			for( GenerateManager manager : generateManagerList.values() ) {
				resultEach.addAll(manager.generateTemplateCode(rtcParam));
			}
			result.addAll(resultEach);
		}	

		return result;
	}

	/**
	 * �o���f�[�g���s��
	 * 
	 * @param generatorParam
	 */
	private void validate(RtcParam rtcParam) {

		if( rtcParam.getOutputProject() == null ) {
			throw new RuntimeException("OutputProject���w�肳��Ă��܂���B");
		}
		if( rtcParam.getName() == null ) {
			throw new RuntimeException("Component Name���w�肳��Ă��܂���B");
		}

		List<String> portNames = new ArrayList<String>();
		for( DataPortParam inport : rtcParam.getInports() ) {
			if (portNames.contains(inport.getName()))
				throw new RuntimeException("Port�ɓ������O�����݂��܂��B :" + rtcParam.getName());
			portNames.add(inport.getName());
		}
		for (DataPortParam outport : rtcParam.getOutports()) {
			if( portNames.contains(outport.getName()) )
				throw new RuntimeException("Port�ɓ������O�����݂��܂��B :" + rtcParam.getName());
			portNames.add(outport.getName());
		}

		List<String> servicePortNames = new ArrayList<String>();
		for( ServicePortParam servicePort : rtcParam.getServicePorts() ) {
			if( servicePortNames.contains(servicePort.getName()) )
				throw new RuntimeException(
						"Provider��������Consumer�ɓ������O�����݂��܂��B :" + rtcParam.getName());
			servicePortNames.add(servicePort.getName());
		}

		List<String> serviceInterfaceNames = new ArrayList<String>();
		for( ServicePortParam servicePort : rtcParam.getServicePorts() ) {
			for( ServicePortInterfaceParam serviceInterface : servicePort.getServicePortInterfaces() ) {
				if (serviceInterfaceNames.contains(serviceInterface.getName()))
					throw new RuntimeException(
							"Provider��������Consumer�ɓ���PortName�����݂��܂��B :" + rtcParam.getName());
				serviceInterfaceNames.add(serviceInterface.getName());
			}
		}
	}

	/**
	 * �Q�Ƃ���Ă���Service�����݂��邩�m�F����
	 * 
	 * @param rtcServiceClasses
	 * @param generatorParam
	 * @return
	 */
	private void checkReferencedServiceParam(List<ServiceClassParam> rtcServiceClasses, RtcParam param) {

		List<String> serviceTypes = new ArrayList<String>();
		for( ServicePortParam serviceport : param.getServicePorts() ) {
			for( ServicePortInterfaceParam serviceInterfaces : serviceport.getServicePortInterfaces() ) {
				if( !serviceTypes.contains(serviceInterfaces.getInterfaceType()) )
					serviceTypes.add(serviceInterfaces.getInterfaceType());
			}
		}

		for( String serviceType : serviceTypes ) {
			ServiceClassParam find = null;
			for( ServiceClassParam serviceClassParam : rtcServiceClasses ) {
				if( serviceType.equals(serviceClassParam.getName()) ) {
					find = serviceClassParam;
					break;
				}
			}
			if( find == null )
				throw new RuntimeException("'" + serviceType + "' is not found in IDL");
		}
	}

	/**
	 * �T�[�r�X�N���X,�^��`���擾����
	 * 
	 * @param generatorParam
	 * @param IDLPathes
	 * @return
	 * @throws ParseException
	 */
	private List<ServiceClassParam> getRtcServiceClass(
			RtcParam rtcParam, List<ServiceClassParam> IDLPathes) throws ParseException {
		List<ServiceClassParam> result = new ArrayList<ServiceClassParam>();

		for(int intIdx=0; intIdx<IDLPathes.size(); intIdx++ ) {
			if(IDLPathes.get(intIdx) != null ) {
				String idlContent = FileUtil.readFile(IDLPathes.get(intIdx).getName());
				if (idlContent != null) {
//					String idl = PreProcessor.parse(idlContent, rtcParam.getIncludeIDLDic());
					String idl = PreProcessor.parse(idlContent, getIncludeIDLDic(IDLPathes.get(intIdx).getIdlPath()));
					IDLParser parser = new IDLParser(new StringReader(idl));
	
					specification spec = parser.specification();
	
					List<ServiceClassParam> serviceClassParams = IDLParamConverter
							.convert(spec, IDLPathes.get(intIdx).getName());
//					Map<String,String> typedefParams = IDLParamConverter
//						.convert_typedef(spec, IDLPathes.get(intIdx).getName());
					List<TypeDefParam> typedefParams = IDLParamConverter
							.convert_typedef(spec, IDLPathes.get(intIdx).getName());
					if( typedefParams.size() > 0 ) {
						serviceClassParams = convertType(serviceClassParams, typedefParams);
					}
					for(ServiceClassParam scp : serviceClassParams) {
						scp.setTypeDef(typedefParams);
					}
					result.addAll(serviceClassParams);
				}
			}
		}

		return result;
	}
	
	private List<ServiceClassParam> convertType(List<ServiceClassParam> source, List<TypeDefParam> types) {
		for( ServiceClassParam target : source) {
			for( ServiceMethodParam method : target.getMethods() ) {
				method.setType(checkType(method.getType(), types));
				for( ServiceArgumentParam param : method.getArguments() ) {
					param.setType(checkType(param.getType(), types));
				}
			}
		}
		return source;
	}
	private String checkType(String target, List<TypeDefParam> types) {
		for(TypeDefParam tdparam : types) {
			if(target.equals(tdparam.getTargetDef())) {
				if(!tdparam.getScopedName().equals("")) {
					return tdparam.getScopedName() + "::" + target;
				}
			}
		}
		return target;
	}

	private File getIncludeIDLDic(String targetDir) {
		File result = null;
		if( targetDir!=null && targetDir.length()>0 ) {
			File file = new File(targetDir);
			if (file.exists()) {
				result = file;
			} else {
				throw new RuntimeException("Include IDL �̃f�B���N�g����������܂���B");
			}
		}
		return result;
	}

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"yyyyMMddHHmmss");

	private void writeFile(List<GeneratedResult> generatedResultList,
			RtcParam rtcParam, MergeHandler handler) throws IOException {

		IWorkspaceRoot workspaceHandle = ResourcesPlugin.getWorkspace().getRoot();
		IProject project = workspaceHandle.getProject(rtcParam.getOutputProject());
		if(!project.exists()) {
			try {
				project.create(null);
				project.open(null);
				LanguageProperty langProp = LanguageProperty.checkPlugin(rtcParam);
				if(langProp != null) {
					IProjectDescription description = project.getDescription();
					String[] ids = description.getNatureIds();
					String[] newIds = new String[ids.length + langProp.getNatures().size()];
					System.arraycopy(ids, 0, newIds, 0, ids.length);
					for( int intIdx=0; intIdx<langProp.getNatures().size(); intIdx++ ) {
						newIds[ids.length+intIdx] = langProp.getNatures().get(intIdx);
					}
					description.setNatureIds(newIds);
					project.setDescription(description, null);
				}
			} catch (CoreException e) {
				throw new RuntimeException("�v���W�F�N�g�̐����Ɏ��s���܂���");
			}
		}
		
		for (GeneratedResult generatedResult : generatedResultList) {
			if (generatedResult.getName().equals("") == false) {
				writeFile(generatedResult, project, handler);
			}
		}
	}

	private void writeFile(GeneratedResult generatedResult, IProject outputProject,
			MergeHandler handler) throws IOException {
		
		File targetFile = new File(outputProject.getLocation().toOSString(), generatedResult.getName());

		boolean isOutput = false;
		if (targetFile.exists()) {
			String originalFileContents = FileUtil.readFile(targetFile.getAbsolutePath());
			if (originalFileContents.equals(generatedResult.getCode()) == false) {
				int selectedProcess = handler.getSelectedProcess(generatedResult, originalFileContents);
				if (selectedProcess != MergeHandler.PROCESS_ORIGINAL_ID
						&& selectedProcess != IDialogConstants.CANCEL_ID) {
					IResource originalFile = outputProject.findMember(generatedResult.getName());
					IFile renameFile = outputProject.getFile(generatedResult.getName() + DATE_FORMAT.format(new GregorianCalendar().getTime()) );
					try {
						originalFile.move(renameFile.getFullPath(), true, null);

						if (selectedProcess == MergeHandler.PROCESS_MERGE_ID) {
							generatedResult.setCode(MergeBlockParser.merge(
									originalFileContents, MergeBlockParser
											.parse(generatedResult.getCode())));
						}
	
						isOutput = true;
					} catch (CoreException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			isOutput = true;
		}

		if (isOutput) {
			IFile outputFile = outputProject.getFile(generatedResult.getName());
			try {
				outputFile.create(new ByteArrayInputStream(generatedResult.getCode().getBytes("UTF-8")), false, null);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * �W�F�l���[�g���A�t�@�C���o�͂��s��
	 * 
	 * @param generatorParam
	 *            �p�����[�^
	 * @param handler
	 *            MergeHandler
	 * @throws ParseException
	 * @throws IOException
	 */
	public void doGenerateWrite(GeneratorParam generatorParam,
			MergeHandler handler) throws Exception {
		
		for( RtcParam rtcParam : generatorParam.getRtcParams() ) {
			List<GeneratedResult> generatedResult = generateTemplateCode(generatorParam);
			writeFile(generatedResult, rtcParam, handler);
		}
	}

	/**
	 * �}�[�W�n���h��
	 */
	public interface MergeHandler {
		/**
		 * �v���Z�X�F�I���W�i�����c��
		 */
		public static final int PROCESS_ORIGINAL_ID = 10;

		/**
		 * �v���Z�X�F�V���������������̂𗘗p����
		 */
		public static final int PROCESS_GENERATE_ID = 20;

		/**
		 * �v���Z�X�F�}�[�W���s��
		 */
		public static final int PROCESS_MERGE_ID = 30;

		/**
		 * �v���Z�X��I������
		 * 
		 * @param generatedResult
		 *            ��������
		 * @param originalFileContents
		 *            �����t�@�C���̓��e
		 * @return
		 */
		public int getSelectedProcess(GeneratedResult generatedResult,
				String originalFileContents);
	}
}
