import org.junit.Test;
import static org.junit.Assert.*;

public class FullAppTest {

    // Registration: Username tests
    @Test
    public void testValidUsername() {
        Registration reg = new Registration();
        assertTrue(reg.checkUsername("abc_")); // valid: <=5 chars and contains "_"
    }

    @Test
    public void testInvalidUsernameTooLong() {
        Registration reg = new Registration();
        assertFalse(reg.checkUsername("abcdef_")); // too long
    }

    @Test
    public void testInvalidUsernameNoUnderscore() {
        Registration reg = new Registration();
        assertFalse(reg.checkUsername("abcd")); // missing "_"
    }

    // ✅ Registration: Password tests
    @Test
    public void testValidPassword() {
        Registration reg = new Registration();
        assertTrue(reg.validPassword("Passw0rd!")); // has uppercase, digit, special
    }

    @Test
    public void testInvalidPasswordTooShort() {
        Registration reg = new Registration();
        assertFalse(reg.validPassword("Pw1!")); // too short
    }

    @Test
    public void testInvalidPasswordNoUppercase() {
        Registration reg = new Registration();
        assertFalse(reg.validPassword("password1!")); // no uppercase
    }

    @Test
    public void testInvalidPasswordNoDigit() {
        Registration reg = new Registration();
        assertFalse(reg.validPassword("Password!")); // missing digit
    }

    @Test
    public void testInvalidPasswordNoSpecialChar() {
        Registration reg = new Registration();
        assertFalse(reg.validPassword("Password1")); // missing special char
    }

    //  Registration: Phone number tests
    @Test
    public void testValidPhoneNumberLocal() {
        Registration reg = new Registration();
        assertTrue(reg.checkPhoneNumber("0123456789")); // starts with 0 + 9 digits
    }

    @Test
    public void testValidPhoneNumberInternational() {
        Registration reg = new Registration();
        assertTrue(reg.checkPhoneNumber("+27123456789")); // starts with +27 + 9 digits
    }

    @Test
    public void testInvalidPhoneNumberTooShort() {
        Registration reg = new Registration();
        assertFalse(reg.checkPhoneNumber("12345")); // too short
    }

    @Test
    public void testInvalidPhoneNumberWrongPrefix() {
        Registration reg = new Registration();
        assertFalse(reg.checkPhoneNumber("9912345678")); // wrong prefix
    }

    // Login: Successful login
    @Test
    public void testSuccessfulLogin() {
        Login login = new Login("user123", "pass123", "John", "Doe");
        String result = login.loginUser("user123", "pass123");
        assertTrue(result.startsWith("Welcome John Doe"));
    }

    // Login: Failed login
    @Test
    public void testFailedLogin() {
        Login login = new Login("user123", "pass123", "John", "Doe");
        String result = login.loginUser("wrongUser", "wrongPass");
        assertEquals("Username or Password incorrect. Please try again.", result);
    }
    //part 2 test units for message tests
    @Test
    //testing the length success of the messages
    public void testMessageLengthSuccess() {
        Messages msg = new Messages();
        String message = "Hi Mike, can you join us for dinner tonight";
        assertTrue(message.length() < 250);
    }
    @Test
    //testing the length failure of the messages
    public void testMessageLengthFaliure() {
        Messages msg = new Messages();
        String message = "a".repeat(251);
        assertFalse(message.length() < 250);
}
    @Test
    //testing if the recipient's number is valid
    public void testRecipientValidNumner(){
        Messages msg = new Messages();
        assertEquals("Cellphone number successfully captured", msg.checkRecipientCell("+27718693002"));
}
    @Test
    //testing if the recipient's number is incorrectly formatted or does not contain an international code
    public void testRecipientInvalidNumner() {
        Messages msg = new Messages();
        assertEquals("Cellphone number is incorrectly formatted or does not contain international code", msg.checkRecipientCell("+27718693002"));
    }
    @Test
    //testing the message hash
    public void testMessageHash(){
        Messages msg = new Messages();
        assertEquals("00:1:HITONIGHT", msg.createMessageHash("0012345678","Hi Mike can you join us for dinner tonight?", 1));
    }
    @Test
    //testing if the messages can send successfully
    public void testSentMessagesSend(){
        Messages msg = new Messages();
        assertEquals("Message sent successfully", msg.sentMessage("Send"));
    }
    @Test
    //testing if we can disregard the messages
    public void testSentMessagesDisregard(){
        Messages msg = new Messages();
        assertEquals("Press 0 to delete the message", msg.sentMessage("Disregard"));
    }
    @Test
    //testing to see if the messages can be stored
    public void testSentMessagesStore() {
        Messages msg = new Messages();
        assertEquals("Message stored successfully", msg.sentMessage("Store"));
    }
    }