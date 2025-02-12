package enums;

public enum SupplierQueries
{
    addSupplier("insert into suppliers (name, country, phoneNumber) VALUES (?, ?, ?)"),
    getSuppliers("select* from suppliers");
    private String query;

    SupplierQueries(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return query;
    }
}
