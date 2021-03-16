package ru.dv.mailhelper.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dv.mailhelper.entities.MessageStatus;

@Repository
public interface MessageStatusRepository extends CrudRepository<MessageStatus, Long> {
}
