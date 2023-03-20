package melodic.dal;

import melodic.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CreditCardDao {
	protected ConnectionManager connectionManager;

	private static CreditCardDao instance = null;
	protected CreditCardDao() {
		connectionManager = new ConnectionManager();
	}
	public static CreditCardDao getInstance() {
		if(instance == null) {
			instance = new CreditCardDao();
		}
		return instance;
	}

	public CreditCard create(CreditCard creditCard) throws SQLException {
		String insertCreditCard = "INSERT INTO CreditCards(CardNumber,Expiration,UserName) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			// BlogPosts has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertCreditCard,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setObject(1, creditCard.getCardNumber());
			insertStmt.setTimestamp(2, new Timestamp(creditCard.getExpiration().getTime()));
			insertStmt.setString(3, creditCard.getUserName());
			insertStmt.executeUpdate();
			

			return creditCard;
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


	public CreditCard updateExpiration(CreditCard creditCard, Date newExpiration)
 throws SQLException {
		String updateExpiration = "UPDATE CreditCards SET Expiration=?, WHERE CardNumber=?";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateExpiration);
			updateStmt.setDate(1, (java.sql.Date) newExpiration);
			updateStmt.executeUpdate();

	
			creditCard.setExpiration(newExpiration);
			return creditCard;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}


	public User delete(CreditCard creditCard) throws SQLException {
		String deleteCreditCard = "DELETE FROM CreditCards WHERE CardNumber=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCreditCard);
			deleteStmt.setObject(1, creditCard.getCardNumber());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Credit instance.
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


	public CreditCard getCreditCardByCardNumber(long cardNumber) throws SQLException {
		String selectCreditCard = "SELECT CardNumber,Expiration,UserName FROM CreditCards WHERE CardNumber=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCreditCard);
			selectStmt.setLong(1, cardNumber);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				Long resultCardNumber = results.getLong("CardNumber");	
				Date expiration = new Date(results.getTimestamp("Expiration").getTime());
				String resultUserName = results.getString("UserName");
				CreditCard creditCard = new CreditCard(resultCardNumber, expiration, resultUserName);
				return creditCard;
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

	
	public List<CreditCard> getCreditCardsByUserName(String userName) throws SQLException {
		List<CreditCard> creditCards = new ArrayList<CreditCard>();
		String selectCreditCards = 
				"SELECT CardNumber,Expiration,UserName FROM CreditCards WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCreditCards);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				Long resultCardNumber = results.getLong("CardNumber");	
				Date expiration = new Date(results.getTimestamp("Expiration").getTime());
				String resultUserName = results.getString("UserName");
				CreditCard creditCard = new CreditCard(resultCardNumber, expiration, resultUserName);
		
				creditCards.add(creditCard);
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
		return creditCards;
	}
}