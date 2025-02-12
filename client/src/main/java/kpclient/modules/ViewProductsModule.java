package kpclient.modules;

import client.ServerClient;
import kpclient.controllers.RegistrationController;
import kpclient.controllers.ViewProductsController;
import pojo.Product;
import pojo.User;
import request.GetNameProductGroupsRequest;
import request.GetProductRequest;
import request.LogoutRequest;
import response.GetNameProductGroupsResponse;
import response.GetProductResponse;

import java.util.ArrayList;

public class ViewProductsModule
{
    private ServerClient serverClient;
    public ViewProductsModule (){
        serverClient = ServerClient.ConnectToServer();
    }

    public ArrayList<String> GetNames(){
        serverClient.SendRequest(new GetNameProductGroupsRequest());
        return ((GetNameProductGroupsResponse)serverClient.GetResponse()).getNames();
    }
    public ArrayList<Product> GetProducts(String name)
    {
        GetProductRequest request = new GetProductRequest();
        request.setGroupName(name);
        serverClient.SendRequest(request);
        return ((GetProductResponse)serverClient.GetResponse()).getProducts();
    }
}
