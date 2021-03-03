package ru.dv.mailhelper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dv.mailhelper.entities.Mailing;
import ru.dv.mailhelper.repositories.MailingRepository;

import java.util.List;

@Service
public class MailingService {
    private MailingRepository mailingRepository;

    @Autowired
    public void setMailingRepository(MailingRepository mailingRepository) {
        this.mailingRepository = mailingRepository;
    }

    @Transactional
    public List<Mailing> findAllMailing(){
        return mailingRepository.findAll();
    }


}
