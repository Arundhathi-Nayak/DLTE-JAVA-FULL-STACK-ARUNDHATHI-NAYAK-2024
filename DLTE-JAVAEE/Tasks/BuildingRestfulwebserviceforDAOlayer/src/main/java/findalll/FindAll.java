package findalll;

import com.google.gson.Gson;
import org.example.entity.Transaction;
import org.example.middleware.DatabaseTarget;
import org.example.remotes.StorageTarget;
import org.example.services.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("findAll")
public class FindAll extends HttpServlet {
        AccountService accountService;

        @Override
        public void init() throws ServletException {
            StorageTarget storageTarget=new DatabaseTarget();
            accountService= new AccountService(storageTarget);
        }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        List<Transaction> transactionList=accountService.callFindAll();
        Gson gson=new Gson();
        String transaction=gson.toJson(transactionList);
        resp.getWriter().println(transaction);
    }
}
