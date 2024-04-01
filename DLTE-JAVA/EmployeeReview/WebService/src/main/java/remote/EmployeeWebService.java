package remote;

import org.reviewdatabase.Details.Employee;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface EmployeeWebService {
    @WebMethod
    List<Employee> getEmployees();

    @WebMethod
    Employee getEmployeeById(@WebParam(name = "employeeId") String employeeId);

    @WebMethod
    List<Employee> getEmployeesByPinCode(@WebParam(name = "pinCode") int pinCode);

    @WebMethod
    List<Employee> createEmployee(@WebParam(name = "employees") List<Employee> employees);
}
