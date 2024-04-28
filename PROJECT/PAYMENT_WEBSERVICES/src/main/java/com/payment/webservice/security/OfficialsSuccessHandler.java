package com.payment.webservice.security;

import com.paymentdao.payment.security.MyBankOfficials;
import com.paymentdao.payment.security.MyBankOfficialsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@Component
public class OfficialsSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    MyBankOfficialsService service;

    ResourceBundle resourceBundle= ResourceBundle.getBundle("account");

    Logger logger= LoggerFactory.getLogger(OfficialsSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        MyBankOfficials myBankOfficials= (MyBankOfficials) authentication.getPrincipal();
        if(myBankOfficials.getCustomerStatus().equalsIgnoreCase("Active")){
            if(myBankOfficials.getAttempts()>1){
                myBankOfficials.setAttempts(1);
                service.updateAttempts(myBankOfficials);
            }
           // super.setDefaultTargetUrl(resourceBundle.getString("default.url"));

            super.setDefaultTargetUrl("/payee/dash");

        }
        else{
            logger.warn(resourceBundle.getString("maxContact.admin"));
          //  super.setDefaultTargetUrl(resourceBundle.getString("failure.url"));
            super.setDefaultTargetUrl("/payee/?error=contact admin");
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
