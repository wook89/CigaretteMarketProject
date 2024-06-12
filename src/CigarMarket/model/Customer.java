package CigarMarket.model;

public class Customer extends User {
	
	String email;
	String adress;
	public String getEmail() {
		return email;
	}
	public void setEmail(String emale) {
		this.email = emale;
	}
	public String getAddress() {
		return adress;
	}
	public void setAddress(String adress) {
		this.adress = adress;
	}
}
