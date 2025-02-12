package kpclient.modules;

import client.ServerClient;
import kpclient.controllers.EditProductController;
import kpclient.controllers.MenuCreateProductController;
import pojo.Product;
import request.AddNewProductRequest;
import request.UpdateProductRequest;
import response.AddNewProductResponse;
import response.UpdateProductResponse;

public class EditProductModule
{
    private EditProductController editProductController;
    private ServerClient serverClient;
    public EditProductModule(EditProductController editProductController){
        this.editProductController = editProductController;
        serverClient = ServerClient.ConnectToServer();
    }

    public UpdateProductResponse UpdateProduct(Product product, String name){
        serverClient.SendRequest(new UpdateProductRequest(product, name));
        return (UpdateProductResponse) serverClient.GetResponse();
    }
}
