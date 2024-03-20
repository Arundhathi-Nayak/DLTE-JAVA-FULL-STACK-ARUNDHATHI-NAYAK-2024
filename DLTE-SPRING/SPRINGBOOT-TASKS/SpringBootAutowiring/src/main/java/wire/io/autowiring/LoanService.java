package wire.io.autowiring;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface LoanService {
    List<Loan> loansList= Stream.of(new Loan(12345L,6000L,"open","home","Arundhathi",987654321L),new Loan(345675L,12000L,"open","home","Annapoorna",4545454545L),new Loan(54321L,5400L,"closed","personal","akshatha",645363L),new Loan(64321L,9400L,"open","personal","avinash",3645363L)).collect(Collectors.toList());
    List<Loan> findALL();
}
