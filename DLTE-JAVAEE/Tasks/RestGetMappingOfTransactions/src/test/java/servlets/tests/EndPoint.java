package servlets.tests;

import org.example.entity.Account;
import org.example.entity.Transaction;
import org.example.services.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import servletsOfRest.RestGetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class EndPoint {
    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;
    @Mock
    private StringWriter stringWriter;
    @Mock
    private PrintWriter printWriter;
    @Mock
    AccountService accountService;

    @Before
    public void initiate() throws IOException {
        stringWriter=new StringWriter();
        printWriter=new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);
    }

    @Test
 public void testDoGetWithDateAndUsername() throws IOException, ServletException {

        when(request.getParameter("date")).thenReturn("2024-03-13");
        when(request.getParameter("username")).thenReturn("shreyas12");


        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(new Date(2024-03-13),1234L,"shreyas12",200,50000));
        when(accountService.callFindAllDate(Date.valueOf("2024-03-13"), "shreyas12")).thenReturn(transactions);

        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        RestGetMapping servlet = new RestGetMapping();
        servlet.accountService = accountService;


        servlet.doGet(request, response);

    }
    @Test
   public void testDoGetWithUsernameOnly() throws ServletException, IOException {

        when(request.getParameter("username")).thenReturn("shreyas12");

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(new Date(2024-03-13),1234L,"shreyas12",200,50000));
        when(accountService.callFindAllUser("shreyas12")).thenReturn(transactions);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        RestGetMapping servlet = new RestGetMapping();
        servlet.accountService = accountService;
        servlet.doGet(request, response);

    }
    @Test
   public  void testDoGetWithoutParams() throws ServletException, IOException {

        AccountService accountService = mock(AccountService.class);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(new Date(2024-03-13),1234L,"shreyas12",200,50000));
        when(accountService.callFindAll()).thenReturn(transactions);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        RestGetMapping servlet = new RestGetMapping();
        servlet.accountService = accountService;


        servlet.doGet(request, response);


    }
}
