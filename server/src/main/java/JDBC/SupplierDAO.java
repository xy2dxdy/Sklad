package JDBC;

import enums.ProductQueries;
import enums.SupplierQueries;
import pojo.Product;
import pojo.Supplier;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

public class SupplierDAO
{
    private Connection connection;
    public boolean AddNewSupplier(Supplier supplier) {
        if (connection == null) {
            connection = JDBCConnector.GetConnection();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SupplierQueries.addSupplier.toString());
            preparedStatement.setString(1, supplier.getName());
            preparedStatement.setString(2, supplier.getCountry());
            preparedStatement.setString(3, supplier.getPhoneNumber());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Supplier> GetSuppliers(){
        if (connection == null) {
            connection = JDBCConnector.GetConnection();
        }
        ArrayList<Supplier> suppliers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SupplierQueries.getSuppliers.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                suppliers.add(SetSupplierFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        System.out.println(suppliers);
        return suppliers;
    }

    private Supplier SetSupplierFromResultSet(ResultSet resultSet){
        Supplier supplier = new Supplier();
        try {
            supplier.setName(resultSet.getString("name"));
            supplier.setCountry(resultSet.getString("country"));
            supplier.setPhoneNumber(resultSet.getString("phoneNumber"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplier;
    }
}
