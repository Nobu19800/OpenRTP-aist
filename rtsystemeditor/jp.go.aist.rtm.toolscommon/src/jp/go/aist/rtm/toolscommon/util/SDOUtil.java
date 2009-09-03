package jp.go.aist.rtm.toolscommon.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import jp.go.aist.rtm.toolscommon.corba.CorbaUtil;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.NameValue;

import org.apache.commons.lang.StringUtils;
import org.omg.CORBA.Any;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.TypeCodePackage.BadKind;

/**
 * SDO�Ɋւ�郆�[�e�B���e�B
 */
public class SDOUtil {

	/**
	 * String��name��value������NameValue�𐶐����܂��B
	 */
	public static _SDOPackage.NameValue newNV(String name, String value) {
		_SDOPackage.NameValue nv = new _SDOPackage.NameValue();
		nv.name = name;
		nv.value = newAny(value);
		return nv;
	}

	/**
	 * String����Any�𐶐����܂��B
	 */
	public static Any newAny(String value) {
		Any any = CorbaUtil.getOrb().create_any();
		if (StringUtils.isAsciiPrintable(value)) {
			any.insert_string(value);
		} else {
			any.insert_wstring(value);
		}
		return any;
	}

	/**
	 * Any�𕶎���ɕϊ����܂��B
	 */
	public static String toAnyString(Any any) {
		if (any == null) return null;
		if (any.type().kind() == TCKind.tk_wstring) {
			return any.extract_wstring();
		} else if (any.type().kind() == TCKind.tk_string){
			return any.extract_string();
		}
		return null;
	}

	/**
	 * nameValue�z�񂩂�Aname�̈�v����Any�^����ɓ����
	 * 
	 * @param nameValue
	 * @param name
	 * @return
	 */
	private static Any getValue(_SDOPackage.NameValue[] nameValue, String name) {
		Any result = null;
		if (nameValue != null) {
			for (_SDOPackage.NameValue elem : nameValue) {
				if (elem != null && name.equals(elem.name)) {
					result = elem.value;
					break;
				}
			}
		}

		return result;
	}

	/**
	 * nameValue�z�񂩂�Aname�̈�v����String�^����ɓ����
	 * 
	 * @param nameValue
	 * @param name
	 * @return
	 */
	public static String getStringValue(_SDOPackage.NameValue[] nameValue, String name) {
		Any any = getValue(nameValue, name);

		String result = null;
		if (any != null) {
			if (any.type().kind() == TCKind.tk_wstring) {
				result = any.extract_wstring();
			} else {
				result = any.extract_string();
			}
		}

		return result;
	}

	/**
	 * nameValue�z�񂩂�Aname���폜����NameValue�z���Ԃ�
	 * 
	 * @param nameValue
	 * @param name
	 * @return
	 */
	public static _SDOPackage.NameValue[] removeNameValue(_SDOPackage.NameValue[] nameValue, String name) {
		List<_SDOPackage.NameValue> result = new ArrayList<_SDOPackage.NameValue>(Arrays
				.asList(nameValue));
		for (_SDOPackage.NameValue value : nameValue) {
			if (value != null) {
				if (value.name.equals(name)) {
					result.remove(value);
				}
			}
		}

		return result.toArray(new _SDOPackage.NameValue[result.size()]);
	}

	/**
	 * nameValue�z��ɁAname�̒l��ݒ肵�����̂�Ԃ��B
	 * <p>
	 * name�����݂���΂��̒l��ύX�������̂�Ԃ��A ���݂��Ȃ���ΐV�����z����쐬���A�l��ǉ����ĕԂ��B
	 * 
	 * @param nameValue
	 * @param name
	 * @return
	 */
	public static _SDOPackage.NameValue[] storeNameValue(_SDOPackage.NameValue[] nameValue,
			String name, String value) {
		Any anyValue = CorbaUtil.getOrb().create_any();
		if (StringUtils.isAsciiPrintable((String) value)) {
			anyValue.insert_string(value);
		} else {
			anyValue.insert_wstring(value);
		}

		return storeNameValue(nameValue, name, anyValue);
	}

	/**
	 * nameValue�z��ɁAname�̒l��ݒ肵�����̂�Ԃ��B
	 * <p>
	 * name�����݂���΂��̒l��ύX�������̂�Ԃ��A ���݂��Ȃ���ΐV�����z����쐬���A�l��ǉ����ĕԂ��B
	 * 
	 * @param nameValue
	 * @param name
	 * @return
	 */
	public static _SDOPackage.NameValue[] storeNameValue(_SDOPackage.NameValue[] nameValue,
			String name, Any value) {
		if (nameValue == null) {
			nameValue = new _SDOPackage.NameValue[0];
		}

		boolean find = false;
		for (_SDOPackage.NameValue elem : nameValue) {
			if (elem != null && name.equals(elem.name)) {
				find = true;
				break;
			}
		}

		_SDOPackage.NameValue[] result;
		if (find) {
			result = nameValue;

			for (_SDOPackage.NameValue elem : result) {
				if (elem != null && name.equals(elem.name)) {
					elem.value = value;
					break;
				}
			}
		} else {
			result = new _SDOPackage.NameValue[nameValue == null ? 0 : nameValue.length + 1];
			System.arraycopy(nameValue, 0, result, 0, nameValue.length);
			result[nameValue.length] = new _SDOPackage.NameValue(name, value);
		}

		return result;
	}

	public static List<String> getValueList(String value) {
		if (value == null) return Collections.emptyList();
		String[] split = value.split(",");
		List<String> result = new ArrayList<String>(split.length);
		for (String item : split) result.add(item.trim());
		return result;
	}

	/**
	 * List����SDO��Namevalue�z����쐬����
	 * 
	 * @param values
	 * @return
	 */
	public static _SDOPackage.NameValue[] createNameValueArray(List<NameValue> values) {
		List<_SDOPackage.NameValue> result = new ArrayList<_SDOPackage.NameValue>();
		for (NameValue nameValue : values) {
			result.add(newNV(nameValue.getName(), nameValue.getValue()));
		}

		return result.toArray(new _SDOPackage.NameValue[result.size()]);
	}

	public static NameValue createNameValue(String key, String value) {
		NameValue nv = ComponentFactory.eINSTANCE.createNameValue();
		nv.setName(key);
		nv.setValue(value);
		return nv;
	}

    /**
     * ConfigurationSet����SDO��ConfigurationSet���쐬����
     */
    @SuppressWarnings("unchecked")
	public static _SDOPackage.ConfigurationSet createSdoConfigurationSet(
    		jp.go.aist.rtm.toolscommon.model.component.ConfigurationSet local) {
        _SDOPackage.ConfigurationSet result = new _SDOPackage.ConfigurationSet();
        result.id = local.getId();

        result.description = "";
        result.configuration_data = createNameValueArray(local
                .getConfigurationData());

        return result;
    }


	/**
	 * SDO��Namevalue�z�񂩂�List���쐬����
	 * 
	 * @param values
	 * @return
	 */
	public static List<NameValue> createNameValueList(_SDOPackage.NameValue[] values) {
		List<NameValue> result = new ArrayList<NameValue>();
		for (_SDOPackage.NameValue value : values) {
			result.add(createNameValue(value));
		}
		return result;
	}

	private static NameValue createNameValue(_SDOPackage.NameValue value) {
		NameValue nameValue = ComponentFactory.eINSTANCE.createNameValue();
		nameValue.setName(value.name);
		if (value.value.type().kind() == TCKind.tk_wstring) {
			nameValue.setValue(value.value.extract_wstring());
		} else if (value.value.type().kind() == TCKind.tk_string) {
			nameValue.setValue(value.value.extract_string());
		} else {
			try {
				nameValue.setTypeName(value.value.type().name());
			} catch (BadKind e) {
				nameValue.setTypeName("<Unknown>");
			}
		}
		return nameValue;
	}

}
