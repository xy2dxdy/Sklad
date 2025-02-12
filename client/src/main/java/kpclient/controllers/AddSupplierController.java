package kpclient.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kpclient.Client;
import kpclient.modules.AddGroupProductsModule;
import kpclient.modules.AddSupplierModule;
import pojo.Product;
import pojo.Supplier;
import pojo.User;
import response.AddNewProductGroupResponse;
import response.AddNewSupplierResponse;

import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;

public class AddSupplierController {
    @FXML
    private TextField name;
    @FXML
    private TextField country;
    @FXML
    private TextField phoneNumber;
    private AddSupplierModule addSupplierModule;
    private User user;
    public void Initialize(User user)
    {
        this.user = user;
    }
    public AddSupplierController(){addSupplierModule = new AddSupplierModule(this);}
    @FXML
    public void OnBackButtonClicked(ActionEvent event)
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
    public void AddButtonClicked(ActionEvent event)
    {
        AddNewSupplierResponse addNewSupplierResponse = addSupplierModule.AddNewSupplier(SetSupplier());
        if(!addNewSupplierResponse.accepted){
            OnBackButtonClicked(event);
        }
    }
    private Supplier SetSupplier() {
        Supplier supplier = new Supplier(name.getText(), country.getText(), phoneNumber.getText());
        return supplier;
    }
}
