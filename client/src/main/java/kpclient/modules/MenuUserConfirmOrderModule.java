package kpclient.modules;

import client.ServerClient;
import kpclient.controllers.CreateOrderController;
import pojo.Order;
import request.AddNewOrderRequest;
import response.AddNewOrderResponse;

public class MenuUserConfirmOrderModule
{
   /* private final CreateOrderController createOrderController;
    private final ServerClient serverClient;
    public CreateOrderModules(CreateOrderController createOrderController){
        this.createOrderController = createOrderController;
        serverClient = ServerClient.ConnectToServer();
    }

    public AddNewOrderResponse AddNewOrder(Order order){
        serverClient.SendRequest(new AddNewOrderRequest(order));
        return (AddNewOrderResponse) serverClient.GetResponse();
    }*/
}
