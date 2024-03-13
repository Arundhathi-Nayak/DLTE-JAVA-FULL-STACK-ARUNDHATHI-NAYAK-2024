package org.example;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import org.example.entity.Account;
import org.example.middleware.UserDatabaseRepository;
import org.example.remotes.StorageTarget;
import org.example.services.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
    private AccountService services;



    @Before
    public void prepareStore(){
        when(mockStorageTarget.getUserRepository()).thenReturn(mockDatabaseRepository);
        services=new AccountService(mockStorageTarget);
    }
    @Test
    public void testVerifyAccount_ValidCredentials_ReturnsTrue() {
        String validUserName = "validUser";
        String validPassword = "validPassword";
        when(mockDatabaseRepository.verifyPassword(validUserName, validPassword)).thenReturn(true);
        boolean result = services.callVerifyPassword(validUserName, validPassword);
        assertTrue(result);
    }

    @Test
    public void testVerifyAccount_InvalidCredentials_ReturnsFalse() {
        String invalidUserName = "invalidUser";
        String invalidPassword = "invalidPassword";
        when(mockDatabaseRepository.verifyPassword(invalidUserName, invalidPassword)).thenReturn(false);
        boolean result = services.callVerifyPassword(invalidUserName, invalidPassword);
        assertFalse(result);
    }
//    @Test
//    public void testFindByAmount_PositiveAmount_CallsRepositoryMethod() {
//
//        Double amount = 50000.0;
//        services.callFindByAmount(amount);
//        verify(mockDatabaseRepository, times(1)).findByAmount(amount);
//    }
//    @Test
//    public void testFindByType_ValidType_CallsRepositoryMethod() {
//        String validType = "withdrawal";
//        services.callFindByType(validType);
//        verify(mockDatabaseRepository, times(1)).findByType(validType);
//    }
//    @Test
//    public void testFindByDate_ValidDates_CallsRepositoryMethod() {
//        String startDate = "01/01/2024";
//        String endDate = "01/31/2024";
//        services.callFindByDate(startDate, endDate);
//        verify(mockDatabaseRepository, times(1)).findByDate(startDate, endDate);
//    }
}
