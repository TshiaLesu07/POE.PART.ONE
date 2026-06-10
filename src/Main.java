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

            // part 3 (Stored messages added
            System.out.println("4)Stored messages");

            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println(" how many messages do you want to send?");
                int numMessages = scanner.nextInt();
                scanner.nextLine(); //added new line to consume leftover newline

                //so a loop can run that amount of times
                for (int i = 0; i < numMessages; i++) {
                    //we are creating a random message ID
                    String messageID = String.valueOf((long) (Math.random() * 900000000L + 1000000000L));

                    System.out.println("enter recipient number: ");
                    String recipient = scanner.nextLine();

                    System.out.println("Enter your message: ");
                    String message = scanner.nextLine();

                    //we create a message hash
                    String hash = msg.createMessageHash(messageID, message, i + 1);

                    //we are asking what should we do with the message
                    System.out.println("what do you want to do?");
                    System.out.println("Send , Disregard , Store ");

                    //Part 3 additions
                    String option = scanner.nextLine().trim();

                    System.out.println(msg.sentMessage(option));

                    //POE part 3 additions
                    if (option.equalsIgnoreCase("Send")) {
                        msg.addsentMessages(message);
                    } else if (option.equalsIgnoreCase("Disregard")) {
                        msg.adddisregaredMessage(message);
                    } else if (option.equalsIgnoreCase("Store")) {
                        msg.addstoredMessage(message , recipient);
                        msg.storeMessage(messageID, recipient, message);
                    }

                    //messageID and hash
                    msg.addMessageID(messageID);
                    msg.addMessageHash(hash);

                    //we show the details of the message
                    System.out.println("Message ID: " + messageID);
                    System.out.println("Message hash: " + hash);
                    System.out.println("Recipient: " + recipient);
                    System.out.println("Message: " + message);
                }
            } else if (choice == 2) {
                System.out.println("coming soon");
                System.out.println(msg.printMessages()); //added recently .
                //POE part 3 :else if added
            } else if (choice == 4) {
                System.out.println("a) Display all stored messages");
                System.out.println("b) Display longest message");
                System.out.println("c) Search by message ID");
                System.out.println("d) Search by recipient");
                System.out.println("e) Delete message");
                System.out.println("f) Display report");

                //adding a submenu
                String subChoice = scanner.next();

                if (subChoice.equals("a")) {
                    for (String m : msg.getstoredMessages()) {
                        System.out.println(m);
                    }
                } else if (subChoice.equals("b")) {
                    System.out.println(msg.getlongestMessage());
                } else if (subChoice.equals("c")) {
                    System.out.println("Enter message ID: ");
                    String id = scanner.next();
                    System.out.println(msg.searchBymessageIDs(id));
                } else if (subChoice.equals("d")) {
                    System.out.println("Enter recipient: ");
                    String recipient = scanner.next();
                    System.out.println(msg.searchByRecipient(recipient));
                } else if (subChoice.equals("e")) {
                    System.out.println("Enter message hash: ");
                    String hash = scanner.next();
                    System.out.println(msg.deleteMessage(hash));
                } else if (subChoice.equals("f")) {
                    msg.displayReport();
                }
            }
        }
    }
}