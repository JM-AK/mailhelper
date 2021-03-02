package ru.dv.mailservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private JavaMailSender sender;
    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    @Autowired
    public void setSender(JavaMailSender sender) {
        this.sender = sender;
    }

    public void sendMail(String from, String toAddress, String ccAddress, String bccAddress, String subject, String body) {
        MimeMessagePreparator preparator = createMessage(from, toAddress,ccAddress,bccAddress, subject, body, null);
        sender.send(preparator);
        logger.info("Email sent successfully To {},{} with Subject {}", toAddress, ccAddress, subject);
    }

    public void sendMailWithAttachment(String from, String toAddress, String ccAddress, String bccAddress, String subject, String body, String attachPath) {
		MimeMessagePreparator preparator = createMessage(from, toAddress,ccAddress,bccAddress, subject, body, attachPath);
        sender.send(preparator);
        logger.info("Email sent successfully To {},{} with Subject {}", toAddress, ccAddress, subject);
    }

    private MimeMessagePreparator createMessage (String from,
                                                String toAddress,
                                                String ccAddress,
                                                String bccAddress,
                                                String subject,
                                                String body,
                                                String attachPath) {
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, (attachPath != null), "UTF-8");
            message.setTo(toAddress.split("[,;]"));
            message.setFrom(from, "<From Name>");
            message.setSubject(subject);
            if (ccAddress != null)
                message.setCc(ccAddress.split("[;,]"));
            if (bccAddress != null)
                message.setBcc(bccAddress.split("[;,]"));
            message.setText(body, false);
            if (attachPath != null) {
                FileSystemResource file = new FileSystemResource(attachPath);
                message.addAttachment(file.getFilename(), file);
            }
        };
        return preparator;
    }

}
