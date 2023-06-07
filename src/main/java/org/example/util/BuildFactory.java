package org.example.util;


import lombok.experimental.UtilityClass;
import org.example.convertor.BirthdayConverter;
import org.example.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class BuildFactory {

    public static SessionFactory buildSessionFactory() {
        //        BlockingDeque<Connection> pool = null;
//        Connection connection = pool.take();
//        SessionFactory

//        Connection connection = DriverManager
//                .getConnection("db.url", "db.username", "db.password");
//        Session
        /**
         * Configuration
         * Внутри есть:
         * 1. BysicType интерфейс для преобразования всех типов данных. Для касмтомных можно создать свой конвертор
         * 2.
         */
        Configuration configuration = new Configuration();
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        configuration.addAnnotatedClass(User.class);
        configuration.addAttributeConverter(new BirthdayConverter());
        //configuration.registerTypeOverride(new JsonBinaryType());
        configuration.configure();

        return configuration.buildSessionFactory();
    }
}
