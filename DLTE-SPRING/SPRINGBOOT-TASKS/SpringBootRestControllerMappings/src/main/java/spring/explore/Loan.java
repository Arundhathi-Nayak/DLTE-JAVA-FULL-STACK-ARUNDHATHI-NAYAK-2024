package spring.explore;

public class Loan {

    private int loanNumber;
    private String borrowerName;
    private double amount;

    public Loan(int loanNumber, String borrowerName, double amount) {
        this.loanNumber = loanNumber;
        this.borrowerName = borrowerName;
        this.amount = amount;
    }

    public Loan() {
    }

    public int getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(int loanNumber) {
        this.loanNumber = loanNumber;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanNumber=" + loanNumber +
                ", borrowerName='" + borrowerName + '\'' +
                ", amount=" + amount +
                '}';
    }
}
