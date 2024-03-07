package org.example;

import java.util.ArrayList;
import java.util.Date;

public interface MyBank {
    ArrayList<Loan> loans=new ArrayList<>();
    void filterBasedOnDate(Date startDate, Date endDate);
}
