package ru.dv.mailhelper.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import ru.dv.mailhelper.utils.MimeMessageBuilder;
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

    public void sendMail(String from,
                         String toAddress,
                         String ccAddress,
                         String bccAddress,
                         String subject,
                         String body,
                         List<String> attachPath, String uploadFolder) {
        sender.send(createMessage(from,
                toAddress,
                ccAddress,
                bccAddress,
                subject,
                body,
                attachPath, uploadFolder));
            logger.info("Email sent successfully To {},{} with Subject {}", toAddress, ccAddress, subject);
    }

    public MimeMessagePreparator createMessage (String from,
                                                 String toAddress,
                                                 String ccAddress,
                                                 String bccAddress,
                                                 String subject,
                                                 String body,
                                                 List<String> attachPath, String uploadFolder) {
        return mimeMessage -> {
            MimeMessageHelper message = new MimeMessageBuilder(mimeMessage)
                    .from(from, FROM_SENDER_NAME)
                    .to(toAddress)
                    .cc(ccAddress)
                    .bcc(bccAddress)
                    .subject(subject)
                    .body(body)
                    .attachment(attachPath, uploadFolder);
        };
    }

}
