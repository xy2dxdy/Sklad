package kpclient.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kpclient.Client;
import kpclient.modules.AddGroupProductsModule;
import pojo.User;
import response.AddNewProductGroupResponse;

import java.io.IOException;

public class AddGroupProductsController
{
    @FXML
    private Button add;
    @FXML
    private TextField name;
    @FXML
    private Button back;
    private AddGroupProductsModule addGroupProductsModule;
    private User user;
    public void Initialize(User user)
    {
        this.user = user;
    }

    public AddGroupProductsController()
    {
        addGroupProductsModule = new AddGroupProductsModule(this);
    }
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
        AddNewProductGroupResponse addNewProductGroupResponse = addGroupProductsModule.AddNewProductGroup(name.getText());
        if(!addNewProductGroupResponse.accepted){
            OnBackButtonClicked(event);
        }

    }
}
