package exception.org.credit;

import java.util.ResourceBundle;

public class MyBankCreditCardException extends Exception {
    public MyBankCreditCardException(){
        super(ResourceBundle.getBundle("application").getString("filter.exceed"));
    }

}
