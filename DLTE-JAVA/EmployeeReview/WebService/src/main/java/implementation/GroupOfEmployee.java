package implementation;

import org.reviewdatabase.Details.Employee;

import java.util.ArrayList;

public class GroupOfEmployee {
    private ArrayList<Employee> employeeArrayList;

    public GroupOfEmployee(ArrayList<Employee> employeeArrayList) {
        this.employeeArrayList = employeeArrayList;
    }

    public GroupOfEmployee() {
    }

    public ArrayList<Employee> getEmployeeArrayList() {
        return employeeArrayList;
    }

    public void setEmployeeArrayList(ArrayList<Employee> employeeArrayList) {
        this.employeeArrayList = employeeArrayList;
    }

    @Override
    public String toString() {
        return "GroupOfEmployee{" +
                "employeeArrayList=" + employeeArrayList +
                '}';
    }
}
