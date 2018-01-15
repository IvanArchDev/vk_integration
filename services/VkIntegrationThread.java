package com.dynamicaSocial.services;

import com.dynamicaSocial.config.MailClientConfig;

import java.io.IOException;
import java.sql.SQLException;

public class VkIntegrationThread extends Thread {
    int tag;
    String email;
    public VkIntegrationThread(int tag, String email){
      this.tag = tag;
      this.email = email;
    }

    @Override
    public void run() {
        MailResponser worker = new MailResponser(tag,email);
        try {
            worker.startWorkMail();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            MailClientConfig mail = new MailClientConfig();
            mail.send("Drop app", "VkIntegrationThread run" + e, mail.getUSERNAME(), "");
            e.printStackTrace();
        }
    }
}
