package ru.dv.mailhelper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.dv.mailhelper.entities.Mailing;
import ru.dv.mailhelper.services.MailingService;

import java.util.List;

@Controller
public class MailingController {
    private MailingService mailingService;

    @Autowired
    public void setMailingService(MailingService mailingService) {
        this.mailingService = mailingService;
    }

    @ResponseBody
    @RequestMapping("/mailings")
    public String getAllMailings(){
        List<Mailing> mailingList = mailingService.findAllMailing();
        return mailingList.toString();
    }
}
