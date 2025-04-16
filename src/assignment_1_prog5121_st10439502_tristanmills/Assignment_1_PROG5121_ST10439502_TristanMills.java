package assignment_1_prog5121_st10439502_tristanmills;

import java.util.*;
import java.util.regex.Pattern;

public class Assignment_1_PROG5121_ST10439502_TristanMills {

    private static String storedUsername;
    private static String storedPassword;
    private static String storedFirstName;
    private static String storedLastName;
    private static boolean isLoggedIn = false;
    private static int messageCounter = 0;

    private static List<String> storedMessages = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Select option: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private static void createAccount(Scanner scanner) {
        System.out.println("\n=== ACCOUNT CREATION ===");
        System.out.print("Enter first name: ");
        storedFirstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        storedLastName = scanner.nextLine();

        System.out.println("\nUSERNAME REQUIREMENTS:\n- Must contain an underscore (_)\n- Must be 5 characters or less");
        System.out.print("Enter username: ");
        storedUsername = scanner.nextLine();

        if (!validateUsername(storedUsername)) {
            System.out.println("Invalid username, please follow the requirements.");
            return;
        }

        System.out.println("\nPASSWORD REQUIREMENTS:\n- At least 8 characters\n- At least 1 capital letter\n- At least 1 number\n- At least 1 special character (!@#$%^&*)");
        System.out.print("Enter password: ");
        storedPassword = scanner.nextLine();

        if (!validatePassword(storedPassword)) {
            System.out.println("Invalid password, please follow the requirements.");
            return;
        }

        System.out.println("Account created successfully!");
    }

    private static void login(Scanner scanner) {
        if (storedUsername == null || storedPassword == null) {
            System.out.println("\nNo account exists. Please create an account first.");
            return;
        }

        System.out.println("\n=== LOGIN ===");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (username.equals(storedUsername) && password.equals(storedPassword)) {
            isLoggedIn = true;
            System.out.printf("\nWelcome %s %s!\n", storedFirstName, storedLastName);
            quickChatMenu(scanner);
        } else {
            System.out.println("Login failed. Incorrect username or password.");
        }
    }

    private static void quickChatMenu(Scanner scanner) {
        System.out.println("\nWelcome to QuickChat.");

        while (true) {
            System.out.println("\n1. Send Messages");
            System.out.println("2. Show Recently Sent Messages");
            System.out.println("3. Quit");
            System.out.print("Select option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    sendMessages(scanner);
                    break;
                case 2:
                    System.out.println("Coming Soon.");
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void sendMessages(Scanner scanner) {
        System.out.print("How many messages would you like to send? ");
        int numMessages = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numMessages; i++) {
            System.out.print("Enter recipient number (+CountryCode and max 10 digits): ");
            String recipient = scanner.nextLine();
            if (!recipient.matches("^\\+\\d{1,3}\\d{0,10}$")) {
                System.out.println("Invalid recipient number format.");
                i--;
                continue;
            }

            System.out.print("Enter message (max 250 characters): ");
            String messageText = scanner.nextLine();

            if (messageText.length() > 250) {
                System.out.println("Please enter a message of less than 250 characters.");
                i--;
                continue;
            }

            messageCounter++;
            long messageId = 1000000000L + new Random().nextInt(900000000);
            String[] words = messageText.split(" ");
            String firstWord = words.length > 0 ? words[0] : "";
            String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
            String messageHash = String.format("%02d:%d:%s%s", messageId % 100, messageCounter, firstWord.toUpperCase(), lastWord.toUpperCase());

            System.out.println("Choose an option:\n1. Send Message\n2. Disregard Message\n3. Store Message to send later");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Message sent.");
                    System.out.println("Message ID: " + messageId);
                    System.out.println("Message Hash: " + messageHash);
                    break;
                case 2:
                    System.out.println("Message disregarded.");
                    messageCounter--;
                    break;
                case 3:
                    storedMessages.add(messageText);
                    System.out.println("Message stored for later.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static boolean validateUsername(String username) {
        return username.length() <= 5 && username.contains("_");
    }

    private static boolean validatePassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        boolean hasCapital = false, hasNumber = false, hasSpecial = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasCapital = true;
            }
            if (Character.isDigit(c)) {
                hasNumber = true;
            }
            if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }
        return hasCapital && hasNumber && hasSpecial;
    }
}
