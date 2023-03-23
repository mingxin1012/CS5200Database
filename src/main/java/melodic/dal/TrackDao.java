package melodic.dal;
import melodic.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class TrackDao {
    protected ConnectionManager connectionManager;
    // Single pattern: instantiation is limited to one object.
    private static TrackDao instance = null;
    protected TrackDao() {
        connectionManager = new ConnectionManager();
    }
    public static TrackDao getInstance() {
        if(instance == null) {
            instance = new TrackDao();
        }
        return instance;
    }
    // create
    public Track create(Track track) throws SQLException {
        String insertReshare =
                "INSERT INTO Track(TrackName,AlbumId,ArtistName,trackURL,ArtistURL,DurationMS,AlbumName) " +
                        "VALUES(?,?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertReshare,
                    Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, track.getTrackName());
            insertStmt.setInt(2, track.getAlbum().getAlbumId());
            insertStmt.setString(3, track.getArtistName());
            insertStmt.setString(4, track.getTrack_uri());
            insertStmt.setString(5, track.getArtist_uri());
            insertStmt.setInt(6, track.getDuration_ms());
            insertStmt.setString(7, track.getAlbum_name());
            insertStmt.executeUpdate();

            // Retrieve the auto-generated key and set it, so it can be used by the caller.
            resultKey = insertStmt.getGeneratedKeys();
            int trackId = -1;
            if(resultKey.next()) {
                trackId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            track.setTrackId(trackId);

            return track;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(insertStmt != null) {
                insertStmt.close();
            }
            if(resultKey != null) {
                resultKey.close();
            }
        }
    }

    public Track getTrackFromTrackURL(String trackURL) throws SQLException {
        String selectCreditCards =
                "SELECT * FROM Track WHERE TrackURL=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCreditCards);
            selectStmt.setString(1, trackURL);
            results = selectStmt.executeQuery();
            while(results.next()) {
                int trackId = results.getInt("TrackId");
                String trackName = results.getString("TrackName");
                int albumId = results.getInt("AlbumId");
                String artistName = results.getString("ArtistName");
                String trackURL_ = results.getString("TrackURI");
                String artistURL = results.getString("ArtistURI");
                int durationMS = results.getInt("DurationMS");
                String albumName = results.getString("AlbumName");
                return new Track(trackId,trackName, new Album(albumId, albumName), artistName, trackURL_, artistURL, durationMS, albumName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return null;
    }
    
    public List<Track> getTrackByTrackName(String trackName) throws SQLException {
    	 List<Track> tracks = new ArrayList<>();
        String selectTrackName =
                "SELECT * FROM Track WHERE LOWER(TrackName) LIKE LOWER(?);";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectTrackName);
            selectStmt.setString(1,  "%"+ trackName + "%");
            results = selectStmt.executeQuery();
            while(results.next()) {
                int trackId = results.getInt("TrackId");
                String resultTrackName = results.getString("TrackName");
                int albumId = results.getInt("AlbumId");
                String artistName = results.getString("ArtistName");
                String trackURL_ = results.getString("TrackURI");
                String artistURL = results.getString("ArtistURI");
                int durationMS = results.getInt("DurationMS");
                String albumName = results.getString("AlbumName");
                tracks.add(new Track(trackId,resultTrackName, new Album(albumId, albumName), artistName, trackURL_, artistURL, durationMS, albumName));
 
            }
            return tracks;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
    }


}
