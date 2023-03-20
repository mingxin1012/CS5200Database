package melodic.model;

import java.util.Date;

public class Singer extends User {

	protected boolean isRetired;

	public Singer(int userId, String userName, String email, Date dob, String description, boolean isRegistered,
			String address, boolean isRetired) {
		super(userId, userName, email, dob, description, isRegistered, address);
		this.isRetired = isRetired;
	}

	public Singer(String userName, String email, Date dob, String description, boolean isRegistered,
			String address, boolean isRetired) {
		super( userName, email, dob, description, isRegistered, address);
		this.isRetired = isRetired;
	}

	public Singer(int userId) {
		super(userId);
	}


	public boolean isRetired() {
		return isRetired;
	}


	public void setRetired(boolean isRetired) {
		this.isRetired = isRetired;
	}
	
}