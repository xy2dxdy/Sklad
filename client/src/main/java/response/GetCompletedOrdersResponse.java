package response;

import pojo.CompletedOrder;

import java.util.ArrayList;

public class GetCompletedOrdersResponse implements IResponse
{
    private ArrayList<CompletedOrder> orders;
    private String context;

    public GetCompletedOrdersResponse(ArrayList<CompletedOrder> orders, String context){
        this.orders = orders;
        this.context = context;
    }

    public String getContext() {
        return context;
    }

    public ArrayList<CompletedOrder> getOrders() {
        return orders;
    }
}
