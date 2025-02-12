package JDBC;

import enums.UserQueries;
import enums.UserRole;
import pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {
    private Connection connection;
    public boolean InsertNewUser(User user) {
        if (connection == null) {
            connection = JDBCConnector.GetConnection();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.insertUser.toString());
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            //preparedStatement.setFloat(5,user.getWallet());
            preparedStatement.setInt(4,user.getUserRole().getInt());
            preparedStatement.setInt(5, 0);
            preparedStatement.setString(6, user.getSurname());
            preparedStatement.setString(7, user.getName());
            preparedStatement.setString(8, user.getPattername());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public User FindUserByLogin(User user) {
        if (connection == null) {
            connection = JDBCConnector.GetConnection();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.findUserByLogin.toString());
            preparedStatement.setString(1,user.getUserName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                boolean isBanned = resultSet.getInt("isBanned") != 0;
                String password = resultSet.getString("password");
                if(password.equals(user.getPassword()) && !isBanned){
                    user = SetUserFromResultSet(resultSet);
                }
                else {
                    user = null;
                }
            }
            else{
                user = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        System.out.println(user);
        return user;
    }
    public float GetCurrencyRate(int currencyID){
        if(connection == null){
            connection = JDBCConnector.GetConnection();
        }
        float currencyRate = 1.0f;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.getCurrencyRate.toString());
            preparedStatement.setInt(1,currencyID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                currencyRate = resultSet.getFloat("currensyrate");
            }
        } catch (SQLException e) {
            return currencyRate;
        }
        return currencyID;
    }
    public boolean BecomeCreator(int userID){
        if(connection == null){
            connection = JDBCConnector.GetConnection();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.becomeCreator.toString());
            preparedStatement.setInt(1, UserRole.worker.getInt());
            preparedStatement.setInt(2,userID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean BuyContent(int userID, int contentID){
        if (connection == null){
            connection = JDBCConnector.GetConnection();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.buyContent.toString());
            preparedStatement.setInt(1,userID);
            preparedStatement.setInt(2,contentID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public void ChangeMoney(int userID,float moneyValue){
        {
            if (connection == null){
                connection = JDBCConnector.GetConnection();
            }
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.changeMoney.toString());
                preparedStatement.setFloat(1,moneyValue);
                preparedStatement.setInt(2,userID);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public ArrayList<User> GetUsers(){
        if (connection == null) {
            connection = JDBCConnector.GetConnection();
        }
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.getUsers.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                users.add(SetUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        System.out.println(users);
        return users;
    }
    private User SetUserFromResultSet(ResultSet resultSet){
        User user = new User();
        try {
            boolean isBanned = resultSet.getInt("isBanned") != 0;
            user.setUserName(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            user.setUserRole(UserRole.setInt(resultSet.getInt("role")));
            user.setIsBanned(isBanned);
            user.setSurname(resultSet.getString("surname"));
            user.setName(resultSet.getString("name"));
            user.setPattername(resultSet.getString("pattername"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean UpdateUser(User user, String name){
        if (connection == null) {
            connection = JDBCConnector.GetConnection();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.updateUser.toString());
            preparedStatement.setString(1, user.getUserName());
            //preparedStatement.setString(2,user.getIsBanned()?1:0);
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setInt(4, user.getUserRole().getInt());
            preparedStatement.setString(5, user.getSurname());
            preparedStatement.setString(6, user.getName());
            preparedStatement.setString(7, user.getPattername());
            preparedStatement.setString(8, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean UpdateSelfUser(User user, String name){
        if (connection == null) {
            connection = JDBCConnector.GetConnection();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.updateUserByUser.toString());
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4, user.getSurname());
            preparedStatement.setString(5, user.getName());
            preparedStatement.setString(6, user.getPattername());
            preparedStatement.setString(7, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean BlockUser(User user){
        if (connection == null) {
            connection = JDBCConnector.GetConnection();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.blockUser.toString());
            preparedStatement.setInt(1, user.getIsBanned()?0:1);
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean DeleteUser(User user){
        if (connection == null) {
            connection = JDBCConnector.GetConnection();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.deleteUser.toString());
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public void CloseConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
