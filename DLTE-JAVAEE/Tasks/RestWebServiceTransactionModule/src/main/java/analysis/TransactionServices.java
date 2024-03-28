package analysis;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet("/services/*")
public class TransactionServices extends HttpServlet {
//    ArrayList<Transaction> transactions= (ArrayList<Transaction>) Stream.of(
//            new Transaction(new Date("1/10/2024"),1500,"Annapo","Friend"),
//            new Transaction(new Date("2/20/2024"),2000,"Akshira","Family"),
//            new Transaction(new Date("3/25/2024"),6500,"Aru","Education"),
//            new Transaction(new Date("4/17/2024"),6000,"Avinash","Bills"),
//            new Transaction(new Date("5/20/2024"),500,"Aru","Education")
//    ).collect(Collectors.toList());
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String maxAmount=req.getParameter("Maximum");
//        String minAmount=req.getParameter("Minimum");
//        if(maxAmount!=null&&minAmount!=null){
//            int max=Integer.parseInt(maxAmount);
//            int min=Integer.parseInt(minAmount);
//            Gson gson=new Gson();
//            resp.setContentType("application/json");
//            for (Transaction each:transactions) {
//                if(each.getTransactionAmount()>min&&each.getTransactionAmount()<max){
//
//                    resp.getWriter().println(gson.toJson(each));
//                }
//            }
//            resp.setStatus(HttpServletResponse.SC_OK);
//        }
//        else{
//            Gson gson=new Gson();
//            resp.setContentType("application/json");
//            String json = gson.toJson(transactions);
//            resp.setStatus(HttpServletResponse.SC_OK);
//            resp.getWriter().println(json);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Gson gson=new Gson();
//        Transaction transaction=gson.fromJson(req.getReader(),Transaction.class);
//        transactions.add(transaction);
//        resp.setStatus(HttpServletResponse.SC_OK);
//        resp.getWriter().println("The transaction done to" + transaction.getTransactionTo()
//        );
//    }
List<Transaction> transactionList = Stream.of(
        new Transaction(new Date(2024, 11, 9), 10000, "Arundhathi", "Family"),
        new Transaction(new Date(2025, 02, 19), 50000, "Annapoo", "Education"),
        new Transaction(new Date(2022, 12, 18), 10000, "Akshira", "Bills"),
        new Transaction(new Date(2025, 06, 5), 10000, "Eeskha", "Emergency"),
        new Transaction(new Date(2024, 2, 20), 20000, "Divija", "Friends")
).collect(Collectors.toList());


    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {


        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");


        PrintWriter writer = resp.getWriter();


        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Transactions</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h1>Transactions</h1>");


        for (Transaction transaction : transactionList) {
            writer.println("<div>");
            writer.println("<p>Date: " + transaction.getTransactionDate() + "</p>");
            writer.println("<p>Amount: " + transaction.getTransactionAmount()+ "</p>");
            writer.println("<p>Name: " + transaction.getTransactionTo()+ "</p>");
            writer.println("<p>Type: " + transaction.getTransactionRemarks() + "</p>");
            writer.println("</div>");
            writer.println("<br>");
        }

        writer.println("</body>");
        writer.println("</html>");

        // Set status to OK
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson=new Gson();

        Transaction transaction = gson.fromJson(req.getReader(),Transaction.class);
        transactionList.add(transaction);
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");


        PrintWriter writer = resp.getWriter();


        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Transaction Insertion</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h1>Transaction Added</h1>");
        writer.println("<p>" + transaction.getTransactionTo() + " has been added to the records.</p>");
        writer.println("</body>");
        writer.println("</html>");

        // Set status to OK
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
