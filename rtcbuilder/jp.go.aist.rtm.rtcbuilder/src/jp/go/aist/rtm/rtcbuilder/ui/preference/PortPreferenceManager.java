package jp.go.aist.rtm.rtcbuilder.ui.preference;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import jp.go.aist.rtm.rtcbuilder.RtcBuilderPlugin;

public class PortPreferenceManager {
	private static PortPreferenceManager __instance = new PortPreferenceManager();
	protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	/**
	 * �R���X�g���N�^
	 * 
	 * @return �V���O���g��
	 */
	public static PortPreferenceManager getInstance() {
		return __instance;
	}

	/**
	 * DataPort Name�̃L�[
	 */
	private static final String Generate_DataPort_Name = PortPreferenceManager.class.getName()
			+ "GENERATE_DATAPORT_NAME";
	/**
	 * DataPort Type�̃L�[
	 */
	private static final String Generate_DataPort_Type = PortPreferenceManager.class.getName()
			+ "GENERATE_DATAPORT_TYPE";
	/**
	 * DataPort VarName�̃L�[
	 */
	private static final String Generate_DataPort_VarName = PortPreferenceManager.class.getName()
			+ "GENERATE_DATAPORT_VARNAME";
	/**
	 * DataPort Constraint�̃L�[
	 */
	private static final String Generate_DataPort_Constraint = PortPreferenceManager.class.getName()
			+ "GENERATE_DATAPORT_CONSTRAINT";
	/**
	 * DataPort Unit�̃L�[
	 */
	private static final String Generate_DataPort_Unit = PortPreferenceManager.class.getName()
			+ "GENERATE_DATAPORT_UNIT";
	/**
	 * DataPort �ړ���̃L�[
	 */
	private static final String Generate_DataPort_Prefix = PortPreferenceManager.class.getName()
			+ "GENERATE_DATAPORT_PREFIX";
	/**
	 * DataPort �ڔ���̃L�[
	 */
	private static final String Generate_DataPort_Suffix = PortPreferenceManager.class.getName()
			+ "GENERATE_DATAPORT_SUFFIX";
	////
	/**
	 * ServicePort Name�̃L�[
	 */
	private static final String Generate_ServicePort_Name = PortPreferenceManager.class.getName()
			+ "GENERATE_SERVICEPORT_NAME";
	/**
	 * ServicePort �ړ���̃L�[
	 */
	private static final String Generate_ServicePort_Prefix = PortPreferenceManager.class.getName()
			+ "GENERATE_SERVICEPORT_PREFIX";
	/**
	 * ServicePort �ڔ���̃L�[
	 */
	private static final String Generate_ServicePort_Suffix = PortPreferenceManager.class.getName()
			+ "GENERATE_SERVICEPORT_SUFFIX";
	/**
	 * ServiceInterfacet Name�̃L�[
	 */
	private static final String Generate_ServiceIF_Name = PortPreferenceManager.class.getName()
			+ "GENERATE_SERVICEIF_NAME";
	/**
	 * ServiceInterfacet Instance Name�̃L�[
	 */
	private static final String Generate_ServiceIF_InstanceName = PortPreferenceManager.class.getName()
			+ "GENERATE_SERVICEIF_INSTANCENAME";
	/**
	 * ServiceInterfacet Varriable Name�̃L�[
	 */
	private static final String Generate_ServiceIF_VarName = PortPreferenceManager.class.getName()
			+ "GENERATE_SERVICEIF_VARNAME";
	/**
	 * ServiceInterfacet Instance �ړ���̃L�[
	 */
	private static final String Generate_ServiceIF_Prefix = PortPreferenceManager.class.getName()
			+ "GENERATE_SERVICEIF_PREFIX";
	/**
	 * ServiceInterfacet Instance �ڔ���̃L�[
	 */
	private static final String Generate_ServiceIF_Suffix = PortPreferenceManager.class.getName()
			+ "GENERATE_SERVICEIF_SUFFIX";
	///
	/**
	 * Port�ݒ��ʂň�x�ł�OK�������ꂽ���ۂ��������t�B�[���h�̃L�[
	 */
	private static final String Port_Preference_Status = PortPreferenceManager.class.getName()
			+ "PORT_PREFERENCE_STATUS";
	/**
	 * Port�ݒ��ʂň�x�ł�OK�������ꂽ�ꍇ��L�t�B�[���h�Ɋi�[����萔�l
	 */
	private static final String Port_Preference_Status_Dirty = "DIRTY";

	public static final String DEFAULT_DATAPORT_NAME = "dp_name";
	public static final String DEFAULT_DATAPORT_TYPE = "dp_type";
	public static final String DEFAULT_DATAPORT_VARNAME = "dp_vname";
	public static final String DEFAULT_DATAPORT_CONSTRAINT = "dp_constraint";
	public static final String DEFAULT_DATAPORT_UNIT = "";
	public static final String DEFAULT_DATAPORT_PREFIX = "";
	public static final String DEFAULT_DATAPORT_SUFFIX = "";
	//
	public static final String DEFAULT_SERVICEPORT_NAME = "sv_name";
	public static final String DEFAULT_SERVICEPORT_PREFIX = "";
	public static final String DEFAULT_SERVICEPORT_SUFFIX = "";
	//
	public static final String DEFAULT_SERVICEIF_NAME = "if_name";
	public static final String DEFAULT_SERVICEIF_INSTANCENAME = "if_instance";
	public static final String DEFAULT_SERVICEIF_VARNAME = "if_varname";
	public static final String DEFAULT_SERVICEIF_PREFIX = "";
	public static final String DEFAULT_SERVICEIF_SUFFIX = "";

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
		if (resultTemp.equals("") && !Port_Preference_Status_Dirty.equals(getPortPreferenceStatus())) { // defaultvalue
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
		if (resultTemp.equals("") && !Port_Preference_Status_Dirty.equals(getPortPreferenceStatus())) { // defaultvalue
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
		if (resultTemp.equals("") && !Port_Preference_Status_Dirty.equals(getPortPreferenceStatus())) { // defaultvalue
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
	 * �R�[�h�������� DataPort ���� �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return DataPort ���� �f�t�H���g�l
	 */
	public String getDataPort_Constraint() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_DataPort_Constraint, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_DataPort_Constraint);
		String result;
		if (resultTemp.equals("") && !Port_Preference_Status_Dirty.equals(getPortPreferenceStatus())) { // defaultvalue
			result = DEFAULT_DATAPORT_CONSTRAINT;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * �R�[�h�������� DataPort ���� �f�t�H���g�l��ݒ肷��
	 * 
	 * @param key �L�[
	 * @param defaultDataPortVarName	 DataPort ���� �f�t�H���g�l
	 */
	public void setDataPort_Constraint(String defaultDataPortConstraint) {
		String oldDataPortConstraint = getDataPort_Constraint();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_DataPort_Constraint, defaultDataPortConstraint);

		propertyChangeSupport.firePropertyChange(Generate_DataPort_Constraint, oldDataPortConstraint, defaultDataPortConstraint);
	}
	
	/**
	 * �R�[�h�������� DataPort Unit �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return DataPort Unit �f�t�H���g�l
	 */
	public String getDataPort_Unit() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_DataPort_Unit, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_DataPort_Unit);
		String result;
		if (resultTemp.equals("") && !Port_Preference_Status_Dirty.equals(getPortPreferenceStatus())) { // defaultvalue
			result = DEFAULT_DATAPORT_UNIT;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * �R�[�h�������� DataPort Unit �f�t�H���g�l��ݒ肷��
	 * 
	 * @param key �L�[
	 * @param defaultDataPortVarName	 DataPort Unit �f�t�H���g�l
	 */
	public void setDataPort_Unit(String defaultDataPortUnit) {
		String oldDataPortUnit = getDataPort_Unit();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_DataPort_Unit, defaultDataPortUnit);

		propertyChangeSupport.firePropertyChange(Generate_DataPort_Unit, oldDataPortUnit, defaultDataPortUnit);
	}
	
	/**
	 * �R�[�h�������� DataPort �ړ��� �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return DataPort �ړ��� �f�t�H���g�l
	 */
	public String getDataPort_Prefix() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_DataPort_Prefix, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_DataPort_Prefix);
		String result;
		if (resultTemp.equals("") && !Port_Preference_Status_Dirty.equals(getPortPreferenceStatus())) { // defaultvalue
			result = DEFAULT_DATAPORT_PREFIX;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * �R�[�h�������� DataPort �ړ��� �f�t�H���g�l��ݒ肷��
	 * 
	 * @param key �L�[
	 * @param defaultDataPortPrefix	 DataPort �ړ��� �f�t�H���g�l
	 */
	public void setDataPort_Prefix(String defaultDataPortPrefix) {
		String oldDataPortPrefix = getDataPort_Prefix();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_DataPort_Prefix, defaultDataPortPrefix);

		propertyChangeSupport.firePropertyChange(Generate_DataPort_Prefix, oldDataPortPrefix, defaultDataPortPrefix);
	}
	
	/**
	 * �R�[�h�������� DataPort �ڔ��� �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return DataPort �ڔ��� �f�t�H���g�l
	 */
	public String getDataPort_Suffix() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_DataPort_Suffix, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_DataPort_Suffix);
		String result;
		if (resultTemp.equals("") && !Port_Preference_Status_Dirty.equals(getPortPreferenceStatus())) { // defaultvalue
			result = DEFAULT_DATAPORT_SUFFIX;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * �R�[�h�������� DataPort �ڔ��� �f�t�H���g�l��ݒ肷��
	 * 
	 * @param key �L�[
	 * @param defaultDataPortSuffix	 DataPort �ڔ��� �f�t�H���g�l
	 */
	public void setDataPort_Suffix(String defaultDataPortSuffix) {
		String oldDataPortSuffix = getDataPort_Suffix();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_DataPort_Suffix, defaultDataPortSuffix);

		propertyChangeSupport.firePropertyChange(Generate_DataPort_Suffix, oldDataPortSuffix, defaultDataPortSuffix);
	}

	//
	//
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
		if (resultTemp.equals("") && !Port_Preference_Status_Dirty.equals(getPortPreferenceStatus())) { // defaultvalue
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
	 * �R�[�h�������� ServicePort �ړ��� �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return ServicePort �ړ��� �f�t�H���g�l
	 */
	public String getServicePort_Prefix() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_ServicePort_Prefix, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_ServicePort_Prefix);
		String result;
		if (resultTemp.equals("") && !Port_Preference_Status_Dirty.equals(getPortPreferenceStatus())) { // defaultvalue
			result = DEFAULT_SERVICEPORT_PREFIX;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * �R�[�h�������� ServicePort �ړ��� �f�t�H���g�l��ݒ肷��
	 * 
	 * @param key �L�[
	 * @param defaultServicePortPrefix	 ServicePort �ړ��� �f�t�H���g�l
	 */
	public void setServicePort_Prefix(String defaultServicePortPrefix) {
		String oldServicePortPrefix = getServicePort_Prefix();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_ServicePort_Prefix, defaultServicePortPrefix);

		propertyChangeSupport.firePropertyChange(Generate_ServicePort_Prefix, oldServicePortPrefix, defaultServicePortPrefix);
	}

	/**
	 * �R�[�h�������� ServicePort �ڔ��� �f�t�H���g�l���擾����
	 * 
	 * @param key �L�[
	 * @return ServicePort �ڔ��� �f�t�H���g�l
	 */
	public String getServicePort_Suffix() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_ServicePort_Suffix, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_ServicePort_Suffix);
		String result;
		if (resultTemp.equals("") && !Port_Preference_Status_Dirty.equals(getPortPreferenceStatus())) { // defaultvalue
			result = DEFAULT_SERVICEPORT_SUFFIX;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * �R�[�h�������� ServicePort �ڔ��� �f�t�H���g�l��ݒ肷��
	 * 
	 * @param key �L�[
	 * @param defaultServicePortSuffix	 ServicePort �ڔ��� �f�t�H���g�l
	 */
	public void setServicePort_Suffix(String defaultServicePortSuffix) {
		String oldServicePortSuffix = getServicePort_Suffix();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_ServicePort_Suffix, defaultServicePortSuffix);

		propertyChangeSupport.firePropertyChange(Generate_ServicePort_Suffix, oldServicePortSuffix, defaultServicePortSuffix);
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
		if (resultTemp.equals("") && !Port_Preference_Status_Dirty.equals(getPortPreferenceStatus())) { // defaultvalue
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
		if (resultTemp.equals("") && !Port_Preference_Status_Dirty.equals(getPortPreferenceStatus())) { // defaultvalue
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
		if (resultTemp.equals("") && !Port_Preference_Status_Dirty.equals(getPortPreferenceStatus())) { // defaultvalue
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
	 * �R�[�h�������� ServiceIF �ړ�����擾����
	 * 
	 * @param key �L�[
	 * @return ServiceIF �ړ��� �f�t�H���g�l
	 */
	public String getServiceIF_Prefix() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_ServiceIF_Prefix, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_ServiceIF_Prefix);
		String result;
		if (resultTemp.equals("") && !Port_Preference_Status_Dirty.equals(getPortPreferenceStatus())) { // defaultvalue
			result = DEFAULT_SERVICEIF_PREFIX;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * �R�[�h�������� ServiceIF �ړ��� �f�t�H���g�l��ݒ肷��
	 * 
	 * @param key �L�[
	 * @param defaultServiceIFPrefix	 ServiceIF �ړ��� �f�t�H���g�l
	 */
	public void setServiceIF_Prefix(String defaultServiceIFPrefix) {
		String oldServiceIFPrefix = getServiceIF_Prefix();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_ServiceIF_Prefix, defaultServiceIFPrefix);

		propertyChangeSupport.firePropertyChange(Generate_ServiceIF_Prefix, oldServiceIFPrefix, defaultServiceIFPrefix);
	}

	/**
	 * �R�[�h�������� ServiceIF �ڔ�����擾����
	 * 
	 * @param key �L�[
	 * @return ServiceIF �ڔ��� �f�t�H���g�l
	 */
	public String getServiceIF_Suffix() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Generate_ServiceIF_Suffix, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Generate_ServiceIF_Suffix);
		String result;
		if (resultTemp.equals("") && !Port_Preference_Status_Dirty.equals(getPortPreferenceStatus())) { // defaultvalue
			result = DEFAULT_SERVICEIF_SUFFIX;
		} else {
			result = resultTemp;
		}
		return result;
	}
	/**
	 * �R�[�h�������� ServiceIF �ڔ��� �f�t�H���g�l��ݒ肷��
	 * 
	 * @param key �L�[
	 * @param defaultServiceIFSuffix	 ServiceIF �ڔ��� �f�t�H���g�l
	 */
	public void setServiceIF_Suffix(String defaultServiceIFSuffix) {
		String oldServiceIFSuffix = getServiceIF_Suffix();

		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Generate_ServiceIF_Suffix, defaultServiceIFSuffix);

		propertyChangeSupport.firePropertyChange(Generate_ServiceIF_Suffix, oldServiceIFSuffix, defaultServiceIFSuffix);
	}

	/**
	 * Port�ݒ��ʂ��ݒ�ς݂��ۂ����������ڂ̒l���擾����
	 * 
	 * @return
	 */
	public String getPortPreferenceStatus() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Port_Preference_Status, "");
		return RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Port_Preference_Status);
	}
	/**
	 * Port�ݒ��ʂ��ݒ�ς݂��ۂ����������ڂ̒l��ݒ肷��
	 */
	public void setDirtyToPortPreferenceStatus() {
		String oldStatus = getPortPreferenceStatus();
		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Port_Preference_Status, Port_Preference_Status_Dirty);
		propertyChangeSupport.firePropertyChange(Port_Preference_Status, oldStatus, Port_Preference_Status_Dirty);
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
