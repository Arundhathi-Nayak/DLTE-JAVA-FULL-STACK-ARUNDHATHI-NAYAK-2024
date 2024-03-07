package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface MyBank {
    void addNewLoan() throws IOException, ClassNotFoundException;
    List<Loan> availableLoan() throws IOException, ClassNotFoundException;
    List<Loan> closedLoan() throws IOException, ClassNotFoundException;
}
