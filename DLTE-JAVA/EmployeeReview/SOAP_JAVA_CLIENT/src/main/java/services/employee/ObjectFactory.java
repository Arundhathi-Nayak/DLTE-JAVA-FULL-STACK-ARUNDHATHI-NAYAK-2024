
package services.employee;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the services.employee package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Employee_QNAME = new QName("http://employee.services", "employee");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: services.employee
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateEmployeeRequest }
     * 
     */
    public CreateEmployeeRequest createCreateEmployeeRequest() {
        return new CreateEmployeeRequest();
    }

    /**
     * Create an instance of {@link Employee }
     * 
     */
    public Employee createEmployee() {
        return new Employee();
    }

    /**
     * Create an instance of {@link FilterByIdResponse }
     * 
     */
    public FilterByIdResponse createFilterByIdResponse() {
        return new FilterByIdResponse();
    }

    /**
     * Create an instance of {@link ServiceStatus }
     * 
     */
    public ServiceStatus createServiceStatus() {
        return new ServiceStatus();
    }

    /**
     * Create an instance of {@link GetEmployeeByPinCodeResponse }
     * 
     */
    public GetEmployeeByPinCodeResponse createGetEmployeeByPinCodeResponse() {
        return new GetEmployeeByPinCodeResponse();
    }

    /**
     * Create an instance of {@link FilterByIdRequest }
     * 
     */
    public FilterByIdRequest createFilterByIdRequest() {
        return new FilterByIdRequest();
    }

    /**
     * Create an instance of {@link CreateEmployeeResponse }
     * 
     */
    public CreateEmployeeResponse createCreateEmployeeResponse() {
        return new CreateEmployeeResponse();
    }

    /**
     * Create an instance of {@link FindAllEmployeeRequest }
     * 
     */
    public FindAllEmployeeRequest createFindAllEmployeeRequest() {
        return new FindAllEmployeeRequest();
    }

    /**
     * Create an instance of {@link FindAllEmployeeResponse }
     * 
     */
    public FindAllEmployeeResponse createFindAllEmployeeResponse() {
        return new FindAllEmployeeResponse();
    }

    /**
     * Create an instance of {@link GetEmployeeByPinCodeRequest }
     * 
     */
    public GetEmployeeByPinCodeRequest createGetEmployeeByPinCodeRequest() {
        return new GetEmployeeByPinCodeRequest();
    }

    /**
     * Create an instance of {@link EmployeeBasicDetails }
     * 
     */
    public EmployeeBasicDetails createEmployeeBasicDetails() {
        return new EmployeeBasicDetails();
    }

    /**
     * Create an instance of {@link EmployeeAddress }
     * 
     */
    public EmployeeAddress createEmployeeAddress() {
        return new EmployeeAddress();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Employee }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://employee.services", name = "employee")
    public JAXBElement<Employee> createEmployee(Employee value) {
        return new JAXBElement<Employee>(_Employee_QNAME, Employee.class, null, value);
    }

}
