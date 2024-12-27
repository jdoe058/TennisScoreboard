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
        int page = parseIntParam(req.getParameter("page"), 1);
        req.setAttribute("page", page);
        int offset = (page - 1) * limit;
        String filter = req.getParameter("filter");

        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {
            long totalPages = MatchFilter.getTotalMatches(session, filter);
            totalPages /= limit;
            totalPages++;

            req.setAttribute("totalPages", totalPages);
            req.setAttribute("matches", MatchFilter.getMatches(session, limit, offset, filter));
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
    public static final String WHERE_FILTER_HQL = """
            WHERE LOWER(m.playerOne.name) LIKE LOWER(:filter)
                OR LOWER(m.playerTwo.name) LIKE LOWER(:filter)
            """;

    public List<Match> getMatches(Session session, int limit, int offset, String filter) {
        Transaction transaction = null;

        try {
            boolean isFilter = isFilter(filter);
            StringBuilder hql = new StringBuilder(" FROM Match m ");
            if (isFilter) {
                hql.append(WHERE_FILTER_HQL);
            }
            hql.append(" ORDER BY m.id ");

            transaction = session.beginTransaction();
            Query<Match> query = session
                    .createQuery(hql.toString(), Match.class)
                    .setMaxResults(limit)
                    .setFirstResult(offset);
            if (isFilter) {
                query.setParameter("filter", "%" + filter + "%");
            }

            List<Match> list = query.list();
            transaction.commit();
            return list;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public long getTotalMatches(Session session, String filter) {
        Transaction transaction = null;

        try {
            StringBuilder hql = new StringBuilder("SELECT COUNT(m) FROM Match m ");
            boolean isFilter = isFilter(filter);
            if (isFilter) {
                hql.append(WHERE_FILTER_HQL);
            }

            transaction = session.beginTransaction();
            Query<Long> query = session.createQuery(hql.toString(), Long.class);
            if (filter != null && !filter.isBlank()) {
                query.setParameter("filter", "%" + filter + "%");
            }

            long totalMatches = query.uniqueResult();
            transaction.commit();
            return totalMatches;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    private static boolean isFilter(String filter) {
        return filter != null && !filter.isBlank();
    }
}
