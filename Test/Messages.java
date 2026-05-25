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
    public String sentMessage (String option){
        if(option.equals("Send")){
            return "Message sent successfully";
        }else if (option.equals("Disregard")){
            return "Press 0 to delete the message";
        }else if (option.equals("Store")){
            return "message successfully stored";
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
}