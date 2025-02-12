package kpclient.modules;

import client.ServerClient;
import kpclient.controllers.LoginController;
import pojo.User;
import request.LoginRequest;
import response.LoginResponse;

import java.util.ArrayList;

public class LoginModule {
    private final LoginController loginController;
    private ServerClient serverClient;
    public LoginModule(LoginController loginController){
        this.loginController = loginController;
        serverClient = ServerClient.ConnectToServer();
    }

    public LoginResponse LogIn(User user){
        serverClient = ServerClient.ConnectToServer();
        user.setPassword(user.getPassword());
        System.out.println(user.getUserName() + user.getPassword());
        serverClient.SendRequest(new LoginRequest(user));

        return (LoginResponse) serverClient.GetResponse();
    }
   /* public ArrayList<Content> GetContent(){
        GetContentRequest getContentRequest = new GetContentRequest(new User());
        serverClient.SendRequest(getContentRequest);
        return ((GetContentResponse) serverClient.GetResponse()).contentList;
    }*/
}
