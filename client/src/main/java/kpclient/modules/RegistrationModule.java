package kpclient.modules;

import client.ServerClient;
import kpclient.controllers.RegistrationController;
import pojo.User;
import request.RegistrationRequest;
import response.RegistrationResponse;

public class RegistrationModule {
    private final RegistrationController registrationController;
    private ServerClient serverClient;
    public RegistrationModule(RegistrationController registrationController){
        this.registrationController = registrationController;
        serverClient = ServerClient.ConnectToServer();
    }

    public RegistrationResponse SignIn(User user){
        serverClient = ServerClient.ConnectToServer();
        serverClient.SendRequest(new RegistrationRequest(user));
        return (RegistrationResponse) serverClient.GetResponse();
    }
}
