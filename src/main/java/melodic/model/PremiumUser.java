package melodic.model;

import java.sql.Date;

public class PremiumUser extends User {

	protected int level;
	protected Date expirationDate;
	
	public PremiumUser(int userId, String userName, String email, java.util.Date dob, String description,
			boolean isRegistered, String address, int level, Date expirationDate) {
		super(userId, userName, email, dob, description, isRegistered, address);
		this.level = level;
		this.expirationDate = expirationDate;
	}

	public PremiumUser(String userName, String email, java.util.Date dob, String description,
			boolean isRegistered, String address, int level, Date expirationDate) {
		super(userName, email, dob, description, isRegistered, address);
		this.level = level;
		this.expirationDate = expirationDate;
	}

	
	public PremiumUser(int userId) {
		super(userId);
	}
	
	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public Date getExpirationDate() {
		return expirationDate;
	}


	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
}