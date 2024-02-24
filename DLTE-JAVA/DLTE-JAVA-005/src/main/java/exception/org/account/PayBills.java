package exception.org.account;

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PayBills {
    static ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    static final Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void main(String[] args)
    {

        Gpay gpay=new Gpay(1234,1000,"Aru","1234");
        Scanner scanner=new Scanner(System.in);
        int attempt=0;

        while(attempt<5){

            try{
                System.out.println("Enter the BillName :");
                String billName=scanner.next();
                System.out.println("Enter the Bill Amount :");
                Double billAmount=scanner.nextDouble();
                System.out.println("Enter the Bill Type :");
                String billType=scanner.next();
                System.out.println("Enter the Pin number :");
                String pin=scanner.next();
                gpay.payBill(billName,billAmount,billType,pin);
                attempt=0;
                return;

            }catch (MyBankException e){

                logger.log(Level.WARNING,e.toString());
                attempt++;
                if(attempt>=5){

                    logger.log(Level.WARNING,"Account Blocked,contact customer support");
                    break;
                }

            }
        }
        scanner.close();;
    }
}
