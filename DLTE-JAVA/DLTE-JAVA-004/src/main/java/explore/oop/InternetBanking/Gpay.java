package explore.oop.InternetBanking;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

public class Gpay extends DebitCard {
    public Gpay(int accountNumber, double accountBalance, String accountHolder, String cardNumber, String cardPin) {
        super(accountNumber, accountBalance, accountHolder, cardNumber, cardPin);
    }
    public boolean payBill(String billName,double billAmount,String billType,String pin){
        if(!validatePin(pin)){
            System.out.println("Invalid Pin,Payment Failed");
            return false;
        }
        System.out.println("Bill payment approved for "+billType+" Biller: "+billName+",Amount: "+billAmount);
        return true;
    }

}
