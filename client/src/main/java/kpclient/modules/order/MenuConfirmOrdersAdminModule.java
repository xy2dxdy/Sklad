package kpclient.modules.order;

import client.ServerClient;
import kpclient.controllers.UpdateUserController;
import kpclient.controllers.order.MenuConfirmOrdersAdminController;
import pojo.CompletedOrder;
import pojo.User;
import request.AddNewCompletedOrderRequest;
import request.UpdateUserRequest;
import response.AddNewCompletedOrderResponse;
import response.UpdateUserResponse;

public class MenuConfirmOrdersAdminModule {
    private final MenuConfirmOrdersAdminController menuConfirmOrdersAdminController;
    private ServerClient serverClient;
    public MenuConfirmOrdersAdminModule (MenuConfirmOrdersAdminController menuConfirmOrdersAdminController){
        this.menuConfirmOrdersAdminController = menuConfirmOrdersAdminController;
        serverClient = ServerClient.ConnectToServer();
    }
    public AddNewCompletedOrderResponse AddNewCompletedOrder(CompletedOrder order){
        serverClient.SendRequest(new AddNewCompletedOrderRequest(order));
        return (AddNewCompletedOrderResponse) serverClient.GetResponse();
    }
}
