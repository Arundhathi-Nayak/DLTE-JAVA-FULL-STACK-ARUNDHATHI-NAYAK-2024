package analysis;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet("/services/*")
public class TransactionServices extends HttpServlet {
    ArrayList<Transaction> transactions= (ArrayList<Transaction>) Stream.of(
            new Transaction(new Date("1/10/2024"),1500,"Annapo","Friend"),
            new Transaction(new Date("2/20/2024"),2000,"Akshira","Family"),
            new Transaction(new Date("3/25/2024"),6500,"Aru","Education"),
            new Transaction(new Date("4/17/2024"),6000,"Avinash","Bills"),
            new Transaction(new Date("5/20/2024"),500,"Aru","Education")
    ).collect(Collectors.toList());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String maxAmount=req.getParameter("Maximum");
        String minAmount=req.getParameter("Minimum");
        if(maxAmount!=null&&minAmount!=null){
            int max=Integer.parseInt(maxAmount);
            int min=Integer.parseInt(minAmount);
            Gson gson=new Gson();
            resp.setContentType("application/json");
            for (Transaction each:transactions) {
                if(each.getTransactionAmount()>min&&each.getTransactionAmount()<max){

                    resp.getWriter().println(gson.toJson(each));
                }
            }
            resp.setStatus(HttpServletResponse.SC_OK);
        }
        else{
            Gson gson=new Gson();
            resp.setContentType("application/json");
            String json = gson.toJson(transactions);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson=new Gson();
        Transaction transaction=gson.fromJson(req.getReader(),Transaction.class);
        transactions.add(transaction);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println("The transaction done to" + transaction.getTransactionTo()
        );
    }
}
