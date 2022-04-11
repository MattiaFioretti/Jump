package it.matty.jump.utils;

import it.matty.jump.JumpPlugin;
import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;

@UtilityClass
public class MessageUtils {

    public Component getMessage(JumpPlugin plugin, String path) {
        return Component.text(textColor(plugin.getConfig().getString(path)));
    }

    public String textColor(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public String textColor(String text, Placeholder... placeholders) {
        String result = ChatColor.translateAlternateColorCodes('&', text);
        for(Placeholder placeholder : placeholders)
            result = result.replace(placeholder.placeholder(), placeholder.result());
        return result;
    }
}
