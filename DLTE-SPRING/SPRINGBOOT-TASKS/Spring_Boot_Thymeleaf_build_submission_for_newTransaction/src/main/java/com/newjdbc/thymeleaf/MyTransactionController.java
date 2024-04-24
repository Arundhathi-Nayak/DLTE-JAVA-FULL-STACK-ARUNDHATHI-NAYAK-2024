package com.newjdbc.thymeleaf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/web")
@ComponentScan("com.springjdbc.driver")
public class MyTransactionController {

    @Autowired
   TransactionService transactionService;

    Logger logger= LoggerFactory.getLogger(MyTransactionController.class);

    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public String approve(Transaction transaction, Model model, BindingResult bindingResult) {
        try {
            if (!bindingResult.hasErrors()) {
                transaction = transactionService.;
                model.addAttribute("status", transaction.getTransactionId() + " has been inserted");
            }
        } catch (TransactionException transactionException) {
            logger.warn(transactionException.toString());
            model.addAttribute("error", transactionException.toString());
        }
        return "index";
    }
}
