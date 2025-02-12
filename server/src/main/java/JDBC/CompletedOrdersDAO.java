package JDBC;

import enums.CompletedOrdersQueries;
import enums.ProductQueries;
import pojo.CompletedOrder;
import pojo.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class CompletedOrdersDAO
{
    private Connection connection;
    public ArrayList<CompletedOrder> GetCompletedOrders(){
        if (connection == null) {
            connection = JDBCConnector.GetConnection();
        }
        ArrayList<CompletedOrder> completedOrders = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CompletedOrdersQueries.getOrders.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                completedOrders.add(SetOrderFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        System.out.println(completedOrders);
        return completedOrders;
    }

    private CompletedOrder SetOrderFromResultSet(ResultSet resultSet){
        CompletedOrder order = new CompletedOrder();
        try {
            order.setAmount(resultSet.getInt("amount"));
            order.setCost(resultSet.getDouble("cost"));
            order.setNameProduct(resultSet.getString("products.name"));
            order.setNameCustomer(resultSet.getString("customers.name"));
            order.setOrderDate(resultSet.getString("orderDate"));
            order.setConfirmationDate(resultSet.getString("confirmationDate"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return order;
    }

    public boolean AddNewOrder(CompletedOrder completedOrder) {
        if (connection == null) {
            connection = JDBCConnector.GetConnection();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CompletedOrdersQueries.insertOrder.toString());
            PreparedStatement idProducts = connection.prepareStatement(ProductQueries.findIdProduct.toString());
            idProducts.setString(1, completedOrder.getNameProduct());
            ResultSet resultSet = idProducts.executeQuery();
            if(resultSet.next())
            {
                preparedStatement.setInt(1, resultSet.getInt("idProducts"));
            }
            preparedStatement.setInt(2, completedOrder.getAmount());
            preparedStatement.setDate(3, completedOrder.getOrderDate());
            preparedStatement.setDate(4, completedOrder.getConfirmationDate());
            preparedStatement.setDouble(5, completedOrder.getCost());
            PreparedStatement idCustomer = connection.prepareStatement(ProductQueries.findIdCustomer.toString());
            idCustomer.setString(1, completedOrder.getNameCustomer());
            ResultSet resultSet1 = idCustomer.executeQuery();
            if(resultSet1.next())
            {
                preparedStatement.setInt(6, resultSet1.getInt("idCustomers"));
            }
            else
            {
                PreparedStatement idSuppliers1 = connection.prepareStatement(ProductQueries.addCustomer.toString());
                idSuppliers1.setString(1, completedOrder.getNameCustomer());
                idSuppliers1.setString(2, completedOrder.getCountry());
                idSuppliers1.setString(3, completedOrder.getAdress());
                idSuppliers1.executeUpdate();
                PreparedStatement idCustomer1 = connection.prepareStatement(ProductQueries.findIdCustomer.toString());
                idCustomer1.setString(1, completedOrder.getNameCustomer());
                ResultSet resultSet2 = idCustomer1.executeQuery();
                if(resultSet2.next())
                {
                    preparedStatement.setInt(6, resultSet2.getInt("idCustomers"));
                }

            }
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
