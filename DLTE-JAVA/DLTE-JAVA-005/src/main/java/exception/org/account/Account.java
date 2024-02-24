package exception.org.account;

public class Account {
    private int accountNumber;
    private double accountBalance;
    private String accountHolder;

    public Account(int accountNumber, double accountBalance, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.accountHolder = accountHolder;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public double getAccountBalance(){
        return accountBalance;
    }

    public double setAccountBalance(double newBalance){
        return this.accountBalance=newBalance;
    }

}
