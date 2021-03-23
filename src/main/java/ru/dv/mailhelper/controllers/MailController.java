package ru.dv.mailhelper.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dv.mailhelper.beans.MsgBuild;
import ru.dv.mailhelper.entities.Message;
import ru.dv.mailhelper.entities.MessageItem;
import ru.dv.mailhelper.entities.User;
import ru.dv.mailhelper.entities.dtos.MessageItemDto;
import ru.dv.mailhelper.services.MailService;
import ru.dv.mailhelper.services.MessageItemConverter;
import ru.dv.mailhelper.services.MessageService;
import ru.dv.mailhelper.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/mail")
public class MailController {
    private MailService mailService;
    private MessageService messageService;
    private UserService userService;
    private MessageItemConverter messageItemConverter;

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

    @Autowired
    public void setMessageItemConverter(MessageItemConverter messageItemConverter) {
        this.messageItemConverter = messageItemConverter;
    }

    private static final Logger logger = LoggerFactory.getLogger(MailController.class);

    @GetMapping("/message/prepare")
    public String messagePrepare(Model model, HttpServletRequest httpServletRequest, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }

        User user = userService.findByUsername(principal.getName());
        MsgBuild msgBuild = (MsgBuild) httpServletRequest.getSession().getAttribute("msgbuild");
        Message message = messageService.createMessage(msgBuild, user);
        List<MessageItemDto> messageItemList = messageItemConverter.mapEntityListToDtoList(message.getMessageItems());
        model.addAttribute("messageItemList", messageItemList);
        return "message-preparator";
    }

    @GetMapping("/message/confirm")
    public String messageConfirm(Model model, HttpServletRequest httpServletRequest, Principal principal) {

        if (principal == null) {
            return "redirect:/login";
        }
        Message message = (Message) httpServletRequest.getSession().getAttribute("message");
        messageService.sendMessage(message);
        message.setSentDate(LocalDateTime.now());
//        message = messageService.saveMessage(message);
        return "message-preparator";
    }


}
