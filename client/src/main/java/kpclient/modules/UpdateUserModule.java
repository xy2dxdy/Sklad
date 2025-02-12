package kpclient.modules;

import client.ServerClient;
import kpclient.controllers.RegistrationController;
import kpclient.controllers.UpdateUserController;
import pojo.Product;
import pojo.User;
import request.AddNewProductRequest;
import request.RegistrationRequest;
import request.UpdateUserRequest;
import response.AddNewProductResponse;
import response.RegistrationResponse;
import response.UpdateUserResponse;

public class UpdateUserModule
{
    private final UpdateUserController updateUserController;
    private ServerClient serverClient;
    public UpdateUserModule (UpdateUserController  updateUserController){
        this.updateUserController = updateUserController;
        serverClient = ServerClient.ConnectToServer();
    }
    public UpdateUserResponse UpdateUser(User user, String name){
        serverClient.SendRequest(new UpdateUserRequest(user, name));
        return (UpdateUserResponse) serverClient.GetResponse();
    }

}
