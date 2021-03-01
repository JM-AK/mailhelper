package ru.dv.mailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import ru.dv.mailservice.services.MailService;

@SpringBootApplication
public class MailApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(MailApplication.class, args);
    }

    @Autowired
    private MailService mailService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void run(String... args) throws Exception {
        sendEmail();

}
    void sendEmail() {
        mailService.sendMail("alexander.komlev@digitech.ventures", "test", "test-email-body");
    }
}
