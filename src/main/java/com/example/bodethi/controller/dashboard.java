package com.example.bodethi.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class dashboard {
    @Bean
    @GetMapping("/admin")
    public String homePage(){
        return "dashboard/Home";
    }
    @Bean
    @GetMapping("/admin/about_us")
    public String aboutUs(){
        return "dashboard/AboutUs";
    }


}
