
package org.openrtp.namespaces.rtc.version03;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>actions complex type��Java�N���X�B
 * 
 * <p>���̃X�L�[�}�E�t���O�����g�́A���̃N���X���Ɋ܂܂��\�������R���e���c���w�肵�܂��B
 * 
 * <pre>
 * &lt;complexType name="actions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OnInitialize" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnFinalize" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnStartup" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnShutdown" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnActivated" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnDeactivated" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnAborting" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnError" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnReset" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnExecute" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnStateUpdate" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnRateChanged" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnAction" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnModeChanged" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "actions", propOrder = {
    "onInitialize",
    "onFinalize",
    "onStartup",
    "onShutdown",
    "onActivated",
    "onDeactivated",
    "onAborting",
    "onError",
    "onReset",
    "onExecute",
    "onStateUpdate",
    "onRateChanged",
    "onAction",
    "onModeChanged"
})
public class Actions {

    @XmlElement(name = "OnInitialize")
    protected ActionStatus onInitialize;
    @XmlElement(name = "OnFinalize")
    protected ActionStatus onFinalize;
    @XmlElement(name = "OnStartup")
    protected ActionStatus onStartup;
    @XmlElement(name = "OnShutdown")
    protected ActionStatus onShutdown;
    @XmlElement(name = "OnActivated")
    protected ActionStatus onActivated;
    @XmlElement(name = "OnDeactivated")
    protected ActionStatus onDeactivated;
    @XmlElement(name = "OnAborting")
    protected ActionStatus onAborting;
    @XmlElement(name = "OnError")
    protected ActionStatus onError;
    @XmlElement(name = "OnReset")
    protected ActionStatus onReset;
    @XmlElement(name = "OnExecute")
    protected ActionStatus onExecute;
    @XmlElement(name = "OnStateUpdate")
    protected ActionStatus onStateUpdate;
    @XmlElement(name = "OnRateChanged")
    protected ActionStatus onRateChanged;
    @XmlElement(name = "OnAction")
    protected ActionStatus onAction;
    @XmlElement(name = "OnModeChanged")
    protected ActionStatus onModeChanged;

    /**
     * onInitialize�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnInitialize() {
        return onInitialize;
    }

    /**
     * onInitialize�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnInitialize(ActionStatus value) {
        this.onInitialize = value;
    }

    /**
     * onFinalize�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnFinalize() {
        return onFinalize;
    }

    /**
     * onFinalize�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnFinalize(ActionStatus value) {
        this.onFinalize = value;
    }

    /**
     * onStartup�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnStartup() {
        return onStartup;
    }

    /**
     * onStartup�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnStartup(ActionStatus value) {
        this.onStartup = value;
    }

    /**
     * onShutdown�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnShutdown() {
        return onShutdown;
    }

    /**
     * onShutdown�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnShutdown(ActionStatus value) {
        this.onShutdown = value;
    }

    /**
     * onActivated�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnActivated() {
        return onActivated;
    }

    /**
     * onActivated�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnActivated(ActionStatus value) {
        this.onActivated = value;
    }

    /**
     * onDeactivated�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnDeactivated() {
        return onDeactivated;
    }

    /**
     * onDeactivated�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnDeactivated(ActionStatus value) {
        this.onDeactivated = value;
    }

    /**
     * onAborting�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnAborting() {
        return onAborting;
    }

    /**
     * onAborting�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnAborting(ActionStatus value) {
        this.onAborting = value;
    }

    /**
     * onError�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnError() {
        return onError;
    }

    /**
     * onError�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnError(ActionStatus value) {
        this.onError = value;
    }

    /**
     * onReset�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnReset() {
        return onReset;
    }

    /**
     * onReset�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnReset(ActionStatus value) {
        this.onReset = value;
    }

    /**
     * onExecute�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnExecute() {
        return onExecute;
    }

    /**
     * onExecute�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnExecute(ActionStatus value) {
        this.onExecute = value;
    }

    /**
     * onStateUpdate�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnStateUpdate() {
        return onStateUpdate;
    }

    /**
     * onStateUpdate�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnStateUpdate(ActionStatus value) {
        this.onStateUpdate = value;
    }

    /**
     * onRateChanged�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnRateChanged() {
        return onRateChanged;
    }

    /**
     * onRateChanged�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnRateChanged(ActionStatus value) {
        this.onRateChanged = value;
    }

    /**
     * onAction�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnAction() {
        return onAction;
    }

    /**
     * onAction�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnAction(ActionStatus value) {
        this.onAction = value;
    }

    /**
     * onModeChanged�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnModeChanged() {
        return onModeChanged;
    }

    /**
     * onModeChanged�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnModeChanged(ActionStatus value) {
        this.onModeChanged = value;
    }

}
