package kpclient.modules;

import client.ServerClient;
import pojo.User;
import request.GetUsersRequest;
import request.LogoutRequest;
import response.GetUsersResponse;
import response.LogoutResponse;

import java.util.ArrayList;

public class WorkerMainMenuModule
{
    private final ServerClient serverClient;
    public WorkerMainMenuModule(){
        serverClient = ServerClient.ConnectToServer();
    }

    public LogoutResponse LogOut(){
        serverClient.SendRequest(new LogoutRequest());
        return (LogoutResponse) serverClient.GetResponse();
    }
   /* public ArrayList<User> GetUsers(){
        serverClient.SendRequest(new GetUsersRequest());
        return ((GetUsersResponse)serverClient.GetResponse()).getUsers();
    }*/
}
