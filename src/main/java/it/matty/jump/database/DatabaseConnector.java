package it.matty.jump.database;

import it.matty.jump.JumpPlugin;
import it.matty.jump.database.managers.SQLJumpManager;
import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
public class DatabaseConnector {
    private Connection connection;
    private final JumpPlugin plugin;

    private SQLJumpManager jumpManager;

    public DatabaseConnector(JumpPlugin plugin) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(
                    String.format("jdbc:mysql://%s/%s",
                            plugin.getConfig().getString("sql.hostname"),
                            plugin.getConfig().getString("sql.database")),
                    plugin.getConfig().getString("sql.auth.username"),
                    plugin.getConfig().getString("sql.auth.password"));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        this.plugin = plugin;
    }

    public void setup() {
        this.jumpManager = new SQLJumpManager(this, plugin);
        this.jumpManager.createTable();
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
