package explore.oop.incorporate;

public class Transaction {
    public Customer getSender() {
        return sender;
    }

    public Transaction(Customer sender, Integer amount, Customer receiver, String typeOfTransaction) {
        this.sender = sender;
        this.amount = amount;
        this.receiver = receiver;
        this.typeOfTransaction = typeOfTransaction;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "sender=" + sender +
                ", amount=" + amount +
                ", receiver=" + receiver +
                ", typeOfTransaction='" + typeOfTransaction + '\'' +
                '}';
    }

    public void setSender(Customer sender) {
        this.sender = sender;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Customer getReceiver() {
        return receiver;
    }

    public void setReceiver(Customer receiver) {
        this.receiver = receiver;
    }

    public String getTypeOfTransaction() {
        return typeOfTransaction;
    }

    public void setTypeOfTransaction(String typeOfTransaction) {
        this.typeOfTransaction = typeOfTransaction;
    }

    private Customer sender;
    private  Integer amount;
    private Customer receiver;
    private String typeOfTransaction;
}
