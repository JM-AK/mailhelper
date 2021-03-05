package ru.dv.mailhelper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dv.mailhelper.entities.Contact;
import ru.dv.mailhelper.entities.Mailing;
import ru.dv.mailhelper.enums.MsgAddressType;
import ru.dv.mailhelper.repositories.MailingRepository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MailingService {
    private MailingRepository mailingRepository;

    @Autowired
    public void setMailingRepository(MailingRepository mailingRepository) {
        this.mailingRepository = mailingRepository;
    }

    @Transactional
    public List<Mailing> findAllMailing(){
        List<Mailing> mailingList = mailingRepository.findAll();
        mailingList.forEach(mailing -> mailing.setMsgAddressMap(sortMapByValue(mailing.getMsgAddressMap())));
        return mailingList;
    }

    public Mailing saveMailing(Mailing mailing) {
        return mailingRepository.save(mailing);
    }


    private Map<Contact , MsgAddressType> sortMapByValue (Map<Contact, MsgAddressType> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

}
