package edu.zhekadoe.tennisscoreboard;

import edu.zhekadoe.tennisscoreboard.model.Match;
import edu.zhekadoe.tennisscoreboard.model.Player;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

@WebListener
public class ContextListener implements ServletContextListener {
    Logger logger = LoggerFactory.getLogger(ContextListener.class);
    private static final int INIT_NUMBER_OF_MATCHES = 50;
    private static final List<String> INIT_PLAYER_NAMES = List.of(
            "A. Smith", "B. Johnson", "C. Brown",
            "D. Davis", "E. Miller", "F. Wilson",
            "G. Moore", "H. Taylor", "I. Anderson",
            "J. Thomas"
    );
    private final Random random = new Random();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Initializing context and creating matches...");

        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {

            session.beginTransaction();
            for (int i = 0; i < INIT_NUMBER_OF_MATCHES; i++) {
                session.persist(createMatch(session));
            }
            session.getTransaction().commit();
            logger.info("Successfully created {} matches.", INIT_NUMBER_OF_MATCHES);
        } catch (Exception e) {
            logger.error("Error occurred while creating matches: ", e);
        }
    }

    private Match createMatch(Session session) {
        String nameOne = getRandomPlayerName();
        String nameTwo;

        do {
            nameTwo = getRandomPlayerName();
        } while (nameTwo.equals(nameOne));

        Player playerOne = getOrCreatePlayer(session, nameOne);
        Player playerTwo = getOrCreatePlayer(session, nameTwo);

        return new Match(playerOne, playerTwo, random.nextBoolean());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Shutting down Hibernate session factory.");
        HibernateUtil.shutdown();
    }

    private String getRandomPlayerName() {
        return INIT_PLAYER_NAMES.get(
                random.nextInt(INIT_PLAYER_NAMES.size()));
    }

    private Player getOrCreatePlayer(Session session, String playerName) {
        Player player = session.createQuery(
                "FROM Player WHERE name = :name", Player.class)
                .setParameter("name", playerName)
                .uniqueResult();

        if (player == null) {
            player = new Player(playerName);
            session.persist(player);
        }
        return player;
    }
}
