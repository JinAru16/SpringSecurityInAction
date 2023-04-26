package com.hello.helloSecurity.controller;


import org.aspectj.weaver.ast.Call;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication a = context.getAuthentication();
        return "Hello, "+a.getName()+"!";
    }

    @GetMapping("/bye")
    @Async
    public String goodbye(){
        SecurityContext context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        System.out.printf("username : "+username);
        return "Bye, "+username+"!";
    }

    @GetMapping("/ciao")
    public String ciao() throws Exception {
        Callable<String> task = () -> {
            SecurityContext context = SecurityContextHolder.getContext();
            return context.getAuthentication().getName();
        };
        ExecutorService e = Executors.newCachedThreadPool();
        try{
            return "Ciao, "+e.submit(task).get()+"!";
        }finally {
            e.shutdown();;
        }
     }

     @GetMapping("/hola")
    public String hola() throws Exception {
        Callable<String> task = () -> {
            SecurityContext context = SecurityContextHolder.getContext();
            return context.getAuthentication().getName();
        };

         ExecutorService e = Executors.newCachedThreadPool();
         e = new DelegatingSecurityContextExecutorService(e);

         try{
             return "Hola, "+e.submit(task).get()+"!";
         }finally {
             e.shutdown();;
         }
     }
     @GetMapping("/home")
    public String home() {
        return "home.html";
     }
}
