package com.mycompany.partonepoe;

import javax.swing.JOptionPane;

public class ChatApp {

    private static int count = 0;

    public static void main(String[] args) {

        JOptionPane.showMessageDialog(null, "Welcome to QuickChat");

        String howManyString = JOptionPane.showInputDialog("How many messages you want to send?");
        int howMany = Integer.parseInt(howManyString);

        boolean running = true;

        while (running) {
            String menu = "Pick a number:\n1 - Send Message\n2 - Show Senders and Recipients\n3 - Show Longest Message\n4 - Search by ID\n5 - Search by Recipient\n6 - Delete by Hash\n7 - Show Report\n8 - Quit";

            String choice = JOptionPane.showInputDialog(menu);
            
            if (choice == null || choice.trim().isEmpty()){
                running = false;
                break;
            }

            switch (choice) {
                case "1":
                    if (count >= howMany) {
                        JOptionPane.showMessageDialog(null, "You have reached max messages");
                    } else {
                        String rec = JOptionPane.showInputDialog("Enter recipient number with +");
                        String text = JOptionPane.showInputDialog("Enter your message (max 250)");

                        Message msg = new Message(count, rec, text);

                        String checkRec = msg.checkRecipientCell();
                        JOptionPane.showMessageDialog(null, checkRec);

                        if (checkRec.equals("Cell phone number successfully captured.")) {
                            String checkLen = msg.checkMessageLength();
                            JOptionPane.showMessageDialog(null, checkLen);

                            if (checkLen.equals("Message ready to send.")) {
                                String hash = msg.createMessageHash();
                                JOptionPane.showMessageDialog(null, "Hash: " + hash + "\nID: " + msg.messageID);

                                String optStr = JOptionPane.showInputDialog("1=Send, 2=Disregard, 3=Store");
                                int opt = Integer.parseInt(optStr);
                                String result = msg.SentMessage(opt);
                                JOptionPane.showMessageDialog(null, result);
                                count++;
                            }
                        }
                    }
                    break;

                case "2":
                    if (Message.printedMessages.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No messages yet");
                    } else {
                        String all = "";
                        for (int i = 0; i < Message.storedList.size(); i++) {
                            Message m = Message.storedList.get(i);
                            all = all + "To: " + m.recipient + " Msg: " + m.messageText + "\n";
                        }
                        if (all.equals("")) {
                            all = new Message(0, "", "").printMessages();
                        }
                        JOptionPane.showMessageDialog(null, all);
                    }
                    break;

                case "3":
                    if (Message.storedList.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No messages");
                    } else {
                        String longest = Message.storedList.get(0).messageText;
                        for (int i = 0; i < Message.storedList.size(); i++) {
                            if (Message.storedList.get(i).messageText.length() > longest.length()) {
                                longest = Message.storedList.get(i).messageText;
                            }
                        }
                        JOptionPane.showMessageDialog(null, "Longest: " + longest);
                    }
                    break;

                case "4":
                    String searchID = JOptionPane.showInputDialog("Enter Message ID to search");
                    boolean found = false;
                    for (int i = 0; i < Message.storedList.size(); i++) {
                        if (Message.storedList.get(i).messageID.equals(searchID)) {
                            JOptionPane.showMessageDialog(null, "Found: " + Message.storedList.get(i).messageText);
                            found = true;
                        }
                    }
                    if (found == false) {
                        JOptionPane.showMessageDialog(null, "ID not found");
                    }
                    break;

                case "5":
                    String searchRec = JOptionPane.showInputDialog("Enter recipient to search");
                    String res = "";
                    for (int i = 0; i < Message.storedList.size(); i++) {
                        if (Message.storedList.get(i).recipient.equals(searchRec)) {
                            res = res + Message.storedList.get(i).messageText + "\n";
                        }
                    }
                    if (res.equals("")) {
                        res = "No messages for that recipient";
                    }
                    JOptionPane.showMessageDialog(null, res);
                    break;

                case "6":
                    String searchHash = JOptionPane.showInputDialog("Enter Hash to delete");
                    boolean deleted = false;
                    for (int i = 0; i < Message.storedList.size(); i++) {
                        if (Message.storedList.get(i).messageHash.equals(searchHash)) {
                            Message.storedList.remove(i);
                            deleted = true;
                            JOptionPane.showMessageDialog(null, "Message deleted");
                            break;
                        }
                    }
                    if (deleted == false) {
                        JOptionPane.showMessageDialog(null, "Hash not found");
                    }
                    break;

                case "7":
                    if (Message.storedList.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No report - no messages");
                    } else {
                        String report = "";
                        for (int i = 0; i < Message.storedList.size(); i++) {
                            Message m = Message.storedList.get(i);
                            report = report + "ID: " + m.messageID + " Hash: " + m.messageHash + " To: " + m.recipient + "\n";
                        }
                        JOptionPane.showMessageDialog(null, report);
                    }
                    break;

                case "8":
                    int total = new Message(0, "", "").returnTotalMessages();
                    JOptionPane.showMessageDialog(null, "Total sent: " + total + "\nBye");
                    running = false;
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid option");
                    break;
            }
        }
    }

}