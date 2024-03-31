package implementation;

import org.reviewdatabase.Details.DatabaseRepositoryImplementation;
import org.reviewdatabase.Details.Employee;
import org.reviewdatabase.connection.ConnectionCreate;
import org.reviewdatabase.exception.EmployeeNotFoundException;
import org.reviewdatabase.remote.InputEmployeeDetails;

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
    public GroupOfEmployee callSaveAll(Employee employees){
        GroupOfEmployee groupOfEmployee = new GroupOfEmployee();
        try {
            ArrayList<Employee> employee = (ArrayList<Employee>) inputEmployeeDetails.read();
            groupOfEmployee.setEmployeeArrayList(employee);
        } catch (EmployeeNotFoundException e) {
            e.printStackTrace();
        }
     return groupOfEmployee;
    }

    @WebResult(name="findBasedOnId")
    public Employee callFilterBasedOnID(String employeeId){
        Employee employee = inputEmployeeDetails.displayBasedOnEmployeeId(employeeId);
        return employee;
    }

    @WebResult(name="findBasedOnPincode")
    public GroupOfEmployee callFilterBasedOnPincode(int pincode){
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
