package exception.org.credit;

import sun.security.mscapi.CPublicKey;

import java.io.IOException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static java.lang.System.exit;

public class CreditCardFilter {
    static  ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
     static  Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static void main(String[] args) {
     //   ResourceBundle resourceBundle=ResourceBundle.getBundle("accounts");
     //   Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        try{
            FileHandler fileHandler=new FileHandler("accounts-logs.txt",true);
            SimpleFormatter simpleFormatter=new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);
        }
        catch (IOException ioException){}
        CreditCard[] mybank={
                new CreditCard(23567876543L,"Annapoo",new Date(2024,1,20),8674,42000,new Date(2024,03,11),new Date(2024,03,8),1234),
                new CreditCard(33567876543L,"Akshira",new Date(2024,12,5),5674,50000,new Date(2024,06,4),new Date(2024,01,11),2234),
                new CreditCard(43567876543L,"Aru",new Date(2024,1,8),7674,62000,new Date(2024,06,5),new Date(2024,05,18),6223),
                new CreditCard(53567876543L,"Divija",new Date(2024,2,6),2674,750000,new Date(2025,01,23),new Date(2024,12,19),3234),
                new CreditCard(63567876543L,"Eeksha",new Date(2024,12,2),1674,7000,new Date(2025,03,11),new Date(2024,06,13),4234)
        };
        CreditCardFilter filter=new CreditCardFilter();
        System.out.println(resourceBundle.getString("Greetings"));
        Scanner scanner=new Scanner(System.in);
        System.out.println(resourceBundle.getString("menu.display"));
        System.out.println(resourceBundle.getString("Enter.choice"));
        int choice=scanner.nextInt();
        switch (choice){
            case 1:try{
                System.out.println(resourceBundle.getString("start.limit"));
                int startLimit=scanner.nextInt();
                System.out.println(resourceBundle.getString("end.limit"));
                int endLimit=scanner.nextInt();
                filter.filterLimit(mybank,startLimit,endLimit);
            }catch (MyBankCreditCardException cardException){
                logger.log(Level.WARNING,cardException.toString());
            }
            break;
            case 2:try{
                System.out.println(resourceBundle.getString("start.date"));
                int startDate=scanner.nextInt();
                System.out.println(resourceBundle.getString("end.date"));
                int endDate=scanner.nextInt();
                filter.filterBasedOnBillPayment(mybank,startDate,endDate);
            }catch (MyBankCreditCardException cardException){
                logger.log(Level.WARNING,cardException.toString());
            }
                break;
            case 3: exit(0);

        }
        scanner.close();
    }
    public void filterLimit(CreditCard[] customer,int startLimit,int endLimit) throws MyBankCreditCardException {
        int flag=0;
        System.out.println(resourceBundle.getString("filter.display")+startLimit+resourceBundle.getString("and")+endLimit);
        for(CreditCard each:customer){
           if(each.getCreditCardLimit()>=startLimit&& each.getCreditCardLimit()<=endLimit) {
               flag=1;
               System.out.println(each.getCreditCardHolder()+", "+resourceBundle.getString("Amount")+" "+each.getCreditCardLimit());
           }
        }
        if(flag==0){
            System.out.println(resourceBundle.getString("No.customers.found"));
            logger.log(Level.WARNING,resourceBundle.getString("No.customers.found"));
            throw new MyBankCreditCardException();
        }
        logger.log(Level.INFO,resourceBundle.getString("success.filter"));
    }
    public void filterBasedOnBillPayment(CreditCard[] customer,int startDate, int endDate) throws MyBankCreditCardException {
        int flag=0;
        System.out.println(resourceBundle.getString("bill.display")+" "+startDate+resourceBundle.getString("and")+" "+endDate);
        for(CreditCard each:customer){
            if(each.getDateOfBillPayment().getTime()>=startDate&& each.getDateOfBillPayment().getDate()<=endDate) {
                flag=1;
                System.out.println(each.getCreditCardHolder()+",Date : "+each.getDateOfBillPayment().getDate());
            }
        }
        if(flag==0){
            System.out.println(resourceBundle.getString("No.customers.found"));
            logger.log(Level.WARNING,resourceBundle.getString("No.customers.found"));
            throw new MyBankCreditCardException();
        }
        logger.log(Level.INFO,resourceBundle.getString("success.filter"));
    }
}
