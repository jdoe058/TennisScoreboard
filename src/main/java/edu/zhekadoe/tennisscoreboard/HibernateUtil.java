package edu.zhekadoe.tennisscoreboard;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {
    @Getter
    SessionFactory sessionFactory = buildSessionFactory();

    public void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    SessionFactory buildSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }
}
