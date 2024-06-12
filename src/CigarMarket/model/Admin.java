package CigarMarket.model;

public class Admin {
	private String id = "admin";
	private String pw = "1234";
	public boolean login(String id, String pw) {
		if(this.id.equals(id) && this.pw.equals(pw))
			return true;
		return false;
	}

}
