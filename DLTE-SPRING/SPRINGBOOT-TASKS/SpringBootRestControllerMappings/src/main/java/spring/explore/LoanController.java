package spring.explore;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {
    private List<Loan> loanList = new ArrayList<>();
    public LoanController() {
        // Adding some sample loans
        loanList.add(new Loan(1, "Arundhathi", 1000.0));
        loanList.add(new Loan(2, "Avinash", 1500.0));
        loanList.add(new Loan(3, "Akshatha", 2000.0));
    }
    @GetMapping("/{index}")
    public Loan getLoan(@PathVariable int index) {
        if (index >= 0 && index < loanList.size()) {
            return loanList.get(index);
        } else {
            throw new IllegalArgumentException("Invalid index");
        }
    }
    @PostMapping("/loanadd")
    public Loan addLoan(@RequestBody Loan loan) {
        loanList.add(loan);
        return loan;
    }
}
