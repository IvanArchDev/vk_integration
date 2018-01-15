package com.dynamicaSocial.vkconnector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class RequestVkData {
    private String url = "";
    private String result = "";

    public String getMessage(String token) throws IOException {
        String url = "https://api.vk.com/method/"+
                "messages.get"+
                "?out=0"+
                "&v=5.67"+
                "&access_token="+ token
                ;
        return getJSonStringData(url);
    }

    public String getDialogGroup(String token) throws IOException {
        url = "https://api.vk.com/method/"+
                "messages.getDialogs"+
                "?unread=1"+
                "?count=20"+
                "&v=5.67"+
                "&access_token="+ token;
        return getJSonStringData(url);
    }

    public String getDataUser(int id, String token) throws IOException {
        url ="https://api.vk.com/method/"+
                "users.get"+
                "?user_id="+id+
                "&v=5.67"+
                "&access_token="+ token;

        return getJSonStringData(url);
    }


    public String getJSonStringData(String url) throws IOException {
        URL urlData = new URL(url);
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlData.openStream()));
        result = reader.readLine();
        reader.close();
        return result;
    }
}
