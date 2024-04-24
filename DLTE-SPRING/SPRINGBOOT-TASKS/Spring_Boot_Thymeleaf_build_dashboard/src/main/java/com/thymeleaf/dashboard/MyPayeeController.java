package com.thymeleaf.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/web")
public class MyPayeeController {
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String myTemplate(){
        return "index";
    }

    @RequestMapping(value = "/view-payees",method = RequestMethod.GET)
    public String viewPayees() {
        return "view-payees";
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout() {

        return "index";
    }

}
