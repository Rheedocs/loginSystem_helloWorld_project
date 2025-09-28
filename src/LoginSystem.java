import java.time.LocalDateTime;
import java.util.Scanner;

public class LoginSystem {
    // Arrays til brugernavne og adgangskoder
    static String[] usernames = {"Alice", "Bob", "Charlie"};
    static String[] passwords = {"pass1", "pass2", "pass3"};

    // Maks antal forsøg
    static final int MAX_ATTEMPTS = 3;

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        loginAttempt();
    }

    // === Loginforsøg ===
    public static void loginAttempt() {
        int attempts = 0;

        System.out.print("Indtast brugernavn: ");
        String username = input.nextLine().trim();

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Indtast adgangskode: ");
            String password = input.nextLine().trim();

            if (validateUser(username)) { // findes brugernavn?
                if (validatePassword(username, password)) { // matcher password?
                    welcomeUser(username);
                    return; // stop login
                } else {
                    attempts++;
                    if (attempts < MAX_ATTEMPTS) {
                        System.out.println("❌ Forkert adgangskode. Du har " +
                                (MAX_ATTEMPTS - attempts) + " forsøg tilbage.");
                    } else {
                        System.out.println("❌ Forkert adgangskode.");
                        System.out.println("🔒 Kontoen er nu låst efter " + MAX_ATTEMPTS + " forsøg.");
                    }
                }
            } else {
                System.out.println("❌ Brugernavn findes ikke.");
                return; // afslut hvis brugernavn er ukendt
            }
        }
    }

    // === Validering ===
    public static boolean validateUser(String username) {
        for (String user : usernames) {
            if (user.equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    public static boolean validatePassword(String username, String password) {
        for (int i = 0; i < usernames.length; i++) {
            if (usernames[i].equalsIgnoreCase(username)) {
                return passwords[i].equals(password);
            }
        }
        return false;
    }

    // === Velkomstbesked ===
    public static void welcomeUser(String username) {
        System.out.println("✅ Velkommen " + username + "! Login kl. " + LocalDateTime.now());
    }
}
