package exception.org.account;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gpay extends Account{
    private String upiPin;
    private String userName;
    ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    public Gpay(int accountNumber, double accountBalance, String accountHolder, String upiPin) {
        super(accountNumber, accountBalance, accountHolder);
        this.upiPin = upiPin;
        this.userName = accountHolder;
    }

    public boolean validateUpiPin(String pinEntered) throws MyBankException{
        if(!upiPin.equals(pinEntered)){
            logger.log(Level.WARNING,resourceBundle.getString("upiPin.invalid")+" "+getAccountNumber());
            throw new MyBankException("upiPin.invalid");
        }
        return true;
    }
    public void payBill(String billName,double billAmount,String billType,String upiPin) throws MyBankException{
        try{
            validateUpiPin(upiPin);
            if(getAccountBalance()>=billAmount){
                logger.log(Level.INFO,"Bill paymnet approved for "+billType+" biller "+billName+",Amount: "+billAmount);
                System.out.println("Bill paymnet approved for "+billType+" biller "+billName+",Amount: "+billAmount);
                setAccountBalance(getAccountBalance()-billAmount);


            }else{
                logger.log(Level.WARNING,resourceBundle.getString("insufficient.fund"));
                throw new MyBankException("upiPin.invalid");
            }

            }catch(MyBankException e){
            logger.log(Level.WARNING,e.toString());
            throw e;

        }
    }


}
