package request;

import pojo.User;

import java.io.Serializable;

public class BlockRequest implements IRequest
{
    private User user;
    public BlockRequest(User user)
    {
        this.user = user;
    }
    @Override
    public Serializable GetPOJO() {
        return user;
    }
}
