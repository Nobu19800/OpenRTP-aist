//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.11.22 at 05:14:48 �ߌ� JST 
//


package org.openrtp.namespaces.rtc.version02;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for property_is_between complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="property_is_between">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LowerBoundary" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UpperBoundary" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "property_is_between", propOrder = {
    "lowerBoundary",
    "upperBoundary"
})
public class PropertyIsBetween {

    @XmlElement(name = "LowerBoundary", required = true)
    protected String lowerBoundary;
    @XmlElement(name = "UpperBoundary", required = true)
    protected String upperBoundary;

    /**
     * Gets the value of the lowerBoundary property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLowerBoundary() {
        return lowerBoundary;
    }

    /**
     * Sets the value of the lowerBoundary property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLowerBoundary(String value) {
        this.lowerBoundary = value;
    }

    /**
     * Gets the value of the upperBoundary property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpperBoundary() {
        return upperBoundary;
    }

    /**
     * Sets the value of the upperBoundary property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpperBoundary(String value) {
        this.upperBoundary = value;
    }

}
