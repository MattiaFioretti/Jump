package it.matty.jump.database;

import lombok.RequiredArgsConstructor;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public abstract class SQLManager {
    private final DatabaseConnector databaseConnector;

    public CompletableFuture<Void> executeUpdateAsync(String query, Object... args) {
        return CompletableFuture.runAsync(() -> executeUpdateSync(query, args));
    }

    public void executeUpdateSync(String query, Object... args) {
        Connection connection = databaseConnector.getConnection();
        try (PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(query)) {
            if (args.length != 0)
                for (int i = 1; i <= args.length; i++)
                    statement.setObject(i, args[i - 1]);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CachedRowSet executeQuery(String query, Object... args) {
        Connection connection = databaseConnector.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            if (args != null && args.length != 0)
                for (int i = 1; i <= args.length; i++)
                    statement.setObject(i, args[i - 1]);

            CachedRowSet cachedRowSet = RowSetProvider.newFactory().createCachedRowSet();
            cachedRowSet.populate(statement.executeQuery());
            return cachedRowSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
