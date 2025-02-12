package kpclient.controllers;

import client.ServerClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kpclient.Client;
import kpclient.controllers.order.MainMenuOrdersController;
import kpclient.modules.AdminMainMenuModule;
import pojo.User;
import request.LogoutRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class AdminMainMenuController {
    public Button Orders;
    public Button LogoutButtonClick;
    private AdminMainMenuModule module;
    private User user;
    //private ArrayList<Content> contentList;
    private ArrayList<User> users;
    //private ArrayList<UserItem> userItems = new ArrayList<>();
    @FXML
    private Button usersButton;
    @FXML
    private Button sklad;

    @FXML
    private VBox userListVBox;
    public AdminMainMenuController(){
        module = new AdminMainMenuModule();
    }
    public void Initialize(User user){
        this.user = user;
        users = module.GetUsers();
    }

    private User GetUserFromList(int id){
        for(var user:users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }
    @FXML
    private void LogoutButtonClick(ActionEvent event) {
        module.LogOut();
        LoadLoginView(event);
    }

    private void LoadLoginView(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Client.class.getResource("hello-view.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void LoadUsersScene(ActionEvent event)
    {
        try {
            FXMLLoader loader = new FXMLLoader(Client.class.getResource("view-users.fxml"));
            Parent root = loader.load();
            ViewUsersController viewUsersController = loader.getController();
            viewUsersController.Initialize(user);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void LoadSkladScene(ActionEvent event)
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

    public void LoadOrdersScene(ActionEvent actionEvent) {
    }
}