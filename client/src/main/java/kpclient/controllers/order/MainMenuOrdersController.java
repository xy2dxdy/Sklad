package kpclient.controllers.order;

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
import kpclient.controllers.AdminMainMenuController;
import kpclient.controllers.MainMenuSkladController;
import kpclient.controllers.MainMenuUserController;
import kpclient.controllers.WorkerMainMenuController;
import pojo.User;

import java.io.IOException;

public class MainMenuOrdersController {
    @FXML
    private Button currentOrders;
    @FXML
    private Button LogoutButtonClick;
    @FXML
    private Button completedOrders;
    private User user;

    public void Initialize(User user)
    {
        this.user = user;
    }
    public void LoadCurrentOrdersScene(ActionEvent event)
    {
        try {
            if(user.getUserRole() == UserRole.user) {
                FXMLLoader loader = new FXMLLoader(Client.class.getResource("orders/menuOrdersUser.fxml"));
                Parent root = loader.load();
                MenuOrdersUserController menuOrdersUserController = loader.getController();
                menuOrdersUserController.Initialize(user);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else {
                FXMLLoader loader = new FXMLLoader(Client.class.getResource("orders/menuOrdersAdmin.fxml"));
                Parent root = loader.load();
                MenuOrdersAdminController menuOrdersAdminController = loader.getController();
                menuOrdersAdminController.Initialize(user);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    public void LogoutButtonClick(ActionEvent event)
    {
        try {
            if(user.getUserRole() == UserRole.user)
            {
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
                FXMLLoader loader = new FXMLLoader(Client.class.getResource("menuSklad.fxml"));
                Parent root = loader.load();
                MainMenuSkladController mainMenuSkladController = loader.getController();
                mainMenuSkladController.Initialize(user);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void LoadCompletedOrdersScene(ActionEvent event)
    {
        try {
            if(user.getUserRole() == UserRole.user) {
                FXMLLoader loader = new FXMLLoader(Client.class.getResource("orders/menuCompleteOrdersUsers.fxml"));
                Parent root = loader.load();
                //MenuCompleteOrdersUserController menuOrdersUserController = loader.getController();
               // menuOrdersUserController.Initialize(user);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else {
                FXMLLoader loader = new FXMLLoader(Client.class.getResource("orders/menuCompleteOrdersAdmin.fxml"));
                Parent root = loader.load();
                MenuCompleteOrdersAdminController menuOrdersAdminController = loader.getController();
                menuOrdersAdminController.Initialize(user);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

}
