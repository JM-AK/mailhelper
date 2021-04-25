package ru.dv.mailhelper.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import ru.dv.mailhelper.entities.Contact;

import java.util.List;

@Repository
public class ContactMapper {
    private static final String GET_CONTACT_BY_ID = "select first_name, last_name, email, phone  " +
            "from contacts" +
            " where id = :id";

    private static final String GET_CONTACT_ALL = "select *  " +
            "from contacts order by id";

    private static final String SAVE_CONTACT = "insert into contacts (first_name, last_name, email, phone)" +
            " values (:first_name, :last_name, :email, :phone)";

    private final Sql2o sql2o;

    public ContactMapper (@Autowired Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    public Long save(Contact contact) {
        try(Connection connection = sql2o.open())  {
            return (Long) connection.createQuery(SAVE_CONTACT, true)
                    .addParameter("first_name", contact.getFirstName())
                    .addParameter("last_name", contact.getLastName())
                    .addParameter("email", contact.getEmail())
                    .addParameter("phone", contact.getPhone())
                    .executeUpdate().getKey();
        }
    }

    public List<Contact> findAll() {
        try(Connection connection = sql2o.open())  {
            return connection.createQuery(GET_CONTACT_ALL, false)
                    .executeAndFetch(Contact.class);
        }
    }

    public Contact findById(Long id) {
        try(Connection connection = sql2o.open())  {
            return connection.createQuery(GET_CONTACT_BY_ID, false)
                    .addParameter("id", id)
                    .executeScalar(Contact.class);
        }
    }
}
