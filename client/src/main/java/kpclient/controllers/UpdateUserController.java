package kpclient.controllers;

import enums.UserRole;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kpclient.Client;
import kpclient.modules.UpdateUserModule;
import pojo.User;
import response.AddNewProductResponse;
import response.UpdateUserResponse;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UpdateUserController implements Initializable
{
    @FXML
    private TextField email;
    @FXML
    private ComboBox<UserRole> role;
    @FXML
    private TextField pattername;
    @FXML
    private PasswordField password;
    @FXML
    private TextField login;
    @FXML
    private TextField surname;
    @FXML
    private TextField name;
    private UpdateUserModule updateUserModule;
    private User user;
    private User thisUser;
    public void setUser(User user)
    {
        this.user = user;
    }
    public User getUser()
    {
        return user;
    }
    public UpdateUserController()
    {
        updateUserModule = new UpdateUserModule(this);
    }
    public void Initialize(User user, User thisUser){
        this.user = user;
        role.setValue(user.getUserRole());
        email.setText(user.getEmail());
        password.setText(user.getPassword());
        pattername.setText(user.getPattername());
        login.setText(user.getUserName());
        surname.setText(user.getSurname());
        name.setText(user.getName());
        this.thisUser = thisUser;
    }
    private User setUser()
    {
        User user = new User(login.getText(), password.getText(), email.getText(), surname.getText(), name.getText(), pattername.getText(), role.getValue());
        return user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        role.setItems(FXCollections.observableArrayList(UserRole.values()));
    }
    @FXML
    public void OnBackButtonClicked(ActionEvent event)
    {
        try {
            FXMLLoader loader = new FXMLLoader(Client.class.getResource("view-users.fxml"));
            Parent root = loader.load();
            ViewUsersController viewUsersController = loader.getController();
            viewUsersController.Initialize(thisUser);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
    @FXML
    public void OnContinueButtonClicked(ActionEvent event)
    {
        String name = user.getUserName();
        UpdateUserResponse updateUserResponse = updateUserModule.UpdateUser(setUser(), name);
        OnBackButtonClicked(event);
        if(updateUserResponse.isUpdated() == true)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успешно");
            alert.setHeaderText(null);
            alert.setContentText("Информация о пользователе успешно изменена");

            alert.showAndWait();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Неудалось изменить информацию о пользователе");

            alert.showAndWait();
        }

    }
}
