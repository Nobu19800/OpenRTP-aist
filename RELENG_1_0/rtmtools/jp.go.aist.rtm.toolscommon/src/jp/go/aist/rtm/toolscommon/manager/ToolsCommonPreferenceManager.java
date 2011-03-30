package jp.go.aist.rtm.toolscommon.manager;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.jface.preference.IPreferenceStore;

import jp.go.aist.rtm.toolscommon.ToolsCommonPlugin;

/**
 * 設定を管理するマネージャ
 * <p>
 * 設定情報にアクセスするにはこのインスタンスを使用する
 */
public class ToolsCommonPreferenceManager {
	private static ToolsCommonPreferenceManager __instance = new ToolsCommonPreferenceManager();

	/**
	 * タイムアウト判定時間
	 */
	public static final String DEFAULT_TIMEOUT_PERIOD = ToolsCommonPreferenceManager.class
			.getName()
			+ "DEFAULT_TIMEOUT_PERIOD";

	/** ��Ԓʒm�I�u�U�[�o�̗L��/�����ݒ� */
	public static final String KEY_STATUS_OBSERVER_HB_ENABLE = ToolsCommonPreferenceManager.class
			.getName()
			+ ".STATUS_OBSERVER_HB_ENABLE";

	/** ��Ԓʒm�I�u�U�[�o�� H.B��M�Ԋu [sec] */
	public static final String KEY_STATUS_OBSERVER_HB_INTERVAL = ToolsCommonPreferenceManager.class
			.getName()
			+ ".STATUS_OBSERVER_HB_INTERVAL";
	/** ��Ԓʒm�I�u�U�[�o�̃^�C���A�E�g����� */
	public static final String KEY_STATUS_OBSERVER_HB_TRYCOUNT = ToolsCommonPreferenceManager.class
			.getName()
			+ ".STATUS_OBSERVER_HB_TRYCOUNT";

	/**
	 * コンストラクタ
	 * 
	 * @return シングルトン
	 */
	public static ToolsCommonPreferenceManager getInstance() {
		return __instance;
	}

	protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);
	IPreferenceStore store;

	public ToolsCommonPreferenceManager() {
		this.propertyChangeSupport = new PropertyChangeSupport(this);
		this.store = ToolsCommonPlugin.getDefault().getPreferenceStore();
	}

	/**
	 * デフォルトタイムアウト判定時間
	 */
	public static final int defaultTimeoutPeriod = 1000;

	/** ��Ԓʒm�I�u�U�[�o (�f�t�H���g �L��) */
	public static final Boolean DEFAULT_STATUS_OBSERVER_HB_ENABLE = true;

	/** ��Ԓʒm�I�u�U�[�o�� H.B��M�Ԋu (�f�t�H���g 1.0[sec]) */
	public static final Double DEFAULT_STATUS_OBSERVER_HB_INTERVAL = 1.0;

	/** ��Ԓʒm�I�u�U�[�o�̃^�C���A�E�g����� (�f�t�H���g 3��) */
	public static final Integer DEFAULT_STATUS_OBSERVER_HB_TRYCOUNT = 3;

	/**
	 * デフォルトタイムアウト判定時間を取得する
	 * 
	 * @param key
	 *            キー
	 * @return デフォルトポート
	 */
	public int getDefaultTimeout(String key) {
		// コンソールから起動された場合には、pluginは存在しない為、nullとなる。
		if (ToolsCommonPlugin.getDefault() == null){
			return defaultTimeoutPeriod;
		}
		ToolsCommonPlugin.getDefault().getPreferenceStore().setDefault(key, -1);
		int result = ToolsCommonPlugin.getDefault().getPreferenceStore().getInt(key);
		if (result == -1) { // defaultvalue
			result = defaultTimeoutPeriod;
		}
		return result;
	}

	/**
	 * デフォルトタイムアウト判定時間を設定する
	 * 
	 * @param key
	 *            キー
	 * @param interval
	 *            間隔
	 */
	public void setDefaultTimeout(String key, int defaultTimeout) {
		int oldDefaultTimeout = getDefaultTimeout(key);
		ToolsCommonPlugin.getDefault().getPreferenceStore().setValue(key,
				defaultTimeout);
		propertyChangeSupport.firePropertyChange(key, oldDefaultTimeout,
				defaultTimeout);
	}

	// ��Ԓʒm�I�u�U�[�o�L��/���� (STATUS_OBSERVER_HB_ENABLE)

	/** STATUS_OBSERVER_HB_ENABLE �̎擾 */
	public final Boolean isSTATUS_OBSERVER_HB_ENABLE() {
		String key = KEY_STATUS_OBSERVER_HB_ENABLE;
		store.setDefault(key, DEFAULT_STATUS_OBSERVER_HB_ENABLE);
		return store.getBoolean(key);
	}

	/** STATUS_OBSERVER_HB_ENABLE �̐ݒ� */
	public void setSTATUS_OBSERVER_HB_ENABLE(Boolean value) {
		String key = KEY_STATUS_OBSERVER_HB_ENABLE;
		Boolean oldValue = isSTATUS_OBSERVER_HB_ENABLE();
		store.setValue(key, (value == null) ? false : value);
		Boolean newValue = isSTATUS_OBSERVER_HB_ENABLE();
		//
		propertyChangeSupport.firePropertyChange(key, oldValue, newValue);
	}

	/** STATUS_OBSERVER_HB_ENABLE �̕��� */
	public void resetSTATUS_OBSERVER_HB_ENABLE() {
		setSTATUS_OBSERVER_HB_ENABLE(DEFAULT_STATUS_OBSERVER_HB_ENABLE);
	}

	// ��Ԓʒm�I�u�U�[�o��M�Ԋu (STATUS_OBSERVER_HB_INTERVAL)

	/** STATUS_OBSERVER_HB_INTERVAL �̎擾 */
	public final Double getSTATUS_OBSERVER_HB_INTERVAL() {
		String key = KEY_STATUS_OBSERVER_HB_INTERVAL;
		store.setDefault(key, DEFAULT_STATUS_OBSERVER_HB_INTERVAL);
		return store.getDouble(key);
	}

	/** STATUS_OBSERVER_HB_INTERVAL �̐ݒ� */
	public void setSTATUS_OBSERVER_HB_INTERVAL(Double value) {
		String key = KEY_STATUS_OBSERVER_HB_INTERVAL;
		Double oldValue = getSTATUS_OBSERVER_HB_INTERVAL();
		store.setValue(key,
				(value == null) ? DEFAULT_STATUS_OBSERVER_HB_INTERVAL : value);
		Double newValue = getSTATUS_OBSERVER_HB_INTERVAL();
		//
		propertyChangeSupport.firePropertyChange(key, oldValue, newValue);
	}

	/** STATUS_OBSERVER_HB_INTERVAL �̕��� */
	public void resetSTATUS_OBSERVER_HB_INTERVAL() {
		setSTATUS_OBSERVER_HB_INTERVAL(DEFAULT_STATUS_OBSERVER_HB_INTERVAL);
	}

	// ��Ԓʒm�I�u�U�[�o��M�� (STATUS_OBSERVER_HB_TRYCOUNT)

	/** STATUS_OBSERVER_HB_TRYCOUNT �̎擾 */
	public final Integer getSTATUS_OBSERVER_HB_TRYCOUNT() {
		String key = KEY_STATUS_OBSERVER_HB_TRYCOUNT;
		store.setDefault(key, DEFAULT_STATUS_OBSERVER_HB_TRYCOUNT);
		return store.getInt(key);
	}

	/** STATUS_OBSERVER_HB_TRYCOUNT �̐ݒ� */
	public void setSTATUS_OBSERVER_HB_TRYCOUNT(Integer value) {
		String key = KEY_STATUS_OBSERVER_HB_TRYCOUNT;
		Integer oldValue = getSTATUS_OBSERVER_HB_TRYCOUNT();
		store.setValue(key,
				(value == null) ? DEFAULT_STATUS_OBSERVER_HB_TRYCOUNT : value);
		Integer newValue = getSTATUS_OBSERVER_HB_TRYCOUNT();
		//
		propertyChangeSupport.firePropertyChange(key, oldValue, newValue);
	}

	/** STATUS_OBSERVER_HB_TRYCOUNT �̕��� */
	public void resetSTATUS_OBSERVER_HB_TRYCOUNT() {
		setSTATUS_OBSERVER_HB_TRYCOUNT(DEFAULT_STATUS_OBSERVER_HB_TRYCOUNT);
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
