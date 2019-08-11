
package org.openrtp.namespaces.rtc.version03;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>basic_info complex type��Java�N���X�B
 * 
 * <p>���̃X�L�[�}�E�t���O�����g�́A���̃N���X���Ɋ܂܂��\�������R���e���c���w�肵�܂��B
 * 
 * <pre>
 * &lt;complexType name="basic_info">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="componentType" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="STATIC"/>
 *             &lt;enumeration value="UNIQUE"/>
 *             &lt;enumeration value="COMMUTATIVE"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="activityType" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="PERIODIC"/>
 *             &lt;enumeration value="SPORADIC"/>
 *             &lt;enumeration value="EVENTDRIVEN"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="componentKind" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="rtcType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="category" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="executionRate" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="executionType" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="maxInstances" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="vendor" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="abstract" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="hardwareProfile" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="creationDate" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="updateDate" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "basic_info")
@XmlSeeAlso({
    BasicInfoDoc.class
})
public class BasicInfo {

    @XmlAttribute(name = "name", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    protected String name;
    @XmlAttribute(name = "componentType", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    protected String componentType;
    @XmlAttribute(name = "activityType", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    protected String activityType;
    @XmlAttribute(name = "componentKind", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    protected String componentKind;
    @XmlAttribute(name = "rtcType", namespace = "http://www.openrtp.org/namespaces/rtc")
    protected String rtcType;
    @XmlAttribute(name = "category", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    protected String category;
    @XmlAttribute(name = "description", namespace = "http://www.openrtp.org/namespaces/rtc")
    protected String description;
    @XmlAttribute(name = "executionRate", namespace = "http://www.openrtp.org/namespaces/rtc")
    protected Double executionRate;
    @XmlAttribute(name = "executionType", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    protected String executionType;
    @XmlAttribute(name = "maxInstances", namespace = "http://www.openrtp.org/namespaces/rtc")
    protected BigInteger maxInstances;
    @XmlAttribute(name = "vendor", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    protected String vendor;
    @XmlAttribute(name = "version", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    protected String version;
    @XmlAttribute(name = "abstract", namespace = "http://www.openrtp.org/namespaces/rtc")
    protected String _abstract;
    @XmlAttribute(name = "hardwareProfile", namespace = "http://www.openrtp.org/namespaces/rtc")
    protected String hardwareProfile;
    @XmlAttribute(name = "creationDate", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creationDate;
    @XmlAttribute(name = "updateDate", namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateDate;

    /**
     * name�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * name�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * componentType�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComponentType() {
        return componentType;
    }

    /**
     * componentType�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComponentType(String value) {
        this.componentType = value;
    }

    /**
     * activityType�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivityType() {
        return activityType;
    }

    /**
     * activityType�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivityType(String value) {
        this.activityType = value;
    }

    /**
     * componentKind�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComponentKind() {
        return componentKind;
    }

    /**
     * componentKind�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComponentKind(String value) {
        this.componentKind = value;
    }

    /**
     * rtcType�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRtcType() {
        return rtcType;
    }

    /**
     * rtcType�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRtcType(String value) {
        this.rtcType = value;
    }

    /**
     * category�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * category�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

    /**
     * description�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * description�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * executionRate�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getExecutionRate() {
        return executionRate;
    }

    /**
     * executionRate�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setExecutionRate(Double value) {
        this.executionRate = value;
    }

    /**
     * executionType�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExecutionType() {
        return executionType;
    }

    /**
     * executionType�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExecutionType(String value) {
        this.executionType = value;
    }

    /**
     * maxInstances�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxInstances() {
        return maxInstances;
    }

    /**
     * maxInstances�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxInstances(BigInteger value) {
        this.maxInstances = value;
    }

    /**
     * vendor�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * vendor�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVendor(String value) {
        this.vendor = value;
    }

    /**
     * version�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * version�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * abstract�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbstract() {
        return _abstract;
    }

    /**
     * abstract�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbstract(String value) {
        this._abstract = value;
    }

    /**
     * hardwareProfile�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHardwareProfile() {
        return hardwareProfile;
    }

    /**
     * hardwareProfile�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHardwareProfile(String value) {
        this.hardwareProfile = value;
    }

    /**
     * creationDate�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreationDate() {
        return creationDate;
    }

    /**
     * creationDate�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreationDate(XMLGregorianCalendar value) {
        this.creationDate = value;
    }

    /**
     * updateDate�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdateDate() {
        return updateDate;
    }

    /**
     * updateDate�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdateDate(XMLGregorianCalendar value) {
        this.updateDate = value;
    }

}
