package ru.dv.mailhelper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.dv.mailhelper.services.MailService;

@Controller
public class SimpleMailController {
    private MailService mailService;

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    @ResponseBody
    @RequestMapping("/sendSimpleEmail")
    public String sendSimpleEmail() {
//        mailService.sendMail("myapptestingmar2021@gmail.com","myapptestingmar2021@gmail.com", null,null ,"test-email", "Паша, привет\r Это сообщение тестовое из приложения по автоматизации писем");
//        mailService.sendMailWithAttachment("myapptestingmar2021@gmail.com","myapptestingmar2021@gmail.com", null,null ,"test-email", "Паша, привет\n Это сообщение тестовое из приложения по автоматизации писем","/Users/komlev/IdeaProjects/mailhelper/src/main/resources/static/favicon.ico", null);
        return "Email Sent!";
    }

}
