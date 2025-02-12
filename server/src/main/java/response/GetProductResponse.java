package response;

import pojo.Product;

import java.util.ArrayList;

public class GetProductResponse implements IResponse{
    private ArrayList<Product> products;
    private String context;

    public GetProductResponse(ArrayList<Product> products, String context){
        this.products = products;
        this.context = context;
    }

    public String getContext() {
        return context;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}