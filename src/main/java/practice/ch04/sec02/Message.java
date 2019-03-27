package practice.ch04.sec02;

import java.util.ArrayList;

public final class Message {
    public Message(String sender, String text) {
        this.sender = sender;
        this.text = text;
    }

    private String sender;
    private ArrayList<String> recipients;
    private String text;
    public void addRecipient(String recipient) {

    }
    public Message clone() {
//        Message cloned = new Message(sender,text);
//        cloned.recipients = new ArrayList<>(recipients);
//        return cloned;
        try {
            Message cloned = (Message) super.clone();
            //不加注解也可以啊
            @SuppressWarnings("unchecked") ArrayList<String> clonedRecipients = (ArrayList<String>)recipients.clone();
            cloned.recipients = clonedRecipients;
            return cloned;
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }

    public static void main(String[] args) {

    }
}
