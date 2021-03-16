package ru.dv.mailhelper.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dv.mailhelper.beans.MsgBuild;
import ru.dv.mailhelper.entities.Message;
import ru.dv.mailhelper.entities.User;
import ru.dv.mailhelper.services.MailService;
import ru.dv.mailhelper.services.MessageService;
import ru.dv.mailhelper.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/mail")
public class MailController {
    private MailService mailService;
    private MessageService messageService;
    private UserService userService;

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private static final Logger logger = LoggerFactory.getLogger(MailController.class);

    @GetMapping("/message/prepare")
    public String orderFill(Model model, HttpServletRequest httpServletRequest, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }

        User user = userService.findByUsername(principal.getName());
        MsgBuild msgBuild = (MsgBuild) httpServletRequest.getSession().getAttribute("msbuild");
        Message message = messageService.createMessage(msgBuild, user);

        model.addAttribute("message", message);
        return "message-preparator";
    }



}
