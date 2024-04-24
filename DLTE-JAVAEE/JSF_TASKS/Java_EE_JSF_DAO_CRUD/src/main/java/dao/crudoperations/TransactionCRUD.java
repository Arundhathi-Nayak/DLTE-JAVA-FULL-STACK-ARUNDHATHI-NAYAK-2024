package dao.crudoperations;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class TransactionCRUD {
    private Collection<Transaction> creditCards;
    private StorageTarget storageTarget;
    private CreditCardServices services;
    private CreditCard currentCard=new CreditCard();

    public CreditCard getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(CreditCard currentCard) {
        this.currentCard = currentCard;
    }

    public CreditCRUDs() {
        storageTarget=new DatabaseTarget();
        services=new CreditCardServices(storageTarget);
        implementFetchAll();
    }

    public Collection<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(Collection<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    // CRUD operations

    public void implementFetchAll(){
        creditCards=services.callFindAll();
    }

    public void addCard(){
        services.callSave(currentCard);
        currentCard=new CreditCard();
    }

    public void removeCard(CreditCard creditCard){
        services.callDelete(creditCard);
    }
}
