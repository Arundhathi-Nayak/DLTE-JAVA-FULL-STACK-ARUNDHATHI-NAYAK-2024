package implementation;

import org.reviewdatabase.Details.DatabaseRepositoryImplementation;
import org.reviewdatabase.Details.Employee;
import org.reviewdatabase.connection.ConnectionCreate;
import org.reviewdatabase.exception.EmployeeNotFoundException;
import org.reviewdatabase.remote.InputEmployeeDetails;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Services {
    InputEmployeeDetails inputEmployeeDetails;
    Connection connection;

    // Constructor to initialize InputEmployeeDetails and establish database connection
    public Services() {
        inputEmployeeDetails = new DatabaseRepositoryImplementation();
        connection= ConnectionCreate.createConnection();
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
        } catch (EmployeeNotFoundException e) {
            e.printStackTrace();
        }
     return employees;
    }

    @WebResult(name="findBasedOnId")
    @WebMethod
    public Employee callFilterBasedOnID(@WebParam String employeeId){
        Employee employee=new Employee();
        try {
            employee = inputEmployeeDetails.displayBasedOnEmployeeId(employeeId);
        }catch (EmployeeNotFoundException employeeException){
            employeeException.printStackTrace();
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
        } catch (EmployeeNotFoundException e) {
            e.printStackTrace();
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
        }  catch (EmployeeNotFoundException e) {
            e.printStackTrace();
        }
        return groupOfEmployee;
    }


}
