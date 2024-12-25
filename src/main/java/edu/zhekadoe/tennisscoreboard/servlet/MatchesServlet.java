package edu.zhekadoe.tennisscoreboard.servlet;

import edu.zhekadoe.tennisscoreboard.HibernateUtil;
import edu.zhekadoe.tennisscoreboard.model.Match;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(MatchesServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int limit = parseIntParam(req.getParameter("limit"), 4);
        int offset = parseIntParam(req.getParameter("offset"), 0);
        String filter = req.getParameter("filter");

        try {
            req.setAttribute("matches", MatchFilter.getMatches(limit, offset, filter));
        } catch (Exception e) {
            logger.error("Error retrieving matches. Params: limit={}, offset={}, filter={}",
                    limit, offset, filter, e);
            req.setAttribute("errorMessage", "Error retrieving matches.");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/matches.jsp").forward(req, resp);
    }

    private static int parseIntParam(String param, int defaultValue) {
        if (param == null || param.isEmpty()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(param);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}

@UtilityClass
class MatchFilter {
    public List<Match> getMatches(int limit, int offset, String filter) {
        Transaction transaction = null;

        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {

            boolean isPlayerName = filter != null && !filter.isBlank();
            StringBuilder hql = new StringBuilder(" FROM Match m ");
            if (isPlayerName) {
                hql.append("""
                        WHERE LOWER(m.playerOne.name) LIKE LOWER(:filter)
                            OR LOWER(m.playerTwo.name) LIKE LOWER(:filter)
                        """);
            }
            hql.append(" ORDER BY m.id ");

            transaction = session.beginTransaction();
            Query<Match> query = session
                    .createQuery(hql.toString(), Match.class)
                    .setMaxResults(limit)
                    .setFirstResult(offset);

            if (isPlayerName) {
                query.setParameter("filter", "%" + filter + "%");
            }

            List<Match> list = query.list();
            transaction.commit();
            return list;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }
}
