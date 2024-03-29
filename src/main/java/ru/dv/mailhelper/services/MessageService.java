package ru.dv.mailhelper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dv.mailhelper.beans.MsgBuild;
import ru.dv.mailhelper.entities.Message;
import ru.dv.mailhelper.entities.MessageItem;
import ru.dv.mailhelper.entities.User;
import ru.dv.mailhelper.entities.dtos.MessageItemDto;
import ru.dv.mailhelper.repositories.MessageRepository;

import javax.transaction.Transactional;
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

            if(mi.getAttachmentList() == null || mi.getAttachmentList().isEmpty()) {
                mailService.sendMail(message.getUser().getEmail(),
                        mi.getEmailTo(),
                        mi.getEmailCopy(),
                        mi.getEmailBcc(),
                        mi.getSubject(), mi.getBody());
            } else {
                mailService.sendMailWithAttachment(message.getUser().getEmail(),
                        mi.getEmailTo(),
                        mi.getEmailCopy(),
                        mi.getEmailBcc(),
                        mi.getSubject(), mi.getBody(), mi.getAttachmentList(), mi.getUploadFolder());
            }
        }
    }
}
