package ru.dv.mailhelper.controllers;

import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dv.mailhelper.entities.Mailing;
import ru.dv.mailhelper.enums.MsgAddressType;
import ru.dv.mailhelper.services.ContactService;
import ru.dv.mailhelper.services.MailingService;

import java.util.List;

@Controller
@RequestMapping("/mailing")
public class MailingController {
    private MailingService mailingService;
    private ContactService contactService;

    private static final Logger logger = LoggerFactory.getLogger(MailingController.class);

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @Autowired
    public void setMailingService(MailingService mailingService) {
        this.mailingService = mailingService;
    }

    @GetMapping
    public String showMailingPage(Model model) {
        List<Mailing> mailingList = mailingService.findAllMailing();
        model.addAttribute("mailingList", mailingList);
        return "mailing-page";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable(value = "id") Long id, Model model) {
        logger.info("Edit mailing with id {}", id);
        Mailing mailing = mailingService.findById(id).orElse(null);
        if (mailing == null) {
            mailing = new Mailing();
            mailing.setId(0L);
        }
        model.addAttribute("mailing", mailing);
        model.addAttribute("contacts", contactService.findAllContacts());
        model.addAttribute("address_types", MsgAddressType.values());
        return "edit-mailing-page";
    }

    @PostMapping("/edit")
    public String editMailing(@ModelAttribute Mailing mailing){
        mailingService.saveMailing(mailing);
        return "redirect:/mailing";
    }

    @PostMapping("/delete/{id}")
    public String deleteMailing(@PathVariable Long id) throws NotFoundException {
        logger.info("Try to deleye mailing woth id: " + id);
        if(mailingService.findById(id).isEmpty()) {
          throw new NotFoundException(String.format("Mailing with id=%s doesn't exists", id));
        }
        mailingService.deleteMailingById(id);
        return "redirect:/mailing";
    }

}
