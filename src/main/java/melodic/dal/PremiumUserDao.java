package melodic.dal;

import melodic.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



/**
 * Data access object (DAO) class to interact with the underlying Persons table in your MySQL
 * instance. This is used to store {@link PremiumUser} into your MySQL instance and retrieve 
 * {@link PremiumUser} from MySQL instance.
 */
public class PremiumUserDao extends UserDao{
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static PremiumUserDao instance = null;
	protected PremiumUserDao() {
		connectionManager = new ConnectionManager();
	}
	public static PremiumUserDao getInstance() {
		if(instance == null) {
			instance = new PremiumUserDao();
		}
		return instance;
	}
	
	public PremiumUser create(PremiumUser premiumUser) throws SQLException{
		User user = create(new User(premiumUser.getUserName(),
				premiumUser.getEmail(),premiumUser.getDob(),premiumUser.getDescription(),
				premiumUser.isRegistered(),premiumUser.getAddress()));
		
		String insertPremiumUser = 
				"INSERT INTO PremiumUser(UserId, Level, ExpirationDate) VALUE(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPremiumUser);
			// PreparedStatement allows us to substitute specific types into the query template.
			// For an overview, see:
			// http://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html.
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// For nullable fields, you can check the property first and then call setNull()
			// as applicable.
			insertStmt.setInt(1, user.getUserId());
			insertStmt.setInt(2, premiumUser.getLevel());
			insertStmt.setDate(3, premiumUser.getExpirationDate());
			
			// Note that we call executeUpdate(). This is used for a INSERT/UPDATE/DELETE
			// statements, and it returns an int for the row counts affected (or 0 if the
			// statement returns nothing). For more information, see:
			// http://docs.oracle.comß/javase/7/docs/api/java/sql/PreparedStatement.html
			// I'll leave it as an exercise for you to write UPDATE/DELETE methods.
			insertStmt.executeUpdate();
			premiumUser.setUserId(user.getUserId());
			// Note 1: if this was an UPDATE statement, then the person fields should be
			// updated before returning to the caller.
			// Note 2: there are no auto-generated keys, so no update to perform on the
			// input param person.
			return premiumUser;
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
		}
		
		
	}
	
	
public PremiumUser delete(PremiumUser premiumUser) throws SQLException {
	String deletePremiumUser = "DELETE FROM PremiumUser WHERE UserId=?;";
	Connection connection = null;
	PreparedStatement deleteStmt = null;
	try {
		connection = connectionManager.getConnection();
		deleteStmt = connection.prepareStatement(deletePremiumUser);
		deleteStmt.setInt(1, premiumUser.getUserId());
		int affectedRows = deleteStmt.executeUpdate();
		if (affectedRows == 0) {
			throw new SQLException("No records available to delete for UserId=" + premiumUser.getUserId());
		}

		// Then also delete from the superclass.
		// Notes:
		// 1. Due to the fk constraint (ON DELETE CASCADE), we could simply call
		//    super.delete() without even needing to delete from Administrators first.
		// 2. BlogPosts has a fk constraint on BlogUsers with the reference option
		//    ON DELETE SET NULL. If the BlogPosts fk reference option was instead
		//    ON DELETE RESTRICT, then the caller would need to delete the referencing
		//    BlogPosts before this BlogUser can be deleted.
		//    Example to delete the referencing BlogPosts:
		//    List<BlogPosts> posts = BlogPostsDao.getBlogPostsForUser(blogUser.getUserName());
		//    for(BlogPosts p : posts) BlogPostsDao.delete(p);
		super.delete(premiumUser);

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
}
