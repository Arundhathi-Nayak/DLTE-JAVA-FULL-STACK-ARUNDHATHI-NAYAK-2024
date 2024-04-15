package com.payment.webservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//     {
//             "customerId": 1,
//             "customerName": "Avinash",
//             "customerAddress": "Shanthala",
//             "customerStatus": "Active",
//             "customerContact": 6643456789,
//             "username": "avinu",
//             "password": "avinu",
//             "attempts":1
//
//             }
@RestController
@RequestMapping("/profile")
public class MyBankOfficialsAPI {
    @Autowired
    MyBankOfficialsService service;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public MyBankOfficials save(@RequestBody MyBankOfficials myBankOfficials){
        myBankOfficials.setPassword(passwordEncoder.encode(myBankOfficials.getPassword()));
        return service.signingUp(myBankOfficials);
    }
}
