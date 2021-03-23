package ru.dv.mailhelper.entities.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dv.mailhelper.entities.Attachment;
import ru.dv.mailhelper.entities.Contact;
import ru.dv.mailhelper.entities.MessageItem;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class MessageItemDto {
    private Long id;
    private Long mailingId;

    private String companyName;
    private List<String> emailTo;
    private List<String> emailCopy;
    private List<String> emailBcc;

    private String subject;
    private String body;
    private String uploadFolder;
    private List<String> attachmentList;

    public MessageItemDto(MessageItem mi) {
        this.id = mi.getId();
        this.mailingId = mi.getMailing().getId();
        this.companyName = mi.getMailing().getCompany().getShortName();
        this.emailTo = mi.getMailing().getContactTo().stream().map(Contact::getEmail).collect(Collectors.toList());
        this.emailCopy = mi.getMailing().getContactCopy().stream().map(Contact::getEmail).collect(Collectors.toList());
        this.emailBcc = mi.getMailing().getContactBcc().stream().map(Contact::getEmail).collect(Collectors.toList());
        this.subject = mi.getSubject();
        this.body = mi.getBody();
        this.uploadFolder = mi.getUploadFolder();
        this.attachmentList = mi.getAttachmentList().stream().map(Attachment::getPath).collect(Collectors.toList());
    }

}
