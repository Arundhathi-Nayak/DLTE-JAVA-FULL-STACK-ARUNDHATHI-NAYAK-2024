package withdrawAmount;

import com.google.gson.Gson;
import org.example.middleware.DatabaseTarget;
import org.example.remotes.StorageTarget;
import org.example.services.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

class account{
    private String username;
    private String password;
    private double withdrawAmount;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getWithdrawAmount() {
        return withdrawAmount;
    }
}
@WebServlet("withdraw")
public class WithdrawAmount extends HttpServlet {

    AccountService service;


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            req.getReader().lines().forEach(System.out::println);
            Gson gson=new Gson();
            account account = gson.fromJson(req.getReader(), account.class);

            service.callWithdraw(account.getUsername(),account.getPassword(),account.getWithdrawAmount());
            resp.setStatus(HttpServletResponse.SC_OK);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget = new DatabaseTarget();
        service = new AccountService(storageTarget);
    }

}
