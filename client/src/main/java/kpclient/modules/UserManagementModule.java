package kpclient.modules;

import client.ServerClient;

public class UserManagementModule {
    private final ServerClient serverClient;

    public UserManagementModule(){
        serverClient = ServerClient.ConnectToServer();
    }

    /*public ArrayList<Content> GetContentByID(User user){
        serverClient.SendRequest(new GetCreatorContentRequest(user));
        return ((GetCreatorContentResponse)serverClient.GetResponse()).getContentList();
    }*/
    /*public UpdateUserResponse UpdateUser(User user){
        serverClient.SendRequest(new UpdateUserRequest(user));
        return ((UpdateUserResponse) serverClient.GetResponse());
    }*/
}

