package com.xrafece.do_sport.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Xrafece
 */
@ControllerAdvice
@RestController
public class HelloController {
    // hello controller的测试接口
    @RequestMapping("hello")
    public String HelloRequest() {
        return "Hello，This is a Spring Boot Project";
    }
}
