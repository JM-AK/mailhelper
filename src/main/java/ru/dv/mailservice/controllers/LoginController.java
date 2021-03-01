package ru.dv.mailservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.dv.mailservice.services.MailService;

@Controller
public class LoginController {
    private MailService mailService;

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/login")
    public String showMyLoginPage() {
        mailService.sendMail("myapptestingmar2021@gmail.com","alexander.komlev@digitech.ventures", null,null ,"test", "test-email-body");

        return "login";
    }
}
