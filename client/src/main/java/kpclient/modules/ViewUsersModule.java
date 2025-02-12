package kpclient.modules;

import client.ServerClient;
import pojo.Product;
import pojo.User;
import request.GetNameProductGroupsRequest;
import request.GetProductRequest;
import request.GetUsersRequest;
import response.GetNameProductGroupsResponse;
import response.GetProductResponse;
import response.GetUsersResponse;

import java.util.ArrayList;

public class ViewUsersModule
{
    private ServerClient serverClient;
    public ViewUsersModule  (){
        serverClient = ServerClient.ConnectToServer();
    }

    public ArrayList<User> GetUsers(){
        serverClient.SendRequest(new GetUsersRequest());
        return ((GetUsersResponse)serverClient.GetResponse()).getUsers();
    }
}
