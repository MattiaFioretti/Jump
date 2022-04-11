package it.matty.jump.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import it.matty.jump.JumpPlugin;
import it.matty.jump.database.managers.SQLJumpManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;

@RequiredArgsConstructor @Getter
public class ConnectionPoolManager {
    private final JumpPlugin plugin;

    private final HikariDataSource dataSource;
    private SQLJumpManager jumpManager;

    public ConnectionPoolManager(JumpPlugin plugin) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(String.format("jdbc:mysql://%s/%s", plugin.getConfig().getString("sql.hostname"), plugin.getConfig().getString("sql.database")));
        config.setUsername(plugin.getConfig().getString("sql.auth.username"));
        config.setPassword(plugin.getConfig().getString("sql.auth.password"));
        config.addDataSourceProperty("useSSL", plugin.getConfig().getBoolean("sql.useSSL"));
        config.addDataSourceProperty("allowPublicKeyRetrieval", plugin.getConfig().getBoolean("sql.allowPublicKeyRetrieval"));

        this.plugin = plugin;
        this.dataSource = new HikariDataSource(config);
    }

    public void setup() {
        this.jumpManager = new SQLJumpManager(this, plugin);
        this.jumpManager.createTable();
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void disconnect() {
        dataSource.close();
    }

}
