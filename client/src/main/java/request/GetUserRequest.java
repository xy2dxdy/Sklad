package request;

import java.io.Serializable;

public class GetUserRequest implements IRequest
{
    private String name;
    public GetUserRequest(String name)
    {
        this.name = name;
    }
    @Override
    public Serializable GetPOJO() {
        return null;
    }
}
