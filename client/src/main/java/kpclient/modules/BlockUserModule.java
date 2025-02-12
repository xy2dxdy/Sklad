package kpclient.modules;

import client.ServerClient;
import pojo.User;
import request.BlockRequest;
import response.BlockResponse;

public class BlockUserModule
{
    private ServerClient serverClient;
    public BlockUserModule(){
        serverClient = ServerClient.ConnectToServer();
    }
    public BlockResponse BlockUser(User user){
        serverClient.SendRequest(new BlockRequest(user));
        return (BlockResponse) serverClient.GetResponse();
    }

}
