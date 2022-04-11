package it.matty.jump.listeners;

import it.matty.jump.JumpPlugin;
import it.matty.jump.builders.ItemBuilder;
import it.matty.jump.jumps.objects.JumpPlayer;
import it.matty.jump.utils.MessageUtils;
import it.matty.jump.utils.Placeholder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public record InventoryListener(JumpPlugin plugin) implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        JumpPlayer jumpPlayer = plugin.getJumpAPI().getByPlayer(player);

        if (!event.getView().title().equals(MessageUtils.getMessage(plugin, "gui.title"))) return;
        event.setCancelled(true);

        switch (event.getSlot()) {
            case 12 -> {
                if (jumpPlayer.getLevel() == 0) return;
                jumpPlayer.decreases(plugin);
            }
            case 14 -> jumpPlayer.increases(plugin);
        }

        event.getInventory().setItem(13, new ItemBuilder(plugin).fromConfig("gui.jump-status",
                new Placeholder("%jump%", jumpPlayer.getLevel() + "")).build());
    }
}
