package kpclient.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import kpclient.Client;
import kpclient.modules.CreateOrderModules;
import kpclient.modules.ViewProductsModule;
import kpclient.modules.ViewSuppliersModule;
import pojo.Order;
import pojo.Product;
import pojo.Supplier;
import pojo.User;
import response.AddNewOrderResponse;
import response.AddNewSupplierResponse;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateOrderController implements Initializable
{
    @FXML
    private TextField amountToOrder;
    @FXML
    private ComboBox<String> groupProducts;
    @FXML
    private TableView view;
    @FXML
    private TableColumn<Product, String> name;
    @FXML
    private TableColumn<Product, Double> price;
    @FXML
    private TableColumn<Product, Integer> amount;
    private CreateOrderModules createOrderModules;
    private ViewProductsModule viewProductsModule;
    private User user;
    public void Initialize(User user)
    {
        this.user = user;
    }

    public CreateOrderController()
    {
        createOrderModules = new CreateOrderModules(this);
        viewProductsModule = new ViewProductsModule();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        price.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        amount.setCellValueFactory(new PropertyValueFactory<Product, Integer>("amount"));
        ArrayList<String> list = viewProductsModule.GetNames();
        groupProducts.setItems(FXCollections.observableArrayList(list));
        groupProducts.setOnAction((event) -> {
            String selectedItem = groupProducts.getSelectionModel().getSelectedItem();
            System.out.println("Selected item: " + selectedItem);
            Print(selectedItem);
        });
    }

    public void Print(String group)
    {
        ArrayList<Product> products = viewProductsModule.GetProducts(group);
        view.setItems(FXCollections.observableArrayList(products));

    }
    @FXML
    public void OnBackButtonClicked(ActionEvent event)
    {
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

    @FXML
    public void OnOrderButtonClicked(ActionEvent event)
    {
        Product product = (Product) view.getSelectionModel().getSelectedItem();
        //MenuUserConfirmOrderController menuUserConfirmOrderController = new MenuUserConfirmOrderController(product, createOrderModules);
        try {
            FXMLLoader loader = new FXMLLoader(Client.class.getResource("menuUserCinfirmOrder.fxml"));
            Parent root = loader.load();
            MenuUserConfirmOrderController menuUserConfirmOrderController = loader.getController();
            menuUserConfirmOrderController.Initialise(product, createOrderModules, user);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }    }
}
