package melodic.model;

import java.util.Date;

public class User {
	protected int userId;
	protected String userName;
	protected String email;
	protected Date dob;
	protected String description;
	protected boolean isRegistered;
	protected String address;
	
	public User(int userId, String userName, String email, Date dob, String description, boolean isRegistered,
			String address) {
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.dob = dob;
		this.description = description;
		this.isRegistered = isRegistered;
		this.address = address;
	}
	public User(String userName, String email, Date dob, String description, boolean isRegistered,
			String address) {
		this.userName = userName;
		this.email = email;
		this.dob = dob;
		this.description = description;
		this.isRegistered = isRegistered;
		this.address = address;
	}
	
	public User(int userId) {
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isRegistered() {
		return isRegistered;
	}

	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}