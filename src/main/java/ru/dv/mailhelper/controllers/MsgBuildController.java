package ru.dv.mailhelper.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dv.mailhelper.beans.MsgBuild;
import ru.dv.mailhelper.services.MailingService;

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





    public MsgBuild getCurrentMsgBuild(HttpSession session) {
        MsgBuild msgBuild = (MsgBuild) session.getAttribute("msgbuild");
        if (msgBuild == null) {
            msgBuild = new MsgBuild();
            session.setAttribute("msgbuild", msgBuild);
        }
        return msgBuild;
    }


}
