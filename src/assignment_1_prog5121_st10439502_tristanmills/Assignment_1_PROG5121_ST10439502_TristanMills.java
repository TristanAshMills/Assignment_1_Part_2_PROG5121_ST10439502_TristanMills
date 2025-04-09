package assignment_1_prog5121_st10439502_tristanmills;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Tristan
 */
public class Assignment_1_PROG5121_ST10439502_TristanMills {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Username input \\ 
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        // Validate username \\
        if (!validateUsername(username)) {
            System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.");
            return;
        }
        System.out.println("Username successfully captured.");

        // Password input \\ 
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Validate password \\
        if (!validatePassword(password)) {
            System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            return;
        }
        System.out.println("Password successfully captured.");

        // Get phone number input \\
        System.out.print("Enter South African cell phone number: ");
        String phoneNumber = scanner.nextLine();

        // Validate phone number \\
        if (!validateSouthAfricanPhoneNumber(phoneNumber)) {
            System.out.println("Phone number is not correctly formatted, please ensure it's a valid South African cell phone number.");
            return;
        }
        System.out.println("Phone number successfully captured.");

        System.out.println("Account created successfully!");
    }

    // Username validation: contains underscore and is no more than 5 charactersv \\
    private static boolean validateUsername(String username) {
        return username.length() <= 5 && username.contains("_");
    }

    // Password validation: at least 8 chars, capital letter, number, special char \\
    private static boolean validatePassword(String password) {
        if (password.length() < 8) {
            return false;
        }

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

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

    // South African phone number validation
    private static boolean validateSouthAfricanPhoneNumber(String phoneNumber) {
        // South African cell numbers start with 0 followed by 7 or 8 or 9, then 8 digits
        return Pattern.matches("^0[26789]\\d{8}$", phoneNumber);
    }
}
