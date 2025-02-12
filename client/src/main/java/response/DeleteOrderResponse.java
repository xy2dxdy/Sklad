package response;

public class DeleteOrderResponse implements IResponse
{
    private final boolean updated;
    private final String context;

    public DeleteOrderResponse(boolean updated, String context) {
        this.updated = updated;
        this.context = context;
    }

    public boolean isUpdated() {
        return updated;
    }

    public String getContext() {
        return context;
    }
}
