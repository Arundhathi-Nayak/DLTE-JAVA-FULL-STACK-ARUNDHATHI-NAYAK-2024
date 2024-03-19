package find.username.date;

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
import java.sql.Date;
import java.util.List;

@WebServlet("findbrusernameanddate")
public class FindByDateAndUsername extends HttpServlet {

    public AccountService accountService;


    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        accountService= new AccountService(storageTarget);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String date = request.getParameter("date");
        String username = request.getParameter("username");

        List<Transaction> transactions = accountService.callFindAllDate(Date.valueOf(date), username);

        Gson gson = new Gson();
        String json = gson.toJson(transactions);

        response.setContentType("application/json");
        response.getWriter().print(json);
    }

}
