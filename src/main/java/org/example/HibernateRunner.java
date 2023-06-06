package org.example;


import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.example.convertor.BirthdayConvertor;
import org.example.entity.Birthday;
import org.example.entity.Role;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.time.LocalDate;

public class HibernateRunner {

    public static void main(String[] args) throws SQLException {
//        BlockingDeque<Connection> pool = null;
//        Connection connection = pool.take();
//        SessionFactory

//        Connection connection = DriverManager
//                .getConnection("db.url", "db.username", "db.password");
//        Session
        /**
         * Внутри есть:
         * 1. BysicType интерфейс для преобразования всех типов данных. Для касмтомных можно создать свой конвертор
         * 2.
         */
        Configuration configuration = new Configuration();
//        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
//        configuration.addAnnotatedClass(User.class);
        configuration.addAttributeConverter(new BirthdayConvertor());
    //    configuration.registerTypeOverride(new JsonBinaryType());
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = User.builder()
                    .username("ivan@gmail.com4")
                    .firstname("Ivan")
                    .lastname("Ivanov")
                    .birthDay(new Birthday(LocalDate.of(2000, 1, 19)))
                    .role(Role.ADMIN)
                    .build();
            session.remove(user);

            User getUser = session.get(User.class, "ivan@gmail.com3");
            User getUser1 = session.get(User.class, "ivan@gmail.com3"); // Запроса к БД не будет, так сущность в кэше
            user.setLastname("DirtySession"); // Все изменния в сущности отразятся на БД, хибернейт сделает доп запрос update

            session.evict(getUser1); // Удалить сущность из кэша
            session.clear(); // Очистить кэш
            System.out.println("getUser: " + getUser);

            session.getTransaction().commit();
        }
    }












}
