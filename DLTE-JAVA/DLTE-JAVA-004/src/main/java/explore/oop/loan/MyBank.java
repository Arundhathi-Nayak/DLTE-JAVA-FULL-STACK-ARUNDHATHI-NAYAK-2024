package explore.oop.loan;

public interface MyBank {
        void addNewLoan(Loan loan);
        Loan[] checkAvailableLoans();
        Loan[] checkClosedLoans();

}
