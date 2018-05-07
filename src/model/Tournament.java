package model;

public class Tournament {
	private int gameid;
	private String game;
	private String user;
	private String format;
	private String currency;
	private String buyin;
	private double result;
	
	public Tournament () {
		
	}
	
	
	
	@Override
	public String toString() {
		return "Tournament [gameid=" + gameid + ", game=" + game + ", user=" + user + ", format=" + format
				+ ", currency=" + currency + ", buyin=" + buyin + ", result=" + result + "]";
	}

	public Tournament (String game, String format, String currency, String buyin, double result) {
		this.game = game;
		this.format = format;
		this.currency = currency;
		this.buyin = buyin;
		this.result = result;
	}

	public Tournament (String game, String user, String format, String currency, String buyin, double result) {
		this.gameid = gameid;
		this.game = game;
		this.user = user;
		this.format = format;
		this.currency = currency;
		this.buyin = buyin;
		this.result = result;
		
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public int getGameid() {
		return gameid;
	}

	public void setGameid(int gameid) {
		this.gameid = gameid;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getBuyin() {
		return buyin;
	}

	public void setBuyin(String buyin) {
		this.buyin = buyin;
	}

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}
	
	

}
