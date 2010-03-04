package jp.go.aist.rtm.rtcbuilder.ui.preference;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.RtcBuilderPlugin;

public class ComponentPreferenceManager {
	private static ComponentPreferenceManager __instance = new ComponentPreferenceManager();
	/**
	 * �R���X�g���N�^
	 * 
	 * @return �V���O���g��
	 */
	public static ComponentPreferenceManager getInstance() {
		return __instance;
	}
	//�R�[�h����
	/**
	 * Module Name�̃L�[
	 */
	public static final String Generate_Basic_Name = getClassName() + "GENERATE_MODULE_NAME";
	/**
	 * Description�̃L�[
	 */
	public static final String Generate_Basic_Description = getClassName() + "GENERATE_BASIC_DESCRIPTION";
	/**
	 * Version�̃L�[
	 */
	public static final String Generate_Basic_Version = getClassName() + "GENERATE_BASIC_VERSION";
	/**
	 * Vendor Name�̃L�[
	 */
	public static final String Generate_Basic_Vendor_Name = getClassName() + "GENERATE_BASIC_VENDOR_NAME";
	/**
	 * Category�̃L�[
	 */
	public static final String Generate_Basic_Category = getClassName() + "GENERATE_BASIC_CATEGORY";
	/**
	 * Component Type�̃L�[
	 */
	public static final String Generate_Basic_ComponentType = getClassName() + "GENERATE_BASIC_COMPONENT_TYPE";
	/**
	 * Activity Type�̃L�[
	 */
	public static final String Generate_Basic_ActivityType = getClassName() + "GENERATE_BASIC_ACTIVITY_TYPE";
	/**
	 * Component Kind�̃L�[
	 */
	public static final String Generate_Basic_ComponentKind = getClassName() + "GENERATE_BASIC_COMPONENT_KIND";
	/**
	 * Max Instance�̃L�[
	 */
	public static final String Generate_Basic_Max_Instance = getClassName() + "GENERATE_BASIC_MAX_INSTANCES";
	/**
	 * Execution Type�̃L�[
	 */
	public static final String Generate_Basic_ExecutionType = getClassName() + "GENERATE_BASIC_EXECUTION_TYPE";
	/**
	 * Execution Rate�̃L�[
	 */
	public static final String Generate_Basic_Execution_Rate = getClassName() + "GENERATE_BASIC_EXECUTION_RATE";
	/**
	 * ���ʐړ���̃L�[
	 */
	public static final String Generate_Basic_Prefix = getClassName() + "GENERATE_BASIC_PREFIX";
	/**
	 * ���ʐڔ���̃L�[
	 */
	public static final String Generate_Basic_Suffix = getClassName() + "GENERATE_BASIC_SUFFIX";
	////
	/**
	 * Configuration Name�̃L�[
	 */
	public static final String Generate_Configuration_Name = getClassName() + "GENERATE_CONFIGURATION_NAME";
	/**
	 * Configuration Type�̃L�[
	 */
	public static final String Generate_Configuration_Type = getClassName() + "GENERATE_CONFIGURATION_TYPE";
	/**
	 * Configuration Variable Name�̃L�[
	 */
	public static final String Generate_Configuration_VarName = getClassName() + "GENERATE_CONFIGURATION_VARNAME";
	/**
	 * Configuration Default Value�̃L�[
	 */
	public static final String Generate_Configuration_Default = getClassName() + "GENERATE_CONFIGURATION_DEFAULT";
	/**
	 * Configuration Constraint�̃L�[
	 */
	public static final String Generate_Configuration_Constraint = getClassName() + "GENERATE_CONFIGURATION_CONSTRAINT";
	/**
	 * Configuration Unit�̃L�[
	 */
	public static final String Generate_Configuration_Unit = getClassName() + "GENERATE_CONFIGURATION_UNIT";
	/**
	 * Configuration �ړ���̃L�[
	 */
	public static final String Generate_Configuration_Prefix = getClassName() + "GENERATE_CONFIGURATION_PREFIX";
	/**
	 * Configuration �ړ����̃L�[
	 */
	public static final String Generate_Configuration_Suffix = getClassName() + "GENERATE_CONFIGURATION_SUFFIX";
	//////////
	/**
	 * DataPort Name�̃L�[
	 */
	public static final String Generate_DataPort_Name = getClassName() + "GENERATE_DATAPORT_NAME";
	/**
	 * DataPort Type�̃L�[
	 */
	public static final String Generate_DataPort_Type = getClassName() + "GENERATE_DATAPORT_TYPE";
	/**
	 * DataPort VarName�̃L�[
	 */
	public static final String Generate_DataPort_VarName = getClassName() + "GENERATE_DATAPORT_VARNAME";
	/**
	 * DataPort �ړ���̃L�[
	 */
	public static final String Generate_DataPort_Prefix = getClassName() + "GENERATE_DATAPORT_PREFIX";
	/**
	 * DataPort �ڔ���̃L�[
	 */
	public static final String Generate_DataPort_Suffix = getClassName() + "GENERATE_DATAPORT_SUFFIX";
	////
	/**
	 * ServicePort Name�̃L�[
	 */
	public static final String Generate_ServicePort_Name = getClassName() + "GENERATE_SERVICEPORT_NAME";
	/**
	 * ServicePort �ړ���̃L�[
	 */
	public static final String Generate_ServicePort_Prefix = getClassName() + "GENERATE_SERVICEPORT_PREFIX";
	/**
	 * ServicePort �ڔ���̃L�[
	 */
	public static final String Generate_ServicePort_Suffix = getClassName() + "GENERATE_SERVICEPORT_SUFFIX";
	/**
	 * ServiceInterfacet Name�̃L�[
	 */
	public static final String Generate_ServiceIF_Name = getClassName() + "GENERATE_SERVICEIF_NAME";
	/**
	 * ServiceInterfacet Instance Name�̃L�[
	 */
	public static final String Generate_ServiceIF_InstanceName = getClassName() + "GENERATE_SERVICEIF_INSTANCENAME";
	/**
	 * ServiceInterfacet Varriable Name�̃L�[
	 */
	public static final String Generate_ServiceIF_VarName = getClassName() + "GENERATE_SERVICEIF_VARNAME";
	/**
	 * ServiceInterfacet Instance �ړ���̃L�[
	 */
	public static final String Generate_ServiceIF_Prefix = getClassName() + "GENERATE_SERVICEIF_PREFIX";
	/**
	 * ServiceInterfacet Instance �ڔ���̃L�[
	 */
	public static final String Generate_ServiceIF_Suffix = getClassName() + "GENERATE_SERVICEIF_SUFFIX";
	
	//�f�t�H���g�l���󔒂���ύX����ꍇ�ɂ́Cgetter�̒�`���K�v
	public static final String DEFAULT_COMPONENT_NAME = "ModuleName";
	public static final String DEFAULT_DESCRIPTION = "ModuleDescription";
	public static final String DEFAULT_CATEGORY = "Category";
	public static final String DEFAULT_VERSION = "1.0.0";
	public static final String DEFAULT_VENDER = "VenderName";
	public static final String DEFAULT_COMPONENT_TYPE = IRtcBuilderConstants.COMPONENT_TYPE_ITEMS[0];
	public static final String DEFAULT_ACTIVITY_TYPE = IRtcBuilderConstants.ACTIVITY_TYPE_ITEMS[0];
	public static final String DEFAULT_COMPONENT_KIND = IRtcBuilderConstants.COMPONENT_KIND_ITEMS[0];
	public static final int DEFAULT_MAXINST = 1;
	public static final String DEFAULT_EXECUTION_TYPE = IRtcBuilderConstants.EXECUTIONCONTEXT_TYPE_ITEMS[0];
	public static final double DEFAULT_EXECUTION_RATE = 1.0;
	public static final String DEFAULT_PREFIX = "m_";
	public static final String DEFAULT_SUFFIX = "";
	//
	public static final String DEFAULT_CONFIGURATION_NAME = "conf_name";
	public static final String DEFAULT_CONFIGURATION_TYPE = "";
	public static final String DEFAULT_CONFIGURATION_VARNAME = "";
	public static final String DEFAULT_CONFIGURATION_DEFAULT = "";
	public static final String DEFAULT_CONFIGURATION_CONSTRAINT = "";
	public static final String DEFAULT_CONFIGURATION_UNIT = "";
	public static final String DEFAULT_CONFIGURATION_PREFIX = "";
	public static final String DEFAULT_CONFIGURATION_SUFFIX = "";
	//////////
	public static final String DEFAULT_DATAPORT_NAME = "dp_name";
	public static final String DEFAULT_DATAPORT_TYPE = "";
	public static final String DEFAULT_DATAPORT_VARNAME = "";
	public static final String DEFAULT_DATAPORT_CONSTRAINT = "";
	public static final String DEFAULT_DATAPORT_UNIT = "";
	public static final String DEFAULT_DATAPORT_PREFIX = "";
	public static final String DEFAULT_DATAPORT_SUFFIX = "";
	//
	public static final String DEFAULT_SERVICEPORT_NAME = "sv_name";
	public static final String DEFAULT_SERVICEPORT_PREFIX = "";
	public static final String DEFAULT_SERVICEPORT_SUFFIX = "";
	//
	public static final String DEFAULT_SERVICEIF_NAME = "if_name";
	public static final String DEFAULT_SERVICEIF_INSTANCENAME = "";
	public static final String DEFAULT_SERVICEIF_VARNAME = "";
	public static final String DEFAULT_SERVICEIF_PREFIX = "";
	public static final String DEFAULT_SERVICEIF_SUFFIX = "";
	
	/**
	 * �R�[�h�������� ModuleName �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return Module Name �f�t�H���g�l
	 */
	public String getBasic_ComponentName() {
		return getStringStoreValue(Generate_Basic_Name, DEFAULT_COMPONENT_NAME);
	}

	/**
	 * �R�[�h�������� ModuleDescription �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return Module Description �f�t�H���g�l
	 */
	public String getBasic_Description() {
		return getStringStoreValue(Generate_Basic_Description, DEFAULT_DESCRIPTION);
	}
	
	/**
	 * �R�[�h�������� Module Category �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return Module Category �f�t�H���g�l
	 */
	public String getBasic_Category() {
		return getStringStoreValue(Generate_Basic_Category, DEFAULT_CATEGORY);
	}
	
	/**
	 * �R�[�h�������� Module Version �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return Module Version �f�t�H���g�l
	 */
	public String getBasic_Version() {
		return getStringStoreValue(Generate_Basic_Version, DEFAULT_VERSION);
	}

	/**
	 * �R�[�h�������� Module Component Type �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return Module Component Type �f�t�H���g�l
	 */
	public String getBasic_ComponentType() {
		return getStringStoreValue(Generate_Basic_ComponentType, Generate_Basic_ComponentType);
	}

	/**
	 * �R�[�h�������� Module Activity Type �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return Module Activity Type �f�t�H���g�l
	 */
	public String getBasic_ActivityType() {
		return getStringStoreValue(Generate_Basic_ActivityType, DEFAULT_ACTIVITY_TYPE);
	}

	/**
	 * �R�[�h�������� Module Component Kind �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return Module Component Kind �f�t�H���g�l
	 */
	public String getBasic_ComponentKind() {
		return getStringStoreValue(Generate_Basic_ComponentKind, DEFAULT_COMPONENT_KIND);
	}

	/**
	 * �R�[�h�������� Module Vendor Name �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return Module Vendor Name �f�t�H���g�l
	 */
	public String getBasic_VendorName() {
		return getStringStoreValue(Generate_Basic_Vendor_Name, DEFAULT_VENDER);
	}
	
	/**
	 * �R�[�h�������� Module Max Instances �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return Module Max Instances �f�t�H���g�l
	 */
	public int getBasic_MaxInstances() {
		return getIntegaerStoreValue(Generate_Basic_Max_Instance, DEFAULT_MAXINST);
	}

	/**
	 * �R�[�h�������� Module Execution Type �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return Module Execution Type �f�t�H���g�l
	 */
	public String getBasic_ExecutionType() {
		return getStringStoreValue(Generate_Basic_ExecutionType, DEFAULT_EXECUTION_TYPE);
	}

	/**
	 * �R�[�h�������� Execution Rate �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return Execution Rate �f�t�H���g�l
	 */
	public double getBasic_ExecutionRate() {
		return getDoubleStoreValue(Generate_Basic_Execution_Rate, DEFAULT_EXECUTION_RATE);
	}
	
	/**
	 * �R�[�h�������� �ړ��� �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return �ړ��� �f�t�H���g�l
	 */
	public String getBasic_Prefix() {
		return getStringStoreValue(Generate_Basic_Prefix, DEFAULT_PREFIX);
	}
	
	////
	/**
	 * �R�[�h�������� Configuration Name �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return Configuration Name �f�t�H���g�l
	 */
	public String getConfiguration_Name() {
		return getStringStoreValue(Generate_Configuration_Name, DEFAULT_CONFIGURATION_NAME);
	}
	//////////
	/**
	 * �R�[�h�������� DataPort Name �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return DataPort Name �f�t�H���g�l
	 */
	public String getDataPort_Name() {
		return getStringStoreValue(Generate_DataPort_Name, DEFAULT_DATAPORT_NAME);
	}
	/////
	/**
	 * �R�[�h�������� ServicePort �� �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return ServicePort �ϐ��� �f�t�H���g�l
	 */
	public String getServicePort_Name() {
		return getStringStoreValue(Generate_ServicePort_Name, DEFAULT_SERVICEPORT_NAME);
	}
	/////
	/**
	 * �R�[�h�������� ServiceIF �� �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return ServiceIF�� �f�t�H���g�l
	 */
	public String getServiceIF_Name() {
		return getStringStoreValue(Generate_ServiceIF_Name, DEFAULT_SERVICEIF_NAME);
	}
	/////
	private String getStringStoreValue(String key, String defaultValue) {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(key, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(key);
		String result = new String();
		if (resultTemp.equals("")) { // defaultvalue
			result = defaultValue;
		} else {
			result = resultTemp;
		}
		return result;
	}
	
	private int getIntegaerStoreValue(String key, int defaultValue) {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(key, "");

		int resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getInt(key);
		int result;
		if (resultTemp == -1) { // defaultvalue
			result = defaultValue;
		} else {
			result = resultTemp;
		}
		return result;
	}
	
	private double getDoubleStoreValue(String key, double defaultValue) {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(key, "");

		double resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getDouble(key);
		double result;
		if (resultTemp == -1) { // defaultvalue
			result = defaultValue;
		} else {
			result = resultTemp;
		}
		return result;
	}
	
	private static String getClassName() {
		return ComponentPreferenceManager.class.getName();
	}
}
