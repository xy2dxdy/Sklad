package request;

import pojo.Order;
import pojo.User;

import java.io.Serializable;

public class DeleteOrderRequest implements IRequest
{
    private Order order;
    public DeleteOrderRequest(Order order)
    {
        this.order = order;
    }
    @Override
    public Serializable GetPOJO() {
        return order;
    }
}
