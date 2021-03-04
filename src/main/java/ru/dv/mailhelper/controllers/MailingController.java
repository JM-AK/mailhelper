package ru.dv.mailhelper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.dv.mailhelper.entities.Mailing;
import ru.dv.mailhelper.services.ContactService;
import ru.dv.mailhelper.services.MailingService;

import java.util.List;

@Controller
@RequestMapping("/mailing")
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

    @GetMapping
    public String showMailingPage(Model model){
        List<Mailing> mailingList = mailingService.findAllMailing();
        model.addAttribute(mailingList);
        return "mailing-page";
    }



}
