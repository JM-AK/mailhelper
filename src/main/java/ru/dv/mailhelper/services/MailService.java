package ru.dv.mailhelper.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {
    private JavaMailSender sender;
    private static final String FROM_SENDER_NAME = "FinanceTeam";
    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    @Autowired
    public void setSender(JavaMailSender sender) {
        this.sender = sender;
    }

    public void sendMail(MimeMessage mimeMessage) {
        sender.send(mimeMessage);
        try {
            logger.info("Email sent successfully To {},{} with Subject {}", mimeMessage.getRecipients(MimeMessage.RecipientType.TO), mimeMessage.getRecipients(MimeMessage.RecipientType.CC), mimeMessage.getSubject());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public MimeMessage prepareMail() {
        return sender.createMimeMessage();
    }

    public String getFromSenderName() {
        return FROM_SENDER_NAME;
    }

    //    public MimeMessagePreparator createMessage (String from,
//                                                 String toAddress,
//                                                 String ccAddress,
//                                                 String bccAddress,
//                                                 String subject,
//                                                 String body,
//                                                 List<String> attachPath, String uploadFolder) {
//        return mimeMessage -> {
//            MimeMessageHelper message = new MimeMessageBuilder(mimeMessage)
//                    .from(from, FROM_SENDER_NAME)
//                    .to(toAddress)
//                    .cc(ccAddress)
//                    .bcc(bccAddress)
//                    .subject(subject)
//                    .body(body)
//                    .attachment(attachPath, uploadFolder);
//        };
//    }

}
