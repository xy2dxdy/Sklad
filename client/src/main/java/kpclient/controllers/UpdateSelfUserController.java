package kpclient.controllers;

import enums.UserRole;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kpclient.Client;
import kpclient.modules.UpdateSelfUserModule;
import kpclient.modules.UpdateUserModule;
import pojo.User;
import response.UpdateSelfUserResponse;
import response.UpdateUserResponse;

import java.io.IOException;

public class UpdateSelfUserController
{
    @FXML
    private PasswordField password;
    @FXML
    private TextField login;
    @FXML
    private TextField surname;
    @FXML
    private TextField name;
    @FXML
    private TextField pattername;
    @FXML
    private TextField email;
    private UpdateSelfUserModule updateSelfUserModule;
    private User user;

    public void setUser(User user)
    {
        this.user = user;
    }
    public User getUser()
    {
        return user;
    }
    public UpdateSelfUserController()
    {
        updateSelfUserModule = new UpdateSelfUserModule(this);
    }
    public void Initialize(User user){
        this.user = user;
        email.setText(user.getEmail());
        password.setText(user.getPassword());
        pattername.setText(user.getPattername());
        login.setText(user.getUserName());
        surname.setText(user.getSurname());
        name.setText(user.getName());
    }
    private User setUser()
    {
        User user = new User(login.getText(), password.getText(), email.getText(), surname.getText(), name.getText(), pattername.getText());
        return user;
    }

    @FXML
    public void OnBackButtonClicked(ActionEvent event)
    {
        try {
            if(user.getUserRole() == UserRole.user) {
                FXMLLoader loader = new FXMLLoader(Client.class.getResource("menu-user.fxml"));
                Parent root = loader.load();
                MainMenuUserController mainMenuUserController = loader.getController();
                mainMenuUserController.Initialize(user);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else
            {
                FXMLLoader loader = new FXMLLoader(Client.class.getResource("menuWorker.fxml"));
                Parent root = loader.load();
                WorkerMainMenuController workerMenuUserController = loader.getController();
                workerMenuUserController.Initialize(user);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    @FXML
    public void OnContinueButtonClicked(ActionEvent event)
    {
        String name = user.getUserName();
        UpdateSelfUserResponse updateSelfUserResponse = updateSelfUserModule.UpdateUser(setUser(), name);
        OnBackButtonClicked(event);
        if(updateSelfUserResponse.isUpdated() == true)
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
