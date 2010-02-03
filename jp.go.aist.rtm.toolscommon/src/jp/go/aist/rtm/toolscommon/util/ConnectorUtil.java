package jp.go.aist.rtm.toolscommon.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import jp.go.aist.rtm.toolscommon.model.component.InPort;
import jp.go.aist.rtm.toolscommon.model.component.OutPort;
import jp.go.aist.rtm.toolscommon.model.component.impl.PortImpl;

/**
 * �|�[�g�ԂŐڑ��\�ȃv���p�e�B���Ǘ����郆�[�e�B���e�B
 * 
 */
public class ConnectorUtil {

	/**
	 * ���[�̃|�[�g���Ƃ���Any�̃f�[�^�^����������Ԃ�
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static boolean isAllowAnyDataType(OutPort source, InPort target) {
		return source.isAllowAnyDataType() && target.isAllowAnyDataType();
	}

	/**
	 * ���[�̃|�[�g���Ƃ���Any�̃C���^�[�t�F�[�X�^����������Ԃ�
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static boolean isAllowAnyInterfaceType(OutPort source, InPort target) {
		return source.isAllowAnyInterfaceType()
				&& target.isAllowAnyInterfaceType();
	}

	/**
	 * ���[�̃|�[�g���Ƃ���Any�̃f�[�^�t���[�^����������Ԃ�
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static boolean isAllowAnyDataflowType(OutPort source, InPort target) {
		return source.isAllowAnyDataflowType()
				&& target.isAllowAnyDataflowType();
	}

	/**
	 * ���[�̃|�[�g���Ƃ���Any�̃T�u�X�N���v�V�����^����������Ԃ�
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static boolean isAllowAnySubscriptionType(OutPort source,
			InPort target) {
		return source.isAllowAnySubscriptionType()
				&& target.isAllowAnySubscriptionType();
	}

	/**
	 * �g�p�\�ȃf�[�^�^�̃��X�g��Ԃ�
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static List<String> getAllowDataTypes(OutPort source, InPort target) {
		return getAllowList(source.getDataTypes(), target.getDataTypes());
	}

	/**
	 * �g�p�\�ȃC���^�[�t�F�[�X�^�̃��X�g��Ԃ�
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static List<String> getAllowInterfaceTypes(OutPort source,
			InPort target) {
		return getAllowList(source.getInterfaceTypes(), target
				.getInterfaceTypes());
	}

	/**
	 * �g�p�\�ȃf�[�^�t���[�^�̃��X�g��Ԃ�
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static List<String> getAllowDataflowTypes(OutPort source,
			InPort target) {
		return getAllowList(source.getDataflowTypes(), target
				.getDataflowTypes(), false);
	}

	/**
	 * �g�p�\�ȃT�u�X�N���v�V�����^�̃��X�g��Ԃ�
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static List<String> getAllowSubscriptionTypes(OutPort source,
			InPort target) {
		return getAllowList(source.getSubscriptionTypes(), target
				.getSubscriptionTypes());
	}

	private static List<String> getAllowList(List<String> one, List<String> two) {
		return getAllowList(one, two, true);
	}
	/**
	 * 2�̕�����̃��X�g���󂯎��A�����ɑ��݂��镶���񂾂��̃��X�g���쐬����B �uAny�v���܂܂��ꍇ�ɂ́A����悷�ׂĂ̕�����������B
	 * �Ԃ�l�̃��X�g�ɁuAny�v���̂͊܂܂�Ȃ����Ƃɒ��ӂ��邱�ƁB
	 * <p>
	 * �������Case�𖳎����Ĕ�r���s����B<br>
	 * Case���Ⴄ������̏ꍇ�A���ʂ̃��X�g�Ɋ܂܂��̂�1�Ԗڂ̈����̕�����Ƃ��Ȃ�Case�ƂȂ�B<br>
	 * ���Ԃ́Aone�̏o�����̌�ɁAtwo�̏o�����ione��any�̏ꍇ�̂݁j�ŕ\�������B
	 * 
	 * @param one
	 * @param two
	 * @return
	 */
	private static List<String> getAllowList(List<String> one, List<String> two, boolean sorting) {
		boolean isAllowAny_One = PortImpl.isExistAny(one);
		boolean isAllowAny_Two = PortImpl.isExistAny(two);

		List<String> result = new ArrayList<String>();
		for (String elem1 : one) {
			if (PortImpl.isAnyString(elem1) == false) {
				boolean isEqualsIgnoreCase = false;
				for (String elem2 : two) {
					if (isAllowAny_Two || elem1.equalsIgnoreCase(elem2)) {
						isEqualsIgnoreCase = true;
						break;
					}
				}

				if (isEqualsIgnoreCase) {
					result.add(elem1);
				}
			}
		}
		if (isAllowAny_One) {
			for (String elem1 : two) {
				if (PortImpl.isAnyString(elem1) == false) {
					boolean isEqualsIgnoreCase = false;
					for (String elem2 : result) {
						if (elem1.equalsIgnoreCase(elem2)) {
							isEqualsIgnoreCase = true;
							break;
						}
					}

					if (isEqualsIgnoreCase == false) {
						result.add(elem1);
					}
				}
			}
		}
		for (Iterator<String> iter = result.iterator(); iter.hasNext();) {
			String elem = iter.next();
			if (PortImpl.isAnyString(elem)) {
				iter.remove();
			}
		}

		if(sorting) {
			// ���X�g�𕶎��񏇂Ń\�[�g
			result = sortTypes(result);
		}

		return result;
	}

	public static List<String> sortTypes(List<String> list) {
		return sortTypes(list, false);
	}

	public static List<String> sortTypes(List<String> list,
			final boolean reverse) {
		Collections.sort(list, new Comparator<String>() {
			public int compare(String a, String b) {
				return a.compareTo(b) * (reverse ? -1 : 1);
			}
		});
		return list;
	}

	public static String join(List<String> list, String d) {
		String result = "";
		for (String s : list) {
			result += (result.isEmpty() ? "" : d) + s;
		}
		return result;
	}

}
