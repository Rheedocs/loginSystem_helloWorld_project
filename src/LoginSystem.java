import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginSystem {
    // Brugere og passwords
    static ArrayList<String> usernames = new ArrayList<>();
    static ArrayList<String> passwords = new ArrayList<>();
    // Dato og tid format
    static LocalDateTime now = LocalDateTime.now();
    static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    //HEJ

    // Log over loginforsøg
    static ArrayList<String> loginLog = new ArrayList<>();

    // Liste over låste brugere
    static ArrayList<String> lockedUsers = new ArrayList<>();

    // ANSI escape codes (bruges til farver og tekst-styling i terminalen)
    static final String YELLOW = "\u001B[33m";
    static final String RESET = "\u001B[0m";
    static final String UNDERLINE = "\u001B[4m";

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // Start med nogle standardbrugere
        usernames.add("Alice"); passwords.add("pass1");
        usernames.add("Bob"); passwords.add("pass2");
        usernames.add("Charlie");passwords.add("pass3");

        menu(); // start menuen
    }

    // === Menu === (Person A)
    public static void menu() {
        while (true) {
            System.out.println("\n--- VELKOMMEN ---\n" + now.format(fmt));
            System.out.println("\n--- LOGIN SYSTEM ---");
            System.out.println("1. Login");
            System.out.println("2. Register ny bruger");
            System.out.println("3. Vis login-log");
            System.out.println("4. Afslut");

            String choice = "";
            while (choice.isEmpty()) {
                System.out.print("Vælg (1-4): ");
                choice = input.nextLine();

                switch (choice) {
                    case "1": loginAttempt();
                        break;
                    case "2":
                        registerUser();
                        break;
                    case "3":
                        showLog();
                        break;
                    case "4":
                        System.out.println("\n---👋 Farvel!👋---\n");
                        return;
                    default:
                        System.out.println("Ugyldigt valg!\n");
                        choice = "";
                }
            }
        }
    }

    // === Loginforsøg === (Person B)
    public static void loginAttempt() {
        int maxTries = 3;

        String username = askUsername();

        // Tjek om kotoen allerede er låst (case-insensitive)
        for (String locked : lockedUsers) {
            if (locked.equalsIgnoreCase(username)) {
                System.out.println("\n🔒 Kontoen " + getProperName(username) + " er låst og kan ikke bruges.\n");
                return;
            }
        }
        for (int i = 0; i < maxTries; i++) {
            String password = askPassword();

            if (validateUser(username)) {
                if (validatePassword(username, password)) {
                    welcomeUser(username);  // Succes
                    logAttempt(username, true);
                    return;                 // Stop login
                } else {
                    if (i == maxTries - 1) {
                        System.out.println("❌ Forkert adgangskode.");
                        System.out.println("🔒 Kontoen " + getProperName(username) + " er nu låst\n");
                        lockedUsers.add(username);
                        logAttempt(username, false);
                        logAccountLocked(username);
                    } else {
                        System.out.println("\n❌ Forkert adgangskode.\nDu har " + YELLOW + UNDERLINE + (maxTries - i - 1) + RESET + " forsøg tilbage. \n");
                        logAttempt(username, false);
                    }
                }
            } else {
                System.out.println("❌ Brugernavn findes ikke. \n");
                logAttempt(username, false);
                return; // afslut hvis brugernavnet ikke findes
            }          // }// }
        }
    }
    // === Registrering af ny bruger ===
    public static void registerUser() {
        System.out.print("\nVælg brugernavn: ");
        String newUser = input.nextLine();

        for (String user : usernames) {
            if (user.equalsIgnoreCase(newUser)) {
                System.out.println("❌ Brugernavn findes allerede.\n");
                return;
            }
        }
        System.out.print("Vælg adgangskode: ");
        String newPass =  input.nextLine();

        usernames.add(newUser);
        passwords.add(newPass);
        System.out.println("Ny bruger oprettet: " + newUser + "\n");
    }

    // === Input-metoder ===
    public static String askUsername() {
        System.out.print("Indtast brugernavn: ");
        return input.nextLine();
    }

    public static String askPassword() {
        System.out.print("Indtast adgangskode: ");
        return input.nextLine();
    }

    // === Validering ===
    public static boolean validateUser(String username) {
        for (String user : usernames) {
            if (user.equalsIgnoreCase(username)) {
                return true;
            }
        } return  false;
    }

    public static boolean validatePassword(String username, String password) {
        for (int i = 0; i < usernames.size(); i++) {
            if (usernames.get(i).equalsIgnoreCase(username)) {
                return passwords.get(i).equals(password);
            }
        } return false;
    }

    // === Velkomstbesked ===
    public static void welcomeUser(String username) {
        System.out.println("\n✅ Velkommen " + getProperName(username) +
                "! Login kl. " + now.format(fmt) + "\n");
    }

    // === Logning ===
    public static void logAttempt(String username, boolean success) {
        String result = success ? "✅ Succes" : "❌ Failed";

        String properName = getProperName(username);

        loginLog.add(now.format(fmt) + " | " + properName + " | " + result);
    }

    public static void logAccountLocked(String username) {
        loginLog.add(now.format(fmt) + " | " + getProperName(username) + " | 🔒 LOCKED");
    }

    public static void showLog() {
        System.out.println("\n--- LOGIN LOG ---");
        if (loginLog.isEmpty()) {
            System.out.println("Ingen log endnu");
        } else {
            for (int i = 0; i < loginLog.size(); i++) {
                System.out.println(YELLOW + "#" + (i + 1) + RESET + " | " + loginLog.get(i));
            }
        }
    }

    // === Hjælpemetode ===
    private static String getProperName(String username) {
        for (String user : usernames) {
            if (user.equalsIgnoreCase(username)) {
                return user;
            }
        } return username;
    }
}
