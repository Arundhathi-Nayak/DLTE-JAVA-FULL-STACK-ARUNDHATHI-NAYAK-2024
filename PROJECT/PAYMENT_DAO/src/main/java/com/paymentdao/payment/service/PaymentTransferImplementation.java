package com.paymentdao.payment.service;

import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.exception.PayeeException;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    public void deletePayeeAdded(int payeeId, Long senderAccountNumber, Long payeeAccountNumber, String payeeName) {

        String procedureCall="Call DELETE_PAYEE_NEW(?,?,?,?)";
        try {
            jdbcTemplate.update(procedureCall,payeeId,senderAccountNumber,payeeAccountNumber,payeeName);
            logger.info(resourceBundle.getString("payee.success"));
        } catch (DataAccessException e) {
            if (e.getLocalizedMessage().contains("ORA-20001")) {
                logger.warn(resourceBundle.getString("payee.notExists"));
                throw new PayeeException(resourceBundle.getString("payee.notExists"));
            }
            if (e.getLocalizedMessage().contains("ORA-20002")) {
                logger.warn(resourceBundle.getString("sender.notMatch"));
                throw new PayeeException(resourceBundle.getString("sender.notMatch"));
            }
            if (e.getLocalizedMessage().contains("ORA-20003")) {
                logger.warn(resourceBundle.getString("payee.notMatch"));
                throw new PayeeException(resourceBundle.getString("payee.notMatch"));
            }
            if (e.getLocalizedMessage().contains("ORA-20004")) {
                logger.warn(resourceBundle.getString("payeeName.notMatch"));
                throw new PayeeException(resourceBundle.getString("payeeName.notMatch"));
            }
        }


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
