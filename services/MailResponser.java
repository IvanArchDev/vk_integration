package com.dynamicaSocial.services;

import com.dynamicaSocial.config.MailClientConfig;
import com.dynamicaSocial.database.DataBaseManager;
import com.dynamicaSocial.pojo.MessageVk;
import com.dynamicaSocial.pojo.DialogVk;
import com.dynamicaSocial.pojo.VkAllDialogs;
import com.dynamicaSocial.vkconnector.VkResponseParser;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MailResponser {
    private MailClientConfig tlsSend;
    private VkAllDialogs vkAllDialogs;
    private VkResponseParser vkResponseParser;
    private DataBaseManager dataBaseManager;
    private String email;
    private int dealer_Tag;
 

    public MailResponser(int tag, String email){
        this.dealer_Tag = tag;
        this.email = email;
        tlsSend = new MailClientConfig();
        vkAllDialogs = new VkAllDialogs();
        vkResponseParser = new VkResponseParser(tag);
        dataBaseManager = new DataBaseManager();
    }

    public void startWorkMail() throws InterruptedException, IOException, SQLException {
        try {
            vkAllDialogs = vkResponseParser.getResponseData();
            List<MessageVk> listForMail = getMessageList(vkAllDialogs.getItems());
            for (MessageVk arriveMsg : listForMail) {
                if (dataBaseIsEmpty() || newMessageArrived(arriveMsg)) {
                    insertDataToDb(arriveMsg);
                    createMailSend(arriveMsg);
                }
            }
        }catch (NullPointerException e){
            Thread.sleep(60000);
            startWorkMail();
        }
        Thread.sleep(60000);
        startWorkMail();
    }

    private void createMailSend(MessageVk messageVk) throws UnsupportedEncodingException {
        int id = messageVk.getUserId();
        String subject = "vk.com";
        String fromUser = new String(vkResponseParser.getUserData(id).getBytes(), "UTF-8");
        String mailTitle = new String(messageVk.getTitle().getBytes(), "UTF-8");
        String mailBody = new String (messageVk.getBody().getBytes(), "UTF-8");
        String mail = "From user " + fromUser + " " + mailTitle + " " + mailBody;
        tlsSend.send(subject, mail, tlsSend.getUSERNAME(), email);

        System.out.println("Send " + mailBody + " " + fromUser + " " + getCurrentTime());
    }

    private void insertDataToDb(MessageVk messageVk){
        try {
            dataBaseManager.insertDataIntoDB(messageVk.getUserId(), messageVk.getId(), messageVk.getDate(), dealer_Tag);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Can't insert data into database");
        }
    }

    private boolean dataBaseIsEmpty() throws SQLException {
        int countRow = dataBaseManager.getCountRecords();
        if (countRow > 0){
            return false;
        }else{
            return true;
        }
    }

    private boolean newMessageArrived(MessageVk message) throws SQLException {
        System.out.println(message.getBody().toString() + " " + getCurrentTime());
        List<MessageVk> oldMsg = dataBaseManager.getMessageDataFromDb(dealer_Tag, message.getUserId());
        if (oldMsg.size() == 0){
            System.out.println("Новый клиент в базе " + vkResponseParser.getUserData(message.getUserId()));
            return true;
        }else {
            return checkIdOldMsg(oldMsg, message);
        }
    }

    private boolean checkIdOldMsg(List<MessageVk> msgList, MessageVk newMsg){
        boolean isNew = true;
        for (MessageVk msg : msgList){
            if (msg.getId() == newMsg.getId()){
                isNew = false;
                return isNew;
            }
        }
        return isNew;
    }

    private List<MessageVk> getMessageList(List<DialogVk> list){
        List<MessageVk> msgList = new ArrayList<MessageVk>();
        for (int i =0; i < list.size(); i++){
           msgList.add(list.get(i).getMessage());
        }
        return msgList;
    }

    private String getCurrentTime(){
        long curTime = System.currentTimeMillis();
        String curStringDate = new SimpleDateFormat("dd.MM.yyyy").format(curTime);
        return curStringDate;
    }


}
