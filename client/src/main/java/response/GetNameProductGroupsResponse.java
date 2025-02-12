package response;

import java.util.ArrayList;

public class GetNameProductGroupsResponse implements IResponse
{
    private ArrayList<String> names;
    private String context;

    public GetNameProductGroupsResponse(ArrayList<String> names, String context){
        this.names = names;
        this.context = context;
    }

    public String getContext() {
        return context;
    }

    public ArrayList<String> getNames() {
        return names;
    }
}
