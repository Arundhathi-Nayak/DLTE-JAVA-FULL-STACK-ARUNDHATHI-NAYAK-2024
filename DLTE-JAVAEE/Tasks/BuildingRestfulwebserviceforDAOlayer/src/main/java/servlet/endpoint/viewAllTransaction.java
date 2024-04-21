package servlet.endpoint;

import org.example.entity.Transaction;
import org.example.middleware.DatabaseTarget;
import org.example.remotes.StorageTarget;
import org.example.services.AccountService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/view")
public class viewAllTransaction extends HttpServlet {

    public AccountService accountService;

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        accountService=new AccountService(storageTarget);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Transaction> myBankCard = accountService.callFindAll();
        RequestDispatcher dispatcher=req.getRequestDispatcher("findAll.jsp");
        req.setAttribute("myCards", myBankCard);
        dispatcher.include(req, resp);
    }
}