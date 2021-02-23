package com.cst2335.plat0039;

public class Message {

    private String msg;
    boolean isSend;


    public Message(String messageText, boolean b) {
        msg = messageText;
        isSend = b;
    }

    public String getMessage(){
        return msg;
    }

    public void setMessage(String msg){
        this.msg = msg;
    }

    public boolean isSendType() {
        return isSend;
    }
}