package ru.dv.mailhelper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dv.mailhelper.beans.MsgBuild;
import ru.dv.mailhelper.entities.Message;
import ru.dv.mailhelper.entities.MessageItem;
import ru.dv.mailhelper.entities.User;
import ru.dv.mailhelper.repositories.MessageRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    private MessageRepository messageRepository;
    private MessageStatusService messageStatusService;

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Autowired
    public void setMessageStatusService(MessageStatusService messageStatusService) {
        this.messageStatusService = messageStatusService;
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

}
