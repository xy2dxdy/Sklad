package kpclient.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kpclient.Client;
import kpclient.modules.CreateOrderModules;
import kpclient.modules.MenuUserConfirmOrderModule;
import pojo.Order;
import pojo.Product;
import pojo.User;
import response.AddNewOrderResponse;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class MenuUserConfirmOrderController {
    @FXML
    private TextField amount;
    @FXML
    private TextField country;
    @FXML
    private TextField adress;
    @FXML
    private Button cancel;
    @FXML
    private TextField name;
    @FXML
    private Button continueButton;
    private Order order = null;
    private CreateOrderModules createOrderModules;
    private User user;
    private Product product;
    public void Initialise(Product product, CreateOrderModules createOrderModules, User user)
    {
        order = new Order();
        this.order.setNameProduct(product.getName());
        this.order.setCost(product.getPrice());
        this.createOrderModules = createOrderModules;
        this.user = user;
        this.product = product;
    }
    @FXML
    public void OnCancelButtonClick(ActionEvent event)
    {
        try {
            FXMLLoader loader = new FXMLLoader(Client.class.getResource("menu-user-order.fxml"));
            Parent root = loader.load();
            CreateOrderController createOrderController = loader.getController();
            createOrderController.Initialize(user);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @FXML
    public void OnSubmitButtonClick(ActionEvent event) throws ParseException {
        order.setCountry(country.getText());
        order.setAdress(adress.getText());
        order.setNameCustomer(name.getText());
        order.setAmount(Integer.valueOf(amount.getText()));
        order.setCost(product.getPrice()*order.getAmount());
        order.setDate(new java.sql.Date((new java.util.Date()).getTime()));
        AddNewOrderResponse addNewOrderResponse = createOrderModules.AddNewOrder(order);
        try {
            FXMLLoader loader = new FXMLLoader(Client.class.getResource("menu-user.fxml"));
            Parent root = loader.load();
            MainMenuUserController mainMenuUserController = loader.getController();
            mainMenuUserController.Initialize(user);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    public Order getOrder(){return order;}
}
