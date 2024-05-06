package com.payment.webservice.security;

import com.paymentdao.payment.security.MyBankOfficials;
import com.paymentdao.payment.security.MyBankOfficialsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@Component
public class OfficialsFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    MyBankOfficialsService service;

    Logger logger= LoggerFactory.getLogger(OfficialsFailureHandler.class);

    ResourceBundle resourceBundle= ResourceBundle.getBundle("account");


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws  IOException, ServletException {
        String username = request.getParameter("username");
        try {
        MyBankOfficials myBankOfficials = service.findByCustomer(username);
            if (myBankOfficials != null) {
                if (myBankOfficials.getCustomerStatus().equalsIgnoreCase("Active")) {
                    if (myBankOfficials.getAttempts() < myBankOfficials.getMaxAttempt()) {
                        myBankOfficials.setAttempts(myBankOfficials.getAttempts() + 1);
                        service.updateAttempts(myBankOfficials);
                        logger.warn(resourceBundle.getString("invalid.credentials"));
                        exception = new LockedException((4-myBankOfficials.getAttempts()) + " " + resourceBundle.getString("attempts.taken"));
                        String err = myBankOfficials.getAttempts().toString() + " " + exception.getMessage();
                        logger.warn(err);
                        super.setDefaultFailureUrl("/payeelogin/?error=" + err);
                    } else {
                        service.updateStatus(myBankOfficials);
                        logger.warn(resourceBundle.getString("account.suspend"));
                        exception = new LockedException(resourceBundle.getString("account.suspend"));
                        super.setDefaultFailureUrl("/payeelogin/?error=" + exception.getMessage());
                    }
                }
//            else{
//                logger.warn(resourceBundle.getString("admin.contact"));
//            }
                else {
                    super.setDefaultFailureUrl("/payeelogin/?error=User not exists");
                }
            }
        }catch (UsernameNotFoundException e){
            logger.info(e.toString());
            logger.
                    warn(resourceBundle.getString("account.suspend"));
            exception = new LockedException("Username not found");
            super.setDefaultFailureUrl("/payeelogin/?error=" + exception.getMessage());
        }
     //   super.setDefaultFailureUrl(resourceBundle.getString("login.error"));
        super.onAuthenticationFailure(request, response, exception);
    }
}
