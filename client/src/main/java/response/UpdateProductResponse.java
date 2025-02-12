package response;

public class UpdateProductResponse implements IResponse {
    private Boolean isUpdated;
    private String product;

    public UpdateProductResponse(Boolean isUpdated,String product){
        this.isUpdated = isUpdated;
        this.product = product;
    }

    public String getContext() {
        return product;
    }

    public Boolean getUpdated() {
        return isUpdated;
    }
}
