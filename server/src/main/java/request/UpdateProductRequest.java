package request;

import pojo.Product;

import java.io.Serializable;

public class UpdateProductRequest implements IRequest{
    private Product product;
    private String name;

    @Override
    public Serializable GetPOJO() {
        return product;
    }
    public String getName(){return name;}
    public UpdateProductRequest(Product product, String name){
        this.product = product;
        this.name = name;
    }
}