package com.payment.webservice.mvc;

import com.paymentdao.payment.security.MyBankOfficialsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ResourceBundle;

@Controller
@RequestMapping("/payeelogin")
public class PaymentController {
    @Autowired
    MyBankOfficialsService myBankService;

    Logger logger= LoggerFactory.getLogger(PaymentController.class);

    ResourceBundle bundle=ResourceBundle.getBundle("account");

    @GetMapping("/")
    public String landing(){
        return "index";
    }

    @RequestMapping(value="/dash", method = RequestMethod.GET)
    public String homePage(){
        return "dashboard";
    }

    @PostMapping("/")
    public String loginError(Model model) {
        model.addAttribute("error", true);
        return "index";
    }
//    @GetMapping("/error")
//    public String showErrorPage() {
//        return "error";
//    }

}
