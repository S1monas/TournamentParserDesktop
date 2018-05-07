package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Tournament;

public class TournamentDao {
	
	public void addTournament(Tournament tournament)
	{
		String sql = "INSERT INTO `tournament`"
				+ "(`game`,`user`,`format`,`currency`,`buyin`,`result`)"
				+ " VALUES (?, ?, ?, ?, ?, ?)";
		try {
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "");
		PreparedStatement add = myConn.prepareStatement(sql);
		add.setString(1,tournament.getGame());
		add.setString(2,tournament.getUser());
		add.setString(3,tournament.getFormat());
		add.setString(4,tournament.getCurrency());
		add.setString(5,tournament.getBuyin());
		add.setDouble(6,tournament.getResult());
	
		add.execute();
		add.close();
		}catch(Exception exc){
			exc.printStackTrace();
		
		}
	}
	public void showTournaments(ObservableList<Tournament> tournamentList, User user) {
		String query = "";//Kad matytø
		if (user.getUserlevel() == view.Dashboard.ADMIN_LEVEL) {
			query = "SELECT * FROM tournament";	
		}else {
			String username = user.getUsername();
			query = "SELECT * FROM tournament WHERE user LIKE '" + username + "'";
		}
		try {
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "");
		PreparedStatement add = myConn.prepareStatement(query);
		ResultSet rs = add.executeQuery();
		while(rs.next()) {
			tournamentList.add(new Tournament(
					rs.getInt("gameid"),
					rs.getString("game"),
					rs.getString("user"),
					rs.getString("format"),
					rs.getString("currency"),
					rs.getString("buyin"),
					rs.getDouble("result")				
					));
		}
		
		}catch(Exception exc){
			exc.printStackTrace();
		
		}
	}
	
	
	public void updateTournament(Tournament tournament)
	{
		try {
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "");
		PreparedStatement update = myConn.prepareStatement("UPDATE tournament SET game = ?, user = ?, format = ?, currency = ?, buyin = ?, result= ? WHERE gameid = ?");
		update.setString(1,tournament.getGame());
		update.setString(2,tournament.getUser());
		update.setString(3,tournament.getFormat());
		update.setString(4,tournament.getCurrency());
		update.setString(5,tournament.getBuyin());
		update.setDouble(6,tournament.getResult());
		update.setInt(7, tournament.getGameid());

		update.executeUpdate();
		update.close();
		}catch(Exception exc){
			exc.printStackTrace();
		
		}
	}
	
	public void deleteTournament(int gameid)
	{
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "");
			PreparedStatement del = myConn.prepareStatement("delete FROM tournament WHERE gameid = ?");
			del.setInt(1, gameid);
			del.executeUpdate();
			}catch(Exception exc){
				exc.printStackTrace();
			
			}
	}
	

	
	public ObservableList<Tournament> searchTournamentByGame(String game, User user){
		String sql = "";
		if (game.isEmpty() && user.getUserlevel() == view.Dashboard.ADMIN_LEVEL) {
			sql = "Select * FROM tournament ";
		} else if (user.getUserlevel() == view.Dashboard.ADMIN_LEVEL){
			sql = "Select * FROM tournament WHERE game LIKE '%" + game + "%'";	
		}else {
			String username = user.getUsername();
			sql = "Select * FROM tournament WHERE game LIKE '%" + game + "%' AND user LIKE '" + username + "'";	
		}
		
		ObservableList<Tournament>tournamentList = FXCollections.observableArrayList();
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "");
			PreparedStatement pavad = myConn.prepareStatement(sql);
			
			ResultSet rs = pavad.executeQuery();	
				while(rs.next()){
					tournamentList.add(new Tournament(
							rs.getInt("gameid"),
							rs.getString("game"),
							rs.getString("user"),
							rs.getString("format"),
							rs.getString("currency"),
							rs.getString("buyin"),
							rs.getDouble("result")				
							));				   		         		         			
				}	
		}catch(Exception exc){
			exc.printStackTrace();	
		}
			return tournamentList;
	}
	

}
