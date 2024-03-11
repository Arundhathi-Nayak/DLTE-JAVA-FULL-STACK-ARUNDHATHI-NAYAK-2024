package Calculations;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.System.exit;

@WebServlet("/Calculations/*")
public class SipandIncomeTax extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String investmentRequest=req.getParameter("investment");
        String returnRequest=req.getParameter("expectedReturn");
        String periodRequest=req.getParameter("period");
        String incomeRequest=req.getParameter("annualIncome");
        String regimeRequest=req.getParameter("regime");
        if(investmentRequest!=null&&returnRequest!=null&&periodRequest!=null) {
            double monthlyInvestment = Double.parseDouble(investmentRequest);
            double annualReturn = Double.parseDouble(returnRequest);
            int years = Integer.parseInt(periodRequest);


            double monthlyRate=annualReturn/12/100;
            double numMonths=12*years;
            double total=monthlyInvestment*((Math.pow((1+monthlyRate),(numMonths))-1)*(1+monthlyRate))/monthlyRate;
            double totalmoneyInvested=numMonths*monthlyInvestment;
            double estimateReturn=total-totalmoneyInvested;
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println("Estimated amount="+estimateReturn+ "Total return = "+ total+ "Total investment="+ totalmoneyInvested);
        }
        else{
            //calling function for tax old and new regime
            String received=findTax(Double.parseDouble(incomeRequest),regimeRequest);
            resp.getWriter().println(received);
        }

    }

    private String findTax(double salary, String regimeRequest) {
        double taxpercent=0;
        switch(regimeRequest){
            case "Old" : case "old":
                if(salary>=0 && salary<=250000){
                    System.out.println("You need not to pay any tax:Exempted");
                }
                if(salary>250000 && salary<=500000){
                    taxpercent=0.058*salary;
                }
                else if(salary>500000 && salary<=750000){
                    taxpercent=0.208*salary;
                }
                else if(salary>750000 && salary<=1000000){
                    taxpercent=0.20*salary;
                }
                else if(salary>1000000 && salary<=1250000){
                    taxpercent=0.30*salary;
                }
                else if(salary>1250000 && salary<=1500000){
                    taxpercent=0.30*salary;
                }
                else{
                    taxpercent=0.30*salary;
                }
                break;
            case "new": case "New":
                if(salary>=0 && salary<=250000){
                    System.out.println("You need not to pay any tax:Exempted");
                }
                if(salary>250000 && salary<=500000){
                    taxpercent=0.05*salary;
                }
                else if(salary>500000 && salary<=750000){
                    taxpercent=0.10*salary;
                }
                else if(salary>750000 && salary<=1000000){
                    taxpercent=0.158*salary;
                }
                else if(salary>1000000 && salary<=1250000){
                    taxpercent=0.20*salary;
                }
                else if(salary>1250000 && salary<=1500000){
                    taxpercent=0.25*salary;
                }
                else{
                    taxpercent=0.30*salary;
                }
                break;
            default:exit(0);
        }
        return Double.toString(taxpercent);
    }

}

