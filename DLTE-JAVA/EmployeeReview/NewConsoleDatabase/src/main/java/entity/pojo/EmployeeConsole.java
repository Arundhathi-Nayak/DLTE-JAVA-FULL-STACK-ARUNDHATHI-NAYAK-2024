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
    public String displayEmployeeDetails() {
        return "Employee ID: " + employeeBasicDetailsConsole.getEmployeeId() +
                "\nName: " + employeeBasicDetailsConsole.getEmployeeName() +
                "\nEmail: " + employeeBasicDetailsConsole.getEmailId() +
                "\nPhone Number: " + employeeBasicDetailsConsole.getPhoneNumber() +
                "\nPermanent Address: " + employeePermanentAddressConsole.getAddress() +
                "\nPermanent House Number: " + employeePermanentAddressConsole.getHouseNumber() +
                "\nPermanent City: " + employeePermanentAddressConsole.getCity() +
                "\nPermanent State: " + employeePermanentAddressConsole.getState() +
                "\nPermanent Pin Code: " + employeePermanentAddressConsole.getPinCode() +
                "\nTemporary Address: " + employeeTemporaryAddressConsole.getAddress() +
                "\nTemporary House Number: " + employeeTemporaryAddressConsole.getHouseNumber() +
                "\nTemporary City: " + employeeTemporaryAddressConsole.getCity() +
                "\nTemporary State: " + employeeTemporaryAddressConsole.getState() +
                "\nTemporary Pin Code: " + employeeTemporaryAddressConsole.getPinCode();
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
