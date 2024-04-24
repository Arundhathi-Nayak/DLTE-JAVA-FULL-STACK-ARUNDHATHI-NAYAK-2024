package operation.crud;


import org.omg.PortableInterceptor.LOCATION_FORWARD;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class Loan {

    private int loanNumber;
    private double  loanAmount;
    private String loanDate;
    private String loanStatus;
    private String borrowerName;
    private Long borrowerContact;
    String message;

    public String getMessage() {
        return message;
    }

    public void showMessage(String action) {
        if("added".equals(action)) message="Loan added successfully";
        else if("delete".equals(action)) message="Loan deleted successfully";
        else message="Unknown action";
    }

    public Loan() {
    }

    public int getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(int loanNumber) {
        this.loanNumber = loanNumber;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public Long getBorrowerContact() {
        return borrowerContact;
    }

    public void setBorrowerContact(Long borrowerContact) {
        this.borrowerContact = borrowerContact;
    }

    public Loan(int loanNumber, double loanAmount, String loanDate, String loanStatus, String borrowerName, Long borrowerContact) {
        this.loanNumber = loanNumber;
        this.loanAmount = loanAmount;
        this.loanDate = loanDate;
        this.loanStatus = loanStatus;
        this.borrowerName = borrowerName;
        this.borrowerContact = borrowerContact;
    }



}
