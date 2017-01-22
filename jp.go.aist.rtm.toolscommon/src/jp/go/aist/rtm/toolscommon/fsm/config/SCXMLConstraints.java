package jp.go.aist.rtm.toolscommon.fsm.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="restrictedState" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="color" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="documentation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="possibleEvent" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="documentation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "restrictedState" })
@XmlRootElement(name = "SCXMLConstraints")
public class SCXMLConstraints implements Serializable {

	@XmlElement(required = true)
	protected List<SCXMLConstraints.RestrictedState> restrictedState;

	/**
	 * Gets the value of the restrictedState property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the restrictedState property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getRestrictedState().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link SCXMLConstraints.RestrictedState }
	 * 
	 * 
	 */
	public List<SCXMLConstraints.RestrictedState> getRestrictedState() {
		if (restrictedState == null) {
			restrictedState = new ArrayList<SCXMLConstraints.RestrictedState>();
		}
		return this.restrictedState;
	}

	/**
	 * <p>
	 * Java class for anonymous complex type.
	 * 
	 * <p>
	 * The following schema fragment specifies the expected content contained
	 * within this class.
	 * 
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;sequence>
	 *         &lt;element name="color" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="documentation" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="possibleEvent" maxOccurs="unbounded">
	 *           &lt;complexType>
	 *             &lt;complexContent>
	 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                 &lt;sequence>
	 *                   &lt;element name="documentation" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                 &lt;/sequence>
	 *                 &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
	 *               &lt;/restriction>
	 *             &lt;/complexContent>
	 *           &lt;/complexType>
	 *         &lt;/element>
	 *       &lt;/sequence>
	 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "color", "documentation", "possibleEvent" })
	public static class RestrictedState implements Serializable {

		@XmlElement(required = true)
		protected String color;
		@XmlElement(required = true)
		protected String documentation;
		@XmlElement(required = true)
		protected List<SCXMLConstraints.RestrictedState.PossibleEvent> possibleEvent;
		@XmlAttribute(name = "name")
		protected String name;

		/**
		 * Gets the value of the color property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getColor() {
			return color;
		}

		/**
		 * Sets the value of the color property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setColor(String value) {
			this.color = value;
		}

		/**
		 * Gets the value of the documentation property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getDocumentation() {
			return documentation;
		}

		/**
		 * Sets the value of the documentation property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setDocumentation(String value) {
			this.documentation = value;
		}

		/**
		 * Gets the value of the possibleEvent property.
		 * 
		 * <p>
		 * This accessor method returns a reference to the live list, not a
		 * snapshot. Therefore any modification you make to the returned list
		 * will be present inside the JAXB object. This is why there is not a
		 * <CODE>set</CODE> method for the possibleEvent property.
		 * 
		 * <p>
		 * For example, to add a new item, do as follows:
		 * 
		 * <pre>
		 * getPossibleEvent().add(newItem);
		 * </pre>
		 * 
		 * 
		 * <p>
		 * Objects of the following type(s) are allowed in the list
		 * {@link SCXMLConstraints.RestrictedState.PossibleEvent }
		 * 
		 * 
		 */
		public List<SCXMLConstraints.RestrictedState.PossibleEvent> getPossibleEvent() {
			if (possibleEvent == null) {
				possibleEvent = new ArrayList<SCXMLConstraints.RestrictedState.PossibleEvent>();
			}
			return this.possibleEvent;
		}

		/**
		 * Gets the value of the name property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getName() {
			return name;
		}

		/**
		 * Sets the value of the name property.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setName(String value) {
			this.name = value;
		}

		/**
		 * <p>
		 * Java class for anonymous complex type.
		 * 
		 * <p>
		 * The following schema fragment specifies the expected content
		 * contained within this class.
		 * 
		 * <pre>
		 * &lt;complexType>
		 *   &lt;complexContent>
		 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *       &lt;sequence>
		 *         &lt;element name="documentation" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *       &lt;/sequence>
		 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
		 *     &lt;/restriction>
		 *   &lt;/complexContent>
		 * &lt;/complexType>
		 * </pre>
		 * 
		 * 
		 */
		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "documentation" })
		public static class PossibleEvent implements Serializable {

			@XmlElement(required = true)
			protected String documentation;
			@XmlAttribute(name = "name")
			protected String name;

			/**
			 * Gets the value of the documentation property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getDocumentation() {
				return documentation;
			}

			/**
			 * Sets the value of the documentation property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setDocumentation(String value) {
				this.documentation = value;
			}

			/**
			 * Gets the value of the name property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getName() {
				return name;
			}

			/**
			 * Sets the value of the name property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setName(String value) {
				this.name = value;
			}

		}

	}

}
