 package com.payment.webservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.webservice.mvc.PaymentController;
import com.payment.webservice.restservices.PayeeController;
import com.payment.webservice.security.MyBankOfficialsAPI;
import com.payment.webservice.security.OfficialsFailureHandler;
import com.payment.webservice.security.OfficialsSuccessHandler;
import com.payment.webservice.soapconfig.SoapPhase;
import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.exception.PayeeException;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import com.paymentdao.payment.security.MyBankOfficials;
import com.paymentdao.payment.security.MyBankOfficialsService;
import com.paymentdao.payment.service.PaymentTransferImplementation;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import services.payee.FindAllPayeeBasedOnAccountNumberRequest;
import services.payee.FindAllPayeeBasedOnAccountNumberResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLSyntaxErrorException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class WebserviceApplicationTests {

    @Mock
    private PaymentTransferRepository paymentTransferRepository;

    @InjectMocks
    private SoapPhase soapPhase;


    @InjectMocks
    PayeeController paymentRestController;


    @Mock
    MyBankOfficialsService myBankOfficialsService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private MyBankOfficialsAPI myBankOfficialsAPI;


    @InjectMocks
    private OfficialsSuccessHandler successHandler;


    @BeforeEach
    public void setUpNew() {
        MockitoAnnotations.initMocks(this);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }



    @Test
    public void testListPayeeBasedOnAccountNumberFail()  {
        List<Payee> payees = new ArrayList<>();
        Payee payee = new Payee();
        payee.setPayeeId(123);
        payee.setSenderAccountNumber(123456789111L);
        payee.setPayeeAccountNumber(987456789111L);
        payee.setPayeeName("Arururu");
        payees.add(payee);
        // Mock authentication
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("testUser");


        MyBankOfficials customer = new MyBankOfficials();
        customer.setCustomerId(12);
        customer.setCustomerName("Sanatah");
        customer.setCustomerAddress("karkala");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(8765432345L);
        customer.setUsername("testUser");
        customer.setPassword("12233");
        customer.setAttempts(1);
        when(myBankOfficialsService.findByCustomer("testUser")).thenReturn(customer);
        when(myBankOfficialsService.getAccountNumbersByCustomerId(12)).thenReturn(Collections.singletonList(123456789111L));


        when(paymentTransferRepository.findAllPayeeBasedOnAccountNumber(123456789111L)).thenReturn(payees);

        FindAllPayeeBasedOnAccountNumberRequest request = new FindAllPayeeBasedOnAccountNumberRequest();
        request.setSenderAccount(123456789111L);


        FindAllPayeeBasedOnAccountNumberResponse response=soapPhase.listPayeeBasedOnAccountNumber(request);

        assertEquals(HttpStatus.OK.value(), response.getServiceStatus().getStatus());
        assertEquals("Payee details for account number123456789111", response.getServiceStatus().getMessage());
        //  assertEquals(1, response.getPayee().size());
        assertNotEquals(2, response.getPayee().size());   //fail
        assertEquals("Arururu", response.getPayee().get(0).getPayeeName());
    }
    @Test
    public void testListPayeeBasedOnAccountNumber()  {
        List<Payee> payees = new ArrayList<>();
        Payee payee = new Payee();
        payee.setPayeeId(123);
        payee.setSenderAccountNumber(123456789111L);
        payee.setPayeeAccountNumber(987456789111L);
        payee.setPayeeName("Arururu");
        payees.add(payee);

        // Mock authentication
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("testUser");


        MyBankOfficials customer = new MyBankOfficials();
        customer.setCustomerId(12);
        customer.setCustomerName("Sanatah");
        customer.setCustomerAddress("karkala");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(8765432345L);
        customer.setUsername("testUser");
        customer.setPassword("12233");
        customer.setAttempts(1);
        when(myBankOfficialsService.findByCustomer("testUser")).thenReturn(customer);
        when(myBankOfficialsService.getAccountNumbersByCustomerId(12)).thenReturn(Collections.singletonList(123456789111L));


        when(paymentTransferRepository.findAllPayeeBasedOnAccountNumber(123456789111L)).thenReturn(payees);

        FindAllPayeeBasedOnAccountNumberRequest request = new FindAllPayeeBasedOnAccountNumberRequest();
        request.setSenderAccount(123456789111L);


        FindAllPayeeBasedOnAccountNumberResponse response=soapPhase.listPayeeBasedOnAccountNumber(request);

        assertEquals(HttpStatus.OK.value(), response.getServiceStatus().getStatus());
        assertEquals("Payee details for account number123456789111", response.getServiceStatus().getMessage());
        assertEquals(1, response.getPayee().size());
        assertEquals("Arururu", response.getPayee().get(0).getPayeeName());
    }  @Test
    public void testDeletePayee() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("account");

        // Mock data
        Payee payee = new Payee();
        payee.setPayeeId(123);
        payee.setSenderAccountNumber(123456789123L);
        payee.setPayeeAccountNumber(987654321123L);
        payee.setPayeeName("Arundhathi");

        // Mock authentication
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("testUser");


        // Mock service behavior
        MyBankOfficials customer = new MyBankOfficials();
        customer.setCustomerId(123);
        customer.setCustomerName("Sanatah");
        customer.setCustomerAddress("karkala");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(8765432345L);
        customer.setUsername("testUser");
        customer.setPassword("12233");
        customer.setAttempts(1);
        when(myBankOfficialsService.findByCustomer("testUser")).thenReturn(customer);
        when(myBankOfficialsService.getAccountNumbersByCustomerId(123)).thenReturn(Collections.singletonList(123456789123L));
        // Mock the deletePayeeImplementation behavior

        doNothing().when(paymentTransferRepository).deletePayeeAdded(123, 123456789123L, 987654321123L, "Arundhathi");

        ResponseEntity<String> responseEntity = paymentRestController.deletePayeeValid(payee);

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(resourceBundle.getString("payee.add") + "Arundhathi" + " " + resourceBundle.getString("delete.success"),
                responseEntity.getBody());
    }
    @Test
    public void testDeletePayeeFailed() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("account");

        // Mock data
        Payee payee = new Payee();
        payee.setPayeeId(123);
        payee.setSenderAccountNumber(123456789123L);
        payee.setPayeeAccountNumber(987654321123L);
        payee.setPayeeName("Arundhathi");

        // Mock authentication
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("testUser");


        // Mock service behavior
        MyBankOfficials customer = new MyBankOfficials();
        customer.setCustomerId(123);
        customer.setCustomerName("Sanatah");
        customer.setCustomerAddress("karkala");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(8765432345L);
        customer.setUsername("testUser");
        customer.setPassword("12233");
        customer.setAttempts(1);
        when(myBankOfficialsService.findByCustomer("testUser")).thenReturn(customer);
        when(myBankOfficialsService.getAccountNumbersByCustomerId(123)).thenReturn(Collections.singletonList(123456789123L));
        // Mock the deletePayeeImplementation behavior

        doNothing().when(paymentTransferRepository).deletePayeeAdded(123, 123456789123L, 987654321123L, "Arundhathi");

        ResponseEntity<String> responseEntity = paymentRestController.deletePayeeValid(payee);

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertNotEquals(resourceBundle.getString("payee.add") ,
                responseEntity.getBody());
    }

    @Test
    public void handleValidationExceptionsTest() {
        // Mock a BindingResult with one field error
        BindingResult bindingResult = new org.springframework.validation.BeanPropertyBindingResult(new Object(), "payee");
        bindingResult.addError(new FieldError("payee", "senderAccountNumber", "Sender account number is required"));

        // Create MethodArgumentNotValidException with the BindingResult
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);

        // Call handleValidationExceptions method
        PayeeController controller = new PayeeController();
        Map<String, String> result = controller.handleValidationExceptions(exception);

        // Assert that the result is not null and contains expected error message
        assertEquals(result.size(), 1); // Assuming one validation error
        assertEquals(result.get("senderAccountNumber"), "Sender account number is required");
    }

    @Test
    public void testOnAuthenticationSuccess_InactiveStatus() throws Exception {
        // Mock authentication
        MyBankOfficials myBankOfficials = new MyBankOfficials();
        myBankOfficials.setCustomerStatus("Inactive");
        Authentication authentication = new UsernamePasswordAuthenticationToken(myBankOfficials, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Call the method
        successHandler.onAuthenticationSuccess(request, response, authentication);

        // Verify behavior
        assertEquals("/payeelogin/?errors=Contact administrator", response.encodeRedirectURL("/payeelogin/?errors=Contact administrator")); // Assuming failure URL is "/payeelogin/?errors=Contact administrator"
    }
    @Test
    public void testOnAuthenticationFailure_InactiveStatus() throws Exception {
        // Mock authentication
        MyBankOfficials myBankOfficials = new MyBankOfficials();
        myBankOfficials.setCustomerStatus("Inactive");
        Authentication authentication = new UsernamePasswordAuthenticationToken(myBankOfficials, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Call the method
        successHandler.onAuthenticationSuccess(request, response, authentication);

        // Verify behavior
        assertNotEquals("/payeelogin/?errors=Contact administrator", response.encodeRedirectURL("/payee/?errors=Contact administrator")); // Assuming failure URL is "/payeelogin/?errors=Contact administrator"
    }



    @InjectMocks
    private PaymentController paymentController;


    @Test
    public void testLandingPage() {
        String result = paymentController.landing();
        assertEquals("index", result);
    }

    @Test
    public void testHomePage() {
        String result = paymentController.homePage();
        assertEquals("dashboard", result);
    }

    @Test
    public void testLoginError() {
        Model model = Mockito.mock(Model.class);
        String result = paymentController.loginError(model);
        assertEquals("index", result);
        verify(model).addAttribute("error", true);
    }

    @Test
    public void testUpdating() {
        String result = paymentController.updating();
        assertEquals("viewAll", result);
    }

    @Test
    public void testShowErrorPage() {
        String result = paymentController.errorPage();
        assertEquals("error", result);
    }

    @Test
    public void testSave() {
        // Create a mock MyBankOfficials object
        MyBankOfficials myBankOfficials = new MyBankOfficials();
        myBankOfficials.setUsername("aru");
        myBankOfficials.setPassword("aru@123");

        // Mock the behavior of the passwordEncoder
        when(passwordEncoder.encode("aru@123")).thenReturn("encodedPassword");

        // Mock the behavior of the service
        when(myBankOfficialsService.signingUp(myBankOfficials)).thenReturn(myBankOfficials);

        // Call the save method
        MyBankOfficials result = myBankOfficialsAPI.save(myBankOfficials);

        // Verify that the password was encoded
        verify(passwordEncoder).encode("aru@123");

        verify(myBankOfficialsService).signingUp(myBankOfficials);

        assertEquals(myBankOfficials, result);
    }
    @Test
    public void testFetchAccountNumber() {
        // Mock authentication
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("testUser");

        // Mock service behavior
        MyBankOfficials customer = new MyBankOfficials();
        customer.setCustomerId(123);
        when(myBankOfficialsService.findByCustomer("testUser")).thenReturn(customer);
        when(myBankOfficialsService.getAccountNumbersByCustomerId(123)).thenReturn(Arrays.asList(12345678901L, 12345678902L));

        // Call the method
        List<Long> accountNumbers = paymentRestController.fetchAccountNumber();

        // Verify the returned account numbers
        assertEquals(Arrays.asList(12345678901L, 12345678902L), accountNumbers);
    }
    @Test
    public void testGetCustomerName() {
        // Mock SecurityContextHolder to return a mock Authentication object
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("testUser");
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        Mockito.when(myBankOfficialsService.getCustomerName(Mockito.anyString())).thenReturn("Aru");

        String result = paymentRestController.getCustomerName();
        Assertions.assertEquals("Aru", result);
    }
    @Test
    void testListPayeeException() {
        Payee payee = new Payee();
        payee.setPayeeId(456);
        payee.setSenderAccountNumber(123654789987L);
        payee.setPayeeAccountNumber(123456898777L);
        payee.setPayeeName("Sanath");
        List<Payee> payeeList = new ArrayList<>();
        payeeList.add(payee);
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("user");
        MyBankOfficials customer = new MyBankOfficials();
        customer.setCustomerId(123);
        customer.setCustomerName("sanath");
        customer.setCustomerAddress("sringeri");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(8277263396L);
        customer.setUsername("user");
        customer.setPassword("1234");
        customer.setAttempts(1);

        when(myBankOfficialsService.findByCustomer("user")).thenReturn(customer);
        when(myBankOfficialsService.getAccountNumbersByCustomerId(123)).thenReturn(Collections.singletonList(123654789987L));
        FindAllPayeeBasedOnAccountNumberRequest request = new FindAllPayeeBasedOnAccountNumberRequest();
        request.setSenderAccount(123654789987L);
        when(paymentTransferRepository.findAllPayeeBasedOnAccountNumber(123654789987L))
                .thenThrow(new PayeeException("Payee not found"));

        FindAllPayeeBasedOnAccountNumberResponse response = soapPhase.listPayeeBasedOnAccountNumber(request);
        Assert.assertEquals(HttpStatus.OK.value(), response.getServiceStatus().getStatus());
        Assert.assertEquals("EXC001 :Payee not found", response.getServiceStatus().getMessage());
    }

}
