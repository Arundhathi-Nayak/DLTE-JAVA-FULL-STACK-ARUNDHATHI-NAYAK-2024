package addaccounts;

import com.google.gson.Gson;
import org.example.entity.Account;
import org.example.middleware.DatabaseTarget;
import org.example.remotes.StorageTarget;
import org.example.services.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("addAccounts")
public class AddAccounts extends HttpServlet {
    AccountService accountService;

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        accountService= new AccountService(storageTarget);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson=new Gson();
        Account account=gson.fromJson(req.getReader(),Account.class);
        accountService.callAddTransactions(account);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println("Account added successfully");
    }
}
