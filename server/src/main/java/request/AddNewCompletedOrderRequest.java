package request;

import pojo.CompletedOrder;
import pojo.Order;

import java.io.Serializable;

public class AddNewCompletedOrderRequest implements IRequest
{
    private CompletedOrder order;

    @Override
    public Serializable GetPOJO() {
        return order;
    }

    public AddNewCompletedOrderRequest(CompletedOrder order){
        this.order = order;
    }
}
