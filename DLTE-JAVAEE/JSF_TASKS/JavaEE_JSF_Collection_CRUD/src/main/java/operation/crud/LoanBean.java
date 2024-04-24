package operation.crud;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean
@ViewScoped
public class LoanBean {
    List<Loan> loans;

    public LoanBean() {
        loans=new ArrayList<>();
        loans.add(new Loan(123,25550,"8/2/2024","open","Aru",6734234567L));
        loans.add(new Loan(124,55000,"8/3/2024","closed","Avinash",9736263738L));
        loans.add(new Loan(125,80000,"7/3/2024","open","Annapoo",636263738L));
        loans.add(new Loan(126,98000,"8/3/2024","closed","Akshira",5736263738L));


    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public void addLoan(Loan loan){
        loans.add(loan);
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Loans added successfully",null));
    }

    public List<Loan> displayClosedLoan(){
        return loans.stream().filter(loan1->loan1.getLoanStatus().equals("closed")).collect(Collectors.toList());
    }

    public void deleteLoan(Long loanNumber){
        loans.removeIf(loan -> loan.getLoanNumber()==loanNumber);
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Loans deleted successfully",null));
    }
}