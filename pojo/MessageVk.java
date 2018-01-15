package com.dynamicaSocial.pojo;

public class MessageVk {
    private Integer id;
    private Long date;
    private Integer out;
    private Integer user_id;
    private Integer read_state;
    private String title;
    private String body;

    public MessageVk(){

    }

    public MessageVk(Integer id, Long date, Integer out, Integer user_id, Integer read_state, String title, String body) {
        this.id = id;
        this.date = date;
        this.out = out;
        this.user_id = user_id;
        this.read_state = read_state;
        this.title = title;
        this.body = body;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Integer getOut() {
        return out;
    }

    public void setOut(Integer out) {
        this.out = out;
    }

    public Integer getUserId() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getRead_state() {
        return read_state;
    }

    public void setRead_state(Integer read_state) {
        this.read_state = read_state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
