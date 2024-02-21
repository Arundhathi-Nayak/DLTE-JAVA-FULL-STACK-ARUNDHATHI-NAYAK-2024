package store.oops;

public class Bonds {
    public static void main(String[] args) {


        BondMain myBond[]= {
                new BondMain("Private Bond", 500, 5.5, "Paid", 5, "Avinash"),
        new BondMain("Zero_Coupon Bond", 600, 6.5, "No Tax", 4, "Annaporna"),
        new BondMain("Corporate Bond", 400, 7.5, "Pending", 3, "Aru"),
        new BondMain("Municipal Bond", 520, 2.5, "Paid", 5, "Akshira"),
        new BondMain("Climate Bond", 522, 5.5, "No Tax", 5, "Eeskha")
        };
        Bonds bonds=new Bonds();
        bonds.sort(myBond);
    }
    public void sort(BondMain[] mybond){
        System.out.println("Before Sorting");
        for(BondMain each:mybond){
            System.out.println("Bond Name "+each.getBondName()+" has Rate of interest "+each.getInterestRate());
        }
        for(int index=0;index<mybond.length;index++){
            for(int next=0;next<mybond.length-index-1;next++){
                if(mybond[next].getInterestRate().compareTo(mybond[next+1].getInterestRate())<0){
                    BondMain backup=mybond[next];
                    mybond[next]=mybond[next+1];
                    mybond[next+1]=backup;
                }
            }
        }
        System.out.println("After Sorting");
        for(BondMain each:mybond){
            System.out.println("Bond Name "+each.getBondName()+" has Rate of interest "+each.getInterestRate());
        }
    }


}
