package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

	private static final String VALID_ID_REGEX ="^[0-9]{1,7}$";
	private static final String VALID_TOURNAMENT_ADD_REGEX ="^[a-zA-Z]|\\d+[$@]$";
	private static final String VALID_TOURNAMENT_SEARCH_REGEX ="^[a-zA-Z]|\\d+[$@]$";
	private static final String VALID_RESULT_REGEX ="^[0-9.]{1,7}$";
	private static final String VALID_CREDENTIALS_REGEX ="^[A-Za-z0-9.-]{5,13}$";
	private static final String VALID_EMAIL_ADDRESS_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$";
	
	
	public static boolean isValidCredentials(String credentials){
		Pattern credentialsPattern = Pattern.compile(VALID_CREDENTIALS_REGEX);
		Matcher credentialsMatcher = credentialsPattern.matcher(credentials);
		return credentialsMatcher.find();
	}
	
	public static boolean isValidEmail(String email){
		Pattern emailPattern = Pattern.compile(VALID_EMAIL_ADDRESS_REGEX);
		Matcher emailMatcher = emailPattern.matcher(email);
		return emailMatcher.find();
	}
	public static boolean isValidID(String ID){
		Pattern IDPattern = Pattern.compile(VALID_ID_REGEX);
		Matcher IDMatcher = IDPattern.matcher(ID);
		return IDMatcher.find();
	}
	
	
	public static boolean isValidTournamentForAdd(String tournament){
		Pattern tournamentNamePattern = Pattern.compile(VALID_TOURNAMENT_ADD_REGEX);
		Matcher tournamentNameMatcher = tournamentNamePattern.matcher(tournament);
		return tournamentNameMatcher.find();
	}
	
	public static boolean isValidResult(String stats){
		Pattern statsPattern = Pattern.compile(VALID_RESULT_REGEX);
		Matcher statsMatcher = statsPattern.matcher(stats);
		return statsMatcher.find();
	}
	
	public static boolean isValidTournamentForSearch(String tournament){
		Pattern tournamentNamePattern = Pattern.compile(VALID_TOURNAMENT_SEARCH_REGEX);
		Matcher tournamentNameMatcher = tournamentNamePattern.matcher(tournament);
		return tournamentNameMatcher.find();
	}

}
