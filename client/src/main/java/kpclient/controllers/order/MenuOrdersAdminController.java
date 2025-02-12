package kpclient.controllers.order;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import kpclient.Client;
import kpclient.controllers.MenuUserConfirmOrderController;
import kpclient.modules.order.MenuOrdersAdminModule;
import kpclient.modules.order.MenuOrdersUserModule;
import pojo.CompletedOrder;
import pojo.Order;
import pojo.Product;
import pojo.User;
import response.AddNewCompletedOrderResponse;
import response.AddNewOrderResponse;
import response.DeleteOrderResponse;
import response.UpdateUserResponse;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class MenuOrdersAdminController implements Initializable
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
    @FXML
    private TableColumn<Order, String> customer;
    private User user;
    private MenuOrdersAdminModule menuOrdersAdminModule;

    public MenuOrdersAdminController()
    {
        menuOrdersAdminModule = new MenuOrdersAdminModule();
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
        customer.setCellValueFactory(new PropertyValueFactory<Order, String>("nameCustomer"));
        ArrayList<Order> orders = menuOrdersAdminModule.GetOrders();
        view.setItems(FXCollections.observableArrayList(orders));
    }

    @FXML
    public void OnBackButtonClicked(ActionEvent event)
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
    public void OnSearchButtonClicked(ActionEvent actionEvent) {
    }
    @FXML
    public void OnCompleteButtonClicked(ActionEvent event)
    {
        Order order = (Order) view.getSelectionModel().getSelectedItem();
        AddNewCompletedOrderResponse addNewCompletedOrderResponse = menuOrdersAdminModule.AddNewCompletedOrder(new CompletedOrder(order));
        DeleteOrderResponse deleteOrderResponse = menuOrdersAdminModule.DeleteOrder(order);
        //MenuUserConfirmOrderController menuUserConfirmOrderController = new MenuUserConfirmOrderController(product, createOrderModules);
        /*try {
            FXMLLoader loader = new FXMLLoader(Client.class.getResource("menuUserCinfirmOrder.fxml"));
            Parent root = loader.load();
            MenuUserConfirmOrderController menuUserConfirmOrderController = loader.getController();
            menuUserConfirmOrderController.Initialise(product, createOrderModules, user);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }*/
        if(addNewCompletedOrderResponse.accepted == true)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успешно");
            alert.setHeaderText(null);
            alert.setContentText("Заказ успешно одобрен");

            alert.showAndWait();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Не удалось одобрить заказ");

            alert.showAndWait();
        }
    }
}
