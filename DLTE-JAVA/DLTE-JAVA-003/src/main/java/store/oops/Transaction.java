package store.oops;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data   //annotation offered by lombok for setter and getter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
        private Date transactionDate;
        private  Integer transactionAmount;
        private String transactionTo;
        private String transactionRemarks;

//    public Transaction(String transactionDate, Double transactionAmount, String transactionTo, String transactionRemarks) {
//        this.transactionDate = transactionDate;
//        this.transactionAmount = transactionAmount;
//        this.transactionTo = transactionTo;
//        this.transactionRemarks = transactionRemarks;
//    }
}
