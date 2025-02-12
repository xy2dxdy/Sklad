package kpclient.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kpclient.Client;
import kpclient.modules.AdminMainMenuModule;
import kpclient.modules.WorkerMainMenuModule;
import pojo.User;
import request.LogoutRequest;
import response.LogoutResponse;

import java.io.IOException;

public class WorkerMainMenuController
{
    private WorkerMainMenuModule module;
    private User user;
    public WorkerMainMenuController(){
        module = new WorkerMainMenuModule();
    }
    public void Initialize(User user){
        this.user = user;
    }
    @FXML
    public void LoadEditScene(ActionEvent event)
    {
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
    @FXML
    public void LogoutButtonClick(ActionEvent event)
    {
        module.LogOut();
        LoadLoginView(event);
    }
}
