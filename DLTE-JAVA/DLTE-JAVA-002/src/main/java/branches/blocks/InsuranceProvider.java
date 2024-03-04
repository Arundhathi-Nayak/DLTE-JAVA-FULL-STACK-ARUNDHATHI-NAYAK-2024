package branches.blocks;

import java.util.Scanner;

public class InsuranceProvider {
    public static void main(String[] args) {
   String lic[]={"illness","regular checkups","freetreatment"};
      String reliance[]={"freetreatment","accidents"};
      String Tata[]={"accidents","maternity benefits"};
        for(String each:args) {
            for (String select : lic) {
                if (each.equalsIgnoreCase(select)) {
                    System.out.println("Lic Offer: " +select);
                }
            }
        }
        for(String each:args) {
            for (String select : reliance) {
                if (each.equalsIgnoreCase(select)) {
                    System.out.println("reliance Offer: " +select);
                }
            }
        }
        for(String each:args) {
            for (String select : Tata) {
                if (each.equalsIgnoreCase(select)) {
                    System.out.println("Tata Offer: " +select);
                }
            }
        }



    }
}
