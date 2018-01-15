package com.dynamicaSocial.pojo;

import java.util.List;

public class VkAllDialogs {
    private int count;
    private List<DialogVk> items;

    public VkAllDialogs(){

    }

    public VkAllDialogs(int count, List<DialogVk> listDialog) {
        this.count = count;
        this.items = listDialog;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DialogVk> getItems() {
        return items;
    }

    public void setItems(List<DialogVk> items) {
        this.items = items;
    }
}
