import java.util.ArrayList;
import org.json.JSONObject;
import java.io.FileWriter;

public class Messages {

    //
    private String messageID;
    private int numMessagesSent;
    private String recipient;
    private String message;
    private String messageHash;

    private ArrayList<String> messageList = new ArrayList<>();

    //we check the message ID
    public Boolean checkMessageID(String messageID) {
        return messageID.length() <= 10;
    }

    //We check to see the Recipient's cellphone number contains 10 numbers and starts with an international code .
    public String checkRecipientCell(String recipient){
        if (recipient.length()<10 && recipient.startsWith("+")) {
            return "Cellphone successfully captured";
        }else{
            return "Cellphone number is incorrectly formatted or does not contain international code";
        }
    }
    // we create a message hash
    public String createMessageHash (String messageID, String message , int numMessagesSent){
        String firstTwoID = messageID.substring(0,2);
        String [] words = message.split(" ");
        String firstWord = words[0];
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z]", " ");
        String Hash = firstTwoID + ":" + numMessagesSent + firstWord + lastWord;{
            return Hash.toUpperCase();
        }
    }
    //
     //edited for Part 3 (Added IgnoreCase to equals)
    public String sentMessage(String option){
        if(option.equalsIgnoreCase("Send")){
            return "Message sent successfully";
        }else if (option.equalsIgnoreCase("Disregard")){
            return "Message disregarded";
        }else if (option.equalsIgnoreCase("Store")){
            return "Message successfully stored";
        }else{
            return "invalid option";
        }
    }
    // We return the total number of messages sent
    public int returnTotalMessages() {
        return numMessagesSent;
    }
    //We are printing out the messages
    public String printMessages(){
        return messageList.toString();
    }
    // We are storing the messages
    public void storeMessage(String messageID, String recipient, String message){
        JSONObject json = new JSONObject();
        json.put("messageID", messageID);
        json.put("recipient", recipient);
        json.put("message", message);

        try (FileWriter fileWriter = new FileWriter("messeges.json", true)){
            fileWriter.write(json.toString() + "\n");
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        messageList.add(message);
        System.out.println("message successfully stored");
    }
    //Part 3 of the POE

    //Creating the arrays

    private ArrayList<String> sentMessages = new ArrayList<>();
    private ArrayList<String> disregardedMessages = new ArrayList<>();
    private ArrayList<String> storedMessages = new ArrayList<>();
    private ArrayList<String> messagehash = new ArrayList<>();
    private ArrayList<String> messageIDs = new ArrayList<>();
    private ArrayList<String> storedRecipients = new ArrayList<>();

    //adding sent messages method
    public void addsentMessages (String message){
        sentMessages.add(message);
        System.out.println("Message sent:" + message);
    }

    //adding disregarded messages method
    public void adddisregaredMessage (String disregarded){
        disregardedMessages.add(disregarded);
        System.out.println("Message disregarded:"+ disregarded);
    }

    //adding stored messages method
    public void addstoredMessage (String stored, String recipient){
        storedMessages.add(stored);
        storedRecipients.add(recipient);
        System.out.println("Message stored:");
    }
    //adding getting the longest message method
     public String getlongestMessage(){
        String longest = "";
        ArrayList<String> all = new ArrayList<>();
        all.addAll(sentMessages);
         all.addAll(storedMessages);
         all.addAll(disregardedMessages);
        for (String message : all){
            if (message.length() > longest.length()){
                longest = message;
            }
        }
        if (longest.isEmpty()){
            return "No stored messages yet.";
        }
        return longest;
     }

     //method to search message by message ID
    public String searchBymessageIDs (String id){
        for (int i = 0; i < messageIDs.size(); i++){
            if (messageIDs.get(i).equals(id)) {
                if (i < sentMessages.size()) {
                    return "Message found (Sent): " + sentMessages.get(i);
                }
                if (i < storedMessages.size()){
                    return "Message found (Stored): " + storedMessages.get(i);
                }
                if (i < disregardedMessages.size()){
                    return "Message found (Stored): " + disregardedMessages.get(i);
                }
            }
        }
        return " Message not found ";
    }

    // search by recipient
    public String searchByRecipient(String recipient){
        String result = "";
        for (int i = 0; i < storedRecipients.size(); i++){
            if (storedRecipients.get(i) != null && storedRecipients.get(i).equalsIgnoreCase(recipient)){
                result += storedMessages.get(i)+ "\n";
            }
        }
        if (result.isEmpty()){
            return "No messages found for recipient" + recipient;
        }
        return result;
    }

    // creating a method to delete message using a message hash
    public String deleteMessage(String hash) {
        for (int i = 0; i < messagehash.size(); i++) {
            if (messagehash.get(i).equalsIgnoreCase(hash)) {
                String deleted = storedMessages.get(i);
                storedMessages.remove(i);
                messagehash.remove(i);
                return "Message: " + deleted + " Successfully deleted";
            }
        }
        return "Message not found";
    }

    //displaying the report
    public void displayReport(){
        System.out.println("== Message Report ==");
        for (int i = 0; i < sentMessages.size(); i++){
            System.out.println("Message hash:" + messagehash.get(i));
            System.out.println("Message:" + sentMessages.get(i));
            System.out.println("___");
        }
    }

    //Adding three methods so we can access messageIDs and message hash in the main class
    //message ID
    public void addMessageID(String id){
        messageIDs.add(id);
    }

    //message hash
    public void addMessageHash(String hash){
        messagehash.add(hash);
    }

    //stored messages
    public ArrayList<String> getstoredMessages(){
        return storedMessages;
    }

    //adding a getter for the test unit
    public ArrayList<String> getSentMessages(){
        return sentMessages;
    }

}