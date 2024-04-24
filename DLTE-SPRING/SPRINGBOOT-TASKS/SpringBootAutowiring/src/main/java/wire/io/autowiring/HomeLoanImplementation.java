package wire.io.autowiring;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("homeLoanImplementation")
public class HomeLoanImplementation implements LoanService {
    @Override
    public List<Loan> findALL() {
        List<Loan> newList = new ArrayList<>();
        for (Loan each : loansList) {
            if (each.getLoanType().equals("home")) {
                newList.add(each);
            }

        }
        return newList;
    }
}
