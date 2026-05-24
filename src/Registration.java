import java.util.Scanner;
public class Registration {
    // Variables to store user details
    public String Username;
    public String Password;
    public String PhoneNumber;
    public String firstName;
    public String LastName;

    //Check if username is valid ( if must contain an underscore and be 5 character or less )
    public boolean checkUsername(String Username) {

        return Username.contains("_") && Username.length() <= 5;
    }
    // check if phone number matches the South African format  which is +27 followed by 9 digits
    public boolean checkPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\+27[0-9]{9}");
    }
    // Validate your password which must have 8 characters, contain uppercase, numbers and special characters
    public static boolean validPassword(String Password){
    int minLength = 8;
        if (Password.length() < minLength){
            return false;
        }
        boolean hasUppercase = false;
        boolean hasSpecial = false;
        boolean hasNumber = false;

        // Loop through each character in the password
        for (char ch: Password.toCharArray()) {
            if (Character.isUpperCase(ch))
                hasUppercase = true;

            if (Character.isDigit(ch))
                hasNumber = true;

            if (!Character.isLetterOrDigit(ch))
                hasSpecial = true;
        }
        // return true if all the conditions are met
        return hasUppercase && hasSpecial && hasNumber;
    }

    //Method to register user details
    public void Register() {
        Scanner scanner = new Scanner(System.in);

        //Ask for first name
        System.out.println("enter first name");
        firstName = scanner.nextLine();

        //Ask for last name
        System.out.println("enter last name");
        LastName = scanner.nextLine();

        //Ask for username
        do {
            System.out.println("Please enter username: ");
            Username = scanner.nextLine();
            if (!checkUsername(Username)) {
                System.out.println("Invalid Username. Try again");
            } else {
                System.out.println("Username entered successfully");
            }
        } while (!checkUsername(Username));

        //Ask for password
        while (true){
            System.out.println("Enter your password");
            Password = scanner.nextLine();

            if (validPassword(Password)){
                System.out.println("Password is correct");
                break;
            }
            else System.out.println("Password is incorrect");
        }

        //Ask for phone number
        do {
            System.out.println(" Please enter phone number: ");
            PhoneNumber = scanner.nextLine();
            if (!checkPhoneNumber(PhoneNumber)){
                System.out.println(" Invalid phone");
            } else {
                System.out.println("cellphone number successfully added");
            }
        } while (!checkPhoneNumber(PhoneNumber));
        //if your details are correct, your registration will be successful
        System.out.println("Registration successful");
    }

}


