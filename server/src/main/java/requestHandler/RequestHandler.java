package requestHandler;

import JDBC.CompletedOrdersDAO;
import JDBC.ProductDAO;
import JDBC.SupplierDAO;
import JDBC.UserDAO;
import enums.UserRole;
import pojo.*;
import request.*;
import response.*;

import java.util.ArrayList;

public class RequestHandler {
    private UserDAO userDAO;
    private ProductDAO productDAO;
    private SupplierDAO supplierDAO;
    private CompletedOrdersDAO completedOrdersDAO;
    private User user = null;

    public IResponse HandleRequest(LoginRequest request) {
        if (userDAO == null) {
            userDAO = new UserDAO();
        }
        user = userDAO.FindUserByLogin((User)request.GetPOJO());
        User responseUser = user;
        String context = "";
        if(user != null){
            //responseUser.setWallet(user.getWallet() * userDAO.GetCurrencyRate(user.getUserCurrencyID()));
        }
        else{
            context = "Неверный логин или пароль";
        }
        LoginResponse loginResponse = new LoginResponse(responseUser,context);
        return loginResponse;
    }
    public IResponse HandleRequest(RegistrationRequest request) {
        if (userDAO == null) {
            userDAO = new UserDAO();
        }
        RegistrationResponse registrationResponse = new RegistrationResponse();
        registrationResponse.accepted = userDAO.InsertNewUser((User) request.GetPOJO());
        return registrationResponse;
    }
    public IResponse HandleRequest(LogoutRequest request) {
        user = null;
        userDAO.CloseConnection();
        userDAO = null;
        System.err.println("User logged out");
        return new LogoutResponse();
    }
    public IResponse HandleRequest(GetProductRequest request){
        if(productDAO == null){
            productDAO = new ProductDAO();
        }
        ArrayList<Product> products;
        products = productDAO.GetProducts(request.getGroupName());
        if(products == null){
            return new GetProductResponse(null,"Возникла ошибка");
        }
        return new GetProductResponse(products,"");
    }
    public IResponse HandleRequest(GetNameProductGroupsRequest request){
        if(productDAO == null)
            productDAO = new ProductDAO();
        ArrayList<String> names;
        names = productDAO.GetNameOfGroups();
        if(names == null){
            return new GetNameProductGroupsResponse(null,"Возникла ошибка");
        }
        return new GetNameProductGroupsResponse(names,"");
    }

    public IResponse HandleRequest(GetSuppliersRequest request){
        if(supplierDAO == null)
            supplierDAO = new SupplierDAO();
        ArrayList<Supplier> suppliers;
        suppliers = supplierDAO.GetSuppliers();
        if(suppliers == null){
            return new GetSuppliersResponse(null,"Возникла ошибка");
        }
        return new GetSuppliersResponse(suppliers,"");
    }

    /*public IResponse HandleRequest(GetCreatorContentRequest request){
        if(contentDAO == null){
            contentDAO = new ContentDAO();
        }
        ArrayList<Content> contentList = contentDAO.GetCreatorContent(((User)request.GetPOJO()).getId());
        if(contentList == null) {
            return new GetCreatorContentResponse(null,"Возникла ошибка");
        }
        return new GetCreatorContentResponse(contentList,"");
    }*/
   /* public IResponse HandleRequest(GetLibraryRequest request){
        if(contentDAO == null){
            contentDAO = new ContentDAO();
        }
        if(user.getUserRole().equals(UserRole.admin))
        {
            return new GetLibraryResponse(null,"У администратора не может быть библиотеки");
        }
        var contentList = contentDAO.GetUsersLibrary(user.getId());
        String context = "";
        if(contentList == null)
        {
            context = "Произошла ошибка";
        }
        return new GetLibraryResponse(contentList,context);
    }*/
    /*public IResponse HandleRequest(BecomeCreatorRequest request){
        if(userDAO == null){
            userDAO = new UserDAO();
        }
        if(user.getUserRole() != UserRole.user){
            return new BecomeCreatorResponse(null,"Вы не можете стать создателем");
        }
        if(!userDAO.BecomeCreator(user.getId())){
            return new BecomeCreatorResponse(null,"Возникла ошибка");
        }
        user.setUserRole(UserRole.creator);
        return new BecomeCreatorResponse(user,"");
    }*/
    /*public IResponse HandleRequest(BuyContentRequest request){
        if(userDAO == null){
            userDAO = new UserDAO();
        }
        if(contentDAO == null){
            contentDAO = new ContentDAO();
        }
        if(user.getUserRole().equals(UserRole.admin)){
            return new BuyContentResponse(null,"Вы не можете покупать товары");
        }
        float contentPrice = contentDAO.GetContentPrice(((Content)request.GetPOJO()).getContentID());
        float userCurrencyRate = userDAO.GetCurrencyRate(user.getUserCurrencyID());
        float userWallet = user.getWallet() * userCurrencyRate;
        if(contentPrice < 0){
            return new BuyContentResponse(null,"Произошла ошибка");
        }
        if(userWallet < contentPrice){
            return new BuyContentResponse(null,"Не достаточно денег");
        }
        if(userDAO.BuyContent(user.getId(), ((Content) request.GetPOJO()).getContentID())){
            user.setWallet((userWallet - contentPrice)/userCurrencyRate);
            userDAO.ChangeMoney(user.getId(),user.getWallet());
            User user1 = new User();
            user1.setWallet(user.getWallet());
            return new BuyContentResponse(user1,"");
        }
        return new BuyContentResponse(null,"Возникла ошибка");
    }*/
    /*public IResponse HandleRequest(AddMoneyRequest addMoneyRequest){
        if(userDAO == null){
            userDAO = new UserDAO();
        }
        float value = user.getWallet() + (Float)addMoneyRequest.GetPOJO();
        userDAO.ChangeMoney(user.getId(),value);
        user.setWallet(value);
        User user1 = new User();
        user1.setWallet(value);
        return new AddMoneyResponse(user1,"");
    }*/
    public IResponse HandleRequest(AddNewProductRequest request){
        if(productDAO == null)
        {
            productDAO = new ProductDAO();
        }
        AddNewProductResponse addNewProductResponse = new AddNewProductResponse();
        addNewProductResponse.accepted = productDAO.InsertNewProduct((Product) request.GetPOJO());
        return addNewProductResponse;
    }
    public IResponse HandleRequest(AddNewProductGroupRequest request){
        if(productDAO == null)
        {
            productDAO = new ProductDAO();
        }
        AddNewProductGroupResponse addNewProductGroupResponse = new AddNewProductGroupResponse();
        addNewProductGroupResponse.accepted = productDAO.AddNewProductGroup((String) request.GetPOJO());
        return addNewProductGroupResponse;
    }
    public IResponse HandleRequest(AddNewSupplierRequest request){
        if(supplierDAO == null)
        {
            supplierDAO = new SupplierDAO();
        }
        AddNewSupplierResponse addNewSupplierResponse = new AddNewSupplierResponse();
        addNewSupplierResponse.accepted = supplierDAO.AddNewSupplier((Supplier) request.GetPOJO());
        return addNewSupplierResponse;
    }

    /*public IResponse HandleRequest(UpdateProductRequest request){
        if(!user.getUserRole().equals(UserRole.creator)) {
            return new UpdateContentResponse(true, "Вы не можете изменять контент");
        }
        if(!contentDAO.UpdateContent((Content) request.GetPOJO())) {
            return new AddnewContentResponse(false,"Возникла ошибка");
        }
        return new UpdateContentResponse(true,"");
    }*/
    public IResponse HandleRequest(UpdateUserRequest request){
        if(!userDAO.UpdateUser((User)request.GetPOJO(), request.getName())){
            return new UpdateUserResponse(false,"Произошла ошибка");
        }
        return new UpdateUserResponse(true,"");
    }
    public IResponse HandleRequest(UpdateSelfUserRequest request){
        if(!userDAO.UpdateSelfUser((User)request.GetPOJO(), request.getName())){
            return new UpdateSelfUserResponse(false,"Произошла ошибка");
        }
        return new UpdateSelfUserResponse(true,"");
    }
    public IResponse HandleRequest(BlockRequest request){
        if(!userDAO.BlockUser((User)request.GetPOJO())){
            return new BlockResponse(false,"Произошла ошибка");
        }
        return new BlockResponse(true,"");
    }
    public IResponse HandleRequest(DeleteUserRequest request){
        if(!userDAO.DeleteUser((User)request.GetPOJO())){
            return new DeleteUserResponse(false,"Произошла ошибка");
        }
        return new DeleteUserResponse(true,"");
    }

    public IResponse HandleRequest(GetUsersRequest request){
        ArrayList<User> users;
        users = userDAO.GetUsers();
        if(users == null){
            return new GetUsersResponse(null,"Возникла ошибка");
        }
        return new GetUsersResponse(users,"");
    }
    public IResponse HandleRequest(AddNewOrderRequest request){
        if(productDAO == null)
        {
            productDAO = new ProductDAO();
        }
        AddNewOrderResponse addNewOrderResponse = new AddNewOrderResponse();
        addNewOrderResponse.accepted = productDAO.AddNewOrder((Order) request.GetPOJO());
        return addNewOrderResponse;
    }
    public IResponse HandleRequest(AddNewCompletedOrderRequest request){
        if(completedOrdersDAO == null)
        {
            completedOrdersDAO = new CompletedOrdersDAO();
        }
        AddNewCompletedOrderResponse addNewCompletedOrderResponse = new AddNewCompletedOrderResponse();
        addNewCompletedOrderResponse.accepted = completedOrdersDAO.AddNewOrder((CompletedOrder) request.GetPOJO());
        return addNewCompletedOrderResponse;
    }
    public IResponse HandleRequest(GetOrdersRequest request){
        ArrayList<Order> orders;
        if(productDAO == null)
        {
            productDAO = new ProductDAO();
        }
        orders = productDAO.GetOrders();
        if(orders == null){
            return new GetOrdersResponse(null,"Возникла ошибка");
        }
        return new GetOrdersResponse(orders,"");
    }
    public IResponse HandleRequest(DeleteOrderRequest request){
        if(!productDAO.DeleteOrder((Order)request.GetPOJO())){
            return new DeleteOrderResponse(false,"Произошла ошибка");
        }
        return new DeleteOrderResponse(true,"");
    }
    public IResponse HandleRequest(CreateNewUserRequest request) {
        if (userDAO == null) {
            userDAO = new UserDAO();
        }
        CreateNewUserResponse createNewUserResponse = new CreateNewUserResponse();
        createNewUserResponse.accepted = userDAO.InsertNewUser((User) request.GetPOJO());
        return createNewUserResponse;
    }
    public IResponse HandleRequest(UpdateProductRequest request){
        if(!productDAO.UpdateProduct((Product)request.GetPOJO(), request.getName())){
            return new UpdateProductResponse(false,"Произошла ошибка");
        }
        return new UpdateProductResponse(true,"");
    }
    public IResponse HandleRequest(GetCompletedOrdersRequest request){
        ArrayList<CompletedOrder> orders;
        if(completedOrdersDAO == null)
        {
            completedOrdersDAO = new CompletedOrdersDAO();
        }
        orders = completedOrdersDAO.GetCompletedOrders();
        if(orders == null){
            return new GetCompletedOrdersResponse(null,"Возникла ошибка");
        }
        return new GetCompletedOrdersResponse(orders,"");
    }

}
