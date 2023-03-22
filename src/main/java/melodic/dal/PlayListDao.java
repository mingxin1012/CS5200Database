package melodic.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import melodic.model.*;

public class PlayListDao {
	protected ConnectionManager connectionManager;

	private static PlayListDao instance = null;
	protected PlayListDao() {
		connectionManager = new ConnectionManager();
	}
	public static PlayListDao getInstance() {
		if(instance == null) {
			instance = new PlayListDao();
		}
		return instance;
	}
	public PlayList create(PlayList playList) throws SQLException {
		String insertPlayList =
			"INSERT INTO PlayList(UserId,Created) " +
			"VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			
			insertStmt = connection.prepareStatement(insertPlayList,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setLong(1, playList.getUser().getUserId());
			
			insertStmt.setDate(2, (Date) playList.getCreated());
			
			insertStmt.executeUpdate();
		
			resultKey = insertStmt.getGeneratedKeys();
			int playListId = -1;
			if(resultKey.next()) {
				playListId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			playList.setPlayListId(playListId);
			return playList;
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
	
	public PlayList delete(PlayList playList) throws SQLException {
		String deletePlayList = "DELETE FROM PlayList WHERE PlayListId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePlayList);
			deleteStmt.setLong(1, playList.getPlayListId());
			deleteStmt.executeUpdate();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
	
	public PlayList getPlayListById(long playListId) throws SQLException {
		String selectPlayList =
			"SELECT * " +
			"FROM PlayList " +
			"WHERE PlayListId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPlayList);
			selectStmt.setLong(1, playListId);
			results = selectStmt.executeQuery();
			

			if(results.next()) {
				long PlayListId = results.getLong("PlayListId");
				Date Created = results.getDate("Created");
				UserDao ud = UserDao.getInstance();
				User user = ud.getUserFromUserID(results.getInt("UserId"));
				
				PlayList playList = new PlayList(PlayListId,user,Created);
				return playList;
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
	
	public List<PlayList> getPLayListForUser(User user) throws SQLException {
		List<PlayList> playLists = new ArrayList<PlayList>();
		String selectPlayList =
			"SELECT PostId,Title,Picture,Content,Published,Created,UserName " +
			"FROM PlayList " +
			"WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPlayList);
			selectStmt.setLong(1, user.getUserId());
			results = selectStmt.executeQuery();
			while(results.next()) {
				long PlayListId = results.getLong("PlayListId");
				Date Created = results.getDate("Created");
				User u = UserDao.getInstance().getUserFromUserID(results.getInt("UserId"));
				PlayList playList = new PlayList(PlayListId,u,Created);
				playLists.add(playList);
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
		return playLists;
	}

	public List<Track> getTracksForPLayList(PlayList playList) throws SQLException {
		List<Track> tracks = new ArrayList<>();
		String getTracks =
				"SELECT *" +
						"FROM PlayListJoiner " +
						"INNER JOIN Track on Track.TrackId = PlayListJoiner.TrackId" +
						"WHERE PlayListId=?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(getTracks);
			selectStmt.setLong(1, playList.getPlayListId());
			results = selectStmt.executeQuery();
			while(results.next()) {
				int trackId = results.getInt("TrackId");
				int albumId = results.getInt("AlbumId");
				String artistName = results.getString("ArtistName");
				String trackURL = results.getString("TrackURL");
				String artistURL = results.getString("ArtistURL");
				int durationMS = results.getInt("DurationMS");
				String albumName = results.getString("AlbumName");
				Track track = new Track(trackId, new Album(albumId, albumName), artistName, trackURL, artistURL, durationMS);
				tracks.add(track);
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
		return tracks;
	}





}
