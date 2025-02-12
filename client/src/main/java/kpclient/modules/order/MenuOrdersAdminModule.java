package kpclient.modules.order;

import client.ServerClient;
import pojo.CompletedOrder;
import pojo.Order;
import request.AddNewCompletedOrderRequest;
import request.AddNewOrderRequest;
import request.DeleteOrderRequest;
import request.GetOrdersRequest;
import response.AddNewCompletedOrderResponse;
import response.AddNewOrderResponse;
import response.DeleteOrderResponse;
import response.GetOrdersResponse;

import java.util.ArrayList;

public class MenuOrdersAdminModule
{
    private ServerClient serverClient;
    public MenuOrdersAdminModule (){
        serverClient = ServerClient.ConnectToServer();
    }

    public ArrayList<Order> GetOrders()
    {
        serverClient.SendRequest(new GetOrdersRequest());
        return ((GetOrdersResponse)serverClient.GetResponse()).getOrders();
    }
    public AddNewCompletedOrderResponse AddNewCompletedOrder(CompletedOrder order){
        serverClient.SendRequest(new AddNewCompletedOrderRequest(order));
        return (AddNewCompletedOrderResponse) serverClient.GetResponse();
    }
    public DeleteOrderResponse DeleteOrder(Order order)
    {
        serverClient.SendRequest(new DeleteOrderRequest(order));
        return (DeleteOrderResponse) serverClient.GetResponse();
    }
}
