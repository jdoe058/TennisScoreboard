package edu.zhekadoe.tennisscoreboard.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PlayerName {
    public final String PATTERN = "[A-Za-z]. [A-Za-z]+";
    public final String EXAMPLE = "n. surname";

    /**
     * Проверяет, соответствует ли имя игрока заданному шаблону.
     * @param name Имя игрока для проверки.
     * @return true, если имя некорректно; false в противном случае.
     */
    public boolean hasError(String name) {
        return name == null || !name.matches(PATTERN);
    }
}
