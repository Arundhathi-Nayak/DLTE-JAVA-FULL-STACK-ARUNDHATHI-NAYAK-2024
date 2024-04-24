package com.paymentdao.payment.remote;

import com.paymentdao.payment.entity.Payee;
import org.springframework.stereotype.Repository;

import java.sql.SQLSyntaxErrorException;
import java.util.List;


@Repository
public interface PaymentTransferRepository {
    List<Payee> findAllPayeeBasedOnAccountNumber(Long accountNumber) ;
    void deletePayeeAdded(int payeeId, Long senderAccountNumber, Long payeeAccountNumber, String payeeName);
}
