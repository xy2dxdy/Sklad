package request;

import pojo.User;

import java.io.Serializable;

public class UpdateSelfUserRequest implements IRequest
{
    private final User user;
    private String name;
    public void setName(String name){this.name = name;}
    public String getName(){return name;}
    public UpdateSelfUserRequest(User user, String name) {
        this.user = user;
        this.name = name;
    }
    @Override
    public Serializable GetPOJO() {
        return user;
    }
}
