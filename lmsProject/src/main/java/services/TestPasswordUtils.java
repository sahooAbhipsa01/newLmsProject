package services;

public class TestPasswordUtils {

    public static void main(String[] args) {
        String plainPassword = "mySecurePassword";

        // Encrypt the password
        String encryptedPassword = PasswordUtils.encryptPassword(plainPassword);
        System.out.println("Encrypted Password: " + encryptedPassword);

        // Verify the password
        boolean isPasswordValid = PasswordUtils.verifyPassword(plainPassword, encryptedPassword);
        System.out.println("Password is valid: " + isPasswordValid);
    }
}
