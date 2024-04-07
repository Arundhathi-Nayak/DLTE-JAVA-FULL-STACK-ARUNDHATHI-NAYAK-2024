package implementation;

import org.reviewdatabase.Details.DatabaseRepositoryImplementation;
import org.reviewdatabase.Details.Employee;
import org.reviewdatabase.connection.ConnectionCreate;
import org.reviewdatabase.exception.ConnectionException;
import org.reviewdatabase.exception.EmployeeExistException;
import org.reviewdatabase.exception.EmployeeNotFoundException;
import org.reviewdatabase.exception.ValidationException;
import org.reviewdatabase.remote.InputEmployeeDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.soap.SOAPFaultException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Services {
    InputEmployeeDetails inputEmployeeDetails;
    Connection connection;
    Logger logger= LoggerFactory.getLogger(Services.class);
    private SOAPFault soapFault;


    // Constructor to initialize InputEmployeeDetails and establish database connection
    public Services() throws SQLException {
        inputEmployeeDetails = new DatabaseRepositoryImplementation();
        connection= ConnectionCreate.createConnection();
    }
    private String exceptionHandler(Exception e) {
        if (e.getClass().equals(ValidationException.class)) {
            return "ValidationException";
        } else if (e.getClass().equals(EmployeeExistException.class)) {
            return "EmployeeExistException";
        } else if (e.getClass().equals(ConnectionException.class)) {
            return "ConnectionException";
        } else if (e.getClass().equals(SQLException.class)) {
            return "SQLException";
        } else if (e.getClass().equals(EmployeeNotFoundException.class)) {
            return "EmployeeNotFoundException";
        } else {
            return "Unknown Exception";
        }
    }


    @WebResult(name="addNewEmployee")
    @WebMethod
    public Employee callSaveAll(@WebParam Employee employees){
        //GroupOfEmployee groupOfEmployee = new GroupOfEmployee();
        List<Employee> employeeList=new ArrayList<>();
        employeeList.add(employees);
        List<Employee> employee=new ArrayList<>();
        try {
             employee =inputEmployeeDetails.create(employeeList);
          //  groupOfEmployee.setEmployeeArrayList((ArrayList<Employee>) employee);
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getClass().getName());
            try {
                soapFault = SOAPFactory.newInstance().createFault();
                soapFault.setFaultCode(exceptionHandler(e));
                soapFault.setFaultString(e.getMessage());
            } catch (SOAPException ex) {
                ex.printStackTrace();
            }
            throw new SOAPFaultException(soapFault);
        }
     return employees;
    }

    @WebResult(name="findBasedOnId")
    @WebMethod
    public Employee callFilterBasedOnID(@WebParam String employeeId){
        Employee employee=new Employee();
        try {
            employee = inputEmployeeDetails.displayBasedOnEmployeeId(employeeId);
        }catch (Exception e){
            logger.error(e.getMessage() + e.getClass().getName());
            try {
                soapFault = SOAPFactory.newInstance().createFault();
                soapFault.setFaultCode(exceptionHandler(e));
                soapFault.setFaultString(e.getMessage());
            } catch (SOAPException ex) {
                ex.printStackTrace();
            }
            throw new SOAPFaultException(soapFault);
        }
        return employee;
    }

    @WebResult(name="findBasedOnPincode")
    @WebMethod
    public GroupOfEmployee callFilterBasedOnPincode(@WebParam int pincode){
        GroupOfEmployee groupOfEmployee = new GroupOfEmployee();
        try {
            ArrayList<Employee> employees = (ArrayList<Employee>) inputEmployeeDetails.displayBasedOnPinCode(pincode);
            groupOfEmployee.setEmployeeArrayList(employees);
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getClass().getName());
            try {
                soapFault = SOAPFactory.newInstance().createFault();
                soapFault.setFaultCode(exceptionHandler(e));
                soapFault.setFaultString(e.getMessage());
            } catch (SOAPException ex) {
                ex.printStackTrace();
            }
            throw new SOAPFaultException(soapFault);
        }
        return groupOfEmployee;

    }

    @WebResult(name="findAll")
    @WebMethod
    public GroupOfEmployee callFindAll(){
        GroupOfEmployee groupOfEmployee = new GroupOfEmployee();
        try {
            ArrayList<Employee> employees = (ArrayList<Employee>) inputEmployeeDetails.read();
            groupOfEmployee.setEmployeeArrayList(employees);
        }  catch (Exception e) {
            logger.error(e.getMessage() + e.getClass().getName());
            try {
                soapFault = SOAPFactory.newInstance().createFault();
                soapFault.setFaultCode(exceptionHandler(e));
                soapFault.setFaultString(e.getMessage());
            } catch (SOAPException ex) {
                ex.printStackTrace();
            }
            throw new SOAPFaultException(soapFault);
        }
        return groupOfEmployee;
    }


}
