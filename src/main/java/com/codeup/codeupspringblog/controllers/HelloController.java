package com.codeup.codeupspringblog.controllers;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HelloController {

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

    @GetMapping("/join")
    public String showJoinForm(){
        return "join";
    }

    @PostMapping ("/join")
    public String joinCohort(@RequestParam(name = "cohort") String cohort, Model model){
            model.addAttribute("cohort", "Welcome to " + cohort + "!");
            return "join";
    }


}