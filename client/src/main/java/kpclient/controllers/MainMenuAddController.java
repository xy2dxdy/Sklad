package kpclient.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import kpclient.Client;
import pojo.User;

import java.io.IOException;

public class MainMenuAddController
{
    @FXML
    private Button addProduct;
    @FXML
    private Button back;
    @FXML
    private Button addSupplier;
    @FXML
    private Button addProductGroup;
    private User user;

    public void Initialize(User user)
    {
        this.user = user;
    }
    public void OnAddProductButtonClicked(ActionEvent event)
    {
        try {
            FXMLLoader loader = new FXMLLoader(Client.class.getResource("add-product.fxml"));
            Parent root = loader.load();
            MenuCreateProductController menuCreateProductController = loader.getController();
            menuCreateProductController.Initialize(user);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void OnAddProductGroupClicked(ActionEvent event)
    {
        try {
            FXMLLoader loader = new FXMLLoader(Client.class.getResource("addGroupProducts.fxml"));
            Parent root = loader.load();
            AddGroupProductsController addGroupProductsController = loader.getController();
            addGroupProductsController.Initialize(user);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void OnAddSupplierClicked(ActionEvent event)
    {
        try {
            FXMLLoader loader = new FXMLLoader(Client.class.getResource("addSupplier.fxml"));
            Parent root = loader.load();
            AddSupplierController addSupplierController  = loader.getController();
            addSupplierController .Initialize(user);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void OnBackButtonClicked(ActionEvent event)
    {
        try {
            FXMLLoader loader = new FXMLLoader(Client.class.getResource("menuSklad.fxml"));
            Parent root = loader.load();
            MainMenuSkladController mainMenuSkladController  = loader.getController();
            mainMenuSkladController.Initialize(user);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
