package com.dynamicaSocial.database;

import com.dynamicaSocial.config.JDBCConnection;
import com.dynamicaSocial.pojo.MessageVk;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseManager {
    private JDBCConnection jdbcConnector;
    private Connection conn = null;
    private Statement statement = null;
    private final String INSERT =
            "INSERT INTO dynamica " +
                    "VALUES(?, ?, ?, ?)";
    private final String GET_ID =
            "SELECT " +
                    "msg_date, " +
                    "user_id, msg_id,  " +
                    "dealer_tag " +
            "FROM dynamica " +
            "WHERE " +
                    "dealer_tag = ? " +
                    "AND user_id = ?";

    private final String GET_COUNTING = "SELECT * FROM dynamica";

    public DataBaseManager() {
      jdbcConnector = new JDBCConnection();

    }

    public void insertDataIntoDB(int userId, int msgId, long date, int dealer_tag) throws SQLException {
        try{
            conn = jdbcConnector.getConnectionDB();
            PreparedStatement preparedStatement = conn.prepareStatement(INSERT);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, msgId);
            preparedStatement.setLong(3, date);
            preparedStatement.setInt(4, dealer_tag);
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if (statement != null) {
                statement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<MessageVk> getMessageDataFromDb(int tag, int userId) throws SQLException {
        List<MessageVk> listMsg = new ArrayList<MessageVk>();
        try {
            conn = jdbcConnector.getConnectionDB();
            PreparedStatement preparedStatement = conn.prepareStatement(GET_ID);
            preparedStatement.setInt(1, tag);
            preparedStatement.setInt(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                MessageVk msgVk = new MessageVk();
                msgVk.setUser_id(resultSet.getInt("user_id"));
                msgVk.setId(resultSet.getInt("msg_id"));
                msgVk.setDate(resultSet.getLong("msg_date"));
                listMsg.add(msgVk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listMsg;
    }

    public int getCountRecords() throws SQLException {
        conn = jdbcConnector.getConnectionDB();
        System.out.println("getCountRecords");
        int countRecords = 0;
        PreparedStatement preparedStatement = conn.prepareStatement(GET_COUNTING);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            countRecords++;
        }
        preparedStatement.close();
        conn.close();
        return countRecords;
    }

}
