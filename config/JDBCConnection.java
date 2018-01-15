package com.dynamicaSocial.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    private MailClientConfig tlsSend = new MailClientConfig();
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:0000/social";
    private static final String DB_USER = "user";
    private static final String DB_PASSWORD ="password";
  
    private String  toEmail = "@mail.ru";
    private String mailBodyJdbc = "Can't found JDBC Driver!!!";
    private String mailBodyDB = "Can't connect to DB!!!";
    private String subject = "Error! Application don't work!!!";


    public Connection getConnectionDB(){
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Can'r found Driver!!!");
            tlsSend.send(subject, mailBodyJdbc, tlsSend.getUSERNAME(), toEmail);
        }
        try {
           connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connect to DB Well done!!!");
           return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Can't connect to DB!!!");
            tlsSend.send(subject, mailBodyDB, tlsSend.getUSERNAME(), toEmail);
        }
        return connection;
    }

}
