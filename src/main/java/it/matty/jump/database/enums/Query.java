package it.matty.jump.database.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor @Getter
public enum Query {
    INSERT_PLAYER("INSERT INTO jump_players(player) VALUES (?)"),
    UPDATE_PLAYER_LEVEL("UPDATE jump_players SET level = ? WHERE player = ?"),
    LOAD_DATA("SELECT * FROM jump_players");
    
    private final String query;
}
