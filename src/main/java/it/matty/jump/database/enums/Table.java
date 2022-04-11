package it.matty.jump.database.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor @Getter
public enum Table {
    JUMP_PLAYERS("CREATE TABLE IF NOT EXISTS jump_players(id INT AUTO_INCREMENT PRIMARY KEY, player VARCHAR(100) NOT NULL, level INTEGER NOT NULL DEFAULT 0)");

    private final String table;
}
