package com.dynamicaSocial.pojo;

public class UserVk {
    private Integer id;
    private String first_name;
    private String last_name;
    private Integer hidden;

    public UserVk(){

    }

    public UserVk(Integer id, String first_name, String last_name, Integer hidden) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.hidden = hidden;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHidden() {
        return hidden;
    }

    public void setHidden(Integer hidden) {
        this.hidden = hidden;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
