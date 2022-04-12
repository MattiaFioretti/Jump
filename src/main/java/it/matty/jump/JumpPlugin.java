package it.matty.jump;

import it.matty.jump.commands.JumpCommand;
import it.matty.jump.database.DatabaseConnector;
import it.matty.jump.jumps.JumpAPI;
import it.matty.jump.jumps.JumpManager;
import it.matty.jump.listeners.InventoryListener;
import it.matty.jump.listeners.JumpListener;
import it.matty.jump.listeners.PlayerListener;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class JumpPlugin extends JavaPlugin {
    private DatabaseConnector poolManager;
    private JumpAPI jumpAPI;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        this.poolManager = new DatabaseConnector(this);
        this.poolManager.setup();

        this.jumpAPI = new JumpManager(this);

        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(this), this);
        getServer().getPluginManager().registerEvents(new JumpListener(this), this);

        getCommand("jump").setExecutor(new JumpCommand(this));
    }

    @Override
    public void onDisable() {
        this.poolManager.disconnect();
    }
}
