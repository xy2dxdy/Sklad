package request;

import pojo.Supplier;

import java.io.Serializable;

public class AddNewSupplierRequest implements IRequest
{
    private Supplier supplier;

    @Override
    public Serializable GetPOJO() {
        return supplier;
    }

    public AddNewSupplierRequest(Supplier supplier){
        this.supplier = supplier;
    }
}
