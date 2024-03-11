package First;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// name same as class not mandatory
@WebServlet("/First/api/")
public class MyFirstServlet extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(MyFirstServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Hello");
        String var = req.getParameter("amount");
        resp.getWriter().println(var);
    }
}
