
package implementation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for employee complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="employee">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="employeePermanentAddress" type="{http://implementation/}employeeAddress" minOccurs="0"/>
 *         &lt;element name="employeeTemporaryAddress" type="{http://implementation/}employeeAddress" minOccurs="0"/>
 *         &lt;element name="employeebasicDetails" type="{http://implementation/}employeebasicDetails" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "employee", propOrder = {
    "employeePermanentAddress",
    "employeeTemporaryAddress",
    "employeebasicDetails"
})
public class Employee {

    protected EmployeeAddress employeePermanentAddress;
    protected EmployeeAddress employeeTemporaryAddress;
    protected EmployeebasicDetails employeebasicDetails;

    /**
     * Gets the value of the employeePermanentAddress property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeAddress }
     *     
     */
    public EmployeeAddress getEmployeePermanentAddress() {
        return employeePermanentAddress;
    }

    /**
     * Sets the value of the employeePermanentAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeAddress }
     *     
     */
    public void setEmployeePermanentAddress(EmployeeAddress value) {
        this.employeePermanentAddress = value;
    }

    /**
     * Gets the value of the employeeTemporaryAddress property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeAddress }
     *     
     */
    public EmployeeAddress getEmployeeTemporaryAddress() {
        return employeeTemporaryAddress;
    }

    /**
     * Sets the value of the employeeTemporaryAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeAddress }
     *     
     */
    public void setEmployeeTemporaryAddress(EmployeeAddress value) {
        this.employeeTemporaryAddress = value;
    }

    /**
     * Gets the value of the employeebasicDetails property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeebasicDetails }
     *     
     */
    public EmployeebasicDetails getEmployeebasicDetails() {
        return employeebasicDetails;
    }

    /**
     * Sets the value of the employeebasicDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeebasicDetails }
     *     
     */
    public void setEmployeebasicDetails(EmployeebasicDetails value) {
        this.employeebasicDetails = value;
    }

}
