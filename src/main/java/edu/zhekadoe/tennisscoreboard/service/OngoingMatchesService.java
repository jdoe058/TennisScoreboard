package edu.zhekadoe.tennisscoreboard.service;

import edu.zhekadoe.tennisscoreboard.model.Match;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OngoingMatchesService {
    private static final OngoingMatchesService INSTANCE = new OngoingMatchesService();

    private final ConcurrentHashMap<UUID, Match> ongoingMatches = new ConcurrentHashMap<>();

    public static OngoingMatchesService getInstance() {
        return INSTANCE;
    }

    public UUID create(List<String> playerNames) {
        if (playerNames == null || playerNames.size() != 2) {
            throw new IllegalArgumentException("Exactly two players are required.");
        }

        UUID uuid = UUID.randomUUID();
        ongoingMatches.put(uuid, new Match(playerNames.get(0), playerNames.get(1)));
        return uuid;
    }

    public Match get(String uuid) {
        Match match = ongoingMatches.get(UUID.fromString(uuid));
        if (match == null) {
            throw new IllegalArgumentException("No ongoing match with uuid " + uuid);
        }
        return match;
    }
}
