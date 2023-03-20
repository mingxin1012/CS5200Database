package melodic.model;

import java.util.Date;


public class StandardUser extends User {

	protected boolean isActive;
	
	public StandardUser(int userId, String userName, String email, Date dob, String description,
			boolean isRegistered, String address, boolean isActive) {
		super(userId, userName, email, dob, description, isRegistered, address);
		this.isActive = isActive;
	}
	
	public StandardUser( String userName, String email, Date dob, String description,
			boolean isRegistered, String address, boolean isActive) {
		super(userName, email, dob, description, isRegistered, address);
		this.isActive = isActive;
	}
	
	public StandardUser(int userId) {
		super(userId);
	}

	public boolean isActive() {
		return isActive;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
}