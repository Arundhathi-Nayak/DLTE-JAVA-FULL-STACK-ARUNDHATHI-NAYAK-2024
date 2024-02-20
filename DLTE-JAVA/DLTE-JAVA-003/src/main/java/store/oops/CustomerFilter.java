package store.oops;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.Date;
import java.util.Scanner;

public class CustomerFilter {
    public static void main(String[] args) throws ParseException {

        CreditCard[] mybank={
                new CreditCard(23567876543L,"Annapoo",new Date(2024,1,20),8674,42000,new Date(2024,03,11),new Date(2024,03,8),1234),
                new CreditCard(33567876543L,"Akshira",new Date(2024,12,5),5674,50000,new Date(2024,06,4),new Date(2024,01,11),2234),
                new CreditCard(43567876543L,"Aru",new Date(2024,1,8),7674,62000,new Date(2024,06,5),new Date(2024,05,18),6223),
                new CreditCard(53567876543L,"Divija",new Date(2024,2,6),2674,750000,new Date(2025,01,23),new Date(2024,12,19),3234),
                new CreditCard(63567876543L,"Eeksha",new Date(2024,12,2),1674,7000,new Date(2025,03,11),new Date(2024,06,13),4234)
        };
        CustomerFilter filter=new CustomerFilter();
        filter.filterOnLimit(mybank);
        filter.filterOnDateOfBillPayment(mybank);
        filter.updatePin(mybank);
        filter.updateLimitOfCustomer(mybank);

    }
    public void filterOnLimit(CreditCard[] customer){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the Credit Card Amount :");
        int limit=scanner.nextInt();
        for(CreditCard each:customer){
            if(each.getCreditCardLimit()>=limit){
                System.out.println(each.getCreditCardHolder()+" your card has exceeded limit");
            }
        }
        scanner.close();
    }
    public void filterOnDateOfBillPayment(CreditCard[] customer)  {
      Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the date in format(DD/MM/YYYY)");
        String startDate=scanner.next();
        String splitDate[]= startDate.split("/");
        for (CreditCard each:customer) {
            if(Integer.parseInt(splitDate[0])==(each.getDateOfBillPayment().getDate())&& Integer.parseInt(splitDate[1])==(each.getDateOfBillPayment().getMonth())&&Integer.parseInt(splitDate[2])==(each.getDateOfBillPayment().getYear())){
                System.out.println("Hello, "+each.getCreditCardHolder()+" This is your date of your bill Payement");
            }
        }
        scanner.close();
    }
    public void updatePin(CreditCard[] customer){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter your credit card number :");
        long creditCardNumber=scanner.nextLong();
        System.out.println("To Change your pin number,Enter your old Pin number");
        int oldPin=scanner.nextInt();
        int newPin=0;
        for(CreditCard each:customer){
            if(each.getCreditCardPin()==oldPin && each.getCreditCardNumber()==creditCardNumber ){
                System.out.println("Enter your new Pin");
                each.setCreditCardPin(newPin=scanner.nextInt());
                System.out.println("Pin updated");
            }
            else{
                System.out.println("Failed");
            }
        }
        scanner.close();
    }
    public void updateLimitOfCustomer(CreditCard[] customer){
        int newLimit;
        for(CreditCard each:customer){
            if(each.getDateOfBillGeneration().getDate()==5){
                int oldLimit=each.getCreditCardLimit();
                newLimit= (int) (oldLimit+(oldLimit*0.05));
                each.setCreditCardLimit((int) newLimit);
                System.out.println("Hello "+each.getCreditCardHolder()+" Your limit is updated to "+newLimit);
            }
        }

    }

}
