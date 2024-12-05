package util;

import model.User;

// Store the current logged in user.
public class Session {
	private static User session;
	
	public static User getUser() {
		return session;
	}
	
	public static void setUser(User user) {
		session = user;
	}
	
	public static void clearSession() {
		session = null;
	}
}
