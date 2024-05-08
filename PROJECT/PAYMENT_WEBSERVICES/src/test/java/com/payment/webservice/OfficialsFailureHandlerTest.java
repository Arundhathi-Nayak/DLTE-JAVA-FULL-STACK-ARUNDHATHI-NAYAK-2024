package com.payment.webservice;

import com.payment.webservice.security.OfficialsFailureHandler;
import com.payment.webservice.security.OfficialsSuccessHandler;
import com.paymentdao.payment.security.MyBankOfficials;
import com.paymentdao.payment.security.MyBankOfficialsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
@SpringBootTest
public class OfficialsFailureHandlerTest {
    @Mock
    private MyBankOfficialsService myBankOfficialsService;

    @InjectMocks
    private OfficialsSuccessHandler successHandler;

    @InjectMocks
    private OfficialsFailureHandler failureHandler;

    @Test
    public void testAuthenticationFailureAttemptsExceeded() throws IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        AuthenticationException exception = new BadCredentialsException("Invalid credentials");

        String username = "testUser";
        MyBankOfficials myBankOfficials = new MyBankOfficials();
        myBankOfficials.setUsername(username);
        myBankOfficials.setCustomerStatus("Active"); // Assuming status allows authentication
        myBankOfficials.setAttempts(3); // Assuming maximum attempts are 3
        when(myBankOfficialsService.findByCustomer(username)).thenReturn(myBankOfficials);

        failureHandler.onAuthenticationFailure(request, response, exception);

        assertEquals("/payeelogin/?error=User not exists",response.getRedirectedUrl());
    }


}
