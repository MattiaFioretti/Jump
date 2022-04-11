package it.matty.jump.commands;

import it.matty.jump.JumpPlugin;
import it.matty.jump.builders.ItemBuilder;
import it.matty.jump.jumps.objects.JumpPlayer;
import it.matty.jump.utils.MessageUtils;
import it.matty.jump.utils.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public record JumpCommand(JumpPlugin plugin) implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return false;
        JumpPlayer jumpPlayer = plugin.getJumpAPI().getByPlayer(player);

        Inventory inventory = Bukkit.createInventory(null, 27, MessageUtils.getMessage(plugin, "gui.title"));
        inventory.setItem(12, new ItemBuilder(plugin).fromConfig("gui.jump-decrease").build());
        inventory.setItem(14, new ItemBuilder(plugin).fromConfig("gui.jump-increase").build());
        inventory.setItem(13, new ItemBuilder(plugin).fromConfig("gui.jump-status",
                new Placeholder("%jump%", jumpPlayer.getLevel() + "")).build());

        player.openInventory(inventory);
        return true;
    }
}
