package com.xrafece.do_sport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Xrafece
 */
@Controller
public class ViewController {

    @RequestMapping("pay")
    public String payView(){
        return "pay/index.html";
    }
}
