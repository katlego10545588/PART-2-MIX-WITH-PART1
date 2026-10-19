package com.mycompany.partonepoe;

import java.util.ArrayList;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

// My Message class - POE Part 2
public class Message {
    
    public String messageID;
    public String recipient;
    public String messageText;
    public String messageHash;
    public int messageNum;
    
    // static lists
    public static int totalMessagesSent = 0;
    public static ArrayList<String> printedMessages = new ArrayList<>();
    public static ArrayList<Message> storedList = new ArrayList<>();

    public Message(int num, String rec, String text) {
        messageNum = num;
        recipient = rec;
        messageText = text;
        messageID = makeID();
        messageHash = createMessageHash();
    }

    public final String makeID() {
        Random rand = new Random();
        String id = "";
        for(int i = 0; i < 10; i++) {
            id = id + rand.nextInt(10);
        }
        messageID = id;
        return id;
    }

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    public String checkRecipientCell() {
        if(recipient.startsWith("+") && recipient.length() <= 13) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an International code. Please correct the number and try again.";
        }
    }

    public final String createMessageHash() {
        String[] parts = messageText.split(" ");
        String first = parts[0].replace(",", "").replace("?", "").toUpperCase();
        String last = parts[parts.length - 1].replace(",", "").replace("?", "").toUpperCase();
        String hash = "00:" + messageNum + ":" + first + last;
        messageHash = hash;
        return hash;
    }

    public String checkMessageLength() {
        if(messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int extra = messageText.length() - 250;
            return "Message exceeds 250 characters by " + extra + ", please reduce the size.";
        }
    }


    public String SentMessage(int option) {
        String result = "";
        switch (option) {
            case 1:
                totalMessagesSent = totalMessagesSent + 1;
                printedMessages.add(messageHash + " To: " + recipient + " Msg: " + messageText);
                storedList.add(this);
                result = "Message successfully sent.";
                break;
            case 2:
                result = "Press 0 to delete the message.";
                break;
            case 3:
                storeMessage();
                result = "Message successfully stored.";
                break;
            default:
                break;
        }
        return result;
    }

    public String printMessages() {
        if(printedMessages.isEmpty()){
            return "No messages.";
        }
        String all = "";
        for(int i = 0; i < printedMessages.size(); i++){
            all = all + printedMessages.get(i) + "\n";
        }
        return all;
    }

    public int returnTotalMessages() {
        return totalMessagesSent;
    }
    
    public int returnTotalMessagess() {
        return totalMessagesSent;
    }

    public void storeMessage() {
        storedList.add(this);
        try {
            FileWriter w = new FileWriter("storedMessages.json", true);
            w.write("{\n\"ID\":\"" + messageID + "\",\n\"Hash\":\"" + messageHash + "\",\n\"To\":\"" + recipient + "\",\n\"Msg\":\"" + messageText + "\"\n},\n");
            w.close();
        } catch(IOException e) {
            System.out.println("Error storing");
        }
    }
}