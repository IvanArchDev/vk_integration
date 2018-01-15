package com.dynamicaSocial;


import com.dynamicaSocial.config.MailClientConfig;
import com.dynamicaSocial.services.ConstantsDynamica;
import com.dynamicaSocial.services.VkIntegrationThread;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.tanukisoftware.wrapper.WrapperListener;
import org.tanukisoftware.wrapper.WrapperManager;

import java.io.IOException;
import java.net.URISyntaxException;


public class Main implements WrapperListener{

    public static void main(String[] args) throws ApiException, ClientException, IOException, URISyntaxException, InterruptedException {
        try {
            System.setProperty("https.protocols", "TLSv1.1");
            WrapperManager.start(new Main(), args);
        }catch (Exception e){
            MailClientConfig mail = new MailClientConfig();
            mail.send("Drop app", "Service Vk integration  catch Exception" + e, mail.getUSERNAME(), "@mail.ru");
        }
    }

    @Override
    public Integer start(String[] strings) {
        ConstantsDynamica cons = new ConstantsDynamica();
        VkIntegrationThread fordVkGroup = new VkIntegrationThread(cons.FORD_TAG, cons.EMAIL_FORD);
        fordVkGroup.start();
        System.out.println("Ford done!");
        VkIntegrationThread MitsubishiVkGroup = new VkIntegrationThread(cons.MITSU_ARH_TAG, cons.EMAIL_MITSU);
        MitsubishiVkGroup.start();
        System.out.println("Mitsubishi Arh done!");
        VkIntegrationThread NissanVkGroup = new VkIntegrationThread(cons.NISSAN_TAG, cons.EMAIL_NISSAN);
        NissanVkGroup.start();
        System.out.println("Nissan done!");
        VkIntegrationThread DatsunGroup = new VkIntegrationThread(cons.DATSUN_TAG, cons.EMAIL_DATSUN);
        DatsunGroup.start();
        System.out.println("Datsun done!");
        VkIntegrationThread MitsuKomiGroupe = new VkIntegrationThread(cons.MITSU_TAG, cons.EMAIL_MITSU_K);
        MitsuKomiGroupe.start();
        System.out.println("Mitsubishi  done!");
        return null;
    }

    @Override
    public int stop(int i) {
        return 0;
    }

    @Override
    public void controlEvent(int i) {
        if ((i == WrapperManager.WRAPPER_CTRL_LOGOFF_EVENT)
                && (WrapperManager.isLaunchedAsService() ||
                WrapperManager.isIgnoreUserLogoffs())) {
            //Ignore
        } else {
            WrapperManager.stop(0);
        }
    }
}
