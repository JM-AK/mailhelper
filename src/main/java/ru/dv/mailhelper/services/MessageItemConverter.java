package ru.dv.mailhelper.services;

import org.springframework.stereotype.Service;
import ru.dv.mailhelper.entities.MessageItem;
import ru.dv.mailhelper.entities.dtos.MessageItemDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageItemConverter {
    public List<MessageItemDto> mapEntityListToDtoList(List<MessageItem> messageItemList) {
        return messageItemList.stream().map(MessageItemDto::new).collect(Collectors.toList());
    }
}
