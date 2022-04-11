package it.matty.jump.listeners;

import it.matty.jump.JumpPlugin;
import it.matty.jump.jumps.objects.JumpPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public record JumpListener(JumpPlugin plugin) implements Listener {

    @EventHandler
    public void onToggleFlight(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        JumpPlayer jumpPlayer = plugin.getJumpAPI().getByPlayer(player);

        event.setCancelled(true);
        player.setFlying(false);

        player.setVelocity(player.getLocation().getDirection().setY(jumpPlayer.getLevel()));
    }
}
