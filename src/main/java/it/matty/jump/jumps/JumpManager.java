package it.matty.jump.jumps;

import it.matty.jump.JumpPlugin;
import it.matty.jump.jumps.objects.JumpPlayer;
import lombok.Getter;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class JumpManager implements JumpAPI {
    private final JumpPlugin plugin;
    private final Map<UUID, JumpPlayer> jumpPlayers = new ConcurrentHashMap<>();

    public JumpManager(JumpPlugin plugin) {
        this.plugin = plugin;
        this.plugin.getPoolManager().getJumpManager().loadCache(this);
    }

    @Override @Nullable
    public JumpPlayer getByPlayer(OfflinePlayer player) {
        return this.jumpPlayers.get(player.getUniqueId());
    }

    @Override
    public void registerPlayer(OfflinePlayer player) {
        this.jumpPlayers.put(player.getUniqueId(), new JumpPlayer(player.getUniqueId(), 0));
        this.plugin.getPoolManager().getJumpManager().registerPlayer(player);
    }

    @Override
    public void loadPlayer(UUID uuid, int level) {
        this.jumpPlayers.put(uuid, new JumpPlayer(uuid, level));
    }

}
