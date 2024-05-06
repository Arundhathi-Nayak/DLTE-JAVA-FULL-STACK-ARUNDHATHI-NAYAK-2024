package com.paymentdao.payment;
import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.exception.PayeeException;
import com.paymentdao.payment.security.MyBankOfficials;
import com.paymentdao.payment.security.MyBankOfficialsService;
import com.paymentdao.payment.service.PaymentTransferImplementation;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PaymentApplicationTests {

    @Mock
    JdbcTemplate jdbcTemplate;
    @InjectMocks
    PaymentTransferImplementation paymentTransferImplementation;
    @InjectMocks
    private MyBankOfficialsService myBankOfficialsService;


    @Test

    void testFindAllBasedOnAccount()  {
        Payee payee1=new Payee(101,213456789654L,543212345678L,"Eeksha");
        Payee payee2=new Payee(102,765423123564L,765432345678L,"Divija");
        Payee payee3=new Payee(103,213456789654L,987654321234L,"Arundhathi");
        Payee payee4=new Payee(104,765423123564L,543567543456L,"Anu");
        List<Payee> payees=Stream.of(payee1,payee3).collect(Collectors.toList());

        when(jdbcTemplate.query(anyString(),any(Object[].class),
                any(PaymentTransferImplementation.PayeeMapper.class))).thenReturn(payees);

        // Call the method under test
        List<Payee> actualList = paymentTransferImplementation.findAllPayeeBasedOnAccountNumber(213456789654L);
        // Assert that the actual list contains the expected payee account number
        assertEquals(543212345678L, actualList.get(0).getPayeeAccountNumber());
    }
  @Test
    void testFindAllBasedOnAccount_notFound()  {
        Payee payee1=new Payee(101,213456789654L,543212345678L,"Eeksha");
        Payee payee2=new Payee(102,765423123564L,765432345678L,"Divija");
        Payee payee3=new Payee(103,213456789654L,987654321234L,"Arundhathi");
        Payee payee4=new Payee(104,765423123564L,543567543456L,"Anu");
        List<Payee> payees=Stream.of(payee1,payee3).collect(Collectors.toList());

        when(jdbcTemplate.query(anyString(),any(Object[].class),
                any(PaymentTransferImplementation.PayeeMapper.class))).thenReturn(payees);

        // Call the method under test
        List<Payee> actualList = paymentTransferImplementation.findAllPayeeBasedOnAccountNumber(313456789654L);
        // Assert that the actual list contains the expected payee account number
        assertNotEquals(343212345678L, actualList.get(0).getPayeeAccountNumber());
    }
    @Test
    void testPayeeException() {
        when(jdbcTemplate.query(anyString(), any(PaymentTransferImplementation.PayeeMapper.class))).thenReturn(Collections.emptyList());
        try {
            paymentTransferImplementation.findAllPayeeBasedOnAccountNumber(123434567897L);
        } catch (PayeeException e) {
         //   assertEquals("Payee exception", e.getMessage());
            assertNotEquals("No payee", e.getMessage());
        }

    }
    @Test
    void testPayeeExceptionEquals() {
        when(jdbcTemplate.query(anyString(), any(PaymentTransferImplementation.PayeeMapper.class))).thenReturn(Collections.emptyList());
        try {
            paymentTransferImplementation.findAllPayeeBasedOnAccountNumber(123434567897L);
        } catch (PayeeException e) {
            //   assertEquals("Payee exception", e.getMessage());
            assertEquals("No payee found with given account number : 123434567897", e.getMessage());
        }

    }
    @Test
    void testDeletePayeeNotFound() {
        int payeeId = 1;
        Long senderAccountNumber = 123456789L;
        Long payeeAccountNumber = 987654321L;
        String payeeName = "Arundhathi";
        doThrow(new DataAccessException("ORA-20001") {}).when(jdbcTemplate).update(anyString(), eq(payeeId), eq(senderAccountNumber), eq(payeeAccountNumber), eq(payeeName));

        PayeeException exception = assertThrows(PayeeException.class, () -> {
            paymentTransferImplementation.deletePayeeAdded(payeeId, senderAccountNumber, payeeAccountNumber, payeeName);
        });

        assertNotEquals("found", exception.getMessage());
    }
    @Test
    void testPayeeFound() {
        int payeeId = 1;
        Long senderAccountNumber = 123456789L;
        Long payeeAccountNumber = 987651L;
        String payeeName = "Arundhathi";

        doThrow(new DataAccessException("ORA-20001") {}).when(jdbcTemplate).update(anyString(), eq(payeeId), eq(senderAccountNumber), eq(payeeAccountNumber), eq(payeeName));

        PayeeException exception = assertThrows(PayeeException.class, () -> {
            paymentTransferImplementation.deletePayeeAdded(payeeId, senderAccountNumber, payeeAccountNumber, payeeName);
        });

        assertEquals("No payee found", exception.getMessage());
    }
    @Test
    void testPayeeNotFound() {
        int payeeId = 1;
        Long senderAccountNumber = 12345678988L;
        Long payeeAccountNumber = 987651777777L;
        String payeeName = "Arundhathi";

        doThrow(new DataAccessException("ORA-20001") {}).when(jdbcTemplate).update(anyString(), eq(payeeId), eq(senderAccountNumber), eq(payeeAccountNumber), eq(payeeName));

        PayeeException exception = assertThrows(PayeeException.class, () -> {
            paymentTransferImplementation.deletePayeeAdded(payeeId, senderAccountNumber, payeeAccountNumber, payeeName);
        });

        assertNotEquals("Failed to delete payee", exception.getMessage());
    }

    @Test
    public void testGetAccountNumbersByCustomerId_ExistingCustomerId_ReturnsAccountNumbers() {
        int customerId = 1;
        List<Long> expectedAccountNumbers = Collections.singletonList(123456L);
        when(jdbcTemplate.queryForList(any(String.class), any(Object[].class), any(Class.class))).thenReturn(expectedAccountNumbers);

        List<Long> accountNumbers = myBankOfficialsService.getAccountNumbersByCustomerId(customerId);

        assertEquals(expectedAccountNumbers, accountNumbers);
    }


    @Test
    public void testDeletePayeeAdded_SuccessfulDeletion() {
        // Mock successful deletion
        when(jdbcTemplate.update(any(String.class), any(Object[].class))).thenReturn(1);

        // No exception should be thrown
        paymentTransferImplementation.deletePayeeAdded(1, 123456L, 789012L, "PayeeName");
    }

    @Test
    public void testPasswordMatch() {
        MyBankOfficialsService myBankUsersServices = mock(MyBankOfficialsService.class);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Setup test data
        String username = "testUser";
        String rawPassword = "password123";
        String encodedPassword =passwordEncoder.encode(rawPassword);

        MyBankOfficials customer = new MyBankOfficials();
        customer.setUsername(username);
        customer.setPassword(encodedPassword);
        when(myBankUsersServices.loadUserByUsername(username))
                .thenReturn(customer);
        UserDetails userDetails = myBankUsersServices.loadUserByUsername(username);

        String enteredPassword="password123";
        assertTrue(passwordEncoder.matches(enteredPassword, userDetails.getPassword()));
    }
    @Test
    void testSigningUp() {
        MyBankOfficials customer = new MyBankOfficials(123,"Aru","udupi","active",8745213698L,"aru","aru123",1);

        when(jdbcTemplate.update(anyString(),eq(customer.getCustomerName()),eq(customer.getCustomerAddress()),eq(customer.getCustomerStatus()),eq(customer.getCustomerContact()),eq(customer.getUsername()),eq(customer.getPassword()))).thenReturn(1);

        MyBankOfficials result = myBankOfficialsService.signingUp(customer);

        assertEquals(customer.getCustomerId()+customer.getAttempts(),result.getCustomerId()+customer.getAttempts());
        assertEquals(customer, result);
    }

    @Test
    void testSigningUpUserDetails() {
        MyBankOfficials customer = new MyBankOfficials(123,"Aru","udupi","active",8745213698L,"aru","aru123",1);

        when(jdbcTemplate.update(anyString(),eq(customer.getCustomerName()),eq(customer.getCustomerAddress()),eq(customer.getCustomerStatus()),eq(customer.getCustomerContact()),eq(customer.getUsername()),eq(customer.getPassword()))).thenReturn(1);

        MyBankOfficials result = myBankOfficialsService.signingUp(customer);

        assertEquals(customer.getAuthorities(),result.getAuthorities());
        assertEquals(customer.isAccountNonExpired(),result.isAccountNonExpired());
        assertEquals(customer.isAccountNonLocked(),result.isAccountNonLocked());
        assertEquals(customer.isCredentialsNonExpired(),result.isCredentialsNonExpired());
        assertEquals(customer.isEnabled(),result.isEnabled());
    }
    @Test
    void testFindAllUsername() {
        MyBankOfficials customer = new MyBankOfficials(123,"Aru","udupi","active",8745213698L,"aru","aru123",1);
        MyBankOfficials customer2 = new MyBankOfficials(123,"Aksh","udupi","active",8945213698L,"aksh","aksh123",1);

        List<MyBankOfficials> customerList = Stream.of(customer,customer2).collect(Collectors.toList());


        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(customerList);

        List<MyBankOfficials> result = myBankOfficialsService.findByCustomerName();

        assertEquals(customerList.get(0).getUsername(), result.get(0).getUsername());

        assertNotEquals(customerList.get(0).getAttempts(),result.get(0).getMaxAttempt());
        verify(jdbcTemplate, times(1)).query(anyString(), any(BeanPropertyRowMapper.class));
    }
    @Test
    void testUpdateAttemptsOfCustomer() {
        MyBankOfficials customer = new MyBankOfficials(123,"Aru","udupi","active",8745213698L,"aru","aru123",1);


        when(jdbcTemplate.update(anyString(), eq(customer.getAttempts()),eq(customer.getUsername()))).thenReturn(1);

        assertDoesNotThrow(() -> {
            myBankOfficialsService.updateAttempts(customer);
        });

        verify(jdbcTemplate, times(1)).update(anyString(), eq(customer.getAttempts()),eq(customer.getUsername()));

    }
    @Test
    void testUpdateStatusOfCustomer() {
        MyBankOfficials customer = new MyBankOfficials(123,"Aru","udupi","active",8745213698L,"aru","aru123",1);


        when(jdbcTemplate.update(anyString(),eq(customer.getUsername()))).thenReturn(1);

        assertDoesNotThrow(() -> {
            myBankOfficialsService.updateStatus(customer);
        });

        verify(jdbcTemplate, times(1)).update(anyString(),eq(customer.getUsername()));

    }
    @Test
    void testGetAccountListSuccess() {
        int customerId = 123;
        List<Long> accountList = Collections.singletonList(456L);

        when(jdbcTemplate.queryForList(anyString(), any(Object[].class), eq(Long.class))).thenReturn(accountList);

        List<Long> result = myBankOfficialsService.getAccountNumbersByCustomerId(customerId);

        assertNotNull(result);
        assertEquals(456L, result.get(0));

    }

    @Test
    void testGetCustomerName() {
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(String.class)))
                .thenReturn("John Doe");
        String customerName = myBankOfficialsService.getCustomerName("johndoe");

        // Verifying that the jdbcTemplate.queryForObject() method was called with the correct parameters
        verify(jdbcTemplate).queryForObject(anyString(), any(Object[].class), eq(String.class));

        // Asserting that the returned customer name is correct
        assertEquals("John Doe", customerName);
    }
    @Test
    void testDeletePayeeAdded_PayeeNotExists() {
        int payeeId = 1;
        Long senderAccountNumber = 123456789L;
        Long payeeAccountNumber = 987654321L;
        String payeeName = "Alice";

        doThrow(new DataAccessException("ORA-20001") {}).when(jdbcTemplate).update(anyString(), anyInt(), anyLong(), anyLong(), anyString());

        assertThrows(PayeeException.class, () -> paymentTransferImplementation.deletePayeeAdded(payeeId, senderAccountNumber, payeeAccountNumber, payeeName));
        verify(jdbcTemplate).update("Call DELETE_PAYEE_NEW(?,?,?,?)", payeeId, senderAccountNumber, payeeAccountNumber, payeeName);
    }
    @Test
    void testDeletePayeeAdded_PayeeNameNotExists() {
        Payee payee=new Payee();
        payee.setPayeeId(1);
        payee.setSenderAccountNumber(123456789345L);
        payee.setPayeeAccountNumber(987654321333L);
        payee.setPayeeName("sanath");

        doThrow(new DataAccessException("ORA-20002") {}).when(jdbcTemplate).update(anyString(), anyInt(), anyLong(), anyLong(), anyString());

        assertThrows(PayeeException.class, () -> paymentTransferImplementation.deletePayeeAdded(payee.getPayeeId(),payee.getSenderAccountNumber(), payee.getPayeeAccountNumber(), payee.getPayeeName()));
        verify(jdbcTemplate).update("Call DELETE_PAYEE_NEW(?,?,?,?)", payee.getPayeeId(), payee.getSenderAccountNumber(), payee.getPayeeAccountNumber(), payee.getPayeeName());
    }
    @Test
    void testFindByUsernameNotExistingCustomer() {
        MyBankOfficials customer = new MyBankOfficials(123,"Aru","Udupi","active",8745213698L,"aru","aru123",1);
        MyBankOfficials customer2=new MyBankOfficials();
        customer2.setCustomerId(14);
        customer2.setCustomerName("eeksha");
        customer2.setCustomerAddress("bedra");
        customer2.setCustomerStatus("active");
        customer2.setCustomerContact(78445213698L);
        customer2.setUsername("eeksha");
        customer2.setPassword("eeksha123");
        customer2.setAttempts(1);

        List<MyBankOfficials> customerList = Stream.of(customer,customer2).collect(Collectors.toList());

        when(myBankOfficialsService.findByCustomerName()).thenReturn(customerList);


        assertThrows(UsernameNotFoundException.class, () -> myBankOfficialsService.findByCustomer("eeksh"));

    }

}
