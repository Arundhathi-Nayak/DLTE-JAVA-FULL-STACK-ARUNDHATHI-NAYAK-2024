package com.paymentdao.payment.security;

import com.paymentdao.payment.exception.PayeeException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

@Service
public class MyBankOfficialsService implements UserDetailsService {
    @Autowired
    JdbcTemplate jdbcTemplate;
    org.slf4j.Logger logger= LoggerFactory.getLogger( MyBankOfficialsService.class);

    ResourceBundle resourceBundle= ResourceBundle.getBundle("account");

    public MyBankOfficials signingUp(MyBankOfficials myBankOfficials){
//        int ack = jdbcTemplate.update("insert into CUSTOMER_CREDENTIALS values(?,?,?,?,?,?,?,?)",new Object[]{
//                myBankOfficials.getCustomerId(),myBankOfficials.getCustomerName(),myBankOfficials.getCustomerAddress(),myBankOfficials.getCustomerStatus(),
//               myBankOfficials.getCustomerContact(),myBankOfficials.getUsername(),myBankOfficials.getPassword(),myBankOfficials.getAttempts()
//        });
        int ack = jdbcTemplate.update("insert into MYBANK_APP_CUSTOMER   values(CUSTOMERID_SEQ.nextval,?,?,?,?,?,?,?)",new Object[]{
                myBankOfficials.getCustomerName(),myBankOfficials.getCustomerAddress(),myBankOfficials.getCustomerStatus(),
                myBankOfficials.getCustomerContact(),myBankOfficials.getUsername(),myBankOfficials.getPassword(),myBankOfficials.getAttempts()
        });
        return myBankOfficials;
    }


    public void updateAttempts(MyBankOfficials myBankOfficials){
        jdbcTemplate.update("update MYBANK_APP_CUSTOMER set attempts=? where username=?",
                new Object[]{myBankOfficials.getAttempts(),myBankOfficials.getUsername()});
        logger.info(resourceBundle.getString("attempts.update"));
    }

    public void updateStatus(MyBankOfficials myBankOfficials){
        jdbcTemplate.update("update MYBANK_APP_CUSTOMER set CUSTOMER_STATUS='Inactive' where username=?",
                new Object[]{myBankOfficials.getUsername()});
        logger.info(resourceBundle.getString("status.change"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyBankOfficials officials = findByCustomer(username);
        if(officials==null)
            throw new UsernameNotFoundException(username);
        return officials;
    }
    public List<MyBankOfficials> findByCustomerName(){
        List<MyBankOfficials> officialsList= jdbcTemplate.query("select * from MYBANK_APP_CUSTOMER",
             new BeanPropertyRowMapper<>(MyBankOfficials.class));
        return officialsList;
    }

    public MyBankOfficials findByCustomer(String username){
        List<MyBankOfficials> officialsList=findByCustomerName();
        MyBankOfficials myBankOfficials=officialsList.stream().filter(myBankOfficials1 -> myBankOfficials1.getUsername().equals(username)).findFirst().orElse(null);
        if(myBankOfficials==null)
            throw new UsernameNotFoundException(username);
        return myBankOfficials;
    }


    public List<Long> getAccountNumbersByCustomerId(int customerId) {

        String sql = "SELECT a.ACCOUNT_NUMBER " +
                "FROM MYBANK_APP_CUSTOMER c " +
                "JOIN MYBANK_APP_ACCOUNT a ON c.CUSTOMER_ID = a.CUSTOMER_ID " +
                "WHERE c.CUSTOMER_ID = ?";
        try {
          //  logger.info("Account numbers for customer ID " + customerId + ": " + accountNumbers);
            return jdbcTemplate.queryForList(sql, new Object[]{customerId}, Long.class);

        //    return accountNumbers;
        } catch (EmptyResultDataAccessException e) {
            logger.error(resourceBundle.getString("no.accountCustomer") + customerId, e);
            throw new PayeeException(resourceBundle.getString("no.accountCustomer")+ customerId);
        } catch (Exception e) {
            logger.error(resourceBundle.getString("fetch.exception") + customerId, e);
            return Collections.emptyList(); // Or handle the error in an appropriate way
        }
    }

    public String getCustomerName(String user) {
        try {
            String sql = "SELECT c.CUSTOMER_NAME FROM mybank_app_customer c WHERE c.username =  ?";
            System.out.println(sql);
            return jdbcTemplate.queryForObject(sql, new Object[]{user}, String.class);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

}
