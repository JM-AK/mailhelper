package ru.dv.mailservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class MailService {
    @Autowired
    @Qualifier("gmail")
    private JavaMailSender sender;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

//    @Autowired
//    public void setSender(JavaMailSender sender) {
//        this.sender = sender;
//    }

    public void sendMail(String from, String toAddress, String ccAddress, String bccAddress, String subject, String body) {
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setTo(toAddress.split("[,;]"));
            message.setFrom(from, "<From Name>");
            message.setSubject(subject);
            if (!ccAddress.isBlank())
                message.setCc(ccAddress.split("[;,]"));
            if (!bccAddress.isBlank())
                message.setBcc(bccAddress.split("[;,]"));
            message.setText(body, false);
        };


        try {
            executorService.submit(() -> sender.send(preparator));
        } catch (MailException e) {
            e.printStackTrace();
        }
        logger.info("Email sent successfully To {},{} with Subject {}", toAddress, ccAddress, subject);
    }

    public void sendMailWithAttachment(String email, String subject, String text, String attachPath) throws MessagingException {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

        try {
            helper.setTo(email);
            helper.setText(text, true);
            helper.setSubject(subject);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

		FileSystemResource file = new FileSystemResource(attachPath);
		helper.addAttachment(file.getFilename(), file);

        try {
            executorService.submit(() -> sender.send(message));
        } catch (MailException e) {
            e.printStackTrace();
        }

	}

}
