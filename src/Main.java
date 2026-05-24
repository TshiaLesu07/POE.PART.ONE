// Main class to run the registration and log in system
void main() {
    // Creating a registration object and calling the registration method
    Registration registration = new Registration();
    registration.Register();

    // Scann for user input
    Scanner pink = new Scanner(System.in);

    //Pass the registered details into the login class
    Login login = new Login(registration.Username, registration.Password,
            registration.firstName, registration.LastName);

    System.out.println("log in");

    String loginResult = "";

    // Allow the user to try 3 attempts to log in
    for (int i = 0; i < 3; i++) {
        System.out.println("Enter username");
        String loginUser = pink.nextLine();

        System.out.println("Enter password");
        String loginPassword = pink.nextLine();

        //Call the login method and display the results
       loginResult = login.loginUser(loginUser, loginPassword);
        System.out.println(loginResult);

        // If login is successful, stop the loop
        if (loginResult.startsWith("Welcome")) {
            break;
        }
        // if there are 3 failed attempts , lock the account.
        if (i == 2) {
            System.out.println("too many failed attempts, account has been locked");
        }
    }
    //We check to see if we logged in successfully before we can show the chats
    if (loginResult.startsWith("Welcome")) {
        System.out.println("Welcome to Quick chat");

        Messages msg = new Messages();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        //we do a while loop for the menu
        while (choice != 3) {
            System.out.println("\n1) Send messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println(" how many messages do you want to send?");
                int numMessages = scanner.nextInt();

                //so a loop can run that amount of times
                for (int i = 0; i < numMessages; i++) {
                    //we are creating a random message ID
                    String messageID = String.valueOf((long)(Math.random()*900000000L + 1000000000L));

                    System.out.println("enter recipient number: ");
                    scanner.nextLine();
                    String recipient = scanner.nextLine();

                    System.out.println("Enter your message: ");
                    String message = scanner.nextLine();

                    //we create a message hash
                    String hash = msg.createMessageHash(messageID, message, i + 1);

                    //we are asking what should we do with the message
                    System.out.println("what do you want to do?");
                    System.out.println("Send / Disregard / Store ?");
                    String option = scanner.nextLine();

                    System.out.println(msg.sentMessage(option));

                    //we show the details of the message
                    System.out.println("Message ID" + messageID);
                    System.out.println("Message hash" + hash );
                    System.out.println("Recipient" + recipient);
                    System.out.println("Message" + message);
                }
            } else if (choice == 2) {
                System.out.println("coming soon");
            }
        }
    }
}