package com.cst2335.plat0039;

import android.widget.Button;

public class Message<MessageType> {

    private String msg;
    boolean isSend;
    private long id;
    private MessageType type;


    public Message(String messageText, boolean b, long i) {
        msg = messageText;
        isSend = b;
        id = i;
    }

    public Message(Button send, Button receive, long newID) {
    }

    public void update(boolean b, long i){
        isSend = b;
        id = i;
    }

    public String getMessage(){
        return msg;
    }

    public MessageType getType() {
        return type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isSendType() {
        return isSend;
    }

}