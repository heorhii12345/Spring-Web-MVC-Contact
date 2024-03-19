package org.example.app.repository.impl.contact;

import org.example.app.entity.Contact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ContactRepositoryImpl implements ContactRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean create(Contact contact) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "INSERT INTO Contact (name, surname, phone) " +
                "VALUES (:name, :surname, :phone)";
        MutationQuery query = session.createMutationQuery(hql);
        query.setParameter("name", contact.getName());
        query.setParameter("surname", contact.getSurname());
        query.setParameter("phone", contact.getPhone());
        return query.executeUpdate() > 0;
    }

    @Override
    public Optional<List<Contact>> fetchAll() {
        try {
            Session session = sessionFactory.getCurrentSession();
            List<Contact> list = session.createQuery("FROM Contact",
                    Contact.class).list();
            return Optional.of(list);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean update(Long id, Contact contact) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "UPDATE Contact SET name = :name, " +
                    "surname = :surname, " +
                    "phone = :phone, " +
                    "WHERE id = :id";
            MutationQuery query = session.createMutationQuery(hql);
            query.setParameter("Name", contact.getName());
            query.setParameter("Surname", contact.getSurname());
            query.setParameter("Phone", contact.getPhone());
            query.setParameter("id", contact.getId());
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Contact contact = session.byId(Contact.class).load(id);
            session.remove(contact);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<Contact> fetchById(Long id) {
        Optional<Contact> optional;
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = " FROM Contact b WHERE b.id = :id";
            Query<Contact> query = session.createQuery(hql, Contact.class);
            query.setParameter("id", id);
            optional = query.uniqueResultOptional();
            return optional;
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
