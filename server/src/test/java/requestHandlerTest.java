
import JDBC.ProductDAO;
import JDBC.UserDAO;
import pojo.Order;
import request.CreateNewUserRequest;
import request.GetOrdersRequest;
import request.GetUsersRequest;
import request.LoginRequest;
import requestHandler.RequestHandler;
import net.bytebuddy.jar.asm.Handle;
import enums.RequestType;
import enums.UserRole;
import pojo.User;
import response.GetOrdersResponse;
import response.GetUsersResponse;
import response.LoginResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class requestHandlerTest {

    @Mock
    UserDAO userDAO;
    @Mock
    ProductDAO productDAO;
    @Mock
    User user;
    @InjectMocks
    RequestHandler requestHandler;

    @Test
    void Test_Login_with_Valid_Data(){
        User user  = new User();
        when(userDAO.FindUserByLogin(user)).thenReturn(user);
        LoginResponse expected = new LoginResponse(user,"");
        LoginResponse real = (LoginResponse) requestHandler.HandleRequest(new LoginRequest(user));
        Assertions.assertEquals(expected.getUser(),real.getUser());
    }
    @Test
    void Test_Login_with_Invalid_Data(){
        User user  = new User();
        when(userDAO.FindUserByLogin(user)).thenReturn(null);
        LoginResponse expected = new LoginResponse(null,"Неверный логин или пароль");
        LoginResponse real = (LoginResponse) requestHandler.HandleRequest(new LoginRequest(user));
        Assertions.assertEquals(expected.getContext(),real.getContext());
    }
    @Test
    void Test_GetUsers_When_Error(){
        when(userDAO.GetUsers()).thenReturn(null);
        var real = (GetUsersResponse) requestHandler.HandleRequest(new GetUsersRequest());
        Assertions.assertNull(real.getUsers());
    }
    @Test
    void Test_GetUsers_With_Valid_Data(){
        ArrayList<User> users = new ArrayList<>();
        for(int i = 0;i < 10;i++){
            users.add(new User());
        }
        when(userDAO.GetUsers()).thenReturn(users);
        var real = (GetUsersResponse) requestHandler.HandleRequest(new GetUsersRequest());
        Assertions.assertEquals(10,real.getUsers().size());
    }

    @Test
    void Test_GetOrders_With_Valid_Data(){
        ArrayList<Order> orders = new ArrayList<>();
        for(int i = 0;i < 10;i++){
            orders.add(new Order());
        }
        when(productDAO.GetOrders()).thenReturn(orders);
        var real = (GetOrdersResponse) requestHandler.HandleRequest(new GetOrdersRequest());
        Assertions.assertEquals(10,real.getOrders().size());
    }

    @Test
    void Test_GetOrders_When_Error(){
        when(productDAO.GetOrders()).thenReturn(null);
        var real = (GetOrdersResponse) requestHandler.HandleRequest(new GetOrdersRequest());
        Assertions.assertNull(real.getOrders());
    }

}
