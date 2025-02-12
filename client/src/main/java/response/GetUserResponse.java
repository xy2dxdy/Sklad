package response;

import pojo.User;

public class GetUserResponse implements IResponse
{
    private User user;
    private String context;

    public GetUserResponse(User users, String context){
        this.user = users;
        this.context = context;
    }

    public String getContext() {
        return context;
    }

    public User getUser() {
        return user;
    }
}
