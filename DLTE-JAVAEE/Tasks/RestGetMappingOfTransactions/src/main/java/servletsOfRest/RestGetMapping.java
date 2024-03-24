package servletsOfRest;

import org.example.entity.Transaction;
import org.example.middleware.DatabaseTarget;
import org.example.remotes.StorageTarget;
import org.example.remotes.UserRepository;
import org.example.services.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;


//   http://localhost:7001/RestGetMapping?date=2024-03-13&username=shreyas12
//   http://localhost:7001/RestGetMapping?username=shreyas12

@WebServlet("/RestGetMapping")
public class RestGetMapping extends HttpServlet {
    public AccountService accountService;

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        accountService = new AccountService(storageTarget);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("date")!=null && req.getParameter("username")!=null){
            List<Transaction> transactionList = accountService.callFindAllDate(Date.valueOf(req.getParameter("date")),req.getParameter("username"));
            for(Transaction each:transactionList) {

                resp.getWriter().println(each.toString());
            }
        }else if(req.getParameter("username")!=null){
            List<Transaction> transactionList = accountService.callFindAllUser(req.getParameter("username"));
            for(Transaction each:transactionList) {
                resp.getWriter().println(each.toString());
            }
        }else{
            List<Transaction> transactionList=accountService.callFindAll();
            for(Transaction each:transactionList) {
                resp.getWriter().println(each.toString());
            }
        }
    }
}
