package ru.dv.mailhelper.utils;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class MimeMessageBuilder extends MimeMessageHelper{

    public MimeMessageBuilder(MimeMessage mimeMessage) {
        super(mimeMessage, "UTF-8");
    }

    public MimeMessageBuilder from(String address, String name) throws MessagingException, UnsupportedEncodingException {
        this.setFrom(address, name);
        return this;
    }

    public MimeMessageBuilder to(String address) throws MessagingException {
        this.setTo(getAddressArray(address));
        return this;
    }

    public MimeMessageBuilder cc(String address) throws MessagingException {
        if (!(address == null || address.equals(""))) {
            this.setCc(getAddressArray(address));
        }
        return this;
    }

    public MimeMessageBuilder bcc(String address) throws MessagingException {
        if (!(address == null || address.equals(""))) {
            this.setBcc(getAddressArray(address));
        }
        return this;
    }

    public MimeMessageBuilder subject(String subject) throws MessagingException {
        this.setSubject(subject);
        return this;
    }

    public MimeMessageBuilder body(String body) throws MessagingException {
        this.setText(body);
        return this;
    }

    public MimeMessageBuilder attachment(List<String> attachPath, String uploadFolder) throws MessagingException {
        if (!(attachPath == null || attachPath.isEmpty())) {
            for (String s : attachPath) {
                FileSystemResource file = new FileSystemResource(uploadFolder + "/" + s);
                this.addAttachment(file.getFilename(), file);
            }
        }
        return this;
    }

    public MimeMessage build() {
        return this.getMimeMessage();
    }

    private String [] getAddressArray (String address) {
        return address.replaceAll("\\[(.*?)\\]", "$1").split("[,;]");
    }

}
