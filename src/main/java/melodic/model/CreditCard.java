package melodic.model;

import java.util.Date;


public class CreditCard {
	
	protected Long cardNumber;
	protected Date expiration;
	protected String userName;
	
	
	public CreditCard(Long cardNumber, Date expiration, String userName) {
		this.cardNumber = cardNumber;
		this.expiration = expiration;
		this.userName = userName;
	}
	
	public Long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public Date getExpiration() {
		return expiration;
	}
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	}