package response;

import pojo.Order;

import java.util.ArrayList;

public class GetOrdersResponse implements IResponse
{
    private ArrayList<Order> orders;
    private String context;

    public GetOrdersResponse(ArrayList<Order> orders, String context){
        this.orders = orders;
        this.context = context;
    }

    public String getContext() {
        return context;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }
}
