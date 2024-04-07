package org.reviewdatabase.validation;

import org.reviewdatabase.Details.DatabaseRepositoryImplementation;
import org.reviewdatabase.Details.Employee;
import org.reviewdatabase.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationData {
    Logger logger= LoggerFactory.getLogger(ValidationData.class);
    public boolean Validationofdata(List<Employee> employee) throws ValidationException {

            for (Employee employees : employee) {
                if (!isValidEmail(employees.getEmployeebasicDetails().getEmailId())) {
                    logger.warn("Invalid Email Format");
                    throw new ValidationException("VAL-001");
                }
                if (!isValidPhoneNumber(employees.getEmployeebasicDetails().getPhoneNumber())) {
                    logger.warn("Invalid Phone Format");
                    throw new ValidationException("VAL-002");
                }
                if (!isValidPin(employees.getEmployeePermanentAddress().getPinCode())) {
                    logger.warn("Invalid Permanent Pincode Format");
                    throw new ValidationException("VAL-003");
                }
                if (!isValidPin(employees.getEmployeeTemporaryAddress().getPinCode())) {
                    logger.warn("Invalid Temporary Pincode Format");
                    throw new ValidationException("VAL-004");
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
