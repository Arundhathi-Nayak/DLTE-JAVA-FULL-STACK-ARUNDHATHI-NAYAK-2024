package com.paymentdao.payment.service;

import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.exception.PayeeException;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.ResourceBundle;


@Service
public class PaymentTransferImplementation implements PaymentTransferRepository {
    ResourceBundle resourceBundle= ResourceBundle.getBundle("account");
    org.slf4j.Logger logger=LoggerFactory.getLogger( PaymentTransferImplementation.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    // finding all payee based on account number
    @Override
    public List<Payee> findAllPayeeBasedOnAccountNumber(Long accountNumber)  {
        List<Payee> payees;
        payees = jdbcTemplate.query("select * from MYBANK_APP_Payee where sender_account_number=?",
                    new Object[]{accountNumber},
//                new BeanPropertyRowMapper<>(Payee.class)
                    new PayeeMapper());

        if(payees.size()==0){
            logger.info(resourceBundle.getString("no.payee"));
            throw new PayeeException(resourceBundle.getString("no.payee")+accountNumber);
        }
        return payees;
    }

    // listing all payee details
    @Override
    public List<Payee> findAllPayee()  {
        List<Payee> payees;
        payees = jdbcTemplate.query("select * from MYBANK_APP_Payee",
//                new BeanPropertyRowMapper<>(Payee.class)
                    new PayeeMapper());

        if(payees.size()==0){
            logger.info(resourceBundle.getString("no.payee"));
            throw new PayeeException(resourceBundle.getString("no.payee"));

        }
        return payees;
    }

    public class PayeeMapper implements RowMapper<Payee> {
        @Override
        public Payee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Payee payee=new Payee();
            payee.setPayeeId(rs.getInt(1));
            payee.setSenderAccountNumber(rs.getLong(2));
            payee.setPayeeAccountNumber(rs.getLong("payee_account_number"));
            payee.setPayeeName(rs.getString("payee_name"));
            return payee;
        }
    }
}
