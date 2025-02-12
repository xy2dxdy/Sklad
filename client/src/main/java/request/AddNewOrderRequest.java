package request;

import pojo.Order;
import pojo.Product;

import java.io.Serializable;

public class AddNewOrderRequest implements IRequest
{
    private Order order;

    @Override
    public Serializable GetPOJO() {
        return order;
    }

    public AddNewOrderRequest(Order order){
        this.order = order;
    }
}
