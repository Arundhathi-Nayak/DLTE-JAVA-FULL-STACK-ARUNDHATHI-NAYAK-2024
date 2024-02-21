package explore.oop.InternetBanking;

public class DebitCard extends Account{
    private String cardNumber;
    private String cardPin;

    public DebitCard(int accountNumber, double accountBalance, String accountHolder, String cardNumber, String cardPin) {
        super(accountNumber, accountBalance, accountHolder);
        this.cardNumber = cardNumber;
        this.cardPin = cardPin;
    }
    public boolean validatePin(String pin){
        return cardPin.equals(pin);
    }
}
