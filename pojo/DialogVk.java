package com.dynamicaSocial.pojo;

public class DialogVk {
    private MessageVk message;
    private Integer unread;
    private Boolean unanswered;
    private Integer in_read;
    private Integer out_read;

    public DialogVk(){

    }

    public DialogVk(MessageVk message, Integer unread, Boolean unanswered, Integer in_read, Integer out_read) {
        this.message = message;
        this.unread = unread;
        this.unanswered = unanswered;
        this.in_read = in_read;
        this.out_read = out_read;
    }

    public Integer getUnread() {
        return unread;
    }

    public void setUnread(Integer unread) {
        this.unread = unread;
    }

    public Boolean isUnanswered() {
        return unanswered;
    }

    public void setUnanswered(Boolean unanswered) {
        this.unanswered = unanswered;
    }

    public MessageVk getMessage() {
        return message;
    }

    public void setMessage(MessageVk message) {
        this.message = message;
    }

    public Integer getIn_read() {
        return in_read;
    }

    public void setIn_read(Integer in_read) {
        this.in_read = in_read;
    }

    public Integer getOut_read() {
        return out_read;
    }

    public void setOut_read(Integer out_read) {
        this.out_read = out_read;
    }
}
