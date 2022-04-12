package it.matty.jump.jumps.objects;

import it.matty.jump.JumpPlugin;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data @AllArgsConstructor
public class JumpPlayer {
    private final UUID uuid;
    private int level;

    public void increases(JumpPlugin plugin) {
        level++;
        plugin.getDatabaseConnector().getJumpManager().updatePlayerLevel(uuid, level);
    }

    public void decreases(JumpPlugin plugin) {
        level--;
        plugin.getDatabaseConnector().getJumpManager().updatePlayerLevel(uuid, level);
    }
}
