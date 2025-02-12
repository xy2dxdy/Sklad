package kpclient.modules;

import client.ServerClient;
import kpclient.controllers.UpdateSelfUserController;
import kpclient.controllers.UpdateUserController;
import pojo.User;
import request.UpdateSelfUserRequest;
import request.UpdateUserRequest;
import response.UpdateSelfUserResponse;
import response.UpdateUserResponse;

public class UpdateSelfUserModule
{
    private final UpdateSelfUserController updateSelfUserController;
    private ServerClient serverClient;
    public UpdateSelfUserModule (UpdateSelfUserController  updateSelfUserController){
        this.updateSelfUserController = updateSelfUserController;
        serverClient = ServerClient.ConnectToServer();
    }
    public UpdateSelfUserResponse UpdateUser(User user, String name){
        serverClient.SendRequest(new UpdateSelfUserRequest(user, name));
        return (UpdateSelfUserResponse) serverClient.GetResponse();
    }
}
