package com.dynamicaSocial.vkconnector;

import com.dynamicaSocial.services.ConstantsDynamica;
import com.dynamicaSocial.pojo.VkAllDialogs;
import com.dynamicaSocial.pojo.UserVk;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import java.util.List;
import java.util.Map;

public class VkResponseParser {
    private VkAllDialogs dialogData;
    private RequestVkData requestVkData;
    private String dialogJson;
    private String userJson;
    private String name;
    private String surname;
    private Gson gson;
    int tag;

    public VkResponseParser(int tag) {
        requestVkData = new RequestVkData();
        dialogData = new VkAllDialogs();
        gson = new Gson();
        this.tag = tag;
    }

    public VkAllDialogs getResponseData() throws IOException {
        switch (tag){
            case 1:
                dialogJson = requestVkData.getDialogGroup(ConstantsDynamica.TOKEN_VK_FORD);
                break;
            case 2:
                dialogJson = requestVkData.getDialogGroup(ConstantsDynamica.TOKEN_VK_MITSUBISHI);
                break;
            case 3:
                dialogJson = requestVkData.getDialogGroup(ConstantsDynamica.TOKEN_VK_NISSAN);
                break;
            case 4:
                dialogJson = requestVkData.getDialogGroup(ConstantsDynamica.TOKEN_VK_DATSUN);
                break;
            case 5:
                dialogJson = requestVkData.getDialogGroup(ConstantsDynamica.TOKEN_VK_MITSUBISHI_KOMI);
                break;
        }

        Map<String, VkAllDialogs> map = gson.fromJson(dialogJson, new TypeToken<Map<String, VkAllDialogs>>(){}.getType());
        dialogData = map.get("response");
        return dialogData;
    }


    public String getUserData(int id){
        try{
            userJson = requestVkData.getDataUser(id, ConstantsDynamica.TOKEN_VK_FORD);
        }catch (IOException e){
            e.printStackTrace();
        }
        Map<String, List<UserVk>> map = gson.fromJson(userJson, new TypeToken<Map<String, List<UserVk>>>(){}.getType());
        List<UserVk> user = map.get("response");
        for (int i = 0; i < user.size(); i++){
            name = correction(user.get(i).getFirst_name());
            surname = correction(user.get(i).getLast_name());
        }

        String subject = name + " " + surname;

        return subject;
    }
    private String correction(String st){
        byte[] bytes = st.getBytes();
        if (bytes.length == 2 && bytes[0] == 48 && bytes[1] == 63){
            bytes[0] = (byte) 208;
            bytes[1] = (byte) 152;
        }
        return new String(bytes);
    }
}

