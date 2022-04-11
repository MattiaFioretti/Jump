package it.matty.jump.listeners;

import it.matty.jump.JumpPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public record PlayerListener(JumpPlugin plugin) implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (plugin.getJumpAPI().getByPlayer(player) != null) return;

        plugin.getJumpAPI().registerPlayer(player);
    }
}
