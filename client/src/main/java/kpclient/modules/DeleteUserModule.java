package kpclient.modules;

import client.ServerClient;
import pojo.User;
import request.BlockRequest;
import request.DeleteUserRequest;
import response.BlockResponse;
import response.DeleteUserResponse;

public class DeleteUserModule
{
    private ServerClient serverClient;
    public DeleteUserModule (){
        serverClient = ServerClient.ConnectToServer();
    }
    public DeleteUserResponse DeleteUser(User user){
        serverClient.SendRequest(new DeleteUserRequest(user));
        return (DeleteUserResponse) serverClient.GetResponse();
    }
}
