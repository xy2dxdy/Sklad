package serverlauncher;

import requestHandler.RequestHandler;
import request.*;
import response.IResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private ServerSocket serverSocket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private RequestHandler requestHandler;
    private Socket socket;
    Server(ServerSocket serverSocket){
        try {
            this.serverSocket = serverSocket;
            socket = serverSocket.accept();
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            requestHandler = new RequestHandler();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("Client connected");
        while (!socket.isClosed()) {
            try {
                IRequest request = GetRequest();
                System.out.println(request.getClass());
                SendResponse(CreateResponse(request));
            } catch (IOException ioe) {
                System.err.println("Client Disconnected");
                CloseConnection();
            }catch (ClassNotFoundException cle){
                cle.printStackTrace();
                System.err.println("invalid Request");
            }
        }
    }

    private IRequest GetRequest()throws IOException,ClassNotFoundException{
        return (IRequest)objectInputStream.readObject();
    }
    private void SendResponse(IResponse response)throws IOException{
        objectOutputStream.writeObject(response);
    }
    private IResponse CreateResponse(IRequest request) {

        if (request.getClass() == LoginRequest.class) {
            return requestHandler.HandleRequest((LoginRequest) request);
        }
        if (request.getClass() == RegistrationRequest.class) {
            return requestHandler.HandleRequest((RegistrationRequest) request);
        }
        if (request.getClass() == LogoutRequest.class) {
            return requestHandler.HandleRequest((LogoutRequest) request);
        }
        if(request.getClass() == GetProductRequest.class) {
            return requestHandler.HandleRequest((GetProductRequest) request);
        }
        if(request.getClass() == AddNewProductRequest.class){
            return requestHandler.HandleRequest((AddNewProductRequest) request);
        }
        if(request.getClass() == GetUsersRequest.class){
            return requestHandler.HandleRequest((GetUsersRequest) request);
        }
        if(request.getClass() == UpdateUserRequest.class){
            return requestHandler.HandleRequest((UpdateUserRequest) request);
        }
        if(request.getClass() == GetNameProductGroupsRequest.class)
        {
            return requestHandler.HandleRequest((GetNameProductGroupsRequest) request);
        }
        if(request.getClass() == AddNewProductGroupRequest.class){
            return requestHandler.HandleRequest((AddNewProductGroupRequest) request);
        }
        if(request.getClass() == AddNewSupplierRequest.class){
            return requestHandler.HandleRequest((AddNewSupplierRequest) request);
        }
        if(request.getClass() == GetSuppliersRequest.class)
        {
            return requestHandler.HandleRequest((GetSuppliersRequest) request);
        }
        if(request.getClass() == BlockRequest.class)
        {
            return requestHandler.HandleRequest((BlockRequest) request);
        }
        if(request.getClass() == DeleteUserRequest.class)
        {
            return requestHandler.HandleRequest((DeleteUserRequest) request);
        }
        if(request.getClass() == AddNewOrderRequest.class)
        {
            return requestHandler.HandleRequest((AddNewOrderRequest) request);
        }
        if(request.getClass() == UpdateSelfUserRequest.class)
        {
            return requestHandler.HandleRequest((UpdateSelfUserRequest) request);
        }
        if(request.getClass() == AddNewCompletedOrderRequest.class)
        {
            return requestHandler.HandleRequest((AddNewCompletedOrderRequest) request);
        }
        if(request.getClass() == GetOrdersRequest.class)
        {
            return requestHandler.HandleRequest((GetOrdersRequest) request);
        }
        if(request.getClass() == DeleteOrderRequest.class)
        {
            return requestHandler.HandleRequest((DeleteOrderRequest) request);
        }
        if(request.getClass() == CreateNewUserRequest.class)
        {
            return requestHandler.HandleRequest((CreateNewUserRequest) request);
        }
        if(request.getClass() == UpdateProductRequest.class)
        {
            return requestHandler.HandleRequest((UpdateProductRequest) request);
        }
        if(request.getClass() == GetCompletedOrdersRequest.class)
        {
            return requestHandler.HandleRequest((GetCompletedOrdersRequest) request);
        }
        return null;
    }

    private void CloseConnection() {
        try {
            objectOutputStream.close();
            objectInputStream.close();
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}