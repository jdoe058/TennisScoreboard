package edu.zhekadoe.tennisscoreboard.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OngoingMatchesService {
    private static final OngoingMatchesService INSTANCE = new OngoingMatchesService();
    public static OngoingMatchesService getInstance() {
        return INSTANCE;
    }

    public UUID create(List<String> playerNames) {
//        throw new RuntimeException("Not implemented yet");
        return UUID.randomUUID();
    }
}
