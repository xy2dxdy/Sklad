package kpclient.controllers.order;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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
import kpclient.modules.order.MenuCompleteOrdersAdminModule;
import kpclient.modules.order.MenuOrdersAdminModule;
import pojo.CompletedOrder;
import pojo.Order;
import pojo.User;
import response.AddNewCompletedOrderResponse;
import response.DeleteOrderResponse;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class MenuCompleteOrdersAdminController implements Initializable
{
    public DatePicker firstDate;
    public DatePicker lastDate;
    public TableView view;
    public TableColumn<CompletedOrder, String> name;
    public TableColumn<CompletedOrder, Integer> amount;
    public TableColumn<CompletedOrder, Double> price;
    public TableColumn<CompletedOrder, Date> orderDate;
    public TableColumn<CompletedOrder, Date> completeDate;
    public TableColumn<CompletedOrder, String> customer;

    private User user;
    private MenuCompleteOrdersAdminModule menuCompleteOrdersAdminModule;

    public MenuCompleteOrdersAdminController()
    {
        menuCompleteOrdersAdminModule = new MenuCompleteOrdersAdminModule();
    }

    public void Initialize(User user)
    {
        this.user = user;
    }
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        name.setCellValueFactory(new PropertyValueFactory<CompletedOrder, String>("nameProduct"));
        amount.setCellValueFactory(new PropertyValueFactory<CompletedOrder, Integer>("amount"));
        price.setCellValueFactory(new PropertyValueFactory<CompletedOrder, Double>("cost"));
        orderDate.setCellValueFactory(new PropertyValueFactory<CompletedOrder, Date>("orderDate"));
        completeDate.setCellValueFactory(new PropertyValueFactory<CompletedOrder, Date>("confirmationDate"));
        customer.setCellValueFactory(new PropertyValueFactory<CompletedOrder, String>("nameCustomer"));
        ArrayList<CompletedOrder> orders = menuCompleteOrdersAdminModule.GetCompletedOrders();
        view.setItems(FXCollections.observableArrayList(orders));
    }

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

    public void OnSearchButtonClicked(ActionEvent event) {
    }
}
