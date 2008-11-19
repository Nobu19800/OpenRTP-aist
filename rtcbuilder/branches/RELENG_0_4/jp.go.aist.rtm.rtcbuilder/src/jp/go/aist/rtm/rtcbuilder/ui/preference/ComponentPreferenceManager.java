package jp.go.aist.rtm.rtcbuilder.ui.preference;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder.RtcBuilderPlugin;

public class ComponentPreferenceManager {
	private static ComponentPreferenceManager __instance = new ComponentPreferenceManager();
	protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

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
	private static final String Generate_Basic_Name = ComponentPreferenceManager.class.getName()
			+ "GENERATE_MODULE_NAME";
	/**
	 * Description�̃L�[
	 */
	private static final String Generate_Basic_Description = ComponentPreferenceManager.class.getName()
			+ "GENERATE_BASIC_DESCRIPTION";
	/**
	 * Version�̃L�[
	 */
	private static final String Generate_Basic_Version = ComponentPreferenceManager.class.getName()
			+ "GENERATE_BASIC_VERSION";
	/**
	 * Vendor Name�̃L�[
	 */
	private static final String Generate_Basic_Vendor_Name = ComponentPreferenceManager.class.getName()
			+ "GENERATE_BASIC_VENDOR_NAME";
	/**
	 * Category�̃L�[
	 */
	private static final String Generate_Basic_Category = ComponentPreferenceManager.class.getName()
			+ "GENERATE_BASIC_CATEGORY";
	/**
	 * Component Type�̃L�[
	 */
	private static final String Generate_Basic_ComponentType = ComponentPreferenceManager.class.getName()
			+ "GENERATE_BASIC_COMPONENT_TYPE";
	/**
	 * Activity Type�̃L�[
	 */
	private static final String Generate_Basic_ActivityType = ComponentPreferenceManager.class.getName()
			+ "GENERATE_BASIC_ACTIVITY_TYPE";
	/**
	 * Component Kind�̃L�[
	 */
	private static final String Generate_Basic_ComponentKind = ComponentPreferenceManager.class.getName()
			+ "GENERATE_BASIC_COMPONENT_KIND";
	/**
	 * Max Instance�̃L�[
	 */
	private static final String Generate_Basic_Max_Instance = ComponentPreferenceManager.class.getName()
			+ "GENERATE_BASIC_MAX_INSTANCES";
	/**
	 * Execution Type�̃L�[
	 */
	private static final String Generate_Basic_ExecutionType = ComponentPreferenceManager.class.getName()
			+ "GENERATE_BASIC_EXECUTION_TYPE";
	/**
	 * Execution Rate�̃L�[
	 */
	private static final String Generate_Basic_Execution_Rate = ComponentPreferenceManager.class.getName()
			+ "GENERATE_BASIC_EXECUTION_RATE";
	////
	/**
	 * DataPort Name�̃L�[
	 */
	private static final String Generate_DataPort_Name = ComponentPreferenceManager.class.getName()
			+ "GENERATE_DATAPORT_NAME";
	/**
	 * DataPort Type�̃L�[
	 */
	private static final String Generate_DataPort_Type = ComponentPreferenceManager.class.getName()
			+ "GENERATE_DATAPORT_TYPE";
	/**
	 * DataPort VarName�̃L�[
	 */
	private static final String Generate_DataPort_VarName = ComponentPreferenceManager.class.getName()
			+ "GENERATE_DATAPORT_VARNAME";
	////
	/**
	 * ServicePort Name�̃L�[
	 */
	private static final String Generate_ServicePort_Name = ComponentPreferenceManager.class.getName()
			+ "GENERATE_SERVICEPORT_NAME";
	/**
	 * ServiceInterfacet Name�̃L�[
	 */
	private static final String Generate_ServiceIF_Name = ComponentPreferenceManager.class.getName()
			+ "GENERATE_SERVICEIF_NAME";
	/**
	 * ServiceInterfacet Instance Name�̃L�[
	 */
	private static final String Generate_ServiceIF_InstanceName = ComponentPreferenceManager.class.getName()
			+ "GENERATE_SERVICEIF_INSTANCENAME";
	/**
	 * ServiceInterfacet Varriable Name�̃L�[
	 */
	private static final String Generate_ServiceIF_VarName = ComponentPreferenceManager.class.getName()
			+ "GENERATE_SERVICEIF_VARNAME";
	////
	/**
	 * Configuration Name�̃L�[
	 */
	private static final String Generate_Configuration_Name = ComponentPreferenceManager.class.getName()
			+ "GENERATE_CONFIGURATION_NAME";
	/**
	 * Configuration Type�̃L�[
	 */
	private static final String Generate_Configuration_Type = ComponentPreferenceManager.class.getName()
			+ "GENERATE_CONFIGURATION_TYPE";
	/**
	 * Configuration Variable Name�̃L�[
	 */
	private static final String Generate_Configuration_VarName = ComponentPreferenceManager.class.getName()
			+ "GENERATE_CONFIGURATION_VARNAME";
	/**
	 * Configuration Default Value�̃L�[
	 */
	private static final String Generate_Configuration_Default = ComponentPreferenceManager.class.getName()
			+ "GENERATE_CONFIGURATION_DEFAULT";

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
	//
	public static final String DEFAULT_DATAPORT_NAME = "dp_name";
	public static final String DEFAULT_DATAPORT_TYPE = "dp_type";
	public static final String DEFAULT_DATAPORT_VARNAME = "dp_vname";
	//
	public static final String DEFAULT_SERVICEPORT_NAME = "sv_name";
	public static final String DEFAULT_SERVICEIF_NAME = "if_name";
	public static final String DEFAULT_SERVICEIF_INSTANCENAME = "if_instance";
	public static final String DEFAULT_SERVICEIF_VARNAME = "if_varname";
	//
	public static final String DEFAULT_CONFIGURATION_NAME = "conf_name";
	public static final String DEFAULT_CONFIGURATION_TYPE = "conf_type";
	public static final String DEFAULT_CONFIGURATION_VARNAME = "conf_varname";
	public static final String DEFAULT_CONFIGURATION_DEFAULT = "conf_default";

	/**
	 * �R�[�h�������� ModuleName �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return Module Name �f�t�H���g�l
	 */
	public String getBasic_ComponentName() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Basic_Name, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_Basic_Name);
		String result = new String();
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_COMPONENT_NAME;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * �R�[�h�������� ModuleName �f�t�H���g�l��ݒ肷��
	 * 
	 * @param key �L�[
	 * @param defaultModuleName	 ModuleName �f�t�H���g�l
	 */
	public void setBasic_ComponentName(String defaultModuleName) {
		String oldModuleName = getBasic_ComponentName();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Basic_Name, defaultModuleName);

		propertyChangeSupport.firePropertyChange(Generate_Basic_Name, oldModuleName, defaultModuleName);
	}
	
	/**
	 * �R�[�h�������� ModuleDescription �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return Module Description �f�t�H���g�l
	 */
	public String getBasic_Description() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Basic_Description, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_Basic_Description);
		String result = new String();
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_DESCRIPTION;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * �R�[�h�������� ModuleDescription �f�t�H���g�l��ݒ肷��
	 * 
	 * @param key �L�[
	 * @param defaultDescription	 Module Description �f�t�H���g�l
	 */
	public void setBasic_Description(String defaultDescription) {
		String oldDescription = getBasic_Description();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Basic_Description, defaultDescription);

		propertyChangeSupport.firePropertyChange(Generate_Basic_Description, oldDescription, defaultDescription);
	}

	/**
	 * �R�[�h�������� Module Category �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return Module Category �f�t�H���g�l
	 */
	public String getBasic_Category() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Basic_Category, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_Basic_Category);
		String result = new String();
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_CATEGORY;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * �R�[�h�������� Module Category �f�t�H���g�l��ݒ肷��
	 * 
	 * @param key �L�[
	 * @param defaultCategory	 Module Category �f�t�H���g�l
	 */
	public void setBasic_Category(String defaultCategory) {
		String oldCategory = getBasic_Category();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Basic_Category, defaultCategory);

		propertyChangeSupport.firePropertyChange(Generate_Basic_Category, oldCategory, defaultCategory);
	}

	/**
	 * �R�[�h�������� Module Version �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return Module Version �f�t�H���g�l
	 */
	public String getBasic_Version() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Basic_Version, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_Basic_Version);
		String result = new String();
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_VERSION;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * �R�[�h�������� Module Version �f�t�H���g�l��ݒ肷��
	 * 
	 * @param key �L�[
	 * @param defaultVersion	 Module Version �f�t�H���g�l
	 */
	public void setBasic_Version(String defaultVersion) {
		String oldVersion = getBasic_Version();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Basic_Version, defaultVersion);

		propertyChangeSupport.firePropertyChange(Generate_Basic_Version, oldVersion, defaultVersion);
	}

	/**
	 * �R�[�h�������� Module Component Type �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return Module Component Type �f�t�H���g�l
	 */
	public String getBasic_ComponentType() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Basic_ComponentType, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_Basic_ComponentType);
		String result = new String();
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_COMPONENT_TYPE;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * �R�[�h�������� Module Component Type �f�t�H���g�l��ݒ肷��
	 * 
	 * @param key �L�[
	 * @param defaultCompType	 Module Component Type �f�t�H���g�l
	 */
	public void setBasic_ComponentType(String defaultCompType) {
		String oldCompType = getBasic_ComponentType();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Basic_ComponentType, defaultCompType);

		propertyChangeSupport.firePropertyChange(Generate_Basic_ComponentType, oldCompType, defaultCompType);
	}

	/**
	 * �R�[�h�������� Module Activity Type �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return Module Activity Type �f�t�H���g�l
	 */
	public String getBasic_ActivityType() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Basic_ActivityType, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_Basic_ActivityType);
		String result = new String();
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_ACTIVITY_TYPE;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * �R�[�h�������� Module Activity Type �f�t�H���g�l��ݒ肷��
	 * 
	 * @param key �L�[
	 * @param defaultActivityType	 Module Activity Type �f�t�H���g�l
	 */
	public void setBasic_ActivityType(String defaultActivityType) {
		String oldActivityType = getBasic_ActivityType();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Basic_ActivityType, defaultActivityType);

		propertyChangeSupport.firePropertyChange(Generate_Basic_ActivityType, oldActivityType, defaultActivityType);
	}

	/**
	 * �R�[�h�������� Module Component Kind �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return Module Component Kind �f�t�H���g�l
	 */
	public String getBasic_ComponentKind() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Basic_ComponentKind, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_Basic_ComponentKind);
		String result = new String();
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_COMPONENT_KIND;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * �R�[�h�������� Module Component Kind �f�t�H���g�l��ݒ肷��
	 * 
	 * @param key �L�[
	 * @param defaultCompKind	 Module Component Kind �f�t�H���g�l
	 */
	public void setBasic_ComponentKind(String defaultCompKind) {
		String oldCompKind = getBasic_ComponentKind();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Basic_ComponentKind, defaultCompKind);

		propertyChangeSupport.firePropertyChange(Generate_Basic_ComponentKind, oldCompKind, defaultCompKind);
	}

	/**
	 * �R�[�h�������� Module Vendor Name �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return Module Vendor Name �f�t�H���g�l
	 */
	public String getBasic_VendorName() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Basic_Vendor_Name, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_Basic_Vendor_Name);
		String result = new String();
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_VENDER;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * �R�[�h�������� Module Vendor Name �f�t�H���g�l��ݒ肷��
	 * 
	 * @param key �L�[
	 * @param defaultVendor	 Module Vendor Name �f�t�H���g�l
	 */
	public void setBasic_VendorName(String defaultVendor) {
		String oldVendor = getBasic_VendorName();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Basic_Vendor_Name, defaultVendor);

		propertyChangeSupport.firePropertyChange(Generate_Basic_Vendor_Name, oldVendor, defaultVendor);
	}

	/**
	 * �R�[�h�������� Module Max Instances �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return Module Max Instances �f�t�H���g�l
	 */
	public int getBasic_MaxInstances() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Basic_Max_Instance, -1);

		int resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getInt(Generate_Basic_Max_Instance);
		int result;
		if (resultTemp == -1) { // defaultvalue
			result = DEFAULT_MAXINST;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * �R�[�h�������� Module Max Instances �f�t�H���g�l��ݒ肷��
	 * 
	 * @param key �L�[
	 * @param defaultMaxInst	 Module Max Instances �f�t�H���g�l
	 */
	public void setBasic_MaxInstances(int defaultMaxInst) {
		int oldMaxInst = getBasic_MaxInstances();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Basic_Max_Instance, defaultMaxInst);

		propertyChangeSupport.firePropertyChange(Generate_Basic_Max_Instance, oldMaxInst, defaultMaxInst);
	}

	/**
	 * �R�[�h�������� Module Execution Type �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return Module Execution Type �f�t�H���g�l
	 */
	public String getBasic_ExecutionType() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Basic_ExecutionType, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_Basic_ExecutionType);
		String result = new String();
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_EXECUTION_TYPE;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * �R�[�h�������� Module Execution Type �f�t�H���g�l��ݒ肷��
	 * 
	 * @param key �L�[
	 * @param defaultExecutionType	 Module Execution Type �f�t�H���g�l
	 */
	public void setBasic_ExecutionType(String defaultExecutionType) {
		String oldExecutionType = getBasic_ExecutionType();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Basic_ExecutionType, defaultExecutionType);

		propertyChangeSupport.firePropertyChange(Generate_Basic_ExecutionType, oldExecutionType, defaultExecutionType);
	}

	/**
	 * �R�[�h�������� Execution Rate �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return Execution Rate �f�t�H���g�l
	 */
	public double getBasic_ExecutionRate() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Basic_Execution_Rate, -1);

		double resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getDouble(Generate_Basic_Execution_Rate);
		double result;
		if (resultTemp == -1) { // defaultvalue
			result = DEFAULT_EXECUTION_RATE;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * �R�[�h�������� Execution Rate �f�t�H���g�l��ݒ肷��
	 * 
	 * @param key �L�[
	 * @param defaultExecutionRate	 Execution Rate �f�t�H���g�l
	 */
	public void setBasic_ExecutionRate(double defaultExecutionRate) {
		double oldExecutionRate = getBasic_ExecutionRate();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Basic_Execution_Rate, defaultExecutionRate);

		propertyChangeSupport.firePropertyChange(Generate_Basic_Execution_Rate, Double.valueOf(oldExecutionRate), Double.valueOf(defaultExecutionRate));
	}
//
	/**
	 * �R�[�h�������� DataPort Name �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return DataPort Name �f�t�H���g�l
	 */
	public String getDataPort_Name() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_DataPort_Name, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_DataPort_Name);
		String result;
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_DATAPORT_NAME;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * �R�[�h�������� DataPort Name �f�t�H���g�l��ݒ肷��
	 * 
	 * @param key �L�[
	 * @param defaultDataPortName	 DataPort Name �f�t�H���g�l
	 */
	public void setDataPort_Name(String defaultDataPortName) {
		String oldDataPortName = getDataPort_Name();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_DataPort_Name, defaultDataPortName);

		propertyChangeSupport.firePropertyChange(Generate_DataPort_Name, oldDataPortName, defaultDataPortName);
	}

	/**
	 * �R�[�h�������� DataPort Type �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return DataPort Type �f�t�H���g�l
	 */
	public String getDataPort_Type() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_DataPort_Type, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_DataPort_Type);
		String result;
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_DATAPORT_TYPE;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * �R�[�h�������� DataPort Type �f�t�H���g�l��ݒ肷��
	 * 
	 * @param key �L�[
	 * @param defaultDataPortType	 DataPort Type �f�t�H���g�l
	 */
	public void setDataPort_Type(String defaultDataPortType) {
		String oldDataPortType = getDataPort_Type();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_DataPort_Type, defaultDataPortType);

		propertyChangeSupport.firePropertyChange(Generate_DataPort_Type, oldDataPortType, defaultDataPortType);
	}

	/**
	 * �R�[�h�������� DataPort �ϐ��� �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return DataPort �ϐ��� �f�t�H���g�l
	 */
	public String getDataPort_VarName() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_DataPort_VarName, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_DataPort_VarName);
		String result;
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_DATAPORT_VARNAME;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * �R�[�h�������� DataPort �ϐ��� �f�t�H���g�l��ݒ肷��
	 * 
	 * @param key �L�[
	 * @param defaultDataPortVarName	 DataPort �ϐ��� �f�t�H���g�l
	 */
	public void setDataPort_VarName(String defaultDataPortVarName) {
		String oldDataPortVarName = getDataPort_VarName();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_DataPort_VarName, defaultDataPortVarName);

		propertyChangeSupport.firePropertyChange(Generate_DataPort_VarName, oldDataPortVarName, defaultDataPortVarName);
	}

	/**
	 * �R�[�h�������� ServicePort �� �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return ServicePort �ϐ��� �f�t�H���g�l
	 */
	public String getServicePort_Name() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_ServicePort_Name, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_ServicePort_Name);
		String result;
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_SERVICEPORT_NAME;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * �R�[�h�������� ServicePort �� �f�t�H���g�l��ݒ肷��
	 * 
	 * @param key �L�[
	 * @param defaultServicePortName	 ServicePort �� �f�t�H���g�l
	 */
	public void setServicePort_Name(String defaultServicePortName) {
		String oldServicePortName = getServicePort_Name();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_ServicePort_Name, defaultServicePortName);

		propertyChangeSupport.firePropertyChange(Generate_ServicePort_Name, oldServicePortName, defaultServicePortName);
	}

	/**
	 * �R�[�h�������� ServiceIF �� �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return ServiceIF�� �f�t�H���g�l
	 */
	public String getServiceIF_Name() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_ServiceIF_Name, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_ServiceIF_Name);
		String result;
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_SERVICEIF_NAME;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * �R�[�h�������� ServiceIF �� �f�t�H���g�l��ݒ肷��
	 * 
	 * @param key �L�[
	 * @param defaultServiceIFName	 ServiceIF �� �f�t�H���g�l
	 */
	public void setServiceIF_Name(String defaultServiceIFName) {
		String oldServiceIFName = getServiceIF_Name();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_ServiceIF_Name, defaultServiceIFName);

		propertyChangeSupport.firePropertyChange(Generate_ServiceIF_Name, oldServiceIFName, defaultServiceIFName);
	}

	/**
	 * �R�[�h�������� ServiceIF �C���X�^���X�� �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return ServiceIF �C���X�^���X�� �f�t�H���g�l
	 */
	public String getServiceIF_InstanceName() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_ServiceIF_InstanceName, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_ServiceIF_InstanceName);
		String result;
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_SERVICEIF_INSTANCENAME;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * �R�[�h�������� ServiceIF �C���X�^���X�� �f�t�H���g�l��ݒ肷��
	 * 
	 * @param key �L�[
	 * @param defaultServiceIFInstanceName	 ServiceIF �C���X�^���X�� �f�t�H���g�l
	 */
	public void setServiceIF_InstanceName(String defaultServiceIFInstanceName) {
		String oldServiceIFInstanceName = getServiceIF_InstanceName();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_ServiceIF_InstanceName, defaultServiceIFInstanceName);

		propertyChangeSupport.firePropertyChange(Generate_ServiceIF_InstanceName, oldServiceIFInstanceName, defaultServiceIFInstanceName);
	}

	/**
	 * �R�[�h�������� ServiceIF �ϐ��� �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return ServiceIF �ϐ��� �f�t�H���g�l
	 */
	public String getServiceIF_VarName() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_ServiceIF_VarName, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_ServiceIF_VarName);
		String result;
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_SERVICEIF_VARNAME;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * �R�[�h�������� ServiceIF �ϐ��� �f�t�H���g�l��ݒ肷��
	 * 
	 * @param key �L�[
	 * @param defaultServiceIFVarName	 ServiceIF �ϐ��� �f�t�H���g�l
	 */
	public void setServiceIF_VarName(String defaultServiceIFVarName) {
		String oldServiceIFVarName = getServiceIF_VarName();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_ServiceIF_VarName, defaultServiceIFVarName);

		propertyChangeSupport.firePropertyChange(Generate_ServiceIF_VarName, oldServiceIFVarName, defaultServiceIFVarName);
	}

	/**
	 * �R�[�h�������� Configuration Name �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return Configuration Name �f�t�H���g�l
	 */
	public String getConfiguration_Name() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Configuration_Name, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_Configuration_Name);
		String result;
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_CONFIGURATION_NAME;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * �R�[�h�������� Configuration Name �f�t�H���g�l��ݒ肷��
	 * 
	 * @param key �L�[
	 * @param defaultConfigurationName	 Configuration Name �f�t�H���g�l
	 */
	public void setConfiguration_Name(String defaultConfigurationName) {
		String oldConfigName = getConfiguration_Name();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Configuration_Name, defaultConfigurationName);

		propertyChangeSupport.firePropertyChange(Generate_Configuration_Name, oldConfigName, defaultConfigurationName);
	}

	/**
	 * �R�[�h�������� Configuration Type �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return Configuration Type �f�t�H���g�l
	 */
	public String getConfiguration_Type() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Configuration_Type, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_Configuration_Type);
		String result;
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_CONFIGURATION_TYPE;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * �R�[�h�������� Configuration Type �f�t�H���g�l��ݒ肷��
	 * 
	 * @param key �L�[
	 * @param defaultConfigurationType	 Configuration Type �f�t�H���g�l
	 */
	public void setConfiguration_Type(String defaultConfigurationType) {
		String oldConfigType = getConfiguration_Type();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Configuration_Type, defaultConfigurationType);

		propertyChangeSupport.firePropertyChange(Generate_Configuration_Type, oldConfigType, defaultConfigurationType);
	}

	/**
	 * �R�[�h�������� Configuration �ϐ��� �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return Configuration �ϐ��� �f�t�H���g�l
	 */
	public String getConfiguration_VarName() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Configuration_VarName, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_Configuration_VarName);
		String result;
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_CONFIGURATION_VARNAME;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * �R�[�h�������� Configuration �ϐ��� �f�t�H���g�l��ݒ肷��
	 * 
	 * @param key �L�[
	 * @param defaultConfigurationVarName	 Configuration �ϐ��� �f�t�H���g�l
	 */
	public void setConfiguration_VarName(String defaultConfigurationVarName) {
		String oldConfigVarName = getConfiguration_VarName();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Configuration_VarName, defaultConfigurationVarName);

		propertyChangeSupport.firePropertyChange(Generate_Configuration_VarName, oldConfigVarName, defaultConfigurationVarName);
	}

	/**
	 * �R�[�h�������� Configuration �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return Configuration �f�t�H���g�l
	 */
	public String getConfiguration_Default() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_Configuration_Default, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_Configuration_Default);
		String result;
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_CONFIGURATION_DEFAULT;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * �R�[�h�������� Configuration �f�t�H���g�l��ݒ肷��
	 * 
	 * @param key �L�[
	 * @param defaultConfigurationDefault	 Configuration �f�t�H���g�l
	 */
	public void setConfiguration_Default(String defaultConfigurationDefault) {
		String oldConfigDefault = getConfiguration_Default();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_Configuration_Default, defaultConfigurationDefault);

		propertyChangeSupport.firePropertyChange(Generate_Configuration_Default, oldConfigDefault, defaultConfigurationDefault);
	}

	/**
	 * @see PropertyChangeSupport#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	/**
	 * @see PropertyChangeSupport#removePropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
}
