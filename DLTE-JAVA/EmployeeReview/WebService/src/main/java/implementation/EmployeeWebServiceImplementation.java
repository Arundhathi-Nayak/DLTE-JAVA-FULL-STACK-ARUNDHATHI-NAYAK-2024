package implementation;

import org.reviewdatabase.Details.DatabaseRepositoryImplementation;
import org.reviewdatabase.Details.Employee;
import remote.EmployeeWebService;

import java.util.List;

public class EmployeeWebServiceImplementation implements EmployeeWebService {
    private final DatabaseRepositoryImplementation databaseRepository = new DatabaseRepositoryImplementation();
    @Override
    public List<Employee> getEmployees() {
        return databaseRepository.read();
    }

    @Override
    public Employee getEmployeeById(String employeeId) {
        return databaseRepository.displayBasedOnEmployeeId(employeeId);
    }

    @Override
    public List<Employee> getEmployeesByPinCode(int pinCode) {
        return databaseRepository.displayBasedOnPinCode(pinCode);
    }

    @Override
    public List<Employee> createEmployee(List<Employee> employees) {
        return databaseRepository.create(employees);
    }
}
