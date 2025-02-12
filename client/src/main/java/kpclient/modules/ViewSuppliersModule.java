package kpclient.modules;

import client.ServerClient;
import pojo.Supplier;
import request.GetNameProductGroupsRequest;
import request.GetSuppliersRequest;
import response.GetNameProductGroupsResponse;
import response.GetSuppliersResponse;

import java.util.ArrayList;

public class ViewSuppliersModule
{
    private ServerClient serverClient;
    public ViewSuppliersModule  (){
        serverClient = ServerClient.ConnectToServer();
    }

    public ArrayList<Supplier> GetSuppliers(){
        serverClient.SendRequest(new GetSuppliersRequest());
        return ((GetSuppliersResponse)serverClient.GetResponse()).getSuppliers();
    }
}
