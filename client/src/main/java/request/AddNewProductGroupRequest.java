package request;

import pojo.Product;

import java.io.Serializable;

public class AddNewProductGroupRequest implements IRequest
{
    private String group;

    @Override
    public Serializable GetPOJO() {
        return group;
    }

    public AddNewProductGroupRequest(String group){
        this.group = group;
    }
}
