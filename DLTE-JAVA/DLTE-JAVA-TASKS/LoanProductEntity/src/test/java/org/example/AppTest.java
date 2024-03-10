package org.example;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private BankApp bankApp;
    static List<Loan> loanList=new ArrayList<>();
    static List<Loan> loan=new ArrayList<>();
    @Before
    public void setUp() {
        bankApp = new BankApp();
    }

    @BeforeClass
    public static void initialize(){
        loanList.add(new Loan(123,1000,"2/2/2024","Aruu","open","9876543"));
        loanList.add(new Loan(223,2000,"3/12/2024","Annapoo","open","9876543"));
        loanList.add(new Loan(323,3000,"4/20/2024","Divija","open","9876543"));
        loanList.add(new Loan(423,4000,"5/6/2024","Eeksha","open","9876543"));
        loanList.add(new Loan(523,5000,"6/8/2024","Avinash","open","9876543"));
    }
    @Test
    public void testAddNewLoan() throws IOException, ClassNotFoundException {
        int initialSize = bankApp.availableLoan().size();
        bankApp.addNewLoan();
        int newSize = bankApp.availableLoan().size();
        assertEquals(initialSize + 1, newSize);
    }



    @Test
    public void testClosedLoan()  {
        String expectedLoanStatus="closed";
        assertNotEquals("Expected test to pass",expectedLoanStatus,loanList.get(0).getLoanStatus());
        assertEquals(expectedLoanStatus,loanList.get(0).getLoanStatus());
        assertEquals(expectedLoanStatus,loanList.get(1).getLoanStatus());
        assertEquals(expectedLoanStatus,loanList.get(2).getLoanStatus());
        assertEquals(expectedLoanStatus,loanList.get(3).getLoanStatus());
        assertEquals(expectedLoanStatus,loanList.get(4).getLoanStatus());
        assertEquals(expectedLoanStatus,loanList.get(5).getLoanStatus());
    }

    @Test
    public void testOpenLoan() {
        // Assuming open loans have a status of "open"
        String expectedLoanStatus="open";
        assertNotEquals("Expected test to pass",expectedLoanStatus,loanList.get(0).getLoanStatus());
        assertEquals(expectedLoanStatus,loanList.get(1).getLoanStatus());
        assertEquals(expectedLoanStatus,loanList.get(2).getLoanStatus());
        assertEquals(expectedLoanStatus,loanList.get(3).getLoanStatus());
        assertEquals(expectedLoanStatus,loanList.get(4).getLoanStatus());
        assertEquals(expectedLoanStatus,loanList.get(5).getLoanStatus());
    }
    @Test(timeout =1000)
    public void testTime() throws InterruptedException {
        Thread.sleep(100);
        assertTrue(loanList.size()>0);
    }
    @Test
    public void checkNull(){
        assertNull(loan);
        assertNotNull(loanList);
    }




}
