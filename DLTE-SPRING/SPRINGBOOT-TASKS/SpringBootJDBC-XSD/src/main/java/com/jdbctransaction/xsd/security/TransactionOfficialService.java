package com.jdbctransaction.xsd.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TransactionOfficialService implements UserDetailsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public TransactionOfficial signingUp(TransactionOfficial transactionOfficial){
        int ack=jdbcTemplate.update("insert into Transaction_credential(name, username, password, email, contact, aadhaar,role) values(?,?,?,?,?,?,?)",new Object[]{
                transactionOfficial.getName(),transactionOfficial.getUsername(),transactionOfficial.getPassword(),
                transactionOfficial.getEmail(),transactionOfficial.getContact(),transactionOfficial.getAadhaar(),transactionOfficial.getRole()
        });
        return transactionOfficial;
    }
    public TransactionOfficial findByUsername(String username){
        TransactionOfficial transactionOfficial=jdbcTemplate.queryForObject("select * from Transaction_credential where username=?",
                new Object[]{username},new BeanPropertyRowMapper<>(TransactionOfficial.class));
        return transactionOfficial;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TransactionOfficial transactionOfficial=findByUsername(username);
        if(transactionOfficial==null)
            throw new UsernameNotFoundException(username);
        return transactionOfficial;
    }
}
