package com.paymentdao.payment.remote;

import org.springframework.stereotype.Repository;

@Repository
public interface DeletePayeeRepository {
    void deletePayee(int payeeId, Long senderAccountNumber, Long payeeAccountNumber, String payeeName);
    void deletePayeeAdded(int payeeId, Long senderAccountNumber, Long payeeAccountNumber, String payeeName);
}
