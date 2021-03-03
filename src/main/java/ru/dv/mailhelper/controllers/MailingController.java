package ru.dv.mailhelper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.dv.mailhelper.entities.Company;
import ru.dv.mailhelper.entities.Contact;
import ru.dv.mailhelper.entities.Mailing;
import ru.dv.mailhelper.enums.MsgAddressType;
import ru.dv.mailhelper.services.ContactService;
import ru.dv.mailhelper.services.MailingService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("/mailings")
public class MailingController {
    private MailingService mailingService;
    private ContactService contactService;

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @Autowired
    public void setMailingService(MailingService mailingService) {
        this.mailingService = mailingService;
    }

    @ResponseBody
    @RequestMapping("/all")
    public String getAllMailings(){
        List<Mailing> mailingList = mailingService.findAllMailing();
        return mailingList.toString();
    }

    @ResponseBody
    @RequestMapping("/test")
    public String testMailings(){

        List<Mailing> mailingList = mailingService.findAllMailing();

        return mailingList.toString();
    }

}
