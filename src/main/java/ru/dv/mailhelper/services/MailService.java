package ru.dv.mailhelper.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailService {
    private JavaMailSender sender;

    private static final String FROM_SENDER_NAME = "FinanceTeam";
    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    @Autowired
    public void setSender(JavaMailSender sender) {
        this.sender = sender;
    }

    public void sendMail(String from, String toAddress, String ccAddress, String bccAddress, String subject, String body) {
        MimeMessagePreparator preparator = createMessage(from, toAddress,ccAddress,bccAddress, subject, body, null, null);
        sender.send(preparator);
        logger.info("Email sent successfully To {},{} with Subject {}", toAddress, ccAddress, subject);
    }

    public void sendMailWithAttachment(String from, String toAddress, String ccAddress, String bccAddress, String subject, String body, List<String> attachPath, String uploadFolder) {
		MimeMessagePreparator preparator = createMessage(from, toAddress,ccAddress,bccAddress, subject, body, attachPath, uploadFolder);
        sender.send(preparator);
        logger.info("Email sent successfully To {},{} with Subject {}", toAddress, ccAddress, subject);
    }

    private MimeMessagePreparator createMessage (String from,
                                                String toAddress,
                                                String ccAddress,
                                                String bccAddress,
                                                String subject,
                                                String body,
                                                List<String> attachPath, String uploadFolder) {
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, (attachPath != null), "UTF-8");
            message.setTo(toAddress.replaceAll("\\[(.*?)\\]", "$1").split("[,;]"));
            message.setFrom(from, FROM_SENDER_NAME);
            message.setSubject(subject);
            if (!(ccAddress == null || ccAddress.equals("")))
                message.setCc(ccAddress.replaceAll("\\[(.*?)\\]", "$1").split("[;,]"));
            if (!(bccAddress == null || bccAddress.equals("")))
                message.setBcc(bccAddress.replaceAll("\\[(.*?)\\]", "$1").split("[;,]"));
            message.setText(body, false);
            if (attachPath != null) {
                for (int i = 0; i < attachPath.size(); i++) {
                    FileSystemResource file = new FileSystemResource(uploadFolder + "/" + attachPath.get(i));
                    message.addAttachment(file.getFilename(), file);
                }
            }
        };
        return preparator;
    }

}
