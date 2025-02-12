package request;

import pojo.User;

import java.io.Serializable;

public class DeleteUserRequest implements IRequest
{
    private User user;
    public DeleteUserRequest(User user)
    {
        this.user = user;
    }
    @Override
    public Serializable GetPOJO() {
        return user;
    }
}
