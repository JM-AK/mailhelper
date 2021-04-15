package ru.dv.mailhelper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import ru.dv.mailhelper.beans.MsgBuild;
import ru.dv.mailhelper.entities.Message;
import ru.dv.mailhelper.entities.MessageItem;
import ru.dv.mailhelper.entities.User;
import ru.dv.mailhelper.entities.dtos.MessageItemDto;
import ru.dv.mailhelper.repositories.MessageRepository;
import ru.dv.mailhelper.utils.MimeMessageBuilder;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class MessageService {
    private MessageRepository messageRepository;
    private MessageStatusService messageStatusService;
    private MailService mailService;
    private MessageItemConverter messageItemConverter;

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Autowired
    public void setMessageStatusService(MessageStatusService messageStatusService) {
        this.messageStatusService = messageStatusService;
    }

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    @Autowired
    public void setMessageItemConverter(MessageItemConverter messageItemConverter) {
        this.messageItemConverter = messageItemConverter;
    }

    public List<Message> findAll(){
        return messageRepository.findAll();
    }

    public Message findById(Long id) {
        return messageRepository.findById(id).get();
    }


    public Message saveMessage(Message message){
        Message msgOut = messageRepository.save(message);
        msgOut.setConfirmed(true);
        return msgOut;
    }

    @Transactional
    public Message createMessage(MsgBuild msgBuild, User user) {
        Message message = new Message();
        message.setId(0L);
        message.setUser(user);
        message.setStatus(messageStatusService.getStatusById(1L));
        message.setMessageItems(new ArrayList<>(msgBuild.getItems()));
        for (MessageItem mi : msgBuild.getItems()) {
            mi.setMessage(message);
        }
        return message;
    }

    public void changeMessageStatus(Message message, Long statusId) {
        message.setStatus(messageStatusService.getStatusById(statusId));
        saveMessage(message);
    }

    public void sendMessage(Message message) {
        List<MessageItemDto> messageItemList = messageItemConverter.mapEntityListToDtoList(message.getMessageItems());
        Iterator<MessageItemDto> iter = messageItemList.iterator();
        while (iter.hasNext()){
            MessageItemDto mi = iter.next();
            try {
                mailService.sendMail(new MimeMessageBuilder(mailService.prepareMail())
                        .from(message.getUser().getEmail(), mailService.getFromSenderName())
                        .to(mi.getEmailTo())
                        .cc(mi.getEmailCopy())
                        .bcc(mi.getEmailBcc())
                        .subject(mi.getSubject())
                        .body(mi.getBody())
                        .attachment(mi.getAttachmentList(), mi.getUploadFolder())
                        .build());
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }
}
