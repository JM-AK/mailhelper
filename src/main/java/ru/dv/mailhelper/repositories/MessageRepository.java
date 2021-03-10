package ru.dv.mailhelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dv.mailhelper.entities.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
