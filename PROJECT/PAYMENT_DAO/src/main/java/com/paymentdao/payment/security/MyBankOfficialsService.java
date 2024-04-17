package com.paymentdao.payment.security;

import com.paymentdao.payment.exception.PayeeException;
import com.paymentdao.payment.service.DeletePayeeImplementation;
import org.slf4j.Logger;
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

@Service
public class MyBankOfficialsService implements UserDetailsService {
    @Autowired
    JdbcTemplate jdbcTemplate;
    org.slf4j.Logger logger= LoggerFactory.getLogger( MyBankOfficialsService.class);

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

    public MyBankOfficials findByUsername(String username){
        MyBankOfficials myBankOfficials = jdbcTemplate.queryForObject("select * from MYBANK_APP_CUSTOMER where username=?",
                new Object[]{username},new BeanPropertyRowMapper<>(MyBankOfficials.class));
        return myBankOfficials;
    }

    public void updateAttempts(MyBankOfficials myBankOfficials){
        jdbcTemplate.update("update MYBANK_APP_CUSTOMER set attempts=? where username=?",
                new Object[]{myBankOfficials.getAttempts(),myBankOfficials.getUsername()});
        logger.info("Attempts are updated");
    }

    public void updateStatus(MyBankOfficials myBankOfficials){
        jdbcTemplate.update("update MYBANK_APP_CUSTOMER set CUSTOMER_STATUS='Inactive' where username=?",
                new Object[]{myBankOfficials.getUsername()});
        logger.info("Status has changed");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyBankOfficials officials = findByUsername(username);
        if(officials==null)
            throw new UsernameNotFoundException(username);
        return officials;
    }

//    public List getAccountNumbersByCustomerId(int customerId) {
//        List<Long> dummy;
////        String sql = "SELECT a.ACCOUNT_NUMBER " +
////                "FROM MYBANK_APP_CUSTOMER c " +
////                "JOIN MYBANK_APP_ACCOUNT a ON c.CUSTOMER_ID = a.CUSTOMER_ID " +
////                "WHERE c.CUSTOMER_ID = ?";
//        String sql = "SELECT a.ACCOUNT_NUMBER " +
//                "FROM MYBANK_APP_CUSTOMER c " +
//                "JOIN MYBANK_APP_ACCOUNT a ON c.CUSTOMER_ID = a.CUSTOMER_ID " +
//                "WHERE c.CUSTOMER_ID = ?";
//        try {
//            dummy= jdbcTemplate.queryForList(sql, new Object[]{customerId}, Long.class);
//            logger.info("account number------->"+dummy);
//            return dummy;
//
//        } catch (EmptyResultDataAccessException e) {
//            throw new PayeeException("No account found for customer ID: " + customerId);
//        } catch (Exception e) {
//            // Handle other exceptions
//            e.printStackTrace();
//            return Collections.emptyList(); // Or handle the error in an appropriate way
//        }
//    }
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
        logger.error("No account found for customer ID: " + customerId, e);
        throw new PayeeException("No account found for customer ID: " + customerId);
    } catch (Exception e) {
        logger.error("Exception occurred while fetching account numbers for customer ID: " + customerId, e);
        return Collections.emptyList(); // Or handle the error in an appropriate way
    }
}

}
