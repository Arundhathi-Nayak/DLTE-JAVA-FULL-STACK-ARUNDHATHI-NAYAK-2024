package org.example;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private BankApp bankApp;
    @Before
    public void setUp() {
        bankApp = new BankApp();
    }
    public void testAddNewLoan() throws IOException, ClassNotFoundException, IOException {
        int initialSize = bankApp.availableLoan().size();
        bankApp.addNewLoan();
        int newSize = bankApp.availableLoan().size();
        assertEquals(initialSize + 1, newSize);
    }
    public void testAvailableLoan() throws IOException, ClassNotFoundException {
        List<Loan> availableLoans = bankApp.availableLoan();
        assertNotNull(availableLoans);
    }



    @Test
    public void testClosedLoan() throws IOException, ClassNotFoundException {
        List<Loan> closedLoans = bankApp.closedLoan();
        assertNotNull(closedLoans);
    }

    @Test
    public void testOpenLoan() throws IOException, ClassNotFoundException {
        // Assuming open loans have a status of "open"
        List<Loan> openLoans = bankApp.availableLoan();
        assertNotNull(openLoans);
        for (Loan loan : openLoans) {
            assertEquals("open", loan.getLoanStatus());
        }
    }





}
