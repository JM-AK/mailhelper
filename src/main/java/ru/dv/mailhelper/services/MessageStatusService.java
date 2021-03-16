package ru.dv.mailhelper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dv.mailhelper.entities.MessageStatus;
import ru.dv.mailhelper.repositories.MessageStatusRepository;

@Service
public class MessageStatusService {
    private MessageStatusRepository messageStatusRepository;

    @Autowired
    public void setMessageStatusRepository(MessageStatusRepository messageStatusRepository) {
        this.messageStatusRepository = messageStatusRepository;
    }

    public MessageStatus getStatusById(Long id) {
        return messageStatusRepository.findById(id).orElse(null);
    }
}
