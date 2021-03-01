package ru.dv.mailservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class MailService {
    private JavaMailSender sender;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Autowired
    public void setSender(JavaMailSender sender) {
        this.sender = sender;
    }

    public void sendMail(String email, String subject, String text) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");

        try {
            helper.setTo(email);
            helper.setText(text, true);
            helper.setSubject(subject);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        try {
            executorService.submit(() -> sender.send(message));
        } catch (MailException e) {
            e.printStackTrace();
        }
    }

}
