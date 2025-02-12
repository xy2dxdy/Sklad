package kpclient.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import kpclient.Client;
import kpclient.controllers.order.MainMenuOrdersController;
import kpclient.modules.MenuCreateProductModule;
import kpclient.modules.RegistrationModule;
import kpclient.modules.ViewProductsModule;
import kpclient.modules.ViewSuppliersModule;
import pojo.Product;
import pojo.Supplier;
import pojo.User;
import response.AddNewProductResponse;
import response.RegistrationResponse;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;


public class MenuCreateProductController implements Initializable
{
    @FXML
    private ComboBox<String> group;
    @FXML
    private ComboBox<String> supplier;
    @FXML
    private TextField name;
    @FXML
    private TextField amount;
    @FXML
    private TextField price;
    @FXML
    private DatePicker date;
    private Product product = null;
    private ViewProductsModule viewProductsModule;
    private ViewSuppliersModule viewSuppliersModule;
    private MenuCreateProductModule menuCreateProductModule;
    private User user;

    public void Initialize(User user)
    {
        this.user = user;
    }

    private Scene scene;
    private Stage stage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> list = viewProductsModule.GetNames();
        group.setItems(FXCollections.observableArrayList(list));
        group.setOnAction((event) -> {
            String selectedItem = group.getSelectionModel().getSelectedItem();
            System.out.println("Selected item: " + selectedItem);
        });
        ArrayList<Supplier> suppliers = viewSuppliersModule.GetSuppliers();
        ArrayList<String> names = new ArrayList<>();
        for(int i = 0; i < suppliers.size(); i++)
        {
            names.add(suppliers.get(i).getName());
        }
        supplier.setItems(FXCollections.observableArrayList(names));
        supplier.setOnAction((event) -> {
            String selectedItem = supplier.getSelectionModel().getSelectedItem();
            System.out.println("Selected item: " + selectedItem);
        });

    }



    public MenuCreateProductController(){
        menuCreateProductModule = new MenuCreateProductModule(this);
        viewProductsModule = new ViewProductsModule();
        viewSuppliersModule = new ViewSuppliersModule();
    }

    private Product SetProduct() {
        Product product = new Product(supplier.getValue().toString(), group.getValue().toString(), name.getText(), Double.parseDouble(price.getText()), Integer.parseInt(amount.getText()), Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        return product;
    }

    @FXML
    public void OnBackButtonClicked(ActionEvent event)
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

    @FXML
    public void AddButtonClicked(ActionEvent event) throws ParseException {
        AddNewProductResponse addNewProductResponse = menuCreateProductModule.AddNewProduct(SetProduct());
        if(!addNewProductResponse.accepted){
            return;
        }
    }
}
