package kpclient.controllers;

import enums.UserRole;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import kpclient.Client;
import kpclient.controllers.order.MainMenuOrdersController;
import kpclient.controllers.order.MenuOrdersUserController;
import pojo.User;

import java.io.IOException;

public class MainMenuSkladController
{

    @FXML
    private Button addProduct;
    @FXML
    private Button back;
    @FXML
    private Button orders;
    @FXML
    private Button productsOnSklad;
    @FXML
    private Button reports;
    private User user;
    public void Initialize(User user)
    {
        this.user = user;
    }

    @FXML
    public void OnBackButtonClicked(ActionEvent event)
    {
        try {
            if(user.getUserRole() == UserRole.admin) {
                FXMLLoader loader = new FXMLLoader(Client.class.getResource("menuAdmin.fxml"));

                Parent root = loader.load();
                AdminMainMenuController adminMainMenuController = loader.getController();
                adminMainMenuController.Initialize(user);


                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else
            {
                FXMLLoader loader = new FXMLLoader(Client.class.getResource("menuWorker.fxml"));

                Parent root = loader.load();
                WorkerMainMenuController workerMainMenuController = loader.getController();
                workerMainMenuController.Initialize(user);


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
    public void OnAddButtonClicked(ActionEvent event)
    {
        try {
            FXMLLoader loader = new FXMLLoader(Client.class.getResource("menuSkladAdd.fxml"));
            Parent root = loader.load();
            MainMenuAddController mainMenuAddController = loader.getController();
            mainMenuAddController.Initialize(user);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    @FXML
    public void OnOrdersButtonClicked(ActionEvent event)
    {
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
    public void OnProductsButtonClicked(ActionEvent event)
    {
        try {
            FXMLLoader loader = new FXMLLoader(Client.class.getResource("view-products.fxml"));
            Parent root = loader.load();
            ViewProductsController viewProductsController = loader.getController();
            viewProductsController.Initialize(user);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    @FXML
    public void OnReportsButtonClicked(ActionEvent actionEvent) {
    }
}
