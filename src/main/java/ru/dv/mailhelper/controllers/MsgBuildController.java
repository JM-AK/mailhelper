package ru.dv.mailhelper.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.dv.mailhelper.beans.MsgBuild;
import ru.dv.mailhelper.entities.Mailing;
import ru.dv.mailhelper.entities.MessageItem;
import ru.dv.mailhelper.exceptions.MailingNotFoundException;
import ru.dv.mailhelper.exceptions.ResourceNotFoundException;
import ru.dv.mailhelper.services.MailingService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/msgbuild")
public class MsgBuildController {
    private MailingService mailingService;
    private MsgBuild msgBuild;

    private static final Logger logger = LoggerFactory.getLogger(MsgBuildController.class);

    @Autowired
    public void setMailingService(MailingService mailingService) {
        this.mailingService = mailingService;
    }

    @Autowired
    public void setMsgBuild(MsgBuild msgBuild) {
        this.msgBuild = msgBuild;
    }

    @GetMapping
    public String showMsgBuildPage(Model model, HttpSession session) {
        this.msgBuild = getCurrentMsgBuild(session);
        model.addAttribute("msgbuild", msgBuild);
        return "msgbuild-page";
    }

    @GetMapping("/add/{mailing_id}")
    public String addMailing(@PathVariable(name = "mailing_id") Long mailingId, HttpServletRequest request) {
        Mailing m = mailingService.findById(mailingId).orElseThrow(() -> new MailingNotFoundException(String.format("Mailing with id=%s doesn't exists", mailingId)));
        msgBuild.add(m, null, null);

        String referrer = request.getHeader("referer");
        return "redirect:" + referrer;
    }

    @GetMapping("/remove/{mailing_id}")
    public String removeMailing(@PathVariable(name = "mailing_id") Long mailingId) {
        msgBuild.remove(mailingId);
        return "redirect:/msgbuild";
    }

    @GetMapping("/edit/{mailing_id}")
    public String showEditMailingItemPage(@PathVariable(value = "mailing_id") Long mailingId, Model model){
        logger.info("Edit mailing_item with mailingId {}", mailingId);
        MessageItem mi = msgBuild.findMessageItemByMailingId(mailingId);
        model.addAttribute("message_item", mi);
        return "edit-message_item-page";
    }

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(ResourceNotFoundException ex){
        ModelAndView modelAndView = new ModelAndView("not found page");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }

    public MsgBuild getCurrentMsgBuild(HttpSession session) {
        MsgBuild msgBuild = (MsgBuild) session.getAttribute("msgbuild");
        if (msgBuild == null) {
            msgBuild = new MsgBuild();
            session.setAttribute("msgbuild", msgBuild);
        }
        return msgBuild;
    }




}
