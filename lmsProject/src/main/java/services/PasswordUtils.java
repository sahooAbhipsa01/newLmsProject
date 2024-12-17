package services;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {
	
	public static String encryptPassword(String plainPassword) {
		return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
	}
	public static boolean verifyPassword(String plainPassword,String storedPassword) {
		return BCrypt.checkpw(plainPassword, storedPassword);
	}

}
