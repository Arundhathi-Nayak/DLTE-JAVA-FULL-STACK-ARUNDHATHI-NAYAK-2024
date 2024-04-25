package com.jdbcwebapp.thymeleaf;

import com.springjdbc.driver.entity.Transaction;
import com.springjdbc.driver.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/transaction")
@ComponentScan("com.springjdbc.driver")
public class MyTransactionController {
    @Autowired
    TransactionService transactionServices;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/display")
    public String display(Model model){
        model.addAttribute("transaction",new Transaction());
        return "index";
    }

    @GetMapping("/new")
    public String show(Model model){
        model.addAttribute("transaction",new Transaction());
        return "newTransaction";
    }
    @RequestMapping(value = "/new" ,method = RequestMethod.POST)
    public String newTransaction(@ModelAttribute Transaction transaction, Model model, BindingResult bindingResult){
        model.addAttribute("transaction",transaction);
        if(!bindingResult.hasErrors()){
            Transaction transaction1=transactionServices.newTransaction(transaction);
            model.addAttribute("message","Transaction successful!");
            model.addAttribute("transaction",transaction1);
            return "index";
        }else{
            model.addAttribute("message","Transaction failed!");
            return "newTransaction";
        }
    }
}

