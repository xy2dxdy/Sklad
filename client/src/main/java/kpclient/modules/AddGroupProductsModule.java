package kpclient.modules;

import client.ServerClient;
import kpclient.controllers.AddGroupProductsController;
import kpclient.controllers.MenuCreateProductController;
import pojo.Product;
import request.AddNewProductGroupRequest;
import request.AddNewProductRequest;
import response.AddNewProductGroupResponse;
import response.AddNewProductResponse;

public class AddGroupProductsModule
{
    private final AddGroupProductsController addGroupProductsController;
    private final ServerClient serverClient;
    public AddGroupProductsModule (AddGroupProductsController addGroupProductsController){
        this.addGroupProductsController = addGroupProductsController;
        serverClient = ServerClient.ConnectToServer();
    }

    public AddNewProductGroupResponse AddNewProductGroup(String group){
        serverClient.SendRequest(new AddNewProductGroupRequest(group));
        return (AddNewProductGroupResponse) serverClient.GetResponse();
    }
}
