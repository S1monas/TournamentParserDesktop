package model;


public class User {
	private String username;
	private String password;
	private int userlevel;
	private String email;
	private int timestamp;
	
	public User() {		
	}
	
	public User(String username, String password, int userlevel, String email) {
		super();
		this.username = username;
		this.password = password;
		this.userlevel = userlevel;
		this.email = email;
	}
	
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUserlevel() {
		return userlevel;
	}
	public void setUserlevel(int userlevel) {
		this.userlevel = userlevel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTimestamp() {
		return timestamp;
	}
	
 
	
	
}
