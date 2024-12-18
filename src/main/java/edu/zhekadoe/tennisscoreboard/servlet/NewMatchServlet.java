package edu.zhekadoe.tennisscoreboard.servlet;

import java.io.*;
import java.util.*;

import edu.zhekadoe.tennisscoreboard.service.OngoingMatchesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(NewMatchServlet.class);
    public final String PLAYER_NAME_PATTERN = "[A-Za-z]. [A-Za-z]+";
    public final String PLAYER_NAME_EXAMPLE = "n. surname";

    public static final String PLAYER_NAME_ERROR_FORMAT_MESSAGE = "Error in %s name. Enter a name in the format %s";
    public static final String UNIQUE_PLAYERS_ERROR_FORMAT_MESSAGE = "%s cannot play by himself";
    public static final List<PlayersParam> PLAYERS_PARAM = List.of(
            new PlayersParam("playerOne", "Player one"),
            new PlayersParam("playerTwo", "Player two"));
    private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("playerNamePattern", PLAYER_NAME_PATTERN.replace(".", "\\."));
        request.setAttribute("playerNameExample", PLAYER_NAME_EXAMPLE);
        request.setAttribute("players", PLAYERS_PARAM);
        request.getRequestDispatcher("/WEB-INF/jsp/new-match.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> playerNames = extractPlayerNames(req);
        List<String> errors = validatePlayerNames(playerNames);

        if (errors.isEmpty()) {
            try {
                UUID uuid = ongoingMatchesService.create(playerNames);
                resp.sendRedirect("%s/match-score?uuid=%s".formatted(req.getContextPath(), uuid));
            } catch (Exception e) {
                logger.error("Error creating match", e);
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                req.setAttribute("errors", "Unable to create match");
                doGet(req, resp);
            }
        } else {
            logger.warn("Validation failed: {}", errors);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("errors", errors);
            doGet(req, resp);
        }
    }

    private List<String> extractPlayerNames(HttpServletRequest req) {
        return PLAYERS_PARAM.stream()
                .map(PlayersParam::name)
                .map(req::getParameter)
                .map(String::trim)
                .toList();
    }

    private List<String> validatePlayerNames(List<String> playerNames) {
        Set<String> uniqueNames = new HashSet<>();
        List<String> errors = new ArrayList<>();

        for (int i = 0; i < PLAYERS_PARAM.size(); i++) {
            String label = PLAYERS_PARAM.get(i).label();
            String name = playerNames.get(i);

            if (name == null || !name.matches(PLAYER_NAME_PATTERN)) {
                errors.add(PLAYER_NAME_ERROR_FORMAT_MESSAGE
                        .formatted(label, PLAYER_NAME_EXAMPLE));
                continue;
            }

            if (!uniqueNames.add(name)) {
                errors.add(UNIQUE_PLAYERS_ERROR_FORMAT_MESSAGE
                        .formatted(label));
            }
        }
        return errors;
    }

    public record PlayersParam(String name, String label) {
    }
}
