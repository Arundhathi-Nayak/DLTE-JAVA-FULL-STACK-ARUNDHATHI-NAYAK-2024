package org.example;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.example.entity.Account;
import org.example.entity.Transaction;
import org.example.middleware.UserDatabaseRepository;
import org.example.remotes.StorageTarget;
import org.example.services.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Unit test for simple App.
 */

@RunWith(MockitoJUnitRunner.class)
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Mock
    private StorageTarget mockStorageTarget;
    @Mock
    private UserDatabaseRepository  mockDatabaseRepository;
    @Mock
    private AccountService services;



    @Before
    public void prepareStore(){
        when(mockStorageTarget.getUserRepository()).thenReturn(mockDatabaseRepository);
        services=new AccountService(mockStorageTarget);
    }
    @Test
    public void testVerifyAccount_ValidCredentials_ReturnsTrue() {
        //  Account account1=new Account(12345678L,45678L,"vanitha@mail.com","Vanitha",35000,"vanitha06",1234);
        String validUserName = "vanitha06";
        String validPassword = "1234";
        // when(mockDatabaseRepository.verifyPassword(validUserName, validPassword)).thenReturn(true);
        when(mockDatabaseRepository.verifyPassword(validUserName, validPassword)).thenReturn(true);
        boolean result = services.callVerifyPassword(validUserName, validPassword);
        assertTrue(result);
       /*
       testing fail test cases
        */
//        when(mockDatabaseRepository.verifyPassword(validUserName, validPassword)).thenReturn(false);
//        boolean result = services.callVerifyPassword("validUserName", validPassword);
//        assertFalse(result);
    }

    @Test
    public void testVerifyAccount_InvalidCredentials_ReturnsFalse() {
        String invalidUserName = "vanitha06";
        String invalidPassword = "5672";
        when(mockDatabaseRepository.verifyPassword(invalidUserName, invalidPassword)).thenReturn(false);
        boolean result = services.callVerifyPassword(invalidUserName, invalidPassword);
        assertFalse(result);
    }

    @Test
    public void test_findAll(){
        Transaction transaction1=new Transaction(new Date("3/2/2024"),1234567L,"Vanitha",200,33500);
        Transaction transaction2=new Transaction(new Date("3/2/2024"),1234568L,"Vanitha",300,33200);
        Transaction transaction3=new Transaction(new Date("3/2/2024"),1234569L,"Vanitha",1000,32200);
        Transaction transaction4=new Transaction(new Date("3/2/2024"),1234570L,"Vanitha",500,31700);
        List<Transaction> transactions = Arrays.asList(transaction1,transaction2,transaction3,transaction4);
        List<Transaction> transactions1 = Arrays.asList(transaction1,transaction2);
        when(mockDatabaseRepository.findALL()).thenReturn(transactions);
        // when(mockDatabaseRepository.findALL()).thenReturn(transactions1);
        List<Transaction> result = services.callFindAll();
        assertEquals(transactions, result);
        // assertNotEquals(transactions, result);  test case fails
        //assertNotEquals(transactions1,result);
    }
    @Test
    public void test_findAllUsers(){
        Transaction transaction1=new Transaction(new Date("3/2/2024"),1234567L,"Vanitha",200,33500);
        Transaction transaction2=new Transaction(new Date("3/2/2024"),1234568L,"Vanitha",300,33200);
        Transaction transaction3=new Transaction(new Date("3/2/2024"),1234569L,"Vanitha",1000,32200);
        Transaction transaction4=new Transaction(new Date("3/2/2024"),1234570L,"Vanitha",500,31700);
        List<Transaction> transactions = Arrays.asList(transaction1,transaction2,transaction3,transaction4);
        when(mockDatabaseRepository.findAllUser("Vinitha")).thenReturn(transactions);
        List<Transaction> result= services.callFindAllUser("Vinitha");
        //List<Transaction> result= services.callFindAllUser("Vinith");   fails test case
        assertEquals(transactions,result);
    }
    @Test
    public void test_findAllByDate(){
        Transaction transaction1=new Transaction(new Date("3/2/2024"),1234567L,"Vanitha",200,33500);
        Transaction transaction2=new Transaction(new Date("3/5/2024"),1234568L,"Vanitha",300,33200);
        Transaction transaction3=new Transaction(new Date("3/6/2024"),1234569L,"Vanitha",1000,32200);
        Transaction transaction4=new Transaction(new Date("3/7/2024"),1234570L,"Vanitha",500,31700);
        List<Transaction> transactions = Arrays.asList(transaction1,transaction2,transaction3,transaction4);
        List<Transaction> expectedTransactions = new ArrayList<>();
        for(Transaction transaction:transactions){
            if(transaction.getDate().equals(new Date("3/5/2024"))){
                expectedTransactions.add(transaction);
            }
        }
        when(mockDatabaseRepository.findAllByDate(java.sql.Date.valueOf("2024-03-05"),"Vinitha")).thenReturn(expectedTransactions);
        List<Transaction>  newTransactions = services.callFindAllDate(java.sql.Date.valueOf("2024-03-05"),"Vinitha");
        assertEquals(expectedTransactions,newTransactions);
    }
    Account account;
    @Test
    public void testWithdraw(){
        account=new Account(123456790L,45679L,"anandi@gmail.com","Anandi",45000,"anandi78","anandi1234");
        String username=account.getUsername();
        String password=account.getPassword();
        Double balance=account.getBalance();
        double withdrawalAmount=500;
        when(mockDatabaseRepository.withdraw(username, password, withdrawalAmount)).thenReturn(balance - withdrawalAmount);
        Double newBalance = services.callWithdraw(username, password, withdrawalAmount);
        Double expectedBalance = balance - withdrawalAmount;
        assertEquals(expectedBalance, newBalance);
    }
}