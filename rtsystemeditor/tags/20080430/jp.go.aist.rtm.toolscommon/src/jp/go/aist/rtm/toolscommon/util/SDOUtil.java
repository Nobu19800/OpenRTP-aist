package jp.go.aist.rtm.toolscommon.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.go.aist.rtm.toolscommon.corba.CorbaUtil;

import org.apache.commons.lang.StringUtils;
import org.omg.CORBA.Any;
import org.omg.CORBA.TCKind;

import _SDOPackage.NameValue;

/**
 * SDO�Ɋւ�郆�[�e�B���e�B
 */
public class SDOUtil {

	/**
	 * nameValue�z�񂩂�Aname�̈�v����Any�^����ɓ����
	 * 
	 * @param nameValue
	 * @param name
	 * @return
	 */
	private static Any getValue(NameValue[] nameValue, String name) {
		Any result = null;
		if (nameValue != null) {
			for (NameValue elem : nameValue) {
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
	public static String getStringValue(NameValue[] nameValue, String name) {
		Any any = getValue(nameValue, name);

		String result = null;
		if (any != null) {
			if( any.type().kind() == TCKind.tk_wstring ) {
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
	public static NameValue[] removeNameValue(NameValue[] nameValue, String name) {
		List<NameValue> result = new ArrayList<NameValue>(Arrays
				.asList(nameValue));
		for (NameValue value : nameValue) {
			if (value != null) {
				if (value.name.equals(name)) {
					result.remove(value);
				}
			}
		}

		return result.toArray(new NameValue[result.size()]);
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
	public static NameValue[] storeNameValue(NameValue[] nameValue,
			String name, String value) {
		Any anyValue = CorbaUtil.getOrb().create_any();
		if( StringUtils.isAsciiPrintable((String) value) ) {
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
	public static NameValue[] storeNameValue(NameValue[] nameValue,
			String name, Any value) {
		if (nameValue == null) {
			nameValue = new NameValue[0];
		}

		boolean find = false;
		for (NameValue elem : nameValue) {
			if (elem != null && name.equals(elem.name)) {
				find = true;
				break;
			}
		}

		NameValue[] result;
		if (find) {
			result = nameValue;

			for (NameValue elem : result) {
				if (elem != null && name.equals(elem.name)) {
					elem.value = value;
					break;
				}
			}
		} else {
			result = new NameValue[nameValue == null ? 0 : nameValue.length + 1];
			System.arraycopy(nameValue, 0, result, 0, nameValue.length);
			result[nameValue.length] = new NameValue(name, value);
		}

		return result;
	}

}
