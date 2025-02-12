package kpclient.controllers;

import javafx.scene.control.Alert;
import kpclient.Client;
import kpclient.modules.LoginModule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pojo.User;
import response.LoginResponse;
import java.io.IOException;

public class LoginController {
    private final LoginModule loginModule;
    @FXML
    private TextField login_field;
    @FXML
    private PasswordField password_field;

    public LoginController() {
        loginModule = new LoginModule(this);
    }

    @FXML
    public void onLoginButtonClick(ActionEvent event) {
        System.out.println(login_field.getText());
        System.out.println(password_field.getText());
        User user = new User(login_field.getText(), password_field.getText());
        LoginResponse loginResponse = loginModule.LogIn(user);
        if (loginResponse.getUser() == null) {
            System.out.println(loginResponse.getContext());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText(loginResponse.getContext());

            alert.showAndWait();
            return;
        }
        DecideMainMenuType(event, loginResponse.getUser());
    }

    @FXML
    public void onRegistrationButtonClick(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Client.class.getResource("menu-registration.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void DecideMainMenuType(ActionEvent event, User user) {
        switch (user.getUserRole()) {
            case user -> LoadUserMainMenu(event, user);
            case worker -> LoadWorkerMainMenu(event, user);
            case admin -> LoadAdminMainMenu(event, user);
        }
    }

    /*private void LoadMainMenu(ActionEvent event, User user) {
        try {
            ArrayList<Content> contentList = loginModule.GetContent();
            FXMLLoader loader = new FXMLLoader(Client.class.getResource("main-menu.fxml"));
            Parent root = loader.load();

            MainMenuController mainMenuController = loader.getController();
            mainMenuController.Initialize(user, contentList);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/
    private void LoadUserMainMenu(ActionEvent event, User user) {
        try {
            FXMLLoader loader = new FXMLLoader(Client.class.getResource("menu-user.fxml"));
            Parent root = loader.load();

            MainMenuUserController mainMenuUserController = loader.getController();
            mainMenuUserController.Initialize(user);

            /*AdminMainMenuController adminMainMenuController = loader.getController();
            adminMainMenuController.Initialize(user);*/

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void LoadAdminMainMenu(ActionEvent event, User user) {
        try {
            FXMLLoader loader = new FXMLLoader(Client.class.getResource("menuAdmin.fxml"));
            Parent root = loader.load();

            AdminMainMenuController adminMainMenuController = loader.getController();
            adminMainMenuController.Initialize(user);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void LoadWorkerMainMenu(ActionEvent event, User user) {
        try {
            FXMLLoader loader = new FXMLLoader(Client.class.getResource("menuWorker.fxml"));
            Parent root = loader.load();

            WorkerMainMenuController workerMainMenuController = loader.getController();
            workerMainMenuController.Initialize(user);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}