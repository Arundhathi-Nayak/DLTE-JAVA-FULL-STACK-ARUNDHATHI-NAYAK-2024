package servlet.endpoint;

import org.example.middleware.DatabaseTarget;
import org.example.remotes.StorageTarget;
import org.example.services.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet("/withdrawNew")
public class withdrawServlet extends HttpServlet {
    private AccountService accountService;
    private ResourceBundle resourceBundle;
    private Logger logger;

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        accountService=new AccountService(storageTarget);
        resourceBundle = ResourceBundle.getBundle("exception");
        logger = LoggerFactory.getLogger(withdrawServlet.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Withdraw servlet invoked");

        // Retrieve withdrawal details from request parameters
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        double withdrawAmount = Double.parseDouble(req.getParameter("withdrawAmount"));

        // Withdraw the amount from the account
        double currentBalance = accountService.callWithdraw(username, password, withdrawAmount);

        // Set appropriate message attributes based on withdrawal result
        if (currentBalance >= 0) {
            req.setAttribute("info", resourceBundle.getString("withdraw.success"));
        } else {
            req.setAttribute("error", resourceBundle.getString("no.money"));
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("withdraw.jsp");
        dispatcher.forward(req, resp);
    }
}
