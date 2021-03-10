package ru.dv.mailhelper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dv.mailhelper.beans.MessageBuilder;
import ru.dv.mailhelper.entities.Message;
import ru.dv.mailhelper.entities.MessageItem;
import ru.dv.mailhelper.repositories.MessageRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    private MessageRepository messageRepository;

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> findAll(){
        return messageRepository.findAll();
    }

    public Message findById(Long id) {
        return messageRepository.findById(id).get();
    }

    public Message saveMessage(Message message){
        return messageRepository.save(message);
    }

    @Transactional
    public Message createMessage(MessageBuilder messageBuilder) {
        Message message = new Message();
        message.setId(0L);
        message.setMessageItems(new ArrayList<>(messageBuilder.getItems()));
        for (MessageItem mi : messageBuilder.getItems()) {
            mi.setMessage(message);
        }
        return message;
    }

}
