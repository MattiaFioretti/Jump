package it.matty.jump.database.managers;

import it.matty.jump.JumpPlugin;
import it.matty.jump.database.DatabaseConnector;
import it.matty.jump.database.SQLManager;
import it.matty.jump.database.enums.Query;
import it.matty.jump.database.enums.Table;
import it.matty.jump.jumps.JumpAPI;
import org.bukkit.OfflinePlayer;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.UUID;


public class SQLJumpManager extends SQLManager {

    public SQLJumpManager(DatabaseConnector pool, JumpPlugin plugin) {
        super(pool);
    }

    public void loadCache(JumpAPI api) {
        try (CachedRowSet set = executeQuery(Query.LOAD_DATA.getQuery())) {
            while (set.next()) {
                api.loadPlayer(UUID.fromString(set.getString("player")), set.getInt("level"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        executeUpdateSync(Table.JUMP_PLAYERS.getTable());
    }

    public void registerPlayer(OfflinePlayer player) {
        executeUpdateAsync(Query.INSERT_PLAYER.getQuery(), player.getUniqueId().toString());
    }

    public void updatePlayerLevel(UUID uuid, int level) {
        executeUpdateAsync(Query.UPDATE_PLAYER_LEVEL.getQuery(), level, uuid.toString());
    }

}
