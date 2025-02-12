package kpclient.controllers.order;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import kpclient.Client;
import kpclient.modules.ViewProductsModule;
import kpclient.modules.order.MenuOrdersUserModule;
import pojo.Order;
import pojo.Product;
import pojo.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class MenuOrdersUserController implements Initializable
{
    @FXML
    private DatePicker firstDate;
    @FXML
    private DatePicker lastDate;
    @FXML
    private TableView view;
    @FXML
    private TableColumn<Order, String> name;
    @FXML
    private TableColumn<Order, Integer> amount;
    @FXML
    private TableColumn<Order, Double> price;
    @FXML
    private TableColumn<Order, Date> orderDate;
    private MenuOrdersUserModule menuOrdersUserModule;
    private User user;
    public MenuOrdersUserController()
    {
        menuOrdersUserModule = new MenuOrdersUserModule();
    }

    public void Initialize(User user)
    {
        this.user = user;
    }

    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        name.setCellValueFactory(new PropertyValueFactory<Order, String>("nameProduct"));
        amount.setCellValueFactory(new PropertyValueFactory<Order, Integer>("amount"));
        price.setCellValueFactory(new PropertyValueFactory<Order, Double>("cost"));
        orderDate.setCellValueFactory(new PropertyValueFactory<Order, Date>("date"));
        ArrayList<Order> orders = menuOrdersUserModule.GetOrders();
        view.setItems(FXCollections.observableArrayList(orders));
    }

    @FXML
    public void OnBackButtonClicked(ActionEvent event) {
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
    public void OnSearchButtonClicked(ActionEvent actionEvent) {
    }
}
