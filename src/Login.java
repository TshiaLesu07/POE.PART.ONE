public class Login {
    //Private variables to store login details
    private String userName;
    private String firstName;
    private String lastName;
    private String password;

    //Constructor to initialize login details
    public Login(String Username, String password, String firstName, String LastName) {
        this.userName = Username;
        this.firstName = firstName;
        this.lastName = LastName;
        this.password = password;
    }
    //Method to check if entered credentials match the stored ones
    public String loginUser(String enteredUsername, String EnteredPassword) {
        if (enteredUsername.equals(this.userName) && EnteredPassword.equals(this.password)) {
            //successful login message
            return "Welcome" + firstName + " " + lastName + " Login successful";
        } else {
            //Failed login message
            return " Login Invalid, try again";
        }
    }
}
