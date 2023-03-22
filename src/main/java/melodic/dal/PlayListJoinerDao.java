package melodic.dal;

import melodic.model.PlayListJoiner;
import java.sql.*;


public class PlayListJoinerDao {
    protected ConnectionManager connectionManager;
    // Single pattern: instantiation is limited to one object.
    private static PlayListJoinerDao instance = null;

    protected PlayListJoinerDao() {
        connectionManager = new ConnectionManager();
    }

    public static PlayListJoinerDao getInstance() {
        if (instance == null) {
            instance = new PlayListJoinerDao();
        }
        return instance;
    }

    // create
    public PlayListJoiner create(PlayListJoiner playListJoiner) throws SQLException {
        String insertPLJ =
                "INSERT INTO PlayListJoiner(PlayListId, TrackId) " +
                        "VALUES(?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertPLJ,
                    Statement.RETURN_GENERATED_KEYS);
            insertStmt.setLong(1, playListJoiner.getPlayList().getPlayListId());
            insertStmt.setInt(2, playListJoiner.getTrack().getTrackId());
            insertStmt.executeUpdate();

            // Retrieve the auto-generated key and set it, so it can be used by the caller.
            resultKey = insertStmt.getGeneratedKeys();
            int pljId = -1;
            if (resultKey.next()) {
                pljId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            playListJoiner.setJoinerId(pljId);

            return playListJoiner;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (insertStmt != null) {
                insertStmt.close();
            }
            if (resultKey != null) {
                resultKey.close();
            }
        }
    }
}