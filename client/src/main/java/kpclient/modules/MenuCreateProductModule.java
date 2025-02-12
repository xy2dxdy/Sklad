package kpclient.modules;

import client.ServerClient;
import kpclient.controllers.MenuCreateProductController;
import kpclient.controllers.RegistrationController;
import pojo.Product;
import request.AddNewProductRequest;
import request.UpdateProductRequest;
import response.AddNewProductResponse;
import response.UpdateProductResponse;

public class MenuCreateProductModule
{
    private final MenuCreateProductController menuCreateProductController;
    private final ServerClient serverClient;
    public MenuCreateProductModule(MenuCreateProductController menuCreateProductController){
        this.menuCreateProductController = menuCreateProductController;
        serverClient = ServerClient.ConnectToServer();
    }

    public AddNewProductResponse AddNewProduct(Product product){
        serverClient.SendRequest(new AddNewProductRequest(product));
        return (AddNewProductResponse) serverClient.GetResponse();
    }
}
