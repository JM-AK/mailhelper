package ru.dv.mailhelper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dv.mailhelper.entities.Mailing;
import ru.dv.mailhelper.repositories.MailingRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MailingService {
    private MailingRepository mailingRepository;

    @Autowired
    public void setMailingRepository(MailingRepository mailingRepository) {
        this.mailingRepository = mailingRepository;
    }

    public List<Mailing> findAllMailing(){
        List<Mailing> mailingList = mailingRepository.findAll();
        return mailingList;
    }

    public Mailing saveMailing(Mailing mailing) {
        return mailingRepository.save(mailing);
    }


    public Optional<Mailing> findById(Long id) {
        return mailingRepository.findById(id);
    }
}
