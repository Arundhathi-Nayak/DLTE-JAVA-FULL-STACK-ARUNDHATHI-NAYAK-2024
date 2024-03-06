package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface MyBank {
    void addNewLoan() throws IOException, ClassNotFoundException;
    Loan[] checkAvailableLoans();
    Loan[] checkClosedLoans();

}
