package com.springjdbc.driver.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")

//http://localhost:8082/profile/register
public class TransactionOfficialAPI {
    @Autowired
    TransactionOfficialService transactionOfficialService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public TransactionOfficial save(@RequestBody TransactionOfficial transactionOfficial){
        transactionOfficial.setPassword(passwordEncoder.encode(transactionOfficial.getPassword()));
        return transactionOfficialService.signingUp(transactionOfficial);
    }

}
