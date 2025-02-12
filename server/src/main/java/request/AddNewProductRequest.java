package request;

import pojo.Product;

import java.io.Serializable;

public class AddNewProductRequest implements IRequest{
    private Product product;

    @Override
    public Serializable GetPOJO() {
        return product;
    }

    public AddNewProductRequest(Product product){
        this.product = product;
    }
}

