//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.11.22 at 05:14:48 �ߌ� JST 
//


package org.openrtp.namespaces.rtc.version02;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for property_is_less_than complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="property_is_less_than">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Literal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="matchCase" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "property_is_less_than", propOrder = {
    "literal"
})
public class PropertyIsLessThan {

    @XmlElement(name = "Literal", required = true)
    protected String literal;
    @XmlAttribute(namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    protected boolean matchCase;

    /**
     * Gets the value of the literal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLiteral() {
        return literal;
    }

    /**
     * Sets the value of the literal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLiteral(String value) {
        this.literal = value;
    }

    /**
     * Gets the value of the matchCase property.
     * 
     */
    public boolean isMatchCase() {
        return matchCase;
    }

    /**
     * Sets the value of the matchCase property.
     * 
     */
    public void setMatchCase(boolean value) {
        this.matchCase = value;
    }

}
