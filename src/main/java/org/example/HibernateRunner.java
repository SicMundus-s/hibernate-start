package org.example;


import lombok.extern.slf4j.Slf4j;
import org.example.entity.Company;
import org.example.entity.PersonalInfo;
import org.example.entity.Role;
import org.example.entity.User;
import org.example.util.BuildFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;

@Slf4j
public class HibernateRunner {
    // private static final Logger log = LoggerFactory.getLogger(HibernateRunner.class); // Аннотация делает то же самое

    public static void main(String[] args) throws SQLException {

        try (SessionFactory sessionFactory = BuildFactory.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            log.trace("Transaction is created, {}", transaction);

            Company company = Company.builder()
                    .name("Google")
                    .build();
            User user = User.builder()
                    .username("ivan@gmail.com1")
                    .personalInfo(PersonalInfo.builder()
                            .firstname("ivan")
                            .lastname("petrov")
                            .build())
                    .role(Role.ADMIN)
                    .company(company)
                    .build();
            log.info("User entity is in transient state, object: {}", user);

            /**
            User getUser = session.get(User.class, "ivan@gmail.com3");
            User getUser1 = session.get(User.class, "ivan@gmail.com3"); // Запроса к БД не будет, так сущность в кэше
            user.setLastname("DirtySession"); // Все изменния в сущности отразятся на БД, хибернейт сделает доп запрос update
             */
            session.persist(company);
            session.persist(user);
            User user1 = session.get(User.class, 1);

            session.evict(user1); // Удалить сущность из кэша
            session.clear(); // Очистить кэш
            session.getTransaction().commit();
            session.close();
            log.warn("User is in detached state: {}, session is closed {}", user1, session);
        }
    }


}
