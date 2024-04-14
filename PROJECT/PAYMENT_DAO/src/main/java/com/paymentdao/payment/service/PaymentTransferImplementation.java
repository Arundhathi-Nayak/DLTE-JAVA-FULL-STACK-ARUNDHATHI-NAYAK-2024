package com.paymentdao.payment.service;

import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.exception.PayeeException;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
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
        logger.info(resourceBundle.getString("payee.success"));

        if(payees.size()==0){
            logger.warn(resourceBundle.getString("no.payee"));
            throw new PayeeException(resourceBundle.getString("no.payee")+accountNumber);
        }
        return payees;
    }

    @Override
    public void deletePayee(int payeeId, Long senderAccountNumber, Long payeeAccountNumber, String payeeName) {
//        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
//                .withProcedureName("DELETE_PAYEE");
//
//        SqlParameterSource inParams = new MapSqlParameterSource()
//                .addValue("p_payee_id", payeeId)
//                .addValue("p_sender_account_number", senderAccountNumber)
//                .addValue("p_payee_account_number", payeeAccountNumber);
        String procedureCall="Call DELETE_PAYEE(?,?,?,?)";

        try {
           // simpleJdbcCall.execute(inParams);
            jdbcTemplate.update(procedureCall,payeeId,senderAccountNumber,payeeAccountNumber,payeeName);
            logger.info(resourceBundle.getString("payee.success"));
        } catch (DataAccessException e) {
            if (e.getLocalizedMessage().contains("ORA-20002")) {
                logger.warn(resourceBundle.getString("Payee.not.found"));
                //throw new PayeeException(e.getMessage());
                throw new PayeeException(resourceBundle.getString("Payee.not.found"));
            }
        }
    }

    // listing all payee details
    @Override
    public List<Payee> findAllPayee()  {
        List<Payee> payees;
        payees = jdbcTemplate.query("select * from MYBANK_APP_Payee",
//                new BeanPropertyRowMapper<>(Payee.class)
                    new PayeeMapper());
        logger.info(resourceBundle.getString("payee.success"));

        if(payees.size()==0){
            logger.warn(resourceBundle.getString("no.payee"));
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
