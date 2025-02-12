package kpclient.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kpclient.Client;
import kpclient.modules.EditProductModule;
import kpclient.modules.MenuCreateProductModule;
import kpclient.modules.ViewProductsModule;
import kpclient.modules.ViewSuppliersModule;
import pojo.Product;
import pojo.Supplier;
import pojo.User;
import request.UpdateProductRequest;
import response.UpdateProductResponse;
import response.UpdateUserResponse;

import java.io.IOException;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class EditProductController implements Initializable
{
    @FXML
    private TextField name;
    @FXML
    private TextField amount;
    @FXML
    private TextField price;
    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<String> supplier;
    @FXML
    private ComboBox<String> group;
    private User user;
    private ViewProductsModule viewProductsModule;
    private ViewSuppliersModule viewSuppliersModule;
    private EditProductModule editProductModule;
    private String nameProduct;

    public EditProductController(){
        editProductModule = new EditProductModule(this);
        viewSuppliersModule = new ViewSuppliersModule();
        viewProductsModule = new ViewProductsModule();
    }

    private Product setProduct()
    {
        Product product = new Product(supplier.getValue().toString(), group.getValue().toString(), name.getText(), Double.parseDouble(price.getText()), Integer.parseInt(amount.getText()), Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        return product;
    }

    public void Initialize(User user, Product product, ViewProductsModule viewProductsModule)
    {
        this.user = user;
        supplier.setValue(product.getSupplier());
        group.setValue(product.getProductsGroup());
        name.setText(product.getName());
        amount.setText(product.getAmount().toString());
        price.setText(product.getPrice().toString());
        date.setValue(product.getReceiptDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        nameProduct= product.getName();

    }
    public EditProductController(ViewProductsController viewProductsController)
    {
        this.viewProductsModule = viewProductsModule;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> list = viewProductsModule.GetNames();
        group.setItems(FXCollections.observableArrayList(list));
        /*group.setOnAction((event) -> {
            String selectedItem = group.getSelectionModel().getSelectedItem();
            System.out.println("Selected item: " + selectedItem);
        });*/
        ArrayList<Supplier> suppliers = viewSuppliersModule.GetSuppliers();
        ArrayList<String> names = new ArrayList<>();
        for(int i = 0; i < suppliers.size(); i++)
        {
            names.add(suppliers.get(i).getName());
        }
        supplier.setItems(FXCollections.observableArrayList(names));
       /* supplier.setOnAction((event) -> {
            String selectedItem = supplier.getSelectionModel().getSelectedItem();
            System.out.println("Selected item: " + selectedItem);
        });*/

    }
    @FXML
    public void OnBackButtonClicked(ActionEvent event)
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
    public void EditButtonClicked(ActionEvent event)
    {
        UpdateProductResponse updateProductResponse = editProductModule.UpdateProduct(setProduct(), nameProduct);
        OnBackButtonClicked(event);
        if(updateProductResponse.getUpdated() == true)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успешно");
            alert.setHeaderText(null);
            alert.setContentText("Информация о товаре успешно изменена");

            alert.showAndWait();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Не удалось изменить информацию о товаре");

            alert.showAndWait();
        }
    }
}
