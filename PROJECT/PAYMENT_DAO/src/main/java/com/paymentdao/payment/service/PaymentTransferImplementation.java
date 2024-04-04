package com.paymentdao.payment.service;

import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.exception.PayeeException;
import com.paymentdao.payment.remote.PaymentTransferRepository;
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
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<Payee> findAllPayee(Long accountNumber) throws SQLSyntaxErrorException {
        List<Payee> payees;
        try {
            payees = jdbcTemplate.query("select payee_account_number,payee_name from MYBANK_APP_Payee where sender_account_number=?",
                    new Object[]{accountNumber},
//                new BeanPropertyRowMapper<>(Payee.class)
                    new PayeeMapper());
        }catch (DataAccessException sqlException){
            throw new SQLSyntaxErrorException();
        }
        if(payees.size()==0){
            throw new PayeeException(resourceBundle.getString("no.payee")+accountNumber);
        }
        return payees;
    }

    public class PayeeMapper implements RowMapper<Payee> {
        @Override
        public Payee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Payee payee=new Payee();
//            payee.setPayeeId(rs.getInt(1));
//            payee.setSenderAccountNumber(rs.getLong(2));
            payee.setPayeeAccountNumber(rs.getLong("payee_account_number"));
            payee.setPayeeName(rs.getString("payee_name"));
            return payee;
        }
    }
}
