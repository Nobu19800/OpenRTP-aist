package jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jp.go.aist.rtm.systemeditor.nl.Messages;

/**
 * �R���t�B�O�f�[�^�̐������
 *
 */
public class ConfigurationCondition {

	/** �s���Ȑ��l�̗�O */
	public static class NumberException extends Exception {
		private static final long serialVersionUID = -7918405683075295181L;

		public NumberException(String m) {
			super(m);
		}

		public NumberException(String m, Exception e) {
			super(m, e);
		}
	};

	/** �s���Ȍ`���̗�O */
	public static class FormatException extends Exception {
		private static final long serialVersionUID = 2976559830653469461L;

		public FormatException(String m) {
			super(m);
		}

		public FormatException(String m, Exception e) {
			super(m, e);
		}
	};

	/** ������I�u�W�F�N�g(Null�I�u�W�F�N�g) */
	public static final ConfigurationCondition NULL_CONDITION = new ConfigurationCondition(
			true);

	/** ������̏ꍇ��true */
	boolean isNull = false;

	/** ���l�ݒ�̒l */
	String constValue;

	/** ����l */
	String max;

	/** ����l���܂ޏꍇ��true */
	boolean maxEquals = false;

	/** �����l */
	String min;

	/** �����l���܂ޏꍇ��true */
	boolean minEquals = false;

	/** �l�̐��x (digits=3 -> 0.001) */
	int digits = -1;

	/** �񋓌^�̃��X�g */
	List<String> enumList = null;

	/** �z��^�����̃��X�g */
	List<ConfigurationCondition> conditionList = null;

	/** �n�b�V���^�����̃}�b�v */
	Map<String, ConfigurationCondition> conditionMap = null;

	/**
	 * ���������������p�[�X���āA�����̃��X�g�𐶐����܂��B
	 * @param s �������������
	 * @return ConfigurationCondition�I�u�W�F�N�g
	 * @throws NumberException ���l�t�H�[�}�b�g�G���[
	 * @throws FormatException �������t�H�[�}�b�g�G���[
	 */
	public static ConfigurationCondition parse(String s)
			throws NumberException, FormatException {
		ConfigurationCondition result = NULL_CONDITION;
		if (s == null) {
			return result;
		}
		s = s.trim();
		if (s.equals("")) { //$NON-NLS-1$
			return result;
		}
		boolean isEnum = false;
		if (s.charAt(0) == '(') {
			int index = s.indexOf(")", 1); //$NON-NLS-1$
			if (index < s.length()) {
				index = skipNextSpace(index, s);
			}
			if (index == s.length() - 1) {
				isEnum = true;
			}
		}
		if (isEnum) {
			// �񋓌^
			ConfigurationCondition cc = parseEnum(s);
			if (cc != null)
				result = cc;
		} else if (s.charAt(0) == '{') {
			// �n�b�V���^
			ConfigurationCondition cc = parseHash(s);
			if (cc != null)
				result = cc;
		} else if (s.split(",").length > 1) { //$NON-NLS-1$
			// �z��^
			ConfigurationCondition cc = parseArray(s);
			if (cc != null)
				result = cc;
		} else {
			ConfigurationCondition cc = parseX(s);
			if (cc != null)
				result = cc;
		}
		return result;
	}


	/**
	 * �͈͐ݒ�̃p�[�X���s���܂��B(�� 0<x<100, 100(���l))
	 * @param s �������������
	 * @return ConfigurationCondition�̃I�u�W�F�N�g
	 * @throws NumberException ���l�t�H�[�}�b�g�G���[
	 * @throws FormatException �������t�H�[�}�b�g�G���[
	 */
	public static ConfigurationCondition parseX(String s)
			throws NumberException, FormatException {
		ConfigurationCondition result = new ConfigurationCondition();

		if (s.indexOf('x') == -1) {
			try {
				Double.valueOf(s);
				result.constValue = s;
				return result;
			} catch (NumberFormatException e) {
				throw new NumberException(Messages.getString("ConfigurationCondition.3") + s, e); //$NON-NLS-1$
			}
		}
		s = s.trim();

		String lPart = s.substring(0, s.indexOf("x")).trim(); //$NON-NLS-1$
		String rPart = s.substring(s.indexOf("x") + 1).trim(); //$NON-NLS-1$
		String value = null;
		char sign = '\0';

		if (lPart.length() > 0) {
			int index = lPart.length() - 1;
			value = null;
			try {
				boolean eq = false;
				if (lPart.charAt(index) == '=') {
					eq = true;
					index = skipPrevSpace(index - 1, lPart);
				}
				sign = lPart.charAt(index);
				if (sign == '<' || sign == '>') {
					index = skipPrevSpace(index - 1, lPart);
					if (index < 0) {
						throw new FormatException(Messages.getString("ConfigurationCondition.6") + lPart); //$NON-NLS-1$
					}
					value = lPart.substring(0, index + 1);
					Double.valueOf(value);
					if (sign == '<') {
						result.minEquals = eq;
						result.min = value;
					} else {
						result.maxEquals = eq;
						result.max = value;
					}
				} else {
					throw new FormatException(Messages.getString("ConfigurationCondition.7") + lPart); //$NON-NLS-1$
				}
			} catch (NumberFormatException e) {
				throw new NumberException(Messages.getString("ConfigurationCondition.8") + value, e); //$NON-NLS-1$
			}
		}
		if (rPart.length() > 0) {
			int index = skipNextSpace(0, rPart);
			value = null;
			try {
				if (sign != '\0' && sign != rPart.charAt(index)) {
					throw new FormatException(Messages.getString("ConfigurationCondition.9") + lPart //$NON-NLS-1$
							+ Messages.getString("ConfigurationCondition.10") + rPart); //$NON-NLS-1$
				}
				sign = rPart.charAt(index);
				if (sign == '<' || sign == '>') {
					index = skipNextSpace(index + 1, rPart);
					if (index >= rPart.length()) {
						throw new FormatException(Messages.getString("ConfigurationCondition.11") + rPart); //$NON-NLS-1$
					}
					boolean eq = false;
					if (rPart.charAt(index) == '=') {
						eq = true;
						index = skipNextSpace(index + 1, rPart);
					}
					value = rPart.substring(index);
					Double.valueOf(value);
					if (sign == '<') {
						result.max = value;
						result.maxEquals = eq;
					} else {
						result.min = value;
						result.minEquals = eq;
					}
				} else {
					throw new FormatException(Messages.getString("ConfigurationCondition.12") + rPart); //$NON-NLS-1$
				}
			} catch (NumberFormatException e) {
				throw new NumberException(Messages.getString("ConfigurationCondition.13") + value, e); //$NON-NLS-1$
			}
		}
		if (result.min != null && result.max != null) {
			if (Double.valueOf(result.min) > Double.valueOf(result.max)) {
				throw new NumberException(Messages.getString("ConfigurationCondition.14") + result.min //$NON-NLS-1$
						+ Messages.getString("ConfigurationCondition.15") + result.max); //$NON-NLS-1$
			}
		}
		return result;
	}

	/**
	 * �񋓌^�̃p�[�X���s���܂��B(�� (9200,9500,10000))
	 * @param s �������������
	 * @return ConfigurationCondition�̃I�u�W�F�N�g
	 * @throws FormatException �������t�H�[�}�b�g�G���[
	 */
	public static ConfigurationCondition parseEnum(String s) throws FormatException {
		ConfigurationCondition result = new ConfigurationCondition();
		s = s.trim();
		if (s.charAt(0) != '(' || s.charAt(s.length() - 1) != ')') {
			throw new FormatException(Messages.getString("ConfigurationCondition.16") + s); //$NON-NLS-1$
		}
		// (, ) ������
		s = s.substring(1, s.length() - 1).trim();
		result.enumList = new ArrayList<String>();
		for (String ss : s.split(",")) { //$NON-NLS-1$
			if (!result.enumList.contains(ss.trim())) {
				result.enumList.add(ss.trim());
			}
		}
		return result;
	}

	/**
	 * �z��^�����̃p�[�X���s���܂��B(�� 0<x<1, 2<x<=5)
	 * @param s �������������
	 * @return ConfigurationCondition�̃I�u�W�F�N�g
	 * @throws NumberException ���l�t�H�[�}�b�g�G���[
	 * @throws FormatException �������t�H�[�}�b�g�G���[
	 */
	public static ConfigurationCondition parseArray(String s)
			throws NumberException, FormatException {
		ConfigurationCondition result = new ConfigurationCondition();
		result.conditionList = new ArrayList<ConfigurationCondition>();
		s = s.trim();
		int start = 0, end = 0;
		while (start <= s.length()) {
			if (start == s.length()) {
				result.conditionList.add(NULL_CONDITION);
				break;
			}
			if (s.charAt(start) == '(') {
				end = s.indexOf(")", start); //$NON-NLS-1$
				if (end != -1) {
					end = s.indexOf(",", end); //$NON-NLS-1$
				}
			} else {
				end = s.indexOf(",", start); //$NON-NLS-1$
			}
			if (end == -1) {
				end = s.length();
			}
			String p = s.substring(start, end).trim();
			if (p.length() == 0) {
				result.conditionList.add(NULL_CONDITION);
				start = skipNextSpace(end + 1, s);
				continue;
			}
			try {
				ConfigurationCondition cc;
				if (p.charAt(0) == '(') {
					// �񋓌^
					cc = parseEnum(p);
				} else {
					cc = parseX(p);
				}
				result.conditionList.add((cc != null) ? cc : NULL_CONDITION);
			} catch (NumberException e) {
				result.conditionList.add(NULL_CONDITION);
			} catch (FormatException e) {
				result.conditionList.add(NULL_CONDITION);
			}

			start = skipNextSpace(end + 1, s);
		}
		return result;
	}

	/**
	 * �n�b�V���^�����̃p�[�X���s���܂��B(�� {key0: 0<x<1, key1: 2<x<=5} )
	 * @param s �������������
	 * @return ConfigurationCondition�̃I�u�W�F�N�g
	 * @throws NumberException ���l�t�H�[�}�b�g�G���[
	 * @throws FormatException �������t�H�[�}�b�g�G���[
	 */
	public static ConfigurationCondition parseHash(String s)
			throws NumberException, FormatException {
		ConfigurationCondition result = new ConfigurationCondition();
		result.conditionMap = new HashMap<String, ConfigurationCondition>();
		s = s.trim();
		if (s.charAt(0) != '{' || s.charAt(s.length() - 1) != '}') {
			throw new FormatException(Messages.getString("ConfigurationCondition.21") + s); //$NON-NLS-1$
		}
		// { }����
		s = s.substring(1, s.length() - 1).trim();

		int start = 0, end = 0;
		String key;
		while (start <= s.length()) {
			end = s.indexOf(":", start); //$NON-NLS-1$
			if (end == -1) {
				// �L�[�Ȃ�
				break;
			}
			key = s.substring(start, end).trim();
			if (key.length() == 0) {
				throw new FormatException(Messages.getString("ConfigurationCondition.23") + key); //$NON-NLS-1$
			}

			start = skipNextSpace(end + 1, s);
			if (start == s.length()) {
				result.conditionMap.put(key, NULL_CONDITION);
				break;
			}
			if (s.charAt(start) == '(') {
				end = s.indexOf(")", start); //$NON-NLS-1$
				if (end != -1) {
					end = s.indexOf(",", end); //$NON-NLS-1$
				}
			} else {
				end = s.indexOf(",", start); //$NON-NLS-1$
			}
			if (end == -1) {
				end = s.length();
			}
			String p = s.substring(start, end).trim();
			if (p.length() == 0) {
				result.conditionMap.put(key, NULL_CONDITION);
				start = skipNextSpace(end + 1, s);
				continue;
			}
			try {
				ConfigurationCondition cc;
				if (p.charAt(0) == '(') {
					// �񋓌^
					cc = parseEnum(p);
				} else {
					cc = parseX(p);
				}
				result.conditionMap
						.put(key, (cc != null) ? cc : NULL_CONDITION);
			} catch (NumberException e) {
				result.conditionMap.put(key, NULL_CONDITION);
			} catch (FormatException e) {
				result.conditionMap.put(key, NULL_CONDITION);
			}

			start = skipNextSpace(end + 1, s);
		}
		return result;
	}


	ConfigurationCondition() {
	}

	ConfigurationCondition(boolean isNull) {
		this.isNull = isNull;
	}

	/**
	 * ������𔻒肵�܂��B
	 * @return ������̏ꍇ��true
	 */
	public boolean isNull() {
		return this.isNull;
	}

	public String getConstValue() {
		return this.constValue;
	}

	public boolean hasConstValue() {
		return getConstValue() != null;
	}

	public String getMax() {
		return this.max;
	}

	public Double getMaxValue() {
		return Double.valueOf(this.max);
	}

	public boolean isMaxEquals() {
		return this.maxEquals;
	}

	public String getMin() {
		return this.min;
	}

	public Double getMinValue() {
		return Double.valueOf(this.min);
	}

	public boolean isMinEquals() {
		return this.minEquals;
	}

	public boolean hasEnumList() {
		return this.enumList != null;
	}

	public List<String> getEnumList() {
		return this.enumList;
	}

	/**
	 * �ő�l�̐��x�𐮐��ɕϊ����܂� (10.0 -> 100)
	 * @return �ő�l(����)
	 */
	public Integer getMaxByInteger() {
		return this.getIntegerByDigits(this.getMaxValue().doubleValue());
	}

	/**
	 * �ŏ��l�̐��x�𐮐��ɕϊ����܂� (10.0 -> 100)
	 * @return �ŏ��l(����)
	 */
	public Integer getMinByInteger() {
		return this.getIntegerByDigits(this.getMinValue().doubleValue());
	}

	/**
	 * ������digits�����炵�Đ����ɕϊ����܂� (digits=2 10 -> 1000)
	 * @param dvalue �����l
	 * @return �����l
	 */
	public Integer getIntegerByDigits(double dvalue) {
		dvalue *= Math.pow(10.0, this.getDigits());
		return new Integer((int) dvalue);
	}

	/**
	 * ������digits���̏����ɕϊ����܂� (digits=2 1000 -> 10.0)
	 * @param ivalue �����l
	 * @return �����l
	 */
	public Double getDecimalByDigits(int ivalue) {
		Double d = Double.valueOf((double) ivalue);
		d /= Math.pow(10.0, this.getDigits());
		return d;
	}

	/**
	 * min�Amax�̐ݒ�l���A���x�̍�����(�������̑傫����)�̌������擾���܂�
	 * @return ���x(������)
	 */
	public int getDigits() {
		if (this.digits != -1)
			return this.digits;
		int i = 0;
		int d = 0;
		if (this.min != null) {
			i = this.min.indexOf("."); //$NON-NLS-1$
			if (i != -1) {
				d = this.min.length() - i - 1;
			}
		}
		this.digits = d;
		d = 0;
		if (this.max != null) {
			i = this.max.indexOf("."); //$NON-NLS-1$
			if (i != -1) {
				d = this.max.length() - i - 1;
			}
		}
		if (this.digits < d)
			this.digits = d;
		return this.digits;
	}

	/**
	 * value��min�`max����maxStep�i�K�ŃX�e�b�v�ʒu�ɕϊ����܂�
	 * @param value �l
	 * @param widget Widget�I�u�W�F�N�g
	 * @return ���Z�����X�e�b�v�ʒu
	 */
	public int getStepByValue(String value, ConfigurationWidget widget) {
		if (this.min == null || this.max == null)
			return 0;
		Double dvalue;
		try {
			dvalue = Double.valueOf(value);
		} catch (NumberFormatException e) {
			return 0;
		}
		Double dmin = Double.valueOf(this.min);
		Double dmax = Double.valueOf(this.max);
		if (dvalue < dmin)
			return 0;
		if (dvalue > dmax)
			return widget.getSliderMaxStep();
		Double step = (dmax - dmin) / (double) widget.getSliderMaxStep();
//		int result = (int) ((dvalue - dmin) / step );
		int result = (int) ((dvalue - dmin) / step + 0.5);
		return result;
	}

	/**
	 * maxStep����step��min�`max���̒l�ɕϊ����܂�
	 * @param step �X�e�b�v�ʒu
	 * @param widget Widget�I�u�W�F�N�g
	 * @param previousValue 
	 * @return ���Z�����l
	 */
	public String getValueByStep(int step, ConfigurationWidget widget,
			String previousValue) {
		if (this.min == null || this.max == null)
			return "0"; //$NON-NLS-1$
		if (step < 0) {
			step = 0;
		}
		if (step > widget.getSliderMaxStep()) {
			step = widget.getSliderMaxStep();
		}
		double dmin = Double.valueOf(this.min);
		double dmax = Double.valueOf(this.max);
		double dprev = Double.valueOf(previousValue);
		double dvalue = dmin + (widget.getSliderStep() * (double) step);
		dvalue = (dvalue > dmax) ? dmax : dvalue;
		if (isInt()) {
			if (dvalue > dprev && dvalue < dprev + 1) {
				dvalue = dprev + 1;
			} else if (dvalue < dprev && dvalue > dprev - 1) {
				dvalue = dprev - 1;
			}
			return Integer.toString((int) dvalue);
		} else {
			return Double.toString(new BigDecimal(dvalue).setScale(getDigits(),
					BigDecimal.ROUND_HALF_EVEN).doubleValue());
		}
	}

	private boolean isInt() {
		if (this.min.indexOf(".") != -1 || this.max.indexOf(".") != -1) { //$NON-NLS-1$ //$NON-NLS-2$
			// �ő�l�A�ŏ��l�̂����ꂩ�������̏ꍇ�͏�������
			return false;
		}
		return true;
	}

	/**
	 * �ŏ�/�ő�l�𒴂���l��L���͈͓��Ɋۂ߂�
	 * @param value ���͒l
	 * @return �ŏ�/�ő�͈͓̔��̗L���Ȓl
	 */
	public String adjustMinMaxValue(String value) {
		if (validate(value)) {
			return value;
		}
		double dmin = Double.valueOf(this.min);
		double dmax = Double.valueOf(this.max);
		double dvalue = Double.valueOf(value);
		double d = 1.0 / Math.pow(10.0, this.getDigits());
		if (dvalue >= dmax) {
			dvalue = dmax;
			if (this.maxEquals) {
				return this.max;
			}
			if (isInt()) {
				return Integer.toString((int) (dvalue - 1.0));
			}
			return Double.toString(dvalue - d);
		} else if (dvalue <= dmin) {
			dvalue = dmin;
			if (this.minEquals) {
				return this.min;
			}
			if (isInt()) {
				return Integer.toString((int) (dvalue + 1.0));
			}
			return Double.toString(dvalue + d);
		}
		return value;
	}

	/**
	 * �����������
	 * @param value ����Ώ�
	 * @return value����������𖞂�����true
	 */
	public boolean validate(String value) {
		if (this.isNull) {
			// ������̏ꍇ��true
			return true;
		}
		if (this.hasConstValue()) {
			// ���l�̏ꍇ
			try {
				Double dconst = Double.valueOf(this.getConstValue());
				Double dvalue = Double.valueOf(value);
				return dconst.doubleValue() == dvalue.doubleValue();
			} catch (NumberFormatException e) {
				return false;
			}
		} else if (this.hasEnumList()) {
			// �񋓌^�̏ꍇ
			for (String es : this.getEnumList()) {
				if (es.equals(value)) {
					return true;
				}
			}
			return false;
		} else {
			try {
				// �͈͎w��
				Double dvalue = Double.valueOf(value);
				boolean result = false;
				if (this.min != null) {
					Double dm = Double.valueOf(this.min);
					if (this.minEquals) {
						result = dvalue.doubleValue() >= dm.doubleValue();
					} else {
						result = dvalue.doubleValue() > dm.doubleValue();
					}
					if (!result)
						return false;
				}
				if (this.max != null) {
					Double dm = Double.valueOf(this.max);
					if (this.maxEquals) {
						result = dvalue.doubleValue() <= dm.doubleValue();
					} else {
						result = dvalue.doubleValue() < dm.doubleValue();
					}
					if (!result)
						return false;
				}
				return result;
			} catch (NumberFormatException e) {
				return false;
			}
		}
	}


	/**
	 * �z������𔻒肵�܂��B
	 * @return �z������̏ꍇ��true
	 */
	public boolean isArrayCondition() {
		return (this.conditionList != null && this.conditionList.size() > 0);
	}

	/**
	 * �z������̗v�f����Ԃ��܂��B
	 * @return �v�f��
	 */
	public int getArraySize() {
		if (!this.isArrayCondition()) {
			return 0;
		}
		return this.conditionList.size();
	}

	/**
	 * index�Ԗڂ̏������擾���܂��B
	 * @param index �����I�u�W�F�N�g�̃C���f�b�N�X
	 * @return index�Ԗڂ̏���
	 */
	public ConfigurationCondition getCondition(int index) {
		if (index >= this.getArraySize()) {
			return null;
		}
		return this.conditionList.get(index);
	}


	/**
	 * �n�b�V�������𔻒肵�܂��B
	 * @return �n�b�V�������̏ꍇ��true
	 */
	public boolean isHashCondition() {
		return (this.conditionMap != null && this.conditionMap.keySet().size() > 0);
	}

	/**
	 * �n�b�V�������̃L�[�Z�b�g��Ԃ��܂��B
	 * @return �n�b�V�������̃L�[�Z�b�g
	 */
	public Set<String> getHashKeySet() {
		if (!this.isHashCondition()) {
			return new HashSet<String>();
		}
		return this.conditionMap.keySet();
	}

	/**
	 * key���L�[�ɂ���������擾���܂��B
	 * @param key �����I�u�W�F�N�g�̃L�[
	 * @return key�ɑΉ��������
	 */
	public ConfigurationCondition getCondition(String key) {
		if (this.conditionMap == null) {
			return null;
		}
		return this.conditionMap.get(key);
	}


	@Override
	public ConfigurationCondition clone() {
		ConfigurationCondition result = new ConfigurationCondition();
		result.isNull = this.isNull;
		result.constValue = this.constValue;
		result.max = this.max;
		result.min = this.min;
		result.maxEquals = this.maxEquals;
		result.minEquals = this.minEquals;
		result.digits = this.digits;
		if (this.enumList != null) {
			result.enumList = new ArrayList<String>();
			for (String s : this.enumList) {
				result.enumList.add(s);
			}
		}
		if (this.conditionList != null) {
			result.conditionList = new ArrayList<ConfigurationCondition>();
			for (ConfigurationCondition cc : this.conditionList) {
				result.conditionList.add(cc.clone());
			}
		}
		if (this.conditionMap != null) {
			result.conditionMap = new HashMap<String, ConfigurationCondition>();
			for (String key : this.conditionMap.keySet()) {
				ConfigurationCondition cc = this.conditionMap.get(key);
				result.conditionMap.put(key, (cc.isNull ? NULL_CONDITION : cc));
			}
		}
		return result;
	}

	@Override
	public String toString() {
		if (this.isNull) {
			return "null"; //$NON-NLS-1$
		} else if (this.hasConstValue()) {
			return this.constValue;
		} else if (this.hasEnumList()) {
			String s = "("; //$NON-NLS-1$
			for (String ss : this.enumList) {
				if (!s.equals("(")) { //$NON-NLS-1$
					s += ","; //$NON-NLS-1$
				}
				s += ss;
			}
			return s + ")"; //$NON-NLS-1$
		} else if (this.min != null && this.max != null) {
			return this.min + "<" + (this.minEquals ? "=" : "") + "x<" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					+ (this.maxEquals ? "=" : "") + this.max; //$NON-NLS-1$ //$NON-NLS-2$
		} else if (this.min != null) {
			return "x>" + (this.minEquals ? "=" : "") + this.min; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		} else if (this.max != null) {
			return "x<" + (this.maxEquals ? "=" : "") + this.max; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		} else if (this.isArrayCondition()) {
			return this.conditionList.toString();
		} else if (this.isHashCondition()) {
			String s = "{"; //$NON-NLS-1$
			for (String key : this.conditionMap.keySet()) {
				if (!s.equals("{")) { //$NON-NLS-1$
					s += ", "; //$NON-NLS-1$
				}
				s += key + ":" + this.conditionMap.get(key); //$NON-NLS-1$
			}
			return s + "}"; //$NON-NLS-1$
		}
		return ""; //$NON-NLS-1$
	}

	static int skipNextSpace(int index, String s) {
		if (index < 0 || index >= s.length())
			return index;
		while (s.charAt(index) == ' ')
			index++;
		return index;
	}

	static int skipPrevSpace(int index, String s) {
		if (index < 0 || index >= s.length())
			return index;
		while (s.charAt(index) == ' ')
			index--;
		return index;
	}
}
