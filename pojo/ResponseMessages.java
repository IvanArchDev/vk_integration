package com.dynamicaSocial.pojo;

import java.util.List;

public class ResponseMessages {
    Object count;
    List<MessageVk> list;

    public ResponseMessages(){

    }

    public Object getCount() {
        return count;
    }

    public void setCount(Object count) {
        this.count = count;
    }

    public List<MessageVk> getList() {
        return list;
    }

    public void setList(List<MessageVk> list) {
        this.list = list;
    }
}
