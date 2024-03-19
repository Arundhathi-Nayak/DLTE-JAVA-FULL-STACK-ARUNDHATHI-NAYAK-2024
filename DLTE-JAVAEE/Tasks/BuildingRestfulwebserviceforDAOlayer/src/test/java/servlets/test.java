package servlets;

import com.google.gson.Gson;
import find.username.date.FindByDateAndUsername;
import findalll.FindAll;
import findbyusername.FindByUsername;
import org.example.entity.Transaction;
import org.example.services.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class test {

    @Mock
    private AccountService service;
    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;
    @Mock
    private StringWriter stringWriter;
    @Mock
    private PrintWriter printWriter;

    @Before
    public void initiate() throws IOException {
        stringWriter=new StringWriter();
        printWriter=new PrintWriter(stringWriter);
        when(httpServletResponse.getWriter()).thenReturn(printWriter);
    }
    @Test
    public void testFindAllByUsername() throws ServletException, IOException {
        FindByUsername findAllByUsername=new FindByUsername();
        findAllByUsername.accountService=service;
        Transaction transaction1=new Transaction(new Date("3/5/2024"),221234L,"Arundhathi",200,4500);
        Transaction transaction2=new Transaction(new Date("13/7/2024"),441235L,"Avinash",600,3900);

        List<Transaction> transactionList= Stream.of(transaction1,transaction2).collect(Collectors.toList());
        when(httpServletRequest.getParameter("username")).thenReturn("Jayant");
        when(service.callFindAllUser(anyString())).thenReturn(transactionList);
        findAllByUsername.doGet(httpServletRequest,httpServletResponse);
        verify(httpServletResponse).setContentType("application/json");
        verify(service).callFindAllUser(anyString());
        assertEquals("Expected List","[{\"date\":\"Mar 2, 2024 12:00:00 AM\",\"transactionID\":221234,\"user\":\"Arundhathi\",\"amount\":200.0,\"balance\":4500.0}",stringWriter.toString().trim());
    }


    @Test
    public void testFindAll() throws ServletException, IOException {
        FindAll findAll=new FindAll();
        findAll.accountService=service;
        Transaction transaction1=new Transaction(new Date("3/5/2024"),221234L,"Arundhathi",200,4500);
        Transaction transaction2=new Transaction(new Date("13/7/2024"),441235L,"Avinash",600,3900);

        List<Transaction> transactionList= Stream.of(transaction1,transaction2).collect(Collectors.toList());

        Gson gson=new Gson();
        String transaction=gson.toJson(transactionList);
        System.out.println(transaction);
        when(service.callFindAll()).thenReturn(transactionList);
        findAll.doGet(httpServletRequest,httpServletResponse);
        verify(httpServletResponse).setContentType("application/json");
        verify(service).callFindAll();
        assertEquals("Expected List","[{\"date\":\"Mar 2, 2024 12:00:00 AM\",\"transactionID\":221234,\"user\":\"Arundhathi\",\"amount\":200.0,\"balance\":4500.0}",stringWriter.toString().trim());
    }

    @Test
    public void testFindByDateAndUsername() throws ServletException, IOException {
        FindByDateAndUsername findByDateAndUsername = new FindByDateAndUsername();
        findByDateAndUsername.accountService= service;
        Transaction transaction1 = new Transaction(new Date("3/5/2024"), 221234L, "Arundhathi", 200, 4500);
        Transaction transaction2 = new Transaction(new Date("13/7/2024"), 441235L, "Avinash", 600, 3900);

        List<Transaction> transactionList = Stream.of(transaction1, transaction2).collect(Collectors.toList());
        when(httpServletRequest.getParameter("username")).thenReturn("Jayant");
        when(httpServletRequest.getParameter("date")).thenReturn("3/5/2024");
        when(service.callFindAllDate(java.sql.Date.valueOf(anyString()), anyString())).thenReturn(transactionList);

        findByDateAndUsername.doGet(httpServletRequest, httpServletResponse);

        verify(httpServletResponse).setContentType("application/json");
        verify(service).callFindAllDate(java.sql.Date.valueOf(anyString()), anyString());

        // Adjust the expected output based on the actual implementation
        assertEquals("Expected List", "[{\"date\":\"Mar 5, 2024 12:00:00 AM\",\"transactionID\":221234,\"user\":\"Arundhathi\",\"amount\":200.0,\"balance\":4500.0}", stringWriter.toString().trim());
    }
}
