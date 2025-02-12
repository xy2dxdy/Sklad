package request;


import java.io.Serializable;

public class GetProductRequest implements IRequest{
    String groupName;
    public void setGroupName(String groupName) {this.groupName = groupName;}
    public String getGroupName(){return groupName;}
    @Override
    public Serializable GetPOJO() {
        return null;
    }
}
