
package org.openrtp.namespaces.rtc.version03;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>doc_serviceinterface complex type��Java�N���X�B
 * 
 * <p>���̃X�L�[�}�E�t���O�����g�́A���̃N���X���Ɋ܂܂��\�������R���e���c���w�肵�܂��B
 * 
 * <pre>
 * &lt;complexType name="doc_serviceinterface">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="docArgument" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="docReturn" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="docException" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="docPreCondition" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="docPostCondition" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "doc_serviceinterface", namespace = "http://www.openrtp.org/namespaces/rtc_doc")
public class DocServiceinterface {

    @XmlAttribute(name = "description", namespace = "http://www.openrtp.org/namespaces/rtc_doc")
    protected String description;
    @XmlAttribute(name = "docArgument", namespace = "http://www.openrtp.org/namespaces/rtc_doc")
    protected String docArgument;
    @XmlAttribute(name = "docReturn", namespace = "http://www.openrtp.org/namespaces/rtc_doc")
    protected String docReturn;
    @XmlAttribute(name = "docException", namespace = "http://www.openrtp.org/namespaces/rtc_doc")
    protected String docException;
    @XmlAttribute(name = "docPreCondition", namespace = "http://www.openrtp.org/namespaces/rtc_doc")
    protected String docPreCondition;
    @XmlAttribute(name = "docPostCondition", namespace = "http://www.openrtp.org/namespaces/rtc_doc")
    protected String docPostCondition;

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
     * docArgument�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocArgument() {
        return docArgument;
    }

    /**
     * docArgument�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocArgument(String value) {
        this.docArgument = value;
    }

    /**
     * docReturn�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocReturn() {
        return docReturn;
    }

    /**
     * docReturn�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocReturn(String value) {
        this.docReturn = value;
    }

    /**
     * docException�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocException() {
        return docException;
    }

    /**
     * docException�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocException(String value) {
        this.docException = value;
    }

    /**
     * docPreCondition�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocPreCondition() {
        return docPreCondition;
    }

    /**
     * docPreCondition�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocPreCondition(String value) {
        this.docPreCondition = value;
    }

    /**
     * docPostCondition�v���p�e�B�̒l���擾���܂��B
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocPostCondition() {
        return docPostCondition;
    }

    /**
     * docPostCondition�v���p�e�B�̒l��ݒ肵�܂��B
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocPostCondition(String value) {
        this.docPostCondition = value;
    }

}
