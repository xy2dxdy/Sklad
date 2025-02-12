package kpclient.modules.order;

import client.ServerClient;
import pojo.Order;
import pojo.Product;
import request.GetNameProductGroupsRequest;
import request.GetOrdersRequest;
import request.GetProductRequest;
import response.GetNameProductGroupsResponse;
import response.GetOrdersResponse;
import response.GetProductResponse;

import java.util.ArrayList;

public class MenuOrdersUserModule {
    private ServerClient serverClient;
    public MenuOrdersUserModule (){
        serverClient = ServerClient.ConnectToServer();
    }

    public ArrayList<Order> GetOrders()
    {
        serverClient.SendRequest(new GetOrdersRequest());
        return ((GetOrdersResponse)serverClient.GetResponse()).getOrders();
    }
}
