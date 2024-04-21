package servlet.endpoint;

import oracle.jdbc.driver.OracleDriver;
import org.example.middleware.DatabaseTarget;
import org.example.middleware.UserDatabaseRepository;
import org.example.remotes.StorageTarget;
import org.example.services.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ResourceBundle;

@WebServlet("/authorize")
public class AuhtorizeServlet extends HttpServlet {
    public AccountService accountService;
    public ResourceBundle resourceBundle;
    public Logger logger;
    public UserDatabaseRepository repository;
    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        accountService=new AccountService(storageTarget);
        resourceBundle=ResourceBundle.getBundle("accounts");
        logger= LoggerFactory.getLogger(AuhtorizeServlet.class);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        try {
            Driver driver=new OracleDriver();
            DriverManager.registerDriver(driver);
            Connection connection=DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"), resourceBundle.getString("db.pass"));
            String query = "select username,password from MY_BANK where username=? and password=?";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                HttpSession session=req.getSession();
                session.setAttribute("username",username);
                resp.sendRedirect("dashboard.jsp");
            }
            else{
                resp.sendRedirect("index.jsp");
            }
        } catch (SQLException var2) {
            SQLException sqlException = var2;
            System.out.println(sqlException);
        }
    }
}