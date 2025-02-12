package kpclient.controllers;

import client.ServerClient;
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
import kpclient.controllers.order.MainMenuOrdersController;
import kpclient.controllers.order.MenuOrdersUserController;
import kpclient.modules.MainMenuModule;
import pojo.User;
import request.GetUserRequest;
import request.GetUsersRequest;
import response.AddNewProductGroupResponse;
import response.GetUserResponse;
import response.GetUsersResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class MainMenuUserController {
    private MainMenuModule mainMenuModule;
    private ServerClient serverClient;
    @FXML
    private Button products;
    @FXML
    private Button orders;
    @FXML
    private Button edit;
    private User user;

    public MainMenuUserController() {
        mainMenuModule = new MainMenuModule();
        serverClient = ServerClient.ConnectToServer();

    }
    public void Initialize(User user)
    {
        this.user = user;
    }
    @FXML
    private void LogoutButtonClick(ActionEvent event) {
        mainMenuModule.LogOut();
        LoadLoginScene(event, "hello-view.fxml");
    }

    @FXML
    private void LoadLoginScene(ActionEvent event, String sceneName) {
        try {
            Parent root = FXMLLoader.load(Client.class.getResource(sceneName));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    @FXML
    private void LoadOrdersScene(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(Client.class.getResource("orders/mainMenuOrdersUsers.fxml"));
            Parent root = loader.load();
            MainMenuOrdersController mainMenuOrdersController = loader.getController();
            mainMenuOrdersController.Initialize(user);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @FXML
    private void LoadEditUserScene(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(Client.class.getResource("menu-selfEdit.fxml"));
            Parent root = loader.load();
            UpdateSelfUserController updateSelfUserController = loader.getController();
            updateSelfUserController.Initialize(user);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @FXML
    private void LoadProductsScene(ActionEvent event) {
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

}
