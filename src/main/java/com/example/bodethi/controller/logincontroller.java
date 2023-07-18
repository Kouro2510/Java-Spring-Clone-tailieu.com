package com.example.bodethi.controller;

import com.example.bodethi.entity.FailedLoginAttemptEntity;
import com.example.bodethi.repository.FailedLoginAttemptRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Controller
public class logincontroller {
    @Autowired
    private FailedLoginAttemptRepository failedLoginAttemptRepository;
    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        System.out.println(ipAddress);
        return "auth/login";
    }

}
