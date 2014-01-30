package jp.go.aist.rtm.rtcbuilder.ui.preference;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.RtcBuilderPlugin;
import jp.go.aist.rtm.rtcbuilder.generator.param.ConfigParameterParam;

public class ConfigPreferenceManager {
	private static ConfigPreferenceManager __instance = new ConfigPreferenceManager();
	private static final String Separator = "::";
	private static final String EscSeparator = "&colon;";
	private static final String EofMark = "&EOF";
	
	public static ConfigPreferenceManager getInstance() {
		getDefaultConfigValue();
		return __instance;
	}

	/**
	 * Configuration Name�̃L�[
	 */
	private static final String Config_Name = ConfigPreferenceManager.class.getName()
									+ "CONFIG_NAME";
	/**
	 * Default Value�̃L�[
	 */
	private static final String Default_Value = ConfigPreferenceManager.class.getName()
									+ "DEFAULT_VALUE";

	public static ArrayList<ConfigParameterParam> defaultConfigValue = new ArrayList<ConfigParameterParam>();

	protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(	this);

	
	private static final String[] CONFIG_ITEM = new String[] { 
//	    "config.version",
		"corba.args",
		"corba.endpoint",
//  "corba.id",               corba_name,
//		"corba.name_servers",
		"corba.nameservers",
		//
		"exec_cxt.evdriven.type",
		"exec_cxt.periodic.rate",
		"exec_cxt.periodic.type",
		//
		"logger.date_format",
		"logger.enable",
		"logger.file_name",
		"logger.log_level",
		"logger.master_logger",
		"logger.stream_lock",
		//
		"manager.modules.load_path",
		"manager.name", 
//  	"manager.pid",
		"module.conf_path",
//		"module.load_path",
		"naming.enable",
		"naming.formats",
		"naming.type",
		"naming.update.enable",
		"naming.update.interval",
//	    "openrtm.version",
		"os.arch",
		"os.hostname",
		"os.name",
		"os.release",
		"os.version",
		"timer.enable",
		"timer.tick"
	};
	private static final String[] DEFAULT_ITEM = new String[] { 
//    	openrtm_version,		"config.version",
		"",								//"corba.args",
		"",								//"corba.endpoint",   // hostname:port_number
//    	corba_name,			"corba.id",
		"",								//"corba.name_servers",
		//
		"EventDrivenExecutionContext",	//"exec_cxt.evdriven.type",
		"1000",							//"exec_cxt.periodic.rate",
		"PeriodicExecutionContext",		//"exec_cxt.periodic.type",
		//
		"%b %d %H:%M:%S",				//"logger.date_format",
		"YES",							//"logger.enable",
		"./rtc%p.log",					//"logger.file_name",       
		"NORMAL",						//"logger.log_level",       
		"",								//"logger.master_logger",
		"NO",							//"logger.stream_lock",
		//
		"",								//"manager.modules.load_path",
		"manager",						//"manager.name",
//    	"",						"manager.pid",
		"",								//"module.conf_path",       
		"YES",							//"naming.enable",
		"%h.host/%n.rtc",				//"naming.formats",
		"corba",						//"naming.type",
		"YES",							//"naming.update.enable",
		"10.0",							//"naming.update.interval",
//    	openrtm_name,			"openrtm.version"        
		"",								//"os.arch",
		"",								//"os.hostname",
		"",								//"os.name",
		"",								//"os.release",
		"",								//"os.version",
		"YES",							//"timer.enable",
		"0.1"							//"timer.tick",
	};

	public static ArrayList<ConfigParameterParam> getDefaultConfigValue() {
		defaultConfigValue = new ArrayList<ConfigParameterParam>();
		String resultConf = null;
		String resultValue = null;
		
		for (int intIdx=0; intIdx < CONFIG_ITEM.length; intIdx++) {
			resultConf = CONFIG_ITEM[intIdx];
			resultValue = DEFAULT_ITEM[intIdx];
			ConfigParameterParam configParam = new ConfigParameterParam(resultConf, resultValue);
			defaultConfigValue.add(configParam);
		}
		
		return defaultConfigValue;
	}

	
	public static String[] getConfigName() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Config_Name, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Config_Name);
		String[] result;
		if (resultTemp.equals("")) { // defaultvalue
			result = CONFIG_ITEM;
		} else {
			result = resultTemp.split(Separator);
			for(int index=0;index<result.length;index++ ) {
				result[index] = result[index].replaceAll(EscSeparator, Separator);
			}
		}
		return result;
	}

	public static String[] getDefaultValue() {
		RtcBuilderPlugin.getDefault().getPreferenceStore().setDefault(Default_Value, "");

		String resultTemp = RtcBuilderPlugin.getDefault().getPreferenceStore().getString(Default_Value);
		String[] result;
		if (resultTemp.equals("")) { // defaultvalue
			result = DEFAULT_ITEM;
		} else {
			result = resultTemp.split(Separator);
			for(int index=0;index<result.length;index++ ) {
				result[index] = result[index].replaceAll(EscSeparator, Separator);
			}
		}
		return result;
	}
	public static List<ConfigParameterParam> getConfigNameValue() {
		String[] configName = getConfigName();
		String[] defaultValue = getDefaultValue();

		ArrayList<ConfigParameterParam> result = new ArrayList<ConfigParameterParam>();
		
		for(int intIdx=0; intIdx < configName.length; intIdx++) {
			ConfigParameterParam configParam;
			if(intIdx < defaultValue.length ) {
				configParam = new ConfigParameterParam(configName[intIdx],defaultValue[intIdx]);
			} else {
				 configParam = new ConfigParameterParam(configName[intIdx],"");
			}
			result.add(configParam);
		}
		return result;
	}

	public void setConfigValue(List<ConfigParameterParam> arrayConfig) {
		String oldConfigName = convArray2String(getConfigName());
		String oldDefaultValue = convArray2String(getDefaultValue());
		String newConfigName = convConfName2String(arrayConfig);
		String newDefaultValue = convDefVal2String(arrayConfig);
		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Config_Name, newConfigName);
		RtcBuilderPlugin.getDefault().getPreferenceStore().setValue(Default_Value, newDefaultValue);
		propertyChangeSupport.firePropertyChange(Config_Name,oldConfigName,arrayConfig);
		propertyChangeSupport.firePropertyChange(Default_Value,oldDefaultValue,arrayConfig);
	}

	public static String convConfName2String(List<ConfigParameterParam> source) {
		StringBuffer resultTemp = new StringBuffer();
		
		for (int intIdx=0; intIdx<source.size(); intIdx++) {
			String target = source.get(intIdx).getDefaultVal();
			target = target.replaceAll(Separator, EscSeparator);
			source.get(intIdx).setDefaultVal(target);
			resultTemp.append(source.get(intIdx).getConfigName()+Separator);
		}

		String result = resultTemp.toString();
		if(result.length() ==0) return "";
		return result.substring(0, result.length()-1);
	}
	public static String convDefVal2String(List<ConfigParameterParam> source) {
		StringBuffer resultTemp = new StringBuffer();
		
		for (int intIdx=0; intIdx<source.size(); intIdx++) {
			String target = source.get(intIdx).getDefaultVal();
			target = target.replaceAll(Separator, EscSeparator);
			source.get(intIdx).setDefaultVal(target);
			resultTemp.append(source.get(intIdx).getDefaultVal()+Separator);
		}

		String result = resultTemp.toString();
		if(result.length() ==0) return "";
		return result.substring(0, result.length()-1);
	}
	public static String convArray2String(String[] source) {
		StringBuffer resultTemp = new StringBuffer();
		
		for (int intIdx=0; intIdx<source.length; intIdx++) {
			source[intIdx] = source[intIdx].replaceAll(Separator, EscSeparator);
			resultTemp.append(source[intIdx]+Separator);
		}

		String result = resultTemp.toString();
		if(result.length() ==0) return "";
		return result.substring(0, result.length()-1);
	}
}