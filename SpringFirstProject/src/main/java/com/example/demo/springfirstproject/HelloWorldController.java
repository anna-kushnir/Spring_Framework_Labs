package com.example.demo.springfirstproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class HelloWorldController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World from Spring!!!";
    }

    // http://localhost:8080/

    /*@GetMapping("/second")
    public String secondPage() {
        System.out.println(secondPageLoad());
        return "Hello again!";
    }
    public String secondPageLoad() {
        return "Second page load!!!";
    }*/
}
