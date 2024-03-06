package org.example;

import java.util.List;
import java.util.Scanner;

public interface InputInterface {
    Employee inputDetails(Scanner scanner);
    void displayInput(List<Employee> employees);
}
