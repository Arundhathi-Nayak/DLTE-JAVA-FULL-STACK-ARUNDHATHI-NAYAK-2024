
package implementation;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import java.util.List;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Services", targetNamespace = "http://implementation/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Services {


    /**
     * 
     * @param arg0
     * @return
     *     returns implementation.Employee
     */
    @WebMethod
    @WebResult(name = "findBasedOnId", partName = "findBasedOnId")
    @Action(input = "http://implementation/Services/callFilterBasedOnIDRequest", output = "http://implementation/Services/callFilterBasedOnIDResponse")
    public org.webconsole.Details.Employee callFilterBasedOnID(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns implementation.GroupOfEmployee
     */
    @WebMethod
    @WebResult(name = "findBasedOnPincode", partName = "findBasedOnPincode")
    @Action(input = "http://implementation/Services/callFilterBasedOnPincodeRequest", output = "http://implementation/Services/callFilterBasedOnPincodeResponse")
    public GroupOfEmployee callFilterBasedOnPincode(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0);

    /**
     *
     * @param arg0
     * @return
     */
    @WebMethod
    @Action(input = "http://implementation/Services/callSaveAllRequest", output = "http://implementation/Services/callSaveAllResponse")
    public GroupOfEmployee callSaveAll(
        @WebParam(name = "arg0", partName = "arg0")
        Employee arg0);

    /**
     * 
     * @return
     *     returns implementation.GroupOfEmployee
     * @param employees
     */
    @WebMethod
    @WebResult(name = "findAll", partName = "findAll")
    @Action(input = "http://implementation/Services/callFindAllRequest", output = "http://implementation/Services/callFindAllResponse")
    public GroupOfEmployee callFindAll(List<org.webconsole.Details.Employee> employees);

}
