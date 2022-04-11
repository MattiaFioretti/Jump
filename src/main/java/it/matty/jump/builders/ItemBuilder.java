package it.matty.jump.builders;

import it.matty.jump.JumpPlugin;
import it.matty.jump.utils.MessageUtils;
import it.matty.jump.utils.Placeholder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {
    private JumpPlugin plugin;
    private Material material;
    private String name;
    private List<String> lore;

    public ItemBuilder() {}

    public ItemBuilder(JumpPlugin plugin) {
        this.plugin = plugin;
    }

    public ItemBuilder fromConfig(String path, Placeholder... placeholders) {
        this.material = Material.valueOf(plugin.getConfig().getString(path + ".material"));
        name(plugin.getConfig().getString(path + ".name"), placeholders);
        lore(plugin.getConfig().getStringList(path + ".lore"), placeholders);
        return this;
    }

    public ItemBuilder(Material material) {
        this.material = material;
    }

    public ItemBuilder name(String name, Placeholder... placeholders) {
        this.name = MessageUtils.textColor(name, placeholders);
        return this;
    }

    public ItemBuilder lore(List<String> lore, Placeholder... placeholders) {
        List<String> loreList = new ArrayList<>();

        for(String string : lore)
            loreList.add(MessageUtils.textColor(string, placeholders));

        this.lore = loreList;
        return this;
    }

    public ItemBuilder material(Material material) {
        this.material = material;
        return this;
    }

    public ItemStack build() {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();

        if(name != null)
            itemMeta.setDisplayName(name);

        if(lore != null)
            itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
