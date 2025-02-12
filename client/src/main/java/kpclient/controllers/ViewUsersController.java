package kpclient.controllers;

import enums.UserRole;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import kpclient.Client;
import kpclient.modules.BlockUserModule;
import kpclient.modules.DeleteUserModule;
import kpclient.modules.ViewUsersModule;
import pojo.Product;
import pojo.User;
import request.UpdateUserRequest;
import response.BlockResponse;
import response.DeleteUserResponse;
import response.UpdateUserResponse;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class ViewUsersController implements Initializable
{
    @FXML
    private TableView view;
    @FXML
    private TableColumn<User, String> login;
    @FXML
    private TableColumn<User, String> password;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, UserRole> role;
    @FXML
    private TableColumn<User, String> isBlocked;
    @FXML
    private TableColumn<User, String> surname;
    @FXML
    private TableColumn<User, String> name;
    @FXML
    private TableColumn<User, String> pattername;
    @FXML
    private Button back;
    @FXML
    private Button edit;
    @FXML
    private Button delete;
    @FXML
    private Button add;
    private ViewUsersModule viewUsersModule;
    private BlockUserModule blockModule;
    private DeleteUserModule deleteUserModule;
    private User user;
    public void Initialize(User user)
    {
        this.user = user;
    }

    public ViewUsersController()
    {
        viewUsersModule = new ViewUsersModule();
        blockModule = new BlockUserModule();
        deleteUserModule = new DeleteUserModule();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        login.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
        password.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        role.setCellValueFactory(new PropertyValueFactory<User, UserRole>("userRole"));
        isBlocked.setCellValueFactory(new PropertyValueFactory<User, String>("isBanned"));
        surname.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));
        name.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        pattername.setCellValueFactory(new PropertyValueFactory<User, String>("pattername"));
        ArrayList<User> list = viewUsersModule.GetUsers();
        view.setItems(FXCollections.observableArrayList(list));
    }
    @FXML
    public void OnBackButtonClicked(ActionEvent event)
    {
        try {
            FXMLLoader loader = new FXMLLoader(Client.class.getResource("menuAdmin.fxml"));
            Parent root = loader.load();
            AdminMainMenuController adminMainMenuController = loader.getController();
            adminMainMenuController.Initialize(user);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    @FXML
    public void OnEditButtonClicked(ActionEvent event)
    {
        User user = (User) view.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(Client.class.getResource("menuAdminEditUser.fxml"));
            Parent root = loader.load();
            UpdateUserController updateUserController = loader.getController();
            updateUserController.Initialize(user, this.user);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    @FXML
    public void OnDeleteButtonClicked(ActionEvent event)
    {
        User user = (User) view.getSelectionModel().getSelectedItem();
        DeleteUserResponse deleteUserResponse = deleteUserModule.DeleteUser(user);
        try {
            FXMLLoader loader = new FXMLLoader(Client.class.getResource("view-users.fxml"));
            Parent root = loader.load();
            ViewUsersController viewUsersController = loader.getController();
            viewUsersController.Initialize(this.user);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        if(deleteUserResponse.isUpdated() == true)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успешно");
            alert.setHeaderText(null);
            alert.setContentText("Пользователь успешно удалён");

            alert.showAndWait();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Неудалось удалить пользователя");

            alert.showAndWait();
        }

    }
    @FXML
    public void OnAddButtonClicked(ActionEvent event)
    {
        try {
            FXMLLoader loader = new FXMLLoader(Client.class.getResource("menuAdminAddUser.fxml"));
            Parent root = loader.load();
            CreateNewUserController createNewUserController = loader.getController();
            createNewUserController.Initialize(this.user);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void OnBlockButtonClicked(ActionEvent event)
    {
        User user = (User) view.getSelectionModel().getSelectedItem();
        BlockResponse blockResponse = blockModule.BlockUser(user);
        try {
            FXMLLoader loader = new FXMLLoader(Client.class.getResource("view-users.fxml"));
            Parent root = loader.load();
            ViewUsersController viewUsersController = loader.getController();
            viewUsersController.Initialize(this.user);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        if(blockResponse.isUpdated() == true)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успешно");
            alert.setHeaderText(null);
            alert.setContentText("Пользователь успешно заблокирован/разблокирован");

            alert.showAndWait();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Неудалось заблокировать/разблокировать пользователя");

            alert.showAndWait();
        }

    }
}
