package request;

import pojo.User;

import java.io.Serializable;

public class CreateNewUserRequest implements IRequest{
    private User user;

    public CreateNewUserRequest(User user) {
        this.user = user;
    }

    @Override
    public Serializable GetPOJO() {
        return user;
    }
}
