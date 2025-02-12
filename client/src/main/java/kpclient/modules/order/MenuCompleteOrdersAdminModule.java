package kpclient.modules.order;

import client.ServerClient;
import pojo.CompletedOrder;
import pojo.Order;
import request.AddNewCompletedOrderRequest;
import request.DeleteOrderRequest;
import request.GetCompletedOrdersRequest;
import request.GetOrdersRequest;
import response.AddNewCompletedOrderResponse;
import response.DeleteOrderResponse;
import response.GetCompletedOrdersResponse;
import response.GetOrdersResponse;

import java.util.ArrayList;

public class MenuCompleteOrdersAdminModule
{
    private ServerClient serverClient;
    public MenuCompleteOrdersAdminModule (){
        serverClient = ServerClient.ConnectToServer();
    }

    public ArrayList<CompletedOrder> GetCompletedOrders()
    {
        serverClient.SendRequest(new GetCompletedOrdersRequest());
        return ((GetCompletedOrdersResponse)serverClient.GetResponse()).getOrders();
    }

}
