
package services.employee;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "EmployeePort", targetNamespace = "http://employee.services")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface EmployeePort {


    /**
     * 
     * @param filterByIdRequest
     * @return
     *     returns services.employee.FilterByIdResponse
     */
    @WebMethod
    @WebResult(name = "filterByIdResponse", targetNamespace = "http://employee.services", partName = "filterByIdResponse")
    public FilterByIdResponse filterById(
        @WebParam(name = "filterByIdRequest", targetNamespace = "http://employee.services", partName = "filterByIdRequest")
        FilterByIdRequest filterByIdRequest);

    /**
     * 
     * @param createEmployeeRequest
     * @return
     *     returns services.employee.CreateEmployeeResponse
     */
    @WebMethod
    @WebResult(name = "createEmployeeResponse", targetNamespace = "http://employee.services", partName = "createEmployeeResponse")
    public CreateEmployeeResponse createEmployee(
        @WebParam(name = "createEmployeeRequest", targetNamespace = "http://employee.services", partName = "createEmployeeRequest")
        CreateEmployeeRequest createEmployeeRequest);

    /**
     * 
     * @param findAllEmployeeRequest
     * @return
     *     returns services.employee.FindAllEmployeeResponse
     */
    @WebMethod
    @WebResult(name = "findAllEmployeeResponse", targetNamespace = "http://employee.services", partName = "findAllEmployeeResponse")
    public FindAllEmployeeResponse findAllEmployee(
        @WebParam(name = "findAllEmployeeRequest", targetNamespace = "http://employee.services", partName = "findAllEmployeeRequest")
        FindAllEmployeeRequest findAllEmployeeRequest);

    /**
     * 
     * @param getEmployeeByPinCodeRequest
     * @return
     *     returns services.employee.GetEmployeeByPinCodeResponse
     */
    @WebMethod
    @WebResult(name = "getEmployeeByPinCodeResponse", targetNamespace = "http://employee.services", partName = "getEmployeeByPinCodeResponse")
    public GetEmployeeByPinCodeResponse getEmployeeByPinCode(
        @WebParam(name = "getEmployeeByPinCodeRequest", targetNamespace = "http://employee.services", partName = "getEmployeeByPinCodeRequest")
        GetEmployeeByPinCodeRequest getEmployeeByPinCodeRequest);

}
