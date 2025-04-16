package assignment_1_prog5121_st10439502_tristanmills;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Tristan
 */
public class Assignment_1_PROG5121_ST10439502_TristanMills {

    private static String storedUsername;
    private static String storedPassword;
    private static String storedFirstName;
    private static String storedLastName;
    private static String storedPhoneNumber;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Select option: ");
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
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }
    
    private static void createAccount(Scanner scanner) {
        System.out.println("\n=== ACCOUNT CREATION ===");
        
        // Get user details \\
        System.out.print("Enter first name: ");
        storedFirstName = scanner.nextLine();
        
        System.out.print("Enter last name: ");
        storedLastName = scanner.nextLine();
        
        // Username with instructions \\
        System.out.println("\nUSERNAME REQUIREMENTS:");
        System.out.println("- Must contain an underscore (_)");
        System.out.println("- Must be 5 characters or less");
        System.out.print("Enter username: ");
        storedUsername = scanner.nextLine();
        
        if (!validateUsername(storedUsername)) {
            System.out.println("\nERROR: Username must:");
            System.out.println("- Contain an underscore (_)");
            System.out.println("- Be 5 characters or less");
            System.out.println("Example: 'john_' or 'a_b'");
            return;
        }
        System.out.println("✓ Username valid");
        
        // Password with instructions \\
        System.out.println("\nPASSWORD REQUIREMENTS:");
        System.out.println("- At least 8 characters");
        System.out.println("- At least 1 capital letter");
        System.out.println("- At least 1 number");
        System.out.println("- At least 1 special character (!@#$%^&*)");
        System.out.print("Enter password: ");
        storedPassword = scanner.nextLine();
        
        if (!validatePassword(storedPassword)) {
            System.out.println("\nERROR: Password must contain:");
            System.out.println("- At least 8 characters");
            System.out.println("- At least 1 capital letter");
            System.out.println("- At least 1 number");
            System.out.println("- At least 1 special character (!@#$%^&*)");
            System.out.println("Example: 'Password123!'");
            return;
        }
        System.out.println("✓ Password valid");
        
        // Phone number with instructions \\
        System.out.println("\nPHONE NUMBER REQUIREMENTS:");
        System.out.println("- South African cell number format");
        System.out.println("- Starts with 0, followed by 7, 8, or 9");
        System.out.println("- 10 digits total (e.g., 0712345678)");
        System.out.print("Enter phone number: ");
        storedPhoneNumber = scanner.nextLine();
        
        if (!validateSouthAfricanPhoneNumber(storedPhoneNumber)) {
            System.out.println("\nERROR: Phone number must be:");
            System.out.println("- A valid South African cell number");
            System.out.println("- Format: 07xxxxxxxx or 08xxxxxxxx or 09xxxxxxxx");
            System.out.println("- Example: '0821234567'");
            return;
        }
        System.out.println("✓ Phone number valid");
        
        System.out.println("\nAccount created successfully!");
    }
    
    private static void login(Scanner scanner) {
        if (storedUsername == null || storedPassword == null) {
            System.out.println("\nNo accounts exist. Please create an account first.");
            return;
        }
        
        System.out.println("\n=== LOGIN ===");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        if (username.equals(storedUsername) && password.equals(storedPassword)) {
            System.out.printf("\nWelcome %s %s! It's great to see you again.\n", 
                           storedFirstName, storedLastName);
        } else {
            System.out.println("\nLogin failed. Incorrect username or password.");
            System.out.println("Please try again or contact support if you forgot your credentials.");
        }
    }
    
    private static boolean validateUsername(String username) {
        return username.length() <= 5 && username.contains("_");
    }
    
    private static boolean validatePassword(String password) {
        if (password.length() < 8) return false;
        
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasCapital = true;
            if (Character.isDigit(c)) hasNumber = true;
            if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        
        return hasCapital && hasNumber && hasSpecial;
    }
    
    private static boolean validateSouthAfricanPhoneNumber(String phoneNumber) {
        return Pattern.matches("^0[26789]\\d{8}$", phoneNumber);
    }
}