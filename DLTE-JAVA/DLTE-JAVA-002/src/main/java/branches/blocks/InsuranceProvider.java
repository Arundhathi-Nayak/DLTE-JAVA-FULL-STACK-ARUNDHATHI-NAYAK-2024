package branches.blocks;

import java.util.Scanner;

public class InsuranceProvider {
    public static void main(String[] args) {
        String lic[]={"illness","regular checkups","free treatment"};
        String reliance[]={"free treatment","accidents"};
        String Tata[]={"accidents","maternity benefits"};
        Scanner sc=new Scanner(System.in);
        String typeOne="";
        System.out.println("Enter the Types");
        typeOne=sc.nextLine();
        String CompanyOffer="";
        for(int i=0;i<lic.length;i++){
            if(typeOne.toLowerCase().contains(lic[i]))
                CompanyOffer+="lic"+"\t";
        }
        for(int i=0;i<reliance.length;i++){
            if(typeOne.toLowerCase().contains(reliance[i]))
                CompanyOffer+="reliance"+"\t";
        }
        for(int i=0;i<Tata.length;i++){
            if(typeOne.toLowerCase().contains(Tata[i]))
                CompanyOffer+="Biba"+"\t";
        }
        System.out.println("Companies that offer features are "+CompanyOffer+" ");
        System.out.println();

    }
}
