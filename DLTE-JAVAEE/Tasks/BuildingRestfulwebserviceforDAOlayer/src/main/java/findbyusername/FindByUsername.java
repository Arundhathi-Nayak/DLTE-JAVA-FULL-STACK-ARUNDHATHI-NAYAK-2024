package findbyusername;

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

@WebServlet("findByUsername")
public class FindByUsername extends HttpServlet {
  public AccountService accountService;

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        accountService= new AccountService(storageTarget);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        resp.setContentType("application/json");
        List<Transaction> transactions=accountService.callFindAllUser(username);
        Gson gson=new Gson();
        String allTransaction=gson.toJson(transactions);
        //String allTransaction=null;
        if(allTransaction!=null)
        resp.getWriter().println(allTransaction);

    }
}
