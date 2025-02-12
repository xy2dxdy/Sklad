package kpclient.modules;

import client.ServerClient;
import kpclient.controllers.CreateNewUserController;
import kpclient.controllers.RegistrationController;
import pojo.User;
import request.CreateNewUserRequest;
import request.RegistrationRequest;
import request.UpdateUserRequest;
import response.CreateNewUserResponse;
import response.RegistrationResponse;
import response.UpdateUserResponse;

public class CreateNewUserModule
{
    private final CreateNewUserController createNewUserController;
    private ServerClient serverClient;
    public CreateNewUserModule(CreateNewUserController createNewUserController){
        this.createNewUserController = createNewUserController;
        serverClient = ServerClient.ConnectToServer();
    }

    public CreateNewUserResponse CreateNewUser(User user){
        serverClient.SendRequest(new CreateNewUserRequest(user));
        return (CreateNewUserResponse) serverClient.GetResponse();
    }
}
