package com.paymentdao.payment.remote;

import com.paymentdao.payment.entity.Payee;

import java.sql.SQLSyntaxErrorException;
import java.util.List;

public interface PaymentTransferRepository {
    List<Payee> findAllPayee(Long accountNumber) throws SQLSyntaxErrorException;
}
