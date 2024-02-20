package store.oops;

import sun.security.rsa.RSAUtil;

import java.util.Date;

public class CustomerSupport {
    public static void main(String[] args) {
//        CreditCard creditCard= new CreditCard();
//        creditCard.setCreditCardNumber(9798654659L);
//        creditCard.setCreditCardExpiry(new Date(2024,12,20));
//        creditCard.setCreditCardCvv(455);
//        creditCard.setCreditCardHolder("Aru");
//        creditCard.setCreditCardLimit(1000);
//        creditCard.setCreditCardPin(1234);
//        creditCard.setDateOfBillGeneration(new Date(2024,03,11));
//        creditCard.setDateOfBillPayment(new Date(2024,03,12));
//
//        CreditCard creditCard1= new CreditCard(23567876543L,"Annapoo",new Date(2024,12,20),5674,12333,new Date(2025,03,11),new Date(2024,05,11),1234);
//        System.out.println(creditCard1.getCreditCardLimit()+" "+creditCard1.getCreditCardHolder());
//        System.out.println(creditCard1);



        // Array of objects
       // CreditCard[] myBank=new CreditCard[10];
        CreditCard[] mybank={
                new CreditCard(23567876543L,"Annapoo",new Date(2024,1,20),5674,1245333,new Date(2025,03,11),new Date(2024,05,11),1234),
                new CreditCard(33567876543L,"Akshira",new Date(2024,12,20),5674,1223333,new Date(2025,05,11),new Date(2024,05,11),22234),
                new CreditCard(43567876543L,"Aru",new Date(2024,1,20),5674,1234233,new Date(2025,05,11),new Date(2024,05,11),12234),
                new CreditCard(53567876543L,"Divija",new Date(2024,2,20),5674,124333,new Date(2025,03,11),new Date(2024,05,11),3234),
                new CreditCard(63567876543L,"Eeksha",new Date(2024,12,20),5674,142333,new Date(2025,03,11),new Date(2024,05,11),4234)
        };
        //Analysis
       CustomerSupport support=new CustomerSupport();
       support.findEarlyExpire(mybank);
       support.findBillDate(mybank,new Date(2024,03,01),new Date(2024,03,01));
       support.list(mybank);
       support.sortingCustomers(mybank);
       support.list(mybank);

    }
    public void findEarlyExpire(CreditCard[] customer){

        for(CreditCard each : customer){
            if(each.getCreditCardExpiry().after(new Date(2024,03,01))){
                System.out.println(each.getCreditCardHolder()+" your credit card "+each.getCreditCardNumber());

            }
        }
    }
    public void findBillDate(CreditCard[] customer,Date start,Date end){
        System.out.println("Customer having bill date between "+start.getDate()+" and "+end.getDate());
        for(CreditCard each:customer){
            if(each.getDateOfBillGeneration().getDate()>=start.getDate()&& each.getDateOfBillGeneration().getDate()>=end.getDate()){
                System.out.println(each.getCreditCardHolder()+" "+each.getDateOfBillGeneration());
            }
        }
    }
    public void list(CreditCard[] customer){
        System.out.println("Available customer");
        for(CreditCard each:customer){
            System.out.println(each);
        }

    }
    public void sortingCustomers(CreditCard[] customer){
        CreditCard backup=null;
        for(int select=0;select<customer.length;select++){
            for(int next=select+1;next<customer.length;next++){
                if(customer[select].getCreditCardHolder().compareTo(customer[next].getCreditCardHolder())>0){
                    backup=customer[select];
                    customer[select]=customer[next];
                    customer[next]=backup;
                }
            }
        }
    }


}
