package JDBC;

import enums.OrderQueries;
import enums.ProductQueries;
import pojo.Order;
import pojo.Product;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

public class ProductDAO
{
    private Connection connection;
    public boolean InsertNewProduct(Product product) {
        if (connection == null) {
            connection = JDBCConnector.GetConnection();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ProductQueries.insertProduct.toString());
            PreparedStatement idSuppliers = connection.prepareStatement(ProductQueries.findIdSupplier.toString());
            idSuppliers.setString(1,product.getSupplier());
            ResultSet resultSet = idSuppliers.executeQuery();
            if(resultSet.next())
            {
                preparedStatement.setInt(1, resultSet.getInt("idSuppliers"));
            }
            else
            {
                PreparedStatement idSuppliers1 = connection.prepareStatement(ProductQueries.addSupplier.toString());
                idSuppliers1.setString(1,product.getSupplier());
                idSuppliers1.executeUpdate();
            }
            PreparedStatement idGroupProduct = connection.prepareStatement(ProductQueries.findIdProductsGroup.toString());
            idGroupProduct.setString(1,product.getProductsGroup());
            ResultSet resultSet1 = idGroupProduct.executeQuery();
            if(resultSet1.next())
            {
                preparedStatement.setInt(2, resultSet1.getInt("idProductsGroups"));
            }
            else
            {
                PreparedStatement idGroupProduct1 = connection.prepareStatement(ProductQueries.addProductGroup.toString());
                idGroupProduct1.setString(1,product.getProductsGroup());
                idGroupProduct1.executeUpdate();
            }
            preparedStatement.setString(3, product.getName());
            preparedStatement.setDouble(4,product.getPrice());
            preparedStatement.setInt(5, product.getAmount());
            java.util.Date utilDate = product.getReceiptDate();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            preparedStatement.setDate(6, sqlDate);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean AddNewProductGroup(String group) {
        if (connection == null) {
            connection = JDBCConnector.GetConnection();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ProductQueries.addProductGroup.toString());
            preparedStatement.setString(1, group);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Product FindProductByName(Product product) {
        if (connection == null) {
            connection = JDBCConnector.GetConnection();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ProductQueries.findProduct.toString());
            preparedStatement.setString(1,product.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                product = SetProductFromResultSet(resultSet);
            }
            else{
                product = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        System.out.println(product);
        return product;
    }
    public ArrayList<Product> GetProducts(String groupName){
        if (connection == null) {
            connection = JDBCConnector.GetConnection();
        }
        ArrayList<Product> products = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ProductQueries.getProducts.toString());
            preparedStatement.setString(1, groupName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                products.add(SetProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        System.out.println(products);
        return products;
    }

    private Product SetProductFromResultSet(ResultSet resultSet){
        Product product = new Product();
        try {
            //product.setIdProduct(resultSet.getInt("idProducts"));
            product.setSupplier(resultSet.getString("suppliers.name"));
            product.setProductsGroup(resultSet.getString("productsgroups.name"));
            product.setName(resultSet.getString("products.name"));
            product.setPrice(resultSet.getDouble("price"));
            product.setAmount(resultSet.getInt("amount"));
            product.setReceiptDate(resultSet.getDate("receiptDate").toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    public ArrayList<Order> GetOrders(){
        if (connection == null) {
            connection = JDBCConnector.GetConnection();
        }
        ArrayList<Order> orders = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ProductQueries.getOrders.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                orders.add(SetOrderFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        System.out.println(orders);
        return orders;
    }

    private Order SetOrderFromResultSet(ResultSet resultSet){
        Order order = new Order();
        try {
            order.setAmount(resultSet.getInt("amount"));
            order.setCost(resultSet.getDouble("cost"));
            order.setNameProduct(resultSet.getString("products.name"));
            order.setNameCustomer(resultSet.getString("customers.name"));
            order.setDate(resultSet.getString("date"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return order;
    }

    public boolean AddNewOrder(Order order) {
        if (connection == null) {
            connection = JDBCConnector.GetConnection();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ProductQueries.addOrder.toString());
            PreparedStatement idProducts = connection.prepareStatement(ProductQueries.findIdProduct.toString());
            idProducts.setString(1, order.getNameProduct());
            ResultSet resultSet = idProducts.executeQuery();
            if(resultSet.next())
            {
                preparedStatement.setInt(1, resultSet.getInt("idProducts"));
            }
            preparedStatement.setInt(2, order.getAmount());
            preparedStatement.setDate(3, order.getDate());
            preparedStatement.setDouble(4, order.getCost());
            PreparedStatement idCustomer = connection.prepareStatement(ProductQueries.findIdCustomer.toString());
            idCustomer.setString(1, order.getNameCustomer());
            ResultSet resultSet1 = idCustomer.executeQuery();
            if(resultSet1.next())
            {
                preparedStatement.setInt(5, resultSet1.getInt("idCustomers"));
            }
            else
            {
                PreparedStatement idSuppliers1 = connection.prepareStatement(ProductQueries.addCustomer.toString());
                idSuppliers1.setString(1, order.getNameCustomer());
                idSuppliers1.setString(2, order.getCountry());
                idSuppliers1.setString(3, order.getAdress());
                idSuppliers1.executeUpdate();
                PreparedStatement idCustomer1 = connection.prepareStatement(ProductQueries.findIdCustomer.toString());
                idCustomer1.setString(1, order.getNameCustomer());
                ResultSet resultSet2 = idCustomer1.executeQuery();
                if(resultSet2.next())
                {
                    preparedStatement.setInt(5, resultSet2.getInt("idCustomers"));
                }

            }
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean DeleteOrder(Order order) {
        if (connection == null) {
            connection = JDBCConnector.GetConnection();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(OrderQueries.deleteOrder.toString());
            PreparedStatement idProducts = connection.prepareStatement(ProductQueries.findIdProduct.toString());
            idProducts.setString(1, order.getNameProduct());
            ResultSet resultSet = idProducts.executeQuery();
            if(resultSet.next())
            {
                preparedStatement.setInt(1, resultSet.getInt("idProducts"));
            }
            preparedStatement.setInt(2, order.getAmount());
            preparedStatement.setDate(3, order.getDate());
            preparedStatement.setDouble(4, order.getCost());
            PreparedStatement idCustomer = connection.prepareStatement(ProductQueries.findIdCustomer.toString());
            idCustomer.setString(1, order.getNameCustomer());
            ResultSet resultSet1 = idCustomer.executeQuery();
            if(resultSet1.next())
            {
                preparedStatement.setInt(5, resultSet1.getInt("idCustomers"));
            }
            else
            {
                PreparedStatement idSuppliers1 = connection.prepareStatement(ProductQueries.addCustomer.toString());
                idSuppliers1.setString(1, order.getNameCustomer());
                idSuppliers1.setString(2, order.getCountry());
                idSuppliers1.setString(3, order.getAdress());
                idSuppliers1.executeUpdate();
                PreparedStatement idCustomer1 = connection.prepareStatement(ProductQueries.findIdCustomer.toString());
                idCustomer1.setString(1, order.getNameCustomer());
                ResultSet resultSet2 = idCustomer1.executeQuery();
                if(resultSet2.next())
                {
                    preparedStatement.setInt(5, resultSet2.getInt("idCustomers"));
                }

            }
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /* public void RemoveProduct(Product product){
        if(connection == null){
            connection = JDBCConnector.GetConnection();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ProductQueries.remove.toString());
            preparedStatement.setInt(1, product.getIdProduct());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException();
        }
    }*/
    public boolean UpdateProduct(Product product, String name){
        if (connection == null) {
            connection = JDBCConnector.GetConnection();
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ProductQueries.updateProduct.toString());
            PreparedStatement idProduct = connection.prepareStatement(ProductQueries.findIdProduct.toString());
            idProduct.setString(1, name);
            ResultSet result  = idProduct.executeQuery();
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getAmount());
            preparedStatement.setDate(4, new java.sql.Date(product.getReceiptDate().getTime()));
            if(result.next()) {
                preparedStatement.setInt(5, result.getInt("idProducts"));
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public ArrayList<String> GetNameOfGroups()
    {
        if (connection == null) {
            connection = JDBCConnector.GetConnection();
        }
        ArrayList<String> names = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ProductQueries.getNameOfProductsGroups.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                names.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        System.out.println(names);
        return names;
    }
}
