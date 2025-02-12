package response;

import pojo.Supplier;

import java.util.ArrayList;

public class GetSuppliersResponse implements IResponse
{
    private ArrayList<Supplier> suppliers;
    private String context;

    public GetSuppliersResponse(ArrayList<Supplier> suppliers, String context){
        this.suppliers = suppliers;
        this.context = context;
    }

    public String getContext() {
        return context;
    }

    public ArrayList<Supplier> getSuppliers() {
        return suppliers;
    }
}
