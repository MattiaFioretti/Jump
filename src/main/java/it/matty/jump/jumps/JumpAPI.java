package it.matty.jump.jumps;

import it.matty.jump.jumps.objects.JumpPlayer;
import org.bukkit.OfflinePlayer;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.UUID;

public interface JumpAPI {

    Map<UUID, JumpPlayer> getJumpPlayers();

    @Nullable
    JumpPlayer getByPlayer(OfflinePlayer player);

    void registerPlayer(OfflinePlayer player);

    void loadPlayer(UUID uuid, int level);

}
