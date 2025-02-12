package kpclient.modules;

import client.ServerClient;
import kpclient.controllers.AddGroupProductsController;
import kpclient.controllers.AddSupplierController;
import pojo.Supplier;
import request.AddNewProductGroupRequest;
import request.AddNewSupplierRequest;
import response.AddNewProductGroupResponse;
import response.AddNewSupplierResponse;

public class AddSupplierModule
{
    private final AddSupplierController addSupplierController;
    private final ServerClient serverClient;
    public AddSupplierModule (AddSupplierController addSupplierController){
        this.addSupplierController = addSupplierController;
        serverClient = ServerClient.ConnectToServer();
    }

    public AddNewSupplierResponse AddNewSupplier(Supplier supplier){
        serverClient.SendRequest(new AddNewSupplierRequest(supplier));
        return (AddNewSupplierResponse) serverClient.GetResponse();
    }
}
