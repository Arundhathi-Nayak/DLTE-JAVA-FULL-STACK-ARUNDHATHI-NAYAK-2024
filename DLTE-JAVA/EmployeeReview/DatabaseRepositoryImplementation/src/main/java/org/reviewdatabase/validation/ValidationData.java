package org.reviewdatabase.validation;

import org.reviewdatabase.Details.Employee;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationData {
    public boolean Validationofdata(List<Employee> employee) {
        for (Employee employees : employee) {
            if (!isValidEmail(employees.getEmployeebasicDetails().getEmailId()) ||
                    !isValidPhoneNumber(employees.getEmployeebasicDetails().getPhoneNumber()) ||
                    !isValidPin(employees.getEmployeePermanentAddress().getPinCode()) ||
                    !isValidPin(employees.getEmployeeTemporaryAddress().getPinCode())) {
                return false;
            }
        }
        return true;
    }
    public static boolean isValidEmail(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean isValidPhoneNumber(long phoneNumber) {
        String regex = "(\\d{10})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(Long.toString(phoneNumber));
        return matcher.matches();
    }
    public static boolean isValidPin(int pin) {
        String pinString = String.valueOf(pin);
        return pinString.length() == 6;
    }
}
