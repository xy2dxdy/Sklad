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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import kpclient.Client;
import kpclient.modules.MenuCreateProductModule;
import kpclient.modules.ViewProductsModule;
import pojo.Product;
import pojo.User;
import request.GetUsersRequest;
import response.GetNameProductGroupsResponse;
import response.GetUsersResponse;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ViewProductsController implements Initializable
{
    @FXML
    private TableView view;
    @FXML
    private TableColumn<Product, String> supplier;
    @FXML
    private TableColumn<Product, String> group;
    @FXML
    private TableColumn<Product, Double> price;
    @FXML
    private TableColumn<Product, String> name;
    @FXML
    private TableColumn<Product, Integer> amount;
    @FXML
    private TableColumn<Product, Date> date;
    @FXML
    private ComboBox<String> groupProducts;
    private ViewProductsModule viewProductsModule;
    private User user;

    public void Initialize(User user)
    {
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        supplier.setCellValueFactory(new PropertyValueFactory<Product, String>("supplier"));
        group.setCellValueFactory(new PropertyValueFactory<Product, String>("productsGroup"));
        price.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        name.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        amount.setCellValueFactory(new PropertyValueFactory<Product, Integer>("amount"));
        date.setCellValueFactory(new PropertyValueFactory<Product, Date>("receiptDate"));
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

    public ViewProductsController()
    {
        viewProductsModule = new ViewProductsModule();
    }
    @FXML
    public void OnBackButtonClicked(ActionEvent event)
    {
        try {
            FXMLLoader loader = new FXMLLoader(Client.class.getResource("menuSklad.fxml"));
            Parent root = loader.load();
            MainMenuSkladController mainMenuSkladController = loader.getController();
            mainMenuSkladController.Initialize(user);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void OnEditButtonClicked(ActionEvent event)
    {
        Product product = (Product) view.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(Client.class.getResource("menuEditProduct.fxml"));
            Parent root = loader.load();
            EditProductController editProductController = loader.getController();
            editProductController.Initialize(user, product, viewProductsModule);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void OnDeleteButtonClicked(ActionEvent actionEvent) {
    }
}
