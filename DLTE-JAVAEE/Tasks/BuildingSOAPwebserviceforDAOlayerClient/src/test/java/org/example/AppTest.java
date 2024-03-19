package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import org.example.entity.Account;
import org.example.remotes.StorageTarget;
import org.example.services.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import soapwebservice.CallOperation;
import soapwebservice.CallOperationService;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Mock
    AccountService accountService;

    @Mock
    CallOperation callOperation;
     @Mock
    CallOperationService callOperationService;

    @Before
    public void setUp(){
        callOperationService.getCallOperationPort();
        callOperationService =new CallOperationService();
    }

    @Test
    public void testFindByUser() {
        String username = "arundhathi12";
        Account newAccount = new Account();
        when(accountService.findUserByUsername(username)).thenReturn(newAccount);
        soapwebservice.Account actualAccount = callOperation.findByUser(username);
        assertEquals(newAccount, actualAccount);
    }

    @Test
    public void testAll() {
        Account account = new Account();
        doNothing().when(accountService).callAddTransactions(account);
        callOperation.createAccount(123456789, 123432111, "nayak@123", "Aru", 5000, "arundhathi12", "arundhathi123");
        verify(accountService, times(1)).callAddTransactions(account);
    }

    @Test
    public void testWithdraw() {
        String username = "arundhathi12";
        String password = "arundhathi123";
        double withdrawAmount = 500;
        doNothing().when(accountService).callWithdraw(username, password, withdrawAmount);
        callOperation.withdraw(username, password, withdrawAmount);
        verify(accountService, times(1)).callWithdraw(username, password, withdrawAmount);
    }
}
