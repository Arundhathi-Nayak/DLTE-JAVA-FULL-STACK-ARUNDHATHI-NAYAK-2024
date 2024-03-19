package entity.pojo;

public class EmployeeConsole {
    EmployeeBasicDetailsConsole employeeBasicDetailsConsole;
    EmployeeAddressConsole employeePermanentAddressConsole;
    EmployeeAddressConsole employeeTemporaryAddressConsole;

    public EmployeeConsole() {
    }

    public EmployeeConsole(EmployeeBasicDetailsConsole employeeBasicDetailsConsole, EmployeeAddressConsole employeePermanentAddressConsole, EmployeeAddressConsole getEmployeeTemporaryAddressConsole) {
        this.employeeBasicDetailsConsole = employeeBasicDetailsConsole;
        this.employeePermanentAddressConsole = employeePermanentAddressConsole;
        this.employeeTemporaryAddressConsole = getEmployeeTemporaryAddressConsole;
    }

    public EmployeeBasicDetailsConsole getEmployeeBasicDetailsConsole() {
        return employeeBasicDetailsConsole;
    }

    public void setEmployeeBasicDetailsConsole(EmployeeBasicDetailsConsole employeeBasicDetailsConsole) {
        this.employeeBasicDetailsConsole = employeeBasicDetailsConsole;
    }

    public EmployeeAddressConsole getEmployeePermanentAddressConsole() {
        return employeePermanentAddressConsole;
    }

    public void setEmployeePermanentAddressConsole(EmployeeAddressConsole employeePermanentAddressConsole) {
        this.employeePermanentAddressConsole = employeePermanentAddressConsole;
    }

    public EmployeeAddressConsole getEmployeeTemporaryAddressConsole() {
        return employeeTemporaryAddressConsole;
    }

    public void setEmployeeTemporaryAddressConsole(EmployeeAddressConsole getEmployeeTemporaryAddressConsole) {
        this.employeeTemporaryAddressConsole = getEmployeeTemporaryAddressConsole;
    }

    @Override
    public String toString() {
        return "EmployeeConsole{" +
                "employeeBasicDetailsConsole=" + employeeBasicDetailsConsole +
                ", employeePermanentAddressConsole=" + employeePermanentAddressConsole +
                ", getEmployeeTemporaryAddressConsole=" + employeeTemporaryAddressConsole +
                '}';
    }
}
