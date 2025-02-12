package request;

import pojo.User;

import java.io.Serializable;

public class RegistrationRequest implements IRequest {
    private User user;

    public RegistrationRequest(User user) {
        this.user = user;
    }

    @Override
    public Serializable GetPOJO() {
        return user;
    }
}